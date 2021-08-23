package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ReferralViewModel(val repository: RepoLive?) : ViewModel() {
	fun getReferral(request: Request) = repository?.getReferral(request)
}
