package com.code.mvvm.core.data.pojo.article;

import java.util.List;

public class ArticleInfoVo {
    public String newsid;
    public String lecture_level1;
    public String lecture_level2;
    public String status;
    public String publishtime;
    public String thumbtype;
    public String is_in_list;
    public String stick_date;
    public String newstype;
    public String content_type;
    public String catid;
    public String title;
    public String thumb;
    public String keywords;
    public String desc;
    public String listorder;
    public String username;
    public String ctime;
    public String utime;
    public String hits;
    public String cmtcount;
    public String url;
    public Integer fav;
    /**
     * rid : 725480
     * img : https://img.meiyuanbang.com/cms/lecture/2017-02-09/3258F96F8344A12A78D5841EEA71A0E0.jpg
     * description : null
     * resource_type : 0
     */
    public List<ImgBean> img;

    public static class ImgBean {
        public Integer rid;
        public String img;
        public Object description;
        public Integer resource_type;

    }
}
