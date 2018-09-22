package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

/**
 * @author：tqzhang   on 18/7/31 16:06
 */
public class FollowDrawRepository extends BaseRepository {

    public void loadFollowDrawList(String mainTypeId, String lastId, String rn, final CallBack<FollowDrawRecommendVo> listener) {
        addSubscribe(apiService.getollowDrawList(mainTypeId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        listener.onNext(followDrawRecommendObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }

    public void loadFollowDrawRemList(String lastId, String rn, final CallBack<FollowDrawRecommendVo> listener) {
        addSubscribe(apiService.getFollowDrawRemList(lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<FollowDrawRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(FollowDrawRecommendVo followDrawRecommendObject) {
                        listener.onNext(followDrawRecommendObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }

    public void loadFollowDrawType(final CallBack<FollowDrawTypeVo> listener) {
        addSubscribe(apiService.getFollowDrawType()
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<FollowDrawTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(FollowDrawTypeVo followDrawTypeVo) {
                        listener.onNext(followDrawTypeVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }
}
