package com.code.mvvm.core.data.pojo.live;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.util.List;

public class LiveCommentListVo extends BaseVo {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * cid : 240157
         * uid : 137973
         * content : 我的天啊，这个么好
         * sname : 1饿2
         */
        public String ctime;
        public int type;
        public String cid;
        public String uid;
        public String content;
        public String sname;


    }
}
