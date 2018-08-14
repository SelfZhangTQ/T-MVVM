package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.material.MaterialListVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/28 13:00
 */
public class MaterialRepository extends BaseRepository {

    public void loadMaterialList(String f_catalog_id, String mlevel, String rn, final OnResultCallBack listener) {

        apiService.getMaterialList(f_catalog_id, mlevel, rn)
                .compose(RxSchedulers.<MaterialListVo>io_main())
                .subscribe(new RxSubscriber<MaterialListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialListVo findMaterialListObject) {
                        listener.onNext(findMaterialListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadMaterialMoreList(String f_catalog_id, String mlevel, String lastId, String rn, final OnResultCallBack listener) {
        apiService.getMaterialMoreList(f_catalog_id, mlevel, lastId, rn)
                .compose(RxSchedulers.<MaterialListVo>io_main())
                .subscribe(new RxSubscriber<MaterialListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(MaterialListVo findMaterialListObject) {
                        listener.onNext(findMaterialListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadMaterialRemList(String f_catalog_id, String lastid, String rn, final OnResultCallBack listener) {
        apiService.getMaterialRemList(f_catalog_id, lastid, rn)
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.material.MaterialRecommendVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.material.MaterialRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.material.MaterialRecommendVo materialRecommendObject) {
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
                    public void onSuccess(MaterialTypeVo bookClassObject) {
                        listener.onNext(bookClassObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }
}
