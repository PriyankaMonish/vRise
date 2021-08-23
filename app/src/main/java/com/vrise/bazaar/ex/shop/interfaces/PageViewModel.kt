package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class PageViewModel(private val repository : RepoLive?) : ViewModel() {
	fun getCustCare(request: Request) = repository?.getCustNum(request)
	fun send(request : Request) = repository?.sendOtp(request)
	fun updateNo(request : Request) = repository?.updateNum(request)
}
