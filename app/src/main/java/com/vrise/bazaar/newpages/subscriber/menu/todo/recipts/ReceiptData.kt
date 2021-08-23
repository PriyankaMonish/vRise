package com.vrise.bazaar.newpages.subscriber.menu.todo.recipts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.ReceiptListItem
import retrofit2.Call
import retrofit2.Callback

class ReceiptData(news : Application) : AndroidViewModel(news) {
    private lateinit var dataItems: MutableLiveData<ArrayList<ReceiptListItem>>
private val apiService = (news as BaseApp).oldService()
    private fun loadReceipts(request: Request) {
        apiService?.receipts(request)?.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                dataItems.value = null
                if (hasData(response)) {
                    if (response!!.body()!!.data != null) {
                        if (response.body()!!.data!!.receiptList != null) {
                            if (response.body()!!.data!!.receiptList!!.isNotEmpty()) {
                                dataItems.value = response.body()!!.data!!.receiptList!!
                            }
                        }
                    }
                }
            }
        })
    }

    fun getReceipts(request: Request): LiveData<ArrayList<ReceiptListItem>> {
        dataItems = MutableLiveData()
        loadReceipts(request)
        return dataItems
    }
}