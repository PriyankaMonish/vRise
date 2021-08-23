package com.vrise.bazaar.ex.shop.pages.registration

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.RegistrationViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.NUMBER
import com.vrise.bazaar.ex.util.Preference.URLLINK
import kotlinx.android.synthetic.main.registration_fragment.*


class RegistrationFragment : Fragment(){
	private lateinit var viewModel: RegistrationViewModel
	companion object {
		var tocken: String? = ""
		var reflink:String? = ""
		var response:String? = ""

		private const val TAG = "Install Referrer"

	}
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.registration_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(
			this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
		).get(RegistrationViewModel::class.java)

		textInputEditText1.setText(Preference.get(activity, DATAFILE, NUMBER))
		reflink  = arguments?.getString(Constants.idlink) ?: ""
//		Toast.makeText(activity, reflink, Toast.LENGTH_SHORT).show()
		getToken()

		button.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				textView249.visibility = View.VISIBLE
				if (!textInputEditText.text.isNullOrBlank() && !textInputEditText1.text.isNullOrBlank()) {
					if (tocken.isNullOrBlank()) {
						textView249.visibility = View.GONE
						toast(activity, getString(R.string.no_deviceid))
					} else {
						Instances.InternetCheck { internet ->
							if (internet) {
								viewModel.signUp(
									Request(
										mobile = textInputEditText1.text.toString(),
										name = textInputEditText.text.toString(),
										device_id = tocken,
										ref_code = "",
										email = "",
										state = "",
										district = "",
										address = "",
										address2 = "",
										pincode = "",
										postOffice = "",
										sellerref = if ( reflink!=null &&  reflink!!.contains("vrise")) {
											arguments?.getString(Constants.idlink) ?: "if$reflink"

										} else {
											""
										}
									)
								)?.observe(viewLifecycleOwner, Observer {
									if (it != null) {

										Preference.put(activity, Preference.TOKEN, it.data?.userToken.toString(), DATAFILE)
										startActivity(Intent(activity, DashBoardContainer::class.java))
										activity?.finish()
									}
								})
							} else {
								toast(activity, getString(R.string.no_internet))
								textView249.visibility = View.GONE
							}
						}
					}
				} else {
					toast(activity, getString(R.string.fill_madatory_fields))
					textView249.visibility = View.GONE
				}

			}
		})
	}

	@SuppressLint("HardwareIds")
	private fun getToken() {
		activity?.let {
			if (tocken.isNullOrBlank() || tocken == "0") {
				val id: String = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
					tocken = id
					Instances.printAny("newToken $tocken")
			}
		}
	}



}
