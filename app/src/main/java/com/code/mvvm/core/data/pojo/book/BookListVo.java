package com.code.mvvm.core.data.pojo.book;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.util.ArrayList;

public class BookListVo extends BaseObject {
    public Data data;

    public static class Data {
        public ArrayList<BookVo> content;
    }

}
