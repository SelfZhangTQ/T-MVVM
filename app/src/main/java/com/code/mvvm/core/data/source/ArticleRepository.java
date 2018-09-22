package com.code.mvvm.core.data.source;


import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.pojo.article.ArticleVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

/**
 * @author：tqzhang on 18/7/28 13:00
 */
public class ArticleRepository extends BaseRepository {

    public void loadArticleRemList(final String lectureLevel, final String lastId, final String rn, final CallBack<ArticleVo> listener) {
        addSubscribe(apiService.getArticleRemList(lectureLevel, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<ArticleVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(ArticleVo articleObject) {
                        listener.onNext(articleObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));

    }

    public void loadArticleType(final CallBack<ArticleTypeVo> listener) {
        apiService.getArticleType()
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.article.ArticleTypeVo>io_main())
                .subscribe(new RxSubscriber<ArticleTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(ArticleTypeVo articleTypeObject) {
                        listener.onNext(articleTypeObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });

    }
}
