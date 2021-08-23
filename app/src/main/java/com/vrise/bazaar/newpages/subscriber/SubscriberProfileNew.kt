package com.vrise.bazaar.newpages.subscriber

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.View
import androidx.preference.Preference
import com.vrise.R

import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE

import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopUp
import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
import com.vrise.bazaar.newpages.utilities.Validator.hasText
import com.vrise.bazaar.newpages.utilities.Validator.isEmail
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_custom_toolbar.*
import kotlinx.android.synthetic.main.subscriber_prof.*
import java.io.*

class SubscriberProfileNew : InitMain() {

    var stateId: String = ""
    var districtId: String = ""
    var stateList: ArrayList<Request>? = null
    var districtList: ArrayList<Request>? = null
    private var imageVlaue: String = ""

    val neededPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun initView() {

    }

    override fun initModel() {

    }

    override fun initControl() {
        try {
            page_tiitle.text = "Subscriber Profile"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (hasConnection(this@SubscriberProfileNew)) {
            getState()
            getDato()
        }

        textView1161.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (hasConnection(this@SubscriberProfileNew)) {
                    if (stateId.isBlank()) {
                        toastit(this@SubscriberProfileNew, "Select a state")
                    } else {
                        textView1161.error = null
                        apiService?.getDistricts(Request(state = stateId))?.enqueue(object : CallbackClient(this@SubscriberProfileNew,
                                object : Interfaces.Callback {
                                    override fun additionalData(display: String?, logout: Boolean) {

                                    }

                                    override fun failed(t: Throwable) {
                                        t.printStackTrace()
                                    }

                                    override fun success(response: Response?, data: Data?, state: Boolean) {
                                        if (state) {
                                            if (data != null) {
                                                if (data.districts != null) {
                                                    if (data.districts.isNotEmpty()) {
                                                        districtList = ArrayList()
                                                        for (i in 0 until data.districts.size) {
                                                            districtList!!.add(Request(
                                                                    name = data.districts[i]!!.name,
                                                                    code = data.districts[i]!!.id
                                                            ))
                                                        }
                                                        if (districtList != null && districtList!!.isNotEmpty()) {
                                                            HelperMethods.openPopUp(this@SubscriberProfileNew, districtList, textView1161, "Districts", object : Interfaces.ReturnId {
                                                                override fun returnId(string: String) {
                                                                    districtId = string
                                                                }
                                                            })
                                                        } else {
                                                            HelperMethods.toastit(this@SubscriberProfileNew, "No districts available")
                                                        }
                                                    } else {
                                                        HelperMethods.toastit(this@SubscriberProfileNew, "No districts available.")
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }) {})
                    }
                }
            }
        })
        textView1153.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                textView1153.error = null
                if (stateList != null) {
                    openPopUp(this@SubscriberProfileNew, stateList, textView1153, "States", object : Interfaces.ReturnId {
                        override fun returnId(string: String) {
                            stateId = string
                            textView1161.text = ""
                            districtId = ""
                        }
                    })
                }
            }
        })
        textView117.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                finish()
            }
        })
        imageView22.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (HelperMethods.checkIfPermissionsGranted(this@SubscriberProfileNew, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))) {
                    getList()
                } else {
                    ActivityCompat.requestPermissions(this@SubscriberProfileNew, neededPermissions, 10003)
                }
            }
        })
        textView118.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (hasConnection(this@SubscriberProfileNew)) {
                    progressBar.visibility = View.VISIBLE
                    if (
                            hasText(arrayOf(textView1081, textView114, textView1151, textView1151, textView1152, textView1154, textView116)) &&
                            isEmail(textView114) &&
                            hasText(textView1153) &&
                            stateId.isNotBlank() &&
                            hasText(textView1161) &&
                            districtId.isNotBlank()
                    ) {
                        print(Request(
                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(this@SubscriberProfileNew, DATAFILE, Constants.ID),
                                name = textView1081.text.toString(),
                                email = textView114.text.toString(),
                                mobile = textView113.text.toString(),
                                address2 = textView1152.text.toString(),
                                state = stateId,
                                district = districtId,
                                address = textView1151.text.toString(),
                                pincode = textView116.text.toString(),
                                profile_pic = imageVlaue,
                                postOffice = textView1154.text.toString()
                        ))
                        apiService?.updateSubscriberProfile(Request(
                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(this@SubscriberProfileNew, DATAFILE, Constants.ID),
                                name = textView1081.text.toString(),
                                email = textView114.text.toString(),
                                mobile = textView113.text.toString(),
                                address = textView1151.text.toString(),
                                address2 = textView1152.text.toString(),
                                state = stateId,
                                district = districtId,
                                pincode = textView116.text.toString(),
                                profile_pic = imageVlaue,
                                postOffice = textView1154.text.toString()
                        ))?.enqueue(object : CallbackClient(this@SubscriberProfileNew, object : Interfaces.Callback {
                            override fun additionalData(display: String?, logout: Boolean) {

                            }

                            override fun failed(t: Throwable) {
                                progressBar.visibility = View.GONE
                                t.printStackTrace()
                            }

                            override fun success(response: Response?, data: Data?, state: Boolean) {
                                if (state) {
//                                    navigateTo(this@SubscriberProfileNew, Constants.ACTIVITY, false, null, SubContainer::class.java, null, null, "")
                                } else {

                                }
                                progressBar.visibility = View.GONE
                            }
                        }) {})
                    } else {
                        textView1153.error = if (stateId.isBlank()) {
                            "Select State"
                        } else {
                            null
                        }
                        textView1161.error = if (districtId.isBlank()) {
                            "Select District"
                        } else {
                            null
                        }
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun getList() {
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(this@SubscriberProfileNew)
        builder.setTitle("Add Photo!")
        builder.setItems(items) { dialog, item ->
            when {
                items[item] == "Take Photo" -> openCamera()
                items[item] == "Choose from Library" -> LibraryOpen()
                items[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun LibraryOpen() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select File"), 1)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                0 -> fromCamera(data)
                1 -> fromLib(data)
            }
            Activity.RESULT_CANCELED -> Log.e("TAG", "Selecting picture cancelled")
        }
    }

    private fun fromCamera(data: Intent?) {

        val thumbnail = data!!.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes)
        val destination = File(Environment.getExternalStorageDirectory(), System.currentTimeMillis().toString() + ".jpg")
        val fo: FileOutputStream
        try {
            destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        setImage(thumbnail)
    }

    private fun fromLib(data: Intent?) {
        var bm: Bitmap? = null
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        setImage(bm!!)
    }

    private fun setImage(bitmap: Bitmap) {
        imageVlaue = HelperMethods.convertBitmap(bitmap)
        imageView22.setImageBitmap(bitmap)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10003 -> if (grantResults.contains(PermissionChecker.PERMISSION_DENIED)) {
                HelperMethods.toastit(this@SubscriberProfileNew, "Permission Needed to Upload Image")
            } else {
                getList()
            }
        }
    }

    private fun getState() {
        apiService?.getStates()?.enqueue(object : CallbackClient(this@SubscriberProfileNew, object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {

            }

            override fun failed(t: Throwable) {
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                if (state) {
                    if (data != null) {
                        if (data.states != null) {
                            if (data.states.isNotEmpty()) {
                                stateList = ArrayList()
                                for (i in 0 until data.states.size) {
                                    stateList!!.add(Request(
                                            name = data.states[i]!!.state,
                                            code = data.states[i]!!.id
                                    ))
                                }
                            }
                        }
                    }
                }
            }
        }) {})
    }

    private fun getDato() {
        apiService?.getSubscriberProfile(Request(utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(this@SubscriberProfileNew, DATAFILE, Constants.ID)))?.enqueue(object : CallbackClient(this@SubscriberProfileNew, object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean) {

            }

            override fun failed(t: Throwable) {
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                if (state) {
                    if (data != null) {
                        if (data.profiledata != null) {
                            Picasso.get().load(data.profiledata.imgLink.toString()).fit().into(imageView22)
                            textView1081.setText(data.profiledata.name)
                            textView111.text = data.profiledata.agent_code
                            textView112.text = data.profiledata.code
                            textView113.text = data.profiledata.mobile
                            textView114.setText(data.profiledata.email)

                            if (!data.profiledata.address.isNullOrBlank()) {
                                textView1151.setText(data.profiledata.address)
                            }
                            if (!data.profiledata.address2.isNullOrBlank()) {
                                textView1152.setText(data.profiledata.address2)
                            }
                            if (!data.profiledata.stateName.isNullOrBlank()) {
                                textView1153.text = data.profiledata.stateName
                            }
                            if (!data.profiledata.district_name.isNullOrBlank()) {
                                textView1161.text = data.profiledata.district_name
                            }
                            if (!data.profiledata.pincode.isNullOrBlank()) {
                                textView116.setText(data.profiledata.pincode)
                            }
                            if (!data.profiledata.post_office.isNullOrBlank()) {
                                textView1154.setText(data.profiledata.post_office)
                            }

                            stateId = data.profiledata.state.toString()
                            districtId = data.profiledata.district.toString()
                        }
                    }
                }
            }

        }) {})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subscriber_prof)
        setInitializer()
    }

}
