package com.code.mvvm.core.view.course.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;

/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseItemHolder extends AbsItemHolder<CourseInfoVo, CourseItemHolder.ViewHolder> {
    private int commonWidth;

    public CourseItemHolder(Context context) {
        super(context);
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(mContext)
                / 2);
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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                commonWidth, (int) (0.56 * commonWidth));
        holder.mVideoImage.setLayoutParams(params);
        holder.mVideoImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(courseListBean.thumb_url).placeholder(R.color.black_e8e8e8).into(holder.mVideoImage);
        Glide.with(mContext).load(courseListBean.userinfo.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
        holder.mUserName.setText(courseListBean.userinfo.sname);
        holder.mVideoTitle.setText(courseListBean.title);
        holder.mLookNum.setText(new StringBuilder(String.valueOf(courseListBean.hits)).append("人看过"));
    }


    static class ViewHolder extends AbsHolder {

        private ImageView mVideoImage, mUserIcon;
        private TextView mLookNum, mVideoTitle, mUserName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoImage = getViewById(R.id.iv_video_image);
            mUserIcon = getViewById(R.id.iv_user_icon);
            mLookNum = getViewById(R.id.tv_look_num);
            mVideoTitle = getViewById(R.id.tv_video_title);
            mUserName = getViewById(R.id.tv_user_name);
        }
    }

}
