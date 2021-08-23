package com.vrise.bazaar.ex.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vrise.bazaar.ex.retrofit.PayEnvironment
import com.vrise.bazaar.ex.retrofit.RetroClient
import com.vrise.bazaar.ex.retrofit.RetroService
import com.vrise.bazaar.newpages.retrofit.ApiService



class BaseApp : MultiDexApplication() {

	private var appEnvironment: PayEnvironment? = null
	private lateinit var appService: RetroService
	private lateinit var apiService: ApiService

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		MultiDex.install(this)
	}

	override fun onCreate() {
		super.onCreate()
		/**
		 * Todo Change when uploading to store PayEnvironment.LIVE PayEnvironment.STAGING
		 */
		appEnvironment = PayEnvironment.STAGING
		apiService  = RetroClient.getInstance(applicationContext).getClient().create(ApiService::class.java)
		appService  = RetroClient.getInstance(applicationContext).getClient().create(RetroService::class.java)
		appInstance = this
	}

	/**
	 * App environment either live or staging
	 */
	fun getAppEnvironment(): PayEnvironment? {
		return appEnvironment
	}

	/**
	 * Api Service of module 'newpages'
	 */
	fun oldService(): ApiService {
		return apiService
	}

	/**
	 * Api Service of module 'ex'
	 */
	fun apiService(): RetroService {
		return appService
	}

	companion object {
		@get : Synchronized
		var appInstance: BaseApp? = null
		var globalVariable: String? = null
		var categoryGlobal :String? = null
		var enableGlobal :Int? = null
		var urlLink:String? = null
		var ordId:String? = null
	}


}