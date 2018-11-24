package com.wildma.androidfastdevelop.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/18
 * Desc	        ${不为空实体类}
 */
public class NotNullBean {

    private String           name;//String
    private List<ListEntity> list;//List
    private DataBean         data;//自定义对象

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListEntity> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public DataBean getData() {
        if (data == null) {
            data = new DataBean();
        }
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ListEntity {
        private String name;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DataBean {
        private String name;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
