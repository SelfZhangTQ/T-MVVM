package com.code.mvvm.core.view.dynamic.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.util.ViewUtils;

/**
 * @author：tqzhang on 18/7/4 15:35
 */
public class DynamicSubjectHolder extends AbsItemHolder<DynamicInfoVo, DynamicSubjectHolder.ViewHolder> {
    private int contentWidth;

    public DynamicSubjectHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dp2px(mContext, 15 + 40 + 10 + 30);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_material_subject;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext).load(item.userinfo.avatar)
                .transform(new GlideCircleTransform(mContext))
                .into(holder.ivUserPic);
        holder.mUserTag.removeAllViews();
        if (!TextUtils.isEmpty(item.userinfo.province) && !TextUtils.equals("false", item.userinfo.province)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext, item.userinfo.province));
        }
        if (!TextUtils.isEmpty(item.userinfo.profession)
                && !TextUtils.equals("false", item.userinfo.profession)) {
            holder.mUserTag.addView(ViewUtils.CreateTagView(mContext, item.userinfo.profession));
        }
        holder.userType.setText(item.title);
        holder.lookNum.setText(new StringBuilder(item.material_info.hits).append("人看过"));
        holder.dynamicTitle.setText(item.material_info.title);

        int mWidth = contentWidth - DisplayUtil.dp2px(App.instance(), 14 * 2);
        float dv = (float) item.material_info.picurl.l.h / (float) item.material_info.picurl.l.w;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, (int) (dv * mWidth));
        holder.dynamicPic.setLayoutParams(params);

        if (item.material_info.picurl != null) {
            Glide.with(mContext).load(item.material_info.picurl.l.url).placeholder(R.color.black_e8e8e8).into(holder.dynamicPic);
        }
    }


    public static class ViewHolder extends AbsHolder {

        private TextView tvUserName, userType, dynamicTitle, lookNum;
        private ImageView ivUserPic, dynamicPic;
        private LinearLayout mUserTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ivUserPic = getViewById(R.id.iv_user_pic);
            dynamicPic = getViewById(R.id.iv_dynamic_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }

}
