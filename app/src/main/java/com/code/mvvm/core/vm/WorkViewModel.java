package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.source.WorkRepository;
import com.mvvm.base.AbsViewModel;


/**
 * @author：tqzhang on 18/7/31 15:32
 */
public class WorkViewModel extends AbsViewModel<WorkRepository> {

    private MutableLiveData<WorksListVo> mWorkMoreData;

    private MutableLiveData<WorkDetailVo> mWorkDetailData;

    private MutableLiveData<WorkRecommentVo> mWorkRecommentData;

    private MutableLiveData<WorkMergeVo> mWorkMergeData;


    public WorkViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<WorksListVo> getWorkMoreData() {
        if (mWorkMoreData == null) {
            mWorkMoreData = new MutableLiveData<>();
        }
        return mWorkMoreData;
    }

    public LiveData<WorkDetailVo> getWorkDetailData() {
        if (mWorkDetailData == null) {
            mWorkDetailData = new MutableLiveData<>();
        }
        return mWorkDetailData;
    }

    public LiveData<WorkRecommentVo> getWorkRecommentData() {
        if (mWorkRecommentData == null) {
            mWorkRecommentData = new MutableLiveData<>();
        }
        return mWorkRecommentData;
    }

    public void getWorkMoreData(String corrected, String lastId, String utime) {
        mRepository.loadWorkMoreData(corrected, lastId, utime, Constants.PAGE_RN);
    }

    public void getWorkData(String corrected, String rn) {
        mRepository.loadWorkData(corrected, rn);

    }

    public void getBannerData(String posType,
                              String fCatalogId,
                              String sCatalogId,
                              String tCatalogId,
                              String province) {
        mRepository.loadBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);
    }

    public void getWorkListData() {
        getBannerData("1", "4", "109", "", null);
        getWorkData("80", "20");
        mRepository.loadWorkListData();
    }

    private void getWorkDetailData(String correctId) {
        mRepository.loadWorkDetailData(correctId);
    }

    public void getWorkRecommendData(String correctId) {
        mRepository.loadWorkRecommendData(correctId);
    }

    public void getWorkDetaiMergeData(String correctId) {
        getWorkDetailData(correctId);
        getWorkRecommendData(correctId);
        mRepository.loadWorkMergeData();

    }

}
