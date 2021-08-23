package com.vrise.bazaar.newpages.agency.menus.todo.newlyadded

import android.app.Application
import android.app.Dialog
import androidx.lifecycle.*
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.vrise.R

import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT

import com.vrise.bazaar.newpages.utilities.EmptyList
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo

import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.ReminderSubscribersItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_newly_added.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback


//class NewlyAdded : InitSub() {
//    override fun networkAvailable() {
//        getData(subscriber, "1")
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private var subscriber = "0"
//
//    private var viewModelSubcribed: SubcriberData? = null
//    private lateinit var Item: ArrayList<ReminderSubscribersItem>
//    private val subscriberObserver = Observer<ArrayList<ReminderSubscribersItem>> { t ->
//        if (t != null) {
//            Item = t
//            setAdapter(Item)
//        } else {
//            linearLayout2.layoutManager = LinearLayoutManager(activity)
//            linearLayout2.adapter = EmptyList(activity!!, null)
//            progressBar19.visibility = View.GONE
//        }
//    }
//
//    private fun setAdapter(t: ArrayList<ReminderSubscribersItem>?) {
//        linearLayout2.layoutManager = LinearLayoutManager(activity)
//        progressBar19.visibility = View.GONE
//        linearLayout2.adapter = SubscriptionList(activity!!, t)
//    }
//
//    override fun initView() {
//        textView70.clearFocus()
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Newly Added"
//        val bundle = arguments
//        Item = ArrayList()
//        subscriber = bundle!!.getString(Constants.ID, "")
//        textView70.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                setAdapter(Item.filter { it.name.toString().toLowerCase().contains(p0.toString().toLowerCase()) or
//                        it.mobile.toString().contains(p0.toString()) or it.code.toString().toLowerCase().contains(p0.toString().toLowerCase()) }
//                        as ArrayList<ReminderSubscribersItem>)
//            }
//        })
//
//        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.drop))
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        textView71.adapter = adapter
//
//        progressBar19.visibility = View.VISIBLE
//
//        /*getData(subscriber, "1")*/
//
//
//        try {
//            textView71.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                    try {
//                        textView27.text = textView71.selectedItem.toString()
//                        textView70.setText("")
//                        progressBar19.visibility = View.VISIBLE
//                        getData(subscriber, textView71.selectedItem.toString().replace("[^0-9]", "").trim())
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun getData(subscriber: String, day: String) {
//        try {
//            viewModelSubcribed = ViewModelProviders.of(activity!!).get(SubcriberData::class.java)
//            viewModelSubcribed!!.getSubscriberAdds(Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.DATAFILE, Constants.ID),
//                    routeId = subscriber,
//                    day = day
//            )).observe(this, subscriberObserver)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_newly_added, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//    class SubscriptionList(val activity: FragmentActivity, val dataItem: ArrayList<ReminderSubscribersItem>?) : RecyclerView.Adapter<SubscriptionList.ViewHolder>() {
//        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//            return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_sub_list_add, p0, false))
//        }
//
//        override fun getItemCount(): Int {
//            return dataItem!!.size
//        }
//
//        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//            p0.imageView13.setOnClickListener {
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, dataItem!![p0.adapterPosition].id)
//                navigateTo(activity, FRAGMENT, true, SubscriberDetails(), null, bundle, null, "")
//            }
//            Picasso.get().load(dataItem!![p0.adapterPosition].imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(p0.imageView13)
//            p0.textView72.text = dataItem[p0.adapterPosition].name
//
//            p0.textView73.text = dataItem[p0.adapterPosition].subscriptions
//
//            if (dataItem[p0.adapterPosition].newUnsubscription.isNullOrBlank() && dataItem[p0.adapterPosition].newSubscription.isNullOrBlank()) {
//                p0.two.visibility = View.GONE
//            } else {
//                p0.two.visibility = View.VISIBLE
//                if (dataItem[p0.adapterPosition].newUnsubscription.isNullOrBlank()) {
//                    p0.textView156.text = "0"
//                } else {
//                    p0.textView156.text = dataItem[p0.adapterPosition].newUnsubscription!!.split(",").size.toString()
//                }
//
//                if (dataItem[p0.adapterPosition].newSubscription.isNullOrBlank()) {
//                    p0.textView154.text = "0"
//                } else {
//                    p0.textView154.text = dataItem[p0.adapterPosition].newSubscription!!.split(",").size.toString()
//                }
//            }
//            p0.linearLayout2.setOnClickListener {
//                openDialogs(dataItem[p0.adapterPosition])
//            }
//
//        }
//
//        private fun openDialogs(reminderSubscribersItem: ReminderSubscribersItem) {
//            val dialog = Dialog(activity)
//            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(false)
//            activity.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//            dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(activity, android.R.drawable.screen_background_dark_transparent))
//            dialog.setContentView(R.layout.item_sub_list_add1)
//            val imageView14 = dialog.findViewById<ImageView>(R.id.imageView14)
//            val textView74 = dialog.findViewById<TextView>(R.id.textView74)
//            val imageView15 = dialog.findViewById<ImageView>(R.id.imageView15)
//            val recyclerView11 = dialog.findViewById<RecyclerView>(R.id.recyclerView11)
//            val recyclerView10 = dialog.findViewById<RecyclerView>(R.id.recyclerView10)
//            val textView151 = dialog.findViewById<TextView>(R.id.textView151)
//            val textView150 = dialog.findViewById<TextView>(R.id.textView150)
//            Picasso.get().load(reminderSubscribersItem.imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(imageView14)
//            textView74.text = reminderSubscribersItem.name
//            imageView15.setOnClickListener {
//                dialog.dismiss()
//            }
//            recyclerView10.layoutManager = GridLayoutManager(activity, 2)
//            recyclerView11.layoutManager = GridLayoutManager(activity, 2)
//
//
//            if (reminderSubscribersItem.newSubscription != null) {
//                if (reminderSubscribersItem.newSubscription.isNotBlank()) {
//                    var listIterator = listOf(reminderSubscribersItem.newSubscription)
//                    if (reminderSubscribersItem.newSubscription.contains(",")) {
//                        listIterator = reminderSubscribersItem.newSubscription.split(",")
//                    }
//                    recyclerView10.adapter = SubscribedList(listIterator)
//                } else {
//                    textView151.text = "No Products Added"
//                    recyclerView10.adapter = EmptyList(activity, null)
//                }
//            } else {
//                textView151.text = "No Products Added"
//                recyclerView10.adapter = EmptyList(activity, null)
//            }
//
//            if (reminderSubscribersItem.newUnsubscription != null) {
//                if (reminderSubscribersItem.newUnsubscription.isNotBlank()) {
//                    var listIterator = listOf(reminderSubscribersItem.newUnsubscription)
//                    if (reminderSubscribersItem.newUnsubscription.contains(",")) {
//                        listIterator = reminderSubscribersItem.newUnsubscription.split(",")
//                    }
//                    recyclerView11.adapter = UnsubscribedList(listIterator)
//                } else {
//                    textView150.text = "No Products Removed"
//                    recyclerView11.adapter = EmptyList(activity, null)
//                }
//            } else {
//                textView150.text = "No Products Removed"
//                recyclerView11.adapter = EmptyList(activity, null)
//            }
//
//            dialog.show()
//        }
//
//        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val two = itemView.findViewById<ConstraintLayout>(R.id.two)
//            val imageView13 = itemView.findViewById<ImageView>(R.id.imageView13)
//            val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//            val textView73 = itemView.findViewById<TextView>(R.id.textView73)
//            val textView156 = itemView.findViewById<TextView>(R.id.textView156)
//            val textView154 = itemView.findViewById<TextView>(R.id.textView154)
//            val linearLayout2 = itemView.findViewById<LinearLayout>(R.id.linearLayout2)
//        }
//    }
//
//    class SubscribedList(val listIterator: List<String>) : RecyclerView.Adapter<SubscribedList.ViewHolder>() {
//        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//            return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subscription_list_added, p0, false))
//        }
//
//        override fun getItemCount(): Int {
//            return listIterator.size
//        }
//
//        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//            p0.textView61.text = listIterator[p0.adapterPosition]
//        }
//
//        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val textView61 = itemView.findViewById<TextView>(R.id.textView61)
//        }
//
//    }
//
//    class UnsubscribedList(val listIterator: List<String>) : RecyclerView.Adapter<UnsubscribedList.ViewHolder>() {
//        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//            return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_unsubsc_list, p0, false))
//        }
//
//        override fun getItemCount(): Int {
//            return listIterator.size
//        }
//
//        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//            p0.textView61.text = listIterator[p0.adapterPosition]
//        }
//
//        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val textView61 = itemView.findViewById<TextView>(R.id.textView61)
//        }
//
//    }
//
//    class SubcriberData(news : Application) : AndroidViewModel(news) {
//        private lateinit var dataItem: MutableLiveData<ArrayList<ReminderSubscribersItem>>
//        private val apiService = (news as BaseApp).oldService()
//        fun getSubscriberAdds(request: Request): LiveData<ArrayList<ReminderSubscribersItem>> {
//            dataItem = MutableLiveData()
//            loadSubscriberLists(request)
//            return dataItem
//        }
//
//        fun loadSubscriberLists(request: Request) {
//            print(request)
//            apiService?.subscriberaddlist(request)?.enqueue(object : Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    dataItem.value = null
//                    t!!.printStackTrace()
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (hasData(response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.reminderSubscribers != null) {
//                                if (response.body()!!.data!!.reminderSubscribers!!.isNotEmpty()) {
//                                    dataItem.value = response.body()!!.data!!.reminderSubscribers
//                                } else {
//                                    dataItem.value = null
//                                }
//                            } else {
//                                dataItem.value = null
//                            }
//                        } else {
//                            dataItem.value = null
//                        }
//                    } else {
//                        dataItem.value = null
//                    }
//                }
//            })
//        }
//
//    }
//
//}
