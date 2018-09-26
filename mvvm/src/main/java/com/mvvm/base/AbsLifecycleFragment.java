package com.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mvvm.stateview.ErrorState;
import com.mvvm.stateview.LoadingState;
import com.mvvm.stateview.StateConstants;
import com.mvvm.util.TUtil;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：tqzhang on 18/7/10 16:20
 */
public abstract class AbsLifecycleFragment<T extends AbsViewModel> extends BaseFragment {

    protected T mViewModel;

    @Override
    public void initView(Bundle state) {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (null != mViewModel) {
            mViewModel.loadState.observe(this, observer);
            dataObserver();
        }
    }

    /**
     * create ViewModelProviders
     *
     * @return ViewModel
     */
    protected <T extends ViewModel> T VMProviders(BaseFragment fragment, @NonNull Class<T> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

    }

    @Override
    protected void onStateRefresh() {
        showLoading();
    }


    /**
     * 获取网络数据
     */
    protected void getRemoteData() {

    }

    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
        loadManager.showStateView(stateView, tag);
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showSuccess() {
        loadManager.showSuccess();
    }

    protected void showLoading() {
        loadManager.showStateView(LoadingState.class);
    }


    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (StateConstants.ERROR_STATE.equals(state)) {
                    showError(ErrorState.class, "2");
                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
                    showError(ErrorState.class, "1");
                } else if (StateConstants.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
                    showSuccess();
                }
            }
        }
    };

}
