package com.code.mvvm.core.view.material.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.material.MaterialInfoVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;

/**
 * @author：tqzhang on 18/6/19 15:16
 */
public class MaterialListHolder extends AbsItemHolder<MaterialInfoVo, MaterialListHolder.ViewHolder> {
    private int commonWidth;

    public MaterialListHolder(Context context) {
        super(context);
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(mContext)
                - DisplayUtil.dp2px(mContext, 30)) / 2);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_material_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MaterialInfoVo matreialsubject) {
        float dv = (float) matreialsubject.imgs.l.h / (float) matreialsubject.imgs.l.w;
        int height = (int) (dv * commonWidth);
        holder.mMaterialPic.setLayoutParams(new RelativeLayout.LayoutParams(commonWidth, height));
        Glide.with(mContext).load(matreialsubject.imgs.l.url)
                .placeholder(R.color.black_e8e8e8)
                .transform(new GlideRoundTransform(mContext, 4))
                .override(commonWidth, (int) (dv * commonWidth))
                .into(holder.mMaterialPic);
        holder.mMaterialTitle.setText(matreialsubject.title);

    }

    static class ViewHolder extends AbsHolder {
        TextView mMaterialTitle;
        CustomHeightImageView mMaterialPic;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMaterialTitle = getViewById(R.id.tv_material_title);
            mMaterialPic = getViewById(R.id.iv_pic_image);

        }

    }

}
