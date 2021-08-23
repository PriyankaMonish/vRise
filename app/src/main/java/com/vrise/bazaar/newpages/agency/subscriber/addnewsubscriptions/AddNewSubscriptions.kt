package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions

//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import android.os.Bundle
//import androidx.annotation.NonNull
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.menus.subscriberprice.SubPriceData
//import com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails.SubscriberDetails
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.CATEGORYID
//import com.vrise.bazaar.newpages.utilities.Constants.CATEGORYNAME
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.LANGUAGEID
//import com.vrise.bazaar.newpages.utilities.Constants.LANGUAGENAME
//import com.vrise.bazaar.newpages.utilities.Constants.SELECTEDTHING
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openCategoryPopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openLanguagePopUp
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import kotlinx.android.synthetic.main.new_subscriptions_add.*
//import kotlinx.android.synthetic.main.save_cancel.*
//import retrofit2.Call
//import retrofit2.Callback
//
//
//class AddNewSubscriptions : InitSub() {
//    override fun networkAvailable() {
//        //Todoo No Network What to Do
//    }
//
//    override fun networkUnavailable() {
//        //Todoo No Network What to Do
//    }
//
//    private var language = ""
//    private var category = ""
//    private var subscribers = ""
//    var behavior: BottomSheetBehavior<*>? = null
//    private var filter = false
//    private var adaptre: SubscriptionList? = null
//    private var adapterItemList: ItemList? = null
//
//    private var viewModel: SubPriceData? = null
//    private var subscribeditem: Subdata? = null
//
//    private var selectedlanguage: String? = null
//    private var selectedcategory: String? = null
//
//    var languagelist: ArrayList<LanguagelistItem>? = null
//    var categorylist: ArrayList<CategorylistItem>? = null
//    var languageName = ""
//    var categoryName = ""
//    private val itemObserver = Observer<List<PrdlistItem?>> { t ->
//        if (t != null) {
//            setItemAdapter(t.toMutableList())
//        } else {
//            recyclerView14.layoutManager = LinearLayoutManager(activity)
//            recyclerView14.adapter = EmptyList(activity!!, null)
//        }
//    }
//
//    private fun setSubscribedAdapter(t: ArrayList<SubscriptionsItem>) {
//        recyclerView9.layoutManager = LinearLayoutManager(activity, 0, false)
//        adaptre = SubscriptionList(activity!!, t, null, object : Interfaces.ReturnSubscriptionsItem {
//            override fun removeData(clickPosvalue: SubscriptionsItem) {
//                /*if (adapterItemList != null) {
//                    adapterItemList!!.removeCheck(clickPosvalue)
//                }
//                if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                    behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                } else {
//                    behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }*/
//            }
//        }, object : Interfaces.ReturnPrdlistItem{
//            override fun returnData(clickPosvalue: PrdlistItem?) {
//                //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun removeData(clickPosvalue: PrdlistItem?) {
//                //To change body of created functions use File | Settings | File Templates.
//            }
//        })
//        recyclerView9.adapter = adaptre
//    }
//
//    private fun setItemAdapter(t: MutableList<PrdlistItem?>?) {
//        recyclerView14.layoutManager = LinearLayoutManager(activity!!)
//        adapterItemList = ItemList(activity, t, object : Interfaces.ReturnPrdlistItem {
//
//            override fun returnData(clickPosvalue: PrdlistItem?) {
//
//                if (subscribeditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue!!.name, id = clickPosvalue.id))) {
////                    toast(activity!!, "${clickPosvalue.name} Already in List")
//                } else {
//                    subscribeditem!!.subscriptions!!.add(SubscriptionsItem(
//                            name = clickPosvalue.name,
//                            id = clickPosvalue.id
//                    ))
//
//                    println(GsonBuilder().setPrettyPrinting().create().toJson(subscribeditem!!.subscriptions))
//
//                    if (adaptre != null) {
//                        adaptre!!.notifyItemChanged(adaptre!!.itemCount)
//                        adaptre!!.notifyDataSetChanged()
//                        recyclerView9.layoutManager!!.scrollToPosition(adaptre!!.itemCount - 1)
//                    }
//                }
//
//                if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                    behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                } else {
//                    behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//
//            }
//
//            override fun removeData(clickPosvalue: PrdlistItem?) {
//                if (subscribeditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue!!.name, id = clickPosvalue.id))) {
//                    subscribeditem!!.subscriptions!!.remove(SubscriptionsItem(
//                            name = clickPosvalue.name,
//                            id = clickPosvalue.id
//                    ))
//                    if (adaptre != null) {
//                        adaptre!!.notifyDataSetChanged()
//                    }
//                } else {
////                    toastit(activity!!, "${clickPosvalue.name} Not in list")
//                }
//
//
//                if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                    behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                } else {
//                    behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//            }
//
//        }, subscribeditem)
//        recyclerView14.adapter = adapterItemList
//
//        setSelectedFilters()
//    }
//
//    override fun initView() {
//        language = ""
//        languageName = ""
//        selectedlanguage = ""
//        category = ""
//        categoryName = ""
//        selectedcategory = ""
//    }
//
//    override fun initModel() {
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Add New Subscriptions"
//
//
//        behavior = BottomSheetBehavior.from(linearLayout5)
//        behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//        behavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
//                // React to state change
//            }
//
//            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
//                // React to dragging events
//            }
//        })
//
//        subscribeditem = Subdata(subscriptions = ArrayList())
//        languagelist = ArrayList()
//        categorylist = ArrayList()
//        getLanguage()
//        getCategories()
//        getBundleData()
//        setSelectableList()
//
//        textView61.visibility = View.VISIBLE
//        textView62.visibility = View.VISIBLE
//
//        textView61.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                openLanguagePopUp(activity, languagelist, textView61, object : Interfaces.ReturnNamenId {
//                    override fun returnIdName(name: String, id: String) {
//                        languageName = name
//                        language = id
//                        selectedlanguage = language
//                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, LANGUAGENAME, languageName, SELECTEDTHING)
//                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, LANGUAGEID, selectedlanguage, SELECTEDTHING)
//                        if (adapterItemList != null) {
//                            adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                        }
//                    }
//                })
//            }
//        })
//        textView62.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                openCategoryPopUp(activity!!, categorylist, textView62, object : Interfaces.ReturnNamenId {
//                    override fun returnIdName(name: String, id: String) {
//                        categoryName = name
//                        category = id
//                        selectedcategory = category
//                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, CATEGORYNAME, categoryName, SELECTEDTHING)
//                        com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, CATEGORYID, selectedcategory, SELECTEDTHING)
//                        if (adapterItemList != null) {
//                            adapterItemList!!.updateList(selectedlanguage, selectedcategory)
//                        }
//                    }
//                })
//            }
//        })
//
//        textView64.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (subscribeditem != null) {
//                    if (subscribeditem!!.subscriptions != null) {
//                        if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                            apiService?.SubProductAdd(Request(
//                                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                    subscriberId = subscribers,
//                                    productList = subscribeditem!!.subscriptions
//                            ))?.enqueue(object : Callback<Response> {
//                                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                    if (activity != null && isAdded) {
//                                        t!!.printStackTrace()
//                                    }
//                                }
//
//                                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                                    if (activity != null && isAdded) {
//                                        if (hasData(activity!!, response)) {
//                                            fragmentManager!!.popBackStack()
///*
//                                            val bundle = Bundle()
//                                            bundle.putString(Constants.ID, subscribers)
//                                            HelperMethods.navigateTo(activity!!, Constants.FRAGMENT, false, SubscriberDetails(), null, bundle)
//*/
//                                        }
//                                    }
//                                }
//                            })
//                        }
//                    }
//                }
//            }
//        })
//        textView63.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, subscribers)
//                HelperMethods.navigateTo(activity!!, Constants.FRAGMENT, false, SubscriberDetails(), null, bundle, null, "")
//            }
//        })
//
//        /*floatingActionButton6.setOnClickListener {
//            if (filter) {
//                filter = false
//                textView61.visibility = View.INVISIBLE
//                textView62.visibility = View.INVISIBLE
//            } else if (!filter) {
//                filter = true
//                textView62.visibility = View.VISIBLE
//                textView61.visibility = View.VISIBLE
//            }
//        }*/
//    }
//
//    private fun setSelectedFilters() {
//        if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, LANGUAGENAME).isNullOrBlank()) {
//            if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, LANGUAGEID).isNullOrBlank()) {
//                language = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, LANGUAGEID)
//                languageName = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, LANGUAGENAME)
//                selectedlanguage = language
//                textView61.text = languageName
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                }
//            }
//        }
//        if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, CATEGORYNAME).isNullOrBlank()) {
//            if (!com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, CATEGORYID).isNullOrBlank()) {
//                category = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, CATEGORYID)
//                categoryName = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, SELECTEDTHING, CATEGORYNAME)
//                selectedcategory = category
//                textView62.text = categoryName
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                }
//            }
//        }
//    }
//
//    private fun getCategories() {
//        apiService?.getCategory(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :
//            Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.categorylist != null) {
//                                if (!response.body()!!.data!!.categorylist!!.isEmpty()) {
//                                    categorylist = response.body()!!.data!!.categorylist!!
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getLanguage() {
//        apiService?.getLanguage(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :
//            Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (HelperMethods.hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.languagelist != null) {
//                                languagelist = response.body()!!.data!!.languagelist!!
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getBundleData() {
//        val bundle = arguments
//        if (bundle != null) {
//            subscribers = bundle.getString(Constants.ID, "")
//        }
//    }
//
//    private fun setSelectableList() {
//        viewModel = ViewModelProviders.of(activity!!).get(SubPriceData::class.java)
//        viewModel!!.getPrice(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                language = language,
//                category = category,
//                agent_product = "",
//                limit = "",
//                offset = "",
//                searchkey = "",
//                subscriberId = subscribers,
//                subscriber_product = "2",
//                sub_product_status = ""
//        )).observe(this, itemObserver)
//
//        if (subscribeditem?.subscriptions != null) {
//            setSubscribedAdapter(subscribeditem!!.subscriptions!!)
//        } else {
//            if (subscribeditem == null || subscribeditem!!.subscriptions == null || subscribeditem!!.subscriptions!!.isEmpty()) {
//                recyclerView9.layoutManager = LinearLayoutManager(activity)
//                recyclerView9.adapter = EmptyList(activity!!, null)
//            }
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_add_new_subscriptions, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
