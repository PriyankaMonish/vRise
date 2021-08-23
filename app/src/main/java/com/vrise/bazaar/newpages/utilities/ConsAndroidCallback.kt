package com.vrise.bazaar.newpages.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import retrofit2.Call
import retrofit2.Callback

open class ConsAndroidCallback(val context: FragmentActivity?, val param: Fragment?, val observer: Interfaces.Callback) :
    Callback<Response> {
    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        if (context != null && !context.isFinishing) {
            if (param != null) {
                if (param.isAdded) {
                    t?.let {
                        observer.failed(it)
                    }
                }
            } else {
                t?.let {
                    observer.failed(it)
                }
            }
        }
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        if (context != null && !context.isFinishing) {
            if (param != null) {
                if (param.isAdded) {
                    if (hasData(context, response)) {
                        observer.success(response!!.body(), response.body()?.data, true)
                    } else {
                        observer.success(null, null, false)
                    }
                }
            } else {
                if (hasData(context, response)) {
                    observer.success(response!!.body(), response.body()?.data, true)
                } else {
                    observer.success(null, null, false)
                }
            }
        }
    }
}