package com.code.mvvm.core.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.ActivityViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/7/4 14:10
 */
public class ActivityListFragment extends BaseListFragment<ActivityViewModel> {
    public static ActivityListFragment newInstance() {
        return new ActivityListFragment();
    }

    @Override
    protected void dataObserver() {
        mViewModel.getActivityList().observe(this, activityListVo -> {
            if (activityListVo != null) {
                lastId = activityListVo.data.get(activityListVo.data.size() - 1).newsid;
                setData(activityListVo.data);
            }

        });
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getActivityAdapter(activity);
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

    public void getNetWorkData() {
        mViewModel.getActivityList(lastId);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mViewModel.getActivityList(lastId);
    }

}
