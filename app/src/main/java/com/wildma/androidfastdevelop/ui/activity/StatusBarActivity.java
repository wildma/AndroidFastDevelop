package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.utils.BarUtils;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/8/12
 * Desc	        ${沉浸式状态栏例子（包括标题栏与状态栏一体化、背景图片与状态栏一体化）}
 */
public class StatusBarActivity extends AppCompatActivity {

    private View         mViewStatusBar;
    private LinearLayout mLlTitle;
    private ImageView    mIvimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        mViewStatusBar = findViewById(R.id.view_status_bar);
        mLlTitle = (LinearLayout) findViewById(R.id.ll_title);
        mIvimage = (ImageView) findViewById(R.id.iv_image);

        /*默认是标题栏与状态栏一体化*/
        BarUtils.setTransparencyBar(StatusBarActivity.this);
        BarUtils.setViewHeightEqualStatusBarHeight(mViewStatusBar);
    }

    /**
     * 标题栏与状态栏一体化
     *
     * @param view
     */
    public void titleStatusBarMode(View view) {
        mViewStatusBar.setVisibility(View.VISIBLE);
        mLlTitle.setVisibility(View.VISIBLE);
        mIvimage.setVisibility(View.GONE);
        BarUtils.setTransparencyBar(StatusBarActivity.this);
        BarUtils.setViewHeightEqualStatusBarHeight(mViewStatusBar);
    }

    /**
     * 背景图片与状态栏一体化
     *
     * @param view
     */
    public void imageStatusBarMode(View view) {
        mViewStatusBar.setVisibility(View.GONE);
        mLlTitle.setVisibility(View.GONE);
        mIvimage.setVisibility(View.VISIBLE);
        BarUtils.setTransparencyBar(StatusBarActivity.this);
    }
}
