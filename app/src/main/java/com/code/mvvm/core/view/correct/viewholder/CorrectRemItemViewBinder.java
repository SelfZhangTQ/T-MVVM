package com.code.mvvm.core.view.correct.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
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
public class CorrectRemItemViewBinder extends AbsItemView<WorkInfoVo, CorrectRemItemViewBinder.ViewHolder> {
    private int commonwidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
            - 30 * DisplayUtil.getDisplayDensity(App.Instance())) / 2);

    private Context mContext;

    public CorrectRemItemViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    CorrectRemItemViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new CorrectRemItemViewBinder.ViewHolder(inflater.inflate(R.layout.correct_item, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull CorrectRemItemViewBinder.ViewHolder holder, @NonNull final WorkInfoVo data) {
        String mStatus = "0";
        if (mStatus.equals(data.status)) {
            float dv = (float) data.source_pic.img.l.h / (float) data.source_pic.img.l.w;
            holder.mCHImageView.setHeight((int) (dv * commonwidth));
            if (!TextUtils.isEmpty(data.source_pic.img.l.url)) {
                Glide.with(App.Instance()).load(data.source_pic.img.l.url)
                        .placeholder(R.color.black)
                        .transform(new GlideRoundTransform(App.Instance(), 4))
                        .into(holder.mCHImageView);
            }
        } else {
            if (data.correct_pic.img != null) {
                float dv = (float) data.correct_pic.img.l.h / (float) data.correct_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonwidth));
                if (!TextUtils.isEmpty(data.correct_pic.img.l.url)) {
                    Glide.with(mContext).load(data.correct_pic.img.l.url)
                            .placeholder(R.color.white)
                            .transform(new GlideRoundTransform(App.Instance(), 4)).into(holder.mCHImageView);
                }

            } else {
                float dv = (float) data.source_pic.img.l.h / (float) data.source_pic.img.l.w;
                holder.mCHImageView.setHeight((int) (dv * commonwidth));
                if (!TextUtils.isEmpty(data.source_pic.img.l.url)) {
                    Glide.with(mContext).load(data.source_pic.img.l.url)
                            .placeholder(R.color.white)
                            .transform(new GlideRoundTransform(App.Instance(), 4)).into(holder.mCHImageView);
                }
            }
        }

        holder.mTvDesc.setText(data.content);
        holder.mUserName.setText(data.teacher_info.sname);
        Glide.with(mContext).load(data.teacher_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
        holder.mCHRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkDetailsActivity.start(mContext, data.correctid);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
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
