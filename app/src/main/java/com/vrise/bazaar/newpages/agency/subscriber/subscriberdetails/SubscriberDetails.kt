package com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails
//
//import android.app.Activity.RESULT_OK
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout.VERTICAL
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions.AddNewSubscription
//import com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers.SubscribersDetail
//import com.vrise.bazaar.newpages.agency.todo.SettleUp
//import com.vrise.bazaar.newpages.agency.todo.bills.Bills
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.vrise.bazaar.newpages.utilities.models.submodels.Subdata
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionRequestItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.fragment_subscriber_details.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class SubscriberDetails : InitSub() {
//
//    override fun networkAvailable() {
//        getViewDatas()
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private var viewModelSubcribed: SubcribedData? = null
//    private var subscriber = ""
//    private var subscriptionsItem = ArrayList<SubscriptionsItem>()
//    private var adapters: SubscriptionList? = null
//    private fun setRequestsAdapter(t: ArrayList<SubscriptionRequestItem?>?) {
//        recyclerView7.layoutManager = LinearLayoutManager(activity)
//        recyclerView7.adapter = RequestList(activity!!, t, subscriber, object : Interfaces.ReturnId {
//            override fun returnId(string: String) {
//                if (subscriptionsItem != null) {
//                    subscriptionsItem.add(SubscriptionsItem(
//                            name = t!![string.toInt()]!!.name
//                    ))
//                    if (adapters != null) {
//                        adapters!!.notifyItemChanged(adapters!!.itemCount)
//                        adapters!!.notifyDataSetChanged()
//                    }
//                }
//            }
//        }, apiService)
//    }
//
//    private val subscriberObserver = Observer<Subdata> { t ->
//        if (t != null) {
//            progressBar12.visibility = View.GONE
//            constraintLayout3.visibility = View.VISIBLE
//            Picasso.get().load(t.imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(imageView10)
//
//            if (t.deviceae.isNullOrBlank()) {
//                imageView10.borderColor = ContextCompat.getColor(activity!!, R.color.cb_errorRed)
//            } else {
//                imageView10.borderColor = ContextCompat.getColor(activity!!, R.color.bright_turquoise)
//            }
//
//            imageView10.setOnClickListener {
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, subscriber)
//                bundle.putString(Constants.FROM, 1.toString())
//                navigateTo(activity!!, Constants.FRAGMENT, true, SubscribersDetail(), null, bundle, null, "")
//            }
//
//            textView54.text = t.name
//            textView55.text = t.mobile
//            textView56.text = t.code
//            imageView11.visibility = View.VISIBLE
//            textView58.text = "₹ " + t.subscriberDue
//            if (t.subscriberDue.isNullOrBlank()) {
//                imageView11.visibility = View.GONE
//            }
//            imageView11.setOnClickListener {
//                if (hasConnection(activity!!)) {
//                    imageView15.visibility = View.VISIBLE
//                    println(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            subscriberId = subscriber
//                    ))
//                    apiService?.subdueremind(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            subscriberId = subscriber
//                    ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun failed(t: Throwable) {
//                            imageView15.visibility = View.GONE
//                            t.printStackTrace()
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//
//                            }
//                            imageView15.visibility = View.GONE
//                        }
//                    }) {})
//                }
//            }
//            if (t.subscriptions != null) {
//                subscriptionsItem = t.subscriptions!!
//                setSubscribedAdapter(subscriptionsItem)
//            } else {
//                recyclerView8.layoutManager = LinearLayoutManager(activity)
//                recyclerView8.adapter = EmptyList(activity!!, null)
//            }
//            if (t.subscriptionRequest != null) {
//                setRequestsAdapter(t.subscriptionRequest)
//            } else {
//                recyclerView7.layoutManager = LinearLayoutManager(activity)
//                recyclerView7.adapter = EmptyList(activity!!, null)
//            }
////            imageView3.text = "Call"
//            imageView3.setOnClickListener {
//                if (t.mobile != null) {
//                    if (t.mobile != "") {
//                        activity?.startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", t.mobile.toString(), null)))
//                    }
//                }
//            }
//        } else {
//            progressBar12.visibility = View.GONE
//            constraintLayout3.visibility = View.GONE
//            textView54.text = ""
//            textView55.text = ""
//            textView56.text = ""
//            textView58.text = ""
//            imageView11.visibility = View.GONE
//            recyclerView8.layoutManager = LinearLayoutManager(activity)
//            recyclerView8.adapter = EmptyList(activity!!, null)
//            recyclerView7.layoutManager = LinearLayoutManager(activity)
//            recyclerView7.adapter = EmptyList(activity!!, null)
//            imageView3.setOnClickListener(null)
//        }
//    }
//
//    private fun setSubscribedAdapter(t: List<SubscriptionsItem>?) {
//        recyclerView8.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
//        adapters = SubscriptionList(activity!!, subscriptionsItem)
//        recyclerView8.adapter = adapters
//    }
//
//    override fun initView() {
//        constraintLayout3.visibility = View.GONE
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        getBundleData()
//        activity!!.page_title.text = "Subscriber Details"
//
//        getViewDatas()
//        textView60.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, subscriber)
//            navigateTo(activity!!, FRAGMENT, true, SettleUp(), null, bundle, null, "")
//        }
//        textView59.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, subscriber)
//            navigateTo(activity!!, FRAGMENT, true, Bills(), null, bundle, null, "")
//        }
//        imageView12.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val intent = Intent(activity, AddNewSubscription::class.java)
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, subscriber)
//                intent.putExtras(bundle)
//                //navigateTo(activity!!, ACTIVITY, true, null, AddNewSubscription::class.java, bundle)
//                startActivityForResult(intent, 10)
//            }
//        })
//
//    }
//
//    private fun getViewDatas() {
//        viewModelSubcribed = ViewModelProviders.of(activity!!).get(SubcribedData::class.java)
//        viewModelSubcribed!!.getSubcription(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.DATAFILE, Constants.ID),
//                subscriberId = subscriber
//        )).observe(this, subscriberObserver)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        try {
//            if (requestCode == 10) {
//                if (resultCode == RESULT_OK) {
//                    if (data != null) {
//                        if (data.getStringExtra(Constants.ID) != null) {
//                            subscriber = data.getStringExtra(Constants.ID)
//                        }
//                        getViewDatas()
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun getBundleData() {
//        val bundle = arguments
//        if (bundle != null) {
//            if (bundle.getString(Constants.ID) != null) {
//                subscriber = bundle.getString(Constants.ID) ?: ""
//            }
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscriber_details, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
