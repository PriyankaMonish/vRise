package com.vrise.bazaar.ex.shop.pages.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.vrise.R
import com.vrise.bazaar.ex.model.StatusData
import com.vrise.bazaar.ex.model.TimeLineItem
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.util.Constants.ACTION_NOTIFICATION
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.serviceApi
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.order_show_fragment.*

class OrderShow : Fragment() {

    private lateinit var viewModel: OrderShowViewModel

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            println(intent?.extras?.getString(""))
            getData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(serviceApi(requireActivity()), requireActivity()))
        ).get(OrderShowViewModel::class.java)

        getData()

    }

    private fun getData() {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.orderstatus(
                    Request(
                        utoken = Preference.get(requireActivity(), DATAFILE, TOKEN),
                        order_id = arguments?.getString("order_id")
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it?.sellerStatus != "cancelled") {
                        if (it?.deliveryStatus != "cancelled") {
                            if (!requireActivity().isFinishing && isAdded) stepper_list.adapter =
                                TimeLineViewHolder(
                                    requireActivity(),
                                    it,
                                    object : Interfaces.ReturnAnyWithKey {
                                        override fun getValue(key: String, value: Any) {
                                            if (key == "cancel" && value is Request) {
                                                viewModel.cancelorder(value)?.observe(
                                                    viewLifecycleOwnerLiveData.value
                                                        ?: this@OrderShow,
                                                    Observer {
                                                        it?.let {
                                                            toast(
                                                                requireActivity(),
                                                                "Order Cancelled"
                                                            )
                                                            getData()
                                                        }
                                                    })
                                            }
                                        }
                                    })
                        } else {
                            val statusData = StatusData().apply {
                                id = it.id
                                timeLine = object : ArrayList<TimeLineItem?>() {
                                    init {
                                        add(
                                            TimeLineItem(
                                                dateTime = "",
                                                notifiType = "",
                                                status = "done",
                                                statusMessage = "This order was cancelled.",
                                                statusTitle = "Order Cancelled",
                                                typeId = ""
                                            )
                                        )
                                    }
                                }
                            }
                            stepper_list.adapter =
                                TimeLineViewHolder(requireActivity(), statusData, null)
                        }
                    } else {
                        view?.findNavController()?.popBackStack()
                    }
                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }
    }

    class TimeLineViewHolder(
        private val requireActivity: FragmentActivity,
        private val statusData: StatusData?,
        private val returnAnyVal: Interfaces.ReturnAnyWithKey?
    ) : RecyclerView.Adapter<TimeLineViewHolder.ViewHolder>() {

        private var timeLine: List<TimeLineItem?>? = null

        init {
            timeLine = statusData?.timeLine
        }

        class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
            val manda_view = itemView.findViewById<TextView?>(R.id.manda_view)
            val time_marker = itemView.findViewById<TimelineView?>(R.id.time_marker)
            val textView276 = itemView.findViewById<TextView?>(R.id.textView276)
            val textView280 = itemView.findViewById<TextView?>(R.id.textView280)
            val textView275 = itemView.findViewById<TextView?>(R.id.textView275)
            val imageView59 = itemView.findViewById<TextView?>(R.id.imageView59)

            init {
                time_marker?.initLine(viewType)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.time_line,
                    parent,
                    false
                ), viewType
            )
        }

        override fun getItemViewType(position: Int): Int {
            return TimelineView.getTimeLineViewType(position, itemCount)
        }

        override fun getItemCount(): Int {
            return timeLine?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            if (holder.adapterPosition == 0) holder.manda_view?.visibility =
                View.INVISIBLE else holder.manda_view?.visibility = View.VISIBLE
            holder.textView275?.isVisible = timeLine?.get(holder.adapterPosition)?.status == "now"

            holder.textView276?.text = timeLine?.get(holder.adapterPosition)?.statusTitle
            holder.textView275?.text =
                if (holder.textView275?.isVisible == true) timeLine?.get(holder.adapterPosition)?.statusMessage else ""
            holder.textView280?.text = timeLine?.get(holder.adapterPosition)?.status.toString()


            if (timeLine?.get(holder.adapterPosition)?.notifiType == "place_order" && timeLine?.get(
                    holder.adapterPosition
                )?.status == "now"
            ) {
                holder.imageView59?.isVisible = true
                holder.imageView59?.text = "Cancel Order"
                holder.imageView59?.setOnClickListener { view ->
                    returnAnyVal?.getValue(
                        "cancel",
                        Request(
                            utoken = Preference.get(requireActivity, DATAFILE, TOKEN),
                            order_id = statusData?.id
                        )
                    )
                }
            } else if (timeLine?.get(holder.adapterPosition)?.notifiType == "delivery_status" && timeLine?.get(
                    holder.adapterPosition
                )?.status == "now"
            ) {
                holder.imageView59?.isVisible = true
                holder.imageView59?.text = "Track"
                holder.imageView59?.setOnClickListener {
                    if (requireActivity is BazaarContainer) {
                        holder.imageView59.findNavController().navigate(
                            R.id.action_orderShow_to_trackPageFragment2,
                            bundleOf("order_id" to statusData?.id)
                        )
                    } else if (requireActivity is DashBoardContainer) {
                        holder.imageView59.findNavController().navigate(
                            R.id.action_orderShow2_to_trackPageFragment,
                            bundleOf("order_id" to statusData?.id)
                        )
                    }
                }
            } else {
                holder.imageView59?.isVisible = false
                holder.imageView59?.text = ""
                holder.imageView59?.setOnClickListener {}
            }

        }

    }

    override fun onResume() {
        super.onResume()
        try {
            broadcastReceiver.let { broadcastReceiver ->
                LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(broadcastReceiver, IntentFilter(ACTION_NOTIFICATION))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            broadcastReceiver.let { broadcastReceiver ->
                LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
