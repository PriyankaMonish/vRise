package com.vrise.bazaar.newpages.agency.menus.todo
//
//import android.app.Dialog
//import android.os.Bundle
//import com.google.android.material.snackbar.Snackbar
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.Window
//import android.widget.TextView
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import kotlinx.android.synthetic.main.fragment_billing.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback
//
//
//class Billing : InitSub() {
//
//    override fun networkAvailable() {
//        billType = ""
//        date = ""
//        CallApi("view", date, billType, "")
//    }
//
//    override fun networkUnavailable() {
//        progressBar16.visibility = View.VISIBLE
//    }
//
//    var utocken = ""
//    var billId = ""
//    var autoStatu = "no"
//    var autoSms = "no"
//    var smsUsers = "0"
//    var firstTime = true
//    var billType = "month"
//    var date = ""
//    var clicked = 0
//    override fun initView() {
//        billing_layout.visibility = View.GONE
//        progressBar16.visibility = View.VISIBLE
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        utocken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)
//        activity!!.page_title.text = "Billing"
//
//        billing_layout.visibility = View.GONE
//        progressBar16.visibility = View.VISIBLE
//
//        month_end.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                billType = "month"
//                date = ""
//                CallApi("", date, billType, "")
//            }
//        })
//        month_end.setOnCheckedChangeListener { _, p1 -> month_end.isClickable = !p1 }
//
//        radioButton3.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                billType = "month"
//                date = ""
//                CallApi("", date, billType, "")
//            }
//        })
//        radioButton3.setOnCheckedChangeListener { _, p1 -> radioButton3.isClickable = !p1 }
//
//        selected_date.setOnClickListener {
//            openDaialog(textView72.text.toString())
//        }
//        selected_date.setOnCheckedChangeListener { _, p1 -> selected_date.isClickable = !p1 }
//
//        radioButton5.setOnClickListener {
//            openDaialog(editText.text.toString())
//        }
//        radioButton5.setOnCheckedChangeListener { _, p1 -> radioButton5.isClickable = !p1 }
//
//        switch1.setOnClickListener {
//            CallApi("", date, billType, "notify")
//        }
//
//        textView187.setOnClickListener {
//            CallApi("", date, billType, "sms")
//        }
//
//        textView72.setOnClickListener {
//            openDaialog(textView72.text.toString())
//        }
//
//        editText.setOnClickListener {
//            openDaialog(editText.text.toString())
//        }
//
//        radioButton.setOnClickListener {
//            smsUsers = "1"
//            CallApi("", date, billType, "")
//        }
//
//        radioButton2.setOnClickListener {
//            smsUsers = "0"
//            CallApi("", date, billType, "")
//        }
//    }
//
//    /*
//    private fun setStatus(status: String) {
//        println("utocken $utocken, status $status")
//        apiService?.setAutoBill(Request(
//                utoken = utocken,
//                autobillStatus = status
//        ))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    switch1.isChecked = !switch1.isChecked
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (hasData(response)) {
//                        if (response!!.body()!!.message.equals(SUCCESS)) {
//                            if (autoStatu == "no") {
//                                autoStatu = "yes"
//                                switch1.isChecked = true
//                            } else {
//                                autoStatu = "no"
//                                switch1.isChecked = false
//                            }
//                            CallApi("", "", "")
//                        } else if (response.body()!!.message.equals(FAILED)) {
//                            switch1.isChecked = !switch1.isChecked
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun setSmsStatus(s: String) {
//        if (hasConnection(activity)) {
//            println("utocken $utocken, autosms_status $s")
//            apiService?.autosmsstatus(Request(
//                    utoken = utocken,
//                    autosms_status = s
//            ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                override fun failed(t: Throwable) {
//                    textView187.isChecked = !textView187.isChecked
//                    t.printStackTrace()
//                }
//
//                override fun success(response: Response?, data: Data?, state: Boolean) {
//                    if (state) {
//                        if (autoSms == "no") {
//                            autoSms = "yes"
//                            textView187.isChecked = true
//                        } else {
//                            autoSms = "no"
//                            textView187.isChecked = false
//                        }
//                        CallApi("", "", "")
//                    } else {
//                        textView187.isChecked = !textView187.isChecked
//                    }
//                }
//            }) {})
//        }
//    }*/
//
//    fun openDaialog(dayte: String) {
//        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.new_calender)
//
//        val checkBox3 = dialog.findViewById<TextView>(R.id.checkBox3)
//        val checkBox4 = dialog.findViewById<TextView>(R.id.checkBox4)
//        val checkBox5 = dialog.findViewById<TextView>(R.id.checkBox5)
//        val checkBox6 = dialog.findViewById<TextView>(R.id.checkBox6)
//        val checkBox7 = dialog.findViewById<TextView>(R.id.checkBox7)
//
//        val textView72 = dialog.findViewById<TextView>(R.id.textView72)
//        val textView13 = dialog.findViewById<TextView>(R.id.textView13)
//        val textView14 = dialog.findViewById<TextView>(R.id.textView14)
//
//        if (dayte.isNotBlank()) {
//            if (dayte != "Month end") {
//                try {
//                    textView72.text = dayte
//                    when (dayte) {
//                        "1" -> {
//                            changeBackground(checkBox3, checkBox4, checkBox5, checkBox6, checkBox7)
//                        }
//                        "2" -> {
//                            changeBackground(checkBox4, checkBox3, checkBox5, checkBox6, checkBox7)
//                        }
//                        "3" -> {
//                            changeBackground(checkBox5, checkBox4, checkBox3, checkBox6, checkBox7)
//                        }
//                        "4" -> {
//                            changeBackground(checkBox7, checkBox4, checkBox5, checkBox3, checkBox6)
//                        }
//                        "5" -> {
//                            changeBackground(checkBox6, checkBox4, checkBox5, checkBox7, checkBox3)
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        checkBox3.setOnClickListener {
//            changeBackground(checkBox3, checkBox4, checkBox5, checkBox6, checkBox7)
//            changeText(checkBox3.text.toString(), textView72)
//        }
//        checkBox4.setOnClickListener {
//            changeBackground(checkBox4, checkBox3, checkBox5, checkBox6, checkBox7)
//            changeText(checkBox4.text.toString(), textView72)
//        }
//        checkBox5.setOnClickListener {
//            changeBackground(checkBox5, checkBox4, checkBox3, checkBox6, checkBox7)
//            changeText(checkBox5.text.toString(), textView72)
//        }
//        checkBox6.setOnClickListener {
//            changeBackground(checkBox6, checkBox4, checkBox5, checkBox3, checkBox7)
//            changeText(checkBox6.text.toString(), textView72)
//        }
//        checkBox7.setOnClickListener {
//            changeBackground(checkBox7, checkBox4, checkBox5, checkBox6, checkBox3)
//            changeText(checkBox7.text.toString(), textView72)
//        }
//
//
//        textView13.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (textView72.text.isNotBlank()) {
//                    dialog.dismiss()
//                    billType = "day"
//                    date = textView72.text.toString()
//                    CallApi("", date, billType, "")
//                }
//            }
//        })
//
//        textView14.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                billType = ""
//                date = ""
//                CallApi("view", date, billType, "")
//                dialog.dismiss()
//            }
//        })
//        dialog.show()
//    }
//
//    private fun changeBackground(checkBox: TextView?, checkBox1: TextView, checkBox2: TextView, checkBox3: TextView, checkBox4: TextView) {
//        checkBox!!.setBackgroundResource(R.drawable.round_afd8)
//        checkBox3.setBackgroundResource(R.drawable.round_grey)
//        checkBox4.setBackgroundResource(R.drawable.round_grey)
//        checkBox1.setBackgroundResource(R.drawable.round_grey)
//        checkBox2.setBackgroundResource(R.drawable.round_grey)
//    }
//
//    private fun changeText(toString: String, textView72: TextView) {
//        textView72.text = toString
//    }
//
//    private fun CallApi(status: String, dates: String, bill_type: String, statuscl: String) {
//
//        progressBar16.visibility = View.VISIBLE
//
//        val stat = if (status == "view") status else "update"
//        val smsUser = if (smsUsers == "0") "napp_user" else "all_user"
//
//        val billStatus = if (statuscl == "notify") {
//            if (autoStatu == "yes") "no" else "yes"
//        } else {
//            autoStatu
//        }
//
//        val smsStatus = if (statuscl == "sms") {
//            if (autoSms == "yes") "no" else "yes"
//        } else {
//            autoSms
//        }
//
//        val billTypes = if (firstTime) "month" else bill_type
//
//        println(GsonBuilder().setPrettyPrinting().create().toJson(Request(
//                utoken = utocken,
//                string = stat,
//                billType = billTypes,
//                date = dates,
//                smsUsers = smsUser,
//                autosms_status = smsStatus,
//                autobillStatus = billStatus
//        )))
//
//        apiService?.getBillSett(Request(
//                utoken = utocken,
//                string = stat,
//                billType = billTypes,
//                date = dates,
//                smsUsers = smsUser,
//                autosms_status = smsStatus,
//                autobillStatus = billStatus
//        ))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//
//                    billing_layout.visibility = View.VISIBLE
//                    progressBar16.visibility = View.GONE
//
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//
//                            if (!response.body()!!.data!!.autobillStatus.isNullOrBlank()) {
//                                if (response.body()!!.data!!.autobillStatus == "1") {
//                                    autoStatu = "yes"
//                                    switch1.isChecked = true
//                                } else {
//                                    autoStatu = "no"
//                                    switch1.isChecked = false
//                                }
//                            }
//
//                            if (!response.body()!!.data!!.autoSmsStatus.isNullOrBlank()) {
//                                if (response.body()!!.data!!.autoSmsStatus == "1") {
//                                    autoSms = "yes"
//                                    textView187.isChecked = true
//                                } else {
//                                    autoSms = "no"
//                                    textView187.isChecked = false
//                                }
//                            }
//
//                            if (response.body()!!.data!!.autobillData != null) {
//                                firstTime = false
//                                billId = response.body()!!.data!!.autobillData!!.id!!
//                                if (response.body()!!.data!!.autobillData!!.billType == "month") {
//
//                                    month_end.isChecked = true
//                                    month_end.isClickable = false
//                                    radioButton3.isChecked = true
//                                    radioButton3.isClickable = false
//
//                                    selected_date.isChecked = false
//                                    selected_date.isClickable = true
//                                    radioButton5.isChecked = false
//                                    radioButton5.isClickable = true
//
//                                    textView72.text = "Month end"
//                                    textView72.isClickable = false
//                                    editText.text = "Month end"
//                                    editText.isClickable = false
//
//                                    billType = "month"
//                                    date = ""
//
//                                } else {
//
//                                    month_end.isChecked = false
//                                    month_end.isClickable = true
//                                    radioButton3.isChecked = false
//                                    radioButton3.isClickable = true
//
//                                    selected_date.isChecked = true
//                                    selected_date.isClickable = false
//                                    radioButton5.isChecked = true
//                                    radioButton5.isClickable = false
//
//                                    textView72.text = response.body()!!.data!!.autobillData!!.date.toString()
//                                    textView72.isClickable = true
//                                    editText.text = response.body()!!.data!!.autobillData!!.date.toString()
//                                    editText.isClickable = true
//
//                                    billType = "day"
//                                    date = response.body()!!.data!!.autobillData!!.date.toString()
//
//                                }
//
//                                if (response.body()!!.data!!.autobillData!!.smsUsers == "all_user") {
//                                    radioButton.isChecked = true
//                                    smsUsers = "1"
//                                } else {
//                                    smsUsers = "0"
//                                    radioButton2.isChecked = true
//                                }
//
//                            } else {
//                                billType = ""
//                                date = ""
//                                CallApi("view", date, billType, "")
//                                firstTime = true
//                            }
//
//                            if (response.body()!!.data!!.autobillStatus != null) {
//                                if (response.body()!!.data?.pendingSms?.toDouble()!! >= 0.0 && response.body()!!.data!!.autobillStatus == "1" && response.body()!!.data!!.smsCredit.toDouble() >= response.body()!!.data?.pendingSms?.toDouble()!!) {
//                                    linearLayout5.visibility = View.VISIBLE
//                                } else {
//                                    linearLayout5.visibility = View.GONE
//                                }
//
//                                if (response.body()!!.data!!.autobillStatus == "1" && response.body()!!.data!!.smsCredit.toDouble() < response.body()!!.data?.pendingSms?.toDouble()!!) {
//                                    val snack = Snackbar.make(linearLayout5, "Please check your SMS credit", Snackbar.LENGTH_LONG)
//                                    snack.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 4
//                                    snack.show()
//
//                                }
//                            }
//                        }
//                    }
//                    billing_layout.visibility = View.VISIBLE
//                    progressBar16.visibility = View.GONE
//                }
//            }
//        })
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_billing, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
