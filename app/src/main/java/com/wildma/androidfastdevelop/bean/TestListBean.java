package com.wildma.androidfastdevelop.bean;

import java.util.List;

/**
 * Author   wildma
 * Date     2017/10/8
 * Desc     ${测试列表实体类}
 */

public class TestListBean {

    /**
     * 后台返回数据格式：{"code": 200,"message": "success","data": [{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]}
     * 需要GsonFormat的格式：{"data": [{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]}
     */


    /**
     * data : [{"sex":"男","name":"wildma"},{"sex":"女","name":"wildma2"}]
     */
    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * sex : 男
         * name : wildma
         */
        private String sex;
        private String name;

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public String getName() {
            return name;
        }
    }

}
