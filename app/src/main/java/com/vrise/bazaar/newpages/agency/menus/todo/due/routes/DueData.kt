package com.vrise.bazaar.newpages.agency.menus.todo.due.routes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.utilities.Constants

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.DuelistItem
import retrofit2.Call
import retrofit2.Callback

//class DueData(news: Application) : AndroidViewModel(news) {
//    private lateinit var dataItems: MutableLiveData<ArrayList<DuelistItem>>
//    private val apiService = (news as BaseApp).oldService()
//
//    fun getDues(request: Request): LiveData<ArrayList<DuelistItem>> {
//        dataItems = MutableLiveData()
//        loadDues(request)
//        return dataItems
//    }
//
//    private fun loadDues(request: Request) {
//        apiService?.duelist(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                dataItems.value = null
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.message == Constants.SUCCESS) {
//                        dataItems.value = null
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.duelist != null) {
//                                if (!response.body()!!.data!!.duelist!!.isEmpty()) {
//                                    dataItems.value = response.body()!!.data!!.duelist!!
//                                }
//                            }
//                        }
//                    } else {
//                        dataItems.value = null
//                    }
//                } else {
//                    dataItems.value = null
//                }
//            }
//        })
//    }
//}