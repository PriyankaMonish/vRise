package com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers

//import android.app.Application
//import androidx.lifecycle.*
//import com.vrise.bazaar.ex.app.BaseApp
//import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
//import retrofit2.Call
//import retrofit2.Callback
//
//
//class SubscriberData(news : Application) : AndroidViewModel(news) {
//    private lateinit var dataItems: MutableLiveData<ArrayList<SublistItem>>
//    fun getSubcribers(tockens: String, routeId: String, searchKey: String, limit: String, offset: String): LiveData<ArrayList<SublistItem>> {
//        dataItems = MutableLiveData()
//        loadSubcribers(tockens, routeId, searchKey, limit, offset)
//        return dataItems
//    }
//
//private val apiService = (news as BaseApp).oldService()
//    private fun loadSubcribers(tockens: String, routeId: String, searchKey: String, limit: String, offset: String) {
//        println(Request(
//                utoken = tockens,
//                routeId = routeId,
//                searchkey = searchKey,
//                limit = limit,
//                offset = offset
//        ))
//        apiService?.getSubscriberList(Request(
//                utoken = tockens,
//                routeId = routeId,
//                searchkey = searchKey,
//                limit = limit.toString(),
//                offset = offset.toString()
//        ))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.message != SUCCESS) {
//                        dataItems.value = null
//                    } else {
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.sublist != null) {
//                                if (response.body()!!.data!!.sublist!!.isNotEmpty()) {
//                                    dataItems.value = response.body()!!.data!!.sublist!!
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//
//
//    }
//}