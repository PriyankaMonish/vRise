package com.vrise.bazaar.newpages.agency.menus.todo.reminder
//
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.EmptyList
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.models.Requests
//import com.vrise.bazaar.newpages.utilities.models.submodels.ReminderRoutesItem
//import kotlinx.android.synthetic.main.fragment_remainder.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//
//class Remainder : InitSub() {
//
//    override fun networkAvailable() {
//        getRouteItems()
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    private var viewModel: RemainderRouteData? = null
//    private val itemObserver = Observer<ArrayList<ReminderRoutesItem>> { t ->
//        if (t != null) {
//            setAdapter(t)
//        } else {
//            recyclerView10.layoutManager = LinearLayoutManager(activity)
//            recyclerView10.adapter = EmptyList(activity!!, Requests(
//                    display = "No Reminders",
//                    id = R.drawable.ic_noreminder
//            ))
//        }
//        progressBar13.visibility = View.GONE
//    }
//
//    private fun setAdapter(t: List<ReminderRoutesItem>?) {
//        recyclerView10.layoutManager = LinearLayoutManager(activity)
//        recyclerView10.adapter = RouteList(activity!!, t)
//    }
//
//    override fun initView() {
//        progressBar13.visibility = View.VISIBLE
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        activity!!.page_title.text = "Reminders" //for Newly Added Subscriber
//        getRouteItems()
//    }
//
//    private fun getRouteItems() {
//        progressBar13.visibility = View.VISIBLE
//        viewModel = ViewModelProviders.of(activity!!).get(RemainderRouteData::class.java)
//        viewModel!!.getRemainderRoutes(com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID)).observe(this, itemObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_remainder, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}
