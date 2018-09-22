package com.mvvm.base;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public abstract class AbsRepository {

    private CompositeSubscription mCompositeSubscription;


    public AbsRepository() {

    }

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
