package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/26 16:18
 */
public class ActivityRepository extends BaseRepository {

    public void loadActivityList(String lastId, String rn) {
        addDisposable(apiService.getActivityList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ActivityListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_ACTIVITY_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ActivityListVo activityListVo) {
                        sendData(Constants.EVENT_KEY_ACTIVITY, activityListVo);
                        showPageState(Constants.EVENT_KEY_ACTIVITY_STATE, StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_ACTIVITY_STATE, StateConstants.ERROR_STATE);
                    }
                }));


    }

}
