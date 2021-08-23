package com.vrise.bazaar.newpages.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotificationsFactory(private val notificationsRepository: NotificationsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = NotificationsViewModel(
        notificationsRepository
    ) as T
}
