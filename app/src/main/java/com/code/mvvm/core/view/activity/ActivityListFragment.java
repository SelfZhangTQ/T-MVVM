package com.code.mvvm.core.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.vm.ActivityViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

/**
 * @author：tqzhang on 18/7/4 14:10
 */
public class ActivityListFragment extends BaseListFragment<ActivityViewModel> {
    public static ActivityListFragment newInstance() {
        return new ActivityListFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_ACTIVITY_STATE;
    }

    @Override
    protected void dataObserver() {
        registerObserver(Constants.EVENT_KEY_ACTIVITY, ActivityListVo.class).observe(this, activityListVo -> {
            if (activityListVo != null) {
                lastId = activityListVo.data.get(activityListVo.data.size() - 1).newsid;
                setData(activityListVo.data);
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
    protected void getLoadMoreData(){
        getRemoteData();
    }
}
