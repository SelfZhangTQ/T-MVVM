package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

/**
 * @author：tqzhang on 18/8/13 15:21
 */
public class DynamicRepository extends BaseRepository {

    public void loadDynamicList(String rn, String token, String lastId, final CallBack<DynamicListVo> listener) {
        addSubscribe(apiService.getDynamicList(rn, token, lastId)
                .compose(RxSchedulers.io_main())
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
                }));

    }
}
