package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.MarqueeView;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/9/2
 * Desc	        ${单行文本滚动demo}
 */
public class MarqueeViewActivity extends AppCompatActivity {

    private MarqueeView mMarqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marqueeview);
        mMarqueeView = (MarqueeView) findViewById(R.id.marqueeView);

        mMarqueeView.setText(new String[]{getString(R.string.welcome_text)})
                .setOnClickable(true)
                .setStep(3)
                .setOrientation(MarqueeView.TOLEFT)
                .setScrollTextColor(0xffFF4081)
                .create()
                .startScroll();
    }
}
