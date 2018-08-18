package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.material.MateriaVo;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/28 13:00
 */
public class MaterialRepository extends BaseRepository {

    public void loadMaterialList(String mCatalogId, String mLevel, String rn, final OnResultCallBack<MateriaVo> listener) {

        apiService.getMaterialList(mCatalogId, mLevel, rn)
                .compose(RxSchedulers.<MateriaVo>io_main())
                .subscribe(new RxSubscriber<MateriaVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MateriaVo materialListVo) {
                        listener.onNext(materialListVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadMaterialMoreList(String mCatalogId, String mLevel, String lastId, String rn, final OnResultCallBack<MateriaVo> listener) {
        apiService.getMaterialMoreList(mCatalogId, mLevel, lastId, rn)
                .compose(RxSchedulers.<MateriaVo>io_main())
                .subscribe(new RxSubscriber<MateriaVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MateriaVo materialListVo) {
                        listener.onNext(materialListVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadMaterialRemList(String mCatalogId, String lastId, String rn, final OnResultCallBack<MaterialRecommendVo> listener) {
        apiService.getMaterialRemList(mCatalogId, lastId, rn)
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.material.MaterialRecommendVo>io_main())
                .subscribe(new RxSubscriber<MaterialRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialRecommendVo materialRecommendObject) {
                        listener.onNext(materialRecommendObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadMaterialTypeData(final OnResultCallBack<MaterialTypeVo> listener) {
        apiService.getMaterialInfo()
                .compose(RxSchedulers.<MaterialTypeVo>io_main())
                .subscribe(new RxSubscriber<MaterialTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialTypeVo materialTypeVo) {
                        listener.onNext(materialTypeVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }
}
