package com.vrise.bazaar.ex.shop.pages.registration

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings.Secure
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.firebase.iid.FirebaseInstanceId
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OtpViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Constants.BANNERS
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.hideKeyboard
import com.vrise.bazaar.ex.util.Instances.logoutFromApp
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.fragment_otp.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import java.util.concurrent.TimeUnit


class VerificationCode : Fragment() {

    lateinit var viewModel: OtpViewModel
    private val SMS_CONSENT_REQUEST = 2

    companion object {
        var otpCode = ""
        var mobileNumber = ""
        var toDoStatus = ""
        var tocken: String = ""
        var url: String? = null
    }

    private var broadcastListener = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
                val extras = intent.extras
                val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (status.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val consentIntent =
                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)

                        try {
                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        toast(activity, activity?.getString(R.string.cannot_read_sms))
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SMS_CONSENT_REQUEST ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val otpCode = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    Instances.printAny("otp $otpCode")
                    if (otpCode?.substring(0, 19) == "[#] IBR Bazaar OTP:" || otpCode?.substring(
                            0,
                            19
                        ) == "<#> IBR Bazaar OTP:"
                    ) {
                        editText3?.setText(otpCode?.substring(19, 23))
                    }
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress.visibility = View.GONE

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(OtpViewModel::class.java)

        /*Start SMS Listener*/
        activity?.let {
            val client = SmsRetriever.getClient(it).startSmsUserConsent(null)
            client.addOnSuccessListener {
                activity?.registerReceiver(
                    broadcastListener, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
                )
            }

            client.addOnFailureListener {
                toast(activity, activity?.getString(R.string.cannot_read_sms))
            }
        }
        /*Get Firebase*/
        getToken()
        /*Start Resend Countdown*/
        startCount()
        /*Bundle Values*/
        otpCode = arguments?.getString(Constants.OTP) ?: ""
        mobileNumber = arguments?.getString(Constants.NUMBER) ?: ""
        toDoStatus = arguments?.getString(Constants.TYPE) ?: ""
        url = arguments?.getString(Constants.URL) ?: ""


        textView107.visibility = View.GONE
        textView107.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                Instances.InternetCheck { internet ->
                    if (internet) {
                        viewModel.resendOtp(Request(mobile = mobileNumber))
                            ?.observe(viewLifecycleOwner, Observer {
                                if (it != null) {
                                    otpCode = it.data?.otp ?: otpCode
                                    startCount()
                                } else {
                                    toast(activity, getString(R.string.no_server))
                                }
                            })
                    } else {
                        toast(activity, getString(R.string.no_internet))
                    }
                }
            }
        })
        editText3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length >= 4) {
                    openHome(s.toString())
                }
            }
        })

    }

    @SuppressLint("HardwareIds")
    private fun getToken() {
        activity?.let {
            if (tocken.isNullOrBlank() || tocken == "") {
FirebaseInstanceId.getInstance()
                    .instanceId.addOnSuccessListener(it) { instanceIdResult ->
                        tocken = instanceIdResult.token
                        Log.e("fcm tocken", tocken)
                        Instances.printAny("newToken $tocken")
                    }
            }
        }
    }

    private fun openHome(s: String) {
        hideKeyboard(activity)
        progress.visibility = View.VISIBLE
        hideKeyboard(activity)
        textView368.visibility = View.VISIBLE
        if (otpCode == s) {
            //To Registration Fragment
            if (toDoStatus == "0") {
                if (tocken.isNullOrBlank() || tocken == "0") {
                    getToken()
                }
                Instances.InternetCheck { internet ->
                    if (internet) {
                        viewModel.redirectMe(Request(mobile = mobileNumber, device_id = tocken))
                            ?.observe(viewLifecycleOwner, Observer {
                                if (it != null) {
                                    if (!it.data?.action.isNullOrBlank()) {
                                        Preference.put(
                                            activity, Preference.NUMBER, mobileNumber, DATAFILE
                                        )

                                        Preference.put(
                                            activity, Preference.URLLINK, url!!, DATAFILE
                                        )
                                        if (it.data?.action == "registration") {
                                            view?.findNavController()
                                                ?.navigate(
                                                    R.id.action_verification_code_to_registration,
                                                    bundleOf(
                                                        Constants.idlink to url
                                                    )
                                                )
                                        } else {
                                            Preference.put(
                                                activity,
                                                TOKEN,
                                                it.data?.userToken.toString(),
                                                DATAFILE
                                            )
                                            val bundle = Bundle()
                                            bundle.putStringArrayList(BANNERS,
                                                object : ArrayList<String>() {
                                                    init {
                                                        if (!it.data?.banner.isNullOrEmpty()) {
                                                            for (i in 0 until (it.data?.banner?.size
                                                                ?: 0)) {
                                                                add(it.data?.banner!![i].image.toString())
                                                            }
                                                        }
                                                    }
                                                })
                                            val container =
                                                Intent(activity, DashBoardContainer::class.java)
                                            container.putExtras(bundle)
                                            Handler().postDelayed({ startActivity(container) }, 0)
                                            activity?.finish()
                                        }
                                    }
                                }
                                progress.visibility = View.GONE
                            })
                    } else {
                        toast(activity, getString(R.string.no_internet))
                        progress.visibility = View.GONE
                    }
                }
            } else if (toDoStatus == "1") {
                Instances.InternetCheck { internet ->
                    if (internet) {
                        viewModel.updateNo(
                            Request(
                                utoken = Preference.get(
                                    activity, DATAFILE, TOKEN
                                ), mobile = mobileNumber
                            )
                        )?.observe(viewLifecycleOwner, Observer {
                            if (it != null) {
                                logoutFromApp("Use new phone number to login", true, activity)
                            } else {
                                toast(activity, getString(R.string.no_server))
                            }
                        })
                    } else {
                        toast(activity, getString(R.string.no_internet))
                    }
                }
            }
        } else {
            progress.visibility = View.GONE
            toast(activity, "OTP mismatch")
        }
        textView368.visibility = View.GONE
    }

    private fun startCount() {
        textView107.visibility = View.GONE
        editText3?.setText("")
        textView107?.isEnabled = false
        textView107?.isClickable = false
        textView107?.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                android.R.color.darker_gray
            )
        )
        object : CountDownTimer(50000, 1000) {
            override fun onFinish() {
                try {
                    textView107?.isEnabled = true
                    textView107?.isClickable = true
                    textView107?.setTextColor(
                        ContextCompat.getColor(
                            activity!!, android.R.color.holo_blue_light
                        )
                    )
                    textView107?.visibility = View.VISIBLE
                    textView108?.text = "Resend OTP"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                try {
                    textView108?.text =
                        "Resend OTP in ${TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)} seconds"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(broadcastListener)
    }

    override fun onResume() {
        super.onResume()
        try {
            activity?.registerReceiver(
                broadcastListener, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}