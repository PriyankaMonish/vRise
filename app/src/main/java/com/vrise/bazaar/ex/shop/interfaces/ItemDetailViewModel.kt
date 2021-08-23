package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ItemDetailViewModel(private val repository : RepoLive?) : ViewModel() {
    fun getItem(request: Request) = repository?.getItemDetail(request)
    fun getItemList(request: Request) = repository?.getItemList(request)
    fun getSlotsData(request: Request) = repository?.getSlotsData(request)
	fun setFavourite(request: Request) = repository?.setFavs(request)
	fun addItem(request: Request)  = repository?.addItem(request)
    fun Ships(request: Request)  = repository?.ShipsItem(request)
}
