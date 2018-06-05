package com.wildma.androidfastdevelop.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Author       wildma
 * Date         2017/10/8
 * Desc	        ${Gson工具类}
 */
public class GsonUtils {

    private static       Gson   gson            = new Gson();
    private static final int    SUCCESS_CODE    = 200;//请求成功码
    private static final int    ERROR_CODE      = -1;//请求失败码
    private static final int    LOGON_INVALID   = -100;//登录失效
    private static final String CODE            = "code";//服务器返回状态码的字段，例如：0=成功、1=失败、其他...
    private static final String MESSAGE         = "message";//服务器返回消息的字段，例如："请求成功"、"删除成功"、"服务器出现异常"
    private static final String DATA            = "data";//服务器返回数据的字段
    private static final String MESSAGE_DEFAULT = "服务器出了点小问题，请稍候再试！";//服务器出现异常等，但是没有返回message说明，前端默认给的

    /**
     * 解析jsonObject
     *
     * @param jsonString
     * @param classes
     * @param <T>
     * @return 使用：TestBean bean = GsonUtils.parseObject("jsonString", TestBean.class);
     */
    public static <T> T parseObject(String jsonString, Class<T> classes) {
        T t = null;
        try {
            t = gson.fromJson(jsonString, classes);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 解析jsonArray
     *
     * @param jsonString
     * @param classes
     * @param <T>
     * @return 使用：List<TestBean> list = GsonUtils.parseArray("jsonString", TestBean[].class);
     * data字段要根据具体返回的数据来定
     */
    public static <T> List<T> parseArray(String jsonString, Class<T[]> classes) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(DATA); //解析jsonString里面的data字段为数组
            T[] list = gson.fromJson(jsonArray.toString(), classes);
            return Arrays.asList(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 是否成功（判断后台是否返回成功）
     *
     * @param context
     * @return
     */
    public static boolean isSuccess(final Context context, String jsonData) {
        JSONObject json = null;
        int code = ERROR_CODE;
        try {
            json = new JSONObject(jsonData);
            code = getInt(json, CODE);
            String errMsg = getStr(json, MESSAGE);
            switch (code) {
                case SUCCESS_CODE:
                    break;
                case LOGON_INVALID:
                    //TODO:退出登录
                    break;
                case ERROR_CODE://服务器出现异常
                default://或者其他未与后台商量好的状态码
                    if (TextUtils.isEmpty(errMsg)) {
                        Toast.makeText(context, MESSAGE_DEFAULT, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code == SUCCESS_CODE ? true : false;
    }

    /**
     * 从json对象中得到，对应的integer数据
     *
     * @param json    json对象
     * @param keyname 对应的键名
     * @return 对应的integer类型的键值
     */
    public static int getInt(JSONObject json, String keyname) {
        int result = ERROR_CODE;
        try {
            if (json != null && !json.isNull(keyname)) {
                result = json.getInt(keyname);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从json对象中得到，对应的String数据
     *
     * @param json    json对象
     * @param keyname 对应的键名
     * @return 对应的String类型的键值
     */
    public static String getStr(JSONObject json, String keyname) {
        String result = "";
        try {
            if (json != null && !json.isNull(keyname)) {
                result = json.getString(keyname);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
