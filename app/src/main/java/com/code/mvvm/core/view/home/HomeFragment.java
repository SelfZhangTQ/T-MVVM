package com.code.mvvm.core.view.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.material.MaterialListVo;
import com.code.mvvm.core.vm.HomeViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;


/**
 * @author：tqzhang on 18/5/2 18:46
 */
public class HomeFragment extends BaseListFragment<HomeViewModel> {
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initView(final Bundle state) {
        super.initView(state);
        setTitle(getResources().getString(R.string.home_title_name));
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
            }

        });

        mViewModel.getBannerList().observe(this, new Observer<BannerListVo>() {
            @Override
            public void onChanged(@Nullable BannerListVo bannerListVo) {
                if (bannerListVo != null) {
                    setBannerData(bannerListVo);
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
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        getRemoteData();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (oldItems.get(position) instanceof MaterialListVo
                        || oldItems.get(position) instanceof CatagoryVo
                        || oldItems.get(position) instanceof TypeVo
                        || oldItems.get(position) instanceof BookList
                        || oldItems.get(position) instanceof BannerListVo
                        || oldItems.get(position) instanceof HeaderVo
                        || oldItems.get(position) instanceof FootVo) ? 2 : 1;
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
        mViewModel.getRequestMerge();
    }

    private void addItems(HomeListVo homeListVo) {
        newItems.add(new CatagoryVo("title"));
        newItems.add(new TypeVo("直播推荐"));
        if (homeListVo.data.live_recommend.size() > 0) {
            newItems.addAll(homeListVo.data.live_recommend);
        }
        newItems.add(new TypeVo("视频课程"));
        if (homeListVo.data.course.size() > 0) {
            newItems.addAll(homeListVo.data.course);
        }
        newItems.add(new TypeVo("图书推荐"));
        if (homeListVo.data.publishingbook.size() > 0) {
            newItems.add(new BookList(homeListVo.data.publishingbook));
        }
        newItems.add(new TypeVo("专题"));
        if (homeListVo.data.matreialsubject.size() > 0) {
            newItems.add(new MaterialListVo(homeListVo.data.matreialsubject));
        }
        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems,true);
        newItems.clear();
    }
}
