package com.code.mvvm.core.view.course.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;

/**
 * @author：tqzhang on 18/7/16 17:37
 */
public class CourseRecommendHolder extends AbsItemHolder<CourseDetailRemVideoVo.DataBean.CourseListBean, CourseRecommendHolder.ViewHolder> {

    public CourseRecommendHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_user_lesson;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CourseDetailRemVideoVo.DataBean.CourseListBean entity) {
        Glide.with(mContext).load(entity.thumb_url).into(holder.lessonPic);
        holder.lessonTitle.setText(entity.title);
        if (TextUtils.equals("1", entity.is_free)) {
            if (TextUtils.equals("1", entity.buy_type)) {
                holder.lessonPrice.setText("￥" + entity.video_price);
            } else if (TextUtils.equals("2", entity.buy_type)) {
                holder.lessonPrice.setText("￥" + entity.course_sale_price);
            }
        } else {
            holder.lessonPrice.setText("免费");
        }
        holder.lessonHits.setText(entity.hits + " 人看过");

    }

    static class ViewHolder extends AbsHolder {

        private ImageView lessonPic;

        private TextView lessonTitle, lessonPrice, lessonHits;

        public ViewHolder(View itemView) {
            super(itemView);
            lessonPic = getViewById(R.id.iv_lesson_img);
            lessonTitle = getViewById(R.id.tv_lesson_title);
            lessonPrice = getViewById(R.id.tv_lesson_price);
            lessonHits = getViewById(R.id.tv_lesson_hits);
        }
    }
}
