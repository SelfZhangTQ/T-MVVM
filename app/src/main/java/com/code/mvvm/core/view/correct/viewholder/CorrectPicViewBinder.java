package com.code.mvvm.core.view.correct.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.correct.CorrectDetailVo;
import com.code.mvvm.core.data.pojo.correct.CorrectPicVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/27 19:14
 */
public class CorrectPicViewBinder extends AbsItemView<CorrectDetailVo, CorrectPicViewBinder.ViewHolder> {
    private Context mContext;

    public CorrectPicViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    CorrectPicViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new CorrectPicViewBinder.ViewHolder(inflater.inflate(R.layout.correct_pic_item, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull CorrectPicViewBinder.ViewHolder holder, @NonNull CorrectDetailVo object) {
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.ll_root.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
        // 头像
        if (!TextUtils.isEmpty(object.data.tweet_info.avatar)) {
            Glide.with(mContext).load(object.data.tweet_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.iv_icon);
            Glide.with(mContext).load(object.data.teacher_info.avatar).transform(new GlideCircleTransform(mContext)).into(holder.correct_teacher_pic);
        }
//老师昵称
        holder.teacherName.setText(object.data.teacher_info.sname);
        holder.tv_name.setText(object.data.tweet_info.sname);


        CorrectPicVo correct_img = null;
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
        // 设置批改作品图片大小
        LinearLayout.LayoutParams cparams = new LinearLayout.LayoutParams(
                DisplayUtil.getScreenWidth(mContext), hightc);
        holder.iv_pic.setLayoutParams(cparams);
        Glide.with(mContext).load(correct_img.img.s.url).placeholder(R.color.dark).override(DisplayUtil.getScreenWidth(mContext), hightc).into(holder.iv_pic);
        // 展示用户信息tag
        holder.ll_user_tag.removeAllViews();
        if (!TextUtils.isEmpty(object.data.tweet_info.province) && !TextUtils.equals("false", object.data.tweet_info.province)) {
            View view = initTag(object.data.tweet_info.province);
            holder.ll_user_tag.addView(view);
        }
        if (!TextUtils.isEmpty(object.data.tweet_info.profession)
                && !TextUtils.equals("false", object.data.tweet_info.profession)) {
            View view = initTag(object.data.tweet_info.profession);
            holder.ll_user_tag.addView(view);
        }
    }

    /**
     * @param text
     * @return
     * @Description 添加用户标签方法
     * @author lad
     */
    private View initTag(String text) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user_tag, null);
        TextView tv_tag = (TextView) view.findViewById(R.id.tv_tag);
        tv_tag.setText(text);
        return view;
    }

    static class ViewHolder extends BaseViewHolder {

        private CustomHeightImageView iv_pic;
        private ImageView iv_icon, correct_teacher_pic;
        private TextView teacherName, tv_name, tv_time;
        private LinearLayout ll_root, ll_user_tag;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_root = itemView.findViewById(R.id.ll_root);
            ll_user_tag = itemView.findViewById(R.id.ll_user_tag);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            iv_icon = itemView.findViewById(R.id.iv_header_pic);
            correct_teacher_pic = itemView.findViewById(R.id.iv_teacher_icon);
            teacherName = itemView.findViewById(R.id.tv_teacher_name);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_name);
        }

    }


}
