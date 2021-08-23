package com.vrise.bazaar.ex.shop.pages.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.StatusData
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.Data
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class OrderShowViewModel(private val repos : RepoLive?) : ViewModel() {
    fun orderstatus(nothing: Request) : LiveData<StatusData?>?= orderstatus1(nothing)

    private fun orderstatus1(nothing: Request): LiveData<StatusData?>? = repos?.oStatus(nothing)
	fun cancelorder(nothing: Request) : LiveData<Data?>? = repos?.cancelorder(nothing)

}
