package com.vrise.bazaar.ex.shop.pages.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.ReferralViewModel
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.serviceApi
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.ex.util.Values.shareLink
import kotlinx.android.synthetic.main.fragment_referral.*
import kotlinx.android.synthetic.main.item_progress_sheet.*

class ReferralFragment : Fragment() {
    private lateinit var viewModel: ReferralViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_referral, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progress.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(serviceApi(activity), activity))
        ).get(ReferralViewModel::class.java)
        var invitedata = ""

        textView246?.visibility = View.GONE

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getReferral(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        if (!it.reff_msg.isNullOrBlank()) {
                            textView251.text = it.reff_msg.toString()
                            invitedata = it.invite_msg.toString()
                            textView252.text = invitedata
                            textView253.text = it.reff_code
                        }
                    }
                    progress.visibility = View.GONE
                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }

        button21.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "IBR BAZAAR")
            var shareMessage = "1.Click on the link to download IBR Bazaar app.\n"
            val message =
                if (textView253.text.isNotBlank() && textView253.text != "No Referral Code Available") {
                    "2.Use referral code `${textView253.text}` when you register using the app.\n" + if (invitedata.isBlank()) {
                        "3.Download now $shareLink"
                    } else {
                        "3.$invitedata\n4.Download now $shareLink"
                    }
                } else {
                    if (invitedata.isBlank()) {
                        "2.Download now $shareLink"
                    } else {
                        "2.$invitedata\n3.Download now $shareLink"
                    }
                }
            shareMessage += message
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share Viaronline  using"))
        }
    }

}
