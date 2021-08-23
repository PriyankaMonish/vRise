package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel;
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.model.mainmodels.Request

class RegistrationViewModel(val repository: RepoLive?) : ViewModel() {
    fun signUp(request: Request) = repository?.signmeUp(request)
    fun addTaag(request: Request) = repository?.AddTag(request)
}
