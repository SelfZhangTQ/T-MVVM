package com.code.mvvm;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.code.mvvm.config.URL;
import com.code.mvvm.network.HttpHelper;
import com.code.mvvm.stateview.ErrorState;
import com.code.mvvm.stateview.LoadingState;
import com.tqzhang.stateview.core.LoadState;


/**
 * @author：tqzhang on 18/4/19 17:57
 */
public class App extends Application {
    public static App mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
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
