package com.code.mvvm.core.data.pojo.live;

import java.util.ArrayList;

public class LiveTypeVo {


    /**
     * errno : 0
     * data : {"s_catalog":[{"id":1,"name":"色彩"},{"id":4,"name":"素描"},{"id":5,"name":"速写"},{"id":2,"name":"设计"}]}
     */

    public int errno;
    public DataBean data;

    public class DataBean {
        public ArrayList<SCatalogBean> s_catalog;

        public class SCatalogBean {
            /**
             * id : 1
             * name : 色彩
             */

            public Integer id;
            public String name;
        }
    }
}
