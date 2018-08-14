package com.code.mvvm.core.data;

import com.code.mvvm.network.ApiService;
import com.network.HttpHelper;

/**
 * @author：zhangtianqiu on 18/7/26 16:15
 */
public abstract class BaseRepository {

    protected ApiService apiService;

    public BaseRepository() {
        apiService = HttpHelper.getInstance().create(ApiService.class);
    }
}
