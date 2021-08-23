package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import java.util.*

class PayWalletViewModel(private val repository: RepoLive?) : ViewModel() {
	fun getHashs(postParam: HashMap<String, String>) = repository?.getHa(postParam)
	fun sale(request: Request) = repository?.addWallet(request)
}
