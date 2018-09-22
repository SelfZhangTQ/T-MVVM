package com.code.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.vm.BaseViewModel;
import com.code.mvvm.stateview.ErrorState;
import com.code.mvvm.stateview.LoadingState;
import com.code.mvvm.util.TUtil;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：tqzhang on 18/8/10 11:40
 */
public abstract class AbsLifecycleActivity<T extends BaseViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsLifecycleActivity() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        mViewModel.loadState.observe(this, observer);
        dataObserver();
    }


    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

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
