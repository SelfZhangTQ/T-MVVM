package com.code.mvvm.core.view.swipe;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.viewmodel.CorrectViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/6/26 17:02
 */
//public class CorrectFragment extends BaseListFragment<CorrectViewModel> {
public class SwipeCorrectFragment extends SwipeListFragment<CorrectViewModel> {
    private String utime;

    public static SwipeCorrectFragment newInstance() {
        return new SwipeCorrectFragment();
    }


    @Override
    public void initView(Bundle state) {
        super.initView(state);
        mViewModel.getBannerData().observe(this, new Observer<BannerAdListVo>() {
            @Override
            public void onChanged(@Nullable BannerAdListVo headAdList) {
                if (headAdList != null) {
                    setBannerData(headAdList);
                }
            }
        });
        mViewModel.getCorrectData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable WorksListVo worksListHotObject) {
                if (worksListHotObject == null) {
                    return;
                }
                lastid = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).tid;
                utime = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).utime;
                if (isRefresh) {
                    newItems.addAll(worksListHotObject.data.content);
                    oldItems.clear();
                    oldItems.addAll(newItems);
                    notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
//                    mRecyclerView.refreshComplete();
                } else {
                    setData(worksListHotObject.data.content);
                }

            }
        });
        mViewModel.getCorrectMoreData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable WorksListVo worksListHotObject) {
                lastid = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).tid;
                utime = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).utime;
                setData(worksListHotObject.data.content);
            }
        });


    }

    @Override
    protected CorrectViewModel createViewModelProviders() {
        return VMProviders(this, CorrectViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getSwipeCorrectAdapter(getActivity());
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getAdData();
        getCorrectData();
        mViewModel.getRequestMerge();
    }

    @Override
    protected void onRefreshAction() {
        getAdData();
        getCorrectData();
        mViewModel.getRequestMerge();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getCorrectMoreData("", lastid, utime, "20");
    }

    @Override
    protected void onStateRefresh() {

    }

    private void getCorrectData() {
        mViewModel.getCorrectData("80", "20");
    }

    private void getAdData() {
        mViewModel.getBannerData("1", "4", "109", "", null);
    }

}
