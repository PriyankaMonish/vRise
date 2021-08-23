package com.vrise.bazaar.newpages.agency.menus

//import android.Manifest
//import android.app.Activity
//import android.app.Dialog
//import android.content.Intent
//import android.graphics.Bitmap
//import android.net.Uri
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import com.google.android.material.textfield.TextInputEditText
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.content.PermissionChecker.PERMISSION_DENIED
//import androidx.appcompat.app.AlertDialog
//import android.text.InputFilter
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.Window
//import android.widget.CompoundButton
//import android.widget.ProgressBar
//import android.widget.TextView
//import com.vrise.R
////import com.vrise.bazaar.newpages.containers.AgentContainer
////import com.vrise.bazaar.newpages.registration.OtpPage
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.HelperMethods.checkIfPermissionsGranted
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.Validator.isEmail
//import com.vrise.bazaar.newpages.utilities.Validator.isPhone
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.fragment_agent_profile.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback
//import java.io.*
//
//class AgentProfile : InitSub() {
//
//    override fun networkAvailable() {
//        getDatas()
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    val neededPermissions = arrayOf(
//        Manifest.permission.CAMERA,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    )
//    var imageVlaue = ""
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
//    override fun initControl() {
//        activity!!.page_title.text = "Agent Profile"
//
//        getState()
//
//        textView30.filters = arrayOf<InputFilter>(HelperMethods.EmojiExcludeFilter())
//
//        imageView4.setOnClickListener {
//            if (checkIfPermissionsGranted(
//                    activity!!,
//                    arrayOf(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE
//                    )
//                )
//            ) {
//                getImage()
//            } else {
//                ActivityCompat.requestPermissions(activity!!, neededPermissions, 10003)
//            }
//        }
//        textView36.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                fragmentManager?.popBackStack()
//            }
//        })
//        textView37.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                progressBar.visibility = View.VISIBLE
//                if (
//                    hasText(arrayOf(textView30, textView32, textView33, textView35, textView46, textView34)) &&
//                    isEmail(textView32) &&
//                    hasText(textView38) &&
//                    stateId.isNotBlank() &&
//                    hasText(textView39) &&
//                    districtId.isNotBlank()
//                ) {
//                    progressBar.visibility = View.VISIBLE
//                    print(
//                        Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
//                                activity,
//                                DATAFILE,
//                                Constants.ID
//                            ),
//                            name = textView30.text.toString(),
//                            email = textView32.text.toString(),
//                            code = textView29.text.toString(),
//                            address = textView33.text.toString(),
//                            state = stateId,
//                            address2 = textView35.text.toString(),
//                            district = districtId,
//                            profile_pic = imageVlaue,
//                            pincode = textView34.text.toString(),
//                            mobile = textView31.text.toString(),
//                            postOffice = textView46.text.toString()
//                        )
//                    )
//                    apiService?.profileupdate(
//                        Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
//                                activity,
//                                DATAFILE,
//                                Constants.ID
//                            ),
//                            name = textView30.text.toString(),
//                            code = textView29.text.toString(),
//                            email = textView32.text.toString(),
//                            address = textView33.text.toString(),
//                            state = stateId,
//                            address2 = textView35.text.toString(),
//                            district = districtId,
//                            profile_pic = imageVlaue,
//                            pincode = textView34.text.toString(),
//                            mobile = textView31.text.toString(),
//                            postOffice = textView46.text.toString()
//                        )
//                    )?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    try {
//                                        (activity!! as AgentContainer).times = 0
//                                    } catch (e: Exception) {
//                                        e.printStackTrace()
//                                    }
//                                    /*toastit(activity!!, "Profile Updated")*/
//                                    progressBar.visibility = View.GONE
//                                    getDatas()
//                                }
//                            }
//                        }
//                    })
//                } else {
//                    progressBar.visibility = View.GONE
//
//                    textView38.error = if (stateId.isBlank()) {
//                        "Select State"
//                    } else {
//                        null
//                    }
//                    textView39.error = if (districtId.isBlank()) {
//                        "Select District"
//                    } else {
//                        null
//                    }
//
//                }
//            }
//        })
//        textView39.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (stateList != null)
//                    textView39.error = null
//                if (stateId.isBlank()) {
//                    toastit(activity!!, "select a state")
//                } else {
//                    getDistrict()
//                }
//            }
//        })
//        textView38.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                if (stateList != null)
//                    textView38.error = null
//                if (stateList.isNotEmpty()) {
//                    openPopUp(activity!!, stateList, textView38, "States", object : Interfaces.ReturnId {
//                        override fun returnId(string: String) {
//                            stateId = string
//                            textView39.text = ""
//                            districtId = ""
//                        }
//                    })
//                }
//            }
//        })
//        checkBox2.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//                if (!p1) {
//                    view11.visibility = View.VISIBLE
//                    textView36.visibility = View.GONE
//                    textView37.visibility = View.GONE
//                    view11.requestFocus()
//                } else {
//                    textView37.visibility = View.VISIBLE
//                    textView36.visibility = View.VISIBLE
//                    view11.visibility = View.GONE
//                    view11.clearFocus()
//                }
//            }
//        })
//        textView31.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val dialog = Dialog(requireContext())
//                dialog.setCancelable(false)
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                activity?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//                dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, android.R.color.transparent))
//                dialog.setContentView(R.layout.change_user)
//                val textView159 = dialog.findViewById<TextInputEditText>(R.id.textView159)
//                val textView160 = dialog.findViewById<TextView>(R.id.textView160)
//                val textView161 = dialog.findViewById<TextView>(R.id.textView161)
//                val progressBar = dialog.findViewById<ProgressBar>(R.id.progressBar)
//                textView161.setOnClickListener(object : ClickListener() {
//                    override fun onOneClick(v: View) {
//                        if (isPhone(textView159)) {
//                            progressBar.visibility = View.VISIBLE
//                            print(Request(mobile = textView159.text.toString()))
//
//                            apiService?.registerMobileNumber(Request(mobile = textView159.text.toString()))
//                                ?.enqueue(object : Callback<Response> {
//                                    override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                        if (activity != null && isAdded) {
//                                            progressBar.visibility = View.GONE
//                                            t!!.printStackTrace()
//                                        }
//                                    }
//
//                                    override fun onResponse(
//                                        call: Call<Response>?,
//                                        response: retrofit2.Response<Response>?
//                                    ) {
//                                        if (activity != null && isAdded) {
//                                            if (hasData(activity!!, response)) {
//                                                if (response!!.body()!!.data != null) {
//                                                    if (response.body()!!.data!!.otp != null) {
//                                                        dialog.dismiss()
//                                                        val bundle = Bundle()
//                                                        bundle.putString(Constants.FROM, "1")
//                                                        bundle.putString(Constants.NUMBER, textView159.text.toString())
//                                                        bundle.putString(Constants.OTP, response.body()!!.data!!.otp)
//                                                        HelperMethods.navigateTo(
//                                                            activity!!,
//                                                            Constants.FRAGMENT,
//                                                            true,
//                                                            OtpPage(),
//                                                            null,
//                                                            bundle,
//                                                            null,
//                                                            ""
//                                                        )
//                                                    }
//                                                }
//                                                progressBar.visibility = View.GONE
//                                            } else {
//                                                progressBar.visibility = View.GONE
//                                            }
//                                        }
//                                    }
//                                })
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
//                    if (hasData(activity!!, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.districts != null) {
//                                if (response.body()!!.data!!.districts!!.isNotEmpty()) {
//                                    districtList = ArrayList()
//                                    for (i in 0 until response.body()!!.data!!.districts!!.size) {
//                                        districtList.add(
//                                            Request(
//                                                name = response.body()!!.data!!.districts!![i]!!.name,
//                                                code = response.body()!!.data!!.districts!![i]!!.id
//                                            )
//                                        )
//                                    }
//                                    if (districtList.isEmpty()) {
//                                        toastit(activity!!, "No districts available.")
//                                    } else {
//                                        openPopUp(
//                                            activity!!,
//                                            districtList,
//                                            textView39,
//                                            "Districts",
//                                            object : Interfaces.ReturnId {
//                                                override fun returnId(string: String) {
//                                                    districtId = string
//                                                }
//                                            })
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
//    private fun getState() {
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
//                                        stateList.add(
//                                            Request(
//                                                name = response.body()!!.data!!.states!![i]!!.state,
//                                                code = response.body()!!.data!!.states!![i]!!.id
//                                            )
//                                        )
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
//        setImage(bm!!, data!!.data)
//    }
//
//    private fun fromCamera(data: Intent?) {
//        val thumbnail = data!!.extras!!.get("data") as Bitmap
//        val bytes = ByteArrayOutputStream()
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes)
//        val destination =
//            File(Environment.getExternalStorageDirectory(), System.currentTimeMillis().toString() + ".jpg")
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
//        setImage(thumbnail, data.data)
//    }
//
//    private fun setImage(bitmap: Bitmap, data: Uri?) {
//        imageVlaue = HelperMethods.convertBitmap(bitmap)
////        imageView4.setImageURI(data.toString())
//        imageView4.setImageBitmap(bitmap)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            10003 -> if (grantResults.contains(PERMISSION_DENIED)) {
//                toastit(activity!!, "Permission Needed to Upload Image")
//            } else {
//                getImage()
//            }
//        }
//    }
//
//    private fun getDatas() {
//        apiService?.getAgentProfile(
//            Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
//            )
//        )?.enqueue(object : Callback<Response> {
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
//                                val data = response.body()!!.data!!.profiledata
//                                //imageView4.setImageURI(data!!.imgLink.toString())
//                                Picasso.get().load(data!!.imgLink).placeholder(R.drawable.ic_placeholder).into(imageView4)
//                                textView29.text = data.code.toString()
//                                textView30.setText(data.name.toString())
//                                textView31.text = data.mobile.toString()
//                                textView32.setText(data.email.toString())
//                                textView33.setText(data.address.toString())
//                                textView34.setText(data.pincode.toString())
//                                textView35.setText(data.address2.toString())
//                                textView38.text = data.stateName.toString()
//                                textView39.text = data.district_name.toString()
//                                textView46.setText(data.post_office.toString())
//                                stateId = data.state.toString()
//                                districtId = data.district.toString()
//                                checkBox2.isChecked = false
//                            }
//                        }
//                    }
//                }
//            }
//        })
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_agent_profile, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}
