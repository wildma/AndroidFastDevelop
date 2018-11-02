package com.wildma.androidfastdevelop.widget.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Locale;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${DatePicker}
 */
public class MyDatePicker extends DatePicker {
    public MyDatePicker(Context context) {
        super(context);
    }

    public MyDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置只显示年月
     */
    public void setYearMonth() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (country.equals("CN") || country.equals("TW")) {
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(2).setVisibility(View.GONE);
        } else {
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(1).setVisibility(View.GONE);
        }
    }

    /**
     * 设置只显示年
     */
    public void setYear() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (country.equals("CN") || country.equals("TW")) {
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(2).setVisibility(View.GONE);
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(1).setVisibility(View.GONE);
        } else {
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(1).setVisibility(View.GONE);
            ((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0))
                    .getChildAt(2).setVisibility(View.GONE);
        }
    }
}
