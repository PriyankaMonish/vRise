package com.vrise.bazaar.newpages.agency.menus.newsubscriberrequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R

import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopUp
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import com.vrise.bazaar.newpages.utilities.models.Requests
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_newly_requested_subscriber.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback

//class NewlyRequstedSubscribers : InitSub() {
//
//    override fun networkAvailable() {
//        getData()
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    var subscriber = ""
//    private var routeId: String = ""
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
//        activity?.page_title?.text = "Newly Requested Subscribers"
//
//        textView100.visibility = View.GONE
//        textView101.visibility = View.GONE
//        textView98.visibility = View.GONE
//
//        if (hasConnection(activity)) {
//            /*getData()*/
//        }
//
//        textView100.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasConnection(activity)) {
//                    textView102.visibility = View.VISIBLE
//                    api("reject")
//                }
//            }
//        })
//        textView101.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasConnection(activity)) {
//                    textView102.visibility = View.VISIBLE
//                    /*if (hasText(textView97)) {*/
//                    if (routeId.isNotBlank()) {
//                        api("accept")
//                    } else {
//                        textView102.visibility = View.GONE
//                        textView98.error = "Required"
//                    }
//                    /*} else {
//                        textView102.visibility = View.GONE
//                    }*/
//                }
//            }
//        })
//    }
//
//    private fun api(tri: String) {
//        textView102.visibility = View.VISIBLE
//
//        HelperMethods.println(
//            Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                subscriberId = subscriber,
//                string = tri,
//                routeId = routeId,
//                code = textView97.text.toString()
//            )
//        )
//
//        apiService?.subscriberProcess(
//            Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                subscriberId = subscriber,
//                string = tri,
//                routeId = routeId,
//                code = textView97.text.toString()
//            )
//        )?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    textView102.visibility = View.GONE
//                    t?.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (hasData(activity!!, response)) {
//                        textView102.visibility = View.GONE
//                        val bundle = Bundle()
//                        bundle.putString(Constants.ID, subscriber)
//                        if (tri == "accept") {
//                            navigateTo(
//                                activity,
//                                Constants.FRAGMENT,
//                                false,
//                                SubscriberDetails(),
//                                null,
//                                bundle,
//                                null,
//                                ""
//                            )
//                        } else {
//                            textView102.visibility = View.GONE
//                            getData()
//                        }
//                    } else {
//                        textView102.visibility = View.GONE
//                        getData()
//                    }
//                }
//            }
//        })
//
//    }
//
//    private fun getData() {
//        news.adapter = EmptyList(activity, Requests(display = "No Data available"))
//        val bundle = arguments
//        if (bundle != null) {
//            subscriber = bundle.getString(Constants.ID, "")
//        }
//        if (hasConnection(activity)) {
//            print(
//                Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                    subscriberId = subscriber
//                )
//            )
//            apiService?.getSubscriberDetails(
//                Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                    subscriberId = subscriber
//                )
//            )?.enqueue(object : Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    if (activity != null && isAdded) {
//                        news.visibility = View.VISIBLE
//                        t?.printStackTrace()
//                    }
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (activity != null && isAdded) {
//                        news.visibility = View.GONE
//                        if (hasData(activity!!, response)) {
//                            if (response?.body()?.data != null) {
//                                if (response.body()!!.data!!.subdata != null) {
//                                    response.body()!!.data!!.subdata
//                                    Picasso.get().load(response.body()?.data?.subdata?.imgLink.toString())
//                                        .into(imageView8)
//                                    imageView8.borderColor = ContextCompat.getColor(activity!!, R.color.cb_errorRed)
//                                    textView47.text = response.body()?.data?.subdata?.name
//                                    textView48.text = response.body()?.data?.subdata?.mobile
//                                    imageView9.setOnClickListener {
//                                        activity?.startActivity(
//                                            Intent(
//                                                Intent.ACTION_DIAL,
//                                                Uri.fromParts(
//                                                    "tel",
//                                                    response.body()?.data?.subdata?.mobile.toString(),
//                                                    null
//                                                )
//                                            )
//                                        )
//                                    }
//                                    textView95.text = response.body()?.data?.subdata?.name
//                                    textView96.text = response.body()?.data?.subdata?.mobile
//                                    textView97.text = response.body()?.data?.subdata?.code
//                                    textView98.setOnClickListener(object : ClickListener() {
//                                        override fun onOneClick(v: View) {
//                                            if (response.body()?.data?.rootlist != null) {
//                                                if (response.body()!!.data!!.rootlist!!.isNotEmpty()) {
//                                                    val arrayList = object : ArrayList<Request>() {
//                                                        init {
//                                                            for (i in 0 until response.body()!!.data!!.rootlist!!.size) {
//                                                                add(
//                                                                    Request(
//                                                                        name = response.body()?.data?.rootlist!![i].name,
//                                                                        code = response.body()?.data?.rootlist!![i].id
//                                                                    )
//                                                                )
//                                                            }
//                                                        }
//                                                    }
//                                                    openPopUp(
//                                                        activity!!,
//                                                        arrayList,
//                                                        textView98,
//                                                        "Select routes",
//                                                        object : Interfaces.ReturnId {
//                                                            override fun returnId(string: String) {
//                                                                routeId = string
//                                                            }
//                                                        })
//                                                } else {
//                                                    toast(activity, "No Routes Available")
//                                                }
//                                            } else {
//                                                toast(activity, "No Routes Available")
//                                            }
//                                        }
//                                    })
//                                    textView99s.text = response.body()!!.data!!.subdata!!.address
//
//                                    if (response.body()?.data?.subdata?.status.toString() == "ok") {
//                                        textView100.visibility = View.GONE
//                                        textView101.visibility = View.GONE
//                                        textView98.visibility = View.GONE
//                                    } else {
//                                        textView100.visibility = View.VISIBLE
//                                        textView101.visibility = View.VISIBLE
//                                        textView98.visibility = View.VISIBLE
//                                    }
//
//                                }
//                            } else {
//                                news.visibility = View.VISIBLE
//                            }
//                        } else {
//                            news.visibility = View.VISIBLE
//                        }
//                    }
//                }
//            })
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_newly_requested_subscriber, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
