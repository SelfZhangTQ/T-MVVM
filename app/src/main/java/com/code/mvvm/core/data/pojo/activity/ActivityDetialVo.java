package com.code.mvvm.core.data.pojo.activity;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.io.Serializable;

public class ActivityDetialVo extends BaseVo
{
    public ActData data;

    public static class ActData
    {
        public ActContent content;
    }

    public static class ActContent implements Serializable
    {
        public String ctime;

        public String thumb;

        public String listorder;

        public String newsid;

        public String hits;

        public String utime;

        public String status;

        public String title;

        public String username;

        public String desc;

        public String supportcount;

        public String cmtcount;

    }

}
