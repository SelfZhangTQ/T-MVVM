package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.core.data.pojo.material.MaterialVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class MaterialRepository extends BaseRepository {

    public static String EVENT_KEY_MT_LIST = null;

    public static String EVENT_KEY_MT_MORE_LIST = null;

    public static String EVENT_KEY_MT = null;

    public static String EVENT_KEY_MT_RED = null;

    public MaterialRepository() {
        if (EVENT_KEY_MT_LIST == null) {
            EVENT_KEY_MT_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_MT_MORE_LIST == null) {
            EVENT_KEY_MT_MORE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_MT == null) {
            EVENT_KEY_MT = StringUtil.getEventKey();
        }
        if (EVENT_KEY_MT_RED == null) {
            EVENT_KEY_MT_RED = StringUtil.getEventKey();
        }

    }

    public void loadMaterialList(String mCatalogId, String mLevel, String rn) {

        addDisposable(apiService.getMaterialList(mCatalogId, mLevel, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);

                    }

                    @Override
                    public void onSuccess(MaterialVo materialVo) {
                        postData(EVENT_KEY_MT_LIST,mLevel, materialVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

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
                        postData(EVENT_KEY_MT_MORE_LIST,mLevel, materialVo);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                    }
                }));
    }

    public void loadMaterialRemList(String mCatalogId, String lastId, String rn) {
        apiService.getMaterialRemList(mCatalogId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<MaterialRecommendVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(MaterialRecommendVo materialRecommendObject) {
                        postData(EVENT_KEY_MT_RED, materialRecommendObject);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);

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
                        postData(EVENT_KEY_MT, materialTypeVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {
                    }
                });
    }

}
