package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class OfferListViewModel(val repository: RepoLive?) : ViewModel() {
	fun getOffea(request: Request) = repository?.getOffa(request)
	fun checkCoupon(request: Request) = repository?.checkCoupon(request)
}
