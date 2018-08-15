package com.code.mvvm.core.view.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.viewmodel.ActivityViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/7/4 14:10
 */
public class ActivityListFragment extends BaseListFragment<ActivityViewModel> {
    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getActivityList().observe(this, new Observer<ActivityListVo>() {
            @Override
            public void onChanged(@Nullable ActivityListVo activityListVo) {
                if (activityListVo != null) {
                    lastId = activityListVo.data.get(activityListVo.data.size() - 1).newsid;
                    setData(activityListVo.data);
                }

            }
        });
    }

    @Override
    protected ActivityViewModel createViewModelProviders() {
        return VMProviders(this, ActivityViewModel.class);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getActivityAdapter(getActivity());
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getRemoteData();

    }

    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getActivityList(lastId, "20");
    }

}
