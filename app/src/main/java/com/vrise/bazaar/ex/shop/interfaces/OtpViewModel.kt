package com.vrise.bazaar.ex.shop.interfaces

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.model.mainmodels.Request

class OtpViewModel(val repository: RepoLive?) : ViewModel(){
    fun resendOtp(request : Request) = repository?.sendOtp(request)
    fun redirectMe(request: Request) = repository?.redirect(request)
    fun updateNo(request: Request)  = repository?.updateNum(request)
}