package com.code.mvvm.core.view.home.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.view.course.VideoDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseHolder;

/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class HomeVideoItemView extends AbsViewHolder<CourseInfoVo, HomeVideoItemView.ViewHolder> {

    private LinearLayout.LayoutParams params;

    public HomeVideoItemView(Context context) {
        super(context);
        int commonWidth = (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - DisplayUtil.dp2px(mContext, 20)) / 2);
        params = new LinearLayout.LayoutParams(
                commonWidth, (int) (0.56 * commonWidth));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_remmend_course;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CourseInfoVo courseListBean) {
        holder.mVideoLayout.setLayoutParams(params);
        holder.mVideoImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(courseListBean.thumb_url).placeholder(R.color.black_e8e8e8).into(holder.mVideoImage);
        Glide.with(mContext).load(courseListBean.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.mUserIcon);
        holder.mVideoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoDetailsActivity.class);
                intent.putExtra("course_id", courseListBean.courseid);
                mContext.startActivity(intent);
            }
        });
        holder.mUserName.setText(courseListBean.userinfo.sname);
        holder.mVideoTitle.setText(courseListBean.title);
        holder.mLookNum.setText(new StringBuilder(courseListBean.hits).append("人看过"));
    }


    public static class ViewHolder extends BaseHolder {
        private ImageView mVideoImage, mUserIcon;
        private TextView mLookNum, mVideoTitle, mUserName;
        private RelativeLayout mVideoLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoImage = getViewById(R.id.iv_video_image);
            mUserIcon = getViewById(R.id.iv_user_icon);
            mLookNum = getViewById(R.id.tv_look_num);
            mVideoTitle = getViewById(R.id.tv_video_title);
            mUserName = getViewById(R.id.tv_user_name);
            mVideoLayout = getViewById(R.id.rl_video_layout);

        }
    }

}
