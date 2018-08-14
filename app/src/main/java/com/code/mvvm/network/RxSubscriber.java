package com.code.mvvm.network;


import com.code.mvvm.App;
import com.code.mvvm.util.NetworkUtils;
import com.google.gson.JsonParseException;
import com.network.helper.ServerException;

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
        if (!NetworkUtils.isNetworkAvailable(App.Instance())) {
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
//            switch (messageCode) {
//                case 0:
//                    message = "错误";
//                    break;
//                case 1:
//                    message = "成功";
//                    break;
//                case 404:
//                    message = "请求出错";
//                    break;
//                case 500:
//                case 504:
//                    message = "服务器异常";
//                    break;
//                case 1001:
//                    message = "系统错误";
//                    break;
//                case 1002:
//                    message = "数据库操作错误";
//                    break;
//                case 1003:
//                    message = "参数不完整";
//                    break;
//                case 1004:
//                    message = "未查询到";
//                    break;
//                case 1010:
//                    message = "默认数据已存在";
//                    break;
//                case 1017:
//                    message = "校验不通过";
//                    break;
//                case 5000:
//                    message = "其它错误";
//                    break;
//                default:
//                    break;
//
//            }


        }
        onFailure(message);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    protected void showLoading() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(String msg);
}
