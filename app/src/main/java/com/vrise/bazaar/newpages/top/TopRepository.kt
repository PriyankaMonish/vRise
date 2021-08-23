package com.vrise.bazaar.newpages.top

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.newpages.top.temps.Callback
import com.vrise.bazaar.newpages.top.temps.Misc
import com.vrise.bazaar.newpages.top.temps.Misc.checkInternet
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data

class TopRepository(var apiService: ApiService?, var activity: FragmentActivity?) {

    fun getShoppingDashboard(utoken: String): LiveData<TopModel?> = getMutableShoppingDashboard(utoken)

    fun getBanner(request: Request): LiveData<ArrayList<String>?>? = getMutableBanner(request)

    fun newsGet(utocken: String?): LiveData<Data?> = newsMutableGet(utocken)

    fun addAddress(request: Request): LiveData<Boolean> = addMutableAddress(request)

    private fun addMutableAddress(request: Request): MutableLiveData<Boolean> {
        val dataVal = MutableLiveData<Boolean>()
        if (activity != null) {
            Misc.InternetCheck { internet ->
                if (Misc.checkInternet(activity, internet)) {
                    apiService?.addAddress(request)?.enqueue(object : Callback(object : Interfaces.Callback {
                        override fun additionalData(display: String?, logout: Boolean) {
                            Misc.additionalData(activity, display, logout)
                        }

                        override fun failed(t: Throwable) {
                            t.printStackTrace()
                            dataVal.value = false
                        }

                        override fun success(response: Response?, data: Data?, state: Boolean) {
                            dataVal.value = state
                        }
                    }) {})
                }
            }
        } else {
            dataVal.value = false
        }

        return dataVal
    }

    private fun newsMutableGet(utocken: String?): MutableLiveData<Data?> {
        Misc.println(utocken)
        val dataVal: MutableLiveData<Data?> = MutableLiveData()

        if (activity != null) {
            Misc.InternetCheck { internet ->
                if (Misc.checkInternet(activity, internet)) {
                    apiService?.news(Request(utoken = utocken))?.enqueue(Callback(object : Interfaces.Callback {
                        override fun additionalData(display: String?, logout: Boolean) {
                            Misc.additionalData(activity, display, logout)
                        }

                        override fun failed(t: Throwable) {
                            dataVal.value = null
                        }

                        override fun success(response: Response?, data: Data?, state: Boolean) {
                            if (state) {
                                dataVal.value = data
                            } else {
                                dataVal.value = null
                            }
                        }
                    }))
                }
            }
        } else {
            dataVal.value = null
        }

        return dataVal
    }

    private fun getMutableShoppingDashboard(utoken: String): MutableLiveData<TopModel?> {
        val topModel = MutableLiveData<TopModel?>()
        if (activity != null) {
            Misc.InternetCheck { internet ->
                if (Misc.checkInternet(activity, internet)) {
                    apiService?.getShoppingDashboard(
                        Request(
                            utoken = utoken
                        )
                    )?.enqueue(object : Callback(object : Interfaces.Callback {
                        override fun additionalData(display: String?, logout: Boolean) {
                            Misc.additionalData(activity, display, logout)
                        }

                        override fun failed(t: Throwable) {
                            topModel.value = null
                            t.printStackTrace()
                        }

                        override fun success(response: Response?, data: Data?, state: Boolean) {
                            if (state) {
                                if (data != null) {
                                    var deliveryAddress = ""
                                    var lat = ""
                                    var lng = ""
                                    if (data.deliveryAddress != null) {
                                        deliveryAddress = data.deliveryAddress.address.toString()
                                        lat = "${data.deliveryAddress.latitude}"
                                        lng = "${data.deliveryAddress.longitude}"
                                    }

                                    topModel.value = TopModel(
                                        uid = 0,
                                        noticicationCount = data.notfiCount,
                                        favouritesCount = data.favCount,
                                        cartCount = data.cartCount,
                                        location = deliveryAddress,
                                        lat = lat,
                                        lng = lng,
                                        pre_lan = data.pre_lan.toString(),
                                        bookCount = data.book_count
                                    )
                                } else {
                                    topModel.value = null
                                }
                            } else {
                                topModel.value = null
                            }

                        }
                    }) {})
                }
            }
        } else {
            topModel.value = null
        }

        return topModel
    }

    private fun getMutableBanner(request: Request): MutableLiveData<ArrayList<String>?> {
        val bannerList = MutableLiveData<ArrayList<String>?>()
        if (activity != null) {
            Misc.InternetCheck { internet ->
                if (checkInternet(activity, internet)) {
                    val bannerListTemp = MutableLiveData<ArrayList<String>?>()
                    apiService?.getDashboardData(request)?.enqueue(object : Callback(object : Interfaces.Callback {
                        override fun additionalData(display: String?, logout: Boolean) {
                            Misc.additionalData(activity, display, logout)
                        }

                        override fun failed(t: Throwable) {
                            bannerList.value = null
                            t.printStackTrace()
                        }

                        override fun success(response: Response?, data: Data?, state: Boolean) {
                            bannerList.value = null
                            if (state) {
                                bannerList.value = ArrayList()
                                bannerListTemp.value = ArrayList()
                                if (data != null) {
                                    if (data.banner != null) {
                                        if (data.banner.isNotEmpty()) {
                                            for (i in 0 until data.banner.size) {
                                                bannerListTemp.value?.add(data.banner[i].image.toString())
                                            }
                                            bannerList.value = bannerListTemp.value
                                        }
                                    }
                                }
                            }
                        }
                    }) {})
                }
            }
        } else {
            bannerList.value = null
        }
        return bannerList
    }

    companion object {

        @Volatile
        private var instance: TopRepository? = null

        fun getInstance(apiService: ApiService?, activity: FragmentActivity?) = instance
            ?: synchronized(this) {
                instance
                    ?: TopRepository(
                        apiService,
                        activity
                    ).also { instance = it }
            }

    }
}