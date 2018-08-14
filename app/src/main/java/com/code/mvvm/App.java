package com.code.mvvm;

import android.app.Application;

import com.code.mvvm.config.URL;
import com.code.mvvm.stateview.ErrorState;
import com.code.mvvm.stateview.LoadingState;
import com.network.HttpHelper;
import com.tqzhang.stateview.core.LoadState;


/**
 * @author：zhangtianqiu on 18/4/19 17:57
 */
public class App extends Application {
    public static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        HttpHelper.init(this, null, URL.BASE_URL);
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();
    }

    public static App Instance() {
        return mInstance;
    }

}
