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

        public String activity_url;

        public String btime;

        public String hits;

        public String utime;

        public String status;

        public String etime;

        public String title;

        public String catid;

        public String signup_url;

        public String keywords;

        public String username;

        public String copyfrom;

        public String desc;

        public String supportcount;

        public String cmtcount;

        public String click_button_text;

        public String param1;

        public String param2;

        public String click_type;

        @Override
        public String toString()
        {
            return "ActContent [ctime=" + ctime + ", thumb=" + thumb
                    + ", listorder=" + listorder + ", newsid=" + newsid
                    + ", activity_url=" + activity_url + ", btime=" + btime
                    + ", hits=" + hits + ", utime=" + utime + ", status="
                    + status + ", etime=" + etime + ", title=" + title
                    + ", catid=" + catid + ", signup_url=" + signup_url
                    + ", keywords=" + keywords + ", username=" + username
                    + ", copyfrom=" + copyfrom + ", desc=" + desc
                    + ", supportcount=" + supportcount + ", cmtcount="
                    + cmtcount + ", click_button_text=" + click_button_text
                    + ", param1=" + param1 + ", param2=" + param2
                    + ", click_type=" + click_type + "]";
        }

    }

}
