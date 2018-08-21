package com.wildma.androidfastdevelop;

import android.app.Application;
import android.content.Context;

import com.wildma.androidfastdevelop.utils.CrashHandler;

/**
 * Author       wildma
 * Date         2017/9/12
 * Desc	        ${TODO}
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(this);//初始化CrashHandler
    }

    public static Context getContext() {
        return mContext;
    }
}
