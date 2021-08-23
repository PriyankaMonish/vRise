package com.vrise.bazaar.newpages.top.temps

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasSuccessData
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import retrofit2.Call
import retrofit2.Callback

open class Callback(val observer: Interfaces.Callback) : Callback<Response> {
    override fun onFailure(call: Call<Response>, t: Throwable) {
        observer.failed(t)
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        if (hasSuccessData(response)) {
            observer.success(response.body(), response.body()?.data, true)

            observer.additionalData(response.body()?.display, false)

        } else {
            observer.additionalData(response.body()?.display, response.body()?.data?.action == "login")

            observer.success(null, null, false)
        }
    }
}
