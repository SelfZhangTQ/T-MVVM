package com.code.mvvm.core.view.book;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.BookViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/7/2 14:40
 */
public class BookListFragment extends BaseListFragment<BookViewModel> {
    private String typeId;

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        if (getArguments() != null) {
            typeId = getArguments().getString("type_id");
        }
    }

    @Override
    protected void dataObserver() {
        mViewModel.getBookList().observe(this, bookListVo -> {
            if (bookListVo == null) {
                return;
            }
            if (bookListVo.data.content.size() > 0) {
                lastId = bookListVo.data.content.get(bookListVo.data.content.size() - 1).bookid;
                BookListFragment.this.setData(bookListVo.data.content);
            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getBookAdapter(activity);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNetWorkData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getNetWorkData();
    }

    private void getNetWorkData() {
        mViewModel.getBookList(typeId, lastId);

    }

}
