package com.vrise.bazaar.newpages.agency.menus.newsubscriberrequest

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast

import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
import kotlinx.android.synthetic.main.fragment_new_subscriber_list.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*

//class NewSubscriberRequest : InitSub() {
//
//    override fun networkAvailable() {
//        toast(activity, "NewSubscriberRequest")
//        try {
//            apiCall()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun networkUnavailable() {
//        progressBar.visibility = View.VISIBLE
//    }
//
//    private var viewModel: SubscriberRequestData? = null
//    private val itemObserver = Observer<ArrayList<SublistItem>> { t ->
//        if (t != null) {
//            setAdapter(t)
//            no_subscribers_found.visibility = View.GONE
//        } else {
//            progressBar.visibility = View.GONE
//            no_subscribers_found.visibility = View.VISIBLE
//        }
//    }
//
//    private fun setAdapter(t: ArrayList<SublistItem>) {
//        progressBar.visibility = View.GONE
//        recyclerView13.layoutManager = LinearLayoutManager(activity)
//        recyclerView13.adapter = ListOfSubscriber(activity!!, t)
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
//        progressBar.visibility = View.VISIBLE
//        activity!!.page_title.text = "New Subscriber Request"
//
//        if (hasConnection(activity)) {
//            apiCall()
//        }
//    }
//
//    private fun apiCall() {
//        viewModel = ViewModelProviders.of(activity!!).get(SubscriberRequestData::class.java)
//        viewModel!!.getRequestedSubcribers(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)
//        )).observe(this, itemObserver)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_new_subscriber_list, container, false)
//    }
//
//}
