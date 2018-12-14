package com.wildma.androidfastdevelop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/12/9
 * Desc	        ${ReturnDataActivity}
 */
public class ReturnDataActivity extends AppCompatActivity {

    private EditText mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_data);
        mEtContent = findViewById(R.id.et_content);
    }

    /**
     * 返回数据
     *
     * @param view
     */
    public void returnData(View view) {
        setResult(RESULT_OK, new Intent().putExtra("text", mEtContent.getText().toString().trim()));
        finish();
    }
}
