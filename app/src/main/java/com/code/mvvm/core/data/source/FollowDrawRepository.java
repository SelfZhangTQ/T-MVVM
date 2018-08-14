package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/31 16:06
 */
public class FollowDrawRepository extends BaseRepository {
    public void loadFollowDrawList(String maintypeid, String lastid, String rn, final OnResultCallBack<FollowDrawRecommendVo> listener) {
        apiService.getollowDrawList(maintypeid, lastid, rn)
                .compose(RxSchedulers.<FollowDrawRecommendVo>io_main())
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
                });
    }

    public void loadFollowDrawRemList(String lastid, String rn, final OnResultCallBack<FollowDrawRecommendVo> listener) {
        apiService.getFollowDrawRemList(lastid, rn)
                .compose(RxSchedulers.<FollowDrawRecommendVo>io_main())
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
                });
    }

    public void loadFollowDrawType(final OnResultCallBack<FollowDrawTypeVo> listener) {
        apiService.getFollowDrawType()
                .compose(RxSchedulers.<FollowDrawTypeVo>io_main())
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
                });
    }
}
