package com.code.mvvm.core.data.pojo.banner;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.util.List;

public class BannerListVo extends BaseVo
{
    public List<BannerVo> data;

    public BannerListVo(List<BannerVo> data) {
        this.data = data;
    }
}
