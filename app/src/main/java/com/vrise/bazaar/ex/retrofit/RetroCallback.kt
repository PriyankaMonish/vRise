package com.vrise.bazaar.ex.retrofit

import com.vrise.bazaar.ex.model.mainmodels.Response
import com.vrise.bazaar.ex.util.Instances.hasSuccessData
import com.vrise.bazaar.ex.util.Interfaces
import retrofit2.Call
import retrofit2.Callback

open class RetroCallback(private val observer: Interfaces.Callback) : Callback<Response> {
    override fun onFailure(call: Call<Response>, t: Throwable) {
        observer.failed(t)
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        if (hasSuccessData(response)) {
            observer.success(response.body(), response.body()?.data, true)

            observer.additionalData(response.body()?.display, false,response.body())

        }
        else {

            observer.additionalData(response.body()?.display, response.body()?.data?.action == "login",response.body())

            observer.success(null, null, false)
        }
    }
}
