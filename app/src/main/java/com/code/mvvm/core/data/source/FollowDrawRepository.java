package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/31 16:06
 */
public class FollowDrawRepository extends BaseRepository {

    public void loadFollowDrawList(String mainTypeId, String lastId, String rn) {
        addDisposable(apiService.getollowDrawList(mainTypeId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_FD_LIST_STATE, mainTypeId, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        sendData(Constants.EVENT_KEY_FD_LIST, mainTypeId, followDrawRecommendObject);
                        showPageState(Constants.EVENT_KEY_FD_LIST_STATE, mainTypeId, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_FD_LIST_STATE, mainTypeId, StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadFollowDrawRemList(String lastId, String rn) {
        addDisposable(apiService.getFollowDrawRemList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_FD_RED_STATE, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        sendData(Constants.EVENT_KEY_FD_RED, followDrawRecommendObject);
                        showPageState(Constants.EVENT_KEY_FD_RED_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_FD_RED_STATE, StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadFollowDrawType() {
        addDisposable(apiService.getFollowDrawType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<FollowDrawTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_FD_STATE, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(FollowDrawTypeVo followDrawTypeVo) {
                        sendData(Constants.EVENT_KEY_FD, followDrawTypeVo);
                        showPageState(Constants.EVENT_KEY_FD_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_FD, StateConstants.ERROR_STATE);

                    }
                }));
    }
}
