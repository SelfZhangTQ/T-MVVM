package com.code.mvvm.core.view.home.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.glide.GlideCircleTransform;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class HomeLiveItemView extends AbsItemView<LiveRecommendVo, HomeLiveItemView.ViewHolder> {

    private int commonWidth=0;

    private Context mContext;

    public HomeLiveItemView(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        if (commonWidth == 0) {
            commonWidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                    - DisplayUtil.dp2px(App.Instance(), 20)) / 2);
        }
        return new ViewHolder(inflater.inflate(R.layout.item_live_view, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LiveRecommendVo live_recommend) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                commonWidth, (int) (0.56 * commonWidth));
        holder.mLiveImage.setLayoutParams(params);
        holder.mLiveImage.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(live_recommend.live_thumb_url).into(holder.mLiveImage);
        Glide.with(mContext).load(live_recommend.userinfo.avatar).transform(new GlideCircleTransform(App.Instance())).into(holder.mUserIcon);

        if (live_recommend.live_status == 1) {
            holder.mLiveState.setBackgroundResource(R.drawable.biaoqian_yugao);
            holder.mLookNum.setText(live_recommend.live_sign_count + "人已报名");
        }
        if (live_recommend.live_status == 2) {
            holder.mLiveState.setBackgroundResource(R.drawable.biaoqian_zhibo);
            holder.mLookNum.setText(live_recommend.hits + "人在围观");
        }
        if (live_recommend.live_status == 3) {
            holder.mLiveState.setBackgroundResource(R.drawable.biaoqian_huifang);
            holder.mLookNum.setText(live_recommend.hits + "人看过");
        }
        holder.mLiveTitle.setText(live_recommend.live_title);
        holder.mUserName.setText(live_recommend.userinfo.sname);
    }


    public static class ViewHolder extends BaseViewHolder {

        private ImageView mLiveState, mLiveImage, mUserIcon;


        private TextView mLookNum, mLiveTitle, mUserName;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLiveState = itemView.findViewById(R.id.iv_live_state);
            mLiveImage = itemView.findViewById(R.id.iv_live_image);
            mUserIcon = itemView.findViewById(R.id.iv_user_icon);
            mLookNum = itemView.findViewById(R.id.tv_look_num);
            mLiveTitle = itemView.findViewById(R.id.tv_live_title);
            mUserName = itemView.findViewById(R.id.tv_user_name);
        }
    }

}
