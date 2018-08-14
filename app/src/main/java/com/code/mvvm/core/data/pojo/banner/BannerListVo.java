package com.code.mvvm.core.data.pojo.banner;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.util.List;

public class BannerListVo extends BaseObject
{
    public List<BannerVo> data;

    public BannerListVo(List<BannerVo> data) {
        this.data = data;
    }
}
