package com.vrise.bazaar.newpages.agency.todo

//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.changeDateFormat
//import com.vrise.bazaar.newpages.utilities.models.Requests
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.BillProductsItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import kotlinx.android.synthetic.main.fragment_bill_editing.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import java.util.*

//class BillEditing : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    var billItem: BilllistItem? = null
//    var billProducts: ArrayList<BillProductsItem?>? = null
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity?.page_title?.text = "Bill Editing"
//        val bundle = arguments
//        if (bundle != null) {
//            try {
//                billItem = bundle.getSerializable(Constants.ID) as BilllistItem
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            if (billItem != null) {
//                getData(billItem)
//            } else {
//                no.visibility = View.VISIBLE
//                recyclerViews.layoutManager = LinearLayoutManager(activity)
//                recyclerViews.adapter = EmptyList(activity!!, Requests(display = "No Data"))
//            }
//        } else {
//            no.visibility = View.VISIBLE
//            recyclerViews.layoutManager = LinearLayoutManager(activity)
//            recyclerViews.adapter = EmptyList(activity!!, Requests(display = "No Data"))
//        }
//
//        textView79.addTextChangedListener(DecimalInputTextWatcher(textView79, 2))
//        textView81.addTextChangedListener(DecimalInputTextWatcher(textView81, 2))
//    }
//
//    private fun getData(billItem: BilllistItem?) {
//        loading.visibility = View.VISIBLE
//        apiService?.getBillList(Request( utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//            month = billItem?.billMonth.toString(),
//            subscriberId = billItem?.subscriberId.toString()))?.enqueue(object : ConsAndroidCallback(activity, null, object : Interfaces.Callback{
//            override fun additionalData(display: String?, logout: Boolean) {
//
//            }
//
//            override fun failed(t: Throwable) {
//                t.printStackTrace()
//            }
//
//            override fun success(response: Response?, data: Data?, state: Boolean) {
//                if (state){
//                    if (data != null) {
//                        if (data.billlist != null){
//                            if (data.billlist.isNotEmpty()){
//                                val item = data.billlist[0]
//                                no.visibility = View.GONE
//                                textView70.text = item?.subscriberName
//                                textView72.text = "Date"
//                                textView71.text = item?.billCode
//                                textView73.text = changeDateFormat("yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy", item?.billDate.toString())
//                                textView75.text = "₹ ${item?.totalAmount}"
//                                textView82.setText(item?.remark.toString())
//                                textView82.setHorizontallyScrolling(false)
//                                textView82.setLines(5)
//
//                                billProducts = ArrayList()
//
//                                if (item?.billProducts != null) {
//                                    if (item.billProducts.isNotEmpty()) {
//                                        for (i in 0 until item.billProducts.size) {
//                                            billProducts?.add(BillProductsItem(
//                                                    roundTotal = item.billProducts[i]?.totalAmount,
//                                                    id = item.billProducts[i]?.id
//                                            ))
//                                        }
//                                    }
//                                }
//
//                                recyclerView.layoutManager = LinearLayoutManager(activity)
//                                recyclerView.adapter = SubItemListEditable(activity!!, item?.billProducts?.toMutableList(), item?.id, object : Interfaces.ReturnBillProductsItem {
//                                    override fun addData(clickPosvalue: BillProductsItem) {
//                                        if (billProducts != null) {
//                                            val billProductsItem = billProducts?.filter { it?.id.toString().toLowerCase() == clickPosvalue.id.toString().toLowerCase() }
//                                            billProducts?.let { `is` ->
//                                                billProductsItem?.let {
//                                                    `is`.remove(element = it[0])
//                                                }
//                                                `is`.add(BillProductsItem(
//                                                        roundTotal = clickPosvalue.totalAmount,
//                                                        id = clickPosvalue.id
//                                                ))
//                                            }
//
//                                            editData(item, false)
//                                        }
//                                    }
//
//                                    override fun removeData(clickPosvalue: BillProductsItem) {
//                                        try {
//                                            val billProductsItem = billProducts?.filter { it?.id.toString().toLowerCase() == clickPosvalue.id.toString().toLowerCase() }
//                                            billProducts?.let { `is` ->
//                                                billProductsItem?.let {
//                                                    `is`.remove(billProductsItem[0])
//                                                }
//                                            }
//                                            /*textView75.text = "₹ " + Math.round((((billItem?.totalAmount)?.toDouble()
//                                                    ?: 0.0) - ((clickPosvalue.totalAmount)?.toDouble()
//                                                    ?: 0.0))).toString()*/
//
//                                            getData(item)
//
//                                        } catch (e: Exception) {
//                                            e.printStackTrace()
//                                        }
//                                    }
//                                }, apiService, item?.status)
//
//                                textView83.setOnClickListener(object : ClickListener() {
//                                    override fun onOneClick(v: View) {
//                                        editData(item, true)
//                                    }
//                                })
//                            }
//                        }
//                    }
//
//                }
//                loading.visibility = View.GONE
//            }
//        }){})
//    }
//
//    private fun editData(item: BilllistItem?, boolean: Boolean) {
//        try {
//            loading.visibility = View.VISIBLE
//            progressBar.visibility = View.VISIBLE
//            apiService?.editBills(Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                    billId = item?.id,
//                    additionalAmount = textView81.text.toString(),
//                    discountAmount = textView79.text.toString(),
//                    remark = textView82.text.toString(),
//                    billProducts = billProducts
//            ))?.enqueue(object : ConsAndroidCallback(activity!!, this@BillEditing, object : Interfaces.Callback {
//                override fun additionalData(display: String?, logout: Boolean) {
//
//                }
//
//                override fun success(response: com.vrise.bazaar.newpages.utilities.models.mainmodels.Response?, data: Data?, state: Boolean) {
//                    if (state) {
//
//                        progressBar.visibility = View.GONE
//                        if (boolean){
//                            fragmentManager?.popBackStack()
//                        } else {
//                            getData(item)
//                        }
//                    } else {
//                        loading.visibility = View.GONE
//                        progressBar.visibility = View.GONE
//                    }
//                }
//
//                override fun failed(t: Throwable) {
//                    loading.visibility = View.GONE
//                    progressBar.visibility = View.GONE
//                    t.printStackTrace()
//                }
//            }) {})
//        } catch (e: Exception) {
//            loading.visibility = View.GONE
//            progressBar.visibility = View.GONE
//            e.printStackTrace()
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_bill_editing, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
