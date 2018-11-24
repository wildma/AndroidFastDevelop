package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.bean.NotNullBean;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/18
 * Desc	        ${不为空实体类demo}
 */
public class NotNullActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotNullBean bean = new NotNullBean();
        if (bean != null) {
            Log.d(TAG, "onCreate: " + bean);
            Log.d(TAG, "onCreate: " + bean.getName().equals(""));
            Log.d(TAG, "onCreate: " + bean.getData());
            Log.d(TAG, "onCreate: " + bean.getData().getName().equals(""));
            Log.d(TAG, "onCreate: " + bean.getList());
            Log.d(TAG, "onCreate: " + bean.getList().size());
        }
    }
}
