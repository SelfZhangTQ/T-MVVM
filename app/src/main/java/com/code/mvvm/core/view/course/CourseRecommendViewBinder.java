package com.code.mvvm.core.view.course;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.LessonDetailRemVideoVo;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/7/16 17:37
 */
public class CourseRecommendViewBinder extends AbsItemView<LessonDetailRemVideoVo.DataBean.CourseListBean, CourseRecommendViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_user_lesson, parent, false));

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LessonDetailRemVideoVo.DataBean.CourseListBean entity) {
        Glide.with(App.Instance()).load(entity.getThumb_url()).into(holder.iv_lesson_img);
        holder.tv_lesson_title.setText(entity.getTitle());
        float mPrice = 0;
        if (TextUtils.equals("1", entity.getIs_free())) {
            if (TextUtils.equals("1", entity.getBuy_type())) {
                holder.tv_lesson_price.setText("￥" + entity.getVideo_price());
            } else if (TextUtils.equals("2", entity.getBuy_type())) {
                holder.tv_lesson_price.setText("￥" + entity.getCourse_sale_price());
            }
        } else {
            holder.tv_lesson_price.setText("免费");
        }
//        //判断付费标签的展示
//        FufeiTagController.setFufeiTag(holder.tuanggou_tag_ly, holder.tv_shengyu_time,
//                holder.fufei_tag, holder.yifu_tag, entity.groupbuy, entity.getIs_free(), entity.buy_status);
        holder.tv_lesson_hits.setText(entity.getHits() + " 人看过");

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_lesson_img;

        public ImageView fufei_tag, yifu_tag;

        public TextView tv_lesson_title, tv_lesson_price, tv_lesson_hits;

        public LinearLayout tuanggou_tag_ly;

        public TextView tv_shengyu_time;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_lesson_img = (ImageView) itemView
                    .findViewById(R.id.iv_lesson_img);
            tv_lesson_title = (TextView) itemView.findViewById(R.id.tv_lesson_title);
            fufei_tag = (ImageView) itemView.findViewById(R.id.fufei_tag);
            yifu_tag = (ImageView) itemView.findViewById(R.id.yifu_tag);
            tv_lesson_price = (TextView) itemView.findViewById(R.id.tv_lesson_price);
            tv_lesson_hits = (TextView) itemView.findViewById(R.id.tv_lesson_hits);
            tv_shengyu_time = (TextView) itemView.findViewById(R.id.tv_shengyu_time);
            tuanggou_tag_ly = (LinearLayout) itemView.findViewById(R.id.tuanggou_tag_ly);

        }
    }
}
