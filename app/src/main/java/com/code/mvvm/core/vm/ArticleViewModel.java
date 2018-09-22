package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.pojo.article.ArticleVo;
import com.code.mvvm.core.data.source.ArticleRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class ArticleViewModel extends AbsViewModel<ArticleRepository> {

    private MutableLiveData<ArticleVo> mArticleData;

    private MutableLiveData<ArticleTypeVo> mArticleTypeData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArticleVo> getArticleList() {
        if (mArticleData == null) {
            mArticleData = new MutableLiveData<>();
        }
        return mArticleData;
    }

    public LiveData<ArticleTypeVo> getArticleType() {
        if (mArticleTypeData == null) {
            mArticleTypeData = new MutableLiveData<>();
        }
        return mArticleTypeData;
    }

    public void getArticleList(String lectureLevel1, String lastId) {
        checkNotNull(lectureLevel1);
        mRepository.loadArticleRemList(lectureLevel1, lastId, Constants.PAGE_RN, new CallBack<ArticleVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(ArticleVo articleObject) {
                mArticleData.postValue(articleObject);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getArticleTypeData() {
        mRepository.loadArticleType(new CallBack<ArticleTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(ArticleTypeVo articleTypeObject) {
                mArticleTypeData.postValue(articleTypeObject);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }


}
