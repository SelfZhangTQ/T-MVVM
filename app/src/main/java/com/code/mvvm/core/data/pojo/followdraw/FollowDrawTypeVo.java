package com.code.mvvm.core.data.pojo.followdraw;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.util.List;

public class FollowDrawTypeVo extends BaseObject {


    public List<DataBean> data;

    public static class DataBean {
        public int maintypeid;
        public String maintypename;
    }
}
