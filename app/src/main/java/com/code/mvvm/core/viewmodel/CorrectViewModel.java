package com.code.mvvm.core.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.correct.CorrectDetailVo;
import com.code.mvvm.core.data.pojo.correct.CorrectRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.source.CorrectRepository;


/**
 * @author：zhangtianqiu on 18/7/31 15:32
 */
public class CorrectViewModel extends BaseViewModel<CorrectRepository> {
    private MutableLiveData<BannerAdListVo> mBannerData;
    private MutableLiveData<WorksListVo> mCorrectMoreData;
    private MutableLiveData<WorksListVo> mCorrectData;
    private MutableLiveData<CorrectDetailVo> mCorrectDetailData;
    private MutableLiveData<CorrectRecommentVo> mCorrectRecommentData;

    public CorrectViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BannerAdListVo> getBannerData() {
        if (mBannerData == null) {
            mBannerData = new MutableLiveData<>();
        }
        return mBannerData;
    }

    public LiveData<WorksListVo> getCorrectMoreData() {
        if (mCorrectMoreData == null) {
            mCorrectMoreData = new MutableLiveData<>();
        }
        return mCorrectMoreData;
    }

    public LiveData<WorksListVo> getCorrectData() {
        if (mCorrectData == null) {
            mCorrectData = new MutableLiveData<>();
        }
        return mCorrectData;
    }

    public LiveData<CorrectDetailVo> getCorrectDetailData() {
        if (mCorrectDetailData == null) {
            mCorrectDetailData = new MutableLiveData<>();
        }
        return mCorrectDetailData;
    }

    public LiveData<CorrectRecommentVo> getCorrectRecommentData() {
        if (mCorrectRecommentData == null) {
            mCorrectRecommentData = new MutableLiveData<>();
        }
        return mCorrectRecommentData;
    }

    public void getCorrectMoreData(String corrected, String lastId, String utime, String rn) {
        mRepository.loadCorrectMoreData(corrected, lastId, utime, rn, new OnResultCallBack<WorksListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorksListVo worksListHotObject) {
                mCorrectMoreData.postValue(worksListHotObject);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getCorrectData(String corrected, String rn) {
        mRepository.loadCorrectData(corrected, rn, new OnResultCallBack<WorksListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(WorksListVo worksListHotObject) {
                mCorrectData.postValue(worksListHotObject);
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
        mRepository.loadBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province, new OnResultCallBack<BannerAdListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(BannerAdListVo headAdList) {
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
                if (object instanceof BannerAdListVo) {
                    mBannerData.postValue((BannerAdListVo) object);
                } else if (object instanceof WorksListVo) {
                    mCorrectData.postValue((WorksListVo) object);
                    loadState.postValue(Constants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });
    }

    public void getCorrectDetailData(String correctId) {
        mRepository.loadCorrectDetailData(correctId, new OnResultCallBack<CorrectDetailVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CorrectDetailVo correctDetailVo) {
                mCorrectDetailData.postValue(correctDetailVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getCorrectRecommendData(String correctId) {
        mRepository.loadCorrectRecommendData(correctId, new OnResultCallBack<CorrectRecommentVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CorrectRecommentVo correctRecommentVo) {
                mCorrectRecommentData.postValue(correctRecommentVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getCorrectMergeData(String correctId) {
        getCorrectDetailData(correctId);
        getCorrectRecommendData(correctId);
        mRepository.loadCorrectMergeData(new OnResultCallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof CorrectDetailVo) {
                    mCorrectDetailData.postValue((CorrectDetailVo) object);
                } else if (object instanceof CorrectRecommentVo) {
                    mCorrectRecommentData.postValue((CorrectRecommentVo) object);
                    loadState.postValue(Constants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });

    }

}
