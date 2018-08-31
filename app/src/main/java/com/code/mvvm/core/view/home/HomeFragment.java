package com.code.mvvm.core.view.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.home.HomeMergeVo;
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
        mViewModel.getMergeData().observe(this, new Observer<HomeMergeVo>() {
            @Override
            public void onChanged(@Nullable HomeMergeVo homeMergeVo) {
                if (homeMergeVo != null) {
                    addItems(homeMergeVo);
                }
            }
        });
    }


    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNetWorkData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
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

    protected void getNetWorkData() {
        mViewModel.getRequestMerge();
    }

    private void addItems(HomeMergeVo homeMergeVo) {
        newItems.add(homeMergeVo.bannerListVo);
        newItems.add(new CatagoryVo("title"));
        newItems.add(new TypeVo("直播推荐"));
        if (homeMergeVo.homeListVo.data.live_recommend.size() > 0) {
            newItems.addAll(homeMergeVo.homeListVo.data.live_recommend);
        }
        newItems.add(new TypeVo("视频课程"));
        if (homeMergeVo.homeListVo.data.course.size() > 0) {
            newItems.addAll(homeMergeVo.homeListVo.data.course);
        }
        newItems.add(new TypeVo("图书推荐"));
        if (homeMergeVo.homeListVo.data.publishingbook.size() > 0) {
            newItems.add(new BookList(homeMergeVo.homeListVo.data.publishingbook));
        }
        newItems.add(new TypeVo("专题"));
        if (homeMergeVo.homeListVo.data.matreialsubject.size() > 0) {
            newItems.add(new MaterialListVo(homeMergeVo.homeListVo.data.matreialsubject));
        }
        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems, true);
        newItems.clear();
    }
}
