package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/8/13 15:21
 */
public class DynamicRepository extends BaseRepository {
    public void loadDynamicList(String rn, String token,String lastId, final OnResultCallBack<DynamicListVo> listener) {
        apiService.getDynamicList(rn, token,lastId)
                .compose(RxSchedulers.<DynamicListVo>io_main())
                .subscribe(new RxSubscriber<DynamicListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(DynamicListVo dynamicListVo) {
                        listener.onNext(dynamicListVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });

    }
}
