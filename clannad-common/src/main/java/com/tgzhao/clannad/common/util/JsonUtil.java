package com.tgzhao.clannad.common.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by tgzhao on 2016/11/22.
 */
public class JsonUtil {

    public static void main(String[] args) {
        AllBean bean = new AllBean();
        bean.setName("名字");
        bean.setGeo("geo字段");
        bean.setId(121212);
        bean.setText("text字段");

        System.out.print(JSON.toJSON(bean));
    }
}

class AllBeanBase {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class AllBean extends AllBeanBase {
    private long id;
    private String text;
    private String geo;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getGeo() {
        return geo;
    }
    public void setGeo(String geo) {
        this.geo = geo;
    }
}