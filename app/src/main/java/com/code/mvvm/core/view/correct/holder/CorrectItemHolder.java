package com.code.mvvm.core.view.correct.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.view.correct.WorkDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;
import com.code.mvvm.widget.CustomHeightRelativeLayout;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseHolder;

/**
 * @author：tqzhang  on 18/6/27 19:14
 */
public class CorrectItemHolder extends AbsViewHolder<WorksListVo.Works, CorrectItemHolder.ViewHolder> {
    private int commonWidth;

    public CorrectItemHolder(Context context) {
        super(context);
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - 30 * DisplayUtil.getDisplayDensity(mContext)) / 2);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.correct_item;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull CorrectItemHolder.ViewHolder holder, @NonNull final WorksListVo.Works data) {
        String mStatus = "0";
        if (mStatus.equals(data.correct.status)) {
            float dv = (float) data.correct.source_pic.img.l.h / (float) data.correct.source_pic.img.l.w;
            holder.mCHImageView.setHeight((int) (dv * commonWidth));
            Glide.with(mContext).load(data.correct.source_pic.img.l.url)
                    .placeholder(R.color.black_e8e8e8)
                    .transform(new GlideRoundTransform(mContext, 4))
                    .into(holder.mCHImageView);
        } else {
            if (data.correct.correct_pic.img != null) {
                float dv = (float) data.correct.correct_pic.img.l.h / (float) data.correct.correct_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonWidth));
                Glide.with(mContext).load(data.correct.correct_pic.img.l.url)
                        .placeholder(R.color.black_e8e8e8)
                        .transform(new GlideRoundTransform(mContext, 4)).into(holder.mCHImageView);
            } else {
                float dv = (float) data.correct.source_pic.img.l.h / (float) data.correct.source_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonWidth));
                Glide.with(mContext).load(data.correct.source_pic.img.l.url)
                        .placeholder(R.color.black_e8e8e8)
                        .transform(new GlideRoundTransform(mContext, 4)).into(holder.mCHImageView);
            }
        }

        holder.mTvDesc.setText(data.correct.content);
        holder.mUserName.setText(data.correct.teacher_info.sname);
        Glide.with(mContext).load(data.correct.teacher_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
        holder.mCHRootLayout.setOnClickListener(v -> WorkDetailsActivity.start(mContext, data.correct.correctid));
    }

    public class ViewHolder extends BaseHolder {
        private CustomHeightImageView mCHImageView;
        private TextView mTvDesc, mUserName;
        private ImageView mUserIcon;
        private CustomHeightRelativeLayout mCHRootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCHImageView = getViewById(R.id.iv_custom_image);
            mTvDesc = getViewById(R.id.tv_desc);
            mCHRootLayout = getViewById(R.id.custom_root_layout);
            mUserName = getViewById(R.id.tv_user_name);
            mUserIcon = getViewById(R.id.iv_user_icon);
        }

    }
}
