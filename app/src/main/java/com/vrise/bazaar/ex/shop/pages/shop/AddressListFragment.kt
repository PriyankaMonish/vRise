package com.vrise.bazaar.ex.shop.pages.shop

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.vrise.BuildConfig.APPLICATION_ID
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.temp.AddressItem
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.PlaceContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.AddressListViewModel
import com.vrise.bazaar.ex.shop.pages.main.DashBoardFragment
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.snack
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.LOC
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.newpages.containers.NotificationContainer
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.android.synthetic.main.address_list_fragment.*
import kotlinx.android.synthetic.main.item_progress_sheet.*

@SuppressLint("ByteOrderMark")
class AddressListFragment : Fragment() {
	private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
	private lateinit var viewModel: AddressListViewModel

	var lati: Double = 0.0
	var long: Double = 0.0
	var addresses = ""
	private lateinit var fusedLocationClient: FusedLocationProviderClient


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.address_list_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		makeRequest()
	}

	@SuppressLint("SetTextI18n")
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		progress?.visibility = View.VISIBLE


		viewModel = ViewModelProviders.of(
			this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
		).get(AddressListViewModel::class.java)
	}

	private fun initData() {
		progress?.visibility = View.VISIBLE
		activity?.textView58?.text = Preference.get(activity, DATAFILE, LOC)
		activity?.imageView19?.setOnClickListener {
			val intent = Intent(activity, DashBoardContainer::class.java)
			startActivity(intent)
		}
		if (Instances.isGpsEnabled(activity)) {
			startFusedLocation()
		} else {
			startGps()
		}
		getData()
		textView100.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				Instances.InternetCheck { internet ->
					if (internet) {
						if (Instances.isGpsEnabled(activity)) {
							if (Instances.hasPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

								findNavController().navigate( R.id.action_addressListFragment_to_addPlaces
								)
							}
							else {
								if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

                                    setupPermissions()

								} else {
									startLocationPermissionRequest(requireActivity())
								}
							}
						} else {
							startGps()
						}
					} else {
						toast(activity, "check network connection")
					}
				}
			}
		})
	}

	private fun startGps() {
		activity?.let { context ->
			val builder = androidx.appcompat.app.AlertDialog.Builder(context).setTitle("Enable Gps")
				.setCancelable(false).setMessage("Please enable your phone's location")
				.setPositiveButton("Continue") { _, _ ->
					startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
				}.setNegativeButton("Cancel") { dialog, _ ->
					dialog.dismiss()
					activity?.finish()
				}
			val dialog = builder.create()
			dialog.show()
		}
	}

	override fun onResume() {
		super.onResume()
		initData()
	}

	private fun isLocationEnabled(context: Context): Boolean {
			val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
			return LocationManagerCompat.isLocationEnabled(locationManager)
		}


	private fun startFusedLocation() {
		activity?.let {
			fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
			if (Instances.hasPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)) {
				getLastLocation(it)
			} else {
				if (!ActivityCompat.shouldShowRequestPermissionRationale(it, Manifest.permission.ACCESS_FINE_LOCATION)) {
					setupPermissions()
				} else {
					startLocationPermissionRequest(it)
				}
			}
		}
	}



	private fun getLastLocation(activity: FragmentActivity) {
		fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
			if (task.isSuccessful && task.result != null) {
				task.result?.longitude?.let { lon ->
					task.result?.latitude?.let { lat ->
						lati = lat
						long = lon
					}
				}
			} else {
			}
		}
	}
	private fun setupPermissions() {
		val permission = ContextCompat.checkSelfPermission(requireActivity(),
			Manifest.permission.ACCESS_FINE_LOCATION)

		if (permission != PackageManager.PERMISSION_GRANTED) {
			Log.i(TAG, "Permission to location enied")
			if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
					Manifest.permission.ACCESS_FINE_LOCATION)) {
				val builder = AlertDialog.Builder(activity)
				builder.setMessage("Permission to access the location is required for this app.")
						.setTitle("Permission required")

							builder.setPositiveButton("OK"
							) { dialog, id ->
						Log.i(TAG, "Clicked")
						makeRequest()
					}

					val dialog = builder.create()
				dialog.show()
			} else {
				makeRequest()
			}
		}
	}
	private fun makeRequest() {
		ActivityCompat.requestPermissions(requireActivity(),
			arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
			REQUEST_PERMISSIONS_REQUEST_CODE)
	}
	private fun startLocationPermissionRequest(activity: FragmentActivity) {
		ActivityCompat.requestPermissions(activity,
			arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
			REQUEST_PERMISSIONS_REQUEST_CODE)
	}

	private fun getData() {
		progress?.visibility = View.VISIBLE
		Instances.InternetCheck { internet ->
			if (internet) {
				viewModel.getAddress(
					Request(
						utoken = Preference.get(activity, DATAFILE, TOKEN)
					)
				)?.observe(viewLifecycleOwner, Observer {
					if (it != null) {
						if (Preference.get(activity, DATAFILE, LOC).isNullOrBlank()) {
							if (it.isNotEmpty()) {
								val ito = it.filter { it?.defaultAddress.toString() == "1" }
								if (!ito.isNullOrEmpty()) {
									Preference.put(
										activity, LOC, ito[0]?.address2.toString(), DATAFILE
									)
								}
							}
						}
						recyclerView15.adapter = NewDeliveryAdapter(
							activity,
							viewModel,
							it,
							object : Interfaces.ReturnAny {
								override fun getValue(values: Any?) {
									Instances.printAny(values)
									progress?.visibility = View.VISIBLE
									if (values is AddressItem) {
										Instances.InternetCheck { internet ->
											if (internet) {
												viewModel.setDeliveryAdd(
													Request(
														utoken = Preference.get(
															activity, DATAFILE, TOKEN
														), address_id = values.id
													)
												)?.observe(viewLifecycleOwner, Observer { it1 ->
													if (it1) {
														Preference.put(
															activity,
															LOC,
															values.address2.toString(),
															DATAFILE
														)
													}
													getData()
												})
											} else {
												toast(activity, getString(R.string.no_internet))
												progress?.visibility = View.GONE
											}
										}
									} else if (values is String) {
										Instances.InternetCheck { internet ->
											if (internet) {
												viewModel.deleteDelAddress(
													Request(
														utoken = Preference.get(
															activity, DATAFILE, TOKEN
														), address_id = values
													)
												)?.observe(viewLifecycleOwner, Observer { it1 ->
													getData()
												})
											} else {
												toast(activity, getString(R.string.no_internet))
												progress?.visibility = View.GONE
											}
										}
									} else {
										progress.visibility = View.GONE
									}
								}
							})
						activity?.textView58?.text = Preference.get(activity, DATAFILE, LOC)
					}
					progress.visibility = View.GONE
				})
			} else {
				toast(activity, getString(R.string.no_internet))
				progress?.visibility = View.GONE
			}
		}
	}

	class NewDeliveryAdapter(
		val activity: FragmentActivity?,
		val viewModel: AddressListViewModel,
		val data: List<AddressItem?>?,
		val param: Interfaces.ReturnAny
	) : RecyclerView.Adapter<NewDeliveryAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_addresses, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return data?.size ?: 0
		}

		@SuppressLint("SetTextI18n")
		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (data != null) {
				if (data[holder.adapterPosition]?.defaultAddress.toString() == "1") {
					holder.change?.visibility = View.GONE
					holder.default?.visibility = View.VISIBLE
					holder.imageView33?.visibility = View.GONE
				} else {
					holder.change?.visibility = View.VISIBLE
					holder.default?.visibility = View.GONE
					holder.imageView33?.visibility = View.VISIBLE
					holder.change?.setOnClickListener(object : ClickListener() {
						override fun onOneClick(v: View) {
							param.getValue(data[holder.adapterPosition])
						}
					})
					holder.imageView33?.setOnClickListener(object : ClickListener() {
						override fun onOneClick(v: View) {
							activity?.let { context ->
								val builder = androidx.appcompat.app.AlertDialog.Builder(context).setTitle("Delete Address")
									.setCancelable(false).setMessage("Are you sure you want to delete the address?")
									.setPositiveButton("Yes") { _, _ ->
										param.getValue(data[holder.adapterPosition]?.id.toString())
									}.setNegativeButton("Cancel") { dialog, _ ->
										dialog.dismiss()

									}
								val dialog = builder.create()
								dialog.show()
							}
						}
					})
				}
				holder.textView101?.text = data[holder.adapterPosition]?.address ?: ""
				holder.textView102?.text = data[holder.adapterPosition]?.address2 ?: ""
				holder.textView103?.text = (data[holder.adapterPosition]?.district
					?: "") + ", " + data[holder.adapterPosition]?.state + ", " + data[holder.adapterPosition]?.postOffice + ", " + data[holder.adapterPosition]?.pincode
				holder.view39?.text = data.get(holder.adapterPosition)?.addr.toString()
			}

		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val change = itemView.findViewById<TextView?>(R.id.textView104)
			val default = itemView.findViewById<TextView?>(R.id.textView105)
			val textView101 = itemView.findViewById<TextView?>(R.id.textView101)
			val textView102 = itemView.findViewById<TextView?>(R.id.textView102)
			val textView103 = itemView.findViewById<TextView?>(R.id.textView103)
			val imageView33 = itemView.findViewById<ImageView?>(R.id.imageView33)
			val view39 = itemView.findViewById<TextView?>(R.id.view39)
		}

	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE ) {

				if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

					Log.i(TAG, "Permission has been denied by user")
				} else {
					Log.i(TAG, "Permission has been granted by user")
				}
			}
		}


	private fun startSettingActivity() {
		val intent = Intent().apply {
			action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
			data = Uri.fromParts("package", APPLICATION_ID, null)
			flags = Intent.FLAG_ACTIVITY_NEW_TASK
		}
		startActivity(intent)
	}

}
