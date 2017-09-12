package com.wildma.androidfastdevelop;

import android.app.Application;

import com.wildma.androidfastdevelop.utils.CrashHandler;

/**
 * Author       wildma
 * Date         2017/9/12
 * Desc	        ${TODO}
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);//初始化CrashHandler
    }
}
