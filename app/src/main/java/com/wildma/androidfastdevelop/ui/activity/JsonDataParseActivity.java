package com.wildma.androidfastdevelop.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.bean.TestBean;
import com.wildma.androidfastdevelop.bean.TestListBean;
import com.wildma.androidfastdevelop.bean.TestListBeanV2;
import com.wildma.androidfastdevelop.utils.GsonUtils;

import java.util.List;

/**
 * Author       wildma
 * Date         2017/10/8
 * Desc	        ${测试json数据解析}
 */
public class JsonDataParseActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data_parse);
    }

    /**
     * 解析对象
     * @param view
     */
    public void parseObject(View view) {
        /**
         * 后台返回数据data为对象格式：
         * 格式一：{"code": 200,"message": "success","data": {"name": "wildma","sex": "男"}}
         * 格式二：{"name": "wildma","sex": "男"}
         */
        String jsonData = "{\"code\": 200,\"message\": \"success\",\"data\": {\"name\": \"wildma\",\"sex\": \"男\"}}";
        if (GsonUtils.isSuccess(this, jsonData)) {
            TestBean objectBean = GsonUtils.parseObject(jsonData, TestBean.class);
            Log.d(TAG, "parseObject: " + objectBean.getData().getName());
        }
    }

    /**
     * 解析包含数组的对象
     * @param view
     */
    public void parseObjectV2(View view) {
        //后台返回数据data为数组格式：{"code": 200,"message": "success","data": [{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]}
        String jsonData = "{\"code\": 200,\"message\": \"success\",\"data\": [{\"name\": \"wildma\",\"sex\": \"男\"},{\"name\": \"wildma2\",\"sex\": \"女\"}]}";
        if (GsonUtils.isSuccess(this, jsonData)) {
            TestListBean bean = GsonUtils.parseObject(jsonData, TestListBean.class);
            Log.d(TAG, "parseObjectV2: " + bean.getData().get(0).getName());
        }
    }

    /**
     * 解析数组 (不常用，直接用parseObjectV2()即可)
     * @param view
     */
    public void parseArray(View view) {
        //后台返回数据data为数组格式：{"code": 200,"message": "success","data": [{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]}
        String arrayData = "{\"code\": 200,\"message\": \"success\",\"data\": [{\"name\": \"wildma\",\"sex\": \"男\"},{\"name\": \"wildma2\",\"sex\": \"女\"}]}";
        if (GsonUtils.isSuccess(this, arrayData)) {
            List<TestListBeanV2> list = GsonUtils.parseArray(arrayData, TestListBeanV2[].class);
            Log.d(TAG, "parseArray: " + list.get(0).getName());
        }
    }
}
