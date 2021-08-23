package com.vrise.bazaar.newpages.agency.subscriber

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails.SubscriberDetails
//import com.vrise.bazaar.newpages.utilities.ClickListener
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hideKeyboard
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.Validator.isPhone
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import kotlinx.android.synthetic.main.fragment_add_subcribers.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback
//
//class AddSubscribers : InitSub() {
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    var route_Id = ""
//    var routeNam = ""
//
//    override fun initView() {
//    }
//
//    override fun initModel() {
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Add Subscriber"
//
//        getBundle()
//
//        textView53.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//
//                if (
//                        hasText(textView49, "Required") &&
//                        hasText(textView50, "Required") &&
//                        isPhone(textView50) &&
//                        hasText(textView52, "Required") &&
//                        route_Id != ""
//                ) {
//                    try {
//                        hideKeyboard(activity!!)
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                    progressBar.visibility = View.VISIBLE
//
//                    apiService?.addSubscriber(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            mobile = textView50.text.toString(),
//                            name = textView49.text.toString(),
//                            routeId = route_Id
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    if (response!!.body()!!.data != null)
//                                        if (response.body()!!.data!!.subscriberId != null) {
//                                            val bundle = Bundle()
//                                            bundle.putString(Constants.ID, response.body()!!.data!!.subscriberId)
//                                            navigateTo(activity!!, Constants.FRAGMENT, false, SubscriberDetails(), null, bundle, null, "")
//                                        }
//                                }
//                                progressBar.visibility = View.GONE
//                            }
//                        }
//                    })
//                }
//            }
//        })
//
//    }
//
//    private fun getBundle() {
//        val bundle = arguments
//        if (bundle != null) {
//            route_Id = bundle.getString(Constants.ID) ?: ""
//            routeNam = bundle.getString(Constants.SECOND) ?: ""
//        }
//        textView52.text = routeNam
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_add_subcribers, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
