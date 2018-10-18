package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/8/13 15:21
 */
public class DynamicRepository extends BaseRepository {

    public void loadDynamicList(String rn, String token, String lastId) {
        addDisposable(apiService.getDynamicList(rn, token, lastId)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<DynamicListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_DYNAMIC_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(DynamicListVo dynamicListVo) {
                        if (dynamicListVo.data != null) {
                            sendData(Constants.EVENT_KEY_DYNAMIC, dynamicListVo);
                            showPageState(Constants.EVENT_KEY_DYNAMIC_STATE, StateConstants.SUCCESS_STATE);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_DYNAMIC_STATE, StateConstants.ERROR_STATE);
                    }
                }));

    }
}
