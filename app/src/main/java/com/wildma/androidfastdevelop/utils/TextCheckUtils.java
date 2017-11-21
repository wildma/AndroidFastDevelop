package com.wildma.androidfastdevelop.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;


/**
 * Author       wildma
 * Date         2017/10/8
 * Desc	        ${文本检查工具类-监听多个EditText与TextView是否全部都有内容}
 */
public class TextCheckUtils {

    private TextView[] mTextViews;//传入的EditText与TextView数组

    /**
     * 构造方法传入需要监听的EditText与TextView
     *
     * @param textViews
     */
    public TextCheckUtils(TextView... textViews) {
        this.mTextViews = textViews;
        init();
    }


    /**
     * 将传入的EditText与TextView设置文本变化监听
     */
    private void init() {
        for (TextView textView : mTextViews) {
            textView.addTextChangedListener(new MyTextWatcher());
        }
    }

    /**
     * 监听文本变化
     */
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isAllHasContent()) {
                onCompleteListener.isComplete(true);
            } else {
                onCompleteListener.isComplete(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 是否全部有内容
     *
     * @return
     */
    private boolean isAllHasContent() {
        for (TextView textView : mTextViews) {
            if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                return false;
            }
        }
        return true;
    }


    /**
     * 监听是否全部填写完成接口
     */
    public interface OnCompleteListener {

        /**
         * 是否完成
         *
         * @param isComplete
         */
        void isComplete(boolean isComplete);
    }

    OnCompleteListener onCompleteListener;

    public void setOnCompleteListener(OnCompleteListener listener) {
        onCompleteListener = listener;
    }

}