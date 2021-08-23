package com.vrise.bazaar.newpages.agency.todo.bills

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
//import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
//import retrofit2.Call
//import retrofit2.Callback
//
//class BillData(news : Application) : AndroidViewModel(news) {
//    private lateinit var dataItems: MutableLiveData<List<BilllistItem?>>
//
//    fun getBills(request: Request): LiveData<List<BilllistItem?>> {
//        dataItems = MutableLiveData()
//        loadData(request)
//        return dataItems
//    }
//private val apiService = (news as BaseApp).oldService()
//    private fun loadData(request: Request) {
//        apiService?.getBillList(request)?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                t?.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (hasData(response)) {
//                    if (response!!.body()!!.message == SUCCESS) {
//                        if (response.body()!!.data != null) {
//                            if (response.body()!!.data!!.billlist!!.isNotEmpty()) {
//                                dataItems.value = response.body()!!.data!!.billlist
//                            }
//                        }
//                    } else {
//                        dataItems.value = null
//                    }
//                }
//            }
//        })
//    }
//}