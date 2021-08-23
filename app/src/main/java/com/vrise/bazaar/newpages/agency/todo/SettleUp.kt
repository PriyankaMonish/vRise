package com.vrise.bazaar.newpages.agency.todo

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import kotlinx.android.synthetic.main.fragment_settle_up.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*

//class SettleUp : InitSub() {
//    override fun networkAvailable() {
//        getDatas()
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    var subscriber = ""
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Settle Up"
//        val bundle = arguments
//        subscriber = bundle!!.getString(Constants.ID) ?: ""
//        progressBar17.visibility = View.VISIBLE
//        settleuplayout.visibility = View.GONE
//
//        if (textView66.text.toString() != "₹ 0") {
//            textView69.visibility = View.VISIBLE
//        } else {
//            textView69.visibility = View.INVISIBLE
//        }
//
//        textView68.addTextChangedListener(DecimalInputTextWatcher(textView68, 2))
//        textView69.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasText(textView68) && subscriber.isNotBlank()) {
//                    if (textView68.text.toString().toDouble() <= textView66.text.toString().substring(2, textView66.text.length).toDouble()) {
//                        apiService?.settleup(Request(
//                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                                subscriberId = subscriber,
//                                settleAmount = textView68.text.toString()
//                        ))?.enqueue(object : ConsAndroidCallback(activity!!, this@SettleUp, object : Interfaces.Callback {
//                            override fun additionalData(display: String?, logout: Boolean) {
//
//                            }
//
//                            override fun success(response: Response?, data: Data?, state: Boolean) {
//                                if (state) {
//                                    fragmentManager!!.popBackStack()
//                                } else {
//
//                                }
//                            }
//
//                            override fun failed(t: Throwable) {
//                                t.printStackTrace()
//                            }
//                        }) {})
//                    } else {
//                        if (textView66.text.toString() == "₹ 0") {
//                            toastit(activity!!, "No dues")
//                        } else {
//                            toastit(activity!!, "Enter amount less than ${textView66.text}")
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getDatas() {
//        apiService?.settleupview(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                subscriberId = subscriber
//        ))?.enqueue(object : ConsAndroidCallback(activity, this@SettleUp, object : Interfaces.Callback {
//            override fun additionalData(display: String?, logout: Boolean) {
//
//            }
//
//            override fun success(response: Response?, data: Data?, state: Boolean) {
//                if (state) {
//                    progressBar17.visibility = View.GONE
//                    settleuplayout.visibility = View.GONE
//                    if (data != null) {
//                        progressBar17.visibility = View.GONE
//                        settleuplayout.visibility = View.VISIBLE
//                        textView66.text = "₹ " + data.due!!.due.toString()
//                    }
//                } else {
//                    progressBar17.visibility = View.GONE
//                    settleuplayout.visibility = View.GONE
//                }
//            }
//
//            override fun failed(t: Throwable) {
//                progressBar17.visibility = View.GONE
//                settleuplayout.visibility = View.GONE
//                t.printStackTrace()
//            }
//        }) {})
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_settle_up, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
