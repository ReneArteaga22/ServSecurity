package com.example.servidores.data.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenGet {
    public static String getTokenFromSharedPreferences(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("token", null);
    }

    public static void saveTokenToSharedPreferences(Context context, String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
