package com.lunlun.fenhow1219;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.TimeUnit;

public class MySingleton {


    private static Context mCtx;
    private static MySingleton mInstance;

    private SharedPreferences mSharedPreferences = getSharedPreferences();

    private MySingleton(Context context) {
        mCtx = context;
    }

    public static synchronized MySingleton getInstance(Context context) {
        MySingleton mySingleton;
        synchronized (MySingleton.class) {
            if (mInstance == null) {
                mInstance = new MySingleton(context);
            }
            mySingleton = mInstance;
        }
        return mySingleton;
    }

    public SharedPreferences getSharedPreferences() {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = mCtx.getSharedPreferences(mCtx.getString(R.string.shared_preferences_name), 0);
        }
        return this.mSharedPreferences;
    }

//    public ImageLoader getImageLoader() {
//        return this.mImageLoader;
//    }
}

