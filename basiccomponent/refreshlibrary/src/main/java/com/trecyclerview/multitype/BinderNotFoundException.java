
package com.trecyclerview.multitype;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
class BinderNotFoundException extends RuntimeException {

  BinderNotFoundException(@NonNull Class<?> clazz) {
    super("Do you have registered {className}.class to the binder in the adapter/pool?"
        .replace("{className}", clazz.getSimpleName()));
  }
}
