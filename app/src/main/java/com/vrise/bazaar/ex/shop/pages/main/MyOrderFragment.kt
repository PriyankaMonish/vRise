package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.ProductIDs
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopsItem
import com.vrise.bazaar.ex.model.temp.OrdPastItem
import com.vrise.bazaar.ex.model.temp.OrdPendingItem
import com.vrise.bazaar.ex.model.temp.OrdProductsItem
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.DashBoardViewModel
import com.vrise.bazaar.ex.shop.interfaces.OrdersViewModel
import com.vrise.bazaar.ex.shop.interfaces.ShopsViewModel
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.dash_board_fragment.*
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.fragment_my_order.include4
import kotlinx.android.synthetic.main.fragment_request_order.*
import kotlinx.android.synthetic.main.fragment_return_order.*
import kotlinx.android.synthetic.main.item_my_order_new.view.*
import kotlinx.android.synthetic.main.item_myitem_two.view.*
import kotlinx.android.synthetic.main.order_fragment.button25
import kotlinx.android.synthetic.main.order_fragment.no_items
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MyOrderFragment : Fragment() {

    private lateinit var viewModel: OrdersViewModel
    private lateinit var viewModel1: DashBoardViewModel
    private var viewModels: ShopsViewModel? = null
    private var fromDtae: String? = null
    private var toDtae: String? = null
    private var saleId: String? = null
    private var orderId: String? = null

    var adapter: MyNewOrdersAdapter? = null

    var handler = Handler()
    private var timeCounter: Runnable? = null

    companion object {
        var shopId = ""
        var end = ""
        var start = ""
        var shopList: ArrayList<ShopsItem?>? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDashs()
    }
    private var receive = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.action == Constants.ACTION_NOTIFICATION) {

                getDataVals()
                if (activity is DashBoardContainer) {
                    (activity as DashBoardContainer).setNotificationBadge()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_order, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        include4.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView1009.text = "My Order"
        if (activity is DashBoardContainer) {
            (activity as DashBoardContainer).setNotificationBadge()

        }
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(OrdersViewModel::class.java)
        viewModel1= ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(DashBoardViewModel::class.java)
        viewModels = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(ShopsViewModel::class.java)
//     getDataVals()

        from.setOnClickListener {
            include4.visibility = View.VISIBLE

            if (from.text.toString() <= to.text.toString()) {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)


                val dpd = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        val s = monthOfYear + 1
                        fromDtae = "$dayOfMonth-$s-$year"
                        // Display Selected date in textbox
                        from.setText("" + fromDtae)
                        start = ("" + fromDtae)
                        Instances.InternetCheck { internet ->
                            if (internet) {
                                viewModel.getItems(
                                    Request(
                                        utoken = Preference.get(
                                            activity,
                                            Preference.DATAFILE,
                                            Preference.TOKEN
                                        ),
                                        shop_id = "",
                                        start_date = start,
                                        end_date = end
                                    )
                                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                                    if (it != null) {

                                        include4.visibility = View.GONE
                                        if (end == ""
                                        ) {

                                            Toast.makeText(
                                                activity,
                                                "Please select end date",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }
                                        no_items.visibility = View.GONE
                                        val items = ArrayList<Any?>()
                                        for (i in 0 until (it.ordPending?.size ?: 0)) {
                                            items.add(it.ordPending?.get(i))

                                        }
                                        for (i in 0 until (it.ordPast?.size ?: 0)) {
                                            items.add(it.ordPast?.get(i))
                                        }
                                        var adapter: MyNewOrdersAdapter? = null

                                        adapter = MyNewOrdersAdapter(activity,
                                            viewModel, this,
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
                                        recy_main_order.adapter = adapter
                                        recy_main_order.layoutManager =
                                            LinearLayoutManager(
                                                activity,
                                                LinearLayoutManager.VERTICAL,
                                                false
                                            )
                                        adapter.notifyDataSetChanged()

                                    } else {
                                        no_items.visibility = View.VISIBLE
                                        include4.visibility = View.GONE
                                    }

                                })
                            } else {
                                Instances.toast(activity, getString(R.string.no_internet))

                            }
                        }
                    },
                    year,
                    month,
                    day
                )

                dpd.show()
            }

        }
        to.setOnClickListener {



            include4.visibility = View.VISIBLE


            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    val s = monthOfYear + 1
                    toDtae = "$dayOfMonth-$s-$year"


                    // Display Selected date in textbox
                    to.setText("" + toDtae)
                    end = ("" + toDtae)

                    if (start == "" && toDtae.toString() >= fromDtae.toString()) {

                        Toast.makeText(
                            activity,
                            "Please select from date",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Instances.InternetCheck { internet ->
                            if (internet) {
                                viewModel.getItems(
                                    Request(
                                        utoken = Preference.get(
                                            activity,
                                            Preference.DATAFILE,
                                            Preference.TOKEN
                                        ),
                                        shop_id = "",
                                        start_date = start,
                                        end_date = end
                                    )
                                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                                    if (it != null) {
                                        include4.visibility = View.GONE
                                        no_items.visibility = View.GONE
                                        val items = ArrayList<Any?>()
                                        for (i in 0 until (it.ordPending?.size ?: 0)) {
                                            items.add(it.ordPending?.get(i))
                                        }
                                        for (i in 0 until (it.ordPast?.size ?: 0)) {
                                            items.add(it.ordPast?.get(i))
                                        }
                                        var adapter: MyNewOrdersAdapter? = null

                                        adapter = MyNewOrdersAdapter(activity,
                                            viewModel, this,
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
                                        recy_main_order.adapter = adapter
                                        recy_main_order.layoutManager =
                                            LinearLayoutManager(
                                                activity,
                                                LinearLayoutManager.VERTICAL,
                                                false
                                            )
                                        adapter!!.notifyDataSetChanged()

                                    } else {
                                        no_items.visibility = View.VISIBLE

                                        include4.visibility = View.GONE
                                    }

                                })
                            } else {
                                Instances.toast(activity, getString(R.string.no_internet))

                            }
                        }
                    }
                                                   },
                year,
                month,
                day
            )


            dpd.show()


        }

        Instances.InternetCheck { internet ->
            if (internet) {
                include4.visibility = View.VISIBLE
                viewModels?.shoList(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, Preference.TOKEN),
                        category_id = "",
                        store_radius = "",
                        sorting = " ",
                        myorder = 1
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        include4.visibility = View.GONE
                        shopList = it.shops as ArrayList<ShopsItem?>?

                    }
                })
            } else {
                include4.visibility = View.GONE
                Instances.toast(activity, getString(R.string.no_internet))
            }
        }

        (activity as DashBoardContainer).textView1009.text = "My Orders"
        no_items.visibility = View.GONE
        button25.setOnClickListener {
            activity?.onBackPressed()

        }

        textView354.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
