package com.code.mvvm.core.data.pojo.article;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.util.List;


public class ArticleTypeVo extends BaseVo {


    public List<DataBean> data;

    public static class DataBean {
        public Integer maintypeid;
        public String maintypename;
    }
}
