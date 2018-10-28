package com.wildma.androidfastdevelop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.global.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/21
 * Desc	        ${EventBusFirstActivity}
 */
public class EventBusFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_first);
        EventBus.getDefault().register(this);  //注册EventBus
    }

    public void toEventBusFirstActivity(View view) {
        startActivity(new Intent(this, EventBusSecondActivity.class));
    }

    /**
     * 准备订阅者
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessageType() == MessageEvent.MESSAGE_TYPE_1) {
            Toast.makeText(this, "收到消息：" + messageEvent.getMessageType(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this); //解除注册EventBus
        super.onDestroy();
    }
}