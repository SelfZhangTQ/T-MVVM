package com.code.mvvm.core.view.dynamic.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/7/4 15:35
 */
public class dynamicBinderAritcle extends AbsItemView<DynamicInfoVo, dynamicBinderAritcle.ViewHolder> {
    int contentWidth;

    private Context mContext;

    public dynamicBinderAritcle(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        contentWidth = DisplayUtil.getScreenWidth(App.Instance()) - DisplayUtil.dp2px(App.Instance(), 15 + 40 + 10 + 30);

        return new ViewHolder(inflater.inflate(R.layout.item_dynamic_article, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext).load(item.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.ivUserPic);
        // 展示用户信息tag
        holder.mUserTag.removeAllViews();
        if (!TextUtils.isEmpty(item.userinfo.province) && !TextUtils.equals("false", item.userinfo.province)) {
            View view = initTag(item.userinfo.province);
            holder.mUserTag.addView(view);
        }
        if (!TextUtils.isEmpty(item.userinfo.profession)
                && !TextUtils.equals("false", item.userinfo.profession)) {
            View view = initTag(item.userinfo.profession);
            holder.mUserTag.addView(view);
        }
        holder.look_num.setText(item.lecture_info.hits + "人看过");
        holder.dynamic_title1.setText(item.lecture_info.title);
        holder.dynamic_title2.setText(item.lecture_info.title);
        holder.dynamic_title3.setText(item.lecture_info.title);
//        holder.publich_time.setText(TimeUtils.getStandardDate(item.ctime));


        //文章图片展示
        int commonWidth = contentWidth - DisplayUtil.dp2px(App.Instance(), 14 * 2);
        int type = Integer.valueOf(item.lecture_info.thumbtype);
        if (type == 1) {
            // 一张大图
            holder.article_type1.setVisibility(View.VISIBLE);
            holder.article_type2.setVisibility(View.GONE);
            holder.article_type3.setVisibility(View.GONE);
            // 图片适配
            double dv = 0.5;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(commonWidth, (int) (dv * commonWidth));
            holder.type1_dynamic_img.setLayoutParams(params);

            if (item.lecture_info.img != null
                    && !TextUtils.isEmpty(item.lecture_info.img.get(0).img)) {
                Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                        .placeholder(R.color.black_333333)
                        .into(holder.type1_dynamic_img);
            }


            //2视频，3音频
            if ("2".equals(item.lecture_info.content_type)) {
                holder.content_type1.setVisibility(View.VISIBLE);
                holder.content_type1.setBackgroundResource(R.drawable.jingjiang_shipin_da);
            } else if ("3".equals(item.lecture_info.content_type)) {
                holder.content_type1.setVisibility(View.VISIBLE);
                holder.content_type1.setBackgroundResource(R.drawable.jingjiang_yinpin_da);
            } else {
                holder.content_type1.setVisibility(View.GONE);
            }
        } else if (type == 2) {
            // 左图
            holder.article_type1.setVisibility(View.GONE);
            holder.article_type2.setVisibility(View.VISIBLE);
            holder.article_type3.setVisibility(View.GONE);
            if (item.lecture_info.img != null
                    && !TextUtils.isEmpty(item.lecture_info.img.get(0).img)) {
                Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                        .placeholder(R.color.black_333333)
                        .into(holder.type2_dynamic_img);
            }


            //2视频，3音频
            if ("2".equals(item.lecture_info.content_type)) {
                holder.content_type2.setVisibility(View.VISIBLE);
                holder.content_type2.setBackgroundResource(R.drawable.jingjiang_shipin_xiao);
            } else if ("3".equals(item.lecture_info.content_type)) {
                holder.content_type2.setVisibility(View.VISIBLE);
                holder.content_type2.setBackgroundResource(R.drawable.jingjiang_yinpin_xiao);
            } else {
                holder.content_type2.setVisibility(View.GONE);
            }
        } else {
            //三图
            holder.article_type1.setVisibility(View.GONE);
            holder.article_type2.setVisibility(View.GONE);
            holder.article_type3.setVisibility(View.VISIBLE);

            double dv2 = 1;
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    (commonWidth - 2 * App.Instance()
                            .getResources().getDimensionPixelSize(
                                    R.dimen.concise_three_img_margin))
                            / 3,
                    (int) (dv2
                            * (commonWidth - 2 * App.Instance().getResources()
                            .getDimensionPixelSize(
                                    R.dimen.concise_three_img_margin))
                            / 3));
            holder.type3_dynamic_img.setLayoutParams(params2);
            holder.type3_dynamic_img2.setLayoutParams(params2);
            holder.type3_dynamic_img3.setLayoutParams(params2);

            Glide.with(mContext).load(item.lecture_info.img.get(0).img)
                    .placeholder(R.color.black_333333)
                    .override(params2.width, params2.height)
                    .into(holder.type3_dynamic_img);
            if (item.lecture_info.img.size() > 1) {
                Glide.with(mContext).load(item.lecture_info.img.get(1).img)
                        .placeholder(R.color.black_333333)
                        .override(params2.width, params2.height)
                        .into(holder.type3_dynamic_img2);
            }
            if (item.lecture_info.img.size() > 2) {
                Glide.with(mContext).load(item.lecture_info.img.get(2).img)
                        .placeholder(R.color.black_333333)
                        .override(params2.width, params2.height)
                        .into(holder.type3_dynamic_img3);
            }
        }

    }


    public static class ViewHolder extends BaseViewHolder {

        public ImageView type1_dynamic_img;
        public ImageView type2_dynamic_img;
        public ImageView type3_dynamic_img, type3_dynamic_img2, type3_dynamic_img3;
        public ImageView content_type1, content_type2;
        public TextView tvUserName, user_action, publich_time, look_num;
        public ImageView ivUserPic;
        public LinearLayout mUserTag;
        public LinearLayout article_type1, article_type2, article_type3;
        public TextView dynamic_title1, dynamic_title2, dynamic_title3;

        public ViewHolder(View itemView) {
            super(itemView);
            type1_dynamic_img = (ImageView) itemView.findViewById(R.id.type1_dynamic_img);
            type2_dynamic_img = (ImageView) itemView.findViewById(R.id.type2_dynamic_img);
            type3_dynamic_img = (ImageView) itemView.findViewById(R.id.type3_dynamic_img);
            type3_dynamic_img2 = (ImageView) itemView.findViewById(R.id.type3_dynamic_img2);
            type3_dynamic_img3 = (ImageView) itemView.findViewById(R.id.type3_dynamic_img3);
            content_type1 = (ImageView) itemView.findViewById(R.id.content_type1);
            content_type2 = (ImageView) itemView.findViewById(R.id.content_type2);

            ivUserPic = getView(R.id.iv_user_pic);
            tvUserName = getView(R.id.tv_user_name);
            user_action = (TextView) itemView.findViewById(R.id.user_type);
            publich_time = (TextView) itemView.findViewById(R.id.publich_time);
            look_num = (TextView) itemView.findViewById(R.id.tv_look_num);
            mUserTag = getView(R.id.ll_user_tag);

            dynamic_title1 = (TextView) itemView.findViewById(R.id.dynamic_title1);
            dynamic_title2 = (TextView) itemView.findViewById(R.id.dynamic_title2);
            dynamic_title3 = (TextView) itemView.findViewById(R.id.dynamic_title3);

            article_type1 = (LinearLayout) itemView.findViewById(R.id.article_type1);
            article_type2 = (LinearLayout) itemView.findViewById(R.id.article_type2);
            article_type3 = (LinearLayout) itemView.findViewById(R.id.article_type3);
        }
    }

    private View initTag(String text) {
        View view = LayoutInflater.from(App.Instance()).inflate(R.layout.item_user_tag, null);
        TextView tv_tag = (TextView) view.findViewById(R.id.tv_tag);
        tv_tag.setText(text);
        return view;
    }

}
