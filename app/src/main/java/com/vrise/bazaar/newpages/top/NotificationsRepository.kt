package com.vrise.bazaar.newpages.top

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.newpages.top.temps.Callback
import com.vrise.bazaar.newpages.top.temps.Misc
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.NotificationsItem

class NotificationsRepository(val apiService: ApiService?, val activity: FragmentActivity?) {

    fun viewnotification(request: Request): LiveData<Boolean> {
        val boolean = MutableLiveData<Boolean>()
        Misc.println(request)
        apiService?.viewnotification(request)?.enqueue(object : Callback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {
                Misc.additionalData(activity, display, logout)
            }

            override fun failed(t: Throwable) {
                boolean.value = false
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                if (state) {
                    boolean.value = true
                }
            }

        }) {})

        return boolean
    }

    fun getNotifications(request: Request): LiveData<List<NotificationsItem?>?> = getShopNotifications(request)

    private fun getShopNotifications(request: Request): MutableLiveData<List<NotificationsItem?>?> {
        val notifications = MutableLiveData<List<NotificationsItem?>?>()
        Misc.println(request)
        apiService?.getShopNotifications(request)?.enqueue(object : Callback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {
                Misc.additionalData(activity, display, logout)
            }

            override fun failed(t: Throwable) {
                notifications.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                notifications.value = null
                if (state) {
                    if (data != null) {
                        if (data.notifications.isNullOrEmpty()) {

                        } else {
                            notifications.value = data.notifications
                        }
                    }
                }
            }
        }) {})

        return notifications
    }

    fun clearnotification(request: Request): LiveData<Boolean> {
        val boolean = MutableLiveData<Boolean>()
        Misc.println(request)
        apiService?.clearnotification(request)?.enqueue(object : Callback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {
                Misc.additionalData(activity, display, logout)
            }

            override fun failed(t: Throwable) {
                boolean.value = false
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                if (state) {
                    boolean.value = true
                }
            }
        }) {})

        return boolean
    }

}
