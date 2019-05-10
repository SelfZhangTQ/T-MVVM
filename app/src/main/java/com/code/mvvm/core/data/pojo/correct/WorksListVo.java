package com.code.mvvm.core.data.pojo.correct;

import com.code.mvvm.core.data.pojo.image.ImageVo;

import java.util.ArrayList;

public class WorksListVo {
    public WorksItem data;

    public class WorksItem {
        public ArrayList<Works> content;
    }

    public static class Works {
        public String tid;
        public String hits;
        public String utime;
        public String f_catalog_id;
        public String uid;
        public String content;
        public String province;
        public String avatar;
        public String sname;
        public String genderid;
        public String gender;
        public String intro;
        /**
         * 图片集合
         */
        public ArrayList<ImageVo> imgs_list;
        public ImageVo imgs;
        public String correctid;
        public WorkInfoVo correct;

        public boolean isCollection=false;

    }
}
