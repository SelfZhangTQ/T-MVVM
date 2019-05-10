package com.code.mvvm.core.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adapter.adapter.DelegateAdapter;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.source.ActivityRepository;
import com.code.mvvm.core.vm.ActivityViewModel;
import com.code.mvvm.util.AdapterPool;

/**
 * @author：tqzhang on 18/7/4 14:10
 */
public class ActivityListFragment extends BaseListFragment<ActivityViewModel> {

    public static ActivityListFragment newInstance() {
        return new ActivityListFragment();
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(ActivityRepository.EVENT_KEY_ACTIVITY, ActivityListVo.class).observe(this, activityListVo -> {
            if (activityListVo != null) {
                lastId = activityListVo.data.get(activityListVo.data.size() - 1).newsid;
                setUiData(activityListVo.data);
            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getActivityAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getActivityList(lastId);
    }


    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        getRemoteData();
    }
}
