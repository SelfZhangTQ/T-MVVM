package com.code.mvvm.core.view.dynamic.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public class DynamicArticleHolder extends AbsItemHolder<DynamicInfoVo, DynamicArticleHolder.ViewHolder> {
    private int contentWidth;


    public DynamicArticleHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dp2px(mContext, 95);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_article;
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
        holder.look_num.setText(new StringBuilder(item.lecture_info.hits).append("人看过"));
        holder.dynamic_title1.setText(item.lecture_info.title);
        holder.dynamic_title2.setText(item.lecture_info.title);
        holder.dynamic_title3.setText(item.lecture_info.title);
        int commonWidth = contentWidth - DisplayUtil.dp2px(mContext, 28);
        int type = Integer.valueOf(item.lecture_info.thumbtype);
        if (type == 1) {
            holder.article_type1.setVisibility(View.VISIBLE);
            holder.article_type2.setVisibility(View.GONE);
            holder.article_type3.setVisibility(View.GONE);
            double dv = 0.5;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(commonWidth, (int) (dv * commonWidth));
            holder.type1_dynamic_img.setLayoutParams(params);
            if (item.lecture_info.img != null
                    && !TextUtils.isEmpty(item.lecture_info.img.get(0).img)) {
                Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                        .placeholder(R.color.black_e8e8e8)
                        .into(holder.type1_dynamic_img);
            }


            if ("2".equals(item.lecture_info.content_type)) {
                holder.content_type1.setVisibility(View.VISIBLE);
                holder.content_type1.setBackgroundResource(R.drawable.tag_video_icon);
            } else if ("3".equals(item.lecture_info.content_type)) {
                holder.content_type1.setVisibility(View.VISIBLE);
                holder.content_type1.setBackgroundResource(R.drawable.tag_audio_icon);
            } else {
                holder.content_type1.setVisibility(View.GONE);
            }
        } else if (type == 2) {
            holder.article_type1.setVisibility(View.GONE);
            holder.article_type2.setVisibility(View.VISIBLE);
            holder.article_type3.setVisibility(View.GONE);
            if (item.lecture_info.img != null
                    && !TextUtils.isEmpty(item.lecture_info.img.get(0).img)) {
                Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                        .placeholder(R.color.black_e8e8e8)
                        .into(holder.type2_dynamic_img);
            }


            if ("2".equals(item.lecture_info.content_type)) {
                holder.content_type2.setVisibility(View.VISIBLE);
                holder.content_type2.setBackgroundResource(R.drawable.tag_video_icon);
            } else if ("3".equals(item.lecture_info.content_type)) {
                holder.content_type2.setVisibility(View.VISIBLE);
                holder.content_type2.setBackgroundResource(R.drawable.tag_audio_icon);
            } else {
                holder.content_type2.setVisibility(View.GONE);
            }
        } else {
            holder.article_type1.setVisibility(View.GONE);
            holder.article_type2.setVisibility(View.GONE);
            holder.article_type3.setVisibility(View.VISIBLE);

            double dv2 = 1;
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    (commonWidth - 2 * mContext
                            .getResources().getDimensionPixelSize(
                                    R.dimen.concise_three_img_margin))
                            / 3,
                    (int) (dv2
                            * (commonWidth - 2 * mContext.getResources()
                            .getDimensionPixelSize(
                                    R.dimen.concise_three_img_margin))
                            / 3));
            holder.type3_dynamic_img.setLayoutParams(params2);
            holder.type3_dynamic_img2.setLayoutParams(params2);
            holder.type3_dynamic_img3.setLayoutParams(params2);

            Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                    .placeholder(R.color.black_e8e8e8)
                    .override(params2.width, params2.height)
                    .into(holder.type3_dynamic_img);
            if (item.lecture_info.img.size() > 1) {
                Glide.with(mContext).load(item.lecture_info.img.get(1).img)
                        .placeholder(R.color.black_e8e8e8)
                        .override(params2.width, params2.height)
                        .into(holder.type3_dynamic_img2);
            }
            if (item.lecture_info.img.size() > 2) {
                Glide.with(mContext).load(item.lecture_info.img.get(2).img)
                        .placeholder(R.color.black_e8e8e8)
                        .override(params2.width, params2.height)
                        .into(holder.type3_dynamic_img3);
            }
        }

    }


    public static class ViewHolder extends AbsHolder {

        private ImageView type1_dynamic_img;
        private ImageView type2_dynamic_img;
        private ImageView type3_dynamic_img, type3_dynamic_img2, type3_dynamic_img3;
        private ImageView content_type1, content_type2;
        private TextView tvUserName, look_num;
        private ImageView ivUserPic;
        private LinearLayout mUserTag;
        private LinearLayout article_type1, article_type2, article_type3;
        private TextView dynamic_title1, dynamic_title2, dynamic_title3;

        public ViewHolder(View itemView) {
            super(itemView);
            type1_dynamic_img = getViewById(R.id.type1_dynamic_img);
            type2_dynamic_img = getViewById(R.id.type2_dynamic_img);
            type3_dynamic_img = getViewById(R.id.type3_dynamic_img);
            type3_dynamic_img2 = getViewById(R.id.type3_dynamic_img2);
            type3_dynamic_img3 = getViewById(R.id.type3_dynamic_img3);
            content_type1 = getViewById(R.id.content_type1);
            content_type2 = getViewById(R.id.content_type2);

            ivUserPic = getViewById(R.id.iv_user_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            look_num = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);

            dynamic_title1 = getViewById(R.id.dynamic_title1);
            dynamic_title2 = getViewById(R.id.dynamic_title2);
            dynamic_title3 = getViewById(R.id.dynamic_title3);

            article_type1 = getViewById(R.id.article_type1);
            article_type2 = getViewById(R.id.article_type2);
            article_type3 = getViewById(R.id.article_type3);
        }
    }

}
