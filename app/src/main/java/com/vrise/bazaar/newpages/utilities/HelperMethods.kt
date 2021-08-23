package com.vrise.bazaar.newpages.utilities


import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.GsonBuilder
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Constants.ACTIVITY
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.FAILED
import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
import com.vrise.bazaar.newpages.utilities.Constants.SUCCESS
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.CategorylistItem
import com.vrise.bazaar.newpages.utilities.models.submodels.LanguagelistItem
import java.io.ByteArrayOutputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*पद्धति*/

object HelperMethods {

    fun displayUpGradeDialog(activity: FragmentActivity, version: String?) {

        val aDialog1 = android.app.AlertDialog.Builder(activity)
        aDialog1.setTitle("New update available!")
        aDialog1.setMessage("A new version $version is available to download. Downloading the latest update you will get the latest features, improvements and bug fixes.")
        aDialog1.setCancelable(false)
        aDialog1.setIcon(R.mipmap.vr2)
        var alertDialog1: android.app.AlertDialog? = null
        aDialog1.setPositiveButton("UPDATE") { arg0, arg1 ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + Constants.APP_ID)
            )
            activity.startActivity(intent)
            alertDialog1?.dismiss()
            activity.finish()
        }
        alertDialog1 = aDialog1.create()
        alertDialog1.setCanceledOnTouchOutside(false)
        alertDialog1.show()
    }

    fun navigateTo(
        contextOne: FragmentActivity?,
        type: String,
        backNeeded: Boolean,
        ifFragment: Fragment?,
        ifActivity: Class<*>?,
        bundle: Bundle?,
        flagsForActivity: Array<Int>?,
        allowStateLoss: String
    ) {
        contextOne?.let { context ->
            when (type) {
                ACTIVITY -> {
                    if (ifActivity != null) {
                        val intent = Intent(context, ifActivity)
                        if (bundle != null) {
                            intent.putExtras(bundle)
                        }
                        if (flagsForActivity != null) {
                            for (i in 0 until flagsForActivity.size) {
                                intent.flags = flagsForActivity[i]
                            }
                        }
                        context.startActivity(intent)
                        if (!backNeeded) {
                            context.finish()
                        }
                    }
                }
                FRAGMENT -> {
                    if (ifFragment != null) {
                        if (allowStateLoss.isNotBlank()) {
                            fragmentRedirection(context, ifFragment, backNeeded, bundle, true)
                        } else {
                            fragmentRedirection(context, ifFragment, backNeeded, bundle)
                        }
                    }
                }
            }
        }
    }

    fun fragmentRedirection(contextTwo: FragmentActivity?, ifFragment: Fragment, backNeeded: Boolean, bundle: Bundle?) {
        contextTwo?.let { activity ->
            fragmentRedirection(activity, ifFragment, backNeeded, bundle, false)
        }
    }

    fun navigateToReturn(activity: Activity, REQUEST_CODE: String, Activity: Class<*>?, bundle: Bundle?) {
        if (Activity != null) {
            val intent = Intent(activity, Activity)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivityForResult(intent, REQUEST_CODE.toInt())
        }
    }

    private fun fragmentRedirection(
        activity: FragmentActivity?,
        fragment: Fragment,
        backStatus: Boolean,
        bundle: Bundle?,
        allowStateLoss: Boolean
    ) {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        activity?.let {
            when {
                backStatus -> {
                    if (allowStateLoss) {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).addToBackStack(null).commit()
                    } else {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).addToBackStack(null).commit()
                    }
                }
                else -> {
                    if (allowStateLoss) {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).commitAllowingStateLoss()
                    } else {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).commit()
                    }
                }
            }
        }
    }

    fun hideKeyboard(activity: Activity?) {
        try {
            activity?.let {
                val inputManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val currentFocusedView = it.currentFocus
                if (currentFocusedView != null) {
                    inputManager.hideSoftInputFromWindow(
                        currentFocusedView.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hasConnection(activity: FragmentActivity?): Boolean {
        return if (activity != null) {
            if ((activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null && (activity.getSystemService(
                    Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager).activeNetworkInfo!!.isConnectedOrConnecting
            ) {
                true
            } else {
                toast(activity, activity.getString(R.string.no_internet))
                false
            }
        } else {
            false
        }
    }

    fun hasData(response: retrofit2.Response<Response>?): Boolean {
        var valid = true
        if (response != null) {
            valid = if (response.isSuccessful) {
                if (response.body() != null) {
                    println(response.body())
                    if (response.body()?.message != null) {
                        when {
                            response.body()?.message == SUCCESS -> true
                            response.body()?.message == FAILED -> true
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


    fun hasSuccessData(response: retrofit2.Response<Response>?): Boolean {
        val valid: Boolean
        if (response != null) {
            valid = if (response.isSuccessful) {
                if (response.body() != null) {
                    println(response.body())
                    if (response.body()?.message != null) {
                        when {
                            response.body()?.message == SUCCESS -> true
                            response.body()?.message == FAILED -> false
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

    fun hasData(activity: FragmentActivity?, response: retrofit2.Response<Response>?): Boolean {
        var valid = true
        if (activity != null) {
            if (response != null) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        println(response.body())
                        if (response.body()?.message != null) {
                            if (response.body()?.message == SUCCESS) {
                                valid = true
                                if (!response.body()?.display.isNullOrBlank()) {
                                    toast(activity, response.body()?.display.toString())
                                }
                            } else if (response.body()?.message == FAILED) {
                                if (response.body()?.display != null) {
                                    toast(activity, response.body()?.display.toString())
                                }
                                if (response.body()?.data != null) {
                                    if (response.body()?.data?.action != null) {
                                        if (response.body()?.data?.action == "login") {
                                            logoutfromapp(activity)
                                        }
                                    }
                                }
                                valid = false
                            } else {
                                toast(activity, response.body()?.message.toString())
                                valid = false
                            }
                        } else {
                            toast(activity, "No Response From Server")
                            valid = false
                        }
                    } else {
                        toast(activity, "No Response From Server")
                        valid = false
                    }
                } else {
                    toast(activity, "No Response From Server")
                    valid = false
                }
            } else {
                toast(activity, "No Response From Server")
                valid = false
            }
        } else {
            /*toast(activity, "No Response From Server")*/
            valid = false
        }
        return valid
    }

    private fun logoutfromapp(activity: FragmentActivity?) {
        if (activity != null) {
            Preference.ClearSharedPreference(activity, DATAFILE)
            ActivityCompat.finishAffinity(activity)
            navigateTo(
                activity,
                ACTIVITY,
                false,
                null,
                com.vrise.bazaar.ex.shop.containers.RegistrationContainer::class.java,
                null,
                arrayOf(Intent.FLAG_ACTIVITY_CLEAR_TOP),
                ""
            )
        }
    }

    fun logoutfromapp(activity: Activity?) {
        activity?.let {
            Preference.ClearSharedPreference(it, DATAFILE)
            ActivityCompat.finishAffinity(it)
            navigateTo(
                it as FragmentActivity,
                ACTIVITY,
                false,
                null,
                com.vrise.bazaar.ex.shop.containers.RegistrationContainer::class.java,
                null,
                arrayOf(Intent.FLAG_ACTIVITY_CLEAR_TOP),
                ""
            )
        }
    }

    fun toast(activity: FragmentActivity?, toString: String?) {
        activity?.let {
            Toast.makeText(it, toString, Toast.LENGTH_SHORT).show()
        }
    }
    fun toast(activity: Context?, toString: String?) {
        activity?.let {
            Toast.makeText(it, toString, Toast.LENGTH_SHORT).show()
        }
    }

    fun toastit(activity: FragmentActivity?, toString: String?) {
        activity?.let {
            Toast.makeText(it, toString, Toast.LENGTH_SHORT).show()
        }
    }

    fun println(any: Any?) {
        print(GsonBuilder().setPrettyPrinting().create().toJson(any))
    }

    fun getUniqueIDforDevice(activity: Activity): String? {
        var newToken = "0"
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity) { instanceIdResult ->
            newToken = instanceIdResult.token
            println("newToken $newToken ")
        }
        return newToken
        /*val tm = activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return try {
            UUID(
                    (
                            if (Settings.Secure.getString(activity.contentResolver, Settings.Secure.ANDROID_ID) != null) {
                                Settings.Secure.getString(activity.contentResolver, Settings.Secure.ANDROID_ID)
                            } else {
                                "0"
                            }).hashCode().toLong()
                    ,
                    (
                            if (tm.deviceId != null) {
                                tm.deviceId
                            } else {
                                "0"
                            }).hashCode().toLong() shl 32 or (
                            if (tm.simSerialNumber != null) {
                                tm.simSerialNumber
                            } else {
                                "0"
                            }).hashCode().toLong()
            ).toString()
        } catch (e: SecurityException) {
            "0"
        }*/
    }

    fun checkIfPermissionsGranted(context: FragmentActivity, array: Array<String>): Boolean {
        val valid = arrayOfNulls<Boolean>(array.size)
        for (i in 0 until array.size) valid[i] = false
        for (i in 0 until array.size) {
            valid[i] = ContextCompat.checkSelfPermission(context, array[i]) == PackageManager.PERMISSION_GRANTED
        }
        return !valid.contains(false) && !valid.contains(null)
    }

    fun checkIfAllPermissionsGranted(context: FragmentActivity): Boolean {
        return when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED -> false
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED -> false
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED -> false
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED -> false
            else -> true
        }
    }

    fun openPopup(message: String, activity: FragmentActivity, flag: Boolean, invoke: Interfaces.Invoke) {

        val dialog = Dialog(activity)
        dialog.setCancelable(flag)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.item_dialog_custom)
        activity.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textView17 = dialog.findViewById<TextView>(R.id.textView17)
        val textView18 = dialog.findViewById<TextView>(R.id.textView18)
        textView18.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                invoke.invokeMe()
                dialog.dismiss()
            }
        })
        textView17.text = message
        dialog.show()
    }

    fun openPopUp(
        activity: FragmentActivity?,
        array: ArrayList<Request>?,
        edit: TextView,
        head: String,
        returnId: Interfaces.ReturnId
    ) {
        activity?.let {
            val dialog = Dialog(it)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.custom_list_dialog)
            val alertRecyclerView = dialog.findViewById<RecyclerView>(R.id.recyclerView18)
            alertRecyclerView.layoutManager = LinearLayoutManager(it)
            val adapter = CustomAdapter(it, array, dialog, object : Interfaces.ClickedItem {
                override fun returnIdValue(clickPosPosition: String, clickPosvalue: String) {
                    edit.text = clickPosvalue
                    returnId.returnId(clickPosPosition)
                }
            })
            alertRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
            dialog.show()
        }
    }

    fun openLanguagePopUp(
        activity: FragmentActivity?,
        array: ArrayList<LanguagelistItem>?,
        edit: TextView,
        returnId: Interfaces.ReturnNamenId
    ) {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.custom_list_dialog)
            val alertRecyclerView = dialog.findViewById<RecyclerView>(R.id.recyclerView18)
            alertRecyclerView.layoutManager = LinearLayoutManager(it)
            val adapter = CustomLanguageAdapter(it, array, dialog, object : Interfaces.ClickedItem {
                override fun returnIdValue(clickPosPosition: String, clickPosvalue: String) {
                    edit.text = clickPosvalue
                    returnId.returnIdName(clickPosvalue, clickPosPosition)
                }
            })
            alertRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
            dialog.show()
        }
    }

    fun openCategoryPopUp(
        activity: FragmentActivity?,
        array: ArrayList<CategorylistItem>?,
        edit: TextView,
        returnId: Interfaces.ReturnNamenId
    ) {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.custom_list_dialog)
            val alertRecyclerView = dialog.findViewById<RecyclerView>(R.id.recyclerView18)
            alertRecyclerView.layoutManager = LinearLayoutManager(it)
            val adapter = TypesAdapter(it, array, dialog, object : Interfaces.ClickedItem {
                override fun returnIdValue(clickPosPosition: String, clickPosvalue: String) {
                    edit.text = clickPosvalue
                    returnId.returnIdName(clickPosvalue, clickPosPosition)
                }
            })
            alertRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
            dialog.show()
        }
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

    class CustomAdapter(
        context: FragmentActivity,
        private val arrayList: kotlin.collections.ArrayList<Request>?,
        private val dialog: Dialog,
        private val clickPos: Interfaces.ClickedItem
    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
        private val context: Context = context

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            arrayList?.let { arrayList ->
                holder.text1.text = arrayList[holder.adapterPosition].name
                holder.text1.setOnClickListener {
                    clickPos.returnIdValue(
                        arrayList[holder.adapterPosition].code.toString(),
                        arrayList[holder.adapterPosition].name.toString()
                    )
                    dialog.dismiss()
                }
            }
        }

        override fun getItemCount(): Int {
            return arrayList?.size ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_simp_popup_list, parent, false)
            return ViewHolder(view)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text1: TextView = itemView.findViewById(R.id.textView102)
        }
    }

    class CustomLanguageAdapter(
        context: FragmentActivity,
        private val arrayList: kotlin.collections.ArrayList<LanguagelistItem>?,
        private val dialog: Dialog,
        private val clickPos: Interfaces.ClickedItem
    ) : RecyclerView.Adapter<CustomLanguageAdapter.ViewHolder>() {
        private val context: Context = context

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            arrayList?.let { arrayList ->
                holder.text1.text = arrayList[holder.adapterPosition].language
                holder.text1.setOnClickListener {
                    clickPos.returnIdValue(
                        arrayList[holder.adapterPosition].id.toString(),
                        arrayList[holder.adapterPosition].language.toString()
                    )
                    dialog.dismiss()
                }
            }
        }

        override fun getItemCount(): Int {
            return arrayList?.size ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_language_popup_list, parent, false)
            return ViewHolder(view)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text1: TextView = itemView.findViewById(R.id.textView102)
        }
    }

    class TypesAdapter(
        context: FragmentActivity,
        private val arrayList: kotlin.collections.ArrayList<CategorylistItem>?,
        private val dialog: Dialog,
        private val clickPos: Interfaces.ClickedItem
    ) : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {
        private val context: Context = context

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            arrayList?.let { arrayList ->
                holder.text1.text = arrayList[holder.adapterPosition].name
                holder.text1.setOnClickListener {
                    clickPos.returnIdValue(
                        arrayList[holder.adapterPosition].id.toString(),
                        arrayList[holder.adapterPosition].name.toString()
                    )
                    dialog.dismiss()
                }
            }
        }

        override fun getItemCount(): Int {
            return arrayList?.size ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_popup_list, parent, false)
            return ViewHolder(view)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text1: TextView = itemView.findViewById(R.id.textView106)
        }
    }

    fun changeDateFormat(currentFormat: String, requiredFormat: String, dateString: String): String {
        var result = ""

        if (dateString.isBlank()) {

        } else {
            val formatterOld = SimpleDateFormat(currentFormat, Locale.getDefault())
            val formatterNew = SimpleDateFormat(requiredFormat, Locale.getDefault())
            var date: Date? = null
            try {
                date = formatterOld.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            if (date != null) {
                result = formatterNew.format(date)
            }
        }
        return result
    }

    fun RequestPermissionRationale(activity: FragmentActivity?): Boolean {
        return if (activity != null) {

            /*ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE) ||*/
            ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
        } else {

            false
        }
    }

    val editTextFilterEmoji: InputFilter
        get() = object : InputFilter {
            override fun filter(
                source: CharSequence,
                start: Int,
                end: Int,
                dest: Spanned,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                var sources = source
                var ends = end
                val sourceOriginal = sources
                sources = replaceEmoji(sources)
                ends = sources.toString().length

                if (ends == 0) return ""

                if (sourceOriginal.toString() != sources.toString()) {
                    val v = CharArray(ends - start)
                    TextUtils.getChars(sources, start, ends, v, 0)

                    val s = String(v)

                    return if (sources is Spanned) {
                        val sp = SpannableString(s)
                        TextUtils.copySpansFrom(sources as Spanned, start, ends, null, sp, 0)
                        sp
                    } else {
                        s
                    }
                } else {
                    return null
                }
            }

            private fun replaceEmoji(source: CharSequence): String {

                val notAllowedCharactersRegex = "[^a-zA-Z0-9@#$%&\\-+()*;:!?~`£{}\\[\\]=.,_/\\\\\\s'\"<>^|÷×]"
                return source.toString()
                    .replace(notAllowedCharactersRegex.toRegex(), "")
            }

        }

    /*
    InputFilter[] filterArray = new InputFilter[] {getEditTextFilterEmoji()}
    editText.setFilters(filterArray);
    */

    class EmojiExcludeFilter : InputFilter {

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            for (i in start until end) {
                val type = Character.getType(source[i])
                if (type == Character.SURROGATE.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                    return ""
                }
            }
            return null
        }

        /*editText.setFilters(new InputFilter[]{new EmojiExcludeFilter()});*/
    }

    var rand = Random()
    fun <T> getRandomItem(list: List<T>): T {
        return list.get(rand.nextInt(list.size))
    }
}