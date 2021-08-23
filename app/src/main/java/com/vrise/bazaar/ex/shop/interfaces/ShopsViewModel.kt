package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ShopsViewModel(private val repository: RepoLive?) : ViewModel() {
    fun shoList(request: Request) = repository?.getS(request)
    fun getShops(request: Request) = repository?.searchShops(request)
}
