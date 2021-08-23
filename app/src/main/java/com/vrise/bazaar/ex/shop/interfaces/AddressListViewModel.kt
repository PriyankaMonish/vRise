package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class AddressListViewModel(private val repository: RepoLive?) : ViewModel() {
	fun getAddress(request: Request) = repository?.getAddress(request)
	fun setDeliveryAdd(request: Request) = repository?.setAddressDelivery(request)
	fun deleteDelAddress(request: Request) = repository?.deleteDeliveryAddress(request)
	fun plus(request: Request) = repository?.addAddress(request)
	fun getFromLocation(lat: Double, lng: Double, maxResult: Int) = repository?.getFrom(lat, lng, maxResult)

}
