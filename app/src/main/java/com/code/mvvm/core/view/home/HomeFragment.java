package com.code.mvvm.core.view.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.data.pojo.home.HomeMergeVo;
import com.code.mvvm.core.vm.HomeViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;


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
        mViewModel.getMergeData().observe(this, homeMergeVo -> {
            if (homeMergeVo != null) {
                addItems(homeMergeVo);
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
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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
        newItems.add(new TypeVo(getResources().getString(R.string.recommend_live_type)));
        if (homeMergeVo.homeListVo.data.live_recommend.size() > 0) {
            newItems.addAll(homeMergeVo.homeListVo.data.live_recommend);
        }
        newItems.add(new TypeVo(getResources().getString(R.string.recommend_video_type)));
        if (homeMergeVo.homeListVo.data.course.size() > 0) {
            newItems.addAll(homeMergeVo.homeListVo.data.course);
        }
        newItems.add(new TypeVo(getResources().getString(R.string.recommend_book_type)));
        if (homeMergeVo.homeListVo.data.publishingbook.size() > 0) {
            newItems.add(new BookList(homeMergeVo.homeListVo.data.publishingbook));
        }
        newItems.add(new TypeVo(getResources().getString(R.string.special_tab_name)));
        if (homeMergeVo.homeListVo.data.matreialsubject.size() > 0) {
            newItems.addAll(homeMergeVo.homeListVo.data.matreialsubject);
        }
        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems, true);
        newItems.clear();

    }

}
