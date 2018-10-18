package com.code.mvvm.util;

import android.support.annotation.NonNull;

/**
 * @author：tqzhang on 18/9/9 11:30
 */
public class Preconditions {
    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            return null;
        }
        return reference;
    }

}
