package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/8/2 10:52
 */
public class QaRepository extends BaseRepository {

    public static String EVENT_KEY_QA = null;

    public QaRepository() {
        if (EVENT_KEY_QA == null) {
            EVENT_KEY_QA = StringUtil.getEventKey();
        }
    }

    public void loadQAList(String lastId) {
        addDisposable(apiService.getQAList(lastId, Constants.PAGE_RN)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<QaListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(QaListVo qaListVo) {
                        postData(EVENT_KEY_QA, qaListVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg, int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }
}
