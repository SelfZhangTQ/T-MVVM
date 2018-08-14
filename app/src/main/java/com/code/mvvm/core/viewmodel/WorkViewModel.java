package com.code.mvvm.core.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.source.WorkRepository;


/**
 * @author：zhangtianqiu on 18/7/31 15:32
 */
public class WorkViewModel extends BaseViewModel<WorkRepository> {
    private MutableLiveData<BannerListVo> mBannerData;
    private MutableLiveData<WorksListVo> mWorkMoreData;
    private MutableLiveData<WorksListVo> mWorkData;
    private MutableLiveData<WorkDetailVo> mWorkDetailData;
    private MutableLiveData<WorkRecommentVo> mWorkRecommentData;

    public WorkViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BannerListVo> getBannerData() {
        if (mBannerData == null) {
            mBannerData = new MutableLiveData<>();
        }
        return mBannerData;
    }

    public LiveData<WorksListVo> getWorkMoreData() {
        if (mWorkMoreData == null) {
            mWorkMoreData = new MutableLiveData<>();
        }
        return mWorkMoreData;
    }

    public LiveData<WorksListVo> getWorkData() {
        if (mWorkData == null) {
            mWorkData = new MutableLiveData<>();
        }
        return mWorkData;
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

    public void getWorkMoreData(String corrected, String lastId, String utime, String rn) {
        mRepository.loadWorkMoreData(corrected, lastId, utime, rn, new OnResultCallBack<WorksListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorksListVo worksListHotObject) {
                mWorkMoreData.postValue(worksListHotObject);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getWorkData(String corrected, String rn) {
        mRepository.loadWorkData(corrected, rn, new OnResultCallBack<WorksListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorksListVo worksListHotObject) {
                mWorkData.postValue(worksListHotObject);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getBannerData(String posType,
                              String fCatalogId,
                              String sCatalogId,
                              String tCatalogId,
                              String province) {
        mRepository.loadBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province, new OnResultCallBack<BannerListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(BannerListVo headAdList) {
                mBannerData.postValue(headAdList);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });

    }

    public void getRequestMerge() {
        mRepository.loadRequestMerge(new OnResultCallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof BannerListVo) {
                    mBannerData.postValue((BannerListVo) object);
                } else if (object instanceof WorksListVo) {
                    mWorkData.postValue((WorksListVo) object);
                    loadState.postValue(Constants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });
    }

    public void getWorkDetailData(String correctId) {
        mRepository.loadWorkDetailData(correctId, new OnResultCallBack<WorkDetailVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorkDetailVo correctDetailVo) {
                mWorkDetailData.postValue(correctDetailVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getWorkRecommendData(String correctId) {
        mRepository.loadWorkRecommendData(correctId, new OnResultCallBack<WorkRecommentVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorkRecommentVo correctRecommentVo) {
                mWorkRecommentData.postValue(correctRecommentVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getWorkMergeData(String correctId) {
        getWorkDetailData(correctId);
        getWorkRecommendData(correctId);
        mRepository.loadWorkMergeData(new OnResultCallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof WorkDetailVo) {
                    mWorkDetailData.postValue((WorkDetailVo) object);
                } else if (object instanceof WorkRecommentVo) {
                    mWorkRecommentData.postValue((WorkRecommentVo) object);
                    loadState.postValue(Constants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });

    }

}
