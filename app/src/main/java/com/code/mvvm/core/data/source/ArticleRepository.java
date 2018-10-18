package com.code.mvvm.core.data.source;


import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.pojo.article.ArticleVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class ArticleRepository extends BaseRepository {

    public void loadArticleRemList(final String lectureLevel, final String lastId, final String rn) {
        addDisposable(apiService.getArticleRemList(lectureLevel, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ArticleVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_ARTICLE_LIST_STATE,lectureLevel, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ArticleVo articleVo) {
                        sendData(Constants.EVENT_KEY_ARTICLE_LIST,lectureLevel, articleVo);
                        showPageState(Constants.EVENT_KEY_ARTICLE_LIST_STATE,lectureLevel, StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }));

    }

    public void loadArticleType() {
        addDisposable(apiService.getArticleType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ArticleTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_ARTICLE_STATE, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ArticleTypeVo articleTypeVo) {
                        sendData(Constants.EVENT_KEY_ARTICLE, articleTypeVo);
                        showPageState(Constants.EVENT_KEY_ARTICLE_STATE, StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg) {
                        showPageState(Constants.EVENT_KEY_ARTICLE_STATE, StateConstants.ERROR_STATE);
                    }
                }));

    }
}
