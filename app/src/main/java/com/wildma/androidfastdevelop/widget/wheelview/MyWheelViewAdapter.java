package com.wildma.androidfastdevelop.widget.wheelview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.wildma.androidfastdevelop.R;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/9/16
 * Desc	        ${滚轮控件listView的adapter}
 */
public class MyWheelViewAdapter extends WheelAdapter<WheelItemBean> {
    private int     mSelectPosition;
    private Context mContext;

    /**
     * @param context 上下文
     * @param data    数据源
     */
    public MyWheelViewAdapter(Context context, List<WheelItemBean> data) {
        super(context, data, R.layout.include_wheel_view_item);
        this.mContext = context;
    }

    @Override
    public void onHandleScroll(int selectPosition) {
        mSelectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    public void covertView(WheelHolder holder, int position, List<WheelItemBean> dataSource, WheelItemBean item) {
        TextView mTvTitle = holder.getView(R.id.tv_title);
        View contentView = holder.getmConvertView().findViewById(R.id.layout_content);
        float scale = 1f;
        float alpha = 0.8f;
        if (mSelectPosition == position) {
            scale = 1.2f;
            alpha = 1f;
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_1b88ed));
        } else {
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_a0a0a0));
        }
        mTvTitle.setText(item.getTitle());
        ViewHelper.setScaleX(contentView, scale);
        ViewHelper.setScaleY(contentView, scale);
        ViewHelper.setAlpha(contentView, alpha);
    }

    /**
     * 获取选择的索引
     */
    public int getSelectPosition() {
        return mSelectPosition;
    }
}