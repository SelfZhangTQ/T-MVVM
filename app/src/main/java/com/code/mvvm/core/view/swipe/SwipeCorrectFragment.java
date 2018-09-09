package com.code.mvvm.core.view.swipe;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.vm.WorkViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/6/26 17:02
 */
//public class WorkFragment extends BaseListFragment<WorkViewModel> {
public class SwipeCorrectFragment extends SwipeListFragment<WorkViewModel> {
    private String utime;

    public static SwipeCorrectFragment newInstance() {
        return new SwipeCorrectFragment();
    }


    @Override
    public void initView(Bundle state) {
        super.initView(state);
//        mViewModel.getBannerData().observe(this, new Observer<BannerListVo>() {
//            @Override
//            public void onChanged(@Nullable BannerListVo headAdList) {
//                if (headAdList != null) {
//                    setBannerData(headAdList);
//                }
//            }
//        });
//        mViewModel.getWorkData().observe(this, new Observer<WorksListVo>() {
//            @Override
//            public void onChanged(@Nullable WorksListVo worksListHotObject) {
//                if (worksListHotObject == null) {
//                    return;
//                }
//                lastId = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).tid;
//                utime = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).utime;
//                if (isRefresh) {
//                    newItems.addAll(worksListHotObject.data.content);
//                    oldItems.clear();
//                    oldItems.addAll(newItems);
//                    notifyDataSetChanged();
//                    mSwipeRefreshLayout.setRefreshing(false);
////                    mRecyclerView.refreshComplete();
//                } else {
//                    setData(worksListHotObject.data.content);
//                }
//
//            }
//        });
        mViewModel.getWorkMoreData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable WorksListVo worksListHotObject) {
                lastId = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).tid;
                utime = worksListHotObject.data.content.get(worksListHotObject.data.content.size() - 1).utime;
                setData(worksListHotObject.data.content);
            }
        });


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
        mViewModel.getWorkMoreData("", lastId, utime);
    }

    @Override
    protected void onStateRefresh() {

    }

    private void getCorrectData() {
        mViewModel.getWorkData("80", "20");
    }

    private void getAdData() {
        mViewModel.getBannerData("1", "4", "109", "", null);
    }

}
