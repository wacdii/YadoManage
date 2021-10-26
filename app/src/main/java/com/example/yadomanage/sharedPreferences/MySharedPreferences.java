package com.example.yadomanage.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String MY_SHARED_PREFENCES = "MY_SHARED_PREFENCES";
    private Context mContext;

    public MySharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PREFENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PREFENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putStringValue(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PREFENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARED_PREFENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}
