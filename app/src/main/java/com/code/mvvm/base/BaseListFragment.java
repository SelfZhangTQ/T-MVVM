package com.code.mvvm.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.mvvm.base.AbsLifecycleFragment;
import com.mvvm.base.AbsViewModel;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.listener.OnRefreshListener;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.util.Collection;
import java.util.List;

/**
 * @author：tqzhang on 18/7/10 16:20
 */
public abstract class BaseListFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> implements OnRefreshListener {
    protected TRecyclerView mRecyclerView;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    protected RecyclerView.LayoutManager layoutManager;

    protected MultiTypeAdapter adapter;

    protected String lastId = null;

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
        mRecyclerView.addOnScrollStateListener(state1 -> {
            if (state1 == RecyclerView.SCROLL_STATE_IDLE) {
                if (activity != null) {
                    Glide.with(activity).resumeRequests();
                }
            } else {
                if (activity != null) {
                    Glide.with(activity).pauseRequests();
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        isLoadMore = false;
    }

    protected void setData(List<?> collection) {
        if (isLoadMore) {
            onLoadMoreSuccess(collection);
        } else {
            onRefreshSuccess(collection);
        }
    }

    @Override
    public void onRefresh() {
        lastId = null;
        isRefresh = true;
        isLoadMore = false;
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
    }

    protected void setBannerData(BannerListVo headAdList) {
        newItems.add(headAdList);
    }

    protected void onRefreshSuccess(Collection<?> collection) {
        newItems.addAll(collection);
        oldItems.clear();
        oldItems.addAll(newItems);
        if (collection.size() < 20) {
            mRecyclerView.refreshComplete(oldItems, true);
        } else {
            mRecyclerView.refreshComplete(oldItems, false);
        }
        isRefresh = false;
    }

    protected void onLoadMoreSuccess(List<?> collection) {
        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        if (collection.size() < 20) {
            mRecyclerView.loadMoreComplete(collection,true);
        } else {
            mRecyclerView.loadMoreComplete(collection,false);
        }
    }

    /**
     * adapter
     * @return MultiTypeAdapter
     */
    protected abstract MultiTypeAdapter createAdapter();

    /**
     * LayoutManager
     * @return LayoutManager
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();


    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }


}
