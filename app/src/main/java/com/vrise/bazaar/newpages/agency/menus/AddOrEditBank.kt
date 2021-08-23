package com.vrise.bazaar.newpages.agency.menus

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.Validator.hasText
import com.vrise.bazaar.newpages.utilities.Validator.isSame
import com.vrise.bazaar.newpages.utilities.Validator.isValidLength
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import kotlinx.android.synthetic.main.bank_edit.*
import kotlinx.android.synthetic.main.bank_show.*
import kotlinx.android.synthetic.main.fragment_add_edit_bank.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback
//
//class AddOrEditBank : InitSub() {
//    override fun networkAvailable() {
//        getDetails()
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    var bankId = ""
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
//        activity!!.page_tiitle.text = "Add or Edit Bank"
//        progressBar.visibility = View.VISIBLE
//
//        calendarView.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//                if (p1) {
//                    image.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_close_box))
//                    constraintLayout5.visibility = View.VISIBLE
//                    constraintLayout6.visibility = View.GONE
//                } else {
//                    image.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_edit))
//                    constraintLayout6.visibility = View.VISIBLE
//                    constraintLayout5.visibility = View.GONE
//                }
//            }
//        })
//
//        image.setOnClickListener {
//            calendarView.isChecked = !calendarView.isChecked
//        }
//
//
//        try {
//            textView75.setOnClickListener {
//                fragmentManager!!.popBackStack()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
////
////        textView75.setOnClickListener(object : ClickListener() {
////            override fun onOneClick(v: View) {
////                progressBar8.visibility = View.VISIBLE
////                if (
////                        hasText(textView70, "Required") &&
////                        hasText(textView71, "Required") &&
////                        hasText(textView72, "Required") &&
////                        isValidLength(textView72, 11) &&
////                        hasText(textView73, "Required") &&
////                        hasText(textView76, "Required") &&
////                        isSame(textView73, textView76) &&
////                        hasText(textView74, "Required")
////                ) {
////                    print(
////                            Request(
////                                    utoken = Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
////                                    bankName = textView70.text.toString(),
////                                    branchName = textView71.text.toString(),
////                                    ifscCode = textView72.text.toString(),
////                                    accountNumber = textView73.text.toString(),
////                                    accountHolder = textView74.text.toString(),
////                                    string = if (bankId.isNullOrBlank()) {
////                                        ""
////                                    } else {
////                                        "update"
////                                    },
////                                    bankId = bankId
////                            )
////                    )
////                    apiService?.addBankDetails(Request(
////                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
////                            bankName = textView70.text.toString(),
////                            branchName = textView71.text.toString(),
////                            ifscCode = textView72.text.toString(),
////                            accountNumber = textView73.text.toString(),
////                            accountHolder = textView74.text.toString(),
////                            string = if (bankId.isNullOrBlank()) {
////                                ""
////                            } else {
////                                "update"
////                            },
////                            bankId = bankId
////                    ))?.enqueue(object : Callback<Response> {
////                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
////                            if (activity != null && isAdded) {
////                                t!!.printStackTrace()
////                                progressBar8.visibility = View.GONE
////                            }
////                        }
////
////                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
////                            if (activity != null && isAdded) {
////                                if (hasData(activity!!, response)) {
////                                    HelperMethods.navigateTo(activity!!, Constants.FRAGMENT, false, AddOrEditBank(), null, null, null, "")
////                                    progressBar8.visibility = View.GONE
////                                } else {
////                                    progressBar8.visibility = View.GONE
////                                }
////                            }
////                        }
////                    })
////                } else {
////                    progressBar8.visibility = View.GONE
////                }
////            }
////        })
//    }
//
//    private fun getDetails() {
////        apiService?.getBankDetail(Request(
////                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)
////        ))?.enqueue(object : Callback<Response> {
////            override fun onFailure(call: Call<Response>?, t: Throwable?) {
////                if (activity != null && isAdded) {
////                    progressBar.visibility = View.GONE
////                    try {
////                        constraintLayout5.visibility = View.INVISIBLE
////                        constraintLayout6.visibility = View.INVISIBLE
////                    } catch (e: Exception) {
////                        e.printStackTrace()
////                    }
////                    t!!.printStackTrace()
////                }
////            }
////
////            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
////                if (activity != null && isAdded) {
////                    if (hasData(activity!!, response)) {
////                        if (response!!.body()!!.data != null) {
////                            if (response.body()!!.data!!.banks != null) {
////
////                                image.visibility = View.GONE
////
////                                try {
////                                    constraintLayout5.visibility = View.INVISIBLE
////                                    constraintLayout6.visibility = View.VISIBLE
////                                } catch (e: Exception) {
////                                    e.printStackTrace()
////                                }
////
////                                bankId = response.body()!!.data!!.banks!!.id.toString()
////
////                                textView115.text = response.body()!!.data!!.banks!!.bankName.toString()
////                                textView140.text = response.body()!!.data!!.banks!!.branchName.toString()
////                                textView142.text = response.body()!!.data!!.banks!!.ifscCode.toString()
////                                textView144.text = response.body()!!.data!!.banks!!.accountNumber.toString()
////                                textView146.text = response.body()!!.data!!.banks!!.accountHolder.toString()
////
////                                textView70.setText(response.body()!!.data!!.banks!!.bankName.toString())
////                                textView71.setText(response.body()!!.data!!.banks!!.branchName.toString())
////                                textView72.setText(response.body()!!.data!!.banks!!.ifscCode.toString())
////                                textView73.setText(response.body()!!.data!!.banks!!.accountNumber.toString())
////                                textView76.setText(response.body()!!.data!!.banks!!.accountNumber.toString())
////                                textView74.setText(response.body()!!.data!!.banks!!.accountHolder.toString())
////                            } else {
////                                try {
////                                    constraintLayout5.visibility = View.VISIBLE
////                                    constraintLayout6.visibility = View.INVISIBLE
////                                } catch (e: Exception) {
////                                    e.printStackTrace()
////                                }
////                                image.visibility = View.VISIBLE
////                            }
////                        }
////                    }
////                    progressBar.visibility = View.GONE
////                }
////            }
////        })
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_add_edit_bank, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
