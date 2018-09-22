package com.mvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.mvvm.util.TUtil;


/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {

    public MutableLiveData<String> loadState;

    public T mRepository;

    public AbsViewModel(@NonNull Application application) {
        super(application);
        loadState = new MutableLiveData<>();
        mRepository = TUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unSubscribe();
        }

    }
}
