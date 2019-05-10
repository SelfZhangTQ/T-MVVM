package com.code.mvvm.core.view.correct.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkPicVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.util.ViewUtils;
import com.code.mvvm.widget.CustomHeightImageView;

/**
 * @author：tqzhang  on 18/6/27 19:14
 */
public class CorrectPicHolder extends AbsItemHolder<WorkDetailVo, CorrectPicHolder.ViewHolder> {

    public CorrectPicHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.correct_pic_item;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull CorrectPicHolder.ViewHolder holder, @NonNull WorkDetailVo object) {
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.ll_root.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
        if (!TextUtils.isEmpty(object.data.tweet_info.avatar)) {
            Glide.with(mContext).load(object.data.tweet_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.workPic);
            Glide.with(mContext).load(object.data.teacher_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.teacherIcon);
        }
        holder.teacherName.setText(object.data.teacher_info.sname);
        holder.userName.setText(object.data.tweet_info.sname);
        WorkPicVo correct_img = null;
        if (object.data.status.equals("1")) {
            if (object.data.correct_pic == null) {
                correct_img = object.data.source_pic;
            } else {
                correct_img = object.data.correct_pic;
            }

        } else {
            correct_img = object.data.source_pic;
        }
        int hightc = DisplayUtil.getScreenWidth(mContext) * correct_img.img.n.h / correct_img.img.n.w;
        LinearLayout.LayoutParams cparams = new LinearLayout.LayoutParams(
                DisplayUtil.getScreenWidth(mContext), hightc);
        holder.imagePic.setLayoutParams(cparams);
        Glide.with(mContext).load(correct_img.img.s.url).placeholder(R.color.black_e8e8e8).override(DisplayUtil.getScreenWidth(mContext), hightc).into(holder.imagePic);
        holder.userTag.removeAllViews();
        if (!TextUtils.isEmpty(object.data.tweet_info.province) && !TextUtils.equals("false", object.data.tweet_info.province)) {
            holder.userTag.addView(ViewUtils.CreateTagView(mContext, object.data.tweet_info.province));
        }
        if (!TextUtils.isEmpty(object.data.tweet_info.profession)
                && !TextUtils.equals("false", object.data.tweet_info.profession)) {
            holder.userTag.addView(ViewUtils.CreateTagView(mContext, object.data.tweet_info.profession));
        }
    }

    static class ViewHolder extends AbsHolder {
        private CustomHeightImageView imagePic;
        private ImageView workPic, teacherIcon;
        private TextView teacherName, userName;
        private LinearLayout ll_root, userTag;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_root = getViewById(R.id.ll_root);
            userTag = getViewById(R.id.ll_user_tag);
            imagePic = getViewById(R.id.iv_pic);
            workPic = getViewById(R.id.iv_header_pic);
            teacherIcon = getViewById(R.id.iv_teacher_icon);
            teacherName = getViewById(R.id.tv_teacher_name);
            userName = getViewById(R.id.tv_name);
        }

    }


}
