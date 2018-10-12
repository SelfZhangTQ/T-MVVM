package com.code.mvvm.event;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * 事件总线
 *
 *
 *  LiveBus.getDefault().postEvent("LiveData","hi LiveData");
 *
 *
 *  LiveBus.getDefault().subscribe("LiveData").observe(this, new Observer<Object>() {
 * @Override
 *     public void onChanged(@Nullable Object o) {
 *         Log.e("onChanged",((String)o));
 *       }
 * });
 *
 * @author：tqzhang on 18/9/11 17:22
 */
public class LiveBus {

    private static volatile LiveBus instance;

    private final Map<Object, LiveBusData<Object>> mLiveBus;

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


    public <T> MutableLiveData<T> subscribe(Object eventKey) {
        checkNotNull(eventKey);
        return (MutableLiveData<T>) subscribe(eventKey, Object.class);
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, Class<T> tMutableLiveData) {
        checkNotNull(eventKey);
        checkNotNull(tMutableLiveData);
        if (!mLiveBus.containsKey(eventKey)) {
            mLiveBus.put(eventKey, new LiveBusData<>(true));
        } else {
            LiveBusData liveBusData = mLiveBus.get(eventKey);
            liveBusData.isFirstSubscribe = false;
        }

        return (MutableLiveData<T>) mLiveBus.get(eventKey);
    }

    public <T> MutableLiveData<T> postEvent(Object eventKey, T value) {
        checkNotNull(eventKey);
        MutableLiveData<T> mutableLiveData = subscribe(eventKey);
        mutableLiveData.postValue(value);
        return mutableLiveData;
    }


    public static class LiveBusData<T> extends MutableLiveData<T> {

        private boolean isFirstSubscribe;

        public LiveBusData(boolean isFirstSubscribe) {
            this.isFirstSubscribe = isFirstSubscribe;
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
            super.observe(owner, new ObserverWrapper<>(observer, isFirstSubscribe));
        }
    }

    private static class ObserverWrapper<T> implements Observer<T> {

        private Observer<T> observer;

        private boolean isChanged;

        private ObserverWrapper(Observer<T> observer, boolean isFirstSubscribe) {
            this.observer = observer;
            isChanged = isFirstSubscribe;
        }

        @Override
        public void onChanged(@Nullable T t) {
            if (isChanged) {
                if (observer != null) {
                    observer.onChanged(t);
                }
            } else {
                isChanged = true;
            }
        }

    }


}
