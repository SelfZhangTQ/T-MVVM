package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.core.data.source.BookRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * @author：tqzhang on 18/7/28 13:32
 */
public class BookViewModel extends AbsViewModel<BookRepository> {

    private MutableLiveData<BookListVo> mBookData;

    private MutableLiveData<BookTypeVo> mBookType;

    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BookListVo> getBookList() {
        if (mBookData == null) {
            mBookData = new MutableLiveData<>();
        }
        return mBookData;
    }

    public LiveData<BookTypeVo> getBookType() {
        if (mBookType == null) {
            mBookType = new MutableLiveData<>();
        }
        return mBookType;
    }

    public void getBookList(String mCatalogId, String lastId) {
        checkNotNull(mCatalogId);
        mRepository.loadBookList(mCatalogId, lastId, Constants.PAGE_RN, new CallBack<BookListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(BookListVo bookListVo) {
                mBookData.postValue(bookListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getBookTypeData() {
        mRepository.loadBookTypeData(new CallBack<BookTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(BookTypeVo bookTypeVo) {
                mBookType.postValue(bookTypeVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

}
