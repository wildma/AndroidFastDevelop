package com.wildma.androidfastdevelop.widget.wheelview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/9/16
 * Desc	        ${滚轮控件Dialog}
 */
public class WheelViewDialog extends Dialog implements View.OnClickListener {

    private Context            mContext;
    private TextView           mTvConfirm;
    private TextView           mtvCancel;
    private WheelView          mWheelView;
    private MyWheelViewAdapter mAdapter;
    List<WheelItemBean> mList;

    public WheelViewDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public WheelViewDialog(Context context, int theme, List<WheelItemBean> list) {
        super(context, theme);
        this.mContext = context;
        this.mList = list;
        initDialog();
    }

    /**
     * 初始化Dialog
     */
    public void initDialog() {
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        win.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        show();
    }

    /**
     * 隐藏Dialog
     */
    private void hideDialog() {
        cancel();
        dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_wheel_view);

        initView();
        initListener();
    }

    private void initView() {
        mtvCancel = (TextView) findViewById(R.id.tv_cancel);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
        mWheelView = (WheelView) findViewById(R.id.wheelview);
        mAdapter = new MyWheelViewAdapter(mContext, mList);
        mWheelView.setAdapter(mAdapter);
    }

    private void initListener() {
        mTvConfirm.setOnClickListener(this);
        mtvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (mListener != null) {
                    mListener.onItemClick(mAdapter.getSelectPosition());
                }
                hideDialog();
                break;
            case R.id.tv_cancel:
                hideDialog();
                break;
        }
    }

    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int selectPosition);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
