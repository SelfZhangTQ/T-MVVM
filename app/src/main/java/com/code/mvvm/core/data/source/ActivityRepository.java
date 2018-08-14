package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/26 16:18
 */
public class ActivityRepository extends BaseRepository {



    public ActivityRepository() {
        super();
    }

    public void loadActivityList(String lastId, String rn, final OnResultCallBack listener) {
        apiService.getActivityList(lastId, rn)
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.activity.ActivityListVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.activity.ActivityListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.activity.ActivityListVo activityListObject) {
                        listener.onNext(activityListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });


    }

}
