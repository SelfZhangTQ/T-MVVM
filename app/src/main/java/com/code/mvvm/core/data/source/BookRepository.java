package com.code.mvvm.core.data.source;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class BookRepository extends BaseRepository {

    public void loadBookList(String fCatalogId, String lastId, String rn) {
        addDisposable(apiService.getBookList(fCatalogId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<BookListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(BookListVo bookListVo) {
                        sendData(Constants.EVENT_KEY_BOOK_LIST, fCatalogId, bookListVo);
                        showPageState(Constants.EVENT_KEY_BOOK_LIST_STATE, fCatalogId, StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }));
    }

    public void loadBookTypeData() {
        addDisposable(apiService.getBookType()
                .compose(RxSchedulers.<BookTypeVo>io_main())
                .subscribeWith(new RxSubscriber<BookTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_BOOK_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(BookTypeVo bookTypeVo) {
                        sendData(Constants.EVENT_KEY_BOOK, bookTypeVo);
                        showPageState(Constants.EVENT_KEY_BOOK_STATE, StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }));
    }
}
