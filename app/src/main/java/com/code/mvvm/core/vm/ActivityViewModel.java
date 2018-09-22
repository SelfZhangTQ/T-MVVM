package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.source.ActivityRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * @author：tqzhang on 18/7/27 15:23
 */
public class ActivityViewModel extends AbsViewModel<ActivityRepository> {

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

    public void getActivityList(String id) {
        checkNotNull(id);
        mRepository.loadActivityList(id, Constants.PAGE_RN, new CallBack<ActivityListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(ActivityListVo activityListVo) {
                activityData.postValue(activityListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });

    }
}
