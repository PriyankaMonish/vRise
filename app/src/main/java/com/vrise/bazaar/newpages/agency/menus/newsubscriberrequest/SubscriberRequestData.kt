package com.vrise.bazaar.newpages.agency.menus.newsubscriberrequest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
import retrofit2.Call
import retrofit2.Callback

//class SubscriberRequestData(news: Application) : AndroidViewModel(news) {
//    private lateinit var dataItems: MutableLiveData<ArrayList<SublistItem>>
//    private val apiService = (news as BaseApp).oldService()
//    fun getRequestedSubcribers(request: Request): LiveData<ArrayList<SublistItem>> {
//        dataItems = MutableLiveData()
//        loadRequestedsubscribers(request)
//        return dataItems
//    }
//
//    private fun loadRequestedsubscribers(request: Request) {
//        apiService?.subscriberRequestList(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.data != null)
//                        if (response.body()!!.data!!.sublist != null)
//                            if (response.body()!!.data!!.sublist!!.isNotEmpty())
//                                dataItems.value = response.body()!!.data!!.sublist
//                            else
//                                dataItems.value = null
//                }
//            }
//        })
//
//    }
//
//
//}
