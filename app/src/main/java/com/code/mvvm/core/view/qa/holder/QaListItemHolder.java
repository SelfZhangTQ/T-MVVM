package com.code.mvvm.core.view.qa.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.util.DisplayUtil;

/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class QaListItemHolder extends AbsItemHolder<QaListVo.DataBean, QaListItemHolder.ViewHolder> {
    private int commonWidth;


    public QaListItemHolder(Context context) {
        super(context);
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(mContext)
                - 2 * App.instance().getResources()
                .getDimensionPixelSize(
                        R.dimen.concise_three_layout_margin));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_qa_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QaListVo.DataBean dataBean) {
        holder.activityTitle.setText(dataBean.title);
        holder.decs.setText(dataBean.desc);
        holder.lookNum.setText(dataBean.hits);
        holder.decsType.setText(dataBean.keywords);
        double dv = 0.5;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.activityPic.getLayoutParams();
        params.height = (int) (dv * commonWidth);
        holder.activityPic.setLayoutParams(params);
        if (dataBean.imgs != null && dataBean.imgs.size() > 0) {
            Glide.with(mContext).load(dataBean.imgs.get(0).img)
                    .placeholder(R.color.black_e8e8e8)
                    .into(holder.activityPic);
        }
    }


    static class ViewHolder extends AbsHolder {
        TextView decs;
        TextView lookNum;
        TextView decsType;
        TextView activityTitle;
        ImageView activityPic;

        public ViewHolder(View itemView) {
            super(itemView);
            activityTitle = getViewById(R.id.tv_activity_title);
            decsType = getViewById(R.id.tv_decs_type);
            lookNum = getViewById(R.id.tv_look_num);
            decs = getViewById(R.id.tv_activity_decs);
            activityPic = getViewById(R.id.iv_activity_pic);

        }
    }

}
