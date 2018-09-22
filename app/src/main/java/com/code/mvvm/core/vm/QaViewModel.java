package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.data.source.QaRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang  on 18/8/2 10:53
 */
public class QaViewModel extends AbsViewModel<QaRepository> {

    private MutableLiveData<QaListVo> mQAData;

    public QaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<QaListVo> getQAList() {
        if (mQAData == null) {
            mQAData = new MutableLiveData<>();
        }
        return mQAData;
    }

    public void getQAList(String lastId, String rn) {
        mRepository.loadQAList(lastId, rn, new CallBack<QaListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(QaListVo articleObject) {
                mQAData.postValue(articleObject);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }
}
