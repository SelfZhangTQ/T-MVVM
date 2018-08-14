package com.code.mvvm.widget.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.trecyclerview.multitype.AbsItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/21 18:00
 */
public class BannerItemView extends AbsItemView<BannerAdListVo, BannerItemView.ViewHolder> {
    private Context mContext;

    public BannerItemView(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    BannerItemView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BannerItemView.ViewHolder(inflater.inflate(R.layout.common_banner_view, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull BannerItemView.ViewHolder holder, @NonNull final BannerAdListVo bannerAdListVo) {
        holder.mBannerView.delayTime(5).setBannerView(new BannerView.OnBindView() {
            @Override
            public List<ImageView> bindView() {
                List<ImageView> imageViewList = new ArrayList<>();
                for (int i = 0; i < bannerAdListVo.data.size(); i++) {
                    ImageView mImageView = new ImageView(mContext);
                    mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    Glide.with(mContext).load(bannerAdListVo.data.get(i).topimage1 == null ? bannerAdListVo.data.get(i).topimage : bannerAdListVo.data.get(i).topimage1).centerCrop().into(mImageView);
                    imageViewList.add(mImageView);
                }
                return imageViewList;
            }
        }).build(bannerAdListVo.data);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.mBannerView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final BannerView mBannerView;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = itemView.findViewById(R.id.banner);
        }

    }

}
