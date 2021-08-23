package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class WalletViewModel(private val repository : RepoLive?) : ViewModel() {
    fun getWalletDetails(request: Request) = repository?.getWallet(request)
}
