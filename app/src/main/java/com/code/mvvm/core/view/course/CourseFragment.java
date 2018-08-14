package com.code.mvvm.core.view.course;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.core.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/30 11:13
 */
public class CourseFragment extends BaseViewPagerFragment<CourseViewModel> {
    private List<CourseTypeVo.DataBean> titleName;

    public static CourseFragment newInstance() {
        return new CourseFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        setTitle("课程");
        titleName = new ArrayList<>();
        mViewModel.getCourseType().observe(this, new Observer<CourseTypeVo>() {
            @Override
            public void onChanged(@Nullable CourseTypeVo lessonTypeObject) {
                setData(lessonTypeObject);
            }
        });
        getTabData();
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getTabData();
    }

    @Override
    protected CourseViewModel createViewModelProviders() {
        return VMProviders(this, CourseViewModel.class);
    }

    @Override
    protected String[] createPageTitle() {
        return mArrTitles;
    }

    @Override
    protected List<BaseFragment> createFragments() {
        return mFragments;
    }

    private void getTabData() {
        mViewModel.getCourseTypeData();

    }


    private void setData(CourseTypeVo lessonTypeObject) {
        mArrTitles = new String[lessonTypeObject.data.size() + 1];
        titleName.clear();
        CourseTypeVo.DataBean dataBean = new CourseTypeVo.DataBean();
        dataBean.name = ("推荐");
        mArrTitles[0] = "推荐";
        titleName.add(dataBean);
        for (int j = 0; j < lessonTypeObject.data.size(); j++) {
            titleName.add(lessonTypeObject.data.get(j));
            mArrTitles[j + 1] = (lessonTypeObject.data.get(j).name);
        }
        initFragment();
        setAdapter();
    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            if (i == 0) {
                CourseRecommendFragment courseRecommendFragment = CourseRecommendFragment.newInstance();
                mFragments.add(courseRecommendFragment);
            } else {
                CourseListFragment courseListFragment = CourseListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("f_catalog_id", titleName.get(i).id);
                bundle.putSerializable("s_catalog", titleName.get(i).s_catalog);
                courseListFragment.setArguments(bundle);
                mFragments.add(courseListFragment);
            }

        }
    }
}
