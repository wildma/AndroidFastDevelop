package com.wildma.androidfastdevelop.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;

/**
 * Author       wildma
 * Date         2017/8/18
 * Desc	        ${屏幕相关工具类}
 */
public class ScreenUtils {

    /**
     * 获取屏幕宽度（px）
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获取屏幕高度（px）
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 设置全屏
     *
     * @param activity
     */
    public static void setFullScreen(@NonNull final Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
