package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.global.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/21
 * Desc	        ${EventBusSecondActivity}
 */
public class EventBusSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_second);
    }

    public void sendEvent(View view) {
        EventBus.getDefault().post(new MessageEvent(MessageEvent.MESSAGE_TYPE_1)); //发送消息
        finish();
    }
}