package com.code.mvvm.core.view.live.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.view.live.LiveDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class LiveItemView extends AbsItemView<LiveRecommendVo, LiveItemView.ViewHolder> {
    int commonwidth;

    private Context mContext;

    public LiveItemView(Context activity) {
        this.mContext = activity;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonwidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance())
                - 2 * App.Instance().getResources()
                .getDimensionPixelSize(
                        R.dimen.concise_three_layout_margin));
        return new ViewHolder(inflater.inflate(R.layout.item_live_list, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final LiveRecommendVo item) {

        double dv = 0.5625;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonwidth));
        holder.zhibo_img.setLayoutParams(params);
        if (item.live_thumb_url != null
                && !TextUtils.isEmpty(item.live_thumb_url)) {
            Glide.with(mContext).load(item.live_thumb_url)
                    .placeholder(R.color.white).centerCrop()
                    .into(holder.zhibo_img);
        }


        if (item.live_status == 1) {
            holder.zhibo_state.setBackgroundResource(R.drawable.bq_yugao);
//            holder.zhibo_time.setText(com.app.meiyuan.util.TimeUtils.getFormatTime(Long.valueOf(item.getStart_time()) * 1000, "MM月dd日 HH:mm") + "开播");
            holder.zhibo_baoming_num.setText(item.live_sign_count + "人已报名");
            if ("0".equals(item.live_sign_status)) {
                holder.go_zhibo_detail.setText("立即报名");
            } else {
                holder.go_zhibo_detail.setBackgroundResource(R.drawable.yuanjiao_shape_white);
                holder.go_zhibo_detail.setText("已报名");
            }

            //判断付费标签的展示
        }
        if (item.live_status == 2) {
            holder.zhibo_state.setBackgroundResource(R.drawable.bq_zhibo);
            holder.zhibo_time.setText("正在直播，快去围观");
            holder.zhibo_baoming_num.setText(item.hits + "人在围观");
            holder.go_zhibo_detail.setBackgroundResource(R.drawable.yuanjiao_shape_yellow);
            holder.go_zhibo_detail.setText("进入直播");

            //判断付费标签的展示
        }
        if (item.live_status == 3) {
            holder.zhibo_state.setBackgroundResource(R.drawable.bq_huifang);
            holder.zhibo_time.setText("已结束，去看看回放");
            holder.zhibo_baoming_num.setText(item.hits + "人已看过");
            holder.go_zhibo_detail.setBackgroundResource(R.drawable.yuanjiao_shape_yellow);
            holder.go_zhibo_detail.setText("看回放");

            //判断付费标签的展示
        }
        holder.zhibo_title.setText(item.live_title);
        Glide.with(mContext).load(item.userinfo.avatar)
                .placeholder(R.color.white).transform(new GlideCircleTransform(mContext))
                .into(holder.img_icon);
        holder.zhibo_teacher_name.setText(item.userinfo.sname);

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LiveDetailsActivity.class);
                intent.putExtra("liveId", item.liveid);
                mContext.startActivity(intent);
            }
        });
    }


    static class ViewHolder extends BaseViewHolder {


        public ImageView zhibo_img, zhibo_state, img_icon, fufei_tag, yifu_tag;

        public TextView zhibo_title, zhibo_teacher_name, go_zhibo_detail, zhibo_time, zhibo_baoming_num;

        private LinearLayout mRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            zhibo_img = getView(R.id.zhibo_img);
            fufei_tag = getView(R.id.fufei_tag);
            yifu_tag = getView(R.id.yifu_tag);
            zhibo_state = getView(R.id.zhibo_state);
            img_icon = getView(R.id.img_icon);
            zhibo_title = getView(R.id.zhibo_title);
            zhibo_teacher_name = getView(R.id.zhibo_teacher_name);
            go_zhibo_detail = getView(R.id.go_zhibo_detail);
            zhibo_time = getView(R.id.zhibo_time);
            zhibo_baoming_num = getView(R.id.zhibo_baoming_num);
            mRootView = getView(R.id.ll_root_view);


        }
    }

}
