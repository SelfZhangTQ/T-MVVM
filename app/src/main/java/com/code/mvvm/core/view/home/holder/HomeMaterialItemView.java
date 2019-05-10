package com.code.mvvm.core.view.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;

/**
 * @author：tqzhang on 18/6/19 15:16
 */
public class HomeMaterialItemView extends AbsItemHolder<MatreialSubjectVo, HomeMaterialItemView.ViewHolder> {

    private int commonWidth ;
    public HomeMaterialItemView(Context context) {
        super(context);
        commonWidth= (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - DisplayUtil.dp2px(mContext, 8)) / 2);
    }

    @Override
    public int getLayoutResId() {
//        return R.layout.home_matreial_item;
        return R.layout.item_home_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MatreialSubjectVo matreialsubject) {
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        holder.mRecyclerView.setLayoutManager(layoutManager);
//        HomeListAdapter adapter = new HomeListAdapter(mContext, null, 0);
//        holder.mRecyclerView.setAdapter(adapter);
//        holder.mRecyclerView.setNestedScrollingEnabled(false);
//        adapter.setList(matreialsubject.matreialsubject);
//        adapter.notifyDataSetChanged();
        float dv = (float) matreialsubject.picurl.l.h / (float) matreialsubject.picurl.l.w;
        int height = (int) (dv * commonWidth);
        holder.videoImg.setLayoutParams(new RelativeLayout.LayoutParams(commonWidth, height));
        Glide.with(mContext).load(matreialsubject.picurl.l.url)
                .placeholder(R.color.black_e8e8e8)
                .transform(new GlideRoundTransform(mContext, 4))
                .override(commonWidth, (int) (dv * commonWidth)).into(holder.videoImg);
        holder.videoTitle.setText(matreialsubject.title);
    }

    static class ViewHolder extends AbsHolder {

         CustomHeightImageView videoImg;
         TextView videoTitle;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImg = getViewById(R.id.iv_pic_image);
            videoTitle = getViewById(R.id.video_title);
        }

    }

}
