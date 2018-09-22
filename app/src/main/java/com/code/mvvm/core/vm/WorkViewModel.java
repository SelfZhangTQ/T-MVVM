package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.source.WorkRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;


/**
 * @author：tqzhang on 18/7/31 15:32
 */
public class WorkViewModel extends AbsViewModel<WorkRepository> {

    private MutableLiveData<WorksListVo> mWorkMoreData;

    private MutableLiveData<WorkDetailVo> mWorkDetailData;

    private MutableLiveData<WorkRecommentVo> mWorkRecommentData;

    private MutableLiveData<WorkMergeVo> mWorkMergeData;

    private WorkMergeVo workMergeVo = new WorkMergeVo();

    public WorkViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<WorkMergeVo> getWorkMergeData() {
        if (mWorkMergeData == null) {
            mWorkMergeData = new MutableLiveData<>();
        }
        return mWorkMergeData;
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
        checkNotNull(corrected);
        checkNotNull(lastId);
        checkNotNull(utime);
        mRepository.loadWorkMoreData(corrected, lastId, utime, Constants.PAGE_RN, new CallBack<WorksListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorksListVo worksListHotObject) {
                mWorkMoreData.postValue(worksListHotObject);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getWorkData(String corrected, String rn) {
        checkNotNull(corrected);
        checkNotNull(rn);
        mRepository.loadWorkData(corrected, rn);

    }

    public void getBannerData(String posType,
                              String fCatalogId,
                              String sCatalogId,
                              String tCatalogId,
                              String province) {
        mRepository.loadBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);
    }

    public void getRequestMerge() {
        getBannerData("1", "4", "109", "", null);
        getWorkData("80", "20");
        mRepository.loadRequestMerge(new CallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof BannerListVo) {
                    workMergeVo.bannerListVo = (BannerListVo) object;
                } else if (object instanceof WorksListVo) {
                    workMergeVo.worksListVo = (WorksListVo) object;
                    mWorkMergeData.postValue(workMergeVo);
                    loadState.postValue(StateConstants.SUCCESS_STATE);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    private void getWorkDetailData(String correctId) {
        mRepository.loadWorkDetailData(correctId, new CallBack<WorkDetailVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorkDetailVo correctDetailVo) {
                mWorkDetailData.postValue(correctDetailVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getWorkRecommendData(String correctId) {
        mRepository.loadWorkRecommendData(correctId, new CallBack<WorkRecommentVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorkRecommentVo correctRecommentVo) {
                mWorkRecommentData.postValue(correctRecommentVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getWorkMergeData(String correctId) {
        getWorkDetailData(correctId);
        getWorkRecommendData(correctId);
        mRepository.loadWorkMergeData(new CallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof WorkDetailVo) {
                    mWorkDetailData.postValue((WorkDetailVo) object);
                } else if (object instanceof WorkRecommentVo) {
                    mWorkRecommentData.postValue((WorkRecommentVo) object);
                    loadState.postValue(StateConstants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });

    }

}
