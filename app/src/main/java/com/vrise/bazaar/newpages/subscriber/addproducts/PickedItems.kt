package com.vrise.bazaar.newpages.subscriber.addproducts

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.vrise.bazaar.ex.app.BaseApp
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import retrofit2.Call
//import retrofit2.Callback

//class PickedItems(news : Application) : AndroidViewModel(news) {
//
//    lateinit var item: MutableLiveData<ArrayList<PrdlistItem?>?>
//
//    fun getProducts(request: Request): LiveData<ArrayList<PrdlistItem?>?> {
//        item = MutableLiveData()
//        loadPickableItems(request)
//        return item
//    }
//private val apiService = (news as BaseApp).oldService()
//    private fun loadPickableItems(request: Request) {
//        print(request)
//        apiService?.getSubscriberProducts(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.data != null) {
//                        if (response.body()!!.data!!.prdlist != null) {
//                            item.value = response.body()!!.data!!.prdlist
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//}