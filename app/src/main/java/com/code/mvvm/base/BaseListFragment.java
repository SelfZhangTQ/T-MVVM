package com.code.mvvm.base;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.viewmodel.BaseViewModel;
import com.code.mvvm.util.DiffCallback;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.listener.OnScrollStateListener;
import com.trecyclerview.listener.OnRefreshListener;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.util.Collection;

import static com.trecyclerview.LoadingMoreFooter.STATE_LOADING;
import static com.trecyclerview.LoadingMoreFooter.STATE_NOMORE;

/**
 * @author：zhangtianqiu on 18/7/10 16:20
 */
public abstract class BaseListFragment<T extends BaseViewModel> extends LifecycleFragment<T> implements OnRefreshListener {
    protected TRecyclerView mRecyclerView;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected RecyclerView.LayoutManager layoutManager;

    protected MultiTypeAdapter adapter;

    protected String lastid = null;

    protected boolean isLoadMore = true;

    protected boolean isLoading = true;

    protected boolean isRefresh = false;

    protected Items oldItems;

    protected Items newItems;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mRecyclerView = getViewById(R.id.recycler_view);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        oldItems = new Items();
        newItems = new Items();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnRefreshListener(this);
        mRecyclerView.addOnScrollStateListener(new OnScrollStateListener() {
            @Override
            public void onScrollStateChanged(int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE) {
                    if (activity != null) {
                        Glide.with(activity).resumeRequests();
                    }
                } else {
                    if (activity != null) {
                        Glide.with(activity).pauseRequests();
                    }
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        oldItems.add(new HeaderInfo());
        isLoadMore = false;
    }

    protected void setData(Collection<?> collection) {
        if (isLoadMore) {
            onLoadMoreSuccess(collection);

        } else {
            if (isRefresh) {
                onRefreshSuccess(collection);
            } else {
                onDefaultSuccess(collection);
            }
        }
    }

    @Override
    public void onRefresh() {
        lastid = null;
        isRefresh = true;
        onRefreshAction();
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        if (isLoading) {
            isLoading = false;
            addFootData(STATE_LOADING);
            getRemoteData();
        }
    }

    protected void onRefreshAction() {
        addHeaderData();
    }

    protected void setBannerData(BannerListVo headAdList) {
        if (isRefresh) {
            newItems.add(headAdList);
        } else {
            oldItems.add(headAdList);
        }
    }


    protected void onRefreshSuccess(Collection<?> collection) {
        newItems.addAll(collection);
        refreshDataChanged();
    }

    protected void onDefaultSuccess(Collection<?> collection) {
        oldItems.addAll(collection);
        adapter.setItems(oldItems);
        if (collection.size() < 20) {
            mRecyclerView.setNoMore();
            addFootData(STATE_NOMORE);
        }
        notifyDataSetChanged();
    }

    protected void onLoadMoreSuccess(Collection<?> collection) {
        oldItems.remove(oldItems.size() - 1);
        oldItems.addAll(collection);
        notifyMoreDataChanged(oldItems.size() - collection.size(), oldItems.size());
        if (collection.size() < 20) {
            mRecyclerView.setNoMore();
            addFootData(STATE_NOMORE);
        }
        mRecyclerView.loadMoreComplete();
        isLoading = true;
        isLoadMore = false;
        newItems.clear();
    }

    protected void refreshDataChanged() {
        oldItems.clear();
        oldItems.addAll(newItems);
        notifyDataSetChanged();
        mRecyclerView.refreshComplete();
        isRefresh = false;
        newItems.clear();
    }


    protected void diffNotifyDataChanged() {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(oldItems, newItems), true);
        oldItems.clear();
        oldItems.addAll(newItems);
        newItems.clear();
        result.dispatchUpdatesTo(adapter);
    }

    protected void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    protected void notifyMoreDataChanged(int positionStart, int itemCount) {
        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    protected void addHeaderData() {
        if (isRefresh) {
            newItems.add(new HeaderInfo());
        } else {
            oldItems.add(new HeaderInfo());
        }

    }

    protected void addFootData(int state) {
        oldItems.add(new FootInfo(state));
        notifyMoreDataChanged(oldItems.size() - 1, oldItems.size());
    }

    /**
     * @return
     */
    protected abstract MultiTypeAdapter createAdapter();

    /**
     * @return
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();


    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }


}
