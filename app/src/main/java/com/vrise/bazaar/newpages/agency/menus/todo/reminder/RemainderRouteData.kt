package com.vrise.bazaar.newpages.agency.menus.todo.reminder
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.vrise.bazaar.ex.app.BaseApp
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.HelperMethods
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.ReminderRoutesItem
//import retrofit2.Call
//
//class RemainderRouteData(news : Application) : AndroidViewModel(news) {
//
//    private lateinit var dataItems: MutableLiveData<ArrayList<ReminderRoutesItem>>
//
//    fun getRemainderRoutes(utocken: String): LiveData<ArrayList<ReminderRoutesItem>> {
//        dataItems = MutableLiveData()
//        loadRoutedata(utocken)
//        return dataItems
//    }
//private val apiService = (news as BaseApp).oldService()
//    private fun loadRemainderdata(utocken: String) {
//        apiService?.newlyadded(Request(
//                utoken = utocken
//        ))?.enqueue(object : retrofit2.Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                dataItems.value = null
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (HelperMethods.hasData(response)) {
//                    if (response!!.body()!!.message != Constants.SUCCESS) {
//                        dataItems.value = null
//                    } else {
//                        dataItems.value = null
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.reminderRoutes != null) {
//                                if (response.body()!!.data!!.reminderRoutes!!.isNotEmpty()) {
//                                    dataItems.value = response.body()!!.data!!.reminderRoutes!!
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun loadRoutedata(utocken: String) {
//        println(Request(
//                utoken = utocken
//        ))
//        loadRemainderdata(utocken)
//    }
//
//
//}