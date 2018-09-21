package com.wildma.androidfastdevelop.widget.wheelview;

import java.io.Serializable;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/9/16
 * Desc	        ${滚轮控件item bean}
 */
public class WheelItemBean implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
