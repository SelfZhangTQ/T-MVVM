package com.code.mvvm.core.view.followdraw;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.vm.FollowDrawViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

/**
 * @author：tqzhang  on 18/7/2 14:39
 */
public class FollowDrawRecommendFragment extends BaseListFragment<FollowDrawViewModel> {
    public static FollowDrawRecommendFragment newInstance() {
        return new FollowDrawRecommendFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_FD_RED_STATE;
    }

    @Override
    protected void dataObserver() {

        registerObserver(Constants.EVENT_KEY_FD_RED, FollowDrawRecommendVo.class).observe(this, followDrawRecommendObject -> {
            if (followDrawRecommendObject == null) {
                return;
            }
            lastId = followDrawRecommendObject.data.get(followDrawRecommendObject.data.size() - 1).lessonid;
            setData(followDrawRecommendObject.data);
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getFollowAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getFollowDrawRemList(lastId);
    }

    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }
}
