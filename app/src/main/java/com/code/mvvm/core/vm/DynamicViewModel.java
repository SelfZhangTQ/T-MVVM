package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.core.data.source.DynamicRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * @author：tqzhang on 18/8/13 15:21
 */
public class DynamicViewModel extends AbsViewModel<DynamicRepository> {

    private MutableLiveData<DynamicListVo> mDynamicListData;

    public DynamicViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<DynamicListVo> getDynamicList() {
        if (mDynamicListData == null) {
            mDynamicListData = new MutableLiveData<>();
        }
        return mDynamicListData;
    }

    public void getDynamicList(String token, String lastId) {
        checkNotNull(token);
        mRepository.loadDynamicList(Constants.PAGE_RN, token, lastId, new CallBack<DynamicListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(DynamicListVo dynamicListVo) {
                mDynamicListData.postValue(dynamicListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });

    }
}
