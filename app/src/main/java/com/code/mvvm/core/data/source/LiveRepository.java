package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

/**
 * @author：tqzhang on 18/8/2 13:36
 */
public class LiveRepository extends BaseRepository {

    public void loadLiveList(String mCatalogId, String id, String rn, final CallBack<LiveListVo> onResultCallBack) {
        addSubscribe(apiService.getLiveList(mCatalogId, id, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<LiveListVo>() {

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        onResultCallBack.onNext(liveListVo);

                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }));
    }


    public void loadLiveRemList(String id, String rn, final CallBack<LiveListVo> onResultCallBack) {
        addSubscribe(apiService.getLiveRem(id, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<LiveListVo>() {

                    @Override
                    public void onSuccess(LiveListVo liveListVo) {
                        onResultCallBack.onNext(liveListVo);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }));
    }

    public void loadLiveTypeData(final CallBack<LiveTypeVo> listener) {
        addSubscribe(apiService.getLiveType()
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<LiveTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(LiveTypeVo liveTypeVo) {
                        listener.onNext(liveTypeVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));

    }
}
