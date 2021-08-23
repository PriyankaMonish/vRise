package com.vrise.bazaar.ex.shop.pages.shop

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.multidex.BuildConfig.APPLICATION_ID
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.AddressListViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.printAny
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.latitude
import com.vrise.bazaar.newpages.utilities.HelperMethods
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.add_address_item.*
import kotlinx.android.synthetic.main.address_map_fragment.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.listpopups.view.*
import java.io.IOException
import java.util.*


class AddPlaces : Fragment(), OnMapReadyCallback {
    private lateinit var viewModel: AddressListViewModel
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    var lati: Double = 0.0
    var long: Double = 0.0
	var latis: Double = 0.0
    var longs: Double = 0.0
    var p: Point? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var mMap: GoogleMap? = null
    val REQUEST_CODE_AUTOCOMPLETE = 25010

	private var locationRequest: LocationRequest? = null
	private var locationCallback: LocationCallback? = null

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(lati, long)))
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lati, long), 16.0f))

        p0?.setOnCameraMoveStartedListener {
            textInputEditText3.setText(getString(R.string.identifying_location))
            textInputEditText4?.setText("")
            textInputEditText5?.setText("")
            textInputEditText6?.setText("")
            textInputEditText15?.setText("")
            textInputEditText7?.setText("")
            textInputEditText9?.setText("")
            textInputEditText8?.setText("")
        }
        p0?.setOnCameraIdleListener {

            lati = p0.cameraPosition.target.latitude

            long = p0.cameraPosition.target.longitude

			Log.e("Camera Long",lati.toString())
            updateAddress()
            println("location :  $lati : $long")
        }
        map.onResume()
    }
	private fun buildLocationRequest() {
		locationRequest = LocationRequest()
		locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
		locationRequest!!.setInterval(100)
		locationRequest!!.setFastestInterval(100)
		locationRequest!!.setSmallestDisplacement(1f)
	}

	//Build the location callback object and obtain the location results //as demonstrated below:
	private fun buildLocationCallBack() {
		locationCallback = object : LocationCallback() {
			override fun onLocationResult(locationResult: LocationResult) {

				Log.e("Address", locationResult.locations.size.toString())
				for (location in locationResult.locations) {

					lati = location.latitude
					long = location.longitude
					Log.e("locationCallback Long",lati.toString())
					println("$lati, $long")
					updateAddress()
					fusedLocationClient?.removeLocationUpdates(locationCallback)
					break

				}


			}
		}
	}
    private fun updateAddress() {
        getLocationAddress(lati, long, object : Interfaces.ReturnAny {
			@SuppressLint("SetTextI18n")
			override fun getValue(values: Any?) {


				if (values is Address) {
					val address = with(values) {
						(0..maxAddressLineIndex).map { getAddressLine(it) }
					}.joinToString(separator = "\n")
					textInputEditText3.setText(address)
					val values1: List<String?>? =
						address.replace(",,", ",").split(",")
					textInputEditText4?.setText(values1?.get(0).let { it ?: "" })
					textInputEditText5?.setText(values1?.get(1).let { it ?: "" })
					textInputEditText6?.setText(values1?.get(4).let { it ?: "" })
					textInputEditText7?.setText(values.adminArea)
					textInputEditText9?.setText(values.postalCode)
					textInputEditText8?.setText(values.subLocality)
					map.onResume()
				} else if (values is String) {
					if (values != "No address found") {
						textInputEditText3.setText(values.toString())
					}
					getGeoAddress(lati, long, object : Interfaces.ReturnAny {
						override fun getValue(values: Any?) {
							if (values.toString().isBlank() || values.toString() == "null") {
								textInputEditText3.setText("No Address Found")
							} else {
								if (values is Address) {
									val address = with(values) {
										(0..maxAddressLineIndex).map { getAddressLine(it) }
									}.joinToString(separator = "\n")
									textInputEditText3.setText(address)
									val values1: List<String?>? =
										address.replace(",,", ",").split(",")
									textInputEditText4?.setText(values1?.get(0).let { it ?: "" })
									textInputEditText5?.setText(values1?.get(1).let { it ?: "" })
									textInputEditText6?.setText(values1?.get(2).let { it ?: "" })
									textInputEditText7?.setText(values1?.get(3).let { it ?: "" })
									textInputEditText8?.setText(values1?.get(4).let { it ?: "" })
									textInputEditText9?.setText("")
								} else {
									textInputEditText3.setText(values.toString())
								}
							}
						}
					})

				}
			}
		})
    }

    private fun getGeoAddress(latitude: Double, longitude: Double, observer: Interfaces.ReturnAny) {
        viewModel.getFromLocation(latitude, longitude, 1)
            ?.observe(viewLifecycleOwnerLiveData.value ?: this, androidx.lifecycle.Observer {
				it?.let {
					if (it.isNotEmpty()) {
						observer.getValue(it[0])
					}
				}
			})
    }

    private fun getLocationAddress(
		latitude: Double,
		longitude: Double,
		observer: Interfaces.ReturnAny
	) {
        var address: List<Address> = emptyList()
        if (Geocoder.isPresent()) {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            try {
                address = geocoder.getFromLocation(latitude, longitude, 1)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            } catch (illegalArgumentException: IllegalArgumentException) {
                illegalArgumentException.printStackTrace()
                printAny("Latitude = $latitude , Longitude = $longitude")
            }
        }
        if (address.isEmpty()) {
            observer.getValue("No address found")
        } else {
            observer.getValue(address[0])
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        progress.visibility = View.GONE
        viewModel = ViewModelProvider(
			this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
		).get(AddressListViewModel::class.java)

        if (Instances.isGpsEnabled(activity)) {
            startFusedLocation()
        } else {
            startGps()
        }

        map.getMapAsync(this)
        map.onCreate(null)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            view41.isGone = false
            view41.setOnClickListener { view41 ->
                p?.let { p ->
                    val view =
                        (requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
							R.layout.listpopups,
							null
						)
                    val popUp = PopupWindow(requireContext())
                    popUp.contentView = view
                    popUp.width = 200
                    popUp.height = popUp.contentView.measuredHeight
                    popUp.isFocusable = true
                    popUp.setBackgroundDrawable(
						ContextCompat.getDrawable(
							requireContext(), R.drawable.my_bg_rounded_gray_button
						)
					)
                    popUp.showAtLocation(view41, Gravity.NO_GRAVITY, 0, view41.bottom - 60)
                    view.view42.layoutManager = LinearLayoutManager(requireContext())
                    view.view42.adapter = Instances.CustomStringListAdapter(
						requireActivity(),
						object : ArrayList<String?>() {
							init {
								add("Home")
								add("Work")
								add("Other")
							}
						}.toList(),
						null,
						object : Interfaces.ReturnData {
							override fun getValue(key: String, value: String) {
								popUp.dismiss()
								view43.setText(value)
							}
						},
						null
					)
                }
            }
        } else {
            view41.isGone = true
        }
        button14.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				Instances.InternetCheck { internet ->
					if (internet) {
						if (textInputEditText4?.text?.toString()
								.isNullOrBlank() || textInputEditText5?.text?.toString()
								.isNullOrBlank() || textInputEditText6?.text?.toString()
								.isNullOrBlank() || textInputEditText7?.text?.toString()
								.isNullOrBlank() || textInputEditText9?.text?.toString()
								.isNullOrBlank() || textInputEditText8?.text?.toString()
								.isNullOrBlank() || textInputEditText15?.text?.toString()
								.isNullOrBlank()
						) {
							toast(activity, "Fill Mandatory Fields")
						} else {
							progress.visibility = View.VISIBLE
							viewModel.plus(
								Request(
									utoken = Preference.get(
										activity, Preference.DATAFILE, Preference.TOKEN
									),
									address = textInputEditText4?.text.toString(),
									address2 = textInputEditText5?.text.toString(),
									district = textInputEditText6?.text.toString(),
									state = textInputEditText7?.text.toString(),
									pincode = textInputEditText9?.text.toString(),
									postOffice = textInputEditText8?.text.toString(),
									landmark = textInputEditText15?.text.toString(),
									latitude = lati.toString(),
									longitude = long.toString(),
									defaultaddress = "0",
									addr = view43?.text.toString()
								)
							)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
								if (it) {

									Preference.put(
										activity,
										Preference.LOC,
										textInputEditText5?.text.toString(),
										Preference.DATAFILE
									)
									progress.visibility = View.GONE

									v.findNavController().navigate(R.id.action_addPlaces_to_addressListFragment)
								} else {


									progress.visibility = View.GONE
								}
							})
						}
					} else {
						toast(activity, getString(R.string.no_internet))
					}
				}
			}
		})

        imageView5.setOnClickListener {
            activity?.let {
                getLastLocation(it, object : Interfaces.ReturnAny {
					@SuppressLint("SetTextI18n")
					override fun getValue(values: Any?) {
						if (values is Address) {
							val addressFragments = with(values) {
								(0..maxAddressLineIndex).map { getAddressLine(it) }
							}
							textInputEditText3.setText(addressFragments.joinToString(separator = "\n"))
							textInputEditText4?.setText(values.featureName)
							textInputEditText5?.setText(
								"${values.featureName ?: ""}, ${
									values.thoroughfare
										?: ""
								}, ${values.subLocality ?: ""}".replace(",  ,", ", ")
							)

							textInputEditText6?.setText(values.subAdminArea)
							textInputEditText7?.setText(values.adminArea)
							textInputEditText9?.setText(values.postalCode)
							textInputEditText8?.setText(values.subLocality)
							mMap?.clear()
							mMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(lati, long)))
							mMap?.animateCamera(
								CameraUpdateFactory.newLatLngZoom(
									LatLng(
										lati, long
									), 16.0f
								)
							)
							map.onResume()
						} else if (values is String) {
							textInputEditText3.setText(values.toString())
						}
					}
				})
            }
        }

        activity?.let {
            com.google.android.libraries.places.api.Places.initialize(
				it,
//				getString(R.string.google_api_key)
						"AIzaSyAZkH6MkpdJLjEiNXkhLZgmIhXVXl_TCes"
			)
        }

        textView1163.setOnClickListener {
            activity?.let {
                //todo testing needed
                val fields: List<Place.Field> =
                    arrayListOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
                // val build = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                // build.setLocationRestriction(RectangularBounds.newInstance(BOUNDS_INDIA))
                val intent: Intent =
                    Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(it)
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE)
            }
        }
        val clipboard =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        textView273.setOnClickListener {
            if (textInputEditText3.text.toString().trim() != "") {
                clipboard.setPrimaryClip(
					ClipData.newPlainText(
						"address",
						textInputEditText3.text.toString()
					)
				)
                HelperMethods.toast(requireActivity(), "address copied")
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // printAny(data)
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    val place = Autocomplete.getPlaceFromIntent(data)
                    lati = place.latLng?.latitude ?: 0.0
                    long = place.latLng?.longitude ?: 0.0
					Log.e("OnAct Long",lati.toString())
                    updateAddress()
                    val name = place.name ?: ""
                    mMap?.clear()
                    mMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(lati, long)))
                    mMap?.animateCamera(
						CameraUpdateFactory.newLatLngZoom(
							LatLng(lati, long),
							16.0f
						)
					)
                    map.onResume()
                }
            }
        }
    }

    private fun startGps() {
        activity?.let {
            val builder = AlertDialog.Builder(activity).setTitle("Enable Gps").setCancelable(false)
                .setMessage("Please enable your phone's location")
                .setPositiveButton("Continue") { dialog, which ->
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
//        if (Instances.isGpsEnabled(activity)) {
//            startFusedLocation()
//        } else {
//            startGps()
//        }
    }

    private fun startFusedLocation() {
        activity?.let {
			buildLocationRequest()
			buildLocationCallBack()
            if (fusedLocationClient == null) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
            }
            if (Instances.hasPermission(it, Manifest.permission.ACCESS_FINE_LOCATION)) {
                getLastLocation(it, null)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
						it, Manifest.permission.ACCESS_FINE_LOCATION
					)
                ) {
                    showSnackbar(R.string.permission_rationale,
						android.R.string.ok,
						View.OnClickListener { view ->
							startLocationPermissionRequest(it)
						})
                } else {
                    startLocationPermissionRequest(it)
                }
            }
        }
    }

    private fun getLastLocation(activity: FragmentActivity, observer: Interfaces.ReturnAny?) {

        fusedLocationClient?.lastLocation?.addOnCompleteListener(activity) { task ->
            if (task.isSuccessful && task.result != null) {
                task.result?.longitude?.let { lon ->
                    task.result?.latitude?.let { lat ->
                        lati = lat
                        long = lon
						updateAddress()
						Log.e("GetLast Long",lati.toString())
                        println("$lati, $long")
                        if (observer != null) {
                            getLocationAddress(lat, lon, observer)
                        }
                    }
                }
            } else {

				fusedLocationClient?.requestLocationUpdates(locationRequest,locationCallback,null)
//                showSnackbar(R.string.no_location_detected)
            }
        }
    }

    private fun startLocationPermissionRequest(activity: FragmentActivity) {
        ActivityCompat.requestPermissions(
			activity,
			arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
			REQUEST_PERMISSIONS_REQUEST_CODE
		)
    }

    override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> toast(activity, "User interaction was cancelled.")
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> activity?.let {
                    getLastLocation(it, object : Interfaces.ReturnAny {
						override fun getValue(values: Any?) {

						}
					})
                }
                else -> {
                    showSnackbar(R.string.permission_denied_explanation,
						R.string.settings,
						View.OnClickListener {
							val intent = Intent().apply {
								action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
								data = Uri.fromParts("package", APPLICATION_ID, null)
								flags = Intent.FLAG_ACTIVITY_NEW_TASK
							}
							startActivity(intent)
						})
                }
            }
        }
    }

    fun showSnackbar(
		snackStrId: Int,
		actionStrId: Int = 0,
		listener: View.OnClickListener? = null
	) {
        val snackbar = Snackbar.make(
			(activity?.findViewById(android.R.id.content)) as View,
			getString(snackStrId),
			LENGTH_INDEFINITE
		)
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }

    override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		buildLocationCallBack()
		buildLocationRequest()
//		(activity as DashBoardContainer).textView1009.text = "Add Address"

        return inflater.inflate(R.layout.address_map_fragment, container, false)
    }
	fun onBackPressed() {
		activity?.finishAffinity()
	}


}