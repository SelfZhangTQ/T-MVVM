package com.code.mvvm.core.view.correct.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.view.correct.WorkDetailsActivity;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;
import com.code.mvvm.widget.CustomHeightRelativeLayout;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/27 19:14
 */
public class CorrectItemViewBinder extends AbsItemView<WorksListVo.Works, CorrectItemViewBinder.ViewHolder> {
    private int commonWidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
            - 30 * DisplayUtil.getDisplayDensity(App.Instance())) / 2);

    private Context mContext;

    public CorrectItemViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    CorrectItemViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new CorrectItemViewBinder.ViewHolder(inflater.inflate(R.layout.correct_item, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull CorrectItemViewBinder.ViewHolder holder, @NonNull final WorksListVo.Works data) {
        String mStatus = "0";
        if (mStatus.equals(data.correct.status)) {
            float dv = (float) data.correct.source_pic.img.l.h / (float) data.correct.source_pic.img.l.w;
            holder.mCHImageView.setHeight((int) (dv * commonWidth));
            Glide.with(mContext).load(data.correct.source_pic.img.l.url)
                    .placeholder(R.color.white)
                    .transform(new GlideRoundTransform(mContext, 4))
                    .into(holder.mCHImageView);
        } else {
            if (data.correct.correct_pic.img != null) {
                float dv = (float) data.correct.correct_pic.img.l.h / (float) data.correct.correct_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonWidth));
                if (!TextUtils.isEmpty(data.correct.correct_pic.img.l.url)) {
                    Glide.with(mContext).load(data.correct.correct_pic.img.l.url)
                            .placeholder(R.color.white)
                            .transform(new GlideRoundTransform(App.Instance(), 4)).into(holder.mCHImageView);
                }
            } else {
                float dv = (float) data.correct.source_pic.img.l.h / (float) data.correct.source_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonWidth));
                if (!TextUtils.isEmpty(data.correct.source_pic.img.l.url)) {
                    Glide.with(mContext).load(data.correct.source_pic.img.l.url)
                            .placeholder(R.color.white)
                            .transform(new GlideRoundTransform(App.Instance(), 4)).into(holder.mCHImageView);
                }
            }
        }

        holder.mTvDesc.setText(data.correct.content);
        holder.mUserName.setText(data.correct.teacher_info.sname);
        Glide.with(mContext).load(data.correct.teacher_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
        holder.mCHRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkDetailsActivity.start(mContext, data.correct.correctid);
            }
        });
    }

    public class ViewHolder extends BaseViewHolder {
        private CustomHeightImageView mCHImageView;
        private TextView mTvDesc, mUserName;
        private ImageView mUserIcon;
        private CustomHeightRelativeLayout mCHRootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCHImageView = itemView
                    .findViewById(R.id.iv_custom_image);
            mTvDesc = itemView.findViewById(R.id.tv_desc);
            mCHRootLayout = itemView.findViewById(R.id.custom_root_layout);
            mUserName = itemView.findViewById(R.id.tv_user_name);
            mUserIcon = itemView.findViewById(R.id.iv_user_icon);
        }

    }
}
