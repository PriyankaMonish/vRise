package com.vrise.bazaar.newpages.registration

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.AGENT_CODE
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.fragment_your_agnet_detailss.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback

//class YourAgentDetails : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    var agentCode = ""
//    var agentId = ""
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
//        activity?.page_title?.text = "Your Agent Details"
//
//        getBundledata()
//
//        if (agentCode.isBlank()) {
//            fragmentManager?.popBackStack()
//        } else {
//            println(
//                Request(
//                    agent_code = agentCode
//                )
//            )
//            apiService?.getAgentDetails(
//                Request(
//                    agent_code = agentCode
//                )
//            )?.enqueue(object : Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    if (activity != null && isAdded) {
//                        t?.printStackTrace()
//                    }
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (activity != null && isAdded) {
//                        if (hasData(activity!!, response)) {
//                            if (response!!.body()!!.data != null) {
//                                if (response.body()!!.data!!.agentdata != null) {
//                                    textView121.setTextAutoTyping(response.body()!!.data!!.agentdata!!.code)
//                                    textView122.setTextAutoTyping(response.body()!!.data!!.agentdata!!.name)
//                                    MOBILE.setText(response.body()!!.data!!.agentdata!!.mobile)
//                                    MOBILE.isEnabled=false
//                                    MOBILE.isClickable=false
//                                    email.setText(response.body()!!.data!!.agentdata!!.email)
//                                    email.isEnabled=false
//                                    email.isClickable=false
////                                    textView126.setTextAutoTyping(
////                                        "${response.body()!!.data!!.agentdata!!.address}, ${response.body()!!.data!!.agentdata!!.address2}, ${response.body()!!.data!!.agentdata!!.district_name}, ${response.body()!!.data!!.agentdata!!.states}".replace(
////                                            ", ,",
////                                            " "
////                                        )
////                                    )
//                                    pincode.setText(response.body()!!.data!!.agentdata!!.pincode)
//                                    pincode.isEnabled=false
//                                    pincode.isClickable=false
//                                    agentId = response.body()!!.data!!.agentdata!!.id.toString()
//                                    Picasso.get().load(response.body()!!.data!!.img_link)
//                                        .placeholder(R.drawable.ic_placeholder).fit().into(imageView27)
//                                }
//                            }
//                        } else {
//                            fragmentManager?.popBackStack()
//                        }
//                    }
//                }
//            })
//        }
//        textView129.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                fragmentManager?.popBackStack()
//            }
//        })
//        textView128.setOnClickListener {
//            if (
//                hasText(textView121, "") &&
//                hasText(textView122, "") &&
//                hasText(MOBILE, "") &&
//                hasText(email, "") &&
//                hasText(pincode, "")
////                hasText(textView127, "")
//            ) {
//                apiService?.newsUserTypes(
//                    Request(
//                        utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.DATAFILE, Constants.ID),
//                        user_type = "1",
//                        agent_id = agentId
//                    )
//                )?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                    override fun additionalData(display: String?, logout: Boolean) {
//
//                    }
//
//                    override fun failed(t: Throwable) {
//                        t.printStackTrace()
//                    }
//
//                    override fun success(response: Response?, data: Data?, state: Boolean) {
//                        if (state) {
//                            navigateTo(
//                                activity,
//                                FRAGMENT,
//                                true,
//                                ContactAgentPopUp(),
//                                null,
//                                null,
//                                null,
//                                ""
//                            )
//                        }
//                    }
//                }) {})
//                /*val bundle = Bundle()
//                bundle.putString(AGENT_ID, agentId)*/
//                /*navigateTo(activity, FRAGMENT, true, SubscriberInformation(), null, bundle, null, "")*/
//            }
//        }
//    }
//
//    private fun getBundledata() {
//        val bundle = arguments
//        if (bundle != null) {
//            agentCode = bundle.getString(AGENT_CODE, "")
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_your_agnet_detailss, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
