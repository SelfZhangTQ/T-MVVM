package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/8/2 10:52
 */
public class QaRepository extends BaseRepository {

    public void loadQAList(String lastId, String rn) {
        addDisposable(apiService.getQAList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<QaListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_QA_STATE, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(QaListVo qaListVo) {
                        sendData(Constants.EVENT_KEY_QA, qaListVo);
                        showPageState(Constants.EVENT_KEY_QA_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_QA_STATE, StateConstants.ERROR_STATE);

                    }
                }));
    }
}
