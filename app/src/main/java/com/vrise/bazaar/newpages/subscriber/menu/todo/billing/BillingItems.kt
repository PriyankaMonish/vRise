package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.utilities.Constants.FAILED
import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
import retrofit2.Call
import retrofit2.Callback

class BillingItems(news : Application) : AndroidViewModel(news) {

    private lateinit var billingItems: MutableLiveData<List<BilllistItem?>>
    private lateinit var dueItem: String
    private lateinit var pai: MutableLiveData<Pair<String, List<BilllistItem?>>>

    fun getBillingItems(request: Request): LiveData<Pair<String, List<BilllistItem?>>> {
        billingItems = MutableLiveData()
        pai = MutableLiveData()
        loadBillingItems(request)
        return pai
    }

    fun getDue(): String {
        return dueItem
    }
private val apiService = (news as BaseApp).oldService()
    private fun loadBillingItems(request: Request) {
        apiService?.subsBillList(request)?.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                if (hasData(response)) {
                    if (response!!.body()!!.message == SUCCESS) {
                        if (response.body()!!.data != null) {
                            billingItems.value = response.body()!!.data!!.billlist
                            dueItem = response.body()!!.data!!.dueAmount.toString()
                            pai.value = Pair(getDue(), billingItems.value!!)
                        }
                    } else if (response.body()!!.message == FAILED) {
                        billingItems.value = null
                        dueItem = 0.toString()
                        pai.value = null
                    }
                }
            }
        })
    }
}