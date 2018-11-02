package com.wildma.androidfastdevelop.widget.dialog;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.utils.TimeUtils;
import com.wildma.androidfastdevelop.widget.DividerLinearItemDecoration;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${DialogHelper}
 */
public class DialogHelper {

    /**
     * 显示默认Dialog:一个Button
     *
     * @param activity              Activity
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               button文字
     * @param positiveClickListener 点击事件
     */
    public static void showDefaultDialog(Activity activity, String title, String content, String btn1Str, IDialog.OnClickListener positiveClickListener) {
        showDefaultDialog(activity, title, content, btn1Str, positiveClickListener, "", null);
    }

    /**
     * 显示默认Dialog:二个Button
     *
     * @param activity              Activity
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               左边按钮
     * @param negativeClickListener 左边点击事件
     * @param btn2Str               右边按钮
     * @param positiveClickListener 右边点击事件
     */
    public static void showDefaultDialog(Activity activity, String title, String content, String btn1Str,
                                         IDialog.OnClickListener positiveClickListener, String btn2Str, IDialog.OnClickListener negativeClickListener) {
        CommonDialog.Builder builder = new CommonDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            builder.setContent(content);
        }
        if (positiveClickListener != null) {
            if (TextUtils.isEmpty(btn1Str)) {
                builder.setPositiveButton(positiveClickListener);
            } else {
                builder.setPositiveButton(btn1Str, positiveClickListener);
            }
        }
        if (negativeClickListener != null) {
            if (TextUtils.isEmpty(btn2Str)) {
                builder.setNegativeButton(negativeClickListener);
            } else {
                builder.setNegativeButton(btn2Str, negativeClickListener);
            }
        }
        builder.show();
    }

    /**
     * 通用底部弹出列表Dialog
     */
    public static void showBottomListDialog(final Activity activity, final ArrayList<String> list, final IDialog.IDialogResultListener<Integer> resultListener) {

        new CommonDialog.Builder(activity)
                .setDialogView(R.layout.dialog_bottom)
                .setWindowBackgroundP(0.5f)
                .setAnimStyle(R.style.ActionSheetDialogAnimation)
                .setCancelableOutSide(true)
                .setCancelableOutSide(true)
                .setScreenWidthP(1.0f)
                .setGravity(Gravity.BOTTOM)
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {

                        Button btn_cancel_dialog = view.findViewById(R.id.btn_cancel_dialog);
                        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                        CommonBottomDialogAdapter adapter = new CommonBottomDialogAdapter(activity);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        DividerLinearItemDecoration dividerItemDecoration = new DividerLinearItemDecoration(activity, DividerLinearItemDecoration.VERTICAL_LIST, false);
                        dividerItemDecoration.setDividerDrawable(ContextCompat.getDrawable(activity, R.drawable.shape_common_linear_divider));
                        recyclerView.addItemDecoration(dividerItemDecoration);
                        recyclerView.setAdapter(adapter);
                        adapter.setDate(list);

                        adapter.setOnItemListener(new CommonBottomDialogAdapter.OnItemListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (resultListener != null) {
                                    resultListener.onResult(position);
                                }
                                dialog.dismiss();
                            }
                        });
                        btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                }).show();
    }

    /**
     * 显示日期选择器
     *
     * @param selectDate 上一次选中的日期，传空表示当前日期，格式为2018-08-08
     */
    public static void showDatePicker(final Activity activity, final String selectDate, final IDialog.IDialogResultListener<String> resultListener) {
        new CommonDialog.Builder(activity)
                .setDialogView(R.layout.dialog_date_picker)
                .setWindowBackgroundP(0.5f)
                .setAnimStyle(R.style.ActionSheetDialogAnimation)
                .setCancelableOutSide(true)
                .setCancelableOutSide(true)
                .setScreenWidthP(1.0f)
                .setGravity(Gravity.BOTTOM)
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        TextView tv_date_cancel = view.findViewById(R.id.tv_date_cancel);
                        TextView tv_date_confirm = view.findViewById(R.id.tv_date_confirm);
                        final MyDatePicker datePicker = view.findViewById(R.id.datePicker);

                        String nowDate = TimeUtils.getNowString(TimeUtils.DATE_FORMAT_Y_M_D);//当前日期
                        String showDate;//显示在日期控件上的日期
                        if (TextUtils.isEmpty(selectDate)) {
                            showDate = nowDate;
                        } else {
                            showDate = selectDate;
                        }
                        final StringBuffer sb = new StringBuffer();
                        sb.append(showDate);//默认是当前日期
                        Integer year = Integer.valueOf(showDate.split("-")[0]);
                        Integer month = Integer.valueOf(showDate.split("-")[1]) - 1;
                        Integer day = Integer.valueOf(showDate.split("-")[2]);
                        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                sb.setLength(0);//清空StringBuffer
                                sb.append(String.format(Locale.CHINA, "%d-%02d-%02d", year, monthOfYear + 1,
                                        dayOfMonth));
                            }
                        });
                        tv_date_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        tv_date_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resultListener.onResult(sb.toString());
                                dialog.dismiss();
                            }
                        });
                    }
                }).show();
    }

    /**
     * 显示时间选择器
     *
     * @param selectTime 上一次选中的时间,传空表示当前时间 格式为08:08
     */
    public static void showTimePicker(final Activity activity, final String selectTime, final IDialog.IDialogResultListener<String> resultListener) {
        new CommonDialog.Builder(activity)
                .setDialogView(R.layout.dialog_time_picker)
                .setWindowBackgroundP(0.5f)
                .setAnimStyle(R.style.ActionSheetDialogAnimation)
                .setCancelableOutSide(true)
                .setCancelableOutSide(true)
                .setScreenWidthP(1.0f)
                .setGravity(Gravity.BOTTOM)
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        TextView tv_time_cancel = view.findViewById(R.id.tv_time_cancel);
                        TextView tv_time_confirm = view.findViewById(R.id.tv_time_confirm);
                        final TimePicker timePicker = view.findViewById(R.id.timePicker);

                        String nowTime = TimeUtils.getNowString(TimeUtils.DATE_FORMAT_H_M);//当前时间
                        String showTime;//显示在时间控件上的时间
                        if (TextUtils.isEmpty(selectTime)) {
                            showTime = nowTime;
                        } else {
                            showTime = selectTime;
                        }
                        final StringBuffer sb = new StringBuffer();
                        sb.append(showTime);//默认是当前时间
                        Integer hour = Integer.valueOf(showTime.split(":")[0]);
                        Integer minute = Integer.valueOf(showTime.split(":")[1]);
                        timePicker.setHour(hour);//设置显示的时
                        timePicker.setMinute(minute);//设置显示的分
                        timePicker.setIs24HourView(true);//设置24小时制显示
                        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                            @Override
                            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                sb.setLength(0);//清空StringBuffer
                                sb.append(String.format(Locale.CHINA, "%02d:%02d", hourOfDay, minute));
                            }
                        });
                        tv_time_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        tv_time_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resultListener.onResult(sb.toString());
                                dialog.dismiss();
                            }
                        });
                    }
                }).show();
    }
}
