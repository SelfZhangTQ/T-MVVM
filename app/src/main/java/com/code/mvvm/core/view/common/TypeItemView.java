package com.code.mvvm.core.view.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.common.TypeVo;

/**
 * @author：tqzhang on 18/6/20 13:41
 */
public class TypeItemView extends AbsItemHolder<TypeVo, TypeItemView.ViewHolder> {

    public TypeItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_type;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TypeVo typeVo) {
        holder.mClassifyType.setText(typeVo.title);
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends AbsHolder {

        private TextView mClassifyType;
        private LinearLayout mRootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mClassifyType = getViewById(R.id.tv_classify_type);
            mRootLayout = getViewById(R.id.root_layout);
        }
    }
}
