package com.vrise.bazaar.newpages.agency.menus.sms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.submodels.SmsPacksItem

class SMS(news: Application) : AndroidViewModel(news) {
    private val apiService = (news as BaseApp).oldService()
    fun getSMS(requests: Request): MutableLiveData<Pair<String?, ArrayList<SmsPacksItem?>?>> {
        return SMSRepositories().loadData(requests, apiService)
    }
}