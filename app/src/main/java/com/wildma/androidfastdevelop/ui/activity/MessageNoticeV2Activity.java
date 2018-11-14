package com.wildma.androidfastdevelop.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.badge.Badge;
import com.wildma.androidfastdevelop.widget.badge.QBadgeView;


/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/04
 * Desc	        ${消息通知demo（包括图标上显示未读消息数量、图标上显示红点）——使用自定义View实现}
 */
public class MessageNoticeV2Activity extends AppCompatActivity {

    private Badge     mQBadgeView;
    private ImageView mIvNewsIcon;
    private ImageView mIvNewsRedDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_notice_v2);

        initView();
        initListener();
    }

    private void initListener() {
        mQBadgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {

            }
        });
    }

    private void initView() {
        mIvNewsIcon = (ImageView) findViewById(R.id.iv_news_icon);
        mIvNewsRedDot = (ImageView) findViewById(R.id.iv_news_red_dot);
        initBadge();
    }

    private void initBadge() {
        mQBadgeView = new QBadgeView(this);
        mQBadgeView
                .bindTarget(mIvNewsIcon)
                .setBadgePadding(3, true)//内边距根据切图具体调试
                .setBadgeTextSize(9, true)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgeTextColor(Color.parseColor("#ffffff"))
                .setBadgeBackgroundColor(Color.parseColor("#fd4242"));
    }

    public void single(View view) {
        mQBadgeView.setBadgeNumber(9).setGravityOffset(12, 15, true);//外边距根据切图具体调试
    }

    public void doubleClick(View view) {
        mQBadgeView.setBadgeNumber(99).setGravityOffset(11, 15, true);
    }

    public void multiple(View view) {
        mQBadgeView.setBadgeNumber(100).setGravityOffset(6, 15, true);
    }

    public void zero(View view) {
        mQBadgeView.setBadgeNumber(0);
    }

    public void redDot(View view) {
        new QBadgeView(this)
                .setBadgeNumber(-1)
                .bindTarget(mIvNewsRedDot)
                .setBadgePadding(3, true)
                .setBadgeTextSize(9, true)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgeTextColor(Color.parseColor("#ffffff"))
                .setBadgeBackgroundColor(Color.parseColor("#fd4242"));
    }
}
