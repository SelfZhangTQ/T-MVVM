package com.code.mvvm.core.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.source.MaterialRepository;
import com.mvvm.base.AbsViewModel;

/**
 * @author：tqzhang on 18/7/28 13:32
 */
public class MaterialViewModel extends AbsViewModel<MaterialRepository> {

    public MaterialViewModel(@NonNull Application application) {
        super(application);

    }
    public void getMaterialList(String fCatalogId, String level) {
        mRepository.loadMaterialList(fCatalogId, level, Constants.PAGE_RN);
    }

    public void getMaterialMoreList(String fCatalogId, String level, String lastId) {
        mRepository.loadMaterialMoreList(fCatalogId, level, lastId, Constants.PAGE_RN);
    }

    public void getMaterialRemList(String fCatalogId, String lastId) {
        mRepository.loadMaterialRemList(fCatalogId, lastId, Constants.PAGE_RN);
    }

    public void getMaterialTypeData() {
        mRepository.loadMaterialTypeData();
    }

}
