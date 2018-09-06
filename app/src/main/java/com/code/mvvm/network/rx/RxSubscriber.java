package com.code.mvvm.network.rx;


import com.code.mvvm.App;
import com.code.mvvm.network.ServerException;
import com.code.mvvm.util.NetworkUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class RxSubscriber<T> extends Subscriber<T> {
    @Override
    public void onStart() {
        super.onStart();
        showLoading();
        if (!NetworkUtils.isNetworkAvailable(App.instance())) {
            onNoNetWork();
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            return;
        }
    }

    protected void onNoNetWork() {

    }

    @Override
    public void onCompleted() {


    }

    @Override
    public void onError(Throwable e) {
        String message = null;
        if (e instanceof UnknownHostException) {
            message = "没有网络";
        } else if (e instanceof HttpException) {
            message = "网络错误";
        } else if (e instanceof SocketTimeoutException) {
            message = "网络连接超时";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            message = "解析错误";
        } else if (e instanceof ConnectException) {
            message = "连接失败";
        } else if (e instanceof ServerException) {
            message = ((ServerException) e).message;
        }
        onFailure(message);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    protected void showLoading() {

    }

    /**
     * success
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * failure
     *
     * @param msg
     */
    public abstract void onFailure(String msg);
}
