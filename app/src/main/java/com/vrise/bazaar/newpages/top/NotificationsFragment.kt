package com.vrise.bazaar.newpages.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.newpages.top.temps.Misc
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.Preference

import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.NotificationsItem

class NotificationsFragment : Fragment() {

    lateinit var binding: com.vrise.databinding.FragmentNotificationsBinding
    var viewModel: NotificationsViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.progressBar27.visibility = View.VISIBLE
        binding.floatingActionButton9.visibility = View.GONE
        activity?.let {
            viewModel =
                ViewModelProviders.of(it,
                    NotificationsFactory(
                        NotificationsRepository(
                            null,
                            activity
                        )
                    )
                )
                    .get(NotificationsViewModel::class.java)
        }

        getS()

        binding.floatingActionButton9.setOnClickListener {
            Misc.println(Request(
                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
            ))
            activity?.let { it1 ->
                viewModel?.clearnotification(
                    Request(
                        utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
                    )
                )?.observe(it1, Observer {
                    getS()
                })
            }
        }

    }

    private fun getS() {
        binding.floatingActionButton9.visibility = View.GONE
        Misc.println(Request(
            utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
        ))
        viewModel?.getShopNotifications(
            Request(
                utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
            )
        )?.observe(requireActivity(), Observer {
            binding.recyclerView26.adapter =
                NotificationsAdapter(
                    viewModel,
                    activity,
                    it?.toMutableList(),
                    object : Interfaces.returnBundle {
                        override fun returnBundle(any: Any) {
                            getS()
                        }
                    })

            binding.floatingActionButton9.visibility = View.VISIBLE
            binding.progress.progressBar27.visibility = View.GONE
        })
    }

    class NotificationsAdapter(
        val viewModel: NotificationsViewModel?,
        val activity: FragmentActivity?,
        val notificationItems: MutableList<NotificationsItem?>?,
        val param: Interfaces.returnBundle
    ) :
        RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_notifications, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return notificationItems?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (notificationItems != null) {

                activity?.let {
                    if (notificationItems[holder.adapterPosition]?.status == "1") {
                        holder.textView293.setTextColor(ContextCompat.getColor(activity, R.color.denim_))
                    } else {
                        holder.textView293.setTextColor(ContextCompat.getColor(activity, R.color.text_heading))
                    }
                }

                holder.textView293.text = notificationItems[holder.adapterPosition]?.title
                holder.textView294.text = notificationItems[holder.adapterPosition]?.message
                holder.textView352.text = notificationItems[holder.adapterPosition]?.notifyOn
                
                holder.textView293.setOnClickListener {
                    viewnotifications(
                        holder.adapterPosition,
                        notificationItems[holder.adapterPosition]?.id ?: "",
                        notificationItems[holder.adapterPosition]
                    )
                }
                holder.textView294.setOnClickListener {
                    viewnotifications(
                        holder.adapterPosition,
                        notificationItems[holder.adapterPosition]?.id ?: "",
                        notificationItems[holder.adapterPosition]
                    )
                }
                holder.imageView62.setOnClickListener {
                    viewnotifications(
                        holder.adapterPosition,
                        notificationItems[holder.adapterPosition]?.id ?: "",
                        notificationItems[holder.adapterPosition]
                    )
                }
            }
        }

        private fun viewnotifications(
            s: Int,
            notId: String,
            notificationsItem: NotificationsItem?
        ) {
            activity?.let {
                if (notificationsItem?.status ?: "1" == "1") {
                    viewModel?.viewnotification(
                        Request(
                            utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
                            notId = notId
                        )
                    )?.observe(activity, Observer {
                        if (it) {
                            param.returnBundle(";")
                        }
                    })
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView293 = itemView.findViewById<TextView>(R.id.textView293)
            val textView294 = itemView.findViewById<TextView>(R.id.textView294)
            val imageView62 = itemView.findViewById<ImageView>(R.id.imageView62)
            val textView352 = itemView.findViewById<TextView>(R.id.textView352)
        }

    }

}

