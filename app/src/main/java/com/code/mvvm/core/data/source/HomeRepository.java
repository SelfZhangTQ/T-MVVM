package com.code.mvvm.core.data.source;


import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author：zhangtianqiu on 18/7/26 16:18
 */
public class HomeRepository extends BaseRepository {
    public HomeRepository() {
        super();
    }

    private Observable<HomeListVo> mHomeListObservable;
    private Observable<BannerListVo> mBannerObservable;

    public void loadHomeData(String id, final OnResultCallBack listener) {
        mHomeListObservable = apiService.getHomeData(id);
//        apiService.getHomeData(id).
//                compose(RxSchedulers.<HomeListVo>io_main()).
//                subscribe(new RxSubscriber<HomeListVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(HomeListVo studyObject) {
//                        listener.onNext(studyObject);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadBannerData(String posType,
                               String f_catalog_id,
                               String s_catalog_id,
                               String t_catalog_id,
                               String province, final OnResultCallBack listener) {
        mBannerObservable = apiService.getBannerData(posType, f_catalog_id, s_catalog_id, t_catalog_id, province);

//        apiService.getBannerData(posType, f_catalog_id, s_catalog_id, t_catalog_id, province).
//                compose(RxSchedulers.<BannerListVo>io_main()).
//                subscribe(new RxSubscriber<BannerListVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(BannerListVo headAdList) {
//                        listener.onNext(headAdList);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }


    public void loadRequestMerge(final OnResultCallBack listener) {
//        Observable.concat(mBannerObservable, mHomeListObservable)
//                .compose(RxSchedulers.<Object>io_main())
//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        listener.onNext(o);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        listener.onError(throwable.getMessage());
//                    }
//                });

        Observable.concatDelayError(mBannerObservable, mHomeListObservable)
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
                });
    }

}
