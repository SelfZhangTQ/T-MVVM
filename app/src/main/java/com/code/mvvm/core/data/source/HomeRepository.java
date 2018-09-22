package com.code.mvvm.core.data.source;


import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

import rx.Observable;

/**
 * @author：tqzhang on 18/7/26 16:18
 */
public class HomeRepository extends BaseRepository {

    private Observable<HomeListVo> mHomeListObservable;

    private Observable<BannerListVo> mBannerObservable;

    public void loadHomeData(String id) {
        mHomeListObservable = apiService.getHomeData(id);
    }

    public void loadBannerData(String posType,
                               String fCatalogId,
                               String sCatalogId,
                               String tCatalogId,
                               String province) {
        mBannerObservable = apiService.getBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);
    }


    public void loadRequestMerge(final CallBack listener) {
        addSubscribe(Observable.concatDelayError(mBannerObservable, mHomeListObservable)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<Object>() {

                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(Object o) {
                        listener.onNext(o);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }

}
