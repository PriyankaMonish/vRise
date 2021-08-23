package com.vrise.bazaar.newpages.top

import androidx.lifecycle.ViewModel
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request

class NotificationsViewModel(val repository : NotificationsRepository) : ViewModel(){
    fun getShopNotifications(request: Request) = repository.getNotifications(request)
    fun viewnotification(request: Request) = repository.viewnotification(request)
    fun clearnotification(request: Request) = repository.clearnotification(request)
}
