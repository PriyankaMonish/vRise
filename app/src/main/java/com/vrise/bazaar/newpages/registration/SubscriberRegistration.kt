package com.vrise.bazaar.newpages.registration

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.CallbackClient
//import com.vrise.bazaar.newpages.utilities.ClickListener
//import com.vrise.bazaar.newpages.utilities.Constants.AGENT_CODE
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import kotlinx.android.synthetic.main.fragment_sub_registration.*

//class SubscriberRegistration : InitSub() {
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
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
//
////        activity?.page_title?.text = getString(R.string.subscriber_registration)
//
//      submit.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//             progressBar5.visibility = View.VISIBLE
//                if (hasText(textView9, getString(R.string.enter_agent_code))) {
//
//                    if (hasConnection(activity)) {
//                        apiService?.getAgentDetails(Request(
//                                agent_code = textView9.text.toString()
//                        ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                            override fun additionalData(display: String?, logout: Boolean) {
//
//                            }
//
//                            override fun failed(t: Throwable) {
//                               progressBar5.visibility = View.GONE
//                                t.printStackTrace()
//                            }
//
//                            override fun success(response: Response?, data: Data?, state: Boolean) {
//                                if (state){
//                                    val bundle = Bundle()
//                                    bundle.putString(AGENT_CODE, textView9.text.toString())
//                                    navigateTo(activity, FRAGMENT, true, YourAgentDetails(), null, bundle, null, "")
//                                } else {
//
//                                }
//                               progressBar5.visibility = View.GONE
//                            }
//                        }) {})
//                    }
//                } else {
//                   progressBar5.visibility = View.GONE
//                }
//            }
//        })
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        return inflater.inflate(R.layout.fragment_sub_registration, container, false)
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setInitializer()
//
//    }
//}