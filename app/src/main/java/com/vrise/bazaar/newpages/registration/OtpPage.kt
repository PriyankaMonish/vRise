package com.vrise.bazaar.newpages.registration

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.GsonBuilder
import com.vrise.R
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.pages.registration.RegistrationFragment
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.ACTIVITY
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.FROM
import com.vrise.bazaar.newpages.utilities.Constants.REGISTRATION
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.HelperMethods.hideKeyboard
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
import com.vrise.bazaar.newpages.utilities.Validator.hasText
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import kotlinx.android.synthetic.main.fragment_otp_pages.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback


/**
 * A placeholder fragment containing a simple view.
 */
//class OtpPage : InitSub(), TextWatcher {
//
//    var SMS_CONSENT_REQUESTs =  4
//    companion object {
//        private var otp: String = "0"
//        private var mobileNumber: String = "0"
//        private var from: String = "0"
//        var device: String? = null
//    }
//
//    override fun networkAvailable() {
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    override fun afterTextChanged(p0: Editable?) {
//
//    }
//
//    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//    }
//
//    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//        when {
//            textView2.hasFocus() -> {
//                if (textView2.text!!.isNotBlank()) {
//                    textView3.requestFocus()
//                }
//                if (textView3.text!!.isNotBlank() && textView4.text!!.isNotBlank() && textView5.text!!.isNotBlank()) {
//                    navigatesTo()
//                }
//            }
//            textView3.hasFocus() -> {
//                if (textView3.text!!.isNotBlank()) {
//                    textView4.requestFocus()
//                }
//                if (textView2.text!!.isNotBlank() && textView4.text!!.isNotBlank() && textView5.text!!.isNotBlank()) {
//                    navigatesTo()
//                }
//            }
//            textView4.hasFocus() -> {
//                if (textView4.text!!.isNotBlank()) {
//                    textView5.requestFocus()
//                }
//                if (textView3.text!!.isNotBlank() && textView2.text!!.isNotBlank() && textView5.text!!.isNotBlank()) {
//                    navigatesTo()
//                }
//            }
//            textView5.hasFocus() -> {
//                if (textView2.text!!.isNotBlank() && textView3.text!!.isNotBlank() && textView4.text!!.isNotBlank()) {
//                    navigatesTo()
//                }
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        try {
//            activity?.registerReceiver(otpListener, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    fun navigatesTo() {
//        hideKeyboard(activity)
//        if (hasConnection(activity)) {
//
//            if (from == 0.toString()) {
//                if (device == "0" || device.isNullOrBlank()) {
//                    activity?.let {
//                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(it) { instanceIdResult ->
//                            device = instanceIdResult.token
//                            HelperMethods.println("newToken2 $device")
//                        }
//                    }
//                }
//                showProgress()
//                if (device.isNullOrBlank()) {
//                    toastit(activity, "Not able to get device id")
//                    try {
//                        fragmentManager?.popBackStack()
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                    removeProgressBar()
//                } else {
//                    if (otp.trim() == "${textView2.text.toString()}${textView3.text.toString()}${textView4.text.toString()}${textView5.text.toString()}".trim()) {
//                        println(
//                            GsonBuilder().setPrettyPrinting().create().toJson(
//                                Request(
//                                    mobile = mobileNumber,
//                                    device_id = device
//                                )
//                            )
//                        )
//                        apiService?.redirectHim(
//                            Request(
//                                mobile = mobileNumber,
//                                device_id = device
//                            )
//                        )?.enqueue(object : Callback<Response> {
//                            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                if (activity != null && isAdded) {
//                                    removeProgressBar()
//                                    t?.printStackTrace()
//                                }
//                            }
//
//                            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                                if (activity != null && isAdded) {
//                                    if (hasData(activity, response)) {
//                                        if (response?.body()?.data != null) {
//                                            if (response.body()?.data?.action != null) {
//
//                                                Preference.putPreference(
//                                                    activity!!,
//                                                    REGISTRATION,
//                                                    mobileNumber,
//                                                    DATAFILE
//                                                )
//
//                                                when (response.body()?.data?.action) {
//                                                    REGISTRATION -> {
//                                                        Handler().postDelayed({
//                                                            navigateTo(
//                                                                activity,
//                                                                ACTIVITY,
//                                                                false,
//                                                                null,
//                                                                RegistrationFragment::class.java,
//                                                                null,
//                                                                arrayOf(Intent.FLAG_ACTIVITY_CLEAR_TOP),
//                                                                ""
//                                                            )
//                                                        }, 0)
//                                                    }
//
//                                                    else -> {
//                                                        val bundle = Bundle()
//                                                        bundle.putString(
//                                                            Constants.ID,
//                                                            response.body()?.data?.userToken.toString()
//                                                        )
//
//                                                        val bannerList: ArrayList<String> = ArrayList()
//                                                        if (response.body()?.data?.banner != null) {
//                                                            if (response.body()?.data?.banner!!.isNotEmpty()) {
//                                                                for (i in 0 until response.body()!!.data!!.banner!!.size) {
//                                                                    bannerList.add(response.body()?.data?.banner!![i].image.toString())
//                                                                }
//                                                            }
//                                                        }
//                                                        bundle.putStringArrayList("bannerList", bannerList)
//
//                                                        Handler().postDelayed({
//                                                            HelperMethods.navigateTo(
//                                                                activity,
//                                                                Constants.ACTIVITY,
//                                                                false,
//                                                                null,
//                                                                DashBoardContainer::class.java,
//                                                                bundle,
//                                                                arrayOf(Intent.FLAG_ACTIVITY_CLEAR_TOP),
//                                                                ""
//                                                            )
//                                                        }, 0)
//                                                    }
//                                                }
//                                                removeProgressBar()
//                                            } else {
//                                                removeProgressBar()
//
//                                            }
//                                        }
//                                    } else {
//                                        removeProgressBar()
//                                    }
//                                }
//                            }
//                        })
//                    } else {
//                        removeProgressBar()
//                        toastit(activity, "Otp Mismatch")
//                    }
//                }
//
//            } else if (from == 1.toString()) {
//                showProgress()
//                if (otp.trim() == "${textView2.text.toString()}${textView3.text.toString()}${textView4.text.toString()}${textView5.text.toString()}".trim()) {
//                    apiService?.mobileupdate(
//                        Request(
//                            utoken = Preference.getSinglePreference(
//                                activity!!,
//                                DATAFILE,
//                                Constants.ID
//                            ),
//                            mobile = mobileNumber
//                        )
//                    )?.enqueue(object : ConsAndroidCallback(activity, this@OtpPage, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//                                HelperMethods.logoutfromapp(activity)
//                            } else {
//                                removeProgressBar()
//                            }
//                        }
//
//                        override fun failed(t: Throwable) {
//                            removeProgressBar()
//                            t.printStackTrace()
//                        }
//                    }) {})
//                } else {
//                    removeProgressBar()
//                    toastit(activity, "Otp Mismatch")
//                }
//            }
//        }
//    }
//
//    private fun removeProgressBar() {
//        if (activity != null && isAdded)
//            if (progressBar != null) {
//                progressBar.visibility = View.GONE
//            }
//    }
//
//    private fun showProgress() {
//        if (activity != null && isAdded)
//            if (progressBar != null) {
//                progressBar.visibility = View.VISIBLE
//            }
//    }
//
//    private fun setAllVisible() {
//        if (activity != null && isAdded) {
//            textView2?.visibility = View.VISIBLE
//            textView3?.visibility = View.VISIBLE
//            textView4?.visibility = View.VISIBLE
//            textView5?.visibility = View.VISIBLE
//        }
//    }
//
//    private fun setAllInvisible() {
//        if (activity != null && isAdded) {
//            textView2?.visibility = View.INVISIBLE
//            textView3?.visibility = View.INVISIBLE
//            textView4?.visibility = View.INVISIBLE
//            textView5?.visibility = View.INVISIBLE
//        }
//    }
//
//    val otpListener = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
//                val extras = intent.extras
//                val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
//
//                when (status.statusCode) {
//                    CommonStatusCodes.SUCCESS -> {
//                        val consentIntent =
//                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
//
//                        try {
//                            startActivityForResult(consentIntent, SMS_CONSENT_REQUESTs)
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                    CommonStatusCodes.TIMEOUT -> {
//                        Instances.toast(activity, activity?.getString(R.string.cannot_read_sms))
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            SMS_CONSENT_REQUESTs ->
//                if (resultCode == Activity.RESULT_OK && data != null) {
//                    val sms = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
//                    Instances.printAny("otp $sms")
//                    try {
//                        if (sms.substring(0, 19) == "[#] IBR Bazaar OTP:" || sms.substring(0, 19) == "<#> IBR Bazaar OTP:") {
//                            if (textView2 != null) textView2?.setText(sms.substring(19, 23).substring(0, 1))
//                            if (textView3 != null) textView3?.setText(sms.substring(19, 23).substring(1, 2))
//                            if (textView4 != null) textView4?.setText(sms.substring(19, 23).substring(2, 3))
//                            if (textView5 != null) textView5?.setText(sms.substring(19, 23).substring(3, 4))
//                            navigatesTo()
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//        }
//    }
//
//    override fun onDestroy() {
//        try {
//            activity?.unregisterReceiver(otpListener)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        super.onDestroy()
//    }
//
//    private fun getBundleData() {
//        val bundle = arguments
//        if (bundle != null) {
//            otp = bundle.getString(Constants.OTP, "")
//            mobileNumber = bundle.getString(Constants.NUMBER, "")
//            from = bundle.getString(FROM, "0")
//        }
//    }
//
//    private fun resendOtp() {
//        if (hasConnection(activity)) {
//
//            if (textView2 != null) textView2?.setText("")
//            if (textView3 != null) textView3?.setText("")
//            if (textView4 != null) textView4?.setText("")
//            if (textView5 != null) textView5?.setText("")
//            if (textView2 != null) textView2?.requestFocus()
//
//            showResendProgress()
//
//            apiService?.mobileValidate(Request(mobile = mobileNumber, otp = otp))
//                ?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                    override fun additionalData(display: String?, logout: Boolean) {
//
//                    }
//
//                    override fun failed(t: Throwable) {
//                        removeResendProgressz()
//                        setAllVisible()
//                        t.printStackTrace()
//                    }
//
//                    override fun success(response: Response?, data: Data?, state: Boolean) {
//                        if (state) {
//                            if (data != null) {
//                                if (data.otp != null) {
//                                    otp = data.otp.toString()
//                                }
//                            }
//                            removeResendProgressz()
//                        } else {
//                            removeResendProgressz()
//                        }
//                        setAllVisible()
//                    }
//                }) {})
//
//            sendTicka()
//        } else {
//            setAllVisible()
//        }
//
//    }
//
//    fun sendTicka() {
//        try {
//            textView7.visibility = View.INVISIBLE
//            textView171.visibility = View.VISIBLE
//            object : CountDownTimer(30000, 1000) {
//                override fun onTick(millisUntilFinished: Long) {
//                    if (activity != null && isAdded) {
//                        if (textView171 != null) {
//                            textView171.text = "Resend otp after ${millisUntilFinished / 1000} seconds"
//                        }
//                    }
//                }
//
//                override fun onFinish() {
//                    if (activity != null && isAdded) {
//                        if (textView171 != null && textView7 != null) {
//                            textView171.visibility = View.INVISIBLE
//                        }
//                        textView7.visibility = View.VISIBLE
//                    }
//                }
//            }.start()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun removeResendProgressz() {
//        if (activity != null && isAdded) {
//            if (progressBar2 != null) {
//                progressBar2.visibility = View.GONE
//                progressBar2.stopAnimation()
//            }
//        }
//    }
//
//    private fun validateUi(): Boolean {
//        return hasText(arrayOf(textView2, textView3, textView4, textView5), "0")
//    }
//
//    private fun showResendProgress() {
//        if (activity != null && isAdded) {
//            if (progressBar2 != null) {
//                progressBar2.setBackgroundAsTile(R.drawable.progressbarbutton2)
//                progressBar2.visibility = View.VISIBLE
//                progressBar2.startAnimation()
//            }
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_otp_pages, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (activity != null && isAdded) {
//            val retriever = SmsRetriever.getClient(activity!!).startSmsUserConsent(null)
//            retriever.addOnSuccessListener {
//                activity?.registerReceiver(otpListener, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
//            }
//
//            retriever.addOnFailureListener {
//            }
//
//        }
//        setInitializer()
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initView() {
//
//    }
//
//    override fun initControl() {
//        activity?.page_title?.text = getString(R.string.caps_otp)
//
//        getBundleData()
//
//        if (from == 0.toString()) {
//            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                device = instanceIdResult.token
//                HelperMethods.println("newToken1 $device")
//            }
//        }
//
//        removeProgressBar()
//
//        textView5.addTextChangedListener(this)
//        textView4.addTextChangedListener(this)
//        textView3.addTextChangedListener(this)
//        textView2.addTextChangedListener(this)
//
//        textView8.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                try {
//                    if (activity != null && isAdded) {
//                        if (validateUi()) {
//                            navigatesTo()
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        })
//        textView7.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                setAllInvisible()
//                activity?.let {
//                    if (hasConnection(it)) {
//                        if (isAdded) {
//                            if (it.isFinishing) {
//                            } else {
//                                resendOtp()
//                            }
//                        }
//                    } else {
//                        setAllVisible()
//                    }
//                }
//            }
//        })
//        sendTicka()
//    }
//
//}
