package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.wheelview.WheelItemBean;
import com.wildma.androidfastdevelop.widget.wheelview.WheelViewDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/9/16
 * Desc	        ${滚轮控件demo(使用ListView实现，可自定义布局)}
 */
public class WheelViewActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private List<WheelItemBean> mWheelItemBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        initData();
    }

    private void initData() {
        mWheelItemBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WheelItemBean wheelItemBean = new WheelItemBean();
            wheelItemBean.setTitle(getString(R.string.wheel_view_title) + i);
            mWheelItemBeanList.add(wheelItemBean);
        }
    }

    public void showDialog(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this, R.style.ActionSheetDialogStyle, mWheelItemBeanList);
        dialog.setOnItemClickListener(new WheelViewDialog.OnItemClickListener() {
            @Override
            public void onItemClick(int selectPosition) {
                Log.d(TAG, "onItemClick: " + selectPosition);
            }
        });
    }
}
