package com.vrise.bazaar.newpages.agency.todo.bills

//import androidx.lifecycle.*
//import androidx.lifecycle.Observer
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.EmptyList
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.models.Requests
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
//import kotlinx.android.synthetic.main.fragment_bills.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class Bills : InitSub() {
//    override fun networkAvailable() {
//        try {
//            getBis(subscriber)
//        } catch (e: Exception) {
//
//        }
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    private var subscriber = ""
//    private var viewModelBill: BillData? = null
//    private val billObserver = Observer<List<BilllistItem?>> { t ->
//        if (t != null) {
//            setBillsAdapter(t)
//        } else {
//            progress_horizontal.visibility = View.GONE
//            recyclerView10.layoutManager = LinearLayoutManager(activity)
//            recyclerView10.adapter = EmptyList(activity!!, Requests(
//                    display = "No Bills",
//                    id = R.drawable.ic_due_nonavailable
//            ))
//        }
//    }
//
//    private fun setBillsAdapter(t: List<BilllistItem?>) {
//        recyclerView10.layoutManager = LinearLayoutManager(activity)
//        progress_horizontal.visibility = View.GONE
//        recyclerView10.adapter = Billz(activity!!, t)
//    }
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//
//    override fun initControl() {
//
//        val bundle = arguments
//        subscriber = bundle!!.getString(Constants.ID, "")
//        activity!!.page_title.text = "Bills"
//
//        progress_horizontal.visibility = View.VISIBLE
//
//        textView70.setOnClickListener {
//            YearMonthPickerDialog(activity, YearMonthPickerDialog.OnDateSetListener { year, month ->
//                val calendar: Calendar? = Calendar.getInstance()
//                calendar?.set(Calendar.YEAR, year)
//                calendar?.set(Calendar.MONTH, month)
//                val dateFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())
//                textView70.text = dateFormat.format(calendar?.time)
//                getBis(subscriber)
//            }, R.style.AlertDialogTheme).show()
//        }
//    }
//
//    private fun getBis(subscriber: String) {
//
//        progress_horizontal.visibility = View.VISIBLE
//
//        viewModelBill = ViewModelProviders.of(activity!!).get(BillData::class.java)
//        viewModelBill?.getBills(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                month = textView70.text.toString(),
//                subscriberId = subscriber
//        ))?.observe(this, billObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_bills, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
