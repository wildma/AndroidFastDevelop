package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.adapter.TabLayoutFragmentPagerAdapter;
import com.wildma.androidfastdevelop.ui.fragment.TestLazyLoadFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/8/5
 * Desc	        ${TabLayout+ViewPager的使用demo}
 */
public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);

        initFragments();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        ArrayList fragments = new ArrayList<Fragment>();
        ArrayList<String> titleLists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TestLazyLoadFragment fragment = TestLazyLoadFragment.newInstance("title" + i);
            titleLists.add("title" + i);
            fragments.add(fragment);
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//是否支持左右滑动，MODE_FIXED不可滑动，MODE_SCROLLABLE可以滑动，根据需求设置
        TabLayoutFragmentPagerAdapter fragmentPagerAdapter = new TabLayoutFragmentPagerAdapter(getSupportFragmentManager(), fragments, titleLists);
        mViewPager.setAdapter(fragmentPagerAdapter);
        //        mViewpager.setOffscreenPageLimit(titleLists.size());//设置预加载的界面个数
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
