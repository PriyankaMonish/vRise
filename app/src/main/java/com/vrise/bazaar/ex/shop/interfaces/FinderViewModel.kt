package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class FinderViewModel(private val repository: RepoLive?) : ViewModel() {
	fun getShops(request: Request) = repository?.searchShops(request)
	fun getCatos(request: Request) = repository?.searchItems(request)
	fun getShopDetails(request: Request) = repository?.getSearchShops(request)

}
