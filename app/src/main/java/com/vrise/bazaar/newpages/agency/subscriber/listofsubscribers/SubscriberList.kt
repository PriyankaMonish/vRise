package com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers

//import androidx.lifecycle.*
//import android.os.Bundle
//import android.os.Parcelable
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.subscriber.AddSubscribers
//import com.vrise.bazaar.newpages.utilities.ClickListener
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//import com.vrise.bazaar.newpages.utilities.EmptyList
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.models.Requests
//import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
//import kotlinx.android.synthetic.main.fragment_subscriber_list.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class SubscriberList : InitSub() {
//    var state: Parcelable? = null
//
//    override fun onPause() {
//        state = recyclerView6.layoutManager?.onSaveInstanceState()
//        super.onPause()
//    }
//    override fun networkAvailable() {
//        getSubscriberList()
//    }
//
//    override fun networkUnavailable() {
//        toast(activity, getString(R.string.no_internet))
//    }
//
//    var routeId = ""
//    var routeName = ""
//    lateinit var subList: ArrayList<SublistItem>
//    private var viewModel: SubscriberData? = null
//
//    private val itemObserver = Observer<ArrayList<SublistItem>> { t ->
//        if (t != null) {
//            subList = t
//            setAdapter(t)
//        } else {
//            progressBar10.visibility = View.GONE
//            recyclerView6.adapter = EmptyList(activity!!, Requests(
//                    id = R.drawable.ic_subscr,
//                    display = "No subscribers found"
//            ))
//        }
//    }
//
//    private fun setAdapter(t: ArrayList<SublistItem>) {
//        progressBar10.visibility = View.GONE
//        recyclerView6.adapter = ListOfSubscriber(activity!!, t, apiService)
//        if (state != null) {
//            recyclerView6.layoutManager?.onRestoreInstanceState(state)
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
//    override fun initControl() {
//        subList = ArrayList<SublistItem>()
//        progressBar10.visibility = View.VISIBLE
//        searchView.setText("")
//        searchView.clearFocus()
//        getBundleData()
//
//        getSubscriberList()
//
//        floatingActionButton4.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val bundle: Bundle? = Bundle()
//                bundle!!.putString(Constants.ID, routeId)
//                bundle.putString(Constants.SECOND, routeName)
//                navigateTo(activity!!, FRAGMENT, true, AddSubscribers(), null, bundle, null, "")
//            }
//        })
//
//        searchView.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                setAdapter(subList.filter { it.name.toString().toLowerCase().contains(p0.toString().toLowerCase(), true) or it.mobile.toString().contains(p0.toString()) or it.code.toString().toLowerCase().contains(p0.toString().toLowerCase(), true) } as ArrayList<SublistItem>)
//            }
//        })
//        recyclerView6.layoutManager = LinearLayoutManager(activity)
//    }
//
//    private fun getSubscriberList() {
//
//        searchView.clearComposingText()
//
//        progressBar10.visibility = View.VISIBLE
//        viewModel = ViewModelProviders.of(activity!!).get(SubscriberData::class.java)
//        viewModel?.getSubcribers(
//                com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                routeId,
//                "",
//                "",
//                ""
//        )?.observe(this, itemObserver)
//    }
//
//    private fun getBundleData() {
//        routeId = ""
//        routeName = "Route Name"
//        arguments?.let {
//            routeId = it.getString(Constants.ID) ?: ""
//            routeName = it.getString(Constants.SECOND) ?: "Route Name"
//        }
//
//        activity?.page_title?.text = routeName
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscriber_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
