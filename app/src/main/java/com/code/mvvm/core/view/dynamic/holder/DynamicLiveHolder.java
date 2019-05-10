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
 * @author：tqzhang  on 18/7/4 15:35
 */
public class DynamicLiveHolder extends AbsItemHolder<DynamicInfoVo, DynamicLiveHolder.ViewHolder> {
    private int contentWidth;

    public DynamicLiveHolder(Context context) {
        super(context);
        contentWidth = DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dp2px(mContext, 95);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_live;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DynamicInfoVo item) {
        holder.tvUserName.setText(item.userinfo.sname);
        Glide.with(mContext)
                .load(item.userinfo.avatar)
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
        holder.lookNum.setText(new StringBuilder(item.live_info.hits).append("人看过").toString());
        holder.dynamicTitle.setText(item.live_info.live_title);
        if (item.live_info.live_status == 1) {
            holder.zhibo_state.setBackgroundResource(R.drawable.preview_state_icon);
        }
        if (item.live_info.live_status == 2) {
            holder.zhibo_state.setBackgroundResource(R.drawable.living_state_icon);
        }
        if (item.live_info.live_status == 3) {
            holder.zhibo_state.setBackgroundResource(R.drawable.playback_state_icon);
        }

        int commonwidth = contentWidth - DisplayUtil.dp2px(App.instance(), 14 * 2);
        double dv = 0.56;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.img_content_layout.getLayoutParams();
        params.height = (int) (dv * commonwidth);
        holder.img_content_layout.setLayoutParams(params);
        if (item.live_info.live_thumb_url != null) {
            Glide.with(mContext).load(item.live_info.live_thumb_url)
                    .placeholder(R.color.black_e8e8e8)
                    .into(holder.zhibo_img);
        } else if (!TextUtils.isEmpty(item.live_info.recording_thumb_url)) {
            Glide.with(mContext).load(item.live_info.recording_thumb_url)
                    .placeholder(R.color.black_e8e8e8)
                    .into(holder.zhibo_img);
        }
    }


    public static class ViewHolder extends AbsHolder {
        private ImageView zhibo_img;
        private ImageView zhibo_state;
        private TextView tvUserName, userType, dynamicTitle, lookNum;
        private ImageView ivUserPic;
        private LinearLayout mUserTag;
        private View img_content_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            zhibo_img = getViewById(R.id.iv_live_pic);
            img_content_layout = getViewById(R.id.ly_zhibo_content_img);
            zhibo_state = getViewById(R.id.iv_live_state);
            ivUserPic = getViewById(R.id.iv_user_pic);
            tvUserName = getViewById(R.id.tv_user_name);
            userType = getViewById(R.id.user_type);
            dynamicTitle = getViewById(R.id.tv_dynamic_title);
            lookNum = getViewById(R.id.tv_look_num);
            mUserTag = getViewById(R.id.ll_user_tag);
        }
    }
}
