package com.code.mvvm.core.data.pojo.activity;

import java.io.Serializable;
import java.util.List;

public class ActivityListVo implements Serializable {

    public List<DataBean> data;

    public static class DataBean {
        public String ctime;
        public ImgBean img;
        public String desc;
        public String thumb;
        public String newsid;
        public String username;
        public String status;
        public String listorder;
        public String url;
        public String utime;
        public String title;

        public static class ImgBean {
            public String rid;
            public String img;
            public Object description;
            public Integer resource_type;

        }
    }
}
