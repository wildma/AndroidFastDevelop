package com.wildma.androidfastdevelop.widget.dialog;

import android.view.View;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${Dialog接口}
 */
public interface IDialog {

    /**
     * 关闭Dialog
     */
    void dismiss();

    interface OnBuildListener {
        void onBuildChildView(IDialog dialog, View view, int layoutRes);
    }

    interface OnClickListener {
        void onClick(IDialog dialog);
    }

    interface IDialogResultListener<T> {
        /**
         * 结果回调
         */
        void onResult(T result);
    }
}
