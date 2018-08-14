package com.code.mvvm.core.view.live.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.widget.TopCropImageView;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class LiveListItemView extends AbsItemView<LiveRecommendVo, LiveListItemView.ViewHolder> {
    private Context mContext;

    public LiveListItemView(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_live_list2, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LiveRecommendVo entity) {

        Glide.with(mContext).load(entity.live_thumb_url).centerCrop()
                .placeholder(R.color.white)
                .into(holder.iv_live_img);
        holder.tv_live_title.setText(entity.live_title);
        holder.tv_live_hits.setText(entity.hits + " 人看过");
        Glide.with(mContext).load(entity.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.iv_live_usericon);

        holder.tv_live_username.setText(entity.userinfo.sname);
        if (entity.live_status == 1) {
            holder.zhibo_state.setBackgroundResource(R.drawable.biaoqian_yugao);
        }
        if (entity.live_status == 2) {
            holder.zhibo_state.setBackgroundResource(R.drawable.biaoqian_zhibo);
        }
        if (entity.live_status == 3) {
            holder.zhibo_state.setBackgroundResource(R.drawable.biaoqian_huifang);
        }
    }


    static class ViewHolder extends BaseViewHolder {


        public TopCropImageView iv_live_img;

        public ImageView iv_live_usericon, zhibo_state, fufei_tag, yifu_tag;

        public TextView tv_live_title, tv_live_hits, tv_live_username;

        public LinearLayout tuangou;

        public TextView tv_shengyu_time;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_live_img = (TopCropImageView) itemView
                    .findViewById(R.id.iv_live_img);
            iv_live_usericon = (ImageView) itemView.findViewById(R.id.iv_live_usericon);
            fufei_tag = (ImageView) itemView.findViewById(R.id.fufei_tag);
            yifu_tag = (ImageView) itemView.findViewById(R.id.yifu_tag);
            zhibo_state = (ImageView) itemView.findViewById(R.id.zhibo_state);
            tv_live_title = (TextView) itemView.findViewById(R.id.tv_live_title);
            tv_live_hits = (TextView) itemView.findViewById(R.id.tv_live_hits);
            tv_live_username = (TextView) itemView.findViewById(R.id.tv_live_username);

            tuangou = (LinearLayout) itemView.findViewById(R.id.tuanggou_tag_ly);
            tv_shengyu_time = (TextView) itemView.findViewById(R.id.tv_shengyu_time);

        }
    }

}
