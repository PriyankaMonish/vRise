package com.vrise.bazaar.newpages.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import kotlinx.android.synthetic.main.fragment_chose_type.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*

//class ChooseUserType : InitSub() {
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//    }
//
//    override fun initControl() {
//        activity?.page_title?.text = getString(R.string.choose_user_type)
//
//        textView10.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                progressBar3.visibility = View.VISIBLE
//                /*navigateTo(activity, Constants.FRAGMENT, false, AgentRegistration(), null, null, null, "")*/
//                apiService?.newsUserTypes(
//                    Request(
//                        utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                        user_type = "2",
//                        agent_id = ""
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
//                            if (data != null) {
//                                if (data.action != null) {
//                                    if (data.action == Constants.EXECUTIVECODE) {
//                                        val bundle = Bundle()
//                                        bundle.putString(Constants.ID, Preference.getSinglePreference(activity, DATAFILE, Constants.ID))
//                                        navigateTo(
//                                            activity,
//                                            Constants.FRAGMENT,
//                                            false,
//                                            MarketingExecutiveCode(),
//                                            null,
//                                            bundle,
//                                            null,
//                                            ""
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }) {})
//            }
//        })
//        textView11.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                progressBar4.visibility = View.VISIBLE
//                navigateTo(activity, Constants.FRAGMENT, false, SubscriberRegistration(), null, null, null, "")
//            }
//        })
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_chose_type, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}