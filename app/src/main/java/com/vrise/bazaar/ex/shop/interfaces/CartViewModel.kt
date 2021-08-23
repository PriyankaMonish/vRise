package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class CartViewModel(private val repository : RepoLive?) : ViewModel() {
    fun getCartData(request: Request) = repository?.getCartData(request)
    fun update(request: Request) = repository?.cartUpdate(request)
    fun delete(request: Request) = repository?.removeFromCart(request)
    fun canplaceorder(nothing: Request) = repository?.canplaceorder(nothing)
    fun sale(request: Request) = repository?.sales(request)
    fun sidebar(request: Request) = repository?.getSideBar(request)
}
