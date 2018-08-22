package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.network.rx.RxSchedulers;
import com.code.mvvm.network.rx.RxSubscriber;

/**
 * @author：tqzhang  on 18/8/2 13:36
 */
public class LiveRepository extends BaseRepository {
    public void loadLiveList(String mCatalogId, String id, String rn, final OnResultCallBack<LiveListVo> onResultCallBack) {
        addSubscribe(apiService.getLiveList(mCatalogId, id, rn)
                .compose(RxSchedulers.<LiveListVo>io_main())
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

    public void loadLiveRemList(String id, String rn, final OnResultCallBack<LiveListVo> onResultCallBack) {
        addSubscribe(apiService.getLiveRem(id, rn)
                .compose(RxSchedulers.<LiveListVo>io_main())
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

    public void loadLiveTypeData(final OnResultCallBack<LiveTypeVo> listener) {
        addSubscribe(apiService.getLiveType()
           .compose(RxSchedulers.<LiveTypeVo>io_main())
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
