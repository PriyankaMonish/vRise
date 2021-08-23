package com.vrise.bazaar.newpages.subscriber.menu

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.google.android.material.textfield.TextInputEditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.*
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_sub_container.*
import kotlinx.android.synthetic.main.fragment_subscriber_profile_edit.*
import retrofit2.Call
import retrofit2.Callback
import java.io.*

import com.vrise.bazaar.newpages.utilities.Validator.isEmail
import com.vrise.bazaar.newpages.utilities.Validator.hasText
import android.view.LayoutInflater
import android.widget.*
import com.bumptech.glide.Glide

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.Validator.isPhone
//
//class SubscriberProfile : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    lateinit var stateList: ArrayList<Request>
//    lateinit var districtList: ArrayList<Request>
//    var stateId: String = ""
//    var districtId: String = ""
//    val neededPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        setHasOptionsMenu(true)
//        activity?.page_title?.text = "Subscriber Profile"
//
////        textView108.visibility = View.VISIBLE
//        textView1081.visibility = View.VISIBLE
//        imageView23.visibility = View.VISIBLE
//        if (hasConnection(activity!!)) {
//            getDato()
//        }
//        textView117.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                fragmentManager!!.popBackStack()
//            }
//        })
//
//        textView1161.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (stateId.isBlank()) {
//                } else {
//                    textView1161.error = null
//                    apiService?.getDistricts(Request(state = stateId))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    if (response!!.body()!!.data != null) {
//                                        if (response.body()!!.data!!.districts != null) {
//                                            if (response.body()!!.data!!.districts!!.isNotEmpty()) {
//                                                districtList = ArrayList()
//                                                for (i in 0 until response.body()!!.data!!.districts!!.size) {
//                                                    districtList.add(Request(
//                                                            name = response.body()!!.data!!.districts!![i]!!.name,
//                                                            code = response.body()!!.data!!.districts!![i]!!.id
//                                                    ))
//                                                }
//                                                if (districtList.isNotEmpty()) {
//                                                    HelperMethods.openPopUp(activity!!, districtList, textView1161, "Districts", object : Interfaces.ReturnId {
//                                                        override fun returnId(string: String) {
//                                                            districtId = string
//                                                        }
//                                                    })
//                                                } else {
//                                                    HelperMethods.toastit(activity!!, "No Districts available")
//                                                }
//                                            } else {
//                                                HelperMethods.toastit(activity!!, "No Districts available.")
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    })
//                }
//            }
//        })
//
//        if (hasConnection(activity)) {
//            getStates()
//        }
//
//        textView113.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val dialog = Dialog(requireContext())
//                dialog.setCancelable(false)
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                activity!!.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//                dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, android.R.color.transparent))
//                dialog.setContentView(R.layout.change_user)
//                val textView159 = dialog.findViewById<TextInputEditText>(R.id.textView159)
//                val textView160 = dialog.findViewById<TextView>(R.id.textView160)
//                val textView161 = dialog.findViewById<TextView>(R.id.textView161)
//                val progressBar = dialog.findViewById<ProgressBar>(R.id.progressBar)
//                textView161.setOnClickListener(object : ClickListener() {
//                    override fun onOneClick(v: View) {
//                        if (hasText(textView159, "Phone Number Required") && isPhone(textView159)) {
//                            progressBar.visibility = View.VISIBLE
//                            print(Request(mobile = textView159.text.toString()))
//
//                            apiService?.registerMobileNumber(Request(mobile = textView159.text.toString()))?.enqueue(object : Callback<Response> {
//                                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                    if (activity != null && isAdded) {
//                                        progressBar.visibility = View.GONE
//                                        t!!.printStackTrace()
//                                    }
//                                }
//
//                                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                                    if (activity != null && isAdded) {
//                                        if (hasData(activity!!, response)) {
//                                            if (response!!.body()!!.data != null) {
//                                                if (response.body()!!.data!!.otp != null) {
//                                                    dialog.dismiss()
//                                                    val bundle = Bundle()
//                                                    bundle.putString(Constants.FROM, "1")
//                                                    bundle.putString(Constants.NUMBER, textView159.text.toString())
//                                                    bundle.putString(Constants.OTP, response.body()!!.data!!.otp)
//                                                    HelperMethods.navigateTo(activity!!, Constants.FRAGMENT, true, OtpPage(), null, bundle, null, "")
//                                                }
//                                            }
//                                            progressBar.visibility = View.GONE
//                                        } else {
//                                            progressBar.visibility = View.GONE
//                                        }
//                                    }
//                                }
//                            })
//
//                        }
//                    }
//                })
//
//                textView160.setOnClickListener(object : ClickListener() {
//                    override fun onOneClick(v: View) {
//                        dialog.dismiss()
//                    }
//                })
//                dialog.show()
//            }
//        })
//        textView1153.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                textView1153.error = null
//                HelperMethods.openPopUp(activity!!, stateList, textView1153, "States", object : Interfaces.ReturnId {
//                    override fun returnId(string: String) {
//                        stateId = string
//                        textView1161.text = ""
//                        districtId = ""
//                    }
//                })
//            }
//        })
//        textView118.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                progressBar.visibility = View.VISIBLE
//                if (
//                        hasText(arrayOf(textView1081, textView1152, textView1151, textView116, textView114, textView1154)) &&
//                        isEmail(textView114) &&
//                        hasText(textView1153) &&
//                        stateId.isNotBlank() &&
//                        hasText(textView1161) &&
//                        districtId.isNotBlank()
//                ) {
//                    /*code = textView112.text.toString()*/
//                    print(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            name = textView1081.text.toString(),
//                            email = textView114.text.toString(),
//                            mobile = textView113.text.toString(),
//                            address2 = textView1152.text.toString(),
//                            state = stateId,
//                            district = districtId,
//                            address = textView1151.text.toString(),
//                            pincode = textView116.text.toString(),
//                            profile_pic = imageVlaue,
//                            postOffice = textView1154.text.toString()
//                    ))
//                    /*code = textView112.text.toString()*/
//                    apiService?.updateSubscriberProfile(Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID),
//                            name = textView1081.text.toString(),
//                            email = textView114.text.toString(),
//                            mobile = textView113.text.toString(),
//                            address = textView1151.text.toString(),
//                            address2 = textView1152.text.toString(),
//                            state = stateId,
//                            district = districtId,
//                            pincode = textView116.text.toString(),
//                            profile_pic = imageVlaue,
//                            postOffice = textView1154.text.toString()
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                t!!.printStackTrace()
////                                textView108.visibility = View.VISIBLE
////                                textView1081.setText(textView108.text.toString())
//                                textView1081.visibility = View.GONE
//                                imageView23.visibility = View.VISIBLE
//                                progressBar.visibility = View.GONE
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    try {
//                                        (activity as SubContainer).times = 0
//                                    } catch (e: Exception) {
//                                        e.printStackTrace()
//                                    }
//                                    imageView23.isChecked = false
//                                    getDato()
//                                }
//                                progressBar.visibility = View.GONE
//                            }
//                        }
//                    })
//                } else {
//                    textView1153.error = if (stateId.isBlank()) {
//                        "Select State"
//                    } else {
//                        null
//                    }
//                    textView1161.error = if (districtId.isBlank()) {
//                        "Select District"
//                    } else {
//                        null
//                    }
//                    progressBar.visibility = View.GONE
//                }
//            }
//
//        })
//        imageView22.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (HelperMethods.checkIfPermissionsGranted(activity!!, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))) {
//                    getList()
//                } else {
//                    ActivityCompat.requestPermissions(activity!!, neededPermissions, 10003)
//                }
//            }
//        })
//
//        imageView23.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(p0: CompoundButton, p1: Boolean) {
//                if (p1) {
//                    textView1081.visibility = View.VISIBLE
////                    textView108.visibility = View.INVISIBLE
//                    view11.visibility = View.GONE
//                    textView117.visibility = View.VISIBLE
//                    textView118.visibility = View.VISIBLE
//                    view11.clearFocus()
//                } else {
//                    textView1081.visibility = View.VISIBLE
////                    textView108.visibility = View.VISIBLE
//                    view11.visibility = View.VISIBLE
//                    textView117.visibility = View.GONE
//                    textView118.visibility = View.GONE
//                    view11.requestFocus()
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
//                    if (hasData(activity!!, response)) {
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
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getDato() {
//        print(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))
//        apiService?.getSubscriberProfile(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)))?.enqueue(object :Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                if (activity != null && isAdded) {
//                    t!!.printStackTrace()
//                }
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                if (activity != null && isAdded) {
//                    if (hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.profiledata != null) {
//                                try {
//                                    Picasso.get().load(response.body()!!.data!!.profiledata!!.imgLink.toString()).fit().into(imageView22)
//                                } catch (e: Exception) {
//                                    e.printStackTrace()
//                                }
////                                textView108.text = response.body()!!.data!!.profiledata!!.name
//                                textView1081.setText(response.body()!!.data!!.profiledata!!.name)
////                                textView109.text = response.body()!!.data!!.profiledata!!.email
//                                textView111.text = "Agentcode :" + response.body()!!.data!!.profiledata!!.agent_code
//                                textView112.text = "Subscribercode :" + response.body()!!.data!!.profiledata!!.code
//                                textView113.text = response.body()!!.data!!.profiledata!!.mobile
//                                textView114.setText(response.body()!!.data!!.profiledata!!.email)
//
//                                if (!response.body()!!.data!!.profiledata!!.address.isNullOrBlank()) {
//                                    textView1151.setText(response.body()!!.data!!.profiledata!!.address)
//                                }
//                                if (!response.body()!!.data!!.profiledata!!.address2.isNullOrBlank()) {
//                                    textView1152.setText(response.body()!!.data!!.profiledata!!.address2)
//                                }
//                                if (!response.body()!!.data!!.profiledata!!.stateName.isNullOrBlank()) {
//                                    textView1153.text = response.body()!!.data!!.profiledata!!.stateName
//                                }
//                                if (!response.body()!!.data!!.profiledata!!.post_office.isNullOrBlank()) {
//                                    textView1154.setText(response.body()!!.data!!.profiledata!!.post_office.toString())
//                                }
//                                if (!response.body()!!.data!!.profiledata!!.district_name.isNullOrBlank()) {
//                                    textView1161.text = response.body()!!.data!!.profiledata!!.district_name
//                                }
//
//                                stateId = response.body()!!.data!!.profiledata!!.state.toString()
//                                districtId = response.body()!!.data!!.profiledata!!.district.toString()
//
//                                if (!response.body()!!.data!!.profiledata!!.pincode.isNullOrBlank()) {
//                                    textView116.setText(response.body()!!.data!!.profiledata!!.pincode)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        })
//
//    }
//
//    private fun getList() {
//        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
//        val builder = AlertDialog.Builder(activity!!)
//        builder.setTitle("Add Photo!")
//        builder.setItems(items) { dialog, item ->
//            when {
//                items[item] == "Take Photo" -> openCamera()
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
//    private fun openCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, 0)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscriber_profile_edit, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
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
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes)
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
//    private var imageVlaue: String = ""
//
//    private fun setImage(bitmap: Bitmap) {
//        imageVlaue = HelperMethods.convertBitmap(bitmap)
//
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//
//        Glide.with(activity!!).asBitmap().load(stream.toByteArray()).into(imageView22)
//        /*imageView22.setImageBitmap(bitmap)*/
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            10003 -> if (grantResults.contains(PermissionChecker.PERMISSION_DENIED)) {
//                HelperMethods.toastit(activity!!, "Permission Needed to Upload Image")
//            } else {
//                getList()
//            }
//        }
//    }
//
//}