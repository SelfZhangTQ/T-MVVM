package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.network.rx.RxSchedulers;
import com.code.mvvm.network.rx.RxSubscriber;

/**
 * @author：tqzhang  on 18/7/28 13:00
 */
public class BookRepository extends BaseRepository {

    public void loadBookList(String f_catalog_id, String lastId, String rn, final OnResultCallBack listener) {
        apiService.getBookList(f_catalog_id, lastId, rn)
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.book.BookListVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.book.BookListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.book.BookListVo bookListObject) {
                        listener.onNext(bookListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }

    public void loadBookTypeData(final OnResultCallBack<com.code.mvvm.core.data.pojo.book.BookTypeVo> listener) {
        apiService.getBookType()
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.book.BookTypeVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.book.BookTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.book.BookTypeVo bookClassObject) {
                        listener.onNext(bookClassObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });
    }
}