//                getDataVals()
                Instances.openListPoPUp(activity,
                    shopList as ArrayList<Any?>,
                    null,
                    "Select your shop",
                    object : Interfaces.ReturnAny {
                        override fun getValue(values: Any?) {
                            if (values is ShopsItem) {
                                shop.setText(values.storeName.toString())
                                shopId = values.id.toString()
                                getDataFilterVals()
                            }
                        }
                    })
            }
        })
      getDataVals()

    }


    private fun getDataVals() {

        Instances.InternetCheck { internet ->
            if (internet) {
                include4.visibility = View.VISIBLE
                viewModel.getItems(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = "",
                        start_date = "",
                        end_date = ""
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                    if (it != null) {
                        include4.visibility = View.GONE

                        no_items.visibility = View.GONE
                        val items = ArrayList<Any?>()
                        for (i in 0 until (it.ordPending?.size ?: 0)) {
                            items.add(it.ordPending?.get(i))
                            saleId = it.ordPending?.get(0)?.saleid
                            orderId = it.ordPending?.get(0)?.id
                            BaseApp.urlLink = saleId
                            BaseApp.ordId = orderId


                        }
                        for (i in 0 until (it.ordPast?.size ?: 0)) {
                            items.add(it.ordPast?.get(i))
                        }


                        adapter = MyNewOrdersAdapter(activity,
                            viewModel, this,
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
                        recy_main_order.adapter = adapter
                        recy_main_order.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        adapter!!.notifyDataSetChanged()

                    } else {
                        include4.visibility = View.GONE
                        no_items.visibility = View.VISIBLE
                        recy_main_order.visibility = View.GONE
                        textView379.visibility = View.GONE
                    }

                })
            } else {
                include4.visibility = View.GONE
                Instances.toast(activity, getString(R.string.no_internet))

            }
        }
    }
    public fun getDashs() {

        include4.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel1.getDashboardItems(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {

                    if (activity is DashBoardContainer) {
                        (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                        (activity as DashBoardContainer).setNotificationBadge()
                    }

    }
    })
            }
        }
    }
    private fun getDataFilterVals() {

        Instances.InternetCheck { internet ->
            if (internet) {
                include4.visibility = View.VISIBLE
                viewModel.getItems(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = shopId,
                        start_date = start,
                        end_date = end
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                    if (it != null) {
                        include4.visibility = View.GONE
                        recy_main_order.visibility = View.VISIBLE

                        no_items.visibility = View.GONE
                        val items = ArrayList<Any?>()
                        for (i in 0 until (it.ordPending?.size ?: 0)) {
                            items.add(it.ordPending?.get(i))
                        }
                        for (i in 0 until (it.ordPast?.size ?: 0)) {
                            items.add(it.ordPast?.get(i))
                        }
                        var adapter: MyNewOrdersAdapter? = null

                        adapter = MyNewOrdersAdapter(activity,
                            viewModel, this,
                            items,
                            object : Interfaces.ReturnPos {
                                override fun getValue(position: String) {
                                    try {
                                        getDataFilterVals()
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
                        recy_main_order.adapter = adapter
                        recy_main_order.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        adapter.notifyDataSetChanged()

                    } else {
                        include4.visibility = View.GONE
                        no_items.visibility = View.VISIBLE
                        recy_main_order.visibility = View.GONE
                        textView379.visibility = View.GONE
                    }

                })
            } else {
                include4.visibility = View.GONE
                Instances.toast(activity, getString(R.string.no_internet))

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
                    if (it?.data != null) {
                        if (it?.data?.shopChange == 1) {
                            val display =
                                AlertDialog.Builder(requireContext())
                                    .setCancelable(true).setTitle("Replace cart item?")
                                    .setMessage(it?.data.display.toString())
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

                            if (it.display != "" && it?.display != null) {
                                Toast.makeText(context, it.display.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                            view?.findNavController()
                                ?.navigate(R.id.action_myOrderFragment_to_navigation_cart) } }
                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }

        }


    }

    class MyNewOrdersAdapter(
        val activity: FragmentActivity?,
        val viewModel: OrdersViewModel,
        val fragment: MyOrderFragment,
        val items: ArrayList<Any?>?,
        val observer: Interfaces.ReturnPos,
        private val observer2: Interfaces.ReturnAnyWithKey,

        ) : RecyclerView.Adapter<MyNewOrdersAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val second = itemView.findViewById<RecyclerView?>(R.id.recy_two)
            val status = itemView.findViewById<TextView?>(R.id.textView336)
            val cust_status = itemView.findViewById<TextView?>(R.id.textView392)
            val returnstatus = itemView.findViewById<TextView?>(R.id.returnstatus)
            val date = itemView.findViewById<TextView?>(R.id.textView330)
            val orderNum = itemView.findViewById<TextView?>(R.id.textView329)
            val viewItems = itemView.findViewById<TextView?>(R.id.textView327)
            val HideItems = itemView.findViewById<TextView?>(R.id.textView351)
            val consnew = itemView.findViewById<ConstraintLayout?>(R.id.cons_new)
            val cancel = itemView.findViewById<Button?>(R.id.cancel_edit)
            val cancellall = itemView.findViewById<Button?>(R.id.cancellall)
            val confirm = itemView.findViewById<Button?>(R.id.confirm)
            val cancel_next = itemView.findViewById<Button?>(R.id.cancel_after)
            val cancel_return = itemView.findViewById<Button?>(R.id.button34)
            val submit = itemView.findViewById<Button?>(R.id.button31)
            val cancel_return_order = itemView.findViewById<Button?>(R.id.button34)
            val requested = itemView.findViewById<TextView?>(R.id.textView389)
            val returnn = itemView.findViewById<Button?>(R.id.button24)
            val tetextView282xtView50 = itemView.findViewById<TextView?>(R.id.textView282)
            val storename = itemView.findViewById<TextView?>(R.id.textView323)
            val Total = itemView.findViewById<TextView?>(R.id.textView343)
            val delivery = itemView.findViewById<TextView?>(R.id.textView341)
            val delivery1 = itemView.findViewById<TextView?>(R.id.textView340)
            val packing = itemView.findViewById<TextView?>(R.id.textView345)
            val packingtext = itemView.findViewById<TextView?>(R.id.textView342)
            val discount = itemView.findViewById<TextView?>(R.id.textView401)
            val tv_disc = itemView.findViewById<TextView?>(R.id.textView402)
            val Detail = itemView.findViewById<ConstraintLayout?>(R.id.view28)
            val Items = itemView.findViewById<TextView?>(R.id.textView371)
            val view = itemView.findViewById<View?>(R.id.view30)

            //            val uNIT = itemView.findViewById<TextView?>(R.id.textView373)
            val QTY = itemView.findViewById<TextView?>(R.id.textView375)

            //            val price = itemView.findViewById<TextView?>(R.id.textView386)
            val inr = itemView.findViewById<TextView?>(R.id.textView377)
            val DeliveryAdd = itemView.findViewById<TextView?>(R.id.textView340)
            val Totals = itemView.findViewById<TextView?>(R.id.textView346)
            val unittt = itemView.findViewById<TextView?>(R.id.textView355)

            init {

                tetextView282xtView50?.isVisible = false
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_my_order_new, parent, false
                )
            )
        }

        override fun getItemCount(): Int {
            return items?.size ?: 0
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        @SuppressLint("ResourceAsColor", "SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {


            when {
                items?.get(holder.adapterPosition) is OrdPastItem -> {
                    if ((items[holder.adapterPosition] as OrdPastItem).status_notes.toString() != "null" &&
                        (items[holder.adapterPosition] as OrdPastItem).status_notes.toString() != ""
                    ) {
                        holder.cust_status.text =
                            (items[holder.adapterPosition] as OrdPastItem).status_notes.toString()
                    }


                    holder.submit.setOnClickListener {

                        showReturn(activity, (items[holder.adapterPosition] as OrdPastItem))
                    }

                    holder.cancel_return_order.setOnClickListener {
                        holder.consnew.visibility = View.VISIBLE
                        holder.Detail.visibility = View.VISIBLE
                        holder.viewItems.visibility = View.GONE
                        holder.HideItems.visibility = View.VISIBLE

                        holder.second.visibility = View.VISIBLE
                        holder.second.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        when (items.get(holder.adapterPosition)) {
                            is OrdPastItem -> {
                                (items.get(holder.adapterPosition) as OrdPastItem).btnEnabled = true
                                (items.get(holder.adapterPosition) as OrdPastItem).btnChecked = true

                                holder.second?.adapter = MyOrderSubAdapter(
                                    activity, fragment,
                                    (items[holder.adapterPosition] as OrdPastItem).ordProducts,
                                    false, true,
                                    observer, viewModel
                                )
                            }
                            is OrdPendingItem -> {
                                (items.get(holder.adapterPosition) as OrdPendingItem).btnEnabled =
                                    true

                                holder.second?.adapter = MyOrderSubAdapter(
                                    activity, fragment,
                                    (items[holder.adapterPosition] as OrdPendingItem).ordProducts,
                                    true, false,
                                    observer, viewModel
                                )
                            }

                        }

                        holder.submit.visibility = View.VISIBLE
                        holder.cancel_return.visibility = View.VISIBLE
                        holder.view.visibility = View.VISIBLE
                    }

                    holder.returnn.setOnClickListener {

                        holder.consnew.visibility = View.VISIBLE
                        holder.Detail.visibility = View.VISIBLE
                        holder.viewItems.visibility = View.GONE
                        holder.HideItems.visibility = View.VISIBLE

                        holder.second.visibility = View.VISIBLE
                        holder.second.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        when (items.get(holder.adapterPosition)) {
                            is OrdPastItem -> {
                                (items.get(holder.adapterPosition) as OrdPastItem).btnEnabled = true
                                (items.get(holder.adapterPosition) as OrdPastItem).btnChecked = true

                                holder.second?.adapter = MyOrderSubAdapter(
                                    activity, fragment,
                                    (items[holder.adapterPosition] as OrdPastItem).ordProducts,
                                    false, true,
                                    observer, viewModel
                                )
                            }
                            is OrdPendingItem -> {
                                (items.get(holder.adapterPosition) as OrdPendingItem).btnEnabled =
                                    true

                                holder.second?.adapter = MyOrderSubAdapter(
                                    activity, fragment,
                                    (items[holder.adapterPosition] as OrdPendingItem).ordProducts,
                                    true, false,
                                    observer, viewModel
                                )
                            }


                        }

                        holder.submit.visibility = View.VISIBLE
                        holder.cancel_return.visibility = View.VISIBLE
                        holder.view.visibility = View.VISIBLE
                    }

                    if (!(items[holder.adapterPosition] as OrdPastItem).ordProducts.isNullOrEmpty()) {
                        //holder.cancel.visibility = View.GONE

                        holder.viewItems.setOnClickListener {
                            holder.consnew.visibility = View.VISIBLE
//                            holder.cancel.isVisible=!(items[holder.adapterPosition] as OrdPastItem).btnEnabled
//                            holder.cancellall.isVisible=(items[holder.adapterPosition] as OrdPastItem).btnEnabled
//                            holder.confirm.visibility = View.VISIBLE
                            holder.Detail.visibility = View.VISIBLE
                            holder.viewItems.visibility = View.GONE
                            holder.HideItems.visibility = View.VISIBLE

                            holder.second.visibility = View.VISIBLE
                            holder.second.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            holder.second?.adapter = MyOrderSubAdapter(
                                activity, fragment,
                                (items[holder.adapterPosition] as OrdPastItem).ordProducts,
                                (items[holder.adapterPosition] as OrdPastItem).btnEnabled,
                                (items[holder.adapterPosition] as OrdPastItem).btnChecked,
                                observer, viewModel
                            )

                        }

                        holder.HideItems.setOnClickListener {
                            holder.second.visibility = View.GONE

                            holder.viewItems.visibility = View.VISIBLE
                            holder.HideItems.visibility = View.GONE
                            holder.consnew.visibility = View.GONE
                            holder.submit.visibility = View.GONE
                            holder.cancel_return.visibility = View.GONE
                            holder.view.visibility = View.GONE
                            holder.cancellall.visibility = View.GONE
//                            holder.confirm.visibility = View.GONE
                            holder.cancel.visibility = View.GONE
                            holder.Detail.visibility = View.GONE
                        }

                        holder.HideItems.text = "Hide Items"

                    }
//sellernotes

                    holder.orderNum?.text =
                        "Order ID #" + (items[holder.adapterPosition] as OrdPastItem).code.toString()

                    holder.orderNum?.text =
                        "Order ID #" + (items[holder.adapterPosition] as OrdPastItem).code.toString()
                    holder.storename?.text =
                        (items[holder.adapterPosition] as OrdPastItem).storeName.toString().split(
                            ' '
                        ).joinToString(" ") { it.capitalize() }
                    holder.status?.text =
                        (items[holder.adapterPosition] as OrdPastItem).deliveryStatus.toString()
                    val precisions = DecimalFormat("0.00")
                    holder.Total?.text =
                        "Rs . " + "" + (items[holder.adapterPosition] as OrdPastItem).grandTotal.toString()


                    if ((items[holder.adapterPosition] as OrdPastItem).packing_charge?.equals(0) == true ||
                        (items[holder.adapterPosition] as OrdPastItem).packing_charge?.toInt() == 0
                    ) {
                        holder.packing?.visibility = View.GONE
                        holder.packingtext?.visibility = View.GONE
                    } else {
                        holder.packing?.text =
                            "Rs . " + "" + (String.format(precisions.format((items[holder.adapterPosition] as OrdPastItem).packing_charge)))
                    }
                    holder.packing?.text =
                        "Rs . " + "" + (String.format(precisions.format((items[holder.adapterPosition] as OrdPastItem).packing_charge)))
                    if ((items[holder.adapterPosition] as OrdPastItem).deliveryCharge?.equals(0) == true ||
                        (items[holder.adapterPosition] as OrdPastItem).deliveryCharge?.toInt() == 0
                    ) {
                        holder.delivery?.visibility = View.GONE
                        holder.delivery1?.visibility = View.GONE
                    } else {
                        holder.delivery?.text =
                            "Rs . " + "" + (String.format(precisions.format((items[holder.adapterPosition] as OrdPastItem).deliveryCharge?.toInt())))
                    }

                    holder.tv_disc?.text =
                        "Rs . " + "" + (String.format(precisions.format((items[holder.adapterPosition] as OrdPastItem).discountAmount)))
                    if ((items[holder.adapterPosition] as OrdPastItem).discountAmount?.equals(0) == true ||
                        (items[holder.adapterPosition] as OrdPastItem).discountAmount?.toInt() == 0
                    ) {
                        holder.discount?.visibility = View.GONE
                        holder.tv_disc?.visibility = View.GONE
                    } else {
                        holder.tv_disc?.text =
                            "Rs . " + "" + (String.format(precisions.format((items[holder.adapterPosition] as OrdPastItem).discountAmount?.toInt())))
                    }
                    holder.viewItems?.text = "View Items"
                    holder.date?.text = Instances.changeDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        "dd/MM/yyyy hh:mm a",
                        (items[holder.adapterPosition] as OrdPastItem).orderDate.toString()
                    )

                }

                items?.get(holder.adapterPosition) is OrdPendingItem -> {


                    if ((items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "cancelled" ||
                        (items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString() == "not_delivered"
                    ) {
                        if ((items[holder.adapterPosition] as OrdPendingItem).status_notes.toString() != "null" &&
                            (items[holder.adapterPosition] as OrdPendingItem).status_notes.toString() != ""
                        ) {
                            holder.cust_status.text =
                                (items[holder.adapterPosition] as OrdPendingItem).status_notes.toString()
                        }


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.status?.setTextColor(
                                ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!, android.R.color.holo_red_dark
                                    )
                                )
                            )

                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.status?.setTextColor(
                                ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        activity!!, android.R.color.holo_green_light
                                    )
                                )
                            )
                        }

                        if (!(items[holder.adapterPosition] as OrdPendingItem).ordProducts.isNullOrEmpty()) {
                            holder.viewItems.setOnClickListener {
                                holder.consnew.visibility = View.VISIBLE
                                holder.viewItems.visibility = View.GONE

//                                holder.confirm.visibility = View.VISIBLE

                                holder.confirm.isVisible =
                                    ((items[holder.adapterPosition] as OrdPendingItem).order_model == 1 &&
                                            (items[holder.adapterPosition] as OrdPendingItem).seller_status == "confirmed" &&

                                            (items[holder.adapterPosition] as OrdPendingItem).customer_status == null)
                                holder.cancel_next.isVisible =
                                    ((items[holder.adapterPosition] as OrdPendingItem).order_model == 1
                                            && (items[holder.adapterPosition] as OrdPendingItem).seller_status == "confirmed" &&

                                            (items[holder.adapterPosition] as OrdPendingItem).customer_status == null)
                                holder.requested.isVisible =
                                    ((items[holder.adapterPosition] as OrdPendingItem).order_model == 1
                                            && (items[holder.adapterPosition] as OrdPendingItem).seller_status == "confirmed"
                                            &&
                                            (items[holder.adapterPosition] as OrdPendingItem).customer_status == null)

                                holder.cancel.isVisible =
                                    (!(items[holder.adapterPosition] as OrdPendingItem).btnEnabled &&
                                            (items[holder.adapterPosition] as OrdPendingItem).order_model == 1 &&
                                            (items[holder.adapterPosition] as OrdPendingItem).seller_status == "pending")

                                holder.cancellall.isVisible =
                                    (items[holder.adapterPosition] as OrdPendingItem).btnEnabled
                                holder.Detail.visibility = View.VISIBLE
                                holder.HideItems.visibility = View.VISIBLE
                                holder.second.visibility = View.VISIBLE
                                holder.second.layoutManager =
                                    LinearLayoutManager(
                                        activity,
                                        LinearLayoutManager.VERTICAL,
                                        false
                                    )

                                holder.second?.adapter = MyOrderSubAdapter(
                                    activity, fragment,
                                    (items[holder.adapterPosition] as OrdPendingItem).ordProducts,
                                    (items[holder.adapterPosition] as OrdPendingItem).btnEnabled,
                                    (items[holder.adapterPosition] as OrdPendingItem).btnChecked,
                                    observer, viewModel
                                )
                            }


                            holder.HideItems.setOnClickListener {
                                Instances.InternetCheck { internet ->
                                    if (internet) {
                                        viewModel.getEdit(
                                            Request(
                                                utoken = Preference.get(
                                                    activity,
                                                    Preference.DATAFILE,
                                                    Preference.TOKEN
                                                ),
                                                sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid,
                                                editing = "0"
                                            )
                                        )?.observe(
                                            activity!!,
                                            Observer {

                                                if (it != null) {


                                                }
                                            })
                                    }
                                }
                                holder.second.visibility = View.GONE
                                holder.consnew.visibility = View.GONE
                                holder.cancellall.visibility = View.GONE

                                holder.confirm.visibility = View.GONE
                                holder.cancel_next.visibility = View.GONE
                                holder.requested.visibility = View.GONE
                                holder.cancel.visibility = View.GONE
                                holder.Detail.visibility = View.GONE
                                holder.viewItems.visibility = View.VISIBLE
                                holder.HideItems.visibility = View.GONE
                                holder.submit.visibility = View.GONE
                                holder.cancel_return.visibility = View.GONE
                                holder.view.visibility = View.GONE

                            }

                            holder.HideItems.text = "Hide Items"

                        }

                        holder.requested.setOnClickListener {
                            showPopup(activity, (items[holder.adapterPosition] as OrdPendingItem))
                        }
                        holder.confirm.setOnClickListener {
                            Instances.InternetCheck { internet ->
                                if (internet) {

                                    viewModel.getConfrml(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                Preference.DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            order_id = (items[holder.adapterPosition] as OrdPendingItem).id,
                                            confirm_status = "1",
                                            sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid
                                        )
                                    )?.observe(
                                        activity!!,
                                        Observer {

                                            val args = Bundle()
                                            args.putString(
                                                Constants.saleId,
                                                (items[holder.adapterPosition] as OrdPendingItem).saleid
                                            )

                                            fragment.findNavController()
                                                .navigate(R.id.saleModel, args)
                                        })
                                }

                            }

                        }

                        holder.cancel_next.setOnClickListener {
                            Instances.InternetCheck { internet ->
                                if (internet) {

                                    viewModel.getConfrml(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                Preference.DATAFILE, TOKEN
                                            ),
                                            order_id = (items[holder.adapterPosition] as OrdPendingItem).id,
                                            confirm_status = "rejected",
                                            sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid
                                        )
                                    )?.observe(
                                        activity!!,
                                        Observer {

                                            fragment.getDataVals()
                                        })
                                }

                            }

                        }
                        holder.viewItems?.text = "View Items"
                        val precision = DecimalFormat("0.00")

                        holder.Total?.text =
                            "Rs . " + "" + (items[holder.adapterPosition] as OrdPendingItem).grandTotal.toString()

                        if ((items[holder.adapterPosition] as OrdPendingItem).deliveryCharge?.equals(
                                0
                            ) == true ||
                            (items[holder.adapterPosition] as OrdPendingItem).deliveryCharge?.toInt() == 0
                        ) {
                            holder.delivery?.visibility = View.GONE
                            holder.delivery1?.visibility = View.GONE
                        } else {
                            holder.delivery?.text =
                                "Rs . " + "" + (String.format(precision.format((items[holder.adapterPosition] as OrdPendingItem).deliveryCharge?.toInt())))
                        }


                        holder.tv_disc?.text =
                            "Rs . " + "" + (String.format(precision.format((items[holder.adapterPosition] as OrdPendingItem).discountAmount)))
                        if ((items[holder.adapterPosition] as OrdPendingItem).discountAmount?.equals(0) == true ||
                            (items[holder.adapterPosition] as OrdPendingItem).discountAmount?.toInt() == 0
                        ) {
                            holder.discount?.visibility = View.GONE
                            holder.tv_disc?.visibility = View.GONE
                        } else {
                            holder.tv_disc?.text =
                                "Rs . " + "" + (String.format(precision.format((items[holder.adapterPosition] as OrdPendingItem).discountAmount)))
                        }


                        if ((items[holder.adapterPosition] as OrdPendingItem).packing_charge?.equals(
                                0
                            ) == true ||
                            (items[holder.adapterPosition] as OrdPendingItem).packing_charge?.toInt() == 0
                        ) {
                            holder.packing?.visibility = View.GONE
                            holder.packingtext?.visibility = View.GONE
                        } else {
                            holder.packing?.text =
                                "Rs . " + "" + (String.format(precision.format((items[holder.adapterPosition] as OrdPendingItem).packing_charge?.toInt())))
                        }
                        holder.storename?.text =
                            (items[holder.adapterPosition] as OrdPendingItem).storeName.toString()
                                .split(
                                    ' '
                                ).joinToString(" ") { it.capitalize() }
                        holder.status?.text =
                            (items[holder.adapterPosition] as OrdPendingItem).deliveryStatus.toString()


                        holder.orderNum?.text =
                            "Order ID #" + (items[holder.adapterPosition] as OrdPendingItem).code.toString()


                        holder.cancellall.setOnClickListener {
                            Instances.InternetCheck { internet ->
                                if (internet) {

                                    viewModel.getCancel(
                                        Request(
                                            utoken = Preference.get(activity, DATAFILE, TOKEN),
                                            product_count = (items[holder.adapterPosition] as OrdPendingItem).item_count,
                                            sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid,
                                            productid = (items[holder.adapterPosition] as OrdPendingItem).sellerId,
                                            productsizeid = ""
                                        )
                                    )?.observe(
                                        activity!!,
                                        Observer {
                                            holder.cancellall.visibility = View.GONE
                                            fragment.getDataVals()

                                            Instances.InternetCheck { internet ->
                                                if (internet) {

                                                    viewModel.getEdit(
                                                        Request(
                                                            utoken = Preference.get(
                                                                activity,
                                                                Preference.DATAFILE,
                                                                Preference.TOKEN
                                                            ),
                                                            sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid,
                                                            editing = "0"
                                                        )
                                                    )?.observe(
                                                        activity!!,
                                                        Observer {

                                                        })
                                                }
                                            }
                                        }) } } }

                        holder.cancel.setOnClickListener {

                            when (items.get(holder.adapterPosition)) {
                                is OrdPastItem -> {
                                    holder.cancellall.visibility = View.VISIBLE
                                    holder.cancel.visibility = View.GONE
                                    (items.get(holder.adapterPosition) as OrdPastItem).btnEnabled =
                                        true
                                    (items.get(holder.adapterPosition) as OrdPastItem).btnChecked =
                                        true
                                    holder.second?.adapter = MyOrderSubAdapter(
                                        activity, fragment,
                                        (items[holder.adapterPosition] as OrdPastItem).ordProducts,
                                        false, true,
                                        observer, viewModel
                                    )
                                }
                                is OrdPendingItem -> {
                                    holder.cancellall.visibility = View.VISIBLE
                                    holder.cancel.visibility = View.GONE
                                    (items.get(holder.adapterPosition) as OrdPendingItem).btnEnabled =
                                        true
                                    (items.get(holder.adapterPosition) as OrdPendingItem).btnChecked =
                                        true
                                    holder.second?.adapter = MyOrderSubAdapter(
                                        activity, fragment,
                                        (items[holder.adapterPosition] as OrdPendingItem).ordProducts,
                                        true, false,
                                        observer, viewModel
                                    )
                                }
                            }
                            //notifyItemChanged(holder.adapterPosition)


                            Instances.InternetCheck { internet ->
                                if (internet) {

                                    viewModel.getEdit(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                Preference.DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            sale_id = (items[holder.adapterPosition] as OrdPendingItem).saleid,
                                            editing = "1"
                                        )
                                    )?.observe(
                                        activity!!,
                                        Observer {

                                            if (it != null) {
                                                BaseApp.urlLink =
                                                    (items[holder.adapterPosition] as OrdPendingItem).saleid

                                            }
                                        })
                                }
                            }
                        }
                    }


                    holder.date?.text = Instances.changeDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        "dd/MM/yyyy hh:mm a",
                        (items[holder.adapterPosition] as OrdPendingItem).orderDate.toString()
                    )

                }

            }



            when (val items = items?.get(holder.adapterPosition)) {
                is OrdPastItem -> {
                    holder.tetextView282xtView50?.isVisible =
                        items.spotDelivery == "1" && items.deliveryStatus != "pending" && items.deliveryStatus != "confirmed"

                    holder.returnn.isVisible =
                        items.spotDelivery == "1" && items.deliveryStatus != "pending"
                                && items.deliveryStatus != "confirmed"
                                && items.deliveryStatus != "cancelled" &&
                    items != "not_delivered"
                            && items.order_return == 0 && items.atleast_one == 1


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
                    holder.returnn.isVisible =
                        items.spotDelivery == "1" && items.deliveryStatus != "pending" && items.deliveryStatus != "confirmed"
                                && items.deliveryStatus != "cancelled"
                    holder.tetextView282xtView50?.setOnClickListener {
                        if (holder.tetextView282xtView50.isVisible) observer2.getValue(
                            "reorder",
                            items.id.toString()
                        )
                    }
                }
            }

        }


        private fun showReturn(activity: FragmentActivity?, itemList: OrdPastItem) {

            val prdct_ids = ArrayList<ProductIDs>()
            var productIdd: ProductIDs
            var productsArray = itemList.ordProducts?.filter { it?.isSelected!! }
            if(productsArray?.size!! >0){



                val dialog = activity?.let { Dialog(it) }
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog?.setCancelable(false)
                dialog?.setContentView(R.layout.fragment_return_order)

                val yesBtn = dialog?.findViewById(R.id.button112) as Button
                val noBtn = dialog?.findViewById(R.id.button35) as Button
                val description = dialog.findViewById(R.id.textView395) as EditText

                yesBtn.setOnClickListener {
                    prdct_ids.clear()
                    prdct_ids.removeAll(prdct_ids)

                    Instances.InternetCheck { internet ->
                        if (internet) {
                            for (i in 0 until(productsArray?.size!!?:0) ) {
                                productIdd = ProductIDs()
                                productIdd.seller_product_id = productsArray.get(i)?.sellerProductId
                                productIdd.product_size_id =
                                    productsArray.get(i)?.product_size_id
                                productIdd.quantity =
                                    if (productsArray.get(i)?.enterdQuantity?.toString() != "" &&
                                        productsArray.get(i)?.enterdQuantity != null
                                    ) {
                                        productsArray.get(i)?.enterdQuantity!!.toFloat().toString()
                                    } else {
                                        productsArray.get(i)?.productQuantity?.toFloat().toString()
                                    }


                                prdct_ids.add(productIdd)
                            }

                                viewModel.getReturn(
                                    Request(
                                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                                        seller_id = itemList.sellerId,
                                        order_id = itemList.id,
                                        product_ids = prdct_ids,
                                        description = description.text.toString(),
                                        sale_id = itemList.saleid,

                                        )
                                )?.observe(
                                    activity!!,
                                    Observer {
                                        fragment.getDataVals()
                                    })

                        }
                        dialog.dismiss()
                    }
                }

                noBtn.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.recy_return.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                var adapter = returnAdapter(
                    activity,
                    fragment,
                    itemList.ordProducts?.filter { it?.isSelected!! })
                dialog.recy_return.adapter = adapter
//                dialog.show()

                val displayMetrics = DisplayMetrics()
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
                val displayWidth = displayMetrics.widthPixels
                val displayHeight = displayMetrics.heightPixels
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window!!.attributes)
                val dialogWindowWidth = (displayWidth * 0.9f).toInt()

                layoutParams.width = dialogWindowWidth

                dialog.window!!.attributes = layoutParams

                dialog.show()
            }
            else
            {
                Toast.makeText(activity, "No Items selected for return", Toast.LENGTH_SHORT).show()
            }

    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    private fun showPopup(activity: FragmentActivity?, item: OrdPendingItem) {

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getrequest(
                    Request(
                        utoken = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.TOKEN
                        ),
                        sale_id = item.saleid
                    )
                )?.observe(
                    activity!!,
                    Observer {
                        val dialog = activity?.let { Dialog(it) }
                        val precisionsw = DecimalFormat("0.00")
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(false)
                        dialog.setContentView(R.layout.fragment_request_order)

                        val yessdBtn = dialog?.findViewById(R.id.button12) as Button
                        yessdBtn.setOnClickListener {
                            dialog.dismiss()
                        }

                        dialog.recy_two.adapter =
                            RequestOrder.requestAdapter(it?.requestedorder!!.get(0)!!.ordProducts)

                        if (it?.requestedorder?.get(0)?.deliveryCharge?.toInt() == 0) {
                            dialog.textView34111.visibility = View.GONE
                            dialog.textView3140.visibility = View.GONE
                        } else {

                            dialog.textView34111.setText(
                                "Rs . " + "" + (String.format(
                                    precisionsw.format(
                                        it.requestedorder.get(0)?.deliveryCharge?.toInt()
                                    )
                                ))
                            )
                        }
                        dialog.textView34133.setText("Rs . " + "" + (it.requestedorder.get(0)?.grandTotal))

                        if (it.requestedorder.get(0)?.packing_charge?.toInt() == 0) {
                            dialog.textView3455.visibility = View.GONE
                            dialog.textView3142.visibility = View.GONE
                        } else {


                            dialog.textView3455.setText(
                                "Rs . " + "" + (String.format(
                                    precisionsw.format(
                                        it.requestedorder?.get(0)?.packing_charge
                                    )
                                ))
                            )
                        }

                        dialog.recy_two.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )


                        if (it.requestedorder.isEmpty()) {
                            Toast.makeText(
                                activity,
                                "No Updations has been done in the particular order by the seller.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                        val displayMetrics = DisplayMetrics()
                        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
                        val displayWidth = displayMetrics.widthPixels
//                        val displayHeight = displayMetrics.heightPixels
                        val layoutParams = WindowManager.LayoutParams()
                        layoutParams.copyFrom(dialog.window!!.attributes)
                        val dialogWindowWidth = (displayWidth * 0.9f).toInt()
//                        val dialogWindowHeight = (displayHeight * 0.9f).toInt()
                        layoutParams.width = dialogWindowWidth
//                        layoutParams.height = dialogWindowHeight
                        dialog.window!!.attributes = layoutParams

                        dialog.show()

                    })
            }
        }
    }
}


