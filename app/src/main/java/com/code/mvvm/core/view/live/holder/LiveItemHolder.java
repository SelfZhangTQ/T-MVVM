package com.code.mvvm.core.view.live.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.view.live.LiveDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;

/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class LiveItemHolder extends AbsItemHolder<LiveRecommendVo, LiveItemHolder.ViewHolder> {
    private int commonwidth;

    public LiveItemHolder(Context activity) {
        super(activity);
        commonwidth = (int) ((float) DisplayUtil.getScreenWidth(mContext)
                - 2 * mContext.getResources()
                .getDimensionPixelSize(
                        R.dimen.space_12));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_live_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final LiveRecommendVo item) {
        double dv = 0.5625;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonwidth));
        holder.livePic.setLayoutParams(params);
        if (item.live_thumb_url != null
                && !TextUtils.isEmpty(item.live_thumb_url)) {
            Glide.with(mContext).load(item.live_thumb_url)
                    .placeholder(R.color.black_e8e8e8).centerCrop()
                    .into(holder.livePic);
        }

        if (item.live_status == 1) {
            holder.liveStateBg.setBackgroundResource(R.drawable.preview_state_icon);
            holder.liveLookNum.setText(new StringBuilder(item.live_sign_count).append("人已报名"));
        }
        if (item.live_status == 2) {
            holder.liveStateBg.setBackgroundResource(R.drawable.living_state_icon);
            holder.liveStateDes.setText("正在直播，快去围观");
            holder.liveLookNum.setText(new StringBuilder(item.hits).append("人在围观"));
            holder.toLiveDetail.setBackgroundResource(R.drawable.fillet_shape_yellow);
            holder.toLiveDetail.setText("进入直播");
        }
        if (item.live_status == 3) {
            holder.liveStateBg.setBackgroundResource(R.drawable.playback_state_icon);
            holder.liveStateDes.setText("已结束，去看看回放");
            holder.liveLookNum.setText(new StringBuilder(item.hits).append("人已看过"));
            holder.toLiveDetail.setBackgroundResource(R.drawable.fillet_shape_yellow);
            holder.toLiveDetail.setText("看回放");
        }
        holder.liveTitle.setText(item.live_title);
        Glide.with(mContext).load(item.userinfo.avatar)
                .placeholder(R.color.black_e8e8e8).transform(new GlideCircleTransform(mContext))
                .into(holder.liveTeacherIcon);
        holder.liveTeacherName.setText(item.userinfo.sname);
        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LiveDetailsActivity.class);
                intent.putExtra("liveId", item.liveid);
                mContext.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends AbsHolder {

        private ImageView livePic;
        private ImageView liveStateBg;
        private ImageView liveTeacherIcon;

        private TextView liveTitle;
        private TextView liveTeacherName;
        private TextView toLiveDetail;
        private TextView liveStateDes;
        private TextView liveLookNum;

        public ViewHolder(View itemView) {
            super(itemView);
            livePic = getViewById(R.id.iv_live_pic);
            liveStateBg = getViewById(R.id.iv_live_state);
            liveTeacherIcon = getViewById(R.id.iv_live_teacher_icon);
            liveTitle = getViewById(R.id.tv_live_title);
            liveTeacherName = getViewById(R.id.tv_live_teacher_name);
            toLiveDetail = getViewById(R.id.tv_live_detail);
            liveStateDes = getViewById(R.id.tv_live_state);
            liveLookNum = getViewById(R.id.tv_live_look_num);
        }
    }

}
