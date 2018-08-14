package com.code.mvvm.core.view.followdraw.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:16
 */
public class FollowDrawListItemViewBinder extends AbsItemView<FollowDrawInfoVo, FollowDrawListItemViewBinder.ViewHolder> {
    private int commonWidth;
    private Context mContext;

    public FollowDrawListItemViewBinder(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                - DisplayUtil.dp2px(App.Instance(), 30)) / 2);

        return new ViewHolder(inflater.inflate(R.layout.item_follow_drawing, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FollowDrawInfoVo matreialsubject) {
        float dv = (float) matreialsubject.imgs.l.h / (float) matreialsubject.imgs.l.w;
        holder.ivPic.setHeight((int) (dv * commonWidth));
        Glide.with(mContext).load(matreialsubject.imgs.l.url)
                .placeholder(R.color.black_333333)
                .transform(new GlideRoundTransform(mContext, 4))
                .override(commonWidth, (int) (dv * commonWidth)).into(holder.ivPic);
        holder.tvTitle.setText(matreialsubject.title);

    }

    static class ViewHolder extends BaseViewHolder {
        TextView tvTitle;
        CustomHeightImageView ivPic;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_pic);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }

    }

}
