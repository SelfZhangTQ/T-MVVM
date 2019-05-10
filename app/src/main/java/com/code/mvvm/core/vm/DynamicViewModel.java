package com.code.mvvm.core.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.source.DynamicRepository;
import com.mvvm.base.AbsViewModel;

/**
 * @author：tqzhang on 18/8/13 15:21
 */
public class DynamicViewModel extends AbsViewModel<DynamicRepository> {


    public DynamicViewModel(@NonNull Application application) {
        super(application);
    }


    public void getDynamicList(String token, String lastId) {
        mRepository.loadDynamicList(Constants.PAGE_RN, token, lastId);

    }
}
