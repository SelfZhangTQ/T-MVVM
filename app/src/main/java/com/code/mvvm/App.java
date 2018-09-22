package com.code.mvvm;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.code.mvvm.config.URL;
import com.mvvm.http.HttpHelper;
import com.mvvm.stateview.ErrorState;
import com.mvvm.stateview.LoadingState;
import com.tqzhang.stateview.core.LoadState;


/**
 * @author：tqzhang on 18/4/19 17:57
 */
public class App extends Application implements ComponentCallbacks2 {
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
        new HttpHelper.Builder()
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build();
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();
    }

    public static App instance() {
        return mInstance;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
