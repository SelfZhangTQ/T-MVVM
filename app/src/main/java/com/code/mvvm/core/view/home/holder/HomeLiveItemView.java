package com.code.mvvm.core.view.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;

/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class HomeLiveItemView extends AbsItemHolder<LiveRecommendVo, HomeLiveItemView.ViewHolder> {

    private int commonWidth;

    public HomeLiveItemView(Context context) {
        super(context);
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - DisplayUtil.dp2px(mContext, 20)) / 2);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_live_view;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LiveRecommendVo liveRecommendVo) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                commonWidth, (int) (0.56 * commonWidth));
        holder.mLiveLayout.setLayoutParams(params);
        holder.mLiveImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(liveRecommendVo.live_thumb_url).into(holder.mLiveImage);
        Glide.with(mContext).load(liveRecommendVo.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.mUserIcon);

        if (liveRecommendVo.live_status == 1) {
            holder.mLiveState.setBackgroundResource(R.drawable.preview_state_icon);
            holder.mLookNum.setText(new StringBuilder(liveRecommendVo.live_sign_count).append("人已报名"));
        }
        if (liveRecommendVo.live_status == 2) {
            holder.mLiveState.setBackgroundResource(R.drawable.living_state_icon);
            holder.mLookNum.setText(new StringBuilder(liveRecommendVo.hits).append("人在围观"));
        }
        if (liveRecommendVo.live_status == 3) {
            holder.mLiveState.setBackgroundResource(R.drawable.playback_state_icon);
            holder.mLookNum.setText(new StringBuilder(liveRecommendVo.hits).append("人看过"));
        }
        holder.mLiveTitle.setText(liveRecommendVo.live_title);
        holder.mUserName.setText(liveRecommendVo.userinfo.sname);
    }


    public static class ViewHolder extends AbsHolder {

        private ImageView mLiveState;
        private ImageView mUserIcon;
        private ImageView mLiveImage;
        private TextView mLookNum;
        private TextView mLiveTitle;
        private TextView mUserName;
        private RelativeLayout mLiveLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLiveState = getViewById(R.id.iv_live_state);
            mLiveImage = getViewById(R.id.iv_live_image);
            mUserIcon = getViewById(R.id.iv_user_icon);
            mLookNum = getViewById(R.id.tv_look_num);
            mLiveTitle = getViewById(R.id.tv_live_title);
            mUserName = getViewById(R.id.tv_user_name);
            mLiveLayout = getViewById(R.id.rl_live_layout);
        }
    }

}
