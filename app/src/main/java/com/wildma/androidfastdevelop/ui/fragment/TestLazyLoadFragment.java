package com.wildma.androidfastdevelop.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.base.BaseLazyLoadFragment;

import butterknife.BindView;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/8/5
 * Desc	        ${TestLazyLoadFragment}
 */
public class TestLazyLoadFragment extends BaseLazyLoadFragment {

    private static final String TITLE = "title";
    @BindView(R.id.tv_text)
    TextView mTvText;
    private String TAG = this.getClass().getSimpleName();
    private String mTitle;

    /**
     * 获取fragment的实例，并传参数进来
     *
     * @param title
     * @return
     */
    public static TestLazyLoadFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        TestLazyLoadFragment fragment = new TestLazyLoadFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_lazy_load;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mTitle = getArguments().getString(TITLE);
        mTvText.setText(mTitle);
    }

    @Override
    protected void loadData() {
        Log.d(TAG, "loadData: " + mTitle);
    }

}
