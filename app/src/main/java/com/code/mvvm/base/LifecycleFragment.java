package com.code.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.viewmodel.BaseViewModel;
import com.code.mvvm.stateview.ErrorState;
import com.code.mvvm.stateview.LoadingState;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：zhangtianqiu on 18/7/10 16:20
 */
public abstract class LifecycleFragment<T extends BaseViewModel> extends BaseFragment {

    protected T mViewModel;

    @Override
    public void initView(Bundle state) {
        mViewModel = createViewModelProviders();
        if (mViewModel != null) {
            mViewModel.loadState.observe(this, observer);
        }
        dataObserver();
    }

    /**
     * @return
     */
    protected abstract T createViewModelProviders();


    protected <T extends ViewModel> T VMProviders(Fragment fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

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
                if (Constants.ERROR_STATE.equals(state)) {
                    showError(ErrorState.class, "2");
                } else if (Constants.NET_WORK_STATE.equals(state)) {
                    showError(ErrorState.class, "1");
                } else if (Constants.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (Constants.SUCCESS_STATE.equals(state)) {
                    showSuccess();
                }
            }
        }
    };

}
