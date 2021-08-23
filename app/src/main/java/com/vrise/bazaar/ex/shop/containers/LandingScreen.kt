package com.vrise.bazaar.ex.shop.containers

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.SplashPageViewModel
import com.vrise.bazaar.ex.shop.pages.NotWorking
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.fullscreen
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Values
import com.vrise.bazaar.newpages.utilities.NetworkStateReceiver

import kotlinx.android.synthetic.main.splash_screen_layout.*

class LandingScreen : AppCompatActivity(), NetworkStateReceiver.NetworkStateReceiverListener {
	override fun networkAvailable() {
		checkVersion()
	}

	override fun networkUnavailable() {
		toast(container, getString(R.string.no_internet))
	}

	private lateinit var viewModel: SplashPageViewModel
	var networkStateReceiver = NetworkStateReceiver()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		fullscreen(this)
		setContentView(R.layout.splash_screen_layout)
		networkStateReceiver.addListener(this)
		(this.application as BaseApp).registerReceiver(
			networkStateReceiver,
			IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
		)
	}

	private fun checkVersion() {
		viewModel = ViewModelProviders.of(
			this, ViewModelFactory(
				RepoLive.getInstance(
					(this.application as BaseApp).apiService(), this@LandingScreen
				)
			)
		).get(SplashPageViewModel::class.java)

		Instances.InternetCheck { internet ->
			if (internet) {
				viewModel.getVersion(Request(app = Values.PAYTOALL))?.observe(this, Observer {
					if (it != null) {
						if (it.version.toString() == packageManager.getPackageInfo(packageName, 0).versionName.toString()) {
							navigateToNewPage()
						}
						else if (it.version.toString() == "0") {
							startActivity(Intent(this, NotWorking::class.java))
							finish()
						} else {
							Instances.displayUpGradeDialog(this@LandingScreen, it.version)
						}
					} else {
						toast(container, getString(R.string.no_server))
					}
				})
			} else {
				toast(container, getString(R.string.no_internet))
			}
		}
	}

	private fun navigateToNewPage() {
		if (Preference.get(this, Preference.DATAFILE, Preference.TOKEN).isNullOrBlank()) {
			/*Registration*/
			startActivity(Intent(this, RegistrationContainer::class.java))
			finish()
		} else {
			/*Main DashBoard*/
			startActivity(Intent(this, DashBoardContainer::class.java))
			finish()
		}

	}

	override fun onPause() {
		unregister()
		super.onPause()
	}

	override fun onDestroy() {
		unregister()
		super.onDestroy()
	}

	private fun unregister() {
		try {
			networkStateReceiver.removeListener(this)
			(this.application as BaseApp).unregisterReceiver(networkStateReceiver)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}
