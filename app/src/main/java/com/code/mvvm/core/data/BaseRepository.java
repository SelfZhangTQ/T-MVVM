package com.code.mvvm.core.data;

import com.code.mvvm.network.ApiService;
import com.code.mvvm.network.HttpHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author：tqzhang  on 18/7/26 16:15
 */
public abstract class BaseRepository {

    protected ApiService apiService;

    public BaseRepository() {
        apiService = HttpHelper.getInstance().create(ApiService.class);
    }

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}
