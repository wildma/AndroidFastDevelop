package com.wildma.androidfastdevelop.bean;

/**
 * Author   wildma
 * Date     2017/10/8
 * Desc     ${测试列表实体类}
 */

public class TestListBeanV2 {

    /**
     * 后台返回数据格式：{"code": 200,"message": "success","data": [{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]}
     * 需要GsonFormat的格式：[{"name": "wildma","sex": "男"},{"name": "wildma2","sex": "女"}]
     */

    /**
     * name : wildma
     * sex : 男
     */

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
