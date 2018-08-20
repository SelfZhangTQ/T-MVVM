package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.source.HomeRepository;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class HomeViewModel extends BaseViewModel<HomeRepository> {

    private MutableLiveData<HomeListVo> homeData;

    private MutableLiveData<BannerListVo> bannerData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<HomeListVo> getHomeList() {
        if (homeData == null) {
            homeData = new MutableLiveData<>();
        }
        return homeData;
    }

    public LiveData<BannerListVo> getBannerList() {
        if (bannerData == null) {
            bannerData = new MutableLiveData<>();
        }
        return bannerData;
    }

    public void loadHomeList(String id) {
        mRepository.loadHomeData(id, new OnResultCallBack<HomeListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(HomeListVo homeListVo) {
                homeData.postValue(homeListVo);
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
            public void onNext(BannerListVo bannerAdListVo) {
                bannerData.postValue(bannerAdListVo);
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
                    bannerData.postValue((BannerListVo) object);
                } else if (object instanceof HomeListVo) {
                    homeData.postValue((HomeListVo) object);
                    loadState.postValue(Constants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
