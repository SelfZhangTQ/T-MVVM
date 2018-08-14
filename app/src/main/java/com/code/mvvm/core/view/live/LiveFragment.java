package com.code.mvvm.core.view.live;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.core.viewmodel.LiveViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/30 11:13
 */
public class LiveFragment extends BaseViewPagerFragment<LiveViewModel> {
    private List<LiveTypeVo.DataBean.SCatalogBean> titleName;


    public static LiveFragment newInstance() {
        return new LiveFragment();
    }


    @Override
    public void initView(Bundle state) {
        super.initView(state);
        titleName = new ArrayList<>();
        mViewModel.getLiveType().observe(this, new Observer<LiveTypeVo>() {
            @Override
            public void onChanged(@Nullable LiveTypeVo livingTypeObject) {
                setData(livingTypeObject);
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
    protected LiveViewModel createViewModelProviders() {
        return VMProviders(this, LiveViewModel.class);
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
        mViewModel.getLiveTypeData();

    }

    private void setData(LiveTypeVo lessonTypeObject) {
        mArrTitles = new String[lessonTypeObject.data.s_catalog.size()];
        titleName.clear();
        for (int j = 0; j < lessonTypeObject.data.s_catalog.size(); j++) {
            titleName.add(lessonTypeObject.data.s_catalog.get(j));
            mArrTitles[j] = (lessonTypeObject.data.s_catalog.get(j).name);
        }
        initFragment();
        setAdapter();
    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            if (i == 0) {
                LiveRecommendFragment liveRecommendFragment = LiveRecommendFragment.newInstance();
                mFragments.add(liveRecommendFragment);
            } else {
                LiveListFragment liveListFragment = LiveListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("f_catalog_id", titleName.get(i).id + "");
                liveListFragment.setArguments(bundle);
                mFragments.add(liveListFragment);
            }

        }
    }
}
