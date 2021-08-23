package com.vrise.bazaar.newpages.subscriber.dashboard
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.vrise.bazaar.ex.app.BaseApp
//import com.vrise.bazaar.newpages.utilities.Constants.FAILED
//import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import retrofit2.Call
//import retrofit2.Callback
//
//class DataList(news : Application) : AndroidViewModel(news) {
//
//    private lateinit var dashImageItems: MutableLiveData<Data>
//
//    fun getDashDatas(utocken: String): LiveData<Data> {
//        dashImageItems = MutableLiveData()
//        loadDashImageData(utocken)
//        return dashImageItems
//    }
//
//private val apiService = (news as BaseApp).oldService()
//    private fun loadDashImageData(utocken: String) {
//
//        println(Request(
//                utoken = utocken
//        ))
//        apiService?.getSubscriberDash(Request(
//                utoken = utocken
//        ))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.message == SUCCESS) {
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.banner != null) {
//                                if (response.body()!!.data!!.banner!!.isNotEmpty()) {
//                                    dashImageItems.value = response.body()!!.data
//                                }
//                            }
//                        }
//                    } else if (response.body()!!.message == FAILED) {
//                        if (response.body()!!.data!!.action != null) {
//                            if (response.body()!!.data!!.action == "profile") {
//                                dashImageItems.value = Data(
//                                        action = "profile"
//                                )
//                            } else {
//                                dashImageItems.value = Data()
//                            }
//                        } else {
//                            dashImageItems.value = Data()
//
//                        }
//                    } else {
//                        dashImageItems.value = Data()
//                    }
//                }
//            }
//        })
//    }
//
//}