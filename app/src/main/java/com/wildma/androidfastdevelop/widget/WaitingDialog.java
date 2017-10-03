package com.wildma.androidfastdevelop.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Date         2017/9/30
 * Desc	        ${等待中dialog-仿微信发送朋友圈等待中dialog}
 */
public class WaitingDialog {

    /**
     * 显示dialog
     *
     * @param context      上下文
     * @param hintText     提示文本
     * @param isCancelable 是否可以按返回键关闭dialog
     * @return Dialog对象
     */
    public static Dialog showDialog(Context context, String hintText, boolean isCancelable) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_waiting, null);
        TextView tvHintText = (TextView) view.findViewById(R.id.tv_hint_text);
        tvHintText.setText(hintText);

        Dialog dialog = new Dialog(context, R.style.MyDialogStyle);// 自定义dialog样式
        dialog.setCancelable(isCancelable); //设置是否可以按返回键关闭dialog
        dialog.setCanceledOnTouchOutside(false); //设置是否可以触摸外部区域关闭dialog
        dialog.setContentView(view);// 设置內容布局

        //设置布局宽高
        /*Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();//获取对话框当前的参数值
        attributes.width = (int) (ScreenUtils.getScreenWidth(context) / 1.21);
        //attributes.height = tempHeight;
        window.setAttributes(attributes);*/

        dialog.show();
        return dialog;
    }

    /**
     * 关闭dialog
     *
     * @param dialog Dialog对象
     */
    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}