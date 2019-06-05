package com.mvvm.event;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 事件总线
 * <p>
 * <p>
 * LiveBus.getDefault().postEvent("LiveData","hi LiveData");
 * <p>
 * <p>
 * LiveBus.getDefault().subscribe("LiveData").observe(this, new Observer<Object>() {
 *
 * @Override public void onChanged(@Nullable Object o) {
 * Log.e("onChanged",((String)o));
 * }
 * });
 * @author：tqzhang on 18/9/11 17:22
 */
public class LiveBus {

    private static volatile LiveBus instance;

    private final ConcurrentHashMap<Object, LiveBusData<Object>> mLiveBus;

    private LiveBus() {
        mLiveBus = new ConcurrentHashMap<>();
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
        return subscribe(eventKey, "");
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, String tag) {
        return (MutableLiveData<T>) subscribe(eventKey, tag, Object.class);
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, Class<T> tClass) {
        return subscribe(eventKey, null, tClass);
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, String tag, Class<T> tClass) {
        String key = mergeEventKey(eventKey, tag);
        if (!mLiveBus.containsKey(key)) {
            mLiveBus.put(key, new LiveBusData<>(true));
        } else {
            LiveBusData liveBusData = mLiveBus.get(key);
            liveBusData.isFirstSubscribe = false;
        }

        return (MutableLiveData<T>) mLiveBus.get(key);
    }

    public <T> MutableLiveData<T> postEvent(Object eventKey, T value) {
        return postEvent(eventKey, null, value);
    }

    public <T> MutableLiveData<T> postEvent(Object eventKey, String tag, T value) {
        MutableLiveData<T> mutableLiveData = subscribe(mergeEventKey(eventKey, tag));
        mutableLiveData.postValue(value);
        return mutableLiveData;
    }


    public static class LiveBusData<T> extends MutableLiveData<T> {

        private boolean isFirstSubscribe;

        LiveBusData(boolean isFirstSubscribe) {
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


    private String mergeEventKey(Object eventKey, String tag) {
        String mEventkey;
        if (!TextUtils.isEmpty(tag)) {
            mEventkey = eventKey + tag;
        } else {
            mEventkey = (String) eventKey;
        }
        return mEventkey;
    }


    public void clear(Object eventKey) {
        clear(eventKey, null);
    }

    public void clear(Object eventKey, String tag) {
        if (mLiveBus != null && mLiveBus.size() > 0) {
            String mEventKey = mergeEventKey(eventKey, tag);
            mLiveBus.remove(mEventKey);
        }

    }

}
