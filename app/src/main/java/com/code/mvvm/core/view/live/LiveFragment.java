package com.code.mvvm.core.view.live;

import android.os.Bundle;

import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.core.data.source.LiveRepository;
import com.code.mvvm.core.vm.LiveViewModel;
import com.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang  on 18/6/30 11:13
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
        getTabData();
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(LiveRepository.EVENT_KEY_LIVE, LiveTypeVo.class).observe(this, liveTypeVo -> {
            if (liveTypeVo!=null) {
                setData(liveTypeVo);
            }
        });
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getTabData();
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

    private void setData(LiveTypeVo liveTypeVo) {
        mArrTitles = new String[liveTypeVo.data.s_catalog.size()];
        titleName.clear();
        for (int j = 0; j < liveTypeVo.data.s_catalog.size(); j++) {
            titleName.add(liveTypeVo.data.s_catalog.get(j));
            mArrTitles[j] = (liveTypeVo.data.s_catalog.get(j).name);
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
                bundle.putString("type_id", String.valueOf(titleName.get(i).id));
                liveListFragment.setArguments(bundle);
                mFragments.add(liveListFragment);
            }

        }
    }
}
