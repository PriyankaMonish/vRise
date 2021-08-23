package com.vrise.bazaar.ex.shop.containers

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.retrofit.RetroService

class RegistrationContainer : AppCompatActivity() {

    var apiService: RetroService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.nav_container)
        apiService = (this.application as BaseApp).apiService()
    }

    fun apiService() = apiService
}