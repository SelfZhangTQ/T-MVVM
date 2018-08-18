package com.code.mvvm.core.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.source.ActivityRepository;

/**
 * @author：tqzhang on 18/7/27 15:23
 */
public class ActivityViewModel extends BaseViewModel<ActivityRepository> {

    private MutableLiveData<ActivityListVo> activityData;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ActivityListVo> getActivityList() {
        if (activityData == null) {
            activityData = new MutableLiveData<>();
        }
        return activityData;
    }

    public void getActivityList(String id, String rn) {
        mRepository.loadActivityList(id, rn, new OnResultCallBack<com.code.mvvm.core.data.pojo.activity.ActivityListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(com.code.mvvm.core.data.pojo.activity.ActivityListVo activityListVo) {
                activityData.postValue(activityListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });

    }
}
