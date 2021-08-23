package com.vrise.bazaar.newpages.agency.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.vrise.R
import com.vrise.bazaar.ex.shop.containers.LandingScreen
import com.vrise.bazaar.newpages.utilities.ClickListener
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.InitSub
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class Settings : InitSub() {
//    override fun networkAvailable() {
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
//        activity!!.page_title.text = "Settings"
//        textView70.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                navigateTo(activity!!, FRAGMENT, true, AddOrEditBank(), null, null, null, "")
//            }
//        })
//        /*textView71.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                navigateTo(activity!!, FRAGMENT, true, AgentProfile(), null, null, null, "")
//            }
//        })*/
//        /*textView72.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                navigateTo(activity!!, FRAGMENT, true, SubscriberPrice(), null, null, null, "")
//            }
//        })*/
//        textView73.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (activity != null && isAdded && !activity!!.isFinishing)
//                    AlertDialog.Builder(activity!!).setTitle("Logout").setMessage("Do you want to logout?").setPositiveButton("Yes") { p0, _ ->
//                        p0.dismiss()
//                        com.vrise.bazaar.newpages.utilities.Preference.ClearSharedPreference(activity, Constants.DATAFILE)
//                        navigateTo(activity!!, Constants.ACTIVITY, false, null, LandingScreen::class.java, null, null, "")
//                        activity!!.finish()
//                    }.setNegativeButton("No") { p0, _ -> p0.dismiss() }.show()
//            }
//        })
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_settings, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
