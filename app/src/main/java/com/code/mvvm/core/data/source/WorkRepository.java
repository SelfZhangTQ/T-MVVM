package com.code.mvvm.core.data.source;


import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

import io.reactivex.Flowable;

/**
 * @author：tqzhang on 18/7/31 15:32
 */
public class WorkRepository extends BaseRepository {

    private Flowable<WorksListVo> mWorkData;

    private Flowable<BannerListVo> mBannerData;

    private Flowable<WorkDetailVo> mWorkDetail;

    private Flowable<WorkRecommentVo> mWorkRecommend;

    private WorkMergeVo workMergeVo = new WorkMergeVo();

    public void loadWorkData(String corrected, String rn) {
        mWorkData = apiService.getWorkData(corrected, rn);
    }

    public void loadWorkMoreData(String corrected, String lastId, String uTime, String rn) {
        addDisposable(apiService.getWorkMoreData(lastId, uTime, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<WorksListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(WorksListVo worksListVo) {
                        sendData(Constants.EVENT_KEY_WORK_MORE, worksListVo);
                    }

                    @Override
                    public void onFailure(String msg) {
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

    public void loadWorkListData() {
        addDisposable(Flowable.concat(mBannerData, mWorkData)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<Object>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(Object object) {
                        if (object instanceof BannerListVo) {
                            workMergeVo.bannerListVo = (BannerListVo) object;
                        } else if (object instanceof WorksListVo) {
                            workMergeVo.worksListVo = (WorksListVo) object;
                            sendData(Constants.EVENT_KEY_WORK_LIST, workMergeVo);
                            showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.SUCCESS_STATE);
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_WORK_LIST_STATE, StateConstants.ERROR_STATE);
                    }
                }));
    }

    public void loadWorkDetailData(String correctId) {
        mWorkDetail = apiService.getWorkDetailData(correctId);
    }

    public void loadWorkRecommendData(String correctId) {
        mWorkRecommend = apiService.getWorkRecommendData(correctId);
    }

    public void loadWorkMergeData() {
        addDisposable(Flowable.concatArrayDelayError(mWorkDetail, mWorkRecommend)
                .compose(RxSchedulers.<Object>io_main())
                .subscribeWith(new RxSubscriber<Object>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_WORK_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(Object object) {
                        if (object instanceof WorkDetailVo) {
                            workMergeVo.workDetailVo = (WorkDetailVo) object;
                        } else if (object instanceof WorkRecommentVo) {
                            workMergeVo.workRecommentVo = (WorkRecommentVo) object;
                            sendData(Constants.EVENT_KEY_WORK, workMergeVo);
                            showPageState(Constants.EVENT_KEY_WORK_STATE, StateConstants.SUCCESS_STATE);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_WORK_STATE, StateConstants.ERROR_STATE);

                    }
                }));
    }
}
