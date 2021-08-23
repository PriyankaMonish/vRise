package com.vrise.bazaar.newpages.agency.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.newpages.agency.dashboard.model.Routes
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import retrofit2.Call

class Repository {

    private var data = MutableLiveData<Routes>()

    fun loadRoutedata(utocken: String, apiService: ApiService?): LiveData<Routes> {

        println(Request(
                utoken = utocken
        ))
        apiService?.getAgentDashBoard(Request(
                utoken = utocken
        ))?.enqueue(object : retrofit2.Callback<Response> {
            override fun onFailure(call: Call<Response>?, t: Throwable?) {

                data.value = Routes(
                        due = null,
                        list = null,
                        notificationCount = "0"
                )

                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                if (HelperMethods.hasData(response)) {
                    if (response!!.body()!!.message != Constants.SUCCESS) {
                        data.value = Routes(
                                due = null,
                                list = null,
                                notificationCount = "0"
                        )

                    } else {

                        data.value = Routes(
                                due = null,
                                list = null,
                                notificationCount = "0"
                        )

                        if (response.body()!!.data != null) {

                            data.value = Routes(
                                    due = response.body()?.data?.totalDues,
                                    list = null,
                                    notificationCount = "0"
                            )

                            if (response.body()!!.data!!.rootlist != null) {
                                if (response.body()!!.data!!.rootlist!!.isNotEmpty()) {
                                    data.value = Routes(
                                            due = response.body()!!.data!!.totalDues!!,
                                            list = response.body()!!.data!!.rootlist,
                                            notificationCount = response.body()!!.data!!.notifiCount.toString()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        })

        return data
    }

}