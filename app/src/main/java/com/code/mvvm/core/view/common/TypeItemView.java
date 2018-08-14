package com.code.mvvm.core.view.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class TypeItemView extends AbsItemView<TypeVo, TypeItemView.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_type, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TypeVo typeVo) {
        holder.mClassifyType.setText(typeVo.title);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.mRootLayout.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final TextView mClassifyType;
        private LinearLayout mRootLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mClassifyType = itemView.findViewById(R.id.tv_classify_type);
            mRootLayout = itemView.findViewById(R.id.root_layout);
        }
    }
}
