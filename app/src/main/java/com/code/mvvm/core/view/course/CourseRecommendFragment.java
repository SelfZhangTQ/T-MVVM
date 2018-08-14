package com.code.mvvm.core.view.course;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.viewmodel.CourseViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/6/30 18:36
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
            public void onChanged(@Nullable CourseRemVo lessonRemObject) {
                if (lessonRemObject != null) {
                    setData(lessonRemObject);
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
                return oldItems.get(position) instanceof TypeVo
                        || oldItems.get(position) instanceof BannerAdListVo
                        || oldItems.get(position) instanceof HeaderInfo ?
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


    private void setData(CourseRemVo lessonRemObject) {
        if (lessonRemObject.data.top_adv != null) {
            setBannerData(new BannerAdListVo(lessonRemObject.data.top_adv));
        }
        for (int i = 0; i < lessonRemObject.data.course_recommend.size(); i++) {
            oldItems.add(new TypeVo(lessonRemObject.data.course_recommend.get(i).f_catalog + "/" + lessonRemObject.data.course_recommend.get(i).s_catalog));
            oldItems.addAll(lessonRemObject.data.course_recommend.get(i).course_list);
        }
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }


}
