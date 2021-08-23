package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vrise.R
import com.vrise.bazaar.ex.model.ProductsRateItem
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.temp.OrdPastItem
import com.vrise.bazaar.ex.model.temp.OrdPendingItem
import com.vrise.bazaar.ex.model.temp.OrdProductsItem
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OrdersViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.changeDateFormat
import com.vrise.bazaar.ex.util.Instances.showBlur
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.item_rate_order.*
import kotlinx.android.synthetic.main.order_fragment.*
import java.lang.Boolean.FALSE


class OrdersFragment : Fragment() {
    private var customerNumbers: List<String?>? = null
    private lateinit var viewModel: OrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        progress.visibility = View.VISIBLE

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(OrdersViewModel::class.java)

        no_items.visibility = View.GONE
        textView49.text = ""
        button25.setOnClickListener {
            activity?.onBackPressed()
        }

        getDataVals()

    }

    private fun getDataVals() {
//        progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getItems(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        no_items.visibility = View.GONE
                        val items = ArrayList<Any?>()
                        for (i in 0 until (it.ordPending?.size ?: 0)) {
                            items.add(it.ordPending?.get(i))
                        }
                        for (i in 0 until (it.ordPast?.size ?: 0)) {
                            items.add(it.ordPast?.get(i))
                        }
                        var adapter: MyOrdersAdapter? = null
                        adapter = MyOrdersAdapter(activity,
                            viewModel,
                            items,
                            object : Interfaces.ReturnPos {
                                override fun getValue(position: String) {
                                    try {
                                        getDataVals()
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            },
                            object : Interfaces.ReturnAnyWithKey {
                                override fun getValue(key: String, value: Any) {
                                    if (key == "reorder") {
                                        reorder(value.toString(), 0.toString())
                                    }
                                }
                            })
                        recyclerView8.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        no_items.visibility = View.VISIBLE
                    }
//                    progress.visibility = View.GONE
                })
            } else {
                toast(activity, getString(R.string.no_internet))
                progress?.visibility = View.GONE
            }
        }
    }

    private fun reorder(value: String, changeSeller: String) {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.reorder(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                        order_id = value,
                        changeSeller = changeSeller
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        if (it.data?.shopChange == 1) {
                            val display =
                                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                                    .setCancelable(true).setTitle("Replace cart item?")
                                    .setMessage(it?.data?.display.toString())
                                    .setPositiveButton("YES") { dialog1, _ ->
                                        reorder(value, "1")
                                        dialog1?.dismiss()
                                    }.setNegativeButton("NO") { dialog1, _ ->
                                        dialog1?.dismiss()
                                    }.create()
                            display.setOnShowListener {
                                display.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.colorPrimary
                                    )
                                )
                                display.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.colorPrimary
                                    )
                                )
                            }
                            display.show()


                        } else {
                            when (requireActivity()) {
                                is DashBoardContainer -> {
                                    view?.findNavController()
                                        ?.navigate(R.id.action_navigation_my_order_to_navigation_cart)
                                }
                                is BazaarContainer -> {
                                    view?.findNavController()
                                        ?.navigate(R.id.action_navigation_my_order_to_navigation_cart2)
                                }
                                else -> {
                                }
                            }
                        }
                    }
                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }
    }

    class MyOrdersAdapter(
        val activity: FragmentActivity?,
        val viewModel: OrdersViewModel,
        val items: ArrayList<Any?>?,
        val observer: Interfaces.ReturnPos,
        private val observer2: Interfaces.ReturnAnyWithKey
    ) : RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var call = itemView.findViewById<FrameLayout?>(R.id.call)
            var track = itemView.findViewById<FrameLayout?>(R.id.track)
            var rateIt = itemView.findViewById<FrameLayout?>(R.id.rate)
            val imageView18 = itemView.findViewById<ImageView?>(R.id.imageView18)
            val textView50 = itemView.findViewById<TextView?>(R.id.textView50)
            val tetextView282xtView50 = itemView.findViewById<TextView?>(R.id.textView282)
            val textView51 = itemView.findViewById<TextView?>(R.id.textView51)
            val textView54 = itemView.findViewById<TextView?>(R.id.textView54)
            val textView55 = itemView.findViewById<TextView?>(R.id.textView55)
            val recyclerView23 = itemView.findViewById<RecyclerView?>(R.id.recyclerView23)
            val textView197 = itemView.findViewById<TextView?>(R.id.textView197)
            val textView240 = itemView.findViewById<TextView?>(R.id.textView240)
            val textView237 = itemView.findViewById<TextView?>(R.id.textView237)
            val textView236 = itemView.findViewById<TextView?>(R.id.textView236)
            val textView304 = itemView.findViewById<TextView?>(R.id.textView304)
            val textView305 = itemView.findViewById<TextView?>(R.id.textView305)
            val textView370 = itemView.findViewById<TextView?>(R.id.textView370)
            // val textView281 = itemView.findViewById<TextView?>(R.id.textView281)

            init {
                rateIt?.visibility = View.GONE
                track?.visibility = View.GONE
                call?.visibility = View.GONE
                tetextView282xtView50?.isVisible = false
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_my_order, parent, false
                )
            )
        }

        override fun getItemCount(): Int {
            return items?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             var customerNumbers: List<String?>? = null
            // Instances.printAny(items?.get(holder.adapterPosition))
            when {
                items?.get(holder.adapterPosition) is OrdPastItem -> {
                    holder.rateIt?.visibility = View.GONE
                    holder.track?.visibility = View.GONE
                    holder.call?.visibility = View.GONE
                    if (!(items[holder.adapterPosition] as OrdPastItem).ordProducts.isNullOrEmpty()) {
                        holder.recyclerView23?.adapter = MyOrderSubAdapter(
                            activity,
                            (items[holder.adapterPosition] as OrdPastItem).ordProducts,
                            observer
                        )
                    }
                    showBlur(
                        activity,
                        (items[holder.adapterPosition] as OrdPastItem).storeImg.toString(),
                        holder.imageView18
                    )
                    // holder.textView281?.text = (items[holder.adapterPosition] as OrdPastItem).storeName.toString()
                    holder.textView197?.text = try {
                        (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.slotName + " ( " + changeDateFormat(
                            "HH:mm:ss",
                            "hh:mm a",
                            (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.startTime.toString()
                        ) + "-" + changeDateFormat(
                            "HH:mm:ss",
                            "hh:mm a",
                            (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.endTime.toString()
                        ) + " )"
                    } catch (e: Exception) {
                        (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.slotName + " ( " + (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.startTime + "-" + (items[holder.adapterPosition] as OrdPastItem).deliverySlotData?.endTime + " )"
                    }
                    holder.textView50?.text =
                        (items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString()
                            .capitalize()
                    try {
                        if ((items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString() == "cancelled" || (items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString() == "not_delivered") {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                holder.textView50?.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!,
                                        android.R.color.holo_red_dark
                                    )
                                )
                            }
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                holder.textView50?.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!,
                                        android.R.color.holo_green_light
                                    )
                                )
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    if ((items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString() == "delivered") {
                        if ((items[holder.adapterPosition] as OrdPastItem).myreview == null) {
                            holder.rateIt?.visibility = View.VISIBLE
                            holder.rateIt?.setOnClickListener(object : ClickListener() {
                                override fun onOneClick(v: View) {
                                    rateOr(
                                        items[holder.adapterPosition] as OrdPastItem,
                                        holder.adapterPosition,
                                        observer
                                    )
                                }
                            })
                        }
                    }

                    (items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString()
                        .let {
                            if (it == "not_delivered" || it == "delivered" || it == "cancelled") {
                                holder.track?.visibility = View.GONE
                            } else {
                                holder.track?.visibility = View.VISIBLE
                            }

                            if ((items[holder.adapterPosition] as OrdPastItem).pickup_order=="1"){
                                holder.textView304?.visibility = View.VISIBLE
                                holder.textView305!!.visibility = View.VISIBLE
                                holder.textView236?.visibility = View.INVISIBLE
                                holder.textView237!!.visibility = View.INVISIBLE
                            }
                            else
                            {
                                holder.textView236?.visibility = View.VISIBLE
                                holder.textView237!!.visibility = View.VISIBLE
                                holder.textView304?.visibility = View.INVISIBLE
                                holder.textView305!!.visibility = View.INVISIBLE
                            }
                        }

                    if((items[holder.adapterPosition] as OrdPastItem).pickup_charge=="0"){
                        holder.textView305!!.visibility=View.GONE
                        holder.textView304!!.visibility=View.GONE
                    }else
holder.textView305!!.text=  "₹ ${(items[holder.adapterPosition] as OrdPastItem).pickup_charge.toString()}"
                    holder.textView51?.text =
                        "Order ID :" + (items[holder.adapterPosition] as OrdPastItem).code.toString()
                    holder.textView54?.text = changeDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        "dd/MM/yyyy hh:mm a",
                        (items[holder.adapterPosition] as OrdPastItem).orderDate.toString()
                    )
                    holder.textView55?.text =
                        "₹ ${(items[holder.adapterPosition] as OrdPastItem).totalPrize.toString()}"
                    holder.textView237?.text =
                        if ((items[holder.adapterPosition] as OrdPastItem).deliveryCharge.toString() == "0" || (items[holder.adapterPosition] as OrdPastItem).deliveryCharge.toString() == "0.0") {
                            "Free"
                        } else {
                            "₹ ${(Math.round((items[holder.adapterPosition] as OrdPastItem).deliveryCharge!!.toDouble()))}"
                        }
                    holder.textView240?.text =
                        "₹ ${(Math.round((items[holder.adapterPosition] as OrdPastItem).grandTotal!!.toDouble()))}"
                    holder.textView370?.text =
                        if ((items[holder.adapterPosition] as OrdPastItem).paymentStatus == "paid") {
                            "Amount Paid"
                        } else {
                            "Amount to be paid - ₹${(Math.round((items[holder.adapterPosition] as OrdPastItem).codDue!!.toDouble()))}"
                        }
                }
                items?.get(holder.adapterPosition) is OrdPendingItem -> {
                    holder.rateIt?.visibility = View.GONE
                    holder.track?.visibility = View.VISIBLE
                    holder.call?.visibility = View.GONE



                    if (!(items[holder.adapterPosition] as OrdPendingItem).ordProducts.isNullOrEmpty()) {
                        holder.recyclerView23?.adapter = MyOrderSubAdapter(
                            activity,
                            (items[holder.adapterPosition] as OrdPendingItem).ordProducts,
                            observer
                        )
                    }
                    holder.textView197?.text = try {
                        (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.slotName + " ( " + changeDateFormat(
                            "HH:mm:ss",
                            "hh:mm a",
                            (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.startTime.toString()
                        ) + "-" + changeDateFormat(
                            "HH:mm:ss",
                            "hh:mm a",
                            (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.endTime.toString()
                        ) + " )"
                    } catch (e: Exception) {
                        (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.slotName + " ( " + (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.startTime + "-" + (items[holder.adapterPosition] as OrdPendingItem).deliverySlotData?.endTime + " )"
                    }
                    showBlur(
                        activity,
                        (items[holder.adapterPosition] as OrdPendingItem).storeImg,
                        holder.imageView18
                    )
                    // holder.textView281?.text = (items[holder.adapterPosition] as OrdPendingItem).storeName.toString()
                    holder.textView50?.text =
                        (items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString()
                            .capitalize()
                    try {
                        if ((items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "cancelled" || (items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "not_delivered") {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                holder.textView50?.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!, android.R.color.holo_red_dark
                                    )
                                )
                            }
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                holder.textView50?.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!, android.R.color.holo_green_light
                                    )
                                )
                            }

                            if ((items!![holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "Confirmed") {
                                if ((items[holder.adapterPosition] as OrdPendingItem).pickup_order.toString() == "1") {
                                    holder.textView304?.visibility = View.VISIBLE
                                    holder.textView305!!.visibility = View.VISIBLE
                                    holder.textView236?.visibility = View.INVISIBLE
                                    holder.textView237!!.visibility = View.INVISIBLE

                                }
                                else
                                    holder.textView304?.visibility = View.INVISIBLE
                                holder.textView305!!.visibility = View.INVISIBLE
                                holder.textView236?.visibility = View.VISIBLE
                                holder.textView237!!.visibility = View.VISIBLE

                                }
                            if ((items!![holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "pending") {
                                if ((items[holder.adapterPosition] as OrdPendingItem).pickup_order.toString() == "1") {
                                    holder.track?.visibility = View.VISIBLE
                                    holder.call!!.visibility = View.VISIBLE



                                    holder.track?.setOnClickListener(object : ClickListener() {
                                        override fun onOneClick(v: View) {
                                            Handler().postDelayed(Runnable {
                                                val gmmIntentUri = Uri.parse(
                                                    "geo:${(items[holder.adapterPosition] as OrdPendingItem).store_lat}," +
                                                            "${(items[holder.adapterPosition] as OrdPendingItem).store_long}" +
                                                            "?q=(${(items[holder.adapterPosition] as OrdPendingItem).store_lat}," +
                                                            "${(items[holder.adapterPosition] as OrdPendingItem).store_long})"
                                                )


                                                val mapIntent =
                                                    Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                                mapIntent.setPackage("com.google.android.apps.maps")
                                                activity!!.startActivity(mapIntent)
                                            }, 1000)


//                                            activity!!.startActivity(intent)
                                        }
                                    })

                                    holder.call!!.setOnClickListener {
                                        val number = (items[holder.adapterPosition] as OrdPendingItem).store_phone
                                        toast(activity, number)
                                        val intent = Intent(Intent.ACTION_DIAL)
                                        intent.data = Uri.parse(number)
                                        activity!!.startActivity(intent)

                                    }




                                }


                               else if ((items[holder.adapterPosition] as OrdPendingItem).pickup_order.toString() == "0") {
                                    holder.textView304?.visibility = View.INVISIBLE
                                    holder.textView305!!.visibility = View.INVISIBLE

                                    holder.track?.setOnClickListener(object : ClickListener() {
                                        override fun onOneClick(v: View) {
                                            if (activity is BazaarContainer) {

                                                v.findNavController().navigate(
                                                    R.id.action_navigation_my_order_to_orderShow2,
                                                    bundleOf(
                                                        "order_id" to when (items?.get(holder.adapterPosition)) {
                                                            is OrdPastItem -> (items[holder.adapterPosition] as OrdPastItem).id
                                                            is OrdPendingItem -> (items[holder.adapterPosition] as OrdPendingItem).id
                                                            else -> ""
                                                        }
                                                    )
                                                )
                                            } else if (activity is DashBoardContainer) {
                                                v.findNavController().navigate(
                                                    R.id.action_navigation_my_order_to_orderShow2,
                                                    bundleOf(
                                                        "order_id" to when (items?.get(holder.adapterPosition)) {
                                                            is OrdPastItem -> (items.get(holder.adapterPosition) as OrdPastItem).id
                                                            is OrdPendingItem -> (items.get(holder.adapterPosition) as OrdPendingItem).id
                                                            else -> ""
                                                        }
                                                    )
                                                )
                                            }
                                        }
                                    })
                                }

                                // Todo holder.call.visibility = View.VISIBLE

                            }




                            /* Todo


                             if items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "upcoming") {
                                holder.track.visibility = View.VISIBLE
                                holder.delete.visibility = View.VISIBLE
                            }*/

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    if((items[holder.adapterPosition] as OrdPendingItem).pickup_charge==0){
                        holder.textView305!!.visibility=View.GONE
                        holder.textView304!!.visibility=View.GONE
                    }else
                    holder.textView305?.text =
                        "₹ ${(items[holder.adapterPosition] as OrdPendingItem).pickup_charge.toString()}"
//                    holder.textView305!!.text= when{
//                        (  items[holder.adapterPosition]as OrdPendingItem).pickup_charge.toString().isNullOrEmpty()-> "0.0"
//                        else -> "₹" +  (  items[holder.adapterPosition]as OrdPendingItem).pickup_charge
//
//                    }
                    holder.textView51?.text =
                        "Order ID :" + (items[holder.adapterPosition] as OrdPendingItem).code.toString()
                    holder.textView54?.text = changeDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        "dd/MM/yyyy hh:mm a",
                        (items[holder.adapterPosition] as OrdPendingItem).orderDate.toString()
                    )
                    holder.textView55?.text =
                        "₹ ${(Math.round((items[holder.adapterPosition] as OrdPendingItem).totalPrize!!.toDouble()))}"
                    holder.textView237?.text =
                        if ((items[holder.adapterPosition] as OrdPendingItem).deliveryCharge.toString() == "0" || (items[holder.adapterPosition] as OrdPendingItem).deliveryCharge.toString() == "0.0") {
                            "Free"
                        } else {

                            "₹ ${(Math.round((items[holder.adapterPosition] as OrdPendingItem).deliveryCharge!!.toDouble()))}"
                        }
                    holder.textView240?.text =
                        "₹ ${(Math.round((items[holder.adapterPosition] as OrdPendingItem).grandTotal!!.toDouble()))}"
                    holder.call?.setOnClickListener(object : ClickListener() {
                        override fun onOneClick(v: View) {
                            if ((items[holder.adapterPosition] as OrdPendingItem).pickup_order.toString() == "1") {
                                val number =
                                    (items[holder.adapterPosition] as OrdPendingItem).store_phone
                                toast(activity, number)

                                val intent = Intent(
                                    Intent.ACTION_DIAL,
                                    Uri.parse("tel:" + number)
                                ) // Initiates the Intent

                                activity!!.startActivity(intent)

                            }
                        }
                    })
                    holder.textView370?.text =
                        if ((items[holder.adapterPosition] as OrdPendingItem).paymentStatus == "paid") {
                            "Amount Paid"
                        } else {
                            "Amount to be paid - ₹" + Math.round((items[holder.adapterPosition] as OrdPendingItem).codDue?.toDouble()!!).toString()
                        }
                }
                else -> {
                    holder.rateIt?.visibility = View.GONE
                    holder.track?.visibility = View.GONE
                    holder.call?.visibility = View.GONE
                }
            }


//            holder.track?.setOnClickListener(object : ClickListener() {
//                override fun onOneClick(v: View) {
//
//                    if (activity is BazaarContainer) {
//
//                        v.findNavController().navigate(
//                            R.id.action_navigation_my_order_to_orderShow, bundleOf(
//                                "order_id" to when (items?.get(holder.adapterPosition)) {
//                                    is OrdPastItem -> (items[holder.adapterPosition] as OrdPastItem).id
//                                    is OrdPendingItem -> (items[holder.adapterPosition] as OrdPendingItem).id
//                                    else -> ""
//                                }
//                            )
//                        )
//                    } else if (activity is DashBoardContainer) {
//                        v.findNavController().navigate(
//                            R.id.action_navigation_my_order_to_orderShow2, bundleOf(
//                                "order_id" to when (items?.get(holder.adapterPosition)) {
//                                    is OrdPastItem -> (items.get(holder.adapterPosition) as OrdPastItem).id
//                                    is OrdPendingItem -> (items.get(holder.adapterPosition) as OrdPendingItem).id
//                                    else -> ""
//                                }
//                            )
//                        )
//                    }
//                }
//            })

            when (val items = items?.get(holder.adapterPosition)) {
                is OrdPastItem -> {
                    holder.tetextView282xtView50?.isVisible =
                        items.spotDelivery == "1" && items.deliveryStatus != "pending" && items.deliveryStatus != "confirmed"
                    holder.tetextView282xtView50?.setOnClickListener {
                        if (holder.tetextView282xtView50.isVisible) observer2.getValue(
                            "reorder",
                            items.id.toString()
                        )
                    }
                }
                is OrdPendingItem -> {
                    holder.tetextView282xtView50?.isVisible =
                        items.spotDelivery == "1" && items.deliveryStatus != "pending" && items.deliveryStatus != "confirmed"
                    holder.tetextView282xtView50?.setOnClickListener {
                        if (holder.tetextView282xtView50.isVisible) observer2.getValue(
                            "reorder",
                            items.id.toString()
                        )
                    }
                }
            }
        }

        private fun rateOr(pastOrds: OrdPastItem, pos: Int, observer1: Interfaces.ReturnPos) {
            val dialog = BottomSheetDialog(activity!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.item_rate_order)
            dialog.setCancelable(true)

            dialog.progress.visibility = View.GONE
            val allTxs = ArrayList<TextView>()
            val allRts = ArrayList<RatingBar>()
            val allEds = ArrayList<EditText>()

            dialog.textView274.text = pastOrds.deliveryBoy?.name ?: ""
            dialog.constraintLayout12.isVisible = pastOrds.deliveryBoy != null

            if (!pastOrds.ordProducts.isNullOrEmpty()) {
                var textView212: TextView
                var ratingBar2: RatingBar
                var editText4: EditText

                for (i in 0 until pastOrds.ordProducts.size) {
                    textView212 = TextView(activity)
                    ratingBar2 = RatingBar(activity, null, android.R.attr.ratingBarStyleSmall)
                    editText4 = EditText(activity)
                    allTxs.add(textView212)
                    allRts.add(ratingBar2)
                    allEds.add(editText4)

                    textView212.textSize =
                        12F //(12 / activity.resources.displayMetrics.scaledDensity)
                    val layout_tx = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layout_tx.width = LinearLayout.LayoutParams.MATCH_PARENT
                    layout_tx.height = LinearLayout.LayoutParams.WRAP_CONTENT
                    layout_tx.leftMargin = 0
                    layout_tx.rightMargin = 0
                    layout_tx.topMargin = 8
                    textView212.layoutParams = layout_tx
                    // textView212.setTypeface(textView212.typeface, Typeface.BOLD)
                    textView212.text =
                        "${pastOrds.ordProducts[i]?.name.toString().capitalize()} *"

                    dialog.containersa.addView(textView212)

                    ratingBar2.scaleX = 1.5F
                    ratingBar2.scaleY = 1.5F
                    ratingBar2.numStars = 5
                    ratingBar2.pivotX = (0 / activity.resources.displayMetrics.density)
                    ratingBar2.pivotY = (0 / activity.resources.displayMetrics.density)
                    ratingBar2.setIsIndicator(FALSE)
                    val layout_rt = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layout_rt.width = LinearLayout.LayoutParams.WRAP_CONTENT
                    layout_rt.height = LinearLayout.LayoutParams.WRAP_CONTENT
                    layout_rt.topMargin = 8
                    layout_rt.rightMargin = 0
                    layout_rt.leftMargin = 0
                    layout_rt.gravity = Gravity.START
                    ratingBar2.layoutParams = layout_rt

                    dialog.containersa.addView(ratingBar2)

                    editText4.setBackgroundResource(R.drawable.item_stroke)
                    editText4.setEms(10)
                    editText4.gravity = Gravity.START
                    editText4.hint = "Remarks.."
                    editText4.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    editText4.setSingleLine(false)
                    editText4.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
                    editText4.textSize =
                        12F/*(12 / activity.resources.displayMetrics.scaledDensity)*/
                    val layout_ed = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layout_ed.width = LinearLayout.LayoutParams.MATCH_PARENT
                    layout_ed.height = 100
                    layout_ed.topMargin = 32
                    layout_ed.leftMargin = 0
                    layout_ed.rightMargin = 0
                    editText4.setPadding(
                        (5F * activity.resources.displayMetrics.density).toInt(),
                        (5F * activity.resources.displayMetrics.density).toInt(),
                        (5F * activity.resources.displayMetrics.density).toInt(),
                        (5F * activity.resources.displayMetrics.density).toInt()
                    )
                    editText4.layoutParams = layout_ed

                    dialog.containersa.addView(editText4)

                }
            }
            var rate = true
            dialog.button16.setOnClickListener(object : ClickListener() {
                override fun onOneClick(v: View) {
                    dialog.progress.visibility = View.VISIBLE
                    val productRateItems = ArrayList<ProductsRateItem>()

                    if (allRts.isNotEmpty()) {
                        for (i in 0 until allRts.size) {
                            if (allRts[i].rating == 0F) {
                                rate = false
                            }
                        }
                    } else {
                        if (dialog.ratingBar3.rating == 0F) {
                            rate = false
                        }
                    }

                    if (rate) {
                        for (i in 0 until (pastOrds.ordProducts?.size ?: 0)) {
                            productRateItems.add(
                                ProductsRateItem(
                                    sellerProductId = pastOrds.ordProducts?.get(i)?.sellerProductId.toString(),
                                    productRating = allRts[i].rating.toString(),
                                    productReview = allEds[i].text.toString()
                                )
                            )
                        }

                        Instances.printAny(
                            Request(
                                utoken = Preference.get(activity, DATAFILE, TOKEN),
                                order_id = pastOrds.id,
                                products_rate = productRateItems,
                                seller_id = pastOrds.sellerId.toString(),
                                seller_rating = dialog.ratingBar3.rating.toString(),
                                seller_review = dialog.editText5.text.toString(),
                                delivery_boy_id = if (pastOrds.deliveryBoy != null) pastOrds.deliveryBoy.deliveryBoyId else "",
                                delivery_boy_rating = if (pastOrds.deliveryBoy != null) dialog.ratingBar2a.rating.toString() else "",
                                delivery_boy_review = if (pastOrds.deliveryBoy != null) dialog.editText6.text.toString() else ""
                            )
                        )
                        Instances.InternetCheck { internet ->
                            if (internet) {
                                viewModel.rateIt(
                                    Request(
                                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                                        order_id = pastOrds.id,
                                        products_rate = productRateItems,
                                        seller_id = pastOrds.sellerId.toString(),
                                        seller_rating = dialog.ratingBar3.rating.toString(),
                                        seller_review = dialog.editText5.text.toString(),
                                        delivery_boy_id = if (pastOrds.deliveryBoy != null) pastOrds.deliveryBoy.deliveryBoyId else "",
                                        delivery_boy_rating = if (pastOrds.deliveryBoy != null) dialog.ratingBar2a.rating.toString() else "",
                                        delivery_boy_review = if (pastOrds.deliveryBoy != null) dialog.editText6.text.toString() else ""
                                    )
                                )?.observe(activity, Observer {
                                    if (it) {
                                        observer1.getValue(pos.toString())
                                        toast(activity, "Thank you for your feedback")
                                    }
                                    dialog.progress.visibility = View.GONE
                                    dialog.dismiss()
                                })
                            } else {
                                Instances.toast(
                                    activity,
                                    activity.getString(R.string.no_internet)
                                )
                                dialog.progress?.visibility = View.GONE
                            }
                        }
                    } else {
                        dialog.progress?.visibility = View.GONE
                        toast(
                            activity,
                            "Please submit your rating, and help us improve to serve you better."
                        )
                    }

                }
            })
            dialog.button17.setOnClickListener(object : ClickListener() {
                override fun onOneClick(v: View) {
                    dialog.dismiss()
                }
            })
            /*dialog.textView212.text = "Rate the Product (${pastOrds.productData.name.toString()})"*/
            dialog.textView213.text =
                "${pastOrds.storeName.toString().toString().capitalize()} *"
            dialog.show()
        }

    }

    class MyOrderSubAdapter(
        val activity: FragmentActivity?,
        val productList: List<OrdProductsItem?>?,
        val observer: Interfaces.ReturnPos
    ) : RecyclerView.Adapter<MyOrderSubAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_an_item, parent, false
                )
            )
        }

        override fun getItemCount(): Int {
            return productList?.size ?: 0
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (productList != null) {
                if (productList.get(holder.adapterPosition) != null) {
                    holder.textView248?.text =
                        productList.get(holder.adapterPosition)?.productQuantity.toString() + "X"
                    holder.textView242?.text =
                        productList[holder.adapterPosition]?.name.toString().split(' ').joinToString(" ") { it.capitalize() } + " ( " + productList[holder.adapterPosition]?.nameLocal.toString() .split(' ').joinToString(" ") { it.capitalize() }+ " )"
                    holder.textView243?.text =
                        "₹" + productList[holder.adapterPosition]?.productTotal.toString()
                    holder.textView244?.text = productList.get(holder.adapterPosition)?.size
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView242 = itemView.findViewById<TextView?>(R.id.textView242)
            val textView243 = itemView.findViewById<TextView?>(R.id.textView243)
            val textView248 = itemView.findViewById<TextView?>(R.id.textView248)
            val textView244 = itemView.findViewById<TextView?>(R.id.textView244)
        }
    }

}