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
 * @author：tqzhang  on 18/7/4 15:35
 */
public class DynamicCourseHolder extends AbsItemHolder<DynamicInfoVo, DynamicCourseHolder.ViewHolder> {
    private int contentWidth;

    public DynamicCourseHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dp2px(mContext, 95);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_lesson;
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
        holder.lookNum.setText(item.course_info.hits + "人看过");
        holder.dynamicTitle.setText(item.course_info.title);
        if (null != item.course_info.video_legth && !TextUtils.isEmpty(item.course_info.video_legth)) {
            int minute = (int) (Long.valueOf(item.course_info.video_legth) / 60);
            int second = (int) (Long.valueOf(item.course_info.video_legth) % 60);
            holder.video_time.setText(minute + "" + "分" + second + "" + "秒");
        }

        int commonwidth = contentWidth - DisplayUtil.dp2px(mContext, 28);
        double dv = 0.56;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.img_content_layout.getLayoutParams();
        params.height = (int) (dv * commonwidth);
        if (item.course_info.thumb_url != null) {
            Glide.with(mContext).load(item.course_info.thumb_url)
                    .placeholder(R.color.black_e8e8e8)
                    .into(holder.video_img);
        } else {
            holder.video_img.setBackgroundResource(R.drawable.collect_morentupian);
        }
    }


    public static class ViewHolder extends AbsHolder {

        private ImageView video_img;
        private TextView video_time;

        private TextView tvUserName, userType, dynamicTitle, lookNum;
        private ImageView ivUserPic;
        private LinearLayout mUserTag;

        private View img_content_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            video_img = getViewById(R.id.video_img);
            img_content_layout = getViewById(R.id.img_ly);
            video_time = getViewById(R.id.video_time);
            ivUserPic = getViewById(R.id.iv_user_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }
}
