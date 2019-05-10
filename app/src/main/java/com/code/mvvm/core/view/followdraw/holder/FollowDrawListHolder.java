package com.code.mvvm.core.view.followdraw.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;

/**
 * @author：tqzhang  on 18/6/19 15:16
 */
public class FollowDrawListHolder extends AbsItemHolder<FollowDrawInfoVo, FollowDrawListHolder.ViewHolder> {
    private int commonWidth;

    public FollowDrawListHolder(Context context) {
        super(context);
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - DisplayUtil.dp2px(mContext, 30)) / 2);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_follow_drawing;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FollowDrawInfoVo matreialsubject) {
        float dv = (float) matreialsubject.imgs.l.h / (float) matreialsubject.imgs.l.w;
        holder.ivPic.setHeight((int) (dv * commonWidth));
        Glide.with(mContext).load(matreialsubject.imgs.l.url)
                .placeholder(R.color.black_e8e8e8)
                .transform(new GlideRoundTransform(mContext, 4))
                .override(commonWidth, (int) (dv * commonWidth)).into(holder.ivPic);
        holder.tvTitle.setText(matreialsubject.title);

    }

    static class ViewHolder extends AbsHolder {
        TextView tvTitle;
        CustomHeightImageView ivPic;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = getViewById(R.id.iv_pic);
            tvTitle = getViewById(R.id.tv_title);

        }

    }

}
