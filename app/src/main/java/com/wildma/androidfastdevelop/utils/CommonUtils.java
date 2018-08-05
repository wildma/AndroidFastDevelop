package com.wildma.androidfastdevelop.utils;

import android.content.Context;
import android.text.TextPaint;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/6/24
 * Desc	        ${公共工具类}
 */
public class CommonUtils {

    /**
     * 获取文本宽度
     *
     * @param context
     * @param text     需要测量的文本
     * @param textSize 字体大小，单位sp
     * @return
     */
    public static float getTextWidth(Context context, String text, int textSize) {
        TextPaint paint = new TextPaint();
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return paint.measureText(text);
    }
}
