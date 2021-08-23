package com.vrise.bazaar.newpages.utilities

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.retrofit.ApiService

//abstract class InitSub : Fragment(), NetworkStateReceiver.NetworkStateReceiverListener {
//	abstract fun initView()
//	abstract fun initModel()
//	abstract fun initControl()
//	var apiService: ApiService? = null
//	var networkStateReceiver = NetworkStateReceiver()
//
//	fun setInitializer() {
//
//		networkStateReceiver.addListener(this)
//		/*not deprecated*/
//		(activity?.application as BaseApp).registerReceiver(
//			networkStateReceiver, IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION)
//		)
//		initView()
//		initModel()
//		apiService = (activity?.application as BaseApp).oldService()
//		initControl()
//	}
//
//	override fun onPause() {
//		unregister()
//		super.onPause()
//	}
//
//	private fun unregister() {
//		try {
//			networkStateReceiver.removeListener(this)
//			(activity?.application as BaseApp).unregisterReceiver(networkStateReceiver)
//		} catch (e: Exception) {
//			e.printStackTrace()
//		}
//	}
//
//	override fun onDestroy() {
//		unregister()
//		super.onDestroy()
//	}
//}

abstract class InitMain : AppCompatActivity() {
	abstract fun initView()
	abstract fun initModel()
	abstract fun initControl()
	var apiService: ApiService? = null
	fun setInitializer() {
		initView()
		initModel()
		apiService = (this.application as BaseApp).oldService()
		initControl()
	}
}
