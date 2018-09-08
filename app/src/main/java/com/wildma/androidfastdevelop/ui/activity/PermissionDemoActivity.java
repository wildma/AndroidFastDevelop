package com.wildma.androidfastdevelop.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.global.PermissionConstants;
import com.wildma.androidfastdevelop.helper.DialogHelper;
import com.wildma.androidfastdevelop.helper.PermissionHelper;
import com.wildma.androidfastdevelop.utils.PermissionUtils;
import com.wildma.androidfastdevelop.utils.ScreenUtils;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/9/8
 * Desc	        ${权限工具类使用demo}
 */
public class PermissionDemoActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    public static void toPermissionDemoActivity(Context context) {
        Intent starter = new Intent(context, PermissionDemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    /**
     * 打开应用详情设置界面
     *
     * @param view
     */
    public void openAppDetailsSettings(View view) {
        PermissionUtils.launchAppDetailsSettings();
    }

    /**
     * 申请存储权限（简单方式-推荐方式）
     *
     * @param view
     */
    public void requestStoragePermissionSimple(View view) {
        PermissionHelper.requestStorage(this, new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "权限被允许", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 申请存储权限
     *
     * @param view
     */
    public void requestStoragePermission(View view) {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        Log.d(TAG, "onDenied: 权限被拒绝后弹框提示");
                        DialogHelper.showRationaleDialog(shouldRequest, PermissionDemoActivity.this);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        Toast.makeText(getApplicationContext(), "权限被允许", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        Log.d(TAG, "onDenied: 权限被拒绝");
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog(PermissionDemoActivity.this);
                        }
                    }
                })
                .request();
    }

    /**
     * 申请相机权限
     *
     * @param view
     */
    public void requestCameraPermission(View view) {
        PermissionUtils.permission(PermissionConstants.CAMERA)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        Log.d(TAG, "onDenied: 权限被拒绝后弹框提示");
                        DialogHelper.showRationaleDialog(shouldRequest, PermissionDemoActivity.this);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        Toast.makeText(getApplicationContext(), "权限被允许", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        Log.d(TAG, "onDenied: 权限被拒绝");
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog(PermissionDemoActivity.this);
                        }
                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);//设置全屏，即隐藏状态栏等
                    }
                })
                .request();
    }
}
