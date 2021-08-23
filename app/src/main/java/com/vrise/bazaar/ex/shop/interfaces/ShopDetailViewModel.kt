package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.Data
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ShopDetailViewModel(private val repository : RepoLive?) : ViewModel() {
//    fun getShopDetail(request: Request) = repository?.getShopDetails(request) {}


    var filterData = MutableLiveData<Data?>()
    fun applyFilter(request: Request) {
        repository?.getShopDetails(request) {
            filterData.value = it
        }
    }
}
