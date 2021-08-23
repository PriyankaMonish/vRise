package com.vrise.bazaar.newpages.agency.menus.todo.due.routes

import androidx.lifecycle.*
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.DuelistItem
import com.vrise.bazaar.newpages.utilities.models.submodels.RouteidsItem
import kotlinx.android.synthetic.main.fragment_due.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class Due : InitSub() {
//    override fun networkAvailable() {
//        try {
//            toast(activity, "Due")
//            getSP()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    private var viewModelDue: DueData? = null
//    private var remidDataIds: ArrayList<RouteidsItem>? = null
//    private val dueObserver = Observer<ArrayList<DuelistItem>> { t ->
//        if (t != null) {
//            recyclerView.visibility = View.GONE
//            if (remidDataIds != null) {
//                remidDataIds!!.clear()
//                for (i in 0 until t.size) {
//                    remidDataIds!!.add(RouteidsItem(
//                            id = t[i].id
//                    ))
//                }
//                if (remidDataIds!!.isEmpty()) {
//                    textView71.visibility = View.GONE
//                } else {
//                    textView71.visibility = View.VISIBLE
//                }
//            } else {
//                textView71.visibility = View.GONE
//            }
//            setAdapter(t)
//        } else {
//            recyclerView.visibility = View.VISIBLE
//            textView71.visibility = View.GONE
//            progressBar14.visibility = View.GONE
//        }
//    }
//
//    private fun setAdapter(t: ArrayList<DuelistItem>) {
//        progressBar14.visibility = View.GONE
//        if (t != null && t.isNotEmpty()) {
//            recyclerView.visibility = View.GONE
//            recyclerView10.layoutManager = LinearLayoutManager(activity)
//            recyclerView10.adapter = DueList(activity!!, t)
//        } else {
//            recyclerView.visibility = View.VISIBLE
//        }
//    }
//
//    override fun initView() {
//        progressBar14.visibility = View.VISIBLE
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Due"
//        remidDataIds = ArrayList()
//        textView71.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (remidDataIds != null)
//                    if (remidDataIds!!.isNotEmpty()) {
//                        print(Request(
//                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                routeids = remidDataIds
//                        ))
//                        apiService?.dueremind(Request(
//                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                routeids = remidDataIds
//                        ))?.enqueue(object : ConsAndroidCallback(activity!!, this@Due, object : Interfaces.Callback {
//                            override fun additionalData(display: String?, logout: Boolean) {
//
//                            }
//
//                            override fun success(response: Response?, data: Data?, state: Boolean) {
//                                if (state) {
//                                    textView71.visibility = View.GONE
//                                } else {
//
//                                }
//                            }
//
//                            override fun failed(t: Throwable) {
//                                t.printStackTrace()
//                            }
//                        }) {})
//                    }
//            }
//        })
//        progressBar14.visibility = View.VISIBLE
//        getSP()
//    }
//
//    private fun getSP() {
//        viewModelDue = ViewModelProviders.of(activity!!).get(DueData::class.java)
//        viewModelDue!!.getDues(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)
//        )).observe(this, dueObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_due, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
