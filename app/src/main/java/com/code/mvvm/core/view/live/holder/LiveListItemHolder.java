package com.code.mvvm.core.view.live.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.widget.CropImageView;

/**
 * @author：tqzhang  on 18/6/19 15:00
 */
public class LiveListItemHolder extends AbsItemHolder<LiveRecommendVo, LiveListItemHolder.ViewHolder> {

    public LiveListItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_live_list2;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LiveRecommendVo entity) {

        Glide.with(mContext).load(entity.live_thumb_url).centerCrop()
                .placeholder(R.color.black_e8e8e8)
                .into(holder.livePic);
        holder.liveTitle.setText(entity.live_title);
        holder.liveHits.setText(new StringBuilder(entity.hits).append(" 人看过"));
        Glide.with(mContext).load(entity.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.liveUserIcon);
        holder.liveUserName.setText(entity.userinfo.sname);
        if (entity.live_status == 1) {
            holder.liveStateBg.setBackgroundResource(R.drawable.preview_state_icon);
        }
        if (entity.live_status == 2) {
            holder.liveStateBg.setBackgroundResource(R.drawable.living_state_icon);
        }
        if (entity.live_status == 3) {
            holder.liveStateBg.setBackgroundResource(R.drawable.playback_state_icon);
        }
    }


    static class ViewHolder extends AbsHolder {

        private CropImageView livePic;

        private ImageView liveUserIcon, liveStateBg;

        private TextView liveTitle, liveHits, liveUserName;

        public ViewHolder(View itemView) {
            super(itemView);
            livePic = getViewById(R.id.iv_live_pic);
            liveUserIcon = getViewById(R.id.iv_live_user_icon);
            liveStateBg = getViewById(R.id.iv_live_state);
            liveTitle = getViewById(R.id.tv_live_title);
            liveHits = getViewById(R.id.tv_live_hits);
            liveUserName = getViewById(R.id.tv_live_user_name);

        }
    }

}
