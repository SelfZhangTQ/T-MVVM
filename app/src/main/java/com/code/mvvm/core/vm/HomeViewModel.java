package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.home.HomeMergeVo;
import com.code.mvvm.core.data.source.HomeRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class HomeViewModel extends AbsViewModel<HomeRepository> {

    private MutableLiveData<HomeListVo> homeData;

    private MutableLiveData<BannerListVo> bannerData;

    private MutableLiveData<HomeMergeVo> mergeData;

    private final HomeMergeVo homeMergeVo = new HomeMergeVo();

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

    public LiveData<HomeMergeVo> getMergeData() {
        if (mergeData == null) {
            mergeData = new MutableLiveData<>();
        }
        return mergeData;
    }

    private void loadHomeList(String id) {
        mRepository.loadHomeData(id);
    }

    private void getBannerData(String posType,
                               String fCatalogId,
                               String sCatalogId,
                               String tCatalogId,
                               String province) {
        mRepository.loadBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);

    }

    public void getRequestMerge() {
        getBannerData("1", "4", "109", "", null);
        loadHomeList("0");
        mRepository.loadRequestMerge(new CallBack<Object>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(Object object) {
                if (object instanceof BannerListVo) {
                    homeMergeVo.bannerListVo = (BannerListVo) object;
                } else if (object instanceof HomeListVo) {
                    homeMergeVo.homeListVo = (HomeListVo) object;
                    mergeData.postValue(homeMergeVo);
                    loadState.postValue(StateConstants.SUCCESS_STATE);
                }

            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
