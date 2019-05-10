package com.code.mvvm.core.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.code.mvvm.core.data.source.QaRepository;
import com.mvvm.base.AbsViewModel;

/**
 * @author：tqzhang  on 18/8/2 10:53
 */
public class QaViewModel extends AbsViewModel<QaRepository> {


    public QaViewModel(@NonNull Application application) {
        super(application);
    }

    public void getQAList(String lastId) {
        mRepository.loadQAList(lastId);
    }
}
