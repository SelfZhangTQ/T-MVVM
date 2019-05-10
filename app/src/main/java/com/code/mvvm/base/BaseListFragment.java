package com.code.mvvm.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.util.RefreshHelper;
import com.mvvm.base.AbsLifecycleFragment;
import com.mvvm.base.AbsViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Collection;

/**
 * @author：tqzhang on 18/7/10 16:20
 */
public abstract class BaseListFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> implements RefreshHelper.OnHelperRefreshListener, RefreshHelper.OnHelperLoadMoreListener {

    protected SmartRefreshLayout mSmartRefreshLayout;

    protected RecyclerView mRecyclerView;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected RecyclerView.LayoutManager layoutManager;

    protected DelegateAdapter adapter;

    protected String lastId = null;

    protected ItemData mItems;

    protected RefreshHelper refreshHelper;

    protected boolean isLoadMore = false;

    protected boolean isRefresh = false;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list_common;
    }

    @Override
    public int getContentResId() {
        return R.id.refresh_layout;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mRecyclerView = getViewById(R.id.recycler_view);
        mSmartRefreshLayout = getViewById(R.id.refresh_layout);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        mItems = new ItemData();
        refreshHelper = new RefreshHelper.Builder()
                .setRefreshLayout(mSmartRefreshLayout)
                .setOnRefreshListener(this)
                .setOnLoadMoreListener(this)
                .build();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnScrollListener(onScrollListener);

    }

    @Override
    protected void lazyLoad() {
        getRemoteData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
    }


    protected void setUiData(Collection<?> data) {
        if (!isLoadMore) {
            mItems.clear();
            isLoadMore = false;
            mItems.addAll(data);
            setData();
        } else {
            mItems.addAll(data);
            setMoreData();
        }
    }

    protected void setData() {
        adapter.setDatas(mItems);
        adapter.notifyDataSetChanged();
        if (isRefresh) {
            refreshHelper.refreshComplete();
        }
    }

    protected void setMoreData() {
        adapter.notifyDataSetChanged();
        if (isLoadMore) {
            refreshHelper.loadMoreComplete();
        }
        isLoadMore = false;
    }


    /**
     * adapter
     *
     * @return DelegateAdapter
     */
    protected abstract DelegateAdapter createAdapter();

    /**
     * LayoutManager
     *
     * @return LayoutManager
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();


    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }


    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        this.isLoadMore = isLoadMore;
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
        lastId = null;
        getRemoteData();
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (activity != null) {
                    Glide.with(activity).resumeRequests();
                }
            } else {
                if (activity != null) {
                    Glide.with(activity).pauseRequests();
                }
            }
        }
    };
}
