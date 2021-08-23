package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class OrdersViewModel(private val repository : RepoLive?) : ViewModel() {
	fun getItems(request: Request)  = repository?.getMyOrder(request)
	fun getEdit(request: Request)  = repository?.edit(request)
	fun getReturn(request: Request)  = repository?.returnEditedProduct(request)
	fun getCancel(request: Request)  = repository?.cancel(request)
	fun getConfrml(request: Request)  = repository?.confirm(request)
	fun getrequest(request: Request)  = repository?.reqstOrder(request)
	fun getSaleModel(request: Request)  = repository?.saleModel(request)
	fun rateIt(request: Request) = repository?.rateApp(request)
	fun reorder(request: Request) = repository?.reorder(request)
}
