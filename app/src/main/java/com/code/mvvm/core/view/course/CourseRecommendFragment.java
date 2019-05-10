package com.code.mvvm.core.view.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.source.CourseRepository;
import com.code.mvvm.core.vm.CourseViewModel;
import com.code.mvvm.util.AdapterPool;

/**
 * @author：tqzhang on 18/5/2 19:40
 */
public class CourseRecommendFragment extends BaseListFragment<CourseViewModel> implements OnItemClickListener {

    public static CourseRecommendFragment newInstance() {
        return new CourseRecommendFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        refreshHelper.setEnableLoadMore(false);
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_RED, CourseRemVo.class)
                .observe(this, courseRemVo -> {
                    if (courseRemVo != null) {
                        setRemoteData(courseRemVo);
                    }

                });
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mItems.get(position) instanceof TypeVo
                        || mItems.get(position) instanceof BannerListVo?
                        2 : 1;
            }
        });
        return layoutManager;
    }

    @Override
    protected DelegateAdapter createAdapter() {
        DelegateAdapter adapter = AdapterPool.newInstance().getCourseRemAdapter(activity)
                .setOnItemClickListener(this).build();
        return adapter;
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getCourseRemList();
    }

    private void setRemoteData(CourseRemVo courseRemVo) {
        mItems.clear();
        if (courseRemVo.data.top_adv != null) {
            mItems.add(new BannerListVo(courseRemVo.data.top_adv));
        }
        for (int i = 0; i < courseRemVo.data.course_recommend.size(); i++) {
            mItems.add(new TypeVo(courseRemVo.data.course_recommend.get(i).f_catalog + "/" + courseRemVo.data.course_recommend.get(i).s_catalog));
            mItems.addAll(courseRemVo.data.course_recommend.get(i).course_list);
        }
        setData();
    }


    @Override
    public void onItemClick(View view, int i, Object object) {
        if (object != null) {
            if (object instanceof CourseInfoVo) {
                Intent intent = new Intent(activity, VideoDetailsActivity.class);
                intent.putExtra(Constants.COURSE_ID, ((CourseInfoVo) object).courseid);
                activity.startActivity(intent);
            }

        }
    }
}
