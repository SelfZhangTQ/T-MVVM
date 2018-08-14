package com.code.mvvm.core.view.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.material.MatreialListVo;
import com.code.mvvm.core.viewmodel.HomeViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.MultiTypeAdapter;


/**
 * @author：zhangtianqiu on 18/5/2 15:46
 */
public class HomeFragment extends BaseListFragment<HomeViewModel> {
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initView(final Bundle state) {
        super.initView(state);
        setTitle("首页");
    }

    @Override
    protected void dataObserver() {
        mViewModel.getHomeList().observe(this, new Observer<HomeListVo>() {
            @Override
            public void onChanged(@Nullable HomeListVo homeListVo) {
                if (homeListVo == null) {
                    return;
                }
                addItems(homeListVo);
                mRecyclerView.refreshComplete();
            }

        });

        mViewModel.getBannerList().observe(this, new Observer<BannerAdListVo>() {
            @Override
            public void onChanged(@Nullable BannerAdListVo bannerAdListVo) {
                if (bannerAdListVo != null) {
                    setBannerData(bannerAdListVo);
                }
            }
        });
    }


    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getRemoteData();
    }

    @Override
    protected HomeViewModel createViewModelProviders() {
        return VMProviders(this, HomeViewModel.class);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getRemoteData();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (oldItems.get(position) instanceof MatreialListVo
                        || oldItems.get(position) instanceof CatagoryVo
                        || oldItems.get(position) instanceof TypeVo
                        || oldItems.get(position) instanceof BookList
                        || oldItems.get(position) instanceof BannerAdListVo
                        || oldItems.get(position) instanceof HeaderInfo
                        || oldItems.get(position) instanceof FootInfo) ? 2 : 1;
            }
        });

        return layoutManager;
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getHomeAdapter(getActivity());
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getBannerData("1", "4", "109", "", null);
        mViewModel.loadHomeList("0");
        mViewModel.getRequestMerge();
    }

    private void addItems(HomeListVo studyObject) {
        if (!isRefresh) {
            oldItems.add(new CatagoryVo("title"));
            oldItems.add(new TypeVo("直播推荐"));
            if (studyObject.data.live_recommend.size() > 0) {
                oldItems.addAll(studyObject.data.live_recommend);
            }
            oldItems.add(new TypeVo("视频课程"));
            if (studyObject.data.course.size() > 0) {
                oldItems.addAll(studyObject.data.course);
            }
            oldItems.add(new TypeVo("图书推荐"));
            if (studyObject.data.publishingbook.size() > 0) {
                oldItems.add(new BookList(studyObject.data.publishingbook));
            }
            oldItems.add(new TypeVo("专题"));
            if (studyObject.data.matreialsubject.size() > 0) {
                oldItems.add(new MatreialListVo(studyObject.data.matreialsubject));
            }
            adapter.setItems(oldItems);
            notifyDataSetChanged();
        } else {
            newItems.add(new CatagoryVo("title"));
            newItems.add(new TypeVo("直播推荐"));
            if (studyObject.data.live_recommend.size() > 0) {
                newItems.addAll(studyObject.data.live_recommend);
            }
            newItems.add(new TypeVo("视频课程"));
            if (studyObject.data.course.size() > 0) {
                newItems.addAll(studyObject.data.course);
            }
            newItems.add(new TypeVo("图书推荐"));
            if (studyObject.data.publishingbook.size() > 0) {
                newItems.add(new BookList(studyObject.data.publishingbook));
            }
            newItems.add(new TypeVo("专题"));
            if (studyObject.data.matreialsubject.size() > 0) {
                newItems.add(new MatreialListVo(studyObject.data.matreialsubject));
            }
            refreshDataChanged();
        }


    }
}
