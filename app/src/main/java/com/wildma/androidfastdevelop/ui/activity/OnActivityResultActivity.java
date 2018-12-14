package com.wildma.androidfastdevelop.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.utils.AvoidOnResult;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/12/9
 * Desc	        ${onActivityResult 的封装使用 demo}
 */
public class OnActivityResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_activity_result);
    }

    /**
     * 跳转到 ReturnDataActivity
     *
     * @param view
     */
    public void jumpToReturnDataActivity(View view) {
        new AvoidOnResult(this).startForResult(ReturnDataActivity.class, new AvoidOnResult.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                if (resultCode == Activity.RESULT_OK) {
                    String text = data.getStringExtra("text");
                    Toast.makeText(OnActivityResultActivity.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
