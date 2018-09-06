package com.code.mvvm.callback;

/**
 * @author：tqzhang on 18/7/31 12:33
 */
public interface CallBack<T> {

    /**
     * no network
     */
    void onNoNetWork();

    /**
     * @param t
     */
    void onNext(T t);

    /**
     * @param e
     */
    void onError(String e);
}
