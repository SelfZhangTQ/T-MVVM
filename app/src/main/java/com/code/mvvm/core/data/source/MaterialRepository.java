package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.core.data.pojo.material.MaterialVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang  on 18/7/28 13:00
 */
public class MaterialRepository extends BaseRepository {

    public void loadMaterialList(String mCatalogId, String mLevel, String rn) {

        addDisposable(apiService.getMaterialList(mCatalogId, mLevel, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_MT_LIST_STATE,mLevel, StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(MaterialVo materialVo) {
                        sendData(Constants.EVENT_KEY_MT_LIST, mLevel,materialVo);
                        showPageState(Constants.EVENT_KEY_MT_LIST_STATE,mLevel, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_MT_LIST_STATE,mLevel, StateConstants.ERROR_STATE);

                    }
                }));
    }

    public void loadMaterialMoreList(String mCatalogId, String mLevel, String lastId, String rn) {
        addDisposable(apiService.getMaterialMoreList(mCatalogId, mLevel, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialVo materialVo) {
                        sendData(Constants.EVENT_KEY_MT_MORE_LIST, mLevel,materialVo);

                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }));
    }

    public void loadMaterialRemList(String mCatalogId, String lastId, String rn) {
        apiService.getMaterialRemList(mCatalogId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_MT_RED_STATE, StateConstants.NET_WORK_STATE);


                    }

                    @Override
                    public void onSuccess(MaterialRecommendVo materialRecommendObject) {
                        sendData(Constants.EVENT_KEY_MT_RED, materialRecommendObject);
                        showPageState(Constants.EVENT_KEY_MT_RED_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_MT_RED_STATE, StateConstants.ERROR_STATE);

                    }
                });
    }

    public void loadMaterialTypeData() {
        apiService.getMaterialInfo()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialTypeVo materialTypeVo) {
                        sendData(Constants.EVENT_KEY_MT, materialTypeVo);
                        showPageState(Constants.EVENT_KEY_MT_STATE, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                });
    }
}
