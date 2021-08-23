package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class ProfileViewModel(private val repository: RepoLive?) : ViewModel() {
    fun getProfile(request: Request) = repository?.getProfiles(request)
    fun getStates() = repository?.getAllState()
    fun getDistricts(request: Request) = repository?.getDistricts(request)
    fun profileUpdate(request: Request) = repository?.updateProfile(request)
}
