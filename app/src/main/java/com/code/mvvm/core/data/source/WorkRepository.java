package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.network.RxSubscriber;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author：zhangtianqiu on 18/7/31 15:32
 */
public class WorkRepository extends BaseRepository {

    private Observable<WorksListVo> observable2;
    private Observable<BannerListVo> observable1;

    private Observable<WorkDetailVo> mCorrectDetail;
    private Observable<WorkRecommentVo> mCorrectRecomment;

    public void loadWorkData(String corrected, String rn, final OnResultCallBack<WorksListVo> listener) {
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

    public void loadWorkMoreData(String corrected, String last_id, String utime, String rn, final OnResultCallBack<WorksListVo> listener) {
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

    public void loadWorkDetailData(String correctId, final OnResultCallBack<WorkDetailVo> listener) {

        mCorrectDetail = apiService.getWorkDetailData(correctId);
//        apiService.getWorkDetailData(correctId).
//                compose(RxSchedulers.<WorkDetailVo>io_main()).
//                subscribe(new RxSubscriber<WorkDetailVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(WorkDetailVo correctDetailVo) {
//                        listener.onNext(correctDetailVo);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadWorkRecommendData(String correctId, final OnResultCallBack<WorkRecommentVo> listener) {
        mCorrectRecomment=apiService.getWorkRecommendData(correctId);
//        apiService.getWorkRecommendData(correctId).
//                compose(RxSchedulers.<WorkRecommentVo>io_main()).
//                subscribe(new RxSubscriber<WorkRecommentVo>() {
//                    @Override
//                    protected void onNoNetWork() {
//                        super.onNoNetWork();
//                        listener.onNoNetWork();
//                    }
//
//                    @Override
//                    public void onSuccess(WorkRecommentVo correctRecommentVo) {
//                        listener.onNext(correctRecommentVo);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        listener.onError(msg);
//                    }
//                });
    }

    public void loadWorkMergeData(final OnResultCallBack<Object> listener) {
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
