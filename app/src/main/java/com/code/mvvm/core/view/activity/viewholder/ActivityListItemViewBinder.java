package com.code.mvvm.core.view.activity.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class ActivityListItemViewBinder extends AbsItemView<ActivityListVo.DataBean, ActivityListItemViewBinder.ViewHolder> {
    private int commonWidth=0;

    private Context mContext;

    public ActivityListItemViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
       if (commonWidth==0) {
           commonWidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance())
                   - 2 * App.Instance().getResources()
                   .getDimensionPixelSize(
                           R.dimen.concise_three_layout_margin));
       }
        return new ViewHolder(inflater.inflate(R.layout.item_activity_list, parent, false));
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ActivityListVo.DataBean dataBean) {
        double dv = 0.42;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonWidth));
        params.setMargins(
                App.Instance().getResources()
                        .getDimensionPixelSize(
                                R.dimen.concise_three_layout_margin),
                App.Instance().getResources()
                        .getDimensionPixelSize(
                                R.dimen.concise_img_margin_top),
                App.Instance().getResources()
                        .getDimensionPixelSize(
                                R.dimen.concise_three_layout_margin),
                App.Instance().getResources()
                        .getDimensionPixelSize(
                                R.dimen.concise_img_margin_bottom));

        RelativeLayout desBg = holder.rlItemLayout;
        holder.ivActivityPic.setLayoutParams(params);
        if (dataBean.img != null
                && !TextUtils.isEmpty(dataBean.img.img)) {
            Glide.with(mContext).load(dataBean.img.img).centerCrop()
                    .placeholder(R.color.black_333333)
                    .into(holder.ivActivityPic);
        }
        holder.tvActivityTitle.setText(dataBean.title);

        int status = dataBean.executestatus;
        if (status == 0) {
            holder.tvActivityType.setVisibility(View.VISIBLE);
            holder.tvActivityState.setText("未开始");
            holder.tvActivityType.setTextColor(R.color.black);
            desBg.setBackgroundResource(R.color.color_ffca00);
            holder.tvActivityType.setText(dataBean.keywords);
        } else if (status == 1) {
            holder.tvActivityType.setVisibility(View.VISIBLE);
            String str = String.format("进行中,剩余%s天", dataBean.resttime);
            holder.tvActivityState.setText(str);
            holder.tvActivityType.setTextColor(R.color.black);
            desBg.setBackgroundResource(R.color.color_ffca00);
            holder.tvActivityType.setText(dataBean.keywords);

        } else if (status == 2) {
            holder.tvActivityType.setVisibility(View.VISIBLE);
            holder.tvActivityState.setText("已结束");
            desBg.setBackgroundResource(R.color.content_type_textview);
            holder.tvActivityType.setText(Html.fromHtml("<font color='#ffffff'>" + dataBean.keywords + "</font>"));
        }


    }


    static class ViewHolder extends BaseViewHolder {
        ImageView ivActivityPic;
        TextView tvActivityTitle;
        TextView tvActivityType;
        TextView tvActivityState;
        RelativeLayout rlItemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ivActivityPic = getView(R.id.iv_activity_pic);
            tvActivityTitle = getView(R.id.tv_activity_title);
            tvActivityType = getView(R.id.tv_activity_type);
            tvActivityState = getView(R.id.tv_activity_state);
            rlItemLayout = getView(R.id.rl_item_layout);
        }
    }

}
