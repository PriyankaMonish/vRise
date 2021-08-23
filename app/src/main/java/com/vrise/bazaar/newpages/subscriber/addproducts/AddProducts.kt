package com.vrise.bazaar.newpages.subscriber.addproducts

//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openCategoryPopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openLanguagePopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.ProductListItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.Subdata
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem
//import kotlinx.android.synthetic.main.app_bar_sub_container.*
//import kotlinx.android.synthetic.main.fragment_adds_product.*
//import retrofit2.Call
//import retrofit2.Callback

//class AddProducts : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private var subscribeditem: Subdata? = null
//    private var adaptre: SubscriptionList? = null
//    private var iadaptre: LiscubLisee? = null
//    private lateinit var viewModel: PickedItems
//    private var viewModelSub: DataList? = null
//
//    val pickableItemsObserver = Observer<ArrayList<PrdlistItem?>?> { t -> setPickableItemsAdapter(t) }
//    val subsciItemsObserver = Observer<ArrayList<ProductListItem?>?> { t -> Items(t) }
//
//    private fun Items(t: ArrayList<ProductListItem?>?) {
//        if (t != null) {
//            if (t.isNotEmpty()) {
//                recyclerView.layoutManager = LinearLayoutManager(activity, 0, false)
//                iadaptre = LiscubLisee(activity!!, t, apiService)
//                recyclerView.adapter = iadaptre
//                recyclerView.layoutManager?.scrollToPosition(iadaptre!!.itemCount - 1)
//                iadaptre?.notifyDataSetChanged()
//            }
//        }
//    }
//
//    private fun setPickedItemsAdapter(t: ArrayList<SubscriptionsItem>?) {
//        setVi(t)
//        adaptre = SubscriptionList(activity!!, t, object : Interfaces.ReturnId {
//            override fun returnId(string: String) {
//                if (adaptre != null) {
//                    adaptre!!.notifyItemChanged(adaptre!!.itemCount)
//                    adaptre!!.notifyDataSetChanged()
//                    recyclerView3.layoutManager!!.scrollToPosition(adaptre!!.itemCount - 1)
//                    setVi(subscribeditem!!.subscriptions)
//                }
//            }
//        })
//        recyclerView3.layoutManager = LinearLayoutManager(activity, 0, false)
//        recyclerView3.adapter = adaptre
//    }
//
//    private fun setVi(t: ArrayList<SubscriptionsItem>?) {
//        if (t == null) {
//            textView15.visibility = View.VISIBLE
//            textView28.visibility = View.GONE
//        } else {
//            if (t.isEmpty()) {
//                textView15.visibility = View.VISIBLE
//                textView28.visibility = View.GONE
//            } else {
//                textView15.visibility = View.GONE
//                textView28.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    private fun setPickableItemsAdapter(t: ArrayList<PrdlistItem?>?) {
//        if (!t!!.isEmpty()) {
//            recyclerView4.layoutManager = LinearLayoutManager(activity)
//            recyclerView4.adapter = ItemList(activity, t, object : Interfaces.ReturnId {
//                override fun returnId(string: String) {
//                    if (subscribeditem!!.subscriptions!!.contains(SubscriptionsItem(
//                                    name = t[string.toInt()]!!.name,
//                                    id = t[string.toInt()]!!.id
//                            ))) {
//                        toastit(activity!!, "Already Selected")
//                    } else {
//                        subscribeditem!!.subscriptions!!.add(SubscriptionsItem(
//                                name = t[string.toInt()]!!.name,
//                                id = t[string.toInt()]!!.id
//                        ))
//                        println(GsonBuilder().setPrettyPrinting().create().toJson(subscribeditem!!.subscriptions))
//                        if (adaptre != null) {
//                            adaptre!!.notifyItemChanged(adaptre!!.itemCount)
//                            adaptre!!.notifyDataSetChanged()
//                            recyclerView3.layoutManager!!.scrollToPosition(adaptre!!.itemCount - 1)
//                            setVi(subscribeditem!!.subscriptions)
//                        }
//                    }
//                }
//            })
//        } else {
//            recyclerView4.layoutManager = LinearLayoutManager(activity)
//            recyclerView4.adapter = EmptyList(activity!!, null)
//        }
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
//
//    private var language: String = ""
//
//    private var category: String = ""
//
//    override fun initControl() {
//        setHasOptionsMenu(true)
//        activity!!.page_title.text = "Add Products"
//        subscribeditem = Subdata(subscriptions = ArrayList())
//        setSelectableList()
//        setSubscribedList()
//        textView25.setOnClickListener {
//            apiService?.getSubscriberLanguage(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    if (activity != null && isAdded) {
//                        t!!.printStackTrace()
//                    }
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (activity != null && isAdded) {
//                        if (HelperMethods.hasData(activity!!, response)) {
//                            if (response!!.body()!!.data != null) {
//                                if (response.body()!!.data!!.languagelist != null) {
//                                    openLanguagePopUp(activity, response.body()!!.data!!.languagelist!!, textView25, object : Interfaces.ReturnNamenId {
//                                        override fun returnIdName(name: String, id: String) {
//                                            language = id
//                                            setSelectableList()
//                                        }
//                                    })
//                                }
//                            }
//                        }
//                    }
//                }
//            })
//        }
//        textView27.setOnClickListener {
//            apiService?.getSubscriberCategory(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    if (activity != null && isAdded) {
//                        t!!.printStackTrace()
//                    }
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (activity != null && isAdded) {
//                        if (HelperMethods.hasData(activity!!, response)) {
//                            if (response!!.body()!!.data != null) {
//                                if (response.body()!!.data!!.categorylist != null) {
//                                    if (!response.body()!!.data!!.categorylist!!.isEmpty()) {
//                                        openCategoryPopUp(activity!!, response.body()!!.data!!.categorylist!!, textView27, object : Interfaces.ReturnNamenId {
//                                            override fun returnIdName(name: String, id: String) {
//                                                category = id
//                                                setSelectableList()
//                                            }
//                                        })
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            })
//        }
//        textView28.setOnClickListener {
//            if (hasConnection(activity)) {
//                if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                    print(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            productList = subscribeditem!!.subscriptions
//                    ))
//                    apiService?.subsproductadd(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            productList = subscribeditem!!.subscriptions
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            t!!.printStackTrace()
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    HelperMethods.openPopup("Your request has been sent", activity!!, false, object : Interfaces.Invoke {
//                                        override fun invokeMe() {
//                                            dashBoard()
//                                        }
//                                    })
//
//                                }
//                            }
//                        }
//                    })
//                } else {
//                    toastit(activity!!, "No Items Selected")
//                }
//            }
//        }
//        /*imageView6.setOnClickListener {
//            show.isChecked = !show.isChecked
//        }*/
//        /*show.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//                if (p1) {
//                    textView25.visibility = View.INVISIBLE
//                    textView27.visibility = View.INVISIBLE
//                } else {
//                    textView25.visibility = View.VISIBLE
//                    textView27.visibility = View.VISIBLE
//                }
//            }
//        })*/
//
//    }
//
//    private fun setSubscribedList() {
//        viewModelSub = ViewModelProviders.of(activity!!).get(DataList::class.java)
//        viewModelSub!!.getDashDatas(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)
//        )).observe(this, subsciItemsObserver)
//    }
//
//    private fun dashBoard() {
//        fragmentManager!!.popBackStack()
//    }
//
//    private fun setSelectableList() {
//        viewModel = ViewModelProviders.of(activity!!).get(PickedItems::class.java)
//        viewModel.getProducts(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                language = language,
//                category = category,
//                searchkey = "",
//                limit = "",
//                offset = "",
//                agent_product = "",
//                subscriber_product = "2",
//                sub_product_status = ""
//        )).observe(this, pickableItemsObserver)
//
//        if (subscribeditem?.subscriptions != null) {
//            setPickedItemsAdapter(subscribeditem!!.subscriptions!!)
//        } else {
//            if (subscribeditem == null || subscribeditem!!.subscriptions == null || subscribeditem!!.subscriptions!!.isEmpty()) {
//                recyclerView3.layoutManager = LinearLayoutManager(activity)
//                recyclerView3.adapter = EmptyList(activity!!, null)
//            }
//        }
//
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_adds_product, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
