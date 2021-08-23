package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions

//import android.app.Activity
//import android.app.Dialog
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.view.*
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import android.widget.TextView
//import androidx.annotation.NonNull
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.menus.subscriberprice.SubPriceData
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.CATEGORYID
//import com.vrise.bazaar.newpages.utilities.Constants.CATEGORYNAME
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.LANGUAGEID
//import com.vrise.bazaar.newpages.utilities.Constants.LANGUAGENAME
//import com.vrise.bazaar.newpages.utilities.Constants.SELECTEDTHING
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
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
//class AddNewSubscription : InitMain() {
//
//    private var language = ""
//    var languageName = ""
//    private var selectedlanguage: String? = null
//    var languagelist: ArrayList<LanguagelistItem>? = null
//    var categoryName = ""
//    private var category = ""
//    private var selectedcategory: String? = null
//    var categorylist: ArrayList<CategorylistItem>? = null
//    private var subscribers = ""
//
//    private var viewModel: SubPriceData? = null
//    private var subViewModel: DataSub? = null
//
//    private var rejecteditem: Subdata? = null
//    private var subscriptionsList: ArrayList<PrdlistItem?>? = null
//    private var subscribeditem: Subdata? = null
//    private var wholeitem: ArrayList<PrdlistItem?>? = null
//
//    private var selectedItems = 0
//    private var rejectedItems = 0
//
//    private var adaptre: SubscriptionList? = null
//    private var subList: SubscribedList? = null
//    private var adapterItemList: ItemList? = null
//
//    var dialog: Dialog? = null
//
//    var behavior: BottomSheetBehavior<*>? = null
//
//    private val itemObserver = Observer<List<PrdlistItem?>> { t ->
//        if (t != null) {
//            setItemAdapter(t.toMutableList())
//        } else {
//            progressBar.visibility = View.GONE
//            recyclerView14.layoutManager = LinearLayoutManager(this@AddNewSubscription)
//            recyclerView14.adapter = EmptyList(this@AddNewSubscription, null)
//        }
//    }
//
//    private val subItemObserver = Observer<ArrayList<PrdlistItem?>> { t ->
//        if (t != null) {
//            subscriptionsList = t
//            setSubscriptedAdapter(subscriptionsList!!)
//        } else {
//            recyclerView.layoutManager = LinearLayoutManager(this@AddNewSubscription)
//            recyclerView.adapter = EmptyList(this@AddNewSubscription, null)
//        }
//    }
//
//    private val selectionObserver = Observer<String> {
//        textView183.text = "$selectedItems Items Selected, $rejectedItems Items Removed"
//    }
//
//    private fun setSubscriptedAdapter(t: ArrayList<PrdlistItem?>) {
//        recyclerView.layoutManager = LinearLayoutManager(this@AddNewSubscription, 0, false)
//        subList = SubscribedList(this@AddNewSubscription, t, object : Interfaces.ReturnPrdlistItem {
//            override fun returnData(clickPosvalue: PrdlistItem?) {
//
//            }
//
//            override fun removeData(clickPosvalue: PrdlistItem?) {
//
//                if (clickPosvalue != null) {
//
//                    if (rejecteditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))) {
//
//                    } else {
//                        rejecteditem!!.subscriptions!!.add(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))
//
//                        println(GsonBuilder().setPrettyPrinting().create().toJson(rejecteditem!!.subscriptions))
//
//                        if (adapterItemList != null) {
//                            adapterItemList!!.addToList(clickPosvalue, selectedlanguage, selectedcategory)
//                        }
//
//                        rejectedItems++
//                        selectionObserver.onChanged("")
//                    }
//                }
//
//                when {
//                    subscribeditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    rejecteditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    else -> behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//
//            }
//        })
//        recyclerView.adapter = subList
//        recyclerView.layoutManager!!.scrollToPosition(subList!!.itemCount - 1)
//        subList?.notifyDataSetChanged()
//    }
//
//    private fun setSubscribedAdapter(t: ArrayList<SubscriptionsItem>, wholeitem: ArrayList<PrdlistItem?>?) {
//        recyclerView9.layoutManager = LinearLayoutManager(this@AddNewSubscription, 0, false)
//        adaptre = SubscriptionList(this@AddNewSubscription, t, wholeitem, object : Interfaces.ReturnSubscriptionsItem {
//            override fun removeData(clickPosvalue: SubscriptionsItem) {
//                when {
//                    subscribeditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    rejecteditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    else -> behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//            }
//        }, object : Interfaces.ReturnPrdlistItem {
//            override fun returnData(clickPosvalue: PrdlistItem?) {
//
//            }
//
//            override fun removeData(clickPosvalue: PrdlistItem?) {
//                if (clickPosvalue != null) {
//
//                    if (subscribeditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))) {
//                        subscribeditem!!.subscriptions!!.remove(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))
//                    }
//                    println(GsonBuilder().setPrettyPrinting().create().toJson(subscribeditem!!.subscriptions))
//                    if (adapterItemList != null) {
//                        adapterItemList!!.addToList(clickPosvalue, selectedlanguage, selectedcategory)
//                    }
//
//                    selectedItems--
//                    selectionObserver.onChanged("")
//                }
//
//                when {
//                    subscribeditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    rejecteditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    else -> behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//            }
//        })
//        recyclerView9.adapter = adaptre
//    }
//
//    private fun setItemAdapter(t: MutableList<PrdlistItem?>?) {
//        val layoutManager = LinearLayoutManager(this@AddNewSubscription)
//        recyclerView14.layoutManager = layoutManager
//
//        val dividerItemDecoration = DividerItemDecoration(recyclerView14.context, layoutManager.orientation)
//        recyclerView14.addItemDecoration(dividerItemDecoration)
//
//        adapterItemList = ItemList(this@AddNewSubscription, t, object : Interfaces.ReturnPrdlistItem {
//
//            override fun returnData(clickPosvalue: PrdlistItem?) {
//
//                if (subscriptionsList!!.contains(clickPosvalue)) {
//
//                } else {
//
//                    if (rejecteditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue!!.name, id = clickPosvalue.id))) {
//
//                        removeRejected(clickPosvalue)
//
//                    } else {
//
//                        if (wholeitem!!.contains(clickPosvalue)) {
//
//                        } else {
//
//                            wholeitem!!.add(clickPosvalue)
//
//                            if (subscribeditem!!.subscriptions!!.contains(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))) {
//
//                            } else {
//
//                                subscribeditem!!.subscriptions!!.add(SubscriptionsItem(
//                                        name = clickPosvalue.name,
//                                        id = clickPosvalue.id
//                                ))
//
//                                println(GsonBuilder().setPrettyPrinting().create().toJson(subscribeditem!!.subscriptions))
//
//                                selectedItems++
//                                selectionObserver.onChanged("")
//                            }
//
//                            if (adaptre != null) {
//                                adaptre!!.notifyItemChanged(adaptre!!.itemCount)
//                                adaptre!!.notifyDataSetChanged()
//                                recyclerView9.layoutManager!!.scrollToPosition(adaptre!!.itemCount - 1)
//                            }
//
//                            if (adapterItemList != null) {
//                                adapterItemList!!.removeFromList(clickPosvalue, selectedlanguage, selectedcategory)
//                            }
//
//                        }
//                    }
//
//                }
//
//
//                if (subscribeditem!!.subscriptions!!.isNotEmpty()) {
//                    behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                } else {
//                    if (rejecteditem!!.subscriptions!!.isNotEmpty()) {
//                        behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//                    } else {
//                        behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//                    }
//                }
//
//            }
//
//            override fun removeData(clickPosvalue: PrdlistItem?) {}
//
//        }, subscribeditem)
//        recyclerView14.adapter = adapterItemList
//        progressBar.visibility = View.GONE
//        setSelectedFilters()
//    }
//
//    private fun removeRejected(clickPosvalue: PrdlistItem?) {
//        if (clickPosvalue != null) {
//            if (rejecteditem != null) {
//                if (rejecteditem!!.subscriptions != null) {
//                    if (rejecteditem!!.subscriptions!!.isNotEmpty()) {
//
//                        rejecteditem!!.subscriptions!!.remove(SubscriptionsItem(name = clickPosvalue.name, id = clickPosvalue.id))
//
//                        println(GsonBuilder().setPrettyPrinting().create().toJson(rejecteditem!!.subscriptions))
//
//                        if (subList != null) {
//                            subscriptionsList!!.add(clickPosvalue)
//                            subList?.notifyDataSetChanged()
//                            recyclerView.layoutManager!!.scrollToPosition(subList!!.itemCount - 1)
//                        }
//
//                        if (adapterItemList != null) {
//                            adapterItemList!!.removeFromList(clickPosvalue, selectedlanguage, selectedcategory)
//                        }
//                    }
//                }
//            }
//            rejectedItems--
//            selectionObserver.onChanged("")
//        }
//
//        when {
//            subscribeditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//            rejecteditem!!.subscriptions!!.isNotEmpty() -> behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//            else -> behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//        }
//
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
//        this@AddNewSubscription.page_title.text = "Add New Subscriptions"
//
//        behavior = BottomSheetBehavior.from(linearLayout5)
//        behavior!!.state = BottomSheetBehavior.STATE_HIDDEN
//        behavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
//
//            }
//
//            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
//                // React to dragging events
//            }
//        })
//        progresswhole.visibility = View.VISIBLE
//        progressBar.visibility = View.VISIBLE
//        whole.visibility = View.GONE
//        subscriptionsList = ArrayList()
//        wholeitem = ArrayList()
//
//        rejecteditem = Subdata(subscriptions = ArrayList())
//        subscribeditem = Subdata(subscriptions = ArrayList())
//
//        languagelist = ArrayList()
//        categorylist = ArrayList()
//
//        selectionObserver.onChanged("")
//
//        getLanguage()
//        getCategories()
//        getBundleData()
//        setSelectableList()
//        setSubscribedList()
//
//        textView61.visibility = View.VISIBLE
//        textView62.visibility = View.VISIBLE
//
//        textView61.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//
//            }
//        })
//        textView62.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//
//            }
//        })
//
//        textView64.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                progressBar.visibility = View.VISIBLE
//                if (hasConnection(this@AddNewSubscription)) {
//                    print(GsonBuilder().setPrettyPrinting().create().toJson(Request(
//                            utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID),
//                            subscriberId = subscribers,
//                            productList = subscribeditem!!.subscriptions,
//                            removeProduct = rejecteditem!!.subscriptions
//                    )))
//
//                    apiService?.SubProductAdd(Request(
//                            utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID),
//                            subscriberId = subscribers,
//                            productList = subscribeditem!!.subscriptions,
//                            removeProduct = rejecteditem!!.subscriptions
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (!this@AddNewSubscription.isFinishing) {
//                                t!!.printStackTrace()
//                                progressBar.visibility = View.GONE
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (!this@AddNewSubscription.isFinishing) {
//                                if (hasData(this@AddNewSubscription, response)) {
//                                    val intent = Intent()
//                                    intent.putExtra(Constants.ID, subscribers)
//                                    setResult(Activity.RESULT_OK, intent)
//                                    finish()
//                                } else {
//                                    progressBar.visibility = View.GONE
//                                }
//                            }
//                        }
//                    })
//                } else {
//                    progressBar.visibility = View.GONE
//                }
//            }
//        })
//
//        textView63.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                finish()
//            }
//        })
//
//        progresswhole.visibility = View.GONE
//        whole.visibility = View.VISIBLE
//    }
//
//    private fun OpenDialogs() {
//        if (!language.isBlank()) {
//            val selectedL = languagelist?.filter { it.id == language }!![0]
//            languagelist?.remove(selectedL)
//            languagelist?.add(0, selectedL)
//        }
//
//        if (!category.isBlank()) {
//            val selectedC = categorylist?.filter { it.id == category }!![0]
//            categorylist?.remove(selectedC)
//            categorylist?.add(0, selectedC)
//        }
//        dialog = Dialog(this)
//        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog?.setCancelable(false)
//        dialog?.setContentView(R.layout.dialog)
//        val textView185 = dialog?.findViewById<TextView>(R.id.textView185)
//        val spinner185 = dialog?.findViewById<Spinner>(R.id.spinner185)
//        val textView186 = dialog?.findViewById<TextView>(R.id.textView186)
//        val spinner186 = dialog?.findViewById<Spinner>(R.id.spinner186)
//        val textView191 = dialog?.findViewById<TextView>(R.id.textView191)
//        textView191?.setOnClickListener { dialog?.dismiss() }
//        var languageListItem: LanguagelistItem? = null
//        var categoryListItem: CategorylistItem? = null
//        val textView190 = dialog?.findViewById<TextView>(R.id.textView190)
//        textView190?.setOnClickListener {
//
//            if (languageListItem != null) {
//                textView61.text = languageListItem?.language
//                languageName = languageListItem?.language.toString()
//                language = languageListItem?.id.toString()
//                selectedlanguage = language
//                Preference.putPreference(this@AddNewSubscription, LANGUAGENAME, languageName, SELECTEDTHING)
//                Preference.putPreference(this@AddNewSubscription, LANGUAGEID, selectedlanguage, SELECTEDTHING)
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                }
//            }
//
//            if (categoryListItem != null) {
//                textView62.text = categoryListItem?.name
//                categoryName = categoryListItem?.name.toString()
//                category = categoryListItem?.id.toString()
//                selectedcategory = category
//                Preference.putPreference(this@AddNewSubscription, CATEGORYNAME, categoryName, SELECTEDTHING)
//                Preference.putPreference(this@AddNewSubscription, CATEGORYID, selectedcategory, SELECTEDTHING)
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage, selectedcategory)
//                }
//            }
//
//            dialog?.dismiss()
//
//        }
//        val lanada = DialogAdapter(this, android.R.layout.simple_spinner_item, languagelist!!)
//        spinner185?.adapter = lanada
//        val catada = DialogAdapter(this, android.R.layout.simple_spinner_item, categorylist!!)
//        spinner186?.adapter = catada
//        spinner185?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                languageListItem = (parent)?.selectedItem as LanguagelistItem
//                textView185?.text = languageListItem?.language
//            }
//        }
//        spinner186?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                categoryListItem = (parent)?.selectedItem as CategorylistItem
//                textView186?.text = categoryListItem?.name
//            }
//        }
//        dialog?.show()
//    }
//
//    private fun setSubscribedList() {
//        subViewModel = ViewModelProviders.of(this@AddNewSubscription).get(DataSub::class.java)
//        subViewModel!!.getPrice(Request(
//                utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID),
//                language = "",
//                category = "",
//                agent_product = "",
//                limit = "",
//                offset = "",
//                searchkey = "",
//                subscriberId = subscribers,
//                subscriber_product = "1",
//                sub_product_status = "1"
//        )).observe(this, subItemObserver)
//    }
//
//    private fun getCategories() {
//        apiService?.getCategory(Request(utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID)))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (!this@AddNewSubscription.isFinishing) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (!this@AddNewSubscription.isFinishing) {
//                    if (hasData(this@AddNewSubscription, response)) {
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
//        apiService?.getLanguage(Request(utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID)))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (!this@AddNewSubscription.isFinishing) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (!this@AddNewSubscription.isFinishing) {
//                    if (HelperMethods.hasData(this@AddNewSubscription, response)) {
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
//        val bundle = intent.extras
//        if (bundle != null) {
//            subscribers = bundle.getString(Constants.ID, "")
//        }
//    }
//
//    private fun setSelectableList() {
//        viewModel = ViewModelProviders.of(this@AddNewSubscription).get(SubPriceData::class.java)
//        viewModel!!.getPrice(Request(
//                utoken = Preference.getSinglePreference(this@AddNewSubscription, DATAFILE, Constants.ID),
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
//            setSubscribedAdapter(subscribeditem!!.subscriptions!!, wholeitem)
//        } else {
//            if (subscribeditem == null || subscribeditem!!.subscriptions == null || subscribeditem!!.subscriptions!!.isEmpty()) {
//                recyclerView9.layoutManager = LinearLayoutManager(this@AddNewSubscription)
//                recyclerView9.adapter = EmptyList(this@AddNewSubscription, null)
//            }
//        }
//    }
//
//    private fun setSelectedFilters() {
//        if (!Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, LANGUAGENAME).isNullOrBlank()) {
//            if (!Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, LANGUAGEID).isNullOrBlank()) {
//                language = Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, LANGUAGEID)
//                languageName = Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, LANGUAGENAME)
//                selectedlanguage = language
//                textView61.text = languageName
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                }
//            }
//        }
//        if (!Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, CATEGORYNAME).isNullOrBlank()) {
//            if (!Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, CATEGORYID).isNullOrBlank()) {
//                category = Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, CATEGORYID)
//                categoryName = Preference.getSinglePreference(this@AddNewSubscription, SELECTEDTHING, CATEGORYNAME)
//                selectedcategory = category
//                textView62.text = categoryName
//                if (adapterItemList != null) {
//                    adapterItemList!!.updateList(selectedlanguage!!, selectedcategory)
//                }
//            }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_add_new_subscriptions)
//        setInitializer()
//        setSupportActionBar(custom_toolbar)
//        imageView19.visibility = View.VISIBLE
//        imageView19.setOnClickListener {
//            finish()
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.filter_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            R.id.menu_select -> {
//                OpenDialogs()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    class DialogAdapter(context: Context, layout: Int, val items: ArrayList<*>) : ArrayAdapter<Any>(context, layout, items.toMutableList()) {
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//            return setView(convertView, position)!!
//        }
//
//        private fun setView(convertView: View?, position: Int): View? {
//            val holder: ViewHolder
//
//            var convertview = convertView
//
//            if (null == convertview) {
//                holder = ViewHolder()
//                convertview = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(android.R.layout.simple_list_item_1, null)
//                holder.textView = convertview?.findViewById(android.R.id.text1)
//                convertview?.tag = holder
//            } else {
//                holder = convertview.tag as ViewHolder
//            }
//
//            if (getItemViewType(position) == 0) {
//                val products = items[position] as LanguagelistItem?
//                holder.textView?.text = products?.language
//            } else if (getItemViewType(position) == 1) {
//                val products = items[position] as CategorylistItem?
//                holder.textView?.text = products?.name
//            }
//
//            return convertview
//        }
//
//        override fun getItemViewType(position: Int): Int {
//            return when {
//                items[position] is LanguagelistItem -> 0
//                items[position] is CategorylistItem -> 1
//                else -> super.getItemViewType(position)
//            }
//        }
//
//        override fun getCount(): Int {
//            return items.size
//        }
//
//        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
//            return setView(convertView, position)
//        }
//
//        class ViewHolder {
//            internal var textView: TextView? = null
//        }
//    }
//
//
//}
