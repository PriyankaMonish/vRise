package com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.Validator.isPhone
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.fragment_subscribers_details_edit.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class SubscribersDetail : InitSub() {
//
//    var subscriber = ""
//    var routeId = ""
//    var from = "0"
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
//        activity!!.page_title.text = "Subscriber Details"
//
//        textView101.setOnClickListener {
//            textView102.visibility = View.VISIBLE
//            if (hasConnection(activity)) {
//                if (
//                        hasText(arrayOf(textView95, textView96)) &&
//                        isPhone(textView96) &&
//                        hasText(textView98) &&
//                        routeId.isNotBlank() &&
//                        subscriber.isNotBlank()
//                ) {
//                    print(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            mobile = textView96.text.toString(),
//                            name = textView95.text.toString(),
//                            routeId = routeId,
//                            subscriberId = subscriber
//                    ))
//                    apiService?.subscriberupdate(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            mobile = textView96.text.toString(),
//                            name = textView95.text.toString(),
//                            routeId = routeId,
//                            subscriberId = subscriber
//                    ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun failed(t: Throwable) {
//                            textView102.visibility = View.GONE
//                            t.printStackTrace()
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//
//                            }
//                            textView102.visibility = View.GONE
//                        }
//                    }) {})
//                } else {
//                    if (subscriber.isBlank()) {
//
//                    }
//                }
//            } else {
//                textView102.visibility = View.GONE
//            }
//        }
//    }
//
//    override fun networkAvailable() {
//        getData()
//    }
//
//    private fun getData() {
//        overla.visibility = View.GONE
//        val bundle = arguments
//        if (bundle != null) {
//            subscriber = bundle.getString(Constants.ID, "")
//            from = bundle.getString(Constants.FROM, "0")
//        }
//        if (hasConnection(activity)) {
//            apiService?.getSubscriberDetails(Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                    subscriberId = subscriber
//            ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                override fun additionalData(display: String?, logout: Boolean) {
//
//                }
//
//                override fun failed(t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun success(response: Response?, data: Data?, state: Boolean) {
//                    if (state) {
//                        if (data != null) {
//                            if (data.subdata != null) {
//
//                                try {
//                                    Picasso.get().load(data.subdata.imgLink.toString()).into(imageView8)
//                                    if (data.subdata.deviceae.isNullOrBlank()) {
//                                        imageView8.borderColor = ContextCompat.getColor(activity!!, R.color.cb_errorRed)
//                                    } else {
//                                        imageView8.borderColor = ContextCompat.getColor(activity!!, R.color.bright_turquoise)
//                                    }
//
//                                    if (from == 0.toString()) {
//                                        overla.visibility = View.GONE
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                                            textView98.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_pincodes, 0, R.drawable.ic_down, 0)
//                                        }
//                                        textView101.visibility = View.VISIBLE
//                                        imageView9.visibility = View.VISIBLE
//                                        textView99.visibility = View.GONE
//                                        textView98.setOnClickListener(object : ClickListener() {
//                                            override fun onOneClick(v: View) {
//                                                if (data.rootlist != null) {
//                                                    if (data.rootlist.isNotEmpty()) {
//                                                        val arrayList = object : ArrayList<Request>() {
//                                                            init {
//                                                                for (i in 0 until data.rootlist.size) {
//                                                                    add(Request(
//                                                                            name = data.rootlist[i].name,
//                                                                            code = data.rootlist[i].id
//                                                                    ))
//                                                                }
//                                                            }
//                                                        }
//                                                        openPopUp(activity!!, arrayList, textView98, "Select routes", object : Interfaces.ReturnId {
//                                                            override fun returnId(string: String) {
//                                                                routeId = string
//                                                            }
//                                                        })
//                                                    } else {
//                                                        toast(activity, "No Routes Available")
//                                                    }
//                                                } else {
//                                                    toast(activity, "No Routes Available")
//                                                }
//                                            }
//                                        })
//                                        imageView9.setOnClickListener {
//                                            activity?.startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", data.subdata.mobile.toString(), null)))
//                                        }
//                                    } else {
//                                        overla.visibility = View.VISIBLE
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                                            textView98.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_pincodes, 0, 0, 0)
//                                        }
//                                        imageView9.visibility = View.GONE
//                                        textView101.visibility = View.GONE
//                                        textView99.visibility = View.VISIBLE
//                                        textView99s.text = "" + "${data.subdata.address}, ${data.subdata.address2}, ${data.subdata.district_name}, ${data.subdata.state_name}"
//
//                                        if (textView99s.text.toString().replace(",", "").isBlank()) {
//                                            textView99s.text = "No Address Added"
//                                        }
//                                    }
//
//                                    textView47.text = data.subdata.name
//                                    textView48.text = data.subdata.mobile
//                                    textView95.setText(data.subdata.name)
//                                    textView96.setText(data.subdata.mobile)
//                                    textView97.text = data.subdata.code
//                                    if (data.subdata.route_name.isNullOrBlank()) {
//                                    } else {
//                                        textView98.text = data.subdata.route_name.toString()
//                                    }
//                                    routeId = data.subdata.routeId.toString()
//                                } catch (e: Exception) {
//                                    e.printStackTrace()
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }) {
//
//            })
//        }
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscribers_details_edit, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
