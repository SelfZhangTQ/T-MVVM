package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/31 16:06
 */
public class FollowDrawRepository extends BaseRepository {
    public static String EVENT_KEY_FD_LIST = null;
    public static String EVENT_KEY_FD_RED = null;
    public static String EVENT_KEY_FD = null;

    public FollowDrawRepository() {
        if (EVENT_KEY_FD_LIST == null) {
            EVENT_KEY_FD_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_FD_RED == null) {
            EVENT_KEY_FD_RED = StringUtil.getEventKey();
        }
        if (EVENT_KEY_FD == null) {
            EVENT_KEY_FD = StringUtil.getEventKey();
        }

    }

    public void loadFollowDrawList(String mainTypeId, String lastId, String rn) {
        addDisposable(apiService.getollowDrawList(mainTypeId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        postData(EVENT_KEY_FD_LIST, followDrawRecommendObject);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadFollowDrawRemList(String lastId, String rn) {
        addDisposable(apiService.getFollowDrawRemList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        postData(EVENT_KEY_FD_RED, followDrawRecommendObject);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadFollowDrawType() {
        addDisposable(apiService.getFollowDrawType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawTypeVo followDrawTypeVo) {
                        postData(EVENT_KEY_FD, followDrawTypeVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

                    }
                }));
    }
}
