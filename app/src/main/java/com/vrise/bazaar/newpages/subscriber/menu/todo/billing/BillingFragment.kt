package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.EmptyList

import com.vrise.bazaar.newpages.utilities.models.Requests
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
import kotlinx.android.synthetic.main.app_bar_sub_container.*
import kotlinx.android.synthetic.main.list_fragment.*


//class BillingFragment : InitSub() {
//
//    override fun networkAvailable() {
//        getDetails()
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private lateinit var viewModel: BillingItems
//    val billingItemsObserver = Observer<Pair<String, List<BilllistItem?>>> { t ->
//        if (t != null) {
//            setBillingItemsAdapter(t.first, t.second)
//        } else {
//            recycle.adapter = EmptyList(activity!!, Requests(
//                    display = "No Bills",
//                    id = R.drawable.ic_due_nonavailable
//            ))
//            progressBar21.visibility = View.GONE
//        }
//    }
//
//    private fun setBillingItemsAdapter(dueAmount: String, list: List<BilllistItem?>?) {
//        progressBar21.visibility = View.GONE
//        recycle.adapter = BillingList(activity!!, list, dueAmount)
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
//    override fun initControl() {
//        setHasOptionsMenu(true)
//        activity!!.page_title.text = "Billing"
//        recycle.layoutManager = LinearLayoutManager(activity)
//    }
//
//    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PayuConstants.PAYU_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                if (data != null && data.hasExtra("MESSAGE")) {
//                    val resStr = data.getStringExtra("MESSAGE")
//                    Toast.makeText(activity, resStr, Toast.LENGTH_SHORT).show()
//                }
//            } else if (resultCode == RESULT_CANCELED)
//                Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show()
//        }
//    }*/
//
//    private fun getDetails() {
//        progressBar21.visibility = View.VISIBLE
//        viewModel = ViewModelProviders.of(activity!!).get(BillingItems::class.java)
//        viewModel.getBillingItems(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                month = ""
//        )).observe(this, billingItemsObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.list_fragment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
