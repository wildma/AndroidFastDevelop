package com.wildma.androidfastdevelop.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Author       wildma
 * Date         2017/8/23
 * Desc	        ${APP相关工具类}
 */
public class AppUtils {

    /**
     * 判断APP是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return true = 已安装   fasle = 未安装
     * QQ包名：com.tencent.mobileqq    微信包名：com.tencent.mm
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取系统中安装的所有软件信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取App包名
     *
     * @param context 上下文
     * @return App包名
     */
    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取APP版本号
     *
     * @param context 上下文
     * @return APP版本号
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取App版本码
     *
     * @param context 上下文
     * @return App版本码
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 获取APP签名 (例如申请微信APP KEY需要APP签名。debug与release获取的不同,因为signingConfigs配置的keystore不同)
     *
     * @param context
     * @return APP签名 例如：775440b971e0c03a2893a6f5ac49588d
     */
    public static String getAppSignature(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int length = packageInfo.signatures.length;
        if (length <= 0) {
            return "";
        }

        Signature signature = packageInfo.signatures[0];
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md5.digest(signature.toByteArray());
        return toHexString(digest).toLowerCase();
    }

    /**
     * 获取应用签名的的SHA1值 (例如申请高德地图Key需要SHA1值。debug与release获取的不同,因为signingConfigs配置的keystore不同)
     *
     * @param context
     * @return 应用签名的SHA1值 例如：77:54:40:B9:71:E0:C0:3A:28:93:A6:F5:AC:49:58:8D
     */
    public static String getAppSignatureSHA1(Context context) {
        return getAppSignature(context).toUpperCase().replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }




    /*============================================获取APP签名需要================================================*/

    //十六进制字符
    private static final char[] HEX_CHAR = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * 将字节数组转化为对应的十六进制字符串
     *
     * @param rawByteArray
     * @return
     */
    private static String toHexString(byte[] rawByteArray) {
        char[] chars = new char[rawByteArray.length * 2];
        for (int i = 0; i < rawByteArray.length; ++i) {
            byte b = rawByteArray[i];
            chars[i * 2] = HEX_CHAR[(b >>> 4 & 0x0F)];
            chars[i * 2 + 1] = HEX_CHAR[(b & 0x0F)];
        }
        return new String(chars);
    }

}
