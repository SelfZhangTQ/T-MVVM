package com.code.mvvm.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/8/1 10:26
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;

    private List<String> mTitles;


    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> lists, List<String> titles) {
        super(fm);
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragments.addAll(lists);
        mTitles.addAll(titles);
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
