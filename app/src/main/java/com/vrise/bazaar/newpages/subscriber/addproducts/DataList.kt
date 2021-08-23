package com.vrise.bazaar.newpages.subscriber.addproducts

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.vrise.bazaar.ex.app.BaseApp
//import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.ProductListItem
//import retrofit2.Call
//import retrofit2.Callback

//class DataList(news : Application) : AndroidViewModel(news) {
//
//    private lateinit var dashImageItems: MutableLiveData<ArrayList<ProductListItem?>>
//
//    fun getDashDatas(request: Request): LiveData<ArrayList<ProductListItem?>> {
//        dashImageItems = MutableLiveData()
//        loadDashImageData(request)
//        return dashImageItems
//    }
//
//    private val apiService = (news as BaseApp).oldService()
//    private fun loadDashImageData(request: Request) {
//
//        apiService?.getSubscriberDash(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.message == SUCCESS) {
//                        if (response.body()!!.data != null) {
//                           if (response.body()!!.data!!.productList != null){
//                               dashImageItems.value = response.body()!!.data!!.productList
//                           }
//                        }
//                    } else {
//                        dashImageItems.value = null
//                    }
//                }
//            }
//        })
//    }
//
//}