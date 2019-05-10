package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class BookRepository extends BaseRepository {

    public static String EVENT_KEY_BOOK = null;

    public static String EVENT_KEY_BOOK_LIST = null;


    public BookRepository() {
        if (EVENT_KEY_BOOK == null) {
            EVENT_KEY_BOOK = StringUtil.getEventKey();
        }
        if (EVENT_KEY_BOOK_LIST == null) {
            EVENT_KEY_BOOK_LIST = StringUtil.getEventKey();
        }
    }

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
                        postData(EVENT_KEY_BOOK_LIST, fCatalogId, bookListVo);
                        postState(StateConstants.SUCCESS_STATE);

                    }

                    @Override
                    public void onFailure(String msg,int code) {

                    }
                }));
    }

    public void loadBookTypeData() {
        addDisposable(apiService.getBookType()
                .compose(RxSchedulers.<BookTypeVo>io_main())
                .subscribeWith(new RxSubscriber<BookTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(BookTypeVo bookTypeVo) {
                        postData(EVENT_KEY_BOOK, bookTypeVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg,int code) {
                    }
                }));
    }
}
