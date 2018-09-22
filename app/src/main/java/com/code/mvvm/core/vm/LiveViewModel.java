package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.core.data.source.LiveRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/27 15:23
 */
public class LiveViewModel extends AbsViewModel<LiveRepository> {

    private MutableLiveData<LiveTypeVo> liveTypeData;

    private MutableLiveData<LiveListVo> liveData;

    private MutableLiveData<LiveListVo> liveRemData;

    public LiveViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<LiveTypeVo> getLiveType() {
        if (liveTypeData == null) {
            liveTypeData = new MutableLiveData<>();
        }
        return liveTypeData;
    }

    public LiveData<LiveListVo> getLiveList() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public LiveData<LiveListVo> getLiveRemList() {
        if (liveRemData == null) {
            liveRemData = new MutableLiveData<>();
        }
        return liveRemData;
    }

    public void getLiveList(String fCatalogId, String id) {
        mRepository.loadLiveList(fCatalogId, id, Constants.PAGE_RN, new CallBack<LiveListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveListVo liveListVo) {
                liveData.postValue(liveListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });

    }

    public void getLiveRemList(String id) {
        mRepository.loadLiveRemList(id, Constants.PAGE_RN, new CallBack<LiveListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveListVo liveListVo) {
                liveRemData.postValue(liveListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });

    }

    public void getLiveTypeData() {
        mRepository.loadLiveTypeData(new CallBack<LiveTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveTypeVo liveTypeVo) {
                liveTypeData.postValue(liveTypeVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }
}
