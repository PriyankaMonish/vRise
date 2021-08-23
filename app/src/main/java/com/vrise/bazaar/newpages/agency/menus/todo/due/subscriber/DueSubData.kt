package com.vrise.bazaar.newpages.agency.menus.todo.due.subscriber

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.SubduelistItem
import retrofit2.Call
import retrofit2.Callback

//class DueSubData(news : Application) : AndroidViewModel(news) {
//
//    private lateinit var dataItems: MutableLiveData<ArrayList<SubduelistItem>>
//private val apiService = (news as BaseApp).oldService()
//    fun getDues(request: Request): LiveData<ArrayList<SubduelistItem>> {
//        dataItems = MutableLiveData()
//        loadDues(request)
//        return dataItems
//    }
//
//    private fun loadDues(request: Request) {
//        apiService?.subscriberduelist(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                dataItems.value = null
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    dataItems.value = null
//                    if (response!!.body()!!.data != null) {
//                        if (response.body()!!.data!!.subduelist != null) {
//                            if (!response.body()!!.data!!.subduelist!!.isEmpty()) {
//                                dataItems.value = response.body()!!.data!!.subduelist!!
//                            }
//                        }
//                    }
//                } else {
//                    dataItems.value = null
//                }
//            }
//        })
//
//    }
//}