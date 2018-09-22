package com.code.mvvm.core.data;

import com.code.mvvm.network.ApiService;
import com.mvvm.base.AbsRepository;
import com.mvvm.http.HttpHelper;


/**
 * @author：tqzhang on 18/7/26 16:15
 */
public abstract class BaseRepository extends AbsRepository{

    protected ApiService apiService;

    public BaseRepository() {
        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }
    }

}
