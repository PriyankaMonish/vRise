package com.vrise.bazaar.newpages.agency.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vrise.bazaar.newpages.agency.dashboard.model.Routes
import com.vrise.bazaar.ex.app.BaseApp

class RouteData(application: Application) : AndroidViewModel(application) {
    val apiService = (application as BaseApp).oldService()
    private val repository : Repository = Repository()

    fun getRouteItems(utocken: String): MutableLiveData<Routes> {
        return repository.loadRoutedata(utocken, apiService) as MutableLiveData<Routes>
    }

}

