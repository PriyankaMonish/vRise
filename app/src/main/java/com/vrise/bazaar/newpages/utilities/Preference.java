package com.vrise.bazaar.newpages.utilities;

/*
 * Created by Estrrado Android on 6/9/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;



public class Preference {
    public static void ClearSingleSharedPreference(Context context, String FileName, String KeyName) {
        SharedPreferences settings = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        settings.edit().remove(KeyName).apply();
    }

    public static String getAllPreference(Context context, String FileName) {
        SharedPreferences preferencesData = context.getApplicationContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return preferencesData.getString("keyName", "keyValue");
    }

    public static void ClearSharedPreference(Context context, String FileName) {
        SharedPreferences prefs = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static String getSinglePreference(Context context, String FileName, String KeyName) {
        SharedPreferences preferencesData = context.getApplicationContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return preferencesData.getString(KeyName, "");
    }

    public static void putPreference(Context context, String key, String keyValue, String FileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString(key, keyValue);
        spe.apply();
    }

}
