package com.vrise.bazaar.newpages.utilities

import androidx.fragment.app.FragmentActivity
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import retrofit2.Call
import retrofit2.Callback

open class CallbackClient(val context: FragmentActivity?, val observer: Interfaces.Callback) :
    Callback<Response> {
    override fun onFailure(call: Call<Response>, t: Throwable) {
        if (context != null && !context.isFinishing) {
            observer.failed(t)
        }
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        if (context != null && !context.isFinishing) {
            if (hasData(context, response)) {
                observer.success(response.body(), response.body()?.data, true)
            } else {
                observer.success(null, null, false)
            }
        }
    }
}