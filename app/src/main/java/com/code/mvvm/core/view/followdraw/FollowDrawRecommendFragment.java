package com.code.mvvm.core.view.followdraw;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.FollowDrawViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang  on 18/7/2 14:39
 */
public class FollowDrawRecommendFragment extends BaseListFragment<FollowDrawViewModel> {
    public static FollowDrawRecommendFragment newInstance() {
        return new FollowDrawRecommendFragment();
    }
    @Override
    protected void dataObserver() {
        mViewModel.getFollowDrawRemList().observe(this, followDrawRecommendObject -> {
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
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getFollowAdapter(activity);
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

    private void getNetWorkData() {
        mViewModel.getFollowDrawRemList(lastId);

    }
}
