package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

import rx.Observable;

/**
 * @author：tqzhang on 18/7/31 15:32
 */
public class WorkRepository extends BaseRepository {

    private Observable<WorksListVo> mWorkData;

    private Observable<BannerListVo> mBannerData;

    private Observable<WorkDetailVo> mWorkDetail;

    private Observable<WorkRecommentVo> mWorkRecommend;

    public void loadWorkData(String corrected, String rn) {
        mWorkData = apiService.getWorkData(corrected, rn);
    }

    public void loadWorkMoreData(String corrected, String lastId, String uTime, String rn, final CallBack<WorksListVo> listener) {
        addSubscribe(apiService.getWorkMoreData(lastId, uTime, rn)
                .compose(RxSchedulers.io_main())
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
                }));
    }

    public void loadBannerData(String posType,
                               String fCatalogId,
                               String sCatalogId,
                               String tCatalogId,
                               String province) {
        mBannerData = apiService.getBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);
    }

    public void loadRequestMerge(final CallBack<Object> listener) {
        addSubscribe(Observable.concatDelayError(mBannerData, mWorkData)
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

    public void loadWorkDetailData(String correctId, final CallBack<WorkDetailVo> listener) {
        mWorkDetail = apiService.getWorkDetailData(correctId);
    }

    public void loadWorkRecommendData(String correctId, final CallBack<WorkRecommentVo> listener) {
        mWorkRecommend = apiService.getWorkRecommendData(correctId);
    }

    public void loadWorkMergeData(final CallBack<Object> listener) {
        addSubscribe(Observable.concatDelayError(mWorkDetail, mWorkRecommend)
                .compose(RxSchedulers.<Object>io_main())
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
