package com.code.mvvm.core.view.book;

import android.os.Bundle;

import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.core.data.source.BookRepository;
import com.code.mvvm.core.vm.BookViewModel;
import com.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/6/30 11:13
 */
public class BookFragment extends BaseViewPagerFragment<BookViewModel> {

    private List<BookTypeVo.ClassContent> titleName = new ArrayList<>();

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        getTabData();
    }


    @Override
    protected void dataObserver() {
       registerSubscriber(BookRepository.EVENT_KEY_BOOK, BookTypeVo.class).observe(this, bookTypeVo -> {
            if (bookTypeVo == null) {
                return;
            }
            setData(bookTypeVo);
        });
    }


    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getTabData();
    }

    @Override
    protected String[] createPageTitle() {
        return mArrTitles;
    }

    @Override
    protected List<BaseFragment> createFragments() {
        return mFragments;
    }

    private void getTabData() {
        mViewModel.getBookTypeData();
    }


    private void setData(BookTypeVo bookTypeVo) {
        mArrTitles = new String[bookTypeVo.data.f_catalog.size()];
        titleName.clear();
        for (int j = 0; j < bookTypeVo.data.f_catalog.size(); j++) {
            titleName.add(bookTypeVo.data.f_catalog.get(j));
            mArrTitles[j] = (bookTypeVo.data.f_catalog.get(j).f_catalog_name);
        }
        initFragment();
        setAdapter();

    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            BookListFragment bookListFragment = BookListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("type_id", titleName.get(i).f_catalog_id);
            bookListFragment.setArguments(bundle);
            mFragments.add(bookListFragment);
        }
    }

}
