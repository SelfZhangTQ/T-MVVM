package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/8/2 13:36
 */
public class LiveRepository extends BaseRepository {

    public static String EVENT_KEY_LIVE_LIST = null;

    public static String EVENT_KEY_LIVE_RED = null;

    public static String EVENT_KEY_LIVE = null;

    public LiveRepository() {
        if (EVENT_KEY_LIVE_LIST == null) {
            EVENT_KEY_LIVE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_LIVE_RED == null) {
            EVENT_KEY_LIVE_RED = StringUtil.getEventKey();
        }
        if (EVENT_KEY_LIVE == null) {
            EVENT_KEY_LIVE = StringUtil.getEventKey();
        }
    }

    public void loadLiveList(String mCatalogId, String id, String rn) {
        addDisposable(apiService.getLiveList(mCatalogId, id, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveListVo>() {

                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        postData(EVENT_KEY_LIVE_LIST, liveListVo);
                        postState(StateConstants.SUCCESS_STATE);


                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }


    public void loadLiveRemList(String id, String rn) {
        addDisposable(apiService.getLiveRem(id, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        postData(EVENT_KEY_LIVE_RED, liveListVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadLiveTypeData() {
        addDisposable(apiService.getLiveType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<LiveTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(LiveTypeVo liveTypeVo) {
                        postData(EVENT_KEY_LIVE, liveTypeVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));

    }
}