class MyOrderSubAdapter(
    val activity: FragmentActivity?,
    val fragment: MyOrderFragment,
    val productList: List<OrdProductsItem?>?,
    var btnEnable: Boolean,
    var btnChecked: Boolean,
    val observer: Interfaces.ReturnPos,
    var viewModel: OrdersViewModel

) : RecyclerView.Adapter<MyOrderSubAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_myitem_two, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (productList != null) {
            holder.itemView.checkBox10.visibility = View.INVISIBLE
            if (btnEnable == true) {
                holder.itemView.imageView73.visibility = View.VISIBLE
                holder.itemView.checkBox10.visibility = View.INVISIBLE
            } else
                holder.itemView.imageView73.visibility = View.INVISIBLE


            if (btnChecked == true) {
                if (productList.get(position)?.returnable == "yes") {
                    holder.itemView.checkBox10.visibility = View.VISIBLE
                } else {
                    holder.itemView.checkBox10.visibility = View.INVISIBLE
                    holder.itemView.imageView73.visibility = View.INVISIBLE
                }
            }


            holder.itemView.checkBox10.setOnClickListener {
                productList.get(holder.adapterPosition)?.isSelected =
                    holder.itemView.checkBox10.isChecked
            }

//
//            if (productList!!.get(holder.adapterPosition)?.returnable == "yes") {
                if ((productList!!.get(holder.adapterPosition)?.returned == "yes") &&
                    (productList!!.get(holder.adapterPosition)?.return_status != "")
                ) {
                    holder.status.visibility = View.VISIBLE
                    holder.status.text =
                        "Return " + productList!!.get(holder.adapterPosition)?.return_status.toString()

                } else {
//                      holder.status.visibility = View.VISIBLE
//                        holder.status.text = "Returned "

                }
//            }

            holder.itemView.imageView73.setOnClickListener {
                Instances.InternetCheck { internet ->
                    if (internet) {

                        viewModel.getCancel(
                            Request(
                                utoken = Preference.get(activity, DATAFILE, TOKEN),
                                product_count = 1,
                                sale_id = productList.get(holder.adapterPosition)?.sale_id,
                                productid = productList.get(holder.adapterPosition)?.sellerProductId,
                                productsizeid = productList.get(holder.adapterPosition)?.product_size_id

                            )
                        )?.observe(
                            activity!!,
                            Observer {

//                                    Toast.makeText(
//                                        activity,
//                                        it?.message.toString(),
//                                        Toast.LENGTH_SHORT
//                                    ).show()

                                fragment.getDataVals()
                                if (it?.data != null) {

                                    Instances.InternetCheck { internet ->
                                        if (internet) {

                                            viewModel.getEdit(
                                                Request(
                                                    utoken = Preference.get(
                                                        activity,
                                                        Preference.DATAFILE,
                                                        Preference.TOKEN
                                                    ),
                                                    sale_id = productList.get(holder.adapterPosition)?.sale_id,
                                                    editing = "0"
                                                )
                                            )?.observe(
                                                activity!!,
                                                Observer {
                                                })
                                        }
                                    }


                                }
                            })
                    }
                }
            }
            if (productList.get(holder.adapterPosition) != null) {
                holder.amount?.text =
                    productList.get(holder.adapterPosition)?.productQuantity
                holder.productname?.text =
                    productList[holder.adapterPosition]?.name.toString().split(' ')
                        ?.joinToString(
                            " "
                        ) { it.capitalize() }
                holder.unitt?.text =
                    productList[holder.adapterPosition]?.size.toString()
//                        holder.price?.text =
//                            productList[holder.adapterPosition]?.price.toString()


                val item1 =
                    productList.get(holder.adapterPosition)?.productQuantity!!.toInt()
                val item2 = productList.get(holder.adapterPosition)?.price!!.toInt()
                var item3 = (item2)
                var item4 = (item1 * item2)
                val precisions = DecimalFormat("0.00")
                holder.price?.text = (String.format(precisions.format(item4)))
                holder.qty?.text = (String.format(precisions.format(item3)))
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productname = itemView.findViewById<TextView?>(R.id.textView337)
        val qty = itemView.findViewById<TextView?>(R.id.textView338)
        val amount = itemView.findViewById<TextView?>(R.id.textView339)
        val unitt = itemView.findViewById<TextView?>(R.id.textView355)
        val status = itemView.findViewById<TextView?>(R.id.textView394)
        val price = itemView.findViewById<TextView?>(R.id.textView397)
    }

}


