package com.code.mvvm.core.data.pojo.book;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.io.Serializable;
import java.util.ArrayList;

public class BookTypeVo extends BaseVo {
    public Data data;

    public static class Data {
        public ArrayList<ClassContent> f_catalog;
    }

    public static class ClassContent implements Serializable {
        public String f_catalog_id;
        public String f_catalog_name;

    }

}
