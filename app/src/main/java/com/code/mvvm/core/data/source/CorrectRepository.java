package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.correct.CorrectDetailVo;
import com.code.mvvm.core.data.pojo.correct.CorrectRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.network.RxSubscriber;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author：zhangtianqiu on 18/7/31 15:32
 */
public class CorrectRepository extends BaseRepository {

    private Observable<WorksListVo> observable2;
    private Observable<BannerAdListVo> observable1;

    private Observable<CorrectDetailVo> mCorrectDetail;
    private Observable<CorrectRecommentVo> mCorrectRecomment;

    public void loadCorrectData(String corrected, String rn, final OnResultCallBack<WorksListVo> listener) {
        observable2 = apiService.getWorkData(corrected, rn);
        //        apiService.getWorkData(corrected, rn)
//                .compose(RxSchedulers.<WorksListHotObject>io_main())
//                .subscribe(new RxSubscriber<WorksListHotObject>() {
//
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//                    @Override
//                    public void onSuccess(WorksListHotObject worksListHotObject) {
//                        listener.onNext(worksListHotObject);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadCorrectMoreData(String corrected, String last_id, String utime, String rn, final OnResultCallBack<WorksListVo> listener) {
        apiService.getWorkMoreData(corrected, last_id, utime, rn)
                .compose(RxSchedulers.<WorksListVo>io_main())
                .subscribe(new RxSubscriber<WorksListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(WorksListVo worksListHotObject) {
                        listener.onNext(worksListHotObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadBannerData(String posType,
                               String f_catalog_id,
                               String s_catalog_id,
                               String t_catalog_id,
                               String province, final OnResultCallBack listener) {
        observable1 = apiService.getBannerData(posType, f_catalog_id, s_catalog_id, t_catalog_id, province);

        //        apiService.getBannerData(posType, f_catalog_id, s_catalog_id, t_catalog_id, province).
//                compose(RxSchedulers.<BannerAdListVo>io_main()).
//                subscribe(new RxSubscriber<BannerAdListVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(BannerAdListVo headAdList) {
//                        listener.onNext(headAdList);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadRequestMerge(final OnResultCallBack<Object> listener) {
        Observable.concat(observable1, observable2)
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

    public void loadCorrectDetailData(String correctId, final OnResultCallBack<CorrectDetailVo> listener) {

        mCorrectDetail = apiService.getWorkDetailData(correctId);
//        apiService.getWorkDetailData(correctId).
//                compose(RxSchedulers.<CorrectDetailVo>io_main()).
//                subscribe(new RxSubscriber<CorrectDetailVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(CorrectDetailVo correctDetailVo) {
//                        listener.onNext(correctDetailVo);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadCorrectRecommendData(String correctId, final OnResultCallBack<CorrectRecommentVo> listener) {
        mCorrectRecomment=apiService.getWorkRecommendData(correctId);
//        apiService.getWorkRecommendData(correctId).
//                compose(RxSchedulers.<CorrectRecommentVo>io_main()).
//                subscribe(new RxSubscriber<CorrectRecommentVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(CorrectRecommentVo correctRecommentVo) {
//                        listener.onNext(correctRecommentVo);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadCorrectMergeData(final OnResultCallBack<Object> listener) {
        Observable.concat(mCorrectDetail, mCorrectRecomment)
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
