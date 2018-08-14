package com.trecyclerview.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/7/13 16:47
 */
public abstract class FootItemView<T, VH extends RecyclerView.ViewHolder> extends AbsItemView {

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Object item) {
        onBindHolder((VH) holder, (T) item);
    }

    protected abstract void onBindHolder(@NonNull VH holder, @NonNull T mFootData);
}
