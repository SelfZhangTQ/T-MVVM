package com.network;


import android.app.Application;
import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author：zhangtianqiu on 18/4/18 17:19
 */
public class HttpHelper {

    public static Context mContext;

    private static OkHttpClient mOkHttpClient;

    private static Retrofit mRetrofit;

    private static OkHttpClient.Builder mBuilder;


    public static HttpHelper mHttpHelper;

    public static HttpHelper getInstance() {
        if (mHttpHelper == null) {
            mHttpHelper = new HttpHelper();
        }

        return mHttpHelper;
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志
     */
    public static OkHttpClient.Builder initBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mBuilder == null) {
            synchronized (HttpHelper.class) {
                if (mBuilder == null) {
                    mBuilder = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS);
                }
            }
        }
        return mBuilder;
    }

    public static OkHttpClient.Builder addInterceptor(Interceptor mInterceptor) {
        mBuilder.addNetworkInterceptor(mInterceptor);
        return mBuilder;
    }

    public static OkHttpClient initOkHttpClient() {
        return mBuilder.build();
    }

    private static void initRetrofit(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);
        mRetrofit = builder.client(initOkHttpClient())
                .build();
    }

    public static void init(Context context, List<Interceptor> mInterceptor, String baseUrl) {
        mContext = context;
        if (mInterceptor != null && mInterceptor.size() > 0) {
            for (Interceptor interceptor : mInterceptor) {
                initBuilder().addNetworkInterceptor(interceptor);
            }
        } else {
            initBuilder();
        }

        initRetrofit(baseUrl);
    }

    public <T> T create(Class<T> clz) {
        return mRetrofit.create(clz);
    }

}
