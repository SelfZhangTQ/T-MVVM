package com.code.mvvm.core.data.pojo.article;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.util.List;


public class ArticleTypeVo extends BaseObject {


    public List<DataBean> data;

    public static class DataBean {
        public int maintypeid;
        public String maintypename;
    }
}
