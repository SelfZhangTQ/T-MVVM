package com.code.mvvm.core.view.book;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.core.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/30 11:13
 */
public class BookFragment extends BaseViewPagerFragment<BookViewModel> {
    private List<BookTypeVo.ClassContent> titleName;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        titleName = new ArrayList<>();
        mViewModel.getBookType().observe(this, new Observer<BookTypeVo>() {
            @Override
            public void onChanged(@Nullable BookTypeVo bookClassObject) {
                setData(bookClassObject);
            }
        });
        getTabData();
    }

    @Override
    protected BookViewModel createViewModelProviders() {
        return VMProviders(this, BookViewModel.class);
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


    private void setData(BookTypeVo lessonTypeObject) {
        mArrTitles = new String[lessonTypeObject.data.f_catalog.size()];
        titleName.clear();
        for (int j = 0; j < lessonTypeObject.data.f_catalog.size(); j++) {
            titleName.add(lessonTypeObject.data.f_catalog.get(j));
            mArrTitles[j] = (lessonTypeObject.data.f_catalog.get(j).f_catalog_name);
        }
        initFragment();
        setAdapter();
    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            BookListFragment bookListFragment = BookListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("f_catalog_id", titleName.get(i).f_catalog_id);
            bookListFragment.setArguments(bundle);
            mFragments.add(bookListFragment);
        }
    }

}
