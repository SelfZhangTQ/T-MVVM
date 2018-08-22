package com.code.mvvm.core.data.source;


import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.network.rx.RxSchedulers;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author：tqzhang on 18/7/26 16:18
 */
public class HomeRepository extends BaseRepository {
    public HomeRepository() {
        super();
    }

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


    public void loadRequestMerge(final OnResultCallBack listener) {
        addSubscribe(Observable.concatDelayError(mBannerObservable, mHomeListObservable)
                .compose(RxSchedulers.<Object>io_main())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        listener.onNext(o);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError(throwable.getMessage());
                    }
                }));
    }

}
