package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class SplashPageViewModel(val repository : RepoLive?) : ViewModel(){
    fun getVersion(request: Request) = repository?.getVersion(request)
}