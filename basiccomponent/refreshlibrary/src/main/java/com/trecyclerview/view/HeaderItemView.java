package com.trecyclerview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trecyclerview.ArrowRefreshHeader;
import com.trecyclerview.ProgressStyle;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/7/13 16:47
 */
public class HeaderItemView extends AbsItemView<HeaderInfo, HeaderItemView.ViewHolder> {

    private ArrowRefreshHeader mRefreshHeader;

    private Context mContext;

    public HeaderItemView(Context context) {
        this.mContext = context;
        mRefreshHeader = new ArrowRefreshHeader(mContext);
        mRefreshHeader.setProgressStyle(ProgressStyle.Pacman);
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(mRefreshHeader);
    }

    public ArrowRefreshHeader getRefreshHeaderView() {
        return mRefreshHeader;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HeaderInfo item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }

    }
}
