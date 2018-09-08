package com.wildma.androidfastdevelop.helper;

import android.app.Activity;

import com.wildma.androidfastdevelop.global.PermissionConstants;
import com.wildma.androidfastdevelop.utils.PermissionUtils;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/9/8
 * Desc	        ${权限Helper}
 * source       Blankj
 */
public class PermissionHelper {

    public static void requestCamera(Activity activity, final OnPermissionGrantedListener listener) {
        request(activity, listener, PermissionConstants.CAMERA);
    }

    public static void requestStorage(Activity activity, final OnPermissionGrantedListener listener) {
        request(activity, listener, PermissionConstants.STORAGE);
    }

    public static void requestPhone(Activity activity, final OnPermissionGrantedListener listener) {
        request(activity, listener, PermissionConstants.PHONE);
    }

    public static void requestPhone(Activity activity, final OnPermissionGrantedListener grantedListener,
                                    final OnPermissionDeniedListener deniedListener) {
        request(activity, grantedListener, deniedListener, PermissionConstants.PHONE);
    }

    public static void requestSms(Activity activity, final OnPermissionGrantedListener listener) {
        request(activity, listener, PermissionConstants.SMS);
    }

    public static void requestLocation(Activity activity, final OnPermissionGrantedListener listener) {
        request(activity, listener, PermissionConstants.LOCATION);
    }

    private static void request(Activity activity, final OnPermissionGrantedListener grantedListener,
                                final @PermissionConstants.Permission String... permissions) {
        request(activity, grantedListener, null, permissions);
    }

    private static void request(final Activity activity, final OnPermissionGrantedListener grantedListener,
                                final OnPermissionDeniedListener deniedListener,
                                final @PermissionConstants.Permission String... permissions) {
        PermissionUtils.permission(permissions)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest, activity);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (grantedListener != null) {
                            grantedListener.onPermissionGranted();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog(activity);
                        }
                        if (deniedListener != null) {
                            deniedListener.onPermissionDenied();
                        }
                    }
                })
                .request();
    }

    public interface OnPermissionGrantedListener {
        void onPermissionGranted();
    }

    public interface OnPermissionDeniedListener {
        void onPermissionDenied();
    }
}
