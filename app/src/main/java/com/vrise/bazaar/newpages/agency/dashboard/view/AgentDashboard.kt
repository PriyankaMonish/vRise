package com.vrise.bazaar.newpages.agency.dashboard.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.databinding.FragmentAgentDashBinding
import com.vrise.bazaar.newpages.agency.dashboard.model.Routes
import com.vrise.bazaar.newpages.agency.dashboard.viewmodel.RouteData
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
import com.vrise.bazaar.newpages.utilities.Constants.Rem
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import com.vrise.bazaar.newpages.utilities.models.submodels.RootlistItem
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import android.os.Parcelable
import java.util.*

//
//class AgentDashboard : InitSub() {
//
//    private var viewModel: RouteData? = null
//    private lateinit var binding: FragmentAgentDashBinding
//    var state: Parcelable? = null
//    override fun networkAvailable() {
//
//    }
//
//    override fun onPause() {
//        // Save ListView state @ onPause
//        state = binding.recyclerView5.layoutManager?.onSaveInstanceState()
//        super.onPause()
//    }
//
//    override fun networkUnavailable() {
//        toast(activity, getString(R.string.no_internet))
//    }
//
//    private val itemObserver = Observer<Routes> { t ->
//        binding.progressBar9.visibility = View.GONE
//        binding.recyclerView5.layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView5.adapter = EmptyList(activity!!, null)
//
//        if (t != null) {
//
//            if (t.due != null) {
//                binding.textView40.text = "Rs ${t.due}"
//            }
//
//            if (t.list != null) {
//                binding.noDatas.visibility = View.GONE
//                setAdapter(t.list)
//            } else {
//                binding.noDatas.visibility = View.VISIBLE
//            }
//
//            com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Rem, t.notificationCount, DATAFILE)
//
//            setNotificationCount()
//        }
//    }
//
//    private fun setNotificationCount() {
//        activity?.invalidateOptionsMenu()
//    }
//
//    private fun setAdapter(t: ArrayList<RootlistItem>) {
//        binding.recyclerView5.adapter = RouteList(activity!!, t)
//        if (state != null) {
//            binding.recyclerView5.layoutManager?.onRestoreInstanceState(state)
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
//        activity?.page_tiitle?.text = getString(R.string.dashboard)
//        binding.progressBar9.visibility = View.VISIBLE
//        binding.floatingActionButton2.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                navigateTo(activity!!, FRAGMENT, true, AddRoute(), null, null, null, "")
//            }
//        })
//
//        if (hasConnection(activity!!)) {
//            getRouteItems()
//        }
//
//        binding.swiperefresh.isRefreshing = false
//        binding.swiperefresh.isEnabled = false
//        binding.recyclerView5.layoutManager = LinearLayoutManager(activity)
//
//    }
//
//    private fun getRouteItems() {
//        binding.progressBar9.visibility = View.VISIBLE
//        viewModel = ViewModelProviders.of(this).get(RouteData::class.java)
//        viewModel?.getRouteItems(com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID))?.observe(this, itemObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentAgentDashBinding.inflate(inflater, container, false) //R.layout.fragment_agent_dash
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//    override fun onResume() {
//        try {
//            if (activity != null && isAdded) {
//                setNotificationCount()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        super.onResume()
//    }
//}
