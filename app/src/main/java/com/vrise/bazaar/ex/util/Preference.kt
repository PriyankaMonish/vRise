package com.vrise.bazaar.ex.util

import android.content.Context
import androidx.fragment.app.FragmentActivity

object Preference {

    const val TOOLBAR_WHOLE_SIZE = "TOOLBAR_SIZE_EHOLE"
    const val TOOLBAR_SIZE = "TOOLBAR_SIZE"
    const val BBARSIZE = "BottolBAR_SIZE"
    const val NOTIFYCOUNT = "NOTIFYCOUNT"
    const val CARTCOUNT = "CARTCOUNT"
    const val CATEGORY = "cAtEgOrY"
    const val LOC = "location"
    const val NAME = "name"
    const val IMAGE = "image"
    const val DATAFILE = "DATA"
    const val MAIN_SUB_CATEGORY_ID = "Main_Sub_Category_Id"
    const val SHOP_ID = "Shop_Id"
    const val STORE_NAME = "Store_Name"
    const val SUB_CATEGORY_ID = "Sub_Category_Id"
    const val NUMBER = "No"
    const val URLLINK = "Urllink"
    const val LANGUA = "langua"
    const val STORE = "store"
    const val MAXRAD = "maxRad"
    const val TOKEN = "id"

    fun clearOne(context: Context?, FileName: String, KeyName: String) {
        val settings = context?.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        settings?.edit()?.remove(KeyName)?.apply()
    }

    fun getAll(context: Context, FileName: String): String? {
        val preferencesData = context.applicationContext.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        return preferencesData.getString("keyName", "keyValue")
    }

    fun clear(context: Context, FileName: String) {
        val prefs = context.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun get(context: FragmentActivity?, FileName: String, KeyName: String): String? {
        val preferencesData = context?.applicationContext?.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        return preferencesData?.getString(KeyName, "")
    }

    fun get(context: Context?, FileName: String, KeyName: String): String? {
            val preferencesData = context?.applicationContext?.getSharedPreferences(FileName, Context.MODE_PRIVATE)
            return preferencesData?.getString(KeyName, "")
        }

    fun put(context: FragmentActivity?, key: String, keyValue: String, FileName: String) {
        val sharedPreferences = context?.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        val spe = sharedPreferences?.edit()
        spe?.putString(key, keyValue)
        spe?.apply()
    }

}
