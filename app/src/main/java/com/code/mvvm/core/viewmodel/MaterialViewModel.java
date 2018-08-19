package com.code.mvvm.core.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.material.MateriaVo;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.core.data.source.MaterialRepository;

/**
 * @author：zhangtianqiu on 18/7/28 13:32
 */
public class MaterialViewModel extends BaseViewModel<MaterialRepository> {


    private MutableLiveData<MaterialTypeVo> mMaterialTypeData;
    private MutableLiveData<MateriaVo> mMaterialData;
    private MutableLiveData<MateriaVo> mMaterialMoreData;
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

    public LiveData<MateriaVo> getMaterialList() {
        if (mMaterialData == null) {
            mMaterialData = new MutableLiveData<>();
        }
        return mMaterialData;
    }

    public LiveData<MateriaVo> getMaterialMoreList() {
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


    public void getMaterialList(String fCatalogId, String mlevel, String rn) {
        mRepository.loadMaterialList(fCatalogId, mlevel, rn, new OnResultCallBack<MateriaVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MateriaVo materialListVo) {
                mMaterialData.postValue(materialListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getMaterialMoreList(String fCatalogId, String mlevel, String lastId, String rn) {
        mRepository.loadMaterialMoreList(fCatalogId, mlevel, lastId, rn, new OnResultCallBack<MateriaVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(MateriaVo materialListVo) {
                mMaterialMoreData.postValue(materialListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getMaterialRemList(String fCatalogId, String lastId, String rn) {
        mRepository.loadMaterialRemList(fCatalogId, lastId, rn, new OnResultCallBack<MaterialRecommendVo>() {
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
        mRepository.loadMaterialTypeData(new OnResultCallBack<MaterialTypeVo>() {
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
