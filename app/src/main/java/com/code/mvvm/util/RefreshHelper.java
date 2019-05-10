package com.code.mvvm.util;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @description
 * @author：tqzhang on 19/4/16 14:37
 */
public class RefreshHelper implements OnRefreshListener, OnLoadMoreListener {

    private int pageIndex = 1;

    private boolean isLoadMore = false;

    private boolean isRefresh = false;

    private SmartRefreshLayout mSmartRefreshLayout;

    private OnHelperRefreshListener mOnRefreshListener;

    private OnHelperLoadMoreListener mOnLoadMoreListener;


    RefreshHelper(Builder builder) {
        this.mSmartRefreshLayout = builder.mSmartRefreshLayout;
        this.mOnRefreshListener = builder.mOnHelperRefreshListener;
        this.mOnLoadMoreListener = builder.mOnHelperLoadMoreListener;
        if (this.mSmartRefreshLayout == null) {
            throw new IllegalArgumentException("SmartRefreshLayout 不能为空");
        }
        if (this.mOnRefreshListener != null) {
            mSmartRefreshLayout.setOnRefreshListener(this);
        }
        if (this.mOnLoadMoreListener != null) {
            mSmartRefreshLayout.setOnLoadMoreListener(this);
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        if (mOnRefreshListener != null) {
            pageIndex = 1;
            isRefresh = true;
            mOnRefreshListener.onRefresh(true);
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (mOnLoadMoreListener != null) {
            if (!isRefresh && !isLoadMore) {
                pageIndex++;
                isLoadMore = true;
                mOnLoadMoreListener.onLoadMore(isLoadMore, pageIndex);
            }
        }
    }

    public void setEnableRefresh(boolean enable) {
        mSmartRefreshLayout.setEnableRefresh(enable);
    }

    public void setEnableLoadMore(boolean enable) {
        mSmartRefreshLayout.setEnableLoadMore(enable);
    }


    public void refreshComplete() {
        isRefresh = false;
        mSmartRefreshLayout.finishRefresh();
    }

    public void loadMoreComplete() {
        isLoadMore = false;
        mSmartRefreshLayout.finishLoadMore();

    }


    public static class Builder {

        private SmartRefreshLayout mSmartRefreshLayout;

        private OnHelperRefreshListener mOnHelperRefreshListener;

        private OnHelperLoadMoreListener mOnHelperLoadMoreListener;

        public Builder setRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
            this.mSmartRefreshLayout = smartRefreshLayout;
            return this;
        }

        public Builder setOnRefreshListener(OnHelperRefreshListener onRefreshListener) {
            this.mOnHelperRefreshListener = onRefreshListener;
            return this;
        }

        public Builder setOnLoadMoreListener(OnHelperLoadMoreListener onLoadMoreListener) {
            this.mOnHelperLoadMoreListener = onLoadMoreListener;
            return this;
        }

        public RefreshHelper build() {
            return new RefreshHelper(this);
        }

    }

    public interface OnHelperRefreshListener {
        /**
         * 刷新
         *
         * @param isRefresh
         */
        void onRefresh(boolean isRefresh);
    }

    public interface OnHelperLoadMoreListener {
        /**
         * 加载跟多
         *
         * @param isLoadMore
         * @param pageIndex
         */
        void onLoadMore(boolean isLoadMore, int pageIndex);
    }


}
