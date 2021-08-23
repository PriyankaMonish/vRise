package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class MobileViewModel(val repository: RepoLive?) : ViewModel() {
    fun sendOtp(request : Request) = repository?.sendOtp(request)
}
