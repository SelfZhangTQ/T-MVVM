package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.core.data.pojo.material.MaterialVo;
import com.code.mvvm.core.data.source.MaterialRepository;

/**
 * @author：tqzhang  on 18/7/28 13:32
 */
public class MaterialViewModel extends BaseViewModel<MaterialRepository> {

    private MutableLiveData<MaterialTypeVo> mMaterialTypeData;

    private MutableLiveData<MaterialVo> mMaterialData;

    private MutableLiveData<MaterialVo> mMaterialMoreData;

    private MutableLiveData<MaterialRecommendVo> mMaterialRecommendData;

    public MaterialViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<MaterialTypeVo> getMaterialType() {
        if (mMaterialTypeData == null) {
            mMaterialTypeData = new MutableLiveData<>();
        }
        return mMaterialTypeData;
    }

    public LiveData<MaterialVo> getMaterialList() {
        if (mMaterialData == null) {
            mMaterialData = new MutableLiveData<>();
        }
        return mMaterialData;
    }

    public LiveData<MaterialVo> getMaterialMoreList() {
        if (mMaterialMoreData == null) {
            mMaterialMoreData = new MutableLiveData<>();
        }
        return mMaterialMoreData;
    }

    public LiveData<MaterialRecommendVo> getMaterialRecommendList() {
        if (mMaterialRecommendData == null) {
            mMaterialRecommendData = new MutableLiveData<>();
        }
        return mMaterialRecommendData;
    }


    public void getMaterialList(String fCatalogId, String level) {
        mRepository.loadMaterialList(fCatalogId, level, Constants.PAGE_RN, new CallBack<MaterialVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MaterialVo materialListVo) {
                mMaterialData.postValue(materialListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getMaterialMoreList(String fCatalogId, String level, String lastId) {
        mRepository.loadMaterialMoreList(fCatalogId, level, lastId, Constants.PAGE_RN, new CallBack<MaterialVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MaterialVo materialListVo) {
                mMaterialMoreData.postValue(materialListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getMaterialRemList(String fCatalogId, String lastId) {
        mRepository.loadMaterialRemList(fCatalogId, lastId, Constants.PAGE_RN, new CallBack<MaterialRecommendVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MaterialRecommendVo materialRecommendObject) {
                mMaterialRecommendData.postValue(materialRecommendObject);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getMaterialTypeData() {
        mRepository.loadMaterialTypeData(new CallBack<MaterialTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MaterialTypeVo materialTypeVo) {
                mMaterialTypeData.postValue(materialTypeVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

}
