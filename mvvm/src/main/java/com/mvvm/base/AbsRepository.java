package com.mvvm.base;



import android.arch.lifecycle.MutableLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public abstract class AbsRepository {

    private CompositeDisposable mCompositeDisposable;

    public MutableLiveData<String> loadState;


    public AbsRepository() {
        loadState = new MutableLiveData<>();
    }

    protected void postState(String state) {
        if (loadState != null) {
            loadState.postValue(state);
        }

    }


    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }
}
