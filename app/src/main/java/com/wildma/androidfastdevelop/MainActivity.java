package com.wildma.androidfastdevelop;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.wildma.androidfastdevelop.widget.WaitingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 测试WaitindDialog
     */
    private void testWaitingDialog() {
        final Dialog dialog = WaitingDialog.showDialog(this, "正在载入数据...", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                WaitingDialog.dismissDialog(dialog);
            }
        }).start();
    }
}
