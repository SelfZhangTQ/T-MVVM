package com.code.mvvm.core.view.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.code.mvvm.R;
import com.trecyclerview.LoadingMoreFooter;
import com.trecyclerview.ProgressStyle;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.view.FootItemView;

import static com.trecyclerview.LoadingMoreFooter.STATE_LOADING;
import static com.trecyclerview.LoadingMoreFooter.STATE_NOMORE;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class FootItemViewBinder extends FootItemView<FootInfo, FootItemViewBinder.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.foot_view, parent, false));

    }

    @Override
    protected void onBindHolder(@NonNull ViewHolder holder, @NonNull FootInfo mFootData) {

        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.rl_foot.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
        holder.loadingProgressBar.setProgressStyle(ProgressStyle.Pacman);
        if (mFootData.state == STATE_NOMORE) {
            holder.loadingProgressBar.setState(STATE_NOMORE);
        } else if (mFootData.state == STATE_LOADING) {
            holder.loadingProgressBar.setState(STATE_LOADING);
        }

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl_foot;
        LoadingMoreFooter loadingProgressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_foot = itemView.findViewById(R.id.foot_view);
            loadingProgressBar = itemView.findViewById(R.id.loading_progress_bar);
        }
    }
}