class returnAdapter(
    var activity: FragmentActivity?, val fragment: MyOrderFragment,
    var dataItem: List<OrdProductsItem?>?
) :
    RecyclerView.Adapter<returnAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_return, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataItem?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (dataItem != null) {

            holder.itemm?.text = dataItem?.get(holder.adapterPosition)?.name
            holder.unitt?.text = dataItem?.get(holder.adapterPosition)?.size
            holder.qtty?.setText(dataItem?.get(holder.adapterPosition)?.productQuantity)
            holder.pricce?.text = dataItem?.get(holder.adapterPosition)?.price.toString()
            (dataItem?.get(holder.adapterPosition)?.enterdQuantity) =
                holder.qtty?.text.toString().toFloat()


            var quantity: Int =
                dataItem?.get(holder.adapterPosition)?.productQuantity?.toInt()!!

            var selected = 0

            holder.plus?.setOnClickListener {
                var minimum = 0
                var maximum = dataItem?.get(holder.adapterPosition)?.productQuantity?.toInt()!!

                quantity = Integer.parseInt(holder.qtty.getText().toString())
                if (Integer.parseInt(holder.qtty.getText().toString()) <= maximum) {
                    if (Integer.parseInt(holder.qtty.getText().toString()) < maximum) {
                        quantity = quantity + 1
                    }
                    holder.qtty.text = quantity.toString()
                    (dataItem?.get(holder.adapterPosition)?.enterdQuantity) = quantity.toFloat()
                } else {
                    Toast.makeText(
                        activity,
                        "Enter a valid Quantity",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            holder.minus.setOnClickListener {

                quantity = Integer.parseInt(holder.qtty.getText().toString())
                if (Integer.parseInt(holder.qtty.getText().toString()) > 1
                ) {
                    quantity = quantity - 1
                    holder.qtty.text = quantity.toString()
                    (dataItem?.get(holder.adapterPosition)?.enterdQuantity) = quantity.toFloat()

                } else {
                    Toast.makeText(
                        activity,
                        "Enter a valid Quantity",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemm = itemView.findViewById<TextView?>(R.id.textView3317)
        val unitt = itemView.findViewById<TextView?>(R.id.textView3515)
        val qtty = itemView.findViewById<TextView?>(R.id.textView75)
        val pricce = itemView.findViewById<TextView?>(R.id.textView3319)
        val plus = itemView.findViewById<TextView?>(R.id.textView76)
        val minus = itemView.findViewById<TextView?>(R.id.textView74)
        val status = itemView.findViewById<TextView?>(R.id.textView393)


    }

    public fun getArrayList(): List<OrdProductsItem?>? {
        return dataItem
    }
}


override fun onResume() {
    super.onResume()
    activity?.let {
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(it)
            .registerReceiver(
                receive, IntentFilter(
                    Constants.ACTION_NOTIFICATION
                )
            )
    }
}

override fun onPause() {
    super.onPause()
    activity?.let {
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(it)
            .unregisterReceiver(receive)
    }
}




}

