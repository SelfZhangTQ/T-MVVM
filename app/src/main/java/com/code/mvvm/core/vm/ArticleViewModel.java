package com.code.mvvm.core.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.source.ArticleRepository;
import com.mvvm.base.AbsViewModel;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class ArticleViewModel extends AbsViewModel<ArticleRepository> {

    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    public void getArticleList(String lectureLevel1, String lastId) {
        mRepository.loadArticleRemList(lectureLevel1, lastId, Constants.PAGE_RN);
    }

    public void getArticleTypeData() {
        mRepository.loadArticleType();
    }


}
