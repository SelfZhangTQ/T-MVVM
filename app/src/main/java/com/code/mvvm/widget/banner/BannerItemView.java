package com.code.mvvm.widget.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/6/21 18:00
 */
public class BannerItemView extends AbsItemHolder<BannerListVo, BannerItemView.ViewHolder> {

    public BannerItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_banner_view;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull BannerItemView.ViewHolder holder, @NonNull final BannerListVo bannerAdListVo) {
        holder.mBannerView.delayTime(5).setBannerView(() -> {
            List<ImageView> imageViewList = new ArrayList<>();
            for (int i = 0; i < bannerAdListVo.data.size(); i++) {
                ImageView mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                Glide.with(mContext).load(bannerAdListVo.data.get(i).topimage1 == null ? bannerAdListVo.data.get(i).topimage : bannerAdListVo.data.get(i).topimage1).centerCrop().into(mImageView);
                imageViewList.add(mImageView);
            }
            return imageViewList;
        }).build(bannerAdListVo.data);

    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends AbsHolder {

        private BannerView mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = getViewById(R.id.banner);
        }

    }

}
