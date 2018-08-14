package com.code.mvvm.core.data.pojo.activity;

import java.io.Serializable;
import java.util.List;

public class ActivityListVo implements Serializable {

    public List<DataBean> data;

    public static class DataBean {
        public String etime;
        public String ctime;
        public ImgBean img;
        public String desc;
        public String thumb;
        public String catid;
        public String newsid;
        public String costcoin;
        public String username;
        public String status;
        public String signup_url;
        public String listorder;
        public String param2;
        public String click_type;
        public String click_button_text;
        public String param1;
        public String activity_url;
        public String keywords;
        public String url;
        public String utime;
        public String btime;
        public String title;
        public int executestatus;
        public int resttime;

        public static class ImgBean {
            public String rid;
            public String img;
            public Object description;
            public int resource_type;

        }
    }
}
