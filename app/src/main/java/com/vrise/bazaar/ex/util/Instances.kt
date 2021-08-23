package com.vrise.bazaar.ex.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Response
import com.vrise.bazaar.ex.model.newmodels.ShopsItem
import com.vrise.bazaar.ex.model.newmodels.SizeStockPriceItem
import com.vrise.bazaar.ex.model.submodels.DistrictsItem
import com.vrise.bazaar.ex.model.submodels.ShopBrandsItem
import com.vrise.bazaar.ex.model.submodels.SizeStockPrice
import com.vrise.bazaar.ex.model.submodels.StatesItem
import com.vrise.bazaar.ex.retrofit.PayEnvironment
import com.vrise.bazaar.ex.retrofit.RetroService
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.LandingScreen
import com.vrise.bazaar.ex.shop.containers.RegistrationContainer
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Values.ACTIVITY
import com.vrise.bazaar.ex.util.Values.FAILED
import com.vrise.bazaar.ex.util.Values.SUCCESS
import com.vrise.bazaar.ex.util.custom.BlurIt
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.custom_list_dialog_new.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.net.ssl.SSLHandshakeException

object Instances {
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    internal class InternetCheck(private val checked: (Boolean) -> Unit) :
        AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            return try {
                val sock = Socket()
                sock.connect(InetSocketAddress("8.8.8.8", 53), 7000)
                sock.close()
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            } catch (e: java.net.SocketTimeoutException) {
                e.printStackTrace()
                false
            } catch (e: java.net.ConnectException) {
                e.printStackTrace()
                false
            }/* catch (e: android.system.ErrnoException){
				e.printStackTrace()
				false
			}*/ catch (e: java.net.UnknownHostException) {
                e.printStackTrace()
                false
            } catch (e: SSLHandshakeException) {
                e.printStackTrace()
                false
            }
        }

        override fun onPostExecute(internet: Boolean) {
            checked(internet)
        }

        init {
            execute()
        }
    }

    fun today(format: String): String {
        val calendar = Calendar.getInstance()
        return SimpleDateFormat(format, Locale.ENGLISH).format(calendar.time).toString()
    }

    fun selectFutureDateDMY(
        activity: FragmentActivity?, selectedDate: String?, returns: Interfaces.ReturnAny
    ) {
        if (activity != null && !activity.isFinishing) {
            val calendar = Calendar.getInstance()
            if (!selectedDate.isNullOrBlank()) {
                try {
                    calendar.set(Calendar.YEAR, selectedDate.split("-")[2].trim().toInt())
                    calendar.set(Calendar.MONTH, selectedDate.split("-")[1].trim().toInt() - 1)
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDate.split("-")[0].trim().toInt())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val year = calendar?.get(Calendar.YEAR) ?: 1970
            val month = calendar?.get(Calendar.MONTH) ?: 1
            val day = calendar?.get(Calendar.DAY_OF_MONTH) ?: 1
            val datepickerDialog = DatePickerDialog(
                activity, DatePickerDialog.OnDateSetListener { _, year1, month1, dayOfMonth ->
                    returns.getValue(
                        StringBuilder().append(dayOfMonth).append("-").append(
                            month1 + 1
                        ).append("-").append(
                            year1
                        )
                    )
                }, year, month, day
            )

            datepickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datepickerDialog.show()
        }
    }

    class InputFilterMax(private var minimumValue: Int, private var maximumValue: Int) :
        InputFilter {
        override fun filter(
            source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int
        ): CharSequence? {
            try {
                val input = Integer.parseInt(
                    dest.subSequence(0, dstart).toString() + source + dest.subSequence(
                        dend, dest.length
                    )
                )
                if (isInRange(minimumValue, maximumValue, input)) return null;
            } catch (nfe: NumberFormatException) {
            }
            return ""
        }

        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) {
                c in a..b
            } else {
                c in b..a
            }
        }

    }

    fun toast(view: View?, string: String) {
        view?.let {
            Snackbar.make(view, "$string", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun toast(activity: Activity?, string: String?) {
        activity?.let {
            if (string.isNullOrBlank()) {
            } else {
                Toast.makeText(activity, "$string", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showSnackbar(
        activity: FragmentActivity?,
        snackStrId: Int,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(
            (activity?.findViewById(android.R.id.content)) as View,
            activity.getString(snackStrId),
            Snackbar.LENGTH_INDEFINITE
        )
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(activity.getString(actionStrId), listener)
        }
        snackbar.show()
    }

    fun snack(activity: Activity?, string: String?) {
        activity?.let {
            if (string.isNullOrBlank()) {
            } else {
                Snackbar.make(
                    activity.findViewById(android.R.id.content) as View,
                    string,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun logoutFromApp(displayValue: String?, boolean: Boolean, activity: Activity?) {
        activity?.let {
            if (displayValue != null) {
                if (displayValue.isNotBlank()) {
//                    toast(it, displayValue)
                }
            }
            if (boolean) {
                logoutFromApp(it)
            }
        }
    }

    private fun logoutFromApp(activity: Activity) {
        //Todo Clear All Pref and Close App
        Preference.clear(activity, DATAFILE)
        ActivityCompat.finishAffinity(activity)
        DirectIt.navigateTo(
            activity as FragmentActivity,
            ACTIVITY,
            false,
            null,
            LandingScreen::class.java,
            null,
            arrayOf(Intent.FLAG_ACTIVITY_CLEAR_TOP),
            ""
        )
    }

    fun permissions() = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    fun displayUpGradeDialog(activity: FragmentActivity, version: String?) {
        val aDialog1 = android.app.AlertDialog.Builder(activity)
        aDialog1.setTitle("New update available!")
        aDialog1.setMessage("A new version $version is available to download. Downloading this update will get you the latest features, improvements and bug fixes.")
        aDialog1.setCancelable(false)
        aDialog1.setIcon(R.mipmap.vr2)
        var alertDialog1: android.app.AlertDialog? = null
        aDialog1.setPositiveButton("UPDATE") { arg0, arg1 ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + Values.APP_ID)
            )
            activity.startActivity(intent)
            alertDialog1?.dismiss()
            activity.finish()
        }
        alertDialog1 = aDialog1.create()
        alertDialog1.setCanceledOnTouchOutside(false)
        alertDialog1.show()
    }

    fun hasSuccessData(response: retrofit2.Response<Response>?): Boolean {
        val valid: Boolean
        if (response != null) {
            valid = if (response.isSuccessful) {
                if (response.body() != null) {
                    println(response.body())
                    if (response.body()?.message != null) {
                        when {
                            response.body()?.message?.toLowerCase(Locale.ENGLISH) == SUCCESS -> true
                            response.body()?.message?.toLowerCase(Locale.ENGLISH) == FAILED -> false
                            else -> false
                        }
                    } else {
                        false
                    }
                } else {
                    false
                }
            } else {
                false
            }
        } else {
            valid = false
        }
        return valid
    }

    fun fullscreen(context: Activity?) {
        context?.let { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            activity.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window?.statusBarColor =
                    ContextCompat.getColor(activity, R.color.colorPrimaryNew)
            }
        }
    }

    fun serviceApi(activity: FragmentActivity?): RetroService? {
        return if (activity != null) {
            if (activity is RegistrationContainer) {
                activity.apiService()
            } else if (activity is DashBoardContainer) {
                activity.apiService()
            } else if (activity is BazaarContainer) {
                activity.apiService()
            } else {
                null
            }
        } else {
            null
        }
    }

    fun printAny(request: Any?) {
        if (BaseApp.appInstance?.getAppEnvironment() == PayEnvironment.STAGING) {
            try {
                println(GsonBuilder().setPrettyPrinting().create().toJson(request))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggle(duration: Long, parent: ViewGroup, child: View, visibility: Boolean, gravity: Int) {
        val transition = androidx.transition.Slide(gravity) //Gravity.TOP
        transition.duration = duration
        transition.addTarget(child)
        androidx.transition.TransitionManager.beginDelayedTransition(parent, transition)
        child.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    fun isPhone(activity: FragmentActivity?, phone: String?): Boolean {
        if (!isPhone(phone)) {
            toast(
                activity,
                activity?.getString(R.string.not_a_phone_number) ?: "Phone number invalid"
            )
        }
        return isPhone(phone)
    }

    fun isPhone(phone: String?): Boolean {
        return if (!phone.isNullOrBlank()) {
            phone.toString().trim().isPhoneNumber()
        } else {
            false
        }
    }

    private fun String.isPhoneNumber() = length in 4..10 && all { it.isDigit() }

    fun toolbarVisibility(activity: FragmentActivity?, visibility: Int) {
        if (activity != null) {
            if (activity is BazaarContainer) {
                if (visibility == View.GONE) {
                    activity.toolbar.setBackgroundColor(
                        ContextCompat.getColor(
                            activity, R.color.colorAccentNew
                        )
                    )
                    //(activity as ContainerShop).toolbars.animate().translationY(-150F).duration = 300
                } else if (visibility == View.VISIBLE) {
                    activity.toolbar.setBackgroundColor(
                        ContextCompat.getColor(
                            activity, android.R.color.transparent
                        )
                    )
                    //(activity as ContainerShop).toolbars.animate().translationY(0F).duration = 0
                }
            }
        }

    }

    fun String.floatToInt(): Int {
        return this.toFloat().toInt()
    }

    fun zoomImage(context: FragmentActivity?, url: String?) {
        context?.let {
            val builder = Dialog(context)
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            builder.setOnDismissListener { dialogInterface ->
                dialogInterface.dismiss()
            }
            val imageViews = ImageView(context)
            showBlur(context, url.toString(), imageViews)
            builder.addContentView(
                imageViews, RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
            builder.show()
        }
    }

    fun showBlur(context: Context?, id: Int?, imageView: ImageView) {
        context?.let {
            if (id != null) {
                Picasso.get().load(id).placeholder(R.drawable.ite).transform(
                    blurTransformation(it)
                ).fit().into(imageView, object : Callback {
                    override fun onError(e: java.lang.Exception) {

                    }

                    override fun onSuccess() {
                        Picasso.get().load(id).placeholder(imageView.drawable).fit().into(imageView)
                    }
                })
            }
        }
    }

    fun changeDateFormat(oldFormat: String, newFormat: String, dateValue: String): String {
        return changeDateFormat(oldFormat, newFormat, dateValue, "")
    }

    fun changeDateFormat(
        oldFormat: String,
        newFormat: String,
        dateValue: String,
        default: String
    ): String {
        var result = if (default.isBlank()) "" else default

        if (dateValue.isBlank() || dateValue == "null") {
        } else {
            val formatterOld = SimpleDateFormat(oldFormat, Locale.ENGLISH)
            val formatterNew = SimpleDateFormat(newFormat, Locale.ENGLISH)
            var date: Date? = null
            try {
                date = formatterOld.parse(dateValue)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            if (date != null) {
                result = formatterNew.format(date)
            }
        }
        return result
    }

    @BindingAdapter("url", "context")
    @JvmStatic
    fun showBlur(imageView: ImageView, url: String, context: Context?) {
        context?.let {
            if (url.isNotBlank() && url != "null") {
                Picasso.get().load(url).placeholder(R.drawable.ite).transform(
                    blurTransformation(it)
                ).fit().into(imageView, object : Callback {
                    override fun onError(e: java.lang.Exception) {

                    }

                    override fun onSuccess() {
                        Picasso.get().load(url).placeholder(imageView.drawable).fit()
                            .into(imageView)
                    }
                })
            }
        }
    }

    @BindingAdapter("rating")
    @JvmStatic
    fun setRating(view: RatingBar?, rating: String?) {
        if (view != null) {
            if (rating.isNullOrBlank()) {
                view.progress = 0
            } else {
                try {
                    view.progress = rating.toInt() * 2
                } catch (e: java.lang.Exception) {
                    view.progress = 0
                }
            }
        }
    }

    fun showBlur(context: Context?, url: String, imageView: ImageView?) {
        context?.let {
            if (imageView != null) {
                if (url.isNotBlank() && url != "null") {
                    Picasso.get().load(url).placeholder(R.drawable.ite).transform(
                        blurTransformation(it)
                    ).fit().into(imageView, object : Callback {
                        override fun onError(e: java.lang.Exception) {

                        }

                        override fun onSuccess() {
                            Picasso.get().load(url).placeholder(imageView.drawable).fit()
                                .into(imageView)
                        }
                    })
                }
            }
        }
    }

    fun showSettingspermissionDialog(
        activity: FragmentActivity?, extra: String?, title: String?, content: String?
    ) {
        AlertDialog.Builder(activity).setTitle(title).setMessage(content).setCancelable(true)
            .setPositiveButton("ok") { p0, p1 ->
                p0.dismiss()
                activity?.startActivity(Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", activity.packageName, null)
                    activity.intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity.intent?.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    activity.intent?.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                })
            }.show()
    }

    fun hideKeyboard(activity: Activity?) {
        try {
            activity?.let {
                val inputManager =
                    it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val currentFocusedView = it.currentFocus
                if (currentFocusedView != null) {
                    inputManager.hideSoftInputFromWindow(
                        currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hasPermission(activity: Context, permission: String) = ActivityCompat.checkSelfPermission(
        activity, permission
    ) == PackageManager.PERMISSION_GRANTED

    fun checkIfPermissionsGranted(context: FragmentActivity?, array: Array<String>): Boolean {
        if (context != null && !context.isFinishing) {
            val valid = arrayOfNulls<Boolean>(array.size)
            for (i in 0 until array.size) valid[i] = false
            for (i in 0 until array.size) {
                valid[i] = ContextCompat.checkSelfPermission(
                    context, array[i]
                ) == PackageManager.PERMISSION_GRANTED
            }
            return !valid.contains(false) && !valid.contains(null)
        } else {
            return false
        }
    }

    fun getImageUri(inContext: Context?, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext?.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI(activity: FragmentActivity?, uri: Uri): String {
        /*val cursor = activity?.contentResolver?.query(uri, null, null, null, null) as Cursor
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val ksnvn = cursor.getString(idx)
        cursor.close()*/
        var ksnvn = ""
        activity?.contentResolver?.query(uri, null, null, null, null)?.use { cursor ->
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            ksnvn = cursor.getString(idx)
        }
        return ksnvn
    }

    fun convertBitmap(bitmap: Bitmap): String {
        var baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        var b: ByteArray = baos.toByteArray()
        var temp = ""
        try {
            System.gc()
            temp = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: OutOfMemoryError) {
            baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos)
            b = baos.toByteArray()
            temp = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT)
            Log.e("EWN", "Out of memory error catched")
        }
        return temp
    }

    private fun blurTransformation(context: Context): Transformation = object : Transformation {
        override fun transform(source: Bitmap): Bitmap? {
            val blurred = BlurIt.fastblur(context, source, 10)
            source.recycle()
            return blurred
        }

        override fun key(): String {
            return "blur()"
        }
    }

    fun openListPoPUp(
        activity: FragmentActivity?,
        arrayList: ArrayList<Any?>,
        textView112: View?,
        header: String?,
        returnData: Interfaces.ReturnAny
    ) {
        activity?.let {
            val dialog = BottomSheetDialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.custom_list_dialog_new)
            dialog.textView223.visibility = View.GONE
            dialog.textView223.text = header
            if (!header.isNullOrBlank()) {
                dialog.textView223.visibility = View.VISIBLE
            }
            val recyclerView16 = dialog.findViewById<RecyclerView>(R.id.recyclerView16)
            recyclerView16?.layoutManager = LinearLayoutManager(activity)
            if (arrayList != null) {
                if (arrayList.isNotEmpty()) {
                    val adapter = CustomStringListAdapter(activity,
                        arrayList,
                        dialog,
                        object : Interfaces.ReturnData {
                            override fun getValue(key: String, value: String) {
                                returnData.getValue(value)
                            }
                        },
                        object : Interfaces.ReturnAny {
                            override fun getValue(values: Any?) {
                                when (values) {
                                    is StatesItem -> returnData.getValue(values)
                                    is DistrictsItem -> returnData.getValue(values)
                                    is ShopsItem -> returnData.getValue(values)
                                    is SizeStockPriceItem -> returnData.getValue(
                                        values
                                    )
                                    is com.vrise.bazaar.ex.model.submodels.SizeStockPriceItem -> returnData.getValue(values)
                                    is SizeStockPrice -> returnData.getValue(values)
                                    is ShopBrandsItem -> returnData.getValue(values)
                                }
                            }
                        })
                    recyclerView16?.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            dialog.show()
        }
    }

    fun isGpsEnabled(activity: FragmentActivity?): Boolean {
        val manager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            manager.isLocationEnabled
        } else {
            /*manager.isProviderEnabled(LocationManager.GPS_PROVIDER)*/
            !(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !manager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            ))
        }
    }

    class CustomStringListAdapter(
        context: FragmentActivity,
        private val arrayList: List<Any?>?,
        private val dialog: Dialog?,
        private val observerData: Interfaces.ReturnData?,
        private val observerNew: Interfaces.ReturnAny?
    ) : RecyclerView.Adapter<CustomStringListAdapter.ViewHolder>() {
        private val context: Context = context

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            arrayList?.let { arrayList ->
                when {
                    arrayList[0] is String? -> {
                        holder.text1.setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(
                                context, R.drawable.default_dot
                            ), null, null, null
                        )
                        holder.text1.compoundDrawablePadding = 16
                        holder.text1.text = (Math.round(
                            arrayList[holder.adapterPosition].toString().toDouble()
                        )).toString()
//               (arrayList[holder.adapterPosition].toString())
                        holder.container.setOnClickListener {
                            observerData?.getValue(
                                holder.adapterPosition.toString(),
                                arrayList[holder.adapterPosition].toString()
                            )
                            dialog?.dismiss()
                        }
                    }
                    arrayList[0] is StatesItem -> {
                        holder.text1.text =
                            (arrayList[holder.adapterPosition] as StatesItem).state.toString()
                        holder.container.setOnClickListener {
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }
                    }

                    arrayList[0] is ShopsItem -> {
                        holder.text1.text =
                            (arrayList[holder.adapterPosition] as ShopsItem).storeName.toString()
                        holder.container.setOnClickListener {
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }
                    }
                    arrayList[0] is DistrictsItem -> {
                        holder.text1.text =
                            (arrayList[holder.adapterPosition] as DistrictsItem).name.toString()
                        holder.container.setOnClickListener {
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }
                    }
                    arrayList[0] is com.vrise.bazaar.ex.model.submodels.SizeStockPriceItem -> {
                        holder.text1.setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(
                                context, R.drawable.ic_credit_old
                            ), null, null, null
                        )
                        holder.text1.compoundDrawablePadding = 16
                        holder.text1.text =
                            ((arrayList[holder.adapterPosition] as com.vrise.bazaar.ex.model.submodels.SizeStockPriceItem).sizeName.toString()) + "   :   " + "₹ " + ((arrayList[holder.adapterPosition] as com.vrise.bazaar.ex.model.submodels.SizeStockPriceItem).sizePrice.toString())
                        holder.container.setOnClickListener {
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }
                    }
                    arrayList[0] is ShopBrandsItem -> {
                        holder.text1.text =
                            (arrayList[holder.adapterPosition] as ShopBrandsItem).name.toString()
                        holder.container.setOnClickListener {
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }
                    }
                    arrayList[0] is SizeStockPrice -> {
                        holder.text2.visibility = View.VISIBLE
                        holder.text1.text =
                            (arrayList[holder.adapterPosition] as SizeStockPrice).sizeName.toString()
                        holder.text2.text =
                            "Rs." + (arrayList[holder.adapterPosition] as SizeStockPrice).sizePrice.toString()
                        holder.container.setOnClickListener {
                            (arrayList[holder.adapterPosition] as SizeStockPrice).selectedPosition =
                                holder.adapterPosition
                            observerNew?.getValue(arrayList[holder.adapterPosition])
                            dialog?.dismiss()
                        }

                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return arrayList?.size ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//            var view = LayoutInflater.from(context)
//                .inflate(android.R.layout.simple_list_item_1, parent, false)
//            if (arrayList?.get(0) is SizeStockPriceItem) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_item_size, parent, false)
//            }
            return ViewHolder(view)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text1: TextView = itemView.findViewById(R.id.text1)
            val text2: TextView = itemView.findViewById(R.id.text2)
            val container: ConstraintLayout = itemView.findViewById(R.id.container)
        }
    }

    object Utils {
        const val selected_position = -1
    }

    fun logOut(activity: FragmentActivity?) {
        activity?.let { context ->
            Preference.clear(context, Preference.DATAFILE)
            messageShow(activity, "Logout")
//            ActivityCompat.finishAffinity(context)
            val intent = Intent(context, LandingScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    fun messageShow(activity: Context?, string: String?) {
        activity.let {
            if (!string.isNullOrBlank()) {
                Toast.makeText(activity, string, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    @JvmName("hideKeyboard1")
    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

}
