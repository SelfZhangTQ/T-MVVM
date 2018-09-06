package com.code.mvvm.core.view.course;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.CourseViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;

/**
 * @author：tqzhang on 18/5/2 19:40
 */
public class CourseListFragment extends BaseListFragment<CourseViewModel> {
    private String mCatalogId;

    public static CourseListFragment newInstance() {
        return new CourseListFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        if (getArguments() != null) {
            mCatalogId = getArguments().getString("f_catalog_id", null);
        }
    }

    @Override
    protected void dataObserver() {
        mViewModel.getCourseList().observe(this, courseListVo -> {
            if (courseListVo != null && courseListVo.data != null) {
                lastId = courseListVo.data.get(courseListVo.data.size() - 1).courseid;
                setData(courseListVo.data);
            }

        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return oldItems.get(position) instanceof FootVo
                        || oldItems.get(position) instanceof HeaderVo ?
                        2 : 1;
            }
        });
        return layoutManager;
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getCourseListAdapter(getActivity());
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

    public void getNetWorkData() {
        mViewModel.getCourseList(mCatalogId, lastId);
    }
}
