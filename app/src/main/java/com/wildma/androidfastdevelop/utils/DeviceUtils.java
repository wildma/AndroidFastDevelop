package com.wildma.androidfastdevelop.utils;

import android.os.Build;

/**
 * Author       wildma
 * Date         2017/9/13
 * Desc	        ${设备相关工具类}
 */
public class DeviceUtils {

    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备型号
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }
}
