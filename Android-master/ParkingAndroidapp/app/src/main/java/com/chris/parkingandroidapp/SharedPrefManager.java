package com.chris.parkingandroidapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chrisantoinette on 11/15/17.
 */


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "RegPref";
    private static final String TAG_LOGIN = "taglogin";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean isLoggedIn () {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(TAG_LOGIN, false);
    }
    public void setLogin ()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TAG_LOGIN, true);
        editor.apply();
    }
    // for testing purpose
    public void clearLogin ()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TAG_LOGIN, false);
        editor.apply();
    }
}