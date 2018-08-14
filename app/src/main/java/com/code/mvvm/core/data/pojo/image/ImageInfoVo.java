package com.code.mvvm.core.data.pojo.image;

import java.io.Serializable;

public class ImageInfoVo implements Serializable{

    public String url;
    public int w=1;
    public int h=1;

    @Override
    public String toString() {
        return "ImageInfoVo{" +
                "url='" + url + '\'' +
                ", width=" + w +
                ", height=" + h +
                '}';
    }
}
