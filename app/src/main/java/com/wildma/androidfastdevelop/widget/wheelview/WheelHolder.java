package com.wildma.androidfastdevelop.widget.wheelview;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/9/16
 * Desc	        ${滚轮控件listview的ViewHolder}
 * Source       ykbjson
 */
public class WheelHolder {

    private int mPosition;
    private View mConvertView;
    SparseArray<View> mMembers;

    public View getmConvertView() {
        return mConvertView;
    }

    private WheelHolder(ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mMembers = new SparseArray<>();
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false);

        mConvertView.setTag(this);
    }

    public static WheelHolder get(View convertView, ViewGroup parent,
                                  int layoutId, int position) {
        if (convertView == null) {
            return new WheelHolder(parent, layoutId, position);
        } else {
            return (WheelHolder) convertView.getTag();
        }
    }


    public <T extends View> T getView(int viewId) {
        View view = mMembers.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mMembers.put(viewId, view);
        }
        return (T) view;
    }
}