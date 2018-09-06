package com.code.mvvm.core.view.live;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.LiveViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/6/30 18:36
 */
public class LiveRecommendFragment extends BaseListFragment<LiveViewModel> {

    public static LiveRecommendFragment newInstance() {
        return new LiveRecommendFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getLiveRemList().observe(this, liveListVo -> {
            if (liveListVo != null) {

                lastId = liveListVo.
                        data.get(liveListVo.data.size() - 1).liveid;
                setData(liveListVo.data);

            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getLiveRemAdapter(activity);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNetWorkData();
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getNetWorkData();
    }

    protected void getNetWorkData() {
        mViewModel.getLiveRemList(lastId);
    }


}
