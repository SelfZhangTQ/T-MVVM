package com.code.mvvm.core.view.followdraw;

import android.os.Bundle;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.core.data.source.FollowDrawRepository;
import com.code.mvvm.core.vm.FollowDrawViewModel;
import com.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang  on 18/7/2 14:24
 */
public class FollowDrawFragment extends BaseViewPagerFragment<FollowDrawViewModel> {

    private List<FollowDrawTypeVo.DataBean> titleName;

    public static FollowDrawFragment newInstance() {
        return new FollowDrawFragment();
    }


    @Override
    public void initView(Bundle state) {
        super.initView(state);
        titleName = new ArrayList<>();
        getTabData();
    }


    @Override
    protected void dataObserver() {
        registerSubscriber(FollowDrawRepository.EVENT_KEY_FD, FollowDrawTypeVo.class).observe(this, followDrawTypeVo -> {
            if (followDrawTypeVo != null) {
                setData(followDrawTypeVo);
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
        mViewModel.getFollowDrawTypeData();
    }

    public void setData(FollowDrawTypeVo followDrawTypeVo) {
        mArrTitles = new String[followDrawTypeVo.data.size() + 1];
        titleName.clear();
        FollowDrawTypeVo.DataBean dataBean = new FollowDrawTypeVo.DataBean();
        dataBean.maintypename = getResources().getString(R.string.recommend_tab_name);
        mArrTitles[0] = getResources().getString(R.string.recommend_tab_name);
        titleName.add(dataBean);
        for (int j = 0; j < followDrawTypeVo.data.size(); j++) {
            titleName.add(followDrawTypeVo.data.get(j));
            mArrTitles[j + 1] = (followDrawTypeVo.data.get(j).maintypename);
        }
        initFragment();
        setAdapter();
    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            if (i == 0) {
                FollowDrawRecommendFragment followDrawRecommendFragment = FollowDrawRecommendFragment.newInstance();
                mFragments.add(followDrawRecommendFragment);
            } else {
                FollowDrawListFragment followDrawListFragment = FollowDrawListFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("type_id", String.valueOf(titleName.get(i).maintypeid));
                followDrawListFragment.setArguments(bundle);
                mFragments.add(followDrawListFragment);
            }

        }
    }
}
