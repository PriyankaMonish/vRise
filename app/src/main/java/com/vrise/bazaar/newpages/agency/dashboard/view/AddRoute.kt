package com.vrise.bazaar.newpages.agency.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.ID
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.Validator.hasText
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.RootlistItem
import kotlinx.android.synthetic.main.fragment_add_route.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*

//class AddRoute : InitSub() {
//
//    companion object {
//        private var dataItem: RootlistItem? = null
//        private var from = ""
//        private var routeId = ""
//    }
//
//     fun networkAvailable() {
//
//    }
//
//    fun networkUnavailable() {
//
//    }
//
//    override fun initView() {
//        requireActivity().page_tiitle.text = getString(R.string.add_route)
//    }
//
//    override fun initModel() {
//        dataItem = RootlistItem()
//    }
//
//    override fun initControl() {
//        TODO("Not yet implemented")
//    }
//
////    override fun initControl() {
////        getDatas()
////        textView45.setOnClickListener(object : ClickListener() {
////            override fun onOneClick(v: View) {
////                if (fragmentManager != null) {
////                    fragmentManager!!.popBackStack()
////                }
////            }
////        })
////        textView46.setOnClickListener(object : ClickListener() {
////            override fun onOneClick(v: View) {
////                if (hasText(arrayOf(textView43, textView44))) {
////                    if (hasConnection(activity!!)) {
////                        addRoute()
////                    }
////                }
////            }
////        })
////    }
//
////    private fun getDatas() {
////        val bundle = arguments
////        if (bundle != null) {
////            try {
////                activity!!.page_tiitle.text = getString(R.string.edit_route)
////            } catch (e: Exception) {
////                e.printStackTrace()
////            }
////            try {
////                dataItem = bundle.getSerializable(Constants.A_DATAS) as RootlistItem
////                textView43.setText(dataItem?.name.toString())
////                textView44.setText(dataItem?.area.toString())
////                routeId = dataItem?.id.toString()
////                from = bundle.getString(Constants.FROM) ?: ""
////            } catch (e: Exception) {
////                from = ""
////                routeId = ""
////                e.printStackTrace()
////            }
////        } else {
////            try {
////                activity!!.page_tiitle.text = getString(R.string.add_route)
////                routeId = ""
////                from = ""
////            } catch (e: Exception) {
////                e.printStackTrace()
////            }
////        }
////    }
////
////    private fun addRoute() {
////        progressBar.visibility = View.VISIBLE
////
////        println(Request(
////                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, ID),
////                routeName = textView43.text.toString(),
////                routeArea = textView44.text.toString(),
////                routeId = routeId,
////                string = from
////        ))
////        apiService?.addRoute(Request(
////                utoken = Preference.getSinglePreference(activity!!, DATAFILE, ID),
////                routeName = textView43.text.toString(),
////                routeArea = textView44.text.toString(),
////                routeId = routeId,
////                string = from
////        ))?.enqueue(object : ConsAndroidCallback(activity, this@AddRoute, object : Interfaces.Callback {
////            override fun additionalData(display: String?, logout: Boolean) {
////
////            }
////
////            override fun failed(t: Throwable) {
////                progressBar.visibility = View.GONE
////                t.printStackTrace()
////            }
////
////            override fun success(response: Response?, data: Data?, state: Boolean) {
////                if (state) {
////                    progressBar.visibility = View.GONE
////                    fragmentManager!!.popBackStack()
////                } else {
////                    progressBar.visibility = View.GONE
////                }
////            }
////        }) {})
////    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_add_route, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
