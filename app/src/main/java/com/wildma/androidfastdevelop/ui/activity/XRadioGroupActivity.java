package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.widget.XRadioGroup;
/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/7/29
 * Desc	        ${演示XRadioGroup的使用}
 */
public class XRadioGroupActivity extends AppCompatActivity {


    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;
    private XRadioGroup mXRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_radio_group);
        initView();
    }

    private void initView() {
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mRb4 = (RadioButton) findViewById(R.id.rb4);
        mXRadioGroup = (XRadioGroup) findViewById(R.id.xRadioGroup);

        mXRadioGroup.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup group, int checkedId) {
                Log.d("TAG", checkedId + "is checked");
                switch (checkedId) {
                    case R.id.rb1:
                        break;
                    case R.id.rb2:
                        break;
                    case R.id.rb3:
                        break;
                    case R.id.rb4:
                        break;
                }
            }
        });
    }
}
