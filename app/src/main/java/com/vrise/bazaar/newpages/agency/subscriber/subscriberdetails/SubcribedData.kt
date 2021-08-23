package com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.Subdata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubcribedData(news : Application) : AndroidViewModel(news) {
    private lateinit var dataItems: MutableLiveData<Subdata>

    fun getSubcription(request: Request): LiveData<Subdata> {
        dataItems = MutableLiveData()
        loadSubscribedLists(request)
        return dataItems
    }
private val apiService = (news as BaseApp).oldService()
    private fun loadSubscribedLists(request: Request) {
        apiService?.getSubscriberDetails(request)?.enqueue(object : Callback<com.vrise.bazaar.newpages.utilities.models.mainmodels.Response> {
            override fun onFailure(call: Call<com.vrise.bazaar.newpages.utilities.models.mainmodels.Response>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<com.vrise.bazaar.newpages.utilities.models.mainmodels.Response>?, response: Response<com.vrise.bazaar.newpages.utilities.models.mainmodels.Response>?) {
                dataItems.value = null
                if (hasData(response)) {
                    if (response!!.body()!!.message == SUCCESS) {
                        if (response.body()!!.data != null) {
                            if (response.body()!!.data!!.subdata != null) {
                                dataItems.value = response.body()!!.data!!.subdata
                            }
                        }
                    } else {
                        dataItems.value = null
                    }
                }
            }
        })

    }
}