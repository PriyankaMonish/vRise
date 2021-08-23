package com.vrise.bazaar.newpages.agency.menus.sms.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.newpages.retrofit.ApiService

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.SmsPacksItem
import retrofit2.Call
import retrofit2.Callback

class SMSRepositories {
    private var data = MutableLiveData<Pair<String?, ArrayList<SmsPacksItem?>?>>()

    fun loadData(requests: Request, apiService: ApiService?): MutableLiveData<Pair<String?, ArrayList<SmsPacksItem?>?>> {
        var array: Pair<String?, ArrayList<SmsPacksItem?>?>
        apiService?.smsPacks(requests)?.enqueue(
                object : Callback<Response> {
                    override fun onFailure(call: Call<Response>, t: Throwable) {
                        data.value = null
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                        if (hasData(response)) {
                            if (response.body()!!.data != null) {
                                if (response.body()!!.data?.smsPacks != null) {
                                    val f = response.body()!!.data?.smsCredit + "," + response.body()!!.data?.subscriberCount
                                    val s = response.body()!!.data?.smsPacks
                                    array = Pair(f, s)
                                    data.value = array
                                }
                            }
                        } else {
                            data.value = null
                        }
                    }
                }
        )
        return data
    }
}