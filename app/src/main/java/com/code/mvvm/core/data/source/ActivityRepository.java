package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/26 16:18
 */
public class ActivityRepository extends BaseRepository {
    public static String EVENT_KEY_ACTIVITY = null;


    public ActivityRepository() {
        if (EVENT_KEY_ACTIVITY==null) {
            EVENT_KEY_ACTIVITY = StringUtil.getEventKey();
        }
    }

    public void loadActivityList(String lastId, String rn) {
        addDisposable(apiService.getActivityList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ActivityListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ActivityListVo activityListVo) {
                        postData(EVENT_KEY_ACTIVITY, activityListVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {
                        postState(StateConstants.ERROR_STATE);
                    }
                }));
    }

}
