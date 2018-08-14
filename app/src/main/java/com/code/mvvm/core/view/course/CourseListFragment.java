package com.code.mvvm.core.view.course;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.viewmodel.CourseViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/6/30 18:36
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
        mViewModel.getCourseList().observe(this, new Observer<CourseListVo>() {
            @Override
            public void onChanged(@Nullable CourseListVo courseListVo) {
                if (courseListVo != null && courseListVo.data.size() > 0) {
                    lastid = courseListVo.data.get(courseListVo.data.size() - 1).courseid;
                    setData(courseListVo.data);
                }

            }
        });
    }

    @Override
    protected CourseViewModel createViewModelProviders() {
        return ViewModelProviders.of(this).get(CourseViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return oldItems.get(position) instanceof FootInfo
                        || oldItems.get(position) instanceof HeaderInfo ?
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
        getRemoteData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getRemoteData();
    }

    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getCourseList(mCatalogId, lastid, "20");
    }
}
