package com.code.mvvm.core.view.material.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.material.MaterialInfoVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:16
 */
public class MatreialListItemViewBinder extends AbsItemView<MaterialInfoVo, MatreialListItemViewBinder.ViewHolder> {
    private int commonWidth;
    private Context mContext;

    public MatreialListItemViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                - DisplayUtil.dp2px(App.Instance(), 30)) / 2);
        return new ViewHolder(inflater.inflate(R.layout.item_material_list, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MaterialInfoVo matreialsubject) {
        float dv = (float) matreialsubject.imgs.l.h / (float) matreialsubject.imgs.l.w;
        int height = (int) (dv * commonWidth);
        holder.mMaterialPic.setLayoutParams(new RelativeLayout.LayoutParams(commonWidth, height));
        Glide.with(mContext).load(matreialsubject.imgs.l.url)
                .placeholder(R.color.black_333333)
                .transform(new GlideRoundTransform(mContext, 4))
                .override(commonWidth, (int) (dv * commonWidth)).into(holder.mMaterialPic);
        holder.mMaterialTitle.setText(matreialsubject.title);

    }

    static class ViewHolder extends BaseViewHolder {
        TextView mMaterialTitle;
        CustomHeightImageView mMaterialPic;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMaterialTitle = itemView.findViewById(R.id.tv_material_title);
            mMaterialPic = itemView.findViewById(R.id.iv_pic_image);

        }

    }

}
