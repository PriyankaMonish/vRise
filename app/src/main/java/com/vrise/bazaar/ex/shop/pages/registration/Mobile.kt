package com.vrise.bazaar.ex.shop.pages.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.MobileViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.isPhone
import kotlinx.android.synthetic.main.mobile_fragment.*
import kotlinx.android.synthetic.main.splash_screen_layout.*

class Mobile : Fragment() {

    private lateinit var viewModel: MobileViewModel
    private lateinit var referrerClient: InstallReferrerClient
    var referrerUrl: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mobile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))).get(MobileViewModel::class.java)
        referrerClient = InstallReferrerClient.newBuilder(activity)
            .build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        getReferrerData()

//                        Toast.makeText(activity, referrerUrl, Toast.LENGTH_SHORT).show()
                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        // Connection couldn't be established.
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
        textView361.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (isPhone(activity, editText2.text.toString())) {
                    Instances.InternetCheck { internet ->
                        if (internet) {
                            textView367.visibility = View.VISIBLE
                            viewModel.sendOtp(Request(mobile = editText2.text.toString()))
                                ?.observe(viewLifecycleOwner, Observer {
                                    if (it != null) {
                                        view?.findNavController()?.navigate(
                                            R.id.action_mobile_to_verification_code2, bundleOf(
                                                Constants.OTP to it.data?.otp.toString(),
                                                Constants.NUMBER to editText2.text.toString(),
                                                Constants.URL to referrerUrl,
                                                Constants.TYPE to "0"/* Registration */
                                            )
                                        )
                                    } else {
                                        Instances.toast(container, getString(R.string.no_server))
                                    }
                                    textView367?.visibility = View.GONE
                                })
                        } else {
                            Instances.toast(container, getString(R.string.no_internet))
                        }
                    }
                }
            }
        })
    }

    private fun getReferrerData() {

        val response: ReferrerDetails = referrerClient.installReferrer
        referrerUrl = response.installReferrer
        val referrerClickTime: Long = response.referrerClickTimestampSeconds
        val appInstallTime: Long = response.installBeginTimestampSeconds
        val instantExperienceLaunched: Boolean = response.googlePlayInstantParam

        println(
            "Hardik --> getReferrerData ${ referrerUrl} -- $referrerClickTime -- $appInstallTime" +
                    " -- $instantExperienceLaunched"
        )

        referrerClient.endConnection()
    }

}
