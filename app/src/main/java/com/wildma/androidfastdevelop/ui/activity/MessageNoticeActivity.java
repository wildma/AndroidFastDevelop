package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/8/17
 * Desc	        ${消息通知Demo（包括图标上显示未读消息数量、图标上显示红点）}
 */
public class MessageNoticeActivity extends AppCompatActivity {

    private TextView  mTvMessageNumber;
    private ImageView mIvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_notice);

        initView();
    }

    private void initView() {
        mTvMessageNumber = (TextView) findViewById(R.id.tv_message_number);
        mIvMessage = (ImageView) findViewById(R.id.iv_message);

        mIvMessage.setSelected(true);
    }

    public void single(View view) {
        mTvMessageNumber.setVisibility(View.VISIBLE);
        mTvMessageNumber.setBackgroundResource(R.drawable.shape_bg_red_dot_small);
        mTvMessageNumber.setText("9");
    }

    public void doubleClick(View view) {
        mTvMessageNumber.setVisibility(View.VISIBLE);
        mTvMessageNumber.setBackgroundResource(R.drawable.shape_bg_red_dot_small);
        mTvMessageNumber.setText("99");
    }

    public void multiple(View view) {
        mTvMessageNumber.setVisibility(View.VISIBLE);
        mTvMessageNumber.setBackgroundResource(R.drawable.shape_bg_red_dot_big);
        mTvMessageNumber.setText("99+");
    }

    public void zero(View view) {
        mTvMessageNumber.setVisibility(View.INVISIBLE);
    }

    public void showMessage(View view) {
        mIvMessage.setSelected(true);
    }

    public void hideMessage(View view) {
        mIvMessage.setSelected(false);
    }
}
