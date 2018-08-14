package com.code.mvvm.core.view.correct;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.viewmodel.WorkViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang
 */
public class WorkFragment extends BaseListFragment<WorkViewModel> {
    private String utime;

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        setTitle("作品");
    }

    @Override
    protected void dataObserver() {
        mViewModel.getBannerData().observe(this, new Observer<BannerListVo>() {
            @Override
            public void onChanged(@Nullable BannerListVo bannerListVo) {
                if (bannerListVo != null) {
                    setBannerData(bannerListVo);
                }
            }
        });
        mViewModel.getWorkData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable WorksListVo worksListVo) {
                if (worksListVo == null) {
                    return;
                }
                lastid = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                utime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                if (isRefresh) {
                    newItems.addAll(worksListVo.data.content);
                    oldItems.clear();
                    oldItems.addAll(newItems);
                    notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                } else {
                    setData(worksListVo.data.content);
                }

            }
        });
        mViewModel.getWorkMoreData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable WorksListVo worksListVo) {
                lastid = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                utime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                setData(worksListVo.data.content);
            }
        });
    }

    @Override
    protected WorkViewModel createViewModelProviders() {
        return VMProviders(this, WorkViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getWorkAdapter(getActivity());
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getAdData();
        getWorkData();
        mViewModel.getRequestMerge();
    }

    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getAdData();
        getWorkData();
        mViewModel.getRequestMerge();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getWorkMoreData("", lastid, utime, "20");
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getAdData();
        getWorkData();
        mViewModel.getRequestMerge();
    }

    private void getWorkData() {
        mViewModel.getWorkData("80", "20");
    }

    private void getAdData() {
        mViewModel.getBannerData("1", "4", "109", "", null);
    }

}
