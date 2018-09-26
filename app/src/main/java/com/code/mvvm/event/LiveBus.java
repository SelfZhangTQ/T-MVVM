package com.code.mvvm.event;


import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * 事件总线
 *
 * @author：tqzhang on 18/9/11 17:22
 */
public class LiveBus {

    private static volatile LiveBus instance;

    private final Map<Object, MutableLiveData<Object>> mLiveBus;

    private LiveBus() {
        mLiveBus = new HashMap<>();
    }

    public static LiveBus getDefault() {
        if (instance == null) {
            synchronized (LiveBus.class) {
                if (instance == null) {
                    instance = new LiveBus();
                }
            }
        }
        return instance;
    }

    public <T> MutableLiveData<T> subscribe(Object subscriber, Class<T> tMutableLiveData) {
        checkNotNull(subscriber);
        checkNotNull(tMutableLiveData);
        if (!mLiveBus.containsKey(subscriber)) {
            mLiveBus.put(subscriber, new MutableLiveData<>());
        }
        return (MutableLiveData<T>) mLiveBus.get(subscriber);

    }
}
