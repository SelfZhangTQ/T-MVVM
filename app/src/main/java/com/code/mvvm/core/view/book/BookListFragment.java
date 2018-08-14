package com.code.mvvm.core.view.book;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.viewmodel.BookViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/7/2 14:40
 */
public class BookListFragment extends BaseListFragment<BookViewModel> {

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getBookList().observe(this, new Observer<BookListVo>() {
            @Override
            public void onChanged(@Nullable BookListVo bookListVo) {
                if (bookListVo.data.content.size() > 0) {
                    lastid = bookListVo.data.content.get(bookListVo.data.content.size() - 1).bookid;
                    setData(bookListVo.data.content);
                }
            }
        });
    }

    @Override
    protected BookViewModel createViewModelProviders() {
        return ViewModelProviders.of(this).get(BookViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getBookAdapter(getActivity());
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getRemoteData();
    }

    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getBookList(getArguments().getString("f_catalog_id"), lastid, "20");

    }

}
