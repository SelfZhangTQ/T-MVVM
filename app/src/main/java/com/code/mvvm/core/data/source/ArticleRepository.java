package com.code.mvvm.core.data.source;


import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/28 13:00
 */
public class ArticleRepository extends BaseRepository {

    public void loadArticleRemList(final String lecture_level1, final String lastid, final String rn, final OnResultCallBack listener) {
        apiService.getArticleRemList(lecture_level1, lastid, rn)
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.article.ArticleVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.article.ArticleVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();

                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.article.ArticleVo articleObject) {
                        listener.onNext(articleObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });

    }

    public void loadArticleType(final OnResultCallBack<com.code.mvvm.core.data.pojo.article.ArticleTypeVo> listener) {
        apiService.getArticleType()
                .compose(RxSchedulers.<com.code.mvvm.core.data.pojo.article.ArticleTypeVo>io_main())
                .subscribe(new RxSubscriber<com.code.mvvm.core.data.pojo.article.ArticleTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(com.code.mvvm.core.data.pojo.article.ArticleTypeVo articleTypeObject) {
                        listener.onNext(articleTypeObject);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                });

    }
}
