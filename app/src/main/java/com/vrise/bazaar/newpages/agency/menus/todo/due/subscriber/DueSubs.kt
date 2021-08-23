package com.vrise.bazaar.newpages.agency.menus.todo.due.subscriber

import androidx.lifecycle.*
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.SubduelistItem
import kotlinx.android.synthetic.main.fragment_due_subs.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*

//class DueSubs : InitSub() {
//
//    override fun networkAvailable() {
//        try {
//            getSP(routeId)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private var routeId = "0"
//    private var viewModelDueSub: DueSubData? = null
//
//    private val dueObserver = Observer<ArrayList<SubduelistItem>> { t ->
//        if (t != null) {
//            setAdapter(t)
//        } else {
//            progressBar15.visibility = View.GONE
//        }
//    }
//
//    private fun setAdapter(t: ArrayList<SubduelistItem>) {
//        progressBar15.visibility = View.GONE
//        recyclerView11.layoutManager = LinearLayoutManager(activity)
//        recyclerView11.adapter = DueSubsList(activity!!, t)
//    }
//
//    override fun initView() {
//        progressBar15.visibility = View.VISIBLE
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Subscriber Due"
//        progressBar15.visibility = View.VISIBLE
//        val bundle = arguments
//        routeId = bundle?.getString(Constants.ID) ?: ""
//        getSP(routeId)
//    }
//
//    private fun getSP(routeId: String) {
//        viewModelDueSub = ViewModelProviders.of(activity!!).get(DueSubData::class.java)
//        viewModelDueSub!!.getDues(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(context, DATAFILE, Constants.ID),
//                routeId = routeId
//        )).observe(this, dueObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_due_subs, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
