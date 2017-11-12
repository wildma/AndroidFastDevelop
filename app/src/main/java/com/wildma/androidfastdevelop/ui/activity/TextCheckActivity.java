package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.utils.TextCheckUtils;

/**
 * Author       wildma
 * Date         2017/10/8
 * Desc	        ${监听多个EditText与TextView文本，改变按钮状态。}
 */
public class TextCheckActivity extends AppCompatActivity {

    private EditText       mEtName;
    private EditText       mEtNumber;
    private TextView       mTvUpload;
    private Button         mBtnSubmit;
    private RelativeLayout mRlIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_check);

        initView();
        initListener();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mTvUpload = (TextView) findViewById(R.id.tv_upload);
        mRlIdCard = (RelativeLayout) findViewById(R.id.rl_id_card);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
    }

    private void initListener() {
        //1、传入需要监听的EditText与TextView
        TextCheckUtils textCheckUtils = new TextCheckUtils(mEtName, mEtNumber, mTvUpload);
        //2、设置是否全部填写完成监听
        textCheckUtils.setOnCompleteListener(new TextCheckUtils.OnCompleteListener() {
            @Override
            public void isComplete(boolean isComplete) {
                if (isComplete) {
                    mBtnSubmit.setEnabled(true);
                } else {
                    mBtnSubmit.setEnabled(false);
                }
            }
        });

        //模拟图片已上传
        mRlIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mTvUpload.getText().toString())) {
                    mTvUpload.setText("已上传");
                } else {
                    mTvUpload.setText("");
                }
            }
        });
    }
}
