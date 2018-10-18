package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/8/2 13:36
 */
public class LiveRepository extends BaseRepository {

    public void loadLiveList(String mCatalogId, String id, String rn) {
        addDisposable(apiService.getLiveList(mCatalogId, id, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveListVo>() {

                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_LIVE_LIST_STATE, mCatalogId, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        sendData(Constants.EVENT_KEY_LIVE_LIST, mCatalogId, liveListVo);
                        showPageState(Constants.EVENT_KEY_LIVE_LIST_STATE, mCatalogId, StateConstants.SUCCESS_STATE);


                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_LIVE_LIST_STATE, mCatalogId, StateConstants.ERROR_STATE);

                    }
                }));
    }


    public void loadLiveRemList(String id, String rn) {
        addDisposable(apiService.getLiveRem(id, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_LIVE_RED_STATE, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        sendData(Constants.EVENT_KEY_LIVE_RED, liveListVo);
                        showPageState(Constants.EVENT_KEY_LIVE_RED_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_LIVE_RED_STATE, StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadLiveTypeData() {
        addDisposable(apiService.getLiveType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_LIVE_STATE, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveTypeVo liveTypeVo) {
                        sendData(Constants.EVENT_KEY_LIVE, liveTypeVo);
                        showPageState(Constants.EVENT_KEY_LIVE_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_LIVE_STATE, StateConstants.ERROR_STATE);

                    }
                }));

    }
}
