package com.code.mvvm.core.data.source;


import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.home.HomeMergeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

import io.reactivex.Flowable;

/**
 * @author：tqzhang on 18/7/26 16:18
 */
public class HomeRepository extends BaseRepository {

    private Flowable<HomeListVo> mHomeListObservable;

    private Flowable<BannerListVo> mBannerObservable;

    private final HomeMergeVo homeMergeVo = new HomeMergeVo();

    public void loadHomeData(String id) {
        mHomeListObservable = apiService.getHomeData(id);
    }

    public void loadBannerData(String posType,
                               String fCatalogId,
                               String sCatalogId,
                               String tCatalogId,
                               String province) {
        mBannerObservable = apiService.getBannerData(posType, fCatalogId, sCatalogId, tCatalogId, province);
    }


    public void loadHomeData() {
        addDisposable(Flowable.concat(mBannerObservable, mHomeListObservable)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<Object>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_HOME_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(Object object) {
                        if (object instanceof BannerListVo) {
                            homeMergeVo.bannerListVo = (BannerListVo) object;
                        } else if (object instanceof HomeListVo) {
                            homeMergeVo.homeListVo = (HomeListVo) object;
                            if (homeMergeVo != null) {
                                sendData(Constants.EVENT_KEY_HOME, homeMergeVo);
                                showPageState(Constants.EVENT_KEY_HOME_STATE, StateConstants.SUCCESS_STATE);
                            } else {
                                showPageState(Constants.EVENT_KEY_HOME_STATE, StateConstants.ERROR_STATE);
                            }
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_HOME_STATE, StateConstants.ERROR_STATE);
                    }
                }));

    }

}
