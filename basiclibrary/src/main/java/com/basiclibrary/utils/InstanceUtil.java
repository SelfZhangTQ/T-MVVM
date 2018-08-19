package com.basiclibrary.utils;

import java.lang.reflect.ParameterizedType;

/**
 *
 * author：zhangtianqiu on 18/3/12 19:12
 */
public class InstanceUtil {


    public static <T> T getNewInstance(Object o, int i) {
        if(o!=null){
            try {
                return ((Class<T>) ((ParameterizedType) (o.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }
}
