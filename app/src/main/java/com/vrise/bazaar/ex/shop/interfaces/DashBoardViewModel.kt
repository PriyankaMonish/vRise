package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class DashBoardViewModel(private val repository: RepoLive?) : ViewModel() {
    fun getAddress(request: Request) = repository?.getAddress(request)
    fun getDashboardItems(request: Request) = repository?.getDashBoardItems(request)
    fun news(request: Request) = repository?.privateNews(request)
}
