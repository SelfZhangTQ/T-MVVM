package com.code.mvvm.core.data.pojo.banner;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.util.List;

public class BannerAdListVo extends BaseObject
{
    public List<BannerVo> data;

    public BannerAdListVo(List<BannerVo> data) {
        this.data = data;
    }
}
