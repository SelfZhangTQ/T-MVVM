package com.code.mvvm.core.view.home.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.view.course.VideoDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class HomeCourseItemView extends AbsItemView<CourseInfoVo, HomeCourseItemView.ViewHolder> {
    private int commonWidth = 0;

    private Context mContext;

    private LinearLayout.LayoutParams params;

    public HomeCourseItemView(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        if (commonWidth == 0) {
            commonWidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                    - DisplayUtil.dp2px(App.Instance(), 20)) / 2);
        }
        if (params == null) {
            params = new LinearLayout.LayoutParams(
                    commonWidth, (int) (0.56 * commonWidth));
        }
        return new ViewHolder(inflater.inflate(R.layout.home_remmend_course, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CourseInfoVo courseListBean) {

        holder.mVideoLayout.setLayoutParams(params);
        holder.mVideoImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(courseListBean.thumb_url).placeholder(R.color.white).into(holder.mVideoImage);
        Glide.with(mContext).load(courseListBean.userinfo.avatar).transform(new GlideCircleTransform(App.Instance())).into(holder.mUserIcon);
        holder.mVideoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoDetailsActivity.class);
                intent.putExtra("LESSON_ID", courseListBean.courseid);
                mContext.startActivity(intent);
            }
        });
        holder.mUserName.setText(courseListBean.userinfo.sname);
        holder.mVideoTitle.setText(courseListBean.title);
        holder.mLookNum.setText(courseListBean.hits + "人看过");
    }


    public static class ViewHolder extends BaseViewHolder {
        private ImageView mVideoImage, mUserIcon;
        private TextView mLookNum, mVideoTitle, mUserName;
        private RelativeLayout mVideoLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoImage = itemView.findViewById(R.id.iv_video_image);
            mUserIcon = itemView.findViewById(R.id.iv_user_icon);
            mLookNum = itemView.findViewById(R.id.tv_look_num);
            mVideoTitle = itemView.findViewById(R.id.tv_video_title);
            mUserName = itemView.findViewById(R.id.tv_user_name);
            mVideoLayout = itemView.findViewById(R.id.rl_video_layout);

        }
    }

}
