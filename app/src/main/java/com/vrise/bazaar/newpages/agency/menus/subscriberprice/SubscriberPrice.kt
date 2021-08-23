package com.vrise.bazaar.newpages.agency.menus.subscriberprice

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.openCategoryPopUp
import com.vrise.bazaar.newpages.utilities.HelperMethods.openLanguagePopUp
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import kotlinx.android.synthetic.main.fragment_subscriber_price.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback

//class SubscriberPrice : InitSub() {
//    override fun networkAvailable() {
//        toast(activity, "SubscriberPrice")
//        try {
//            setList()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    /*private var showFilter = true*/
//    private var language = ""
//    private var category = ""
//    var languageName = ""
//    var categoryName = ""
//    private var viewModePrice: SubPriceData? = null
//
//    private val priceObserver = Observer<List<PrdlistItem?>> { t ->
//        if (t != null) {
//            setAdapter(t)
//        } else {
//            progressBar.visibility = View.GONE
//            recyclerView12.layoutManager = LinearLayoutManager(activity)
//            recyclerView12.adapter = EmptyList(activity!!, null)
//        }
//    }
//
//    private fun setAdapter(t: List<PrdlistItem?>) {
//        progressBar.visibility = View.GONE
//        recyclerView12.layoutManager = LinearLayoutManager(activity)
//        recyclerView12.adapter = SubscriberPriceData(activity, t, object : Interfaces.Invoke {
//            override fun invokeMe() {
//                setList()
//            }
//        }, apiService)
//
//    }
//
//    private fun setSelectedFilters() {
//
//        if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.LANGUAGENAME).isNullOrBlank()) {
//            if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.LANGUAGEID).isNullOrBlank()) {
//                language = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.LANGUAGEID)
//                languageName = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.LANGUAGENAME)
//                textView84.text = languageName
//            }
//        }
//
//        if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.CATEGORYNAME).isNullOrBlank()) {
//            if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.CATEGORYID).isNullOrBlank()) {
//                categoryName = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.CATEGORYNAME)
//                category = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.SELECTEDTHING, Constants.CATEGORYID)
//                textView85.text = categoryName
//            }
//        }
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
//        activity!!.page_title.text = "Subscriptions Price"
//
//        setSelectedFilters()
//
//        progressBar.visibility = View.VISIBLE
//        setList()
//
//        textView84.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasConnection(activity)) {
//                    progressBar22.visibility = View.VISIBLE
//                    apiService?.getLanguage(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :                        Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar22.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (response!!.body()!!.data != null) {
//                                    if (response.body()!!.data!!.languagelist != null) {
//                                        openLanguagePopUp(activity, response.body()!!.data!!.languagelist!!, textView84, object : Interfaces.ReturnNamenId {
//                                            override fun returnIdName(name: String, id: String) {
//                                                language = id
//                                                com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Constants.LANGUAGENAME, name, Constants.SELECTEDTHING)
//                                                com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Constants.LANGUAGEID, id, Constants.SELECTEDTHING)
//                                                languageName = name
//                                                setList()
//                                            }
//                                        })
//                                        progressBar22.visibility = View.GONE
//                                    } else {
//                                        progressBar22.visibility = View.GONE
//                                    }
//                                } else {
//                                    progressBar22.visibility = View.GONE
//                                }
//                            }
//                        }
//                    })
//                }
//            }
//        })
//
//        textView85.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (hasConnection(activity)) {
//                    progressBar23.visibility = View.VISIBLE
//                    apiService?.getCategory(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :                        Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar23.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (HelperMethods.hasData(activity!!, response)) {
//                                    if (response!!.body()!!.data != null) {
//                                        if (response.body()!!.data!!.categorylist != null) {
//                                            if (!response.body()!!.data!!.categorylist!!.isEmpty()) {
//                                                openCategoryPopUp(activity!!, response.body()!!.data!!.categorylist!!, textView85, object : Interfaces.ReturnNamenId {
//                                                    override fun returnIdName(name: String, id: String) {
//                                                        category = id
//                                                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Constants.CATEGORYID, id, Constants.SELECTEDTHING)
//                                                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Constants.CATEGORYNAME, name, Constants.SELECTEDTHING)
//                                                        categoryName = name
//                                                        setList()
//                                                    }
//                                                })
//                                                progressBar23.visibility = View.GONE
//                                            } else {
//                                                progressBar23.visibility = View.GONE
//                                            }
//                                        } else {
//                                            progressBar23.visibility = View.GONE
//                                        }
//                                    } else {
//                                        progressBar23.visibility = View.GONE
//                                    }
//                                } else {
//                                    progressBar23.visibility = View.GONE
//                                }
//                            }
//                        }
//                    })
//                }
//            }
//        })
//
//        textView86.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                try {
//                    fragmentManager!!.popBackStack()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        })
//
//        /*floatingActionButton6.setOnClickListener {
//            showFilter = !showFilter
//            if (!showFilter) {
//                textView84.visibility = View.INVISIBLE
//                textView85.visibility = View.INVISIBLE
//            } else {
//                textView84.visibility = View.VISIBLE
//                textView85.visibility = View.VISIBLE
//            }
//        }*/
//
//    }
//
//    private fun setList() {
//        viewModePrice = ViewModelProviders.of(activity!!).get(SubPriceData::class.java)
//        viewModePrice!!.getPrice(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                language = language,
//                category = category,
//                agent_product = "",
//                limit = "",
//                searchkey = "",
//                offset = "",
//                subscriberId = "",
//                subscriber_product = "",
//                sub_product_status = ""
//        )).observe(this, priceObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscriber_price, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
