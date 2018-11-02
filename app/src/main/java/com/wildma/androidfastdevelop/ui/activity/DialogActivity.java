package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.dialog.DialogHelper;
import com.wildma.androidfastdevelop.widget.dialog.IDialog;

import java.util.ArrayList;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${Dialog demo}
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    /**
     * 显示默认Dialog:一个Button
     *
     * @param view
     */
    public void showDefaultDialog1(View view) {
        DialogHelper.showDefaultDialog(this, "", "今天天气很好",
                "知道了",
                new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 显示默认Dialog:二个Button
     *
     * @param view
     */
    public void showDefaultDialog2(View view) {
        DialogHelper.showDefaultDialog(this, "", "确定退出登录?",
                "取消",
                new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                }, "确定", new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 显示日期
     */
    public void showDate(View view) {
        DialogHelper.showDatePicker(this, "", new IDialog.IDialogResultListener<String>() {
            @Override
            public void onResult(String result) {
                Toast.makeText(DialogActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示时间
     */
    public void showTime(View view) {
        DialogHelper.showTimePicker(this, "", new IDialog.IDialogResultListener<String>() {
            @Override
            public void onResult(String result) {
                Toast.makeText(DialogActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示底部弹出列表Dialog
     */
    public void showBottomListDialog(View view) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("选项一");
        list.add("选项二");
        list.add("选项三");
        list.add("选项四");
        DialogHelper.showBottomListDialog(this, list, new IDialog.IDialogResultListener<Integer>() {
            @Override
            public void onResult(Integer position) {
                Toast.makeText(DialogActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


}