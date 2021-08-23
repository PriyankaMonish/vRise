package com.vrise.bazaar.newpages.subscriber.menu.todo.recipts

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
import com.vrise.bazaar.newpages.utilities.models.Requests
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.ReceiptListItem
import com.vrise.bazaar.newpages.utilities.models.submodels.ReceiptidsItem
import kotlinx.android.synthetic.main.app_bar_sub_container.*
import kotlinx.android.synthetic.main.fragment_receipts.*


//class Receipts : InitSub() {
//    private var arraySize = 0
//    private var receiptLists: ArrayList<ReceiptidsItem>? = null
//
//    private var downloaded: BroadcastReceiver? = null
//    override fun networkAvailable() {
//        getData()
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    private var viewModelReceipts: ReceiptData? = null
//    private lateinit var list: ArrayList<String>
//    private val receiptObserver = Observer<ArrayList<ReceiptListItem>> { t ->
//        if (t != null) {
//            progressBar.visibility = View.GONE
//            list = ArrayList()
//            if (t.isNotEmpty())
//                for (i in 0 until t.size) {
//                    if (t[i].billLink != null) {
//                        if (t[i].billLink!!.isNotBlank()) {
//                            val http = t[i].billLink.toString().substring(0, 7)
//                            val www = "www."
//                            val baki = t[i].billLink.toString().substring(7, t[i].billLink.toString().length)
//                            val mail = "$http$www$baki"
//                            list.add(mail)
//                        }
//                    }
//                }
//            setadapter(t)
//        } else {
//            arraySize = 0
//            progressBar.visibility = View.GONE
//            recyclerView16.layoutManager = LinearLayoutManager(activity)
//            recyclerView16.adapter = EmptyList(activity!!, Requests(
//                    id = R.drawable.ic_no_receipts,
//                    display = "No Receipts"
//            ))
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
//    fun setadapter(t: ArrayList<ReceiptListItem>?) {
//        if (t != null) {
//            arraySize = 1
//            receiptLists = ArrayList()
//            if (t.isNotEmpty())
//                for (i in 0 until t.size) {
//                    receiptLists!!.add(ReceiptidsItem(t[i].id))
//                }
//        }
//
//        recyclerView16.layoutManager = LinearLayoutManager(activity)
//        recyclerView16.adapter = RecepitsList(activity!!, t, apiService)
//    }
//
//
//    override fun initControl() {
//        activity!!.page_title.text = "Receipts"
//        progressBar.visibility = View.VISIBLE
//
//        textView110.setOnClickListener {
//            if (arraySize > 0) {
//                progressBar22.visibility = View.VISIBLE
//                /*Handler().postDelayed(
//                        {
//                            progressBar22.visibility = View.GONE
//                        }, 1000
//                )*/
//
//                println(Request(
//                        utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                        receiptids = receiptLists
//                ))
//                apiService?.mailreceipts(Request(
//                        utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                        receiptids = receiptLists
//                ))?.enqueue(object : ConsAndroidCallback(activity, null, object : Interfaces.Callback {
//                    override fun additionalData(display: String?, logout: Boolean) {
//
//                    }
//
//                    override fun success(response: Response?, data: Data?, state: Boolean) {
//                        if (state) {
//                        }
//                        progressBar22.visibility = View.GONE
//                    }
//
//                    override fun failed(t: Throwable) {
//                        progressBar22.visibility = View.GONE
//                        t.printStackTrace()
//                    }
//                }) {})
//            } else {
//                toastit(activity!!, "No items to sent")
//            }
//        }
//
//        textView119.setOnClickListener {
//            if (arraySize > 0) {
//                progressBar26.visibility = View.VISIBLE
//                try {
//                    if (list != null) {
//                        if (list.isNotEmpty()) {
//                            textView119.text = "Downloading."
//
//                            /*val data = Data.Builder()
//                                    .putStringArray(DownloaserWorker.URL, list.toTypedArray())
//                                    .build()
//
//                            val simpleRequest = OneTimeWorkRequest.Builder(DownloaserWorker::class.java)
//                                    .setInputData(data)
//                                    .build()
//                            WorkManager.getInstance()?.enqueue(simpleRequest);*/
//
//                            val service = Intent(activity, com.vrise.bazaar.newpages.utilities.DownloadService()::class.java)
//                            val bundle = Bundle()
//                            bundle.putStringArrayList(Constants.ID, list)
//                            service.putExtras(bundle)
//                            activity!!.startService(service)
//
//                            progressBar26.visibility = View.GONE
//                        } else {
//                            progressBar26.visibility = View.GONE
//                        }
//                    } else {
//                        progressBar26.visibility = View.GONE
//                    }
//                } catch (e: Exception) {
//                    progressBar26.visibility = View.GONE
//                    e.printStackTrace()
//                }
//            } else {
//                toastit(activity!!, "No item to download")
//            }
//        }
//
//        downloaded = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                if (intent?.action == "MESSAGE_PROGRESS") {
//                    textView119.setText("Download")
//                }
//            }
//
//        }
//    }
//
//
//    override fun onResume() {
//        if (activity != null && downloaded != null) {
//            val intentFilter = IntentFilter("MESSAGE_PROGRESS")
//            LocalBroadcastManager.getInstance(activity!!).registerReceiver(downloaded!!, intentFilter)
//        }
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        if (activity != null && downloaded != null) {
//            LocalBroadcastManager.getInstance(activity!!).unregisterReceiver(downloaded!!)
//        }
//    }
//
//    private fun getData() {
//        viewModelReceipts = ViewModelProviders.of(activity!!).get(ReceiptData::class.java)
//        viewModelReceipts!!.getReceipts(Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                month = ""
//        )).observe(this, receiptObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_receipts, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//    /*override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater!!.inflate(R.menu.item_filter, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item!!.itemId) {
//            R.id.menu_notifications -> true
//            R.id.filter_menu -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }*/
//
//}
