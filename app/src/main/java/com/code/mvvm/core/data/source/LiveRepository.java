package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/8/2 13:36
 */
public class LiveRepository extends BaseRepository {
    public void loadLiveList(String f_catalog_id, String id, String rn, final OnResultCallBack<LiveListVo> onResultCallBack) {
        apiService.getLiveList(f_catalog_id, id, rn)
                .compose(RxSchedulers.<LiveListVo>io_main())
                .subscribe(new RxSubscriber<LiveListVo>() {

                    @Override
                    public void onSuccess(LiveListVo liveListObject) {
                        onResultCallBack.onNext(liveListObject);

                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }

    public void loadLiveRemList(String id, String rn, final OnResultCallBack<LiveListVo> onResultCallBack) {
        apiService.getLiveRem(id, rn)
                .compose(RxSchedulers.<LiveListVo>io_main())
                .subscribe(new RxSubscriber<LiveListVo>() {

                    @Override
                    public void onSuccess(LiveListVo liveListObject) {
                        onResultCallBack.onNext(liveListObject);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }

    public void loadLiveTypeData(final OnResultCallBack<LiveTypeVo> listener) {
   apiService.getLiveType()
           .compose(RxSchedulers.<LiveTypeVo>io_main())
           .subscribe(new RxSubscriber<LiveTypeVo>() {
               @Override
               protected void onNoNetWork() {
                   super.onNoNetWork();
                   listener.onNoNetWork();
               }

               @Override
               public void onSuccess(LiveTypeVo bookClassObject) {
                   listener.onNext(bookClassObject);
               }

               @Override
               public void onFailure(String msg) {
                   listener.onError(msg);
               }
           });

    }
}
