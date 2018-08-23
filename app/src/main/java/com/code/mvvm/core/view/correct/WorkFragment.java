package com.code.mvvm.core.view.correct;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.vm.WorkViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/5/2 19:30
 */
public class WorkFragment extends BaseListFragment<WorkViewModel> {
    private String uTime;

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        setTitle(getResources().getString(R.string.work_title_name));
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
                lastId = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                uTime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                setData(worksListVo.data.content);
            }
        });
        mViewModel.getWorkMoreData().observe(this, new Observer<WorksListVo>() {
            @Override
            public void onChanged(@Nullable final WorksListVo worksListVo) {
                if (worksListVo == null) {
                    return;
                }
                lastId = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                uTime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                setData(worksListVo.data.content);
            }
        });
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
        getNetWorkData();
        mViewModel.getRequestMerge();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
        mViewModel.getRequestMerge();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getWorkMoreData("", lastId, uTime, "20");
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
        mViewModel.getRequestMerge();
    }
    private void getNetWorkData() {
        mViewModel.getBannerData("1", "4", "109", "", null);
        mViewModel.getWorkData("80", "20");
    }
}
