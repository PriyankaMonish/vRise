package com.vrise.bazaar.newpages.agency.menus.sms.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.databinding.SmsFragmentContainerBinding
import com.vrise.bazaar.newpages.agency.menus.sms.viewmodel.SMS
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
import com.vrise.bazaar.newpages.utilities.models.Requests
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.SmsPacksItem
import kotlinx.android.synthetic.main.app_bar_sub_container.*

//class SMSList : InitSub() {
//
//    private var viewModel: SMS? = null
//    private lateinit var binding: SmsFragmentContainerBinding
//
//    private var itemObserver = Observer<Pair<String?, ArrayList<SmsPacksItem?>?>> { t ->
//        if (t != null) {
//            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//            if (t.second!!.isNotEmpty()) {
//                setAdapter(t.first, t.second)
//            } else {
//                binding.textView178.visibility = View.GONE
//                binding.textView179.visibility = View.GONE
//                binding.recyclerView.adapter = EmptyList(activity!!, Requests(display = "No Packages Available"))
//                binding.progress.visibility = View.GONE
//            }
//        } else {
//            binding.textView178.visibility = View.GONE
//            binding.textView179.visibility = View.GONE
//            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//            binding.recyclerView.adapter = EmptyList(activity!!, Requests(display = "No Packages Available"))
//            binding.progress.visibility = View.GONE
//        }
//    }
//
//    private fun setAdapter(i: String?, t: ArrayList<SmsPacksItem?>?) {
//        val ia = i?.split(",")
//        binding.textView178.visibility = View.VISIBLE
//        binding.textView179.visibility = View.VISIBLE
//        binding.textView178.text = "No of Subscribers : ${ia!![1]}"
//        binding.textView179.text = "Remaining Credit : ${ia[0]}"
//        binding.recyclerView.adapter = SMSListAdapter(activity!!, t, object : Interfaces.ReturnId {
//            override fun returnId(string: String) {
//                toastit(activity!!, "string")
//            }
//        })
//        binding.progress.visibility = View.GONE
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
//        try {
//            activity!!.page_title.text = "SMS Packages"
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun networkAvailable() {
//        getDatas()
//    }
//
//    private fun getDatas() {
//        binding.progress.visibility = View.VISIBLE
//        viewModel = ViewModelProviders.of(this).get(SMS::class.java)
//        viewModel?.getSMS(Request(utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.observe(this, itemObserver)
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = SmsFragmentContainerBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//}

