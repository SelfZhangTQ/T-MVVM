package com.code.mvvm.core.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.basiclibrary.utils.InstanceUtil;
import com.code.mvvm.core.data.BaseRepository;

/**
 * @author：zhangtianqiu on 18/7/26 16:15
 */
public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {
    public MutableLiveData<String> loadState;

    public T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        loadState = new MutableLiveData<>();

        mRepository = InstanceUtil.getNewInstance(this, 0);
    }


}
