package com.wildma.androidfastdevelop.bean;

/**
 * Author   wildma
 * Date     2017/10/8
 * Desc     ${测试实体类}
 */

public class TestBean {

    /**
     * 后台返回数据格式：{"code": 200,"message": "success","data": {"name": "wildma","sex": "男"}}
     * 需要GsonFormat的格式：{"data": {"name": "wildma","sex": "男"}}
     */

    /**
     * data : {"sex":"男","name":"wildma"}
     */
    private DataEntity data;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public DataEntity getData() {
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
