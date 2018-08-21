package com.code.mvvm.core.view.course;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.vm.CourseViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.HeaderVo;

/**
 * @author：tqzhang  on 18/5/2 19:40
 */
public class CourseRecommendFragment extends BaseListFragment<CourseViewModel> {
    public static CourseRecommendFragment newInstance() {
        return new CourseRecommendFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getCourseRemList().observe(this, new Observer<CourseRemVo>() {
            @Override
            public void onChanged(@Nullable CourseRemVo courseRemVo) {
                if (courseRemVo != null) {
                    setData(courseRemVo);
                }

            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return oldItems.get(position) instanceof TypeVo
                        || oldItems.get(position) instanceof BannerListVo
                        || oldItems.get(position) instanceof HeaderVo ?
                        2 : 1;
            }
        });
        return layoutManager;
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getCourseRemAdapter(getActivity());
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
        mViewModel.getCourseRemList("20");
    }


    private void setData(CourseRemVo courseRemVo) {
        if (courseRemVo.data.top_adv != null) {
            setBannerData(new BannerListVo(courseRemVo.data.top_adv));
        }
        for (int i = 0; i < courseRemVo.data.course_recommend.size(); i++) {
            oldItems.add(new TypeVo(courseRemVo.data.course_recommend.get(i).f_catalog + "/" + courseRemVo.data.course_recommend.get(i).s_catalog));
            oldItems.addAll(courseRemVo.data.course_recommend.get(i).course_list);
        }
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }


}
