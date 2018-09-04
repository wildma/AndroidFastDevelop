package com.wildma.androidfastdevelop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.wildma.androidfastdevelop.MainActivity;
import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/1/16
 * Desc	        ${splash demo}
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
