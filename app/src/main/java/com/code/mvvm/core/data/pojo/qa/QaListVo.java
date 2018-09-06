package com.code.mvvm.core.data.pojo.qa;

import java.util.List;

public class QaListVo {

    public int errno;


    public List<DataBean> data;

    public static class DataBean {
        public String ask_limit;
        public String ctime;
        public String desc;
        public String catid;
        public String thumb;
        public String activity_type;
        public String newsid;
        public String answer_uids;
        public String status;
        public String username;
        public String listorder;
        public String reserve1;
        public String cover_type;
        public String copyfrom;
        public String supportcount;
        public String qaid;
        public String keywords;
        public String cmtcount;
        public String utime;
        public String hits;
        public String title;
        public String reserve2;
        public String reserve3;
        public List<ImgsBean> imgs;
        public Integer fav;
        public String share_url;
        public String url;

        public static class ImgsBean {
            public String rid;
            public String description;
            public String img;
            public String resource_type;


        }
    }
}
