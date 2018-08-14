package com.code.mvvm.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.core.data.pojo.banner.BannerAdListVo;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.book.BookVo;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialInfoVo;
import com.code.mvvm.core.data.pojo.material.MatreialListVo;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.view.activity.viewholder.ActivityListItemViewBinder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem1ItemViewBinder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem2ItemViewBinder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem3ItemViewBinder;
import com.code.mvvm.core.view.book.viewholder.BookItemView;
import com.code.mvvm.core.view.book.viewholder.BookListItemView;
import com.code.mvvm.core.view.common.FootItemViewBinder;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.viewholder.CorrectItemViewBinder;
import com.code.mvvm.core.view.course.viewholder.CourseItemView;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderAritcle;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderCorrect;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderFollow;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderLesson;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderLive;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderSubject;
import com.code.mvvm.core.view.dynamic.viewholder.dynamicBinderWork;
import com.code.mvvm.core.view.followdraw.viewholder.FollowDrawListItemViewBinder;
import com.code.mvvm.core.view.home.viewholder.CategoryItemView;
import com.code.mvvm.core.view.home.viewholder.HomeCourseItemView;
import com.code.mvvm.core.view.home.viewholder.HomeLiveItemView;
import com.code.mvvm.core.view.home.viewholder.HomeMaterialItemView;
import com.code.mvvm.core.view.home.viewholder.QAListItemViewBinder;
import com.code.mvvm.core.view.live.viewholder.LiveItemView;
import com.code.mvvm.core.view.live.viewholder.LiveListItemView;
import com.code.mvvm.core.view.material.viewholder.MaterialItemViewBinder;
import com.code.mvvm.core.view.material.viewholder.MatreialListItemViewBinder;
import com.code.mvvm.widget.banner.BannerItemView;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.AbsItemView;
import com.trecyclerview.multitype.ClassLinker;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.view.HeaderItemView;

/**
 * @author：zhangtianqiu on 18/8/3 16:25
 */
public class AdapterPool {

    public static AdapterPool adapterPool;

    public static AdapterPool newInstance() {
        if (adapterPool == null) {
            synchronized (AdapterPool.class) {
                if (adapterPool == null) {
                    adapterPool = new AdapterPool();
                }
            }
        }

        return adapterPool;
    }

    public MultiTypeAdapter getNoHeadAdapter(MultiTypeAdapter adapter, Context context) {
        adapter.register(FootInfo.class, new FootItemViewBinder());
        return adapter;
    }

    public MultiTypeAdapter getNoFootAdapter(MultiTypeAdapter adapter, Context context) {
        adapter.register(HeaderInfo.class, new HeaderItemView(context));
        return adapter;
    }

    public MultiTypeAdapter getAdapter(MultiTypeAdapter adapter, Context context) {
        adapter.register(HeaderInfo.class, new HeaderItemView(context));
        adapter.register(FootInfo.class, new FootItemViewBinder());
        return adapter;
    }

    public MultiTypeAdapter getCorrectAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerAdListVo.class, new BannerItemView(context));
        adapter.register(WorksListVo.Works.class, new CorrectItemViewBinder(context));
        return getNoHeadAdapter(adapter, context);
    }

    public MultiTypeAdapter getSwipeCorrectAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerAdListVo.class, new BannerItemView(context));
        adapter.register(WorksListVo.Works.class, new CorrectItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getBookAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BookVo.class, new BookListItemView());
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getActivityAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(ActivityListVo.DataBean.class, new ActivityListItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getArticleAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(ArticleInfoVo.class).to(new ArticleRem1ItemViewBinder(context), new ArticleRem2ItemViewBinder(context), new ArticleRem3ItemViewBinder(context))
                .withClassLinker(new ClassLinker<ArticleInfoVo>() {
                    @NonNull
                    @Override
                    public Class<? extends AbsItemView<ArticleInfoVo, ?>> index(int position, @NonNull ArticleInfoVo listBean) {
                        if ("1".equals(listBean.thumbtype)) {
                            return ArticleRem1ItemViewBinder.class;
                        } else if ("2".equals(listBean.thumbtype)) {
                            return ArticleRem2ItemViewBinder.class;
                        } else if ("3".equals(listBean.thumbtype)) {
                            return ArticleRem3ItemViewBinder.class;
                        }
                        return null;
                    }
                });
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getCourseRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(TypeVo.class, new TypeItemView());
        adapter.register(BannerAdListVo.class, new BannerItemView(context));
        adapter.register(CourseInfoVo.class, new HomeCourseItemView(context));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(context));
        return getNoFootAdapter(adapter, context);

    }

    public MultiTypeAdapter getCourseListAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(CourseInfoVo.class, new CourseItemView(context));
        return getAdapter(adapter, context);

    }

    public MultiTypeAdapter getFollowAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(FollowDrawInfoVo.class, new FollowDrawListItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getQaAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(QaListVo.DataBean.class, new QAListItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getMaterialListAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(MaterialInfoVo.class, new MatreialListItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getMaterialRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(MatreialSubjectVo.class, new MaterialItemViewBinder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getLiveAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(LiveRecommendVo.class, new LiveListItemView(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getLiveRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(LiveRecommendVo.class, new LiveItemView(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getHomeAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerAdListVo.class, new BannerItemView(context));
        adapter.register(TypeVo.class, new TypeItemView());
        adapter.register(CatagoryVo.class, new CategoryItemView(context));
        adapter.register(BookList.class, new BookItemView());
        adapter.register(CourseInfoVo.class, new HomeCourseItemView(context));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(context));
        adapter.register(MatreialListVo.class, new HomeMaterialItemView(context));
        return getNoFootAdapter(adapter, context);
    }

    public MultiTypeAdapter getDynamicAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(DynamicInfoVo.class)
                .to(new dynamicBinderCorrect(context),
                        new dynamicBinderWork(context),
                        new dynamicBinderSubject(context),
                        new dynamicBinderAritcle(context),
                        new dynamicBinderLesson(context),
                        new dynamicBinderLive(context),
                        new dynamicBinderFollow(context)
                )
                .withClassLinker(new ClassLinker<DynamicInfoVo>() {
                    @NonNull
                    @Override
                    public Class<? extends AbsItemView<DynamicInfoVo, ?>> index(int position, @NonNull DynamicInfoVo dynamicInfoVo) {
                        if (dynamicInfoVo.subjecttype.equals("1")) {
                            return dynamicBinderCorrect.class;
                        } else if (dynamicInfoVo.subjecttype.equals("2")) {
                            return dynamicBinderWork.class;
                        } else if (dynamicInfoVo.subjecttype.equals("3")) {
                            return dynamicBinderSubject.class;
                        } else if (dynamicInfoVo.subjecttype.equals("4")) {
                            return dynamicBinderAritcle.class;
                        } else if (dynamicInfoVo.subjecttype.equals("5")) {
                            return dynamicBinderFollow.class;
                        } else if (dynamicInfoVo.subjecttype.equals("6")) {
                            return dynamicBinderLive.class;
                        } else if (dynamicInfoVo.subjecttype.equals("7")) {
                            return dynamicBinderLesson.class;
                        }
                        return null;
                    }
                });
        return getAdapter(adapter, context);
    }
}
