package com.code.mvvm.core.view.dynamic.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.util.ViewUtils;
import com.code.mvvm.widget.CustomHeightImageView;

/**
 * @author：tqzhang on 18/7/4 15:35
 */
public class DynamicFollowHolder extends AbsItemHolder<DynamicInfoVo, DynamicFollowHolder.ViewHolder> {
    private int contentWidth;

    public DynamicFollowHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(App.instance()) - DisplayUtil.dp2px(App.instance(), 95);

    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_dynamic_followdraw, parent, false));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_followdraw;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext)
                .load(item.userinfo.avatar)
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
        holder.lookNum.setText(new StringBuilder(item.lesson_info.hits).append("人看过").toString());
        holder.dynamicTitle.setText(item.lesson_info.title);
        int commonwidth = (contentWidth - DisplayUtil.dp2px(App.instance(), 14 * 2)) * 2 / 3;
        double dv = 268.0 / 204;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(commonwidth, (int) (dv * commonwidth));
        holder.img_content_layout.setLayoutParams(params);
        if (item.lesson_info.imgs != null) {
            Glide.with(mContext)
                    .load(item.lesson_info.imgs.l.url)
                    .placeholder(R.color.black_e8e8e8)
                    .into(holder.ImagePic);
        } else {
            holder.ImagePic.setBackgroundResource(R.drawable.collect_morentupian);
        }
    }

    public static class ViewHolder extends AbsHolder {
        private CustomHeightImageView ImagePic;
        private View img_content_layout;
        private TextView tvUserName, userType, dynamicTitle, lookNum;
        private ImageView ivUserPic;
        private LinearLayout mUserTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ImagePic = getViewById(R.id.img_drawing);
            img_content_layout = getViewById(R.id.img_ly);
            ivUserPic = getViewById(R.id.iv_user_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }

}
