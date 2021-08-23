package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import java.util.*

class OutViewModel(private val repository : RepoLive?) : ViewModel() {
	fun getcartData(request: Request) = repository?.getCartData(request)
	fun sale(request: Request) = repository?.sales(request)
	fun sellerOrder(request: Request) = repository?.myOrder(request)

	fun checkCoupon(request: Request) = repository?.checkCoupon(request)
	fun getHashs(postParam: HashMap<String, String>) = repository?.getHa(postParam)
}
