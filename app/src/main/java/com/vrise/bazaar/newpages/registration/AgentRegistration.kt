package com.vrise.bazaar.newpages.registration

//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import androidx.core.app.ActivityCompat
//import androidx.appcompat.app.AlertDialog
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.google.firebase.iid.FirebaseInstanceId
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.Constants.EXECUTIVECODE
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//import com.vrise.bazaar.newpages.utilities.HelperMethods.checkIfPermissionsGranted
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import kotlinx.android.synthetic.main.fragment_agent_registration.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback
//import java.io.*

//class AgentRegistration : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    val neededPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//    var imageVlaue = ""
//
//    lateinit var stateList: ArrayList<Request>
//    lateinit var districtList: ArrayList<Request>
//    var stateId: String = ""
//    var districtId: String = ""
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    private var device: String? = null
//
//    override fun initControl() {
//        try {
//            activity?.page_title?.text = "Agent Registration"
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//            device = instanceIdResult.token
//            HelperMethods.println("newToken1 $device ")
//        }
//        getStates()
//
//        textView31.setText(com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.REGISTRATION).toString())
//
//        textView36.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (
//                        hasText(arrayOf(
//                                Validator.Main(textView30, ""),
//                                Validator.Main(textView31, Validator.isPhone),
//                                Validator.Main(textView32, Validator.isEmail),
//                                Validator.Main(textView331, ""),
//                                Validator.Main(textView332, ""),
//                                Validator.Main(textView335, ""),
//                                Validator.Main(textView34, ""))
//                        ) &&
//                        hasText(textView333) &&
//                        stateId.isNotBlank() &&
//                        hasText(textView334) &&
//                        districtId.isNotBlank()
//                ) {
//                    progressBar.visibility = View.VISIBLE
//
//                    if (device.isNullOrBlank()) {
//                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                            device = instanceIdResult.token
//                            HelperMethods.println("newToken2 $device ")
//                        }
//                    } else if (device == "0") {
//                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                            device = instanceIdResult.token
//                            HelperMethods.println("newToken3 $device ")
//                        }
//                    }
//
//                    println(Request(
//                            mobile = textView31.text.toString(),
//                            device_id = device,
//                            user_type = "2",
//                            email = textView32.text.toString(),
//                            name = textView30.text.toString(),
//                            code = textView29.text.toString(),
//                            agent_id = "",
//                            address = textView331.text.toString(),
//                            address2 = textView332.text.toString(),
//                            district = districtId,
//                            state = stateId,
//                            profile_pic = imageVlaue,
//                            pincode = textView34.text.toString(),
//                            postOffice = textView335.text.toString()
//                    ))
//                    apiService?.signUp(Request(
//                            mobile = textView31.text.toString(),
//                            device_id = device,
//                            user_type = "2",
//                            email = textView32.text.toString(),
//                            name = textView30.text.toString(),
//                            code = textView29.text.toString(),
//                            agent_id = "",
//                            address = textView331.text.toString(),
//                            address2 = textView332.text.toString(),
//                            state = stateId,
//                            district = districtId,
//                            profile_pic = imageVlaue,
//                            pincode = textView34.text.toString(),
//                            postOffice = textView335.text.toString()
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (HelperMethods.hasData(activity!!, response)) {
//                                    if (response!!.body()!!.data != null) {
//                                        if (response.body()!!.data!!.action != null) {
//                                            if (response.body()!!.data!!.action == EXECUTIVECODE) {
//                                                val bundle = Bundle()
//                                                bundle.putString(Constants.ID, response.body()!!.data!!.userToken.toString())
//                                                navigateTo(activity!!, FRAGMENT, false, MarketingExecutiveCode(), null, bundle, null, "")
//                                            }
//                                        }
//                                    }
//                                }
//                                progressBar.visibility = View.GONE
//                            }
//                        }
//                    })
//                } else {
//                    textView333.error = if (stateId.isBlank()) {
//                        "Select State"
//                    } else {
//                        null
//                    }
//                    textView334.error = if (districtId.isBlank()) {
//                        "Select District"
//                    } else {
//                        null
//                    }
//                }
//            }
//        })
//
//        textView334.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (stateId.isBlank()) {
//                    toastit(activity!!, "select a state")
//                } else {
//                    textView334.error = null
//                    getDistrict()
//                }
//            }
//        })
//
//        textView333.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (stateList != null) {
//                    textView333.error = null
//                    if (stateList.isNotEmpty()) {
//                        HelperMethods.openPopUp(activity!!, stateList, textView333, "States", object : Interfaces.ReturnId {
//                            override fun returnId(string: String) {
//                                stateId = string
//                                textView334.text = ""
//                                districtId = ""
//                            }
//                        })
//                    }
//                }
//            }
//        })
//
//        imageView4.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (checkIfPermissionsGranted(activity!!, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))) {
//                    getImage()
//                } else {
//                    ActivityCompat.requestPermissions(activity!!, neededPermissions, 10003)
//                }
//            }
//        })
//    }
//
//    private fun getDistrict() {
//        apiService?.getDistricts(Request(state = stateId))?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (HelperMethods.hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.districts != null) {
//                                if (response.body()!!.data!!.districts!!.isNotEmpty()) {
//                                    districtList = ArrayList()
//                                    for (i in 0 until response.body()!!.data!!.districts!!.size) {
//                                        districtList.add(Request(
//                                                name = response.body()!!.data!!.districts!![i]!!.name,
//                                                code = response.body()!!.data!!.districts!![i]!!.id
//                                        ))
//                                    }
//                                    if (districtList.isNotEmpty()) {
//                                        HelperMethods.openPopUp(activity!!, districtList, textView334, "Districts", object : Interfaces.ReturnId {
//                                            override fun returnId(string: String) {
//                                                districtId = string
//                                            }
//                                        })
//                                    } else {
//                                        toastit(activity!!, "No districts available")
//                                    }
//                                } else {
//                                    toastit(activity!!, "No districts available.")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getStates() {
//        apiService?.getStates()?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (HelperMethods.hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.states != null) {
//                                if (response.body()!!.data!!.states!!.isNotEmpty()) {
//                                    stateList = ArrayList()
//                                    for (i in 0 until response.body()!!.data!!.states!!.size) {
//                                        stateList.add(Request(
//                                                name = response.body()!!.data!!.states!![i]!!.state,
//                                                code = response.body()!!.data!!.states!![i]!!.id
//                                        ))
//                                    }
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getImage() {
//        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
//        val builder = AlertDialog.Builder(activity!!)
//        builder.setTitle("Add Photo!")
//        builder.setItems(items) { dialog, item ->
//            when {
//                items[item] == "Take Photo" -> {
//                    if (activity!!.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
//                        openCamera()
//                    } else {
//                        toastit(activity!!, "No Camera")
//                    }
//                }
//                items[item] == "Choose from Library" -> LibraryOpen()
//                items[item] == "Cancel" -> dialog.dismiss()
//            }
//        }
//        builder.show()
//    }
//
//    private fun LibraryOpen() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(Intent.createChooser(intent, "Select File"), 1)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (resultCode) {
//            Activity.RESULT_OK -> when (requestCode) {
//                0 -> fromCamera(data)
//                1 -> fromLib(data)
//            }
//            Activity.RESULT_CANCELED -> Log.e("TAG", "Selecting picture cancelled")
//        }
//    }
//
//    private fun fromLib(data: Intent?) {
//        var bm: Bitmap? = null
//        if (data != null) {
//            try {
//                bm = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, data.data)
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//        setImage(bm!!)
//    }
//
//    private fun fromCamera(data: Intent?) {
//        val thumbnail = data!!.extras!!.get("data") as Bitmap
//        val bytes = ByteArrayOutputStream()
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 60, bytes)
//        val destination = File(Environment.getExternalStorageDirectory(), System.currentTimeMillis().toString() + ".jpg")
//        val fo: FileOutputStream
//        try {
//            destination.createNewFile()
//            fo = FileOutputStream(destination)
//            fo.write(bytes.toByteArray())
//            fo.close()
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        setImage(thumbnail)
//    }
//
//    private fun setImage(thumbnail: Bitmap) {
//        imageVlaue = HelperMethods.convertBitmap(thumbnail)
//        imageView4.setImageBitmap(thumbnail)
//    }
//
//    private fun openCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, 0)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_agent_registration, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
