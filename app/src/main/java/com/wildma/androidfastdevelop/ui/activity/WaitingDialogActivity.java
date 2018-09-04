package com.wildma.androidfastdevelop.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.WaitingDialog;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2017/10/3
 * Desc	        ${WaitingDialog demo}
 */
public class WaitingDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_dialog);
        showWaitingDialog();
    }

    /**
     * 显示WaitindDialog
     */
    private void showWaitingDialog() {
        final Dialog dialog = WaitingDialog.showDialog(this, getString(R.string.Waiting_hint), true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                WaitingDialog.dismissDialog(dialog);
            }
        }).start();
    }
}
