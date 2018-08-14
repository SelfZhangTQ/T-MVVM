package com.code.mvvm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.home.CatagoryInfoVo;

import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/26 14:05
 */
public class HomeCatagoryAdapter extends BaseRecyclerAdapter<CatagoryInfoVo> {


    public HomeCatagoryAdapter(Context context, @Nullable List<CatagoryInfoVo> list, int itemLayoutId) {
        super(context, list, R.layout.item_classify);
    }

    @Override
    protected void convert(BaseViewHolder holder, final CatagoryInfoVo catagoryInfoVo, int position, List payloads) {
        ImageView iv_classify = holder.getView(R.id.iv_classify);
        TextView tv_classify = holder.getView(R.id.tv_classify);
        Glide.with(App.Instance())
                .load(catagoryInfoVo.resId)
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv_classify);
        tv_classify.setText(catagoryInfoVo.title);

    }
}
