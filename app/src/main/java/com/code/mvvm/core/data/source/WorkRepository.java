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

    private Observable<WorksListVo> mWorkData;
    private Observable<BannerListVo> mBannerData;

    private Observable<WorkDetailVo> mWorkDetail;
    private Observable<WorkRecommentVo> mWorkRecomment;

    public void loadWorkData(String corrected, String rn, final OnResultCallBack<WorksListVo> listener) {
        mWorkData = apiService.getWorkData(corrected, rn);
    }

    public void loadWorkMoreData(String corrected, String last_id, String utime, String rn, final OnResultCallBack<WorksListVo> listener) {
        apiService.getWorkMoreData(last_id, utime, rn)
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
        mBannerData = apiService.getBannerData(posType, f_catalog_id, s_catalog_id, t_catalog_id, province);
    }

    public void loadRequestMerge(final OnResultCallBack<Object> listener) {
        Observable.concatDelayError(mBannerData, mWorkData)
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
        mWorkDetail = apiService.getWorkDetailData(correctId);
    }

    public void loadWorkRecommendData(String correctId, final OnResultCallBack<WorkRecommentVo> listener) {
        mWorkRecomment = apiService.getWorkRecommendData(correctId);
    }

    public void loadWorkMergeData(final OnResultCallBack<Object> listener) {
        Observable.concatDelayError(mWorkDetail, mWorkRecomment)
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
