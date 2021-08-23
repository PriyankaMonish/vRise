package com.vrise.bazaar.ex.shop.pages

import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager.GPS_PROVIDER
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.vrise.R
import com.vrise.bazaar.ex.model.DeliveryboyData
import com.vrise.bazaar.ex.model.SellerData
import com.vrise.bazaar.ex.model.UserAddress
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.route.DirectionsResult
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.printAny
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.ex.util.custom.CustomInfoWindowAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.track_fragment.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.math.*

class TrackPageFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: TrackPageViewModel
    private var googleMap: GoogleMap? = null

    var userLatLng: LatLng? = null
    var sellerLatLng: LatLng? = null
    var deliveryBoyLatLng = MutableLiveData<LatLng?>()

    var sellerMarker: Marker? = null //deliveryBoy
    private var shopMarker: Marker? = null
    var userMarker: Marker? = null

    var shopData: SellerData? = null
    var status__data: DeliveryboyData? = null
    var userAddress: UserAddress? = null
    val markerList = ArrayList<Marker>()
    var polyline: Polyline? = null
    var nowPlace: LatLng? = LatLng(0.0, 0.0)
    lateinit var context: GeoApiContext

    private val handler = Handler()

    override fun onDetach() {
        handler.removeCallbacksAndMessages(null)
        super.onDetach()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onStop() {
        handler.removeCallbacksAndMessages(null)
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progress?.isVisible = true

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(requireActivity()),
                    requireActivity()
                )
            )
        ).get(TrackPageViewModel::class.java)

        context = GeoApiContext.Builder().apiKey(getString(R.string.google_api_key)).build()

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.trackorder(
                    Request(
                        utoken = Preference.get(requireActivity(), DATAFILE, TOKEN),
                        order_id = arguments?.getString("order_id") ?: ""
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                    userLatLng = it?.userAddress?.let { userAddress ->
                        val latitude: Double =
                            if (userAddress.latitude.isNullOrBlank() && userAddress.latitude.toString() == "null") Double.NaN else userAddress.latitude.toString().toDouble()
                        val longitude: Double =
                            if (userAddress.longitude.isNullOrBlank() && userAddress.longitude.toString() == "null") Double.NaN else userAddress.longitude.toString().toDouble()
                        if (latitude.isNaN() || longitude.isNaN()) {
                            null
                        } else LatLng(latitude, longitude)
                    }
                    userAddress = it?.userAddress
                    userLatLng?.let { userLatLng ->
                        userMarker = googleMap?.addMarker(MarkerOptions().apply {
                            position(userLatLng)
                            title(userAddress?.addressType ?: userAddress?.address ?: "You")
                            icon(bitmap(R.drawable.user))
                        })
                        userMarker?.showInfoWindow()
                    }

                    sellerLatLng = it?.sellerData?.let { sellerData ->
                        val latitude: Double =
                            if (sellerData.latitude.isNullOrBlank() && sellerData.latitude.toString() == "null") Double.NaN else sellerData.latitude.toString().toDouble()
                        val longitude: Double =
                            if (sellerData.longitude.isNullOrBlank() && sellerData.longitude.toString() == "null") Double.NaN else sellerData.longitude.toString().toDouble()
                        if (latitude.isNaN() || longitude.isNaN()) {
                            null
                        } else LatLng(latitude, longitude)
                    }
                    shopData = it?.sellerData

                    sellerLatLng?.let { sellerLatLng ->
                        shopMarker = googleMap?.addMarker(MarkerOptions().apply {
                            position(sellerLatLng)
                            title(shopData?.storeName ?: shopData?.address ?: "Shop")
                            icon(bitmap(R.drawable.marker))
                        })
                        shopMarker?.showInfoWindow()
                    }

                    status__data = it?.deliveryboyData
                    textView276.text = status__data?.fname
                    textView277.text = status__data?.vehicleNo


                    textView284?.setOnClickListener {
                        startActivity(
                            Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:" + "${status__data?.phone}")
                            )
                        )
                    }

                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            println("inside handler")
                            if (userLatLng != null && sellerLatLng != null && deliveryBoyLatLng.value != null) {
                                if (userMarker != null && shopMarker != null && sellerMarker != null) {
                                    try {
                                        activity?.let {
                                            if (!it.isFinishing && isAdded) {

                                                viewModel.dboylocation(
                                                    Request(
                                                        utoken = Preference.get(
                                                            requireActivity(),
                                                            DATAFILE,
                                                            TOKEN
                                                        ),
                                                        order_id = arguments?.getString("order_id")
                                                            ?: ""
                                                    )
                                                )?.observe(
                                                    viewLifecycleOwnerLiveData.value
                                                        ?: this@TrackPageFragment, Observer {
                                                        println("get dboylocation")
                                                        textView283.text = ""
                                                        if (!it?.delivery_boy_status.isNullOrBlank()) {
                                                            when (it?.delivery_boy_status) {
                                                                "pending", "picked", "reached_shop", "confirmed" -> {
                                                                    it.deliveryboyData?.let {
                                                                        if (!it.latitude.isNullOrEmpty() && it.latitude.toString() != "0.0" && !it.longitude.isNullOrEmpty() && it.longitude.toString() != "0.0") {
                                                                            deliveryBoyLatLng.value =
                                                                                LatLng(
                                                                                    it.latitude.toString().toDouble(),
                                                                                    it.longitude.toString().toDouble()
                                                                                )
                                                                        }
                                                                    }
                                                                }
                                                                "ongoing", "delivered", "cancelled", "not_delivered", "rejected" -> {
                                                                    view?.findNavController()
                                                                        ?.popBackStack()
                                                                }
                                                                "reached_customer" -> {
                                                                    textView283.text =
                                                                        "Delivery partner reached your location."
                                                                }
                                                                else -> {
                                                                    //view?.findNavController()?.popBackStack()
                                                                }
                                                            }
                                                        }
                                                    })
                                            } else {
                                                println("removeCallbacks")
                                                handler.removeCallbacks(this)
                                            }
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        handler.removeCallbacks(this)
                                    }
                                }
                            }
                            handler.postDelayed(this, 10000)
                        }
                    }, 1000)

                    val deliverLatLng = it?.deliveryboyData?.let { deliveryboyData ->
                        val latitude: Double =
                            if (deliveryboyData.latitude.isNullOrBlank() && deliveryboyData.latitude.toString() == "null")
                                Double.NaN else deliveryboyData.latitude.toString().toDouble()
                        val longitude: Double =
                            if (deliveryboyData.longitude.isNullOrBlank() && deliveryboyData.longitude.toString() == "null")
                                Double.NaN else deliveryboyData.longitude.toString().toDouble()
                        if (latitude.isNaN() || longitude.isNaN()) {
                            null
                        } else LatLng(latitude, longitude)
                    }

                    sellerMarker = googleMap?.addMarker(MarkerOptions().apply {
                        if (deliverLatLng != null) {
                            position(deliverLatLng)
                        }
                        icon(bitmap(R.drawable.bike_top))
                    })

                    deliveryBoyLatLng.value = deliverLatLng

                    textView2841.setOnClickListener {
                        if (status__data != null) {
                            if (activity is DashBoardContainer) {
                                view?.findNavController()?.navigate(
                                    R.id.action_trackPageFragment_to_chatFragment, bundleOf(
                                        "order_id" to arguments?.getString("order_id")
                                    )
                                )
                            } else if (activity is BazaarContainer) {
                                view?.findNavController()?.navigate(
                                    R.id.action_trackPageFragment2_to_chatFragment, bundleOf(
                                        "order_id" to arguments?.getString("order_id")
                                    )
                                )
                            }
                        }
                    }
                    map.onResume()

                    progress.isVisible = false
                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }

        map.getMapAsync(this)
        map.onCreate(null)
    }

    private fun getBounds(markers: ArrayList<Marker>): LatLngBounds? {
        val builder: LatLngBounds.Builder = LatLngBounds.Builder()
        if (markers.isNotEmpty()) {
            for (marker in markers) {
                builder.include(marker.position)
            }
        }
        return builder.build()
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0
        googleMap?.setOnMarkerClickListener {
            //Prevent marker click
            true
        }
        googleMap?.uiSettings?.isZoomControlsEnabled = false
        googleMap?.uiSettings?.isZoomGesturesEnabled = true

        //googleMap?.setMinZoomPreference(15f)

        googleMap?.setInfoWindowAdapter(CustomInfoWindowAdapter(requireContext()))

        deliveryBoyLatLng.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

            printAny("deliveryBoyLatLng ${deliveryBoyLatLng.value}")
            printAny("userLatLng ${userLatLng}")

            if (userLatLng != null && sellerLatLng != null && deliveryBoyLatLng.value != null) {
                if (userMarker != null && shopMarker != null) {
                    println(deliveryBoyLatLng.value.toString())
                    it?.let {
                        if (nowPlace != LatLng(it.latitude.toString().toDouble(),
                                it.longitude.toString().toDouble())) {
                            nowPlace = LatLng(
                                it.latitude.toString().toDouble(),
                                it.longitude.toString().toDouble()
                            )

                            println("at same ${nowPlace.toString()}")

                            nowPlace?.let {
                                userLatLng?.let { it1 ->
                                    showMap(it, it1)
                                }
                            }
                        } else {
                            println("at same place")
                        }
                    }
                }
            }
        })

        map.onResume()
    }

    private fun showMap(deliveryBoyLatLng: LatLng, userLatLng: LatLng): Boolean {


        println("launch")

        val path: ArrayList<LatLng> = ArrayList()
        viewModel.getDirection(
            origin = "${deliveryBoyLatLng.latitude},${deliveryBoyLatLng.longitude}",
            destination = "${userLatLng.latitude},${userLatLng.longitude}",
            key = getString(R.string.google_api_key),
            directions = directions
        )?.observe(viewLifecycleOwnerLiveData.value ?: this@TrackPageFragment, Observer {
            println("yay direction result change")
            it?.let { res ->
                if (res?.routes != null && res.routes.isNotEmpty()) {
                    val route = res.routes[0]
                    if (route?.legs != null) {
                        for (i in route.legs.indices) {
                            val leg = route.legs[i]
                            if (leg?.steps != null) {
                                for (j in leg.steps.indices) {
                                    val step = leg.steps[j]
                                    if (step?.steps != null && step.steps.isNotEmpty()) {
                                        for (k in step.steps.indices) {
                                            val step1 = step.steps[k]
                                            val points1 = step1?.polyline
                                            if (points1 != null) {
                                                points1.points?.let { it1 ->
                                                    val coords1 = decode(it1) //points1.decodePath(it1)
                                                    for (coord1 in coords1) {
                                                        path.add(
                                                            LatLng(
                                                                coord1.lat,
                                                                coord1.lng))
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        val points = step?.polyline
                                        if (points != null) { //Decode polyline and add points to list of route coordinates
                                            points.points?.let { it1 ->
                                                val coords = decode(it1) //points.decodePath(it1)
                                                for (coord in coords) {
                                                    path.add(
                                                        LatLng(
                                                            coord.lat,
                                                            coord.lng))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (path.size > 0) {
                    polyline?.remove()

                    polyline = this.googleMap?.addPolyline(
                        PolylineOptions().addAll(path).color(
                            ContextCompat.getColor(
                                requireActivity(),
                                R.color.colorPrimaryNew
                            )
                        ).width(8f)
                    ) //Color.BLUE
                }


            }

            animateMarkerTo(Location(GPS_PROVIDER).apply {
                latitude = userLatLng.latitude
                longitude = userLatLng.longitude
            }, sellerMarker)

        })
        return true
    }


    private fun bitmap(user: Int): BitmapDescriptor? {
        val smallMarker =
            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, user), 80, 80, false)
        return BitmapDescriptorFactory.fromBitmap(smallMarker)
    }

    private fun animateMarkerTo(destination: Location, marker: Marker?) {

        if (marker != null) {
            val startPosition: LatLng = marker.position
            val endPosition = LatLng(destination.latitude, destination.longitude)
            val startRotation: Float = marker.rotation
            val latLngInterpolator: LatLngInterpolatorNew = LatLngInterpolatorNew.LinearFixed()
            val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
            valueAnimator.duration = 3000 // duration 3 second
            valueAnimator.interpolator = LinearInterpolator()
            valueAnimator.addUpdateListener { animation ->
                try {
                    val v = animation.animatedFraction
                    val newPosition: LatLng? =
                        latLngInterpolator.interpolate(v, startPosition, endPosition)

                    marker.rotation = getBearing(startPosition, endPosition)

                    newPosition?.let {
                        marker.position = it
                    }

                    markerList.apply {
                        clear()
                        userMarker?.let { add(it) }
                        sellerMarker?.let { add(it) }
                    }

                    userMarker?.showInfoWindow()

                    CameraUpdateFactory.newLatLngBounds(getBounds(markerList), 50)
                        ?.let { CameraUpdate ->
                            googleMap?.moveCamera(CameraUpdate)
                            googleMap?.animateCamera(CameraUpdate)
                        }

                } catch (ex: java.lang.Exception) {

                    ex.printStackTrace()

                    val zoomMarker =
                        userLatLng?.let { deliveryBoyLatLng.value?.let { it1 -> center(it, it1) } }
                    zoomMarker?.let {
                        CameraUpdateFactory.newLatLng(
                            LatLng(
                                zoomMarker.latitude,
                                zoomMarker.longitude
                            )
                        )?.let { CameraUpdate ->
                            googleMap?.moveCamera(CameraUpdate)
                            googleMap?.animateCamera(CameraUpdate)
                        }
                    }
                }
            }
            valueAnimator.addListener(object : AnimatorListenerAdapter() {})
            valueAnimator.start()
        }
        map.onResume()
    }

    private interface LatLngInterpolatorNew {
        fun interpolate(fraction: Float, a: LatLng?, b: LatLng?): LatLng?
        class LinearFixed : LatLngInterpolatorNew {
            override fun interpolate(fraction: Float, a: LatLng?, b: LatLng?): LatLng {
                val lat =
                    ((b?.latitude ?: 0.0) - (a?.latitude ?: 0.0)) * fraction + (a?.latitude ?: 0.0)
                var lngDelta = (b?.longitude ?: 0.0) - (a?.longitude ?: 0.0)
                if (abs(lngDelta) > 180) {
                    lngDelta -= sign(lngDelta) * 360
                }
                val lng = lngDelta * fraction + (a?.longitude ?: 0.0)
                return LatLng(lat, lng)
            }
        }
    }
    private fun getBearing(begin: LatLng, end: LatLng): Float {
        val lat = abs(begin.latitude - end.latitude)
        val lng = abs(begin.longitude - end.longitude)
        if (begin.latitude < end.latitude && begin.longitude < end.longitude) return Math.toDegrees(
            atan(lng / lat)
        )
            .toFloat() else if (begin.latitude >= end.latitude && begin.longitude < end.longitude) return (90 - Math.toDegrees(
            Math.atan(lng / lat)
        ) + 90).toFloat() else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude) return (Math.toDegrees(
            atan(lng / lat)
        ) + 180).toFloat() else if (begin.latitude < end.latitude && begin.longitude >= end.longitude) return (90 - Math.toDegrees(
            atan(lng / lat)
        ) + 270).toFloat()
        return (-1).toFloat()
    }

    class NewRetroClient() {
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? = if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
            retrofit
        } else {
            retrofit
        }
    }

    interface GoogleService {

        //https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=YOUR_API_KEY

        @GET("json")
        fun getDirections(
            @Query("origin") origin: String, @Query("destination") destination: String, @Query(
                "key"
            ) key: String
        ): Call<DirectionsResult>

    }

    companion object {

        @Volatile
        private var instanceOf: NewRetroClient? = null

        private fun getRetrofitInstance() = instanceOf ?: synchronized(this) {
            instanceOf ?: NewRetroClient().also {
                instanceOf = it
            }
        }

        val directions = getRetrofitInstance().getClient()?.create(GoogleService::class.java)

        private val pi = Math.PI / 180
        private val xpi = 180 / Math.PI

        fun center(vararg arr: LatLng): LatLng {
            if (arr.size == 1) {
                return arr[0]
            }
            var x = 0.0
            var y = 0.0
            var z = 0.0
            arr.forEach {
                val latitude = it.latitude * pi
                val longitude = it.longitude * pi
                val cl = cos(latitude)
                x += cl * cos(longitude)
                y += cl * sin(longitude)
                z += sin(latitude)
            }
            val total = arr.size
            x /= total
            y /= total
            z /= total
            val centralLongitude = atan2(y, x)
            val centralSquareRoot = sqrt(x * x + y * y)
            val centralLatitude = atan2(z, centralSquareRoot)
            return LatLng(centralLatitude * xpi, centralLongitude * xpi)
        }

        private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
            val background =
                ContextCompat.getDrawable(context, R.drawable.indicator_circle_selected)
            background?.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
            val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
            vectorDrawable?.setBounds(
                40, 20, vectorDrawable.intrinsicWidth + 40, vectorDrawable.intrinsicHeight + 20
            )
            val bitmap = background?.let { backgrounds ->
                Bitmap.createBitmap(
                    backgrounds.intrinsicWidth + 40,
                    backgrounds.intrinsicHeight + 20,
                    Bitmap.Config.ARGB_8888
                )
            }
            bitmap?.let {
                val canvas = Canvas(it)
                background.draw(canvas)
                vectorDrawable?.draw(canvas)
            }
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }

        fun decode(encodedPath: String): List<com.google.maps.model.LatLng> {
            val len = encodedPath.length
            val path: MutableList<com.google.maps.model.LatLng> = java.util.ArrayList(len / 2)
            var index = 0
            var lat = 0
            var lng = 0
            while (index < len) {
                var result = 1
                var shift = 0
                var b: Int
                do {
                    b = encodedPath[index++].toInt() - 63 - 1
                    result += b shl shift
                    shift += 5
                } while (b >= 0x1f)
                lat += if (result and 1 != 0) (result shr 1).inv() else result shr 1
                result = 1
                shift = 0
                do {
                    b = encodedPath[index++].toInt() - 63 - 1
                    result += b shl shift
                    shift += 5
                } while (b >= 0x1f)
                lng += if (result and 1 != 0) (result shr 1).inv() else result shr 1
                path.add(com.google.maps.model.LatLng(lat * 1e-5, lng * 1e-5))
            }
            return path
        }
    }
}

