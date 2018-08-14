package com.code.mvvm.core.view.activity.viewholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class ActivityListItemViewBinder extends AbsItemView<ActivityListVo.DataBean, ActivityListItemViewBinder.ViewHolder> {
    int commonwidth;

    private Context mContext;

    public ActivityListItemViewBinder(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonwidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance())
                - 2 * App.Instance().getResources()
                .getDimensionPixelSize(
                        R.dimen.concise_three_layout_margin));
        return new ViewHolder(inflater.inflate(R.layout.item_activity_list, parent, false));
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ActivityListVo.DataBean dataBean) {
        double dv = 0.42;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonwidth));
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

        ImageView imageView = holder.activity_list_imageview;
        RelativeLayout desBg = holder.item_rl;
        imageView.setLayoutParams(params);
        if (dataBean.img != null
                && !TextUtils.isEmpty(dataBean.img.img)) {
            Glide.with(App.Instance()).load(dataBean.img.img).centerCrop()
                    .placeholder(R.color.black_333333)
                    .into(imageView);
        }
        holder.activity_list_textview.setText(dataBean.title);

        int status = dataBean.executestatus;
        TextView content_type_textview = holder.content_type_textview;
        TextView state_textview = holder.state_textview;
        if (status == 0) {
            content_type_textview.setVisibility(View.VISIBLE);
            state_textview.setText("未开始");
            content_type_textview.setTextColor(R.color.black);
            desBg.setBackgroundResource(R.color.color_ffca00);
            content_type_textview.setText(dataBean.keywords);
        } else if (status == 1) {
            content_type_textview.setVisibility(View.VISIBLE);
            String str = String.format("进行中,剩余%s天", dataBean.resttime);
            state_textview.setText(str);
            content_type_textview.setTextColor(R.color.black);
            desBg.setBackgroundResource(R.color.color_ffca00);
            content_type_textview.setText(dataBean.keywords);

        } else if (status == 2) {
            content_type_textview.setVisibility(View.VISIBLE);
            state_textview.setText("已结束");
            desBg.setBackgroundResource(R.color.content_type_textview);
            content_type_textview.setText(Html.fromHtml("<font color='#ffffff'>" + dataBean.keywords + "</font>"));
        }


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView activity_list_imageview;
        TextView activity_list_textview;
        TextView content_type_textview;
        TextView state_textview;
        RelativeLayout item_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_list_imageview = itemView.findViewById(R.id.activity_list_imageview);
            activity_list_textview = itemView.findViewById(R.id.activity_list_textview);
            content_type_textview = itemView.findViewById(R.id.content_type_textview);
            state_textview = itemView.findViewById(R.id.state_textview);
            item_rl = itemView.findViewById(R.id.item_rl);
        }
    }

}
