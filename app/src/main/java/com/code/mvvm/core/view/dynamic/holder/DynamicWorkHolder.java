package com.code.mvvm.core.view.dynamic.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.util.ViewUtils;

/**
 * @author：tqzhang on 18/7/4 15:35
 */
public class DynamicWorkHolder extends AbsItemHolder<DynamicInfoVo, DynamicWorkHolder.ViewHolder> {
    private int contentWidth;

    public DynamicWorkHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dp2px(mContext, 15 + 40 + 10 + 30);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_work;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext).load(item.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.ivUserPic);
        holder.mUserTag.removeAllViews();
        if (!TextUtils.isEmpty(item.userinfo.province) && !TextUtils.equals("false", item.userinfo.province)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext, item.userinfo.province));
        }
        if (!TextUtils.isEmpty(item.userinfo.profession)
                && !TextUtils.equals("false", item.userinfo.profession)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext, item.userinfo.profession));
        }
        holder.userType.setText(item.title);
        holder.lookNum.setText(new StringBuilder(item.tweet_info.hits).append("人看过"));
        holder.dynamicTitle.setText(item.tweet_info.content);
//
        holder.dynamicPic.setVisibility(View.VISIBLE);
        int commonwidth = (contentWidth - DisplayUtil.dp2px(mContext, 14 * 2)) * 2 / 3;
        float dv = (float) item.tweet_info.imgs_list.get(0).l.h / (float) item.tweet_info.imgs_list.get(0).l.w;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(commonwidth, (int) (dv * commonwidth));
        holder.dynamicPic.setLayoutParams(params);

        Glide.with(mContext).load(item.tweet_info.imgs_list.get(0).l.url)
                .placeholder(R.color.black_e8e8e8).into(holder.dynamicPic);
    }


    public static class ViewHolder extends AbsHolder {

        private TextView tvUserName, userType, dynamicTitle, lookNum;
        private ImageView ivUserPic, dynamicPic;
        private LinearLayout mUserTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ivUserPic = getViewById(R.id.iv_user_pic);
            dynamicPic = getViewById(R.id.iv_dynamic_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }

}
