package com.wildma.androidfastdevelop.bean;

/**
 * Author   wildma
 * Date     2018/09/16
 * Desc     ${批量操作bean}
 */
public class BatchOperationBean {

    private String  title;
    private String  source;
    public  boolean isSelect;//是否选中

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
