package com.vrise.bazaar.newpages.registration

//import android.app.Dialog
//import android.os.Bundle
//import com.google.android.material.textfield.TextInputEditText
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.Window.FEATURE_NO_TITLE
//import android.widget.ProgressBar
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.containers.AgentContainer
//import com.vrise.bazaar.newpages.utilities.ClickListener
//import com.vrise.bazaar.newpages.utilities.Constants.ACTIVITY
//import com.vrise.bazaar.newpages.utilities.Constants.ADASH
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.ID
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopup
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import kotlinx.android.synthetic.main.fragment_marketing_executive.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback

//class MarketingExecutiveCode : InitSub() {
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
//
//    }
//
//    override fun initControl() {
//        activity?.page_title?.text = getString(R.string.agent_registration)
//        val bundle = arguments
//        textView38.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (bundle != null) {
//                    openPopsUp(bundle)
//                }
//            }
//        })
//        textView37.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                openPopup(
//                    getString(R.string.Pay_Per_All_Administrator_Will_Contact_You),
//                    activity!!,
//                    false,
//                    object : Interfaces.Invoke {
//                        override fun invokeMe() {
//                            /*logoutfromapp(activity!!)*/
//                            activity?.finish()
//                        }
//                    })
//            }
//        })
//    }
//
//    private fun openPopsUp(bundle: Bundle?) {
//        val dialog = Dialog(requireContext())
//        dialog.setCancelable(true)
//        dialog.requestWindowFeature(FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.add_marketing_executive_code)
//        activity?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        val textView37 = dialog.findViewById<TextView>(R.id.textView37)
//        val textView38 = dialog.findViewById<TextView>(R.id.textView38)
//        val progressBar1 = dialog.findViewById<ProgressBar>(R.id.progressBar)
//        val textView35 = dialog.findViewById<TextInputEditText>(R.id.textView35)
//        textView38.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasText(textView35)) {
//                    progressBar1.visibility = View.VISIBLE
//                    println(
//                        Request(
//                            utoken = bundle?.getString(ID, ""),
//                            market_code = textView35.text.toString()
//                        )
//                    )
//                    apiService?.postMarketingCode(
//                        Request(
//                            utoken = bundle?.getString(ID, ""),
//                            market_code = textView35.text.toString()
//                        )
//                    )?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar1.visibility = View.GONE
//                                t?.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    if (response!!.body()!!.data != null && response.body()!!.data!!.action != null)
//                                        if (response.body()!!.data!!.action == ADASH) {
//                                            com.vrise.bazaar.newpages.utilities.Preference.putPreference(
//                                                activity!!,
//                                                ID,
//                                                bundle?.getString(ID, ""),
//                                                DATAFILE
//                                            )
//                                            dialog.dismiss()
//
//                                            progressBar.visibility = View.VISIBLE
//
//                                            navigateTo(
//                                                activity,
//                                                ACTIVITY,
//                                                false,
//                                                null,
//                                                AgentContainer::class.java,
//                                                null,
//                                                null,
//                                                ""
//                                            )
//                                        }
//                                }
//                                progressBar1.visibility = View.GONE
//                            }
//                        }
//                    })
//                } else {
//                    progressBar1.visibility = View.GONE
//                }
//            }
//
//        })
//        textView37.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_marketing_executive, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
