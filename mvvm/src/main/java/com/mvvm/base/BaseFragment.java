package com.mvvm.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：tqzhang on 18/3/12 19:25
 */
public abstract class BaseFragment extends Fragment {
    private View rootView;

    protected FragmentActivity activity;

    protected LoadManager loadManager;

    protected boolean mIsFirstVisible = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        rootView = inflater.inflate(getLayoutResId(), null, false);
        View contentLayout = rootView.findViewById(getContentResId());
        if (contentLayout != null) {
            loadManager = new LoadManager.Builder()
                    .setViewParams(contentLayout == null ? rootView : contentLayout)
                    .setListener(new BaseStateControl.OnRefreshListener() {
                        @Override
                        public void onRefresh(View v) {
                            onStateRefresh();
                        }
                    })
                    .build();
        }
        initView(state);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isVis = isHidden() || getUserVisibleHint();
        if (isVis && mIsFirstVisible) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }


    /**
     * @return
     */
    public abstract int getLayoutResId();


    protected int getContentResId() {

        return -1;
    }

    /**
     * 初始化views
     *
     * @param state
     */
    public abstract void initView(Bundle state);

    /**
     *
     */
    protected abstract void onStateRefresh();


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (mIsFirstVisible && isResumed()) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.activity = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int id) {
        return (T) rootView.findViewById(id);
    }


}
