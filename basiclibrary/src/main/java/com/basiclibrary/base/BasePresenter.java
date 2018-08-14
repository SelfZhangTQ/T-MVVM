package com.basiclibrary.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author：zhangtianqiu on 18/3/12 19:05
 */
public abstract class BasePresenter<M, T> {
    public M mModel;
    public T mView;

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

    public void initVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
    }

}
