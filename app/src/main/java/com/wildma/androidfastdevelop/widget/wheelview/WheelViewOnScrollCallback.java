package com.wildma.androidfastdevelop.widget.wheelview;
/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/9/16
 * Desc	        ${滚轮滚动回调}
 * Source       ykbjson
 */
public interface WheelViewOnScrollCallback {

        void onHandleScroll(int selectPosition);

        void onHandleIdle(WheelListView wheelListView, int selectPosition);
    }