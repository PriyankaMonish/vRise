package com.vrise.bazaar.newpages.top.temps

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*


object Misc {

    fun displayImagePopUp(context: Context, url: String?) {
        val builder = Dialog(context)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.setOnDismissListener { dialogInterface ->
            dialogInterface.dismiss()
        }
        val imageViews = ImageView(context)
        Glide.with(context).load(url).into(imageViews) //.placeholder(R.drawable.ic_placeholder)

        Picasso.get()
            .load(url)
            /*.placeholders(com.ibrbazaar.R.drawable.placeholder)*/
            .transform(blurTransformation(context))
            .fit()
            .into(imageViews, object : Callback {
                override fun onError(e: java.lang.Exception) {

                }

                override fun onSuccess() {
                    Picasso.get()
                        .load(url)
                        .placeholder(imageViews.drawable)
                        .fit()
                        .into(imageViews)
                }
            })

        builder.addContentView(
            imageViews, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        builder.show()
    }

    fun showBlur(context: Context?, id: Int?, imageView: ImageView) {
        context?.let {
            if (id != null) {
                Picasso.get()
                    .load(id)
                    .placeholder(R.drawable.shape_white_rounded_category)
                    .transform(blurTransformation(it))
                    .fit()
                    .into(imageView, object : Callback {
                        override fun onError(e: java.lang.Exception) {

                        }

                        override fun onSuccess() {
                            Picasso.get()
                                .load(id)
                                .placeholder(imageView.drawable)
                                .fit()
                                .into(imageView)
                        }
                    })
            }
        }
    }

    fun showBlur(context: Context?, url: String, imageView: ImageView) {
        context?.let {
            if (url.isNotBlank()) {
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.shape_white_rounded_category)
                    .transform(blurTransformation(it))
                    .fit()
                    .into(imageView, object : Callback {
                        override fun onError(e: java.lang.Exception) {

                        }

                        override fun onSuccess() {
                            Picasso.get()
                                .load(url)
                                .placeholder(imageView.drawable)
                                .fit()
                                .into(imageView)
                        }
                    })
            }
        }
    }

    fun println(any: Any?) {
        kotlin.io.println(GsonBuilder().setPrettyPrinting().create().toJson(any))
    }

    fun blurTransformation(context: Context): Transformation = object : Transformation {
        override fun transform(source: Bitmap): Bitmap? {
            val blurred = Blur.fastblur(context, source, 10)
            source.recycle()
            return blurred
        }

        override fun key(): String {
            return "blur()"
        }
    }

    fun createBadge(type: String, i1: Int) {

        /*val item = mTopViewModel?.getItem()

        var i = i1

        item?.let {
            i = when (type) {
                "cart" -> {
                    (it.cartCount ?: 0) + i1
                }
                "notifications" -> {
                    (it.noticicationCount ?: 0) + i1
                }
                "favs" -> {
                    (it.favouritesCount ?: 0) + i1
                }
                else -> {
                    i1
                }
            }
        }

        when(type){
            "cart" -> {
                mTopViewModel?.updateCarts(i)
            }
            "notifications"->{
                mTopViewModel?.updateNotificationCount(i)
            }
            "favs"->{
                mTopViewModel?.updateFavouriteCount(i)

            }
        }
        observer.onChanged(mTopViewModel?.getAll()?.value)*/
    }

    fun getToday(format: String): String {
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat(format, Locale.getDefault())
        val strDate = mdformat.format(calendar.time)
        return strDate
    }

    private var calendar: Calendar? = null
    var year: Int = 0
    var day: Int = 0
    var month: Int = 0

    fun datePicker(
        activity: FragmentActivity?,
        date: TextView?,
        observer: Interfaces.returnBundle
    ) {
        calendar = Calendar.getInstance()
        year = calendar!!.get(Calendar.YEAR)
        month = calendar!!.get(Calendar.MONTH)
        day = calendar!!.get(Calendar.DAY_OF_MONTH)

        val atePickerDialo = DatePickerDialog(
            activity!!,
            DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                date?.text = StringBuilder().append(i2).append("-").append(i1 + 1).append("-").append(i)
                observer.returnBundle(StringBuilder().append(i2).append("-").append(i1 + 1).append("-").append(i))
            }
            ,
            year,
            month,
            day
        )
        atePickerDialo.datePicker.minDate = System.currentTimeMillis() - 1000
        atePickerDialo.show()
    }

    fun noInternet(activity: FragmentActivity?) {
        activity?.let {
            HelperMethods.toastit(activity, activity.getString(R.string.no_internet))
        }
    }

    fun logoutfromapp(activity: FragmentActivity?, logout: Boolean) {
        if (logout) HelperMethods.logoutfromapp(activity)
    }

    fun display(activity: FragmentActivity?, display: String?) {
        display?.let {
            if (display.isNotBlank()) {
                HelperMethods.toastit(activity, display)
            }
        }
    }

    fun additionalData(activity: FragmentActivity?, display: String?, logout: Boolean) {
        display(activity, display)
        logoutfromapp(activity, logout)
    }

    fun checkInternet(activity: FragmentActivity?, internet: Boolean): Boolean {
        return when {
            internet -> true
            else -> {
                activity?.let {
                    noInternet(activity)
                }
                false
            }
        }
    }

    fun isLocEnabled(addDeliveryAddress: FragmentActivity): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lm = addDeliveryAddress.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return lm.isLocationEnabled
        } else {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                    val mode = Settings.Secure.getInt(
                        addDeliveryAddress.contentResolver,
                        Settings.Secure.LOCATION_MODE,
                        Settings.Secure.LOCATION_MODE_OFF
                    )
                    return (mode != Settings.Secure.LOCATION_MODE_OFF)
                }
                else -> {
                    val locationManager =
                        addDeliveryAddress.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    return !(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(
                        LocationManager.NETWORK_PROVIDER
                    ))
                }
            }
        }
    }



    fun getRealPathFromURI(activity: FragmentActivity?, uri : Uri) : String {
        val cursor = activity?.contentResolver?.query(uri, null, null, null, null) as Cursor
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex (MediaStore.Images.ImageColumns.DATA)
        val ksnvn= cursor.getString(idx)
        cursor.close()
        return ksnvn
    }

    fun getImageUri(inContext: Context?, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext?.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun showEnableLocationDialog(context: FragmentActivity?) {
        context?.let { activity ->
            val alert = AlertDialog.Builder(activity)
                .setTitle("Location")
                .setMessage("Please enable your phone's location")
                .setPositiveButton("OK") { dialog, which ->
                    activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    dialog.dismiss()
                }
                .setNegativeButton("CANCEL") { dialog, which ->
                    dialog.dismiss()
                }
            alert.show()
        }
    }

    internal class InternetCheck(private val onInternetChecked: (Boolean) -> Unit) : AsyncTask<Void, Void, Boolean>() {
        init {
            execute()
        }

        override fun doInBackground(vararg voids: Void): Boolean {
            return try {
                val sock = Socket()
                sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                sock.close()
                true
            } catch (e: IOException) {
                false
            }

        }

        override fun onPostExecute(internet: Boolean) {
            onInternetChecked(internet)
        }
    }
}