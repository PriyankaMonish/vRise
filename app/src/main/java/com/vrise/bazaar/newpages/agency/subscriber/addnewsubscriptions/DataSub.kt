package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.google.gson.GsonBuilder
//import com.vrise.bazaar.ex.app.BaseApp
//import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS
//import com.vrise.bazaar.newpages.utilities.HelperMethods
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import retrofit2.Call
//import retrofit2.Callback
//
//class DataSub(news : Application) : AndroidViewModel(news) {
//    private lateinit var dataItems: MutableLiveData<ArrayList<PrdlistItem?>>
//
//    fun getPrice(request: Request): LiveData<ArrayList<PrdlistItem?>> {
//        dataItems = MutableLiveData()
//        loadPrice(request)
//        return dataItems
//    }
//private val apiService = (news as BaseApp).oldService()
//    private fun loadPrice(request: Request) {
//        print(GsonBuilder().setPrettyPrinting().create().toJson(request))
//        apiService?.getProducts(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (HelperMethods.hasData(response)) {
//                    if (response!!.body()!!.message == SUCCESS) {
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.prdlist != null) {
//                                if (response.body()!!.data!!.prdlist!!.isNotEmpty()) {
//                                    dataItems.value = response.body()!!.data!!.prdlist!!
//                                    //.sortedWith(compareBy { it!!.agentStatus == "1" })
//                                }
//                            }
//                        }
//                    } else {
//                        dataItems.value = null
//                    }
//                }
//            }
//        })
//
//    }
//}