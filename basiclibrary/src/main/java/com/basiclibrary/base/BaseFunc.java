package com.basiclibrary.base;



import rx.functions.Func1;

/**
 * @author：zhangtianqiu on 18/3/12 19:02
 */
public class BaseFunc<T> implements Func1<BaseEntity<T>, T> {
    @Override
    public T call(BaseEntity<T> tBaseEntity) {
        if (tBaseEntity.res_code != 1) {
//            throw new ServerException(tBaseEntity.res_code, tBaseEntity.message);
        }
        return tBaseEntity.result;
    }
}
