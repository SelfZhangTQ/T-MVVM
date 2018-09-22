package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class BookRepository extends BaseRepository {

    public void loadBookList(String fCatalogId, String lastId, String rn, final CallBack listener) {
        addSubscribe(apiService.getBookList(fCatalogId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<BookListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(BookListVo bookListObject) {
                        listener.onNext(bookListObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }

    public void loadBookTypeData(final CallBack<BookTypeVo> listener) {
        addSubscribe(apiService.getBookType()
                .compose(RxSchedulers.<BookTypeVo>io_main())
                .subscribe(new RxSubscriber<BookTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(BookTypeVo bookClassObject) {
                        listener.onNext(bookClassObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }
}
