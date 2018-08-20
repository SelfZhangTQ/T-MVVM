package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.core.data.source.LiveRepository;

/**
 * @author：zhangtianqiu on 18/7/27 15:23
 */
public class LiveViewModel extends BaseViewModel<LiveRepository> {

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

    public void getLiveList(String fCatalogId, String id, String rn) {
        mRepository.loadLiveList(fCatalogId, id, rn, new OnResultCallBack<LiveListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveListVo liveListVo) {
                liveData.postValue(liveListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });

    }

    public void getLiveRemList(String id, String rn) {
        mRepository.loadLiveRemList(id, rn, new OnResultCallBack<LiveListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveListVo liveListVo) {
                liveRemData.postValue(liveListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });

    }

    public void getLiveTypeData() {
        mRepository.loadLiveTypeData(new OnResultCallBack<LiveTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(LiveTypeVo liveTypeVo) {
                liveTypeData.postValue(liveTypeVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }
}
