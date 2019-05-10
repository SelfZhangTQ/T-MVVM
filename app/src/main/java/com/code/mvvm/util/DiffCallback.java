package com.code.mvvm.util;

import android.support.v7.util.DiffUtil;

import com.adapter.adapter.ItemData;


/**
 * Created by Meiji on 2017/4/18.
 */

public class DiffCallback extends DiffUtil.Callback {

    private final ItemData mOldItems, mNewItems;

    public DiffCallback(ItemData oldItems, ItemData mNewItems) {
        this.mOldItems = oldItems;
        this.mNewItems = mNewItems;
    }


    @Override
    public int getOldListSize() {
        return mOldItems != null ? mOldItems.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewItems != null ? mNewItems.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition).hashCode();
    }
}
