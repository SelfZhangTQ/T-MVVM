package com.code.mvvm.core.data.source;


import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.pojo.article.ArticleVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class ArticleRepository extends BaseRepository {

    public static String EVENT_KEY_ARTICLE_LIST = null;

    public static String EVENT_KEY_ARTICLE = null;

    public ArticleRepository() {
        if (EVENT_KEY_ARTICLE_LIST == null) {
            EVENT_KEY_ARTICLE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_ARTICLE == null) {
            EVENT_KEY_ARTICLE = StringUtil.getEventKey();
        }
    }

    public void loadArticleRemList(final String lectureLevel, final String lastId, final String rn) {
        addDisposable(apiService.getArticleRemList(lectureLevel, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ArticleVo>() {

                    @Override
                    protected void onNoNetWork() {
                        showPageState(Constants.EVENT_KEY_ARTICLE_LIST_STATE, lectureLevel, StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ArticleVo articleVo) {
                        postData(EVENT_KEY_ARTICLE_LIST, lectureLevel, articleVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {
                    }
                }));

    }

    public void loadArticleType() {
        addDisposable(apiService.getArticleType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ArticleTypeVo>() {

                    @Override
                    protected void onNoNetWork() {
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(ArticleTypeVo articleTypeVo) {
                        postData(EVENT_KEY_ARTICLE, articleTypeVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {
                        postState(StateConstants.ERROR_STATE);
                    }
                }));

    }
}
