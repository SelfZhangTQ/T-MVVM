package com.code.mvvm.core.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.source.BookRepository;
import com.mvvm.base.AbsViewModel;


/**
 * @author：tqzhang on 18/7/28 13:32
 */
public class BookViewModel extends AbsViewModel<BookRepository> {

    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    public void getBookList(String mCatalogId, String lastId) {
        mRepository.loadBookList(mCatalogId, lastId, Constants.PAGE_RN);
    }

    public void getBookTypeData() {
        mRepository.loadBookTypeData();
    }

}
