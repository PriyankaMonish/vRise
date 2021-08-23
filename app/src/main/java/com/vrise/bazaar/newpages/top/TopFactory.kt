package com.vrise.bazaar.newpages.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vrise.bazaar.newpages.top.models.TopViewModel

class TopFactory(
    private val repository: TopRepository?
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TopViewModel(
        repository
    ) as T
}