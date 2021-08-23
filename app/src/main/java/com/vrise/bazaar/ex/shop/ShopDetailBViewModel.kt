package com.vrise.bazaar.ex.shop

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.Data
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ShopDetailBViewModel(private var repo: RepoLive?) : ViewModel() {
//    fun getDetail(request: Request): LiveData<Data?>? = repo?.getShopDetails(request) {}

        var filterData = MutableLiveData<Data?>()
    fun applyFilter(request: Request) {
        repo?.getShopDetails(request) {
            filterData.value = it
        }
    }
}
