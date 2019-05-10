package com.code.mvvm.util;

import android.content.Context;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.OneToMany;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.core.data.pojo.book.BookVo;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.dynamic.DynamicInfoVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.core.data.pojo.home.CategoryVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialInfoVo;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.view.activity.holder.ActivityItemHolder;
import com.code.mvvm.core.view.article.holder.ArticleRem1ItemHolder;
import com.code.mvvm.core.view.article.holder.ArticleRem2ItemHolder;
import com.code.mvvm.core.view.article.holder.ArticleRem3ItemHolder;
import com.code.mvvm.core.view.book.holder.BookItemHolder;
import com.code.mvvm.core.view.book.holder.BookListHolder;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.holder.CorrectItemHolder;
import com.code.mvvm.core.view.course.holder.CourseItemHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicArticleHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicCorrectHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicCourseHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicFollowHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicLiveHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicSubjectHolder;
import com.code.mvvm.core.view.dynamic.holder.DynamicWorkHolder;
import com.code.mvvm.core.view.followdraw.holder.FollowDrawListHolder;
import com.code.mvvm.core.view.home.holder.CategoryItemView;
import com.code.mvvm.core.view.home.holder.HomeLiveItemView;
import com.code.mvvm.core.view.home.holder.HomeMaterialItemView;
import com.code.mvvm.core.view.live.holder.LiveItemHolder;
import com.code.mvvm.core.view.live.holder.LiveListItemHolder;
import com.code.mvvm.core.view.material.holder.MaterialItemHolder;
import com.code.mvvm.core.view.material.holder.MaterialListHolder;
import com.code.mvvm.core.view.qa.holder.QaListItemHolder;
import com.code.mvvm.widget.banner.BannerItemView;

/**
 * @author：tqzhang on 18/8/3 16:25
 */
public class AdapterPool {

    private static AdapterPool adapterPool;

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



    public DelegateAdapter.Builder getWorkAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(WorksListVo.Works.class, new CorrectItemHolder(context));
    }

    public DelegateAdapter.Builder getBookAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(BookVo.class, new BookListHolder(context));
    }

    public DelegateAdapter.Builder getActivityAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(ActivityListVo.DataBean.class, new ActivityItemHolder(context));
    }

    public DelegateAdapter.Builder getArticleAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bindArray(ArticleInfoVo.class, new ArticleRem1ItemHolder(context), new ArticleRem2ItemHolder(context), new ArticleRem3ItemHolder(context))
                .withClass((OneToMany<ArticleInfoVo>) (position, listBean) -> {
                    if ("1".equals(listBean.thumbtype)) {
                        return ArticleRem1ItemHolder.class;
                    } else if ("2".equals(listBean.thumbtype)) {
                        return ArticleRem2ItemHolder.class;
                    } else if ("3".equals(listBean.thumbtype)) {
                        return ArticleRem3ItemHolder.class;
                    }
                    return null;
                });
    }

    public DelegateAdapter.Builder getCourseRemAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(TypeVo.class, new TypeItemView(context))
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(LiveRecommendVo.class, new HomeLiveItemView(context));

    }

    public DelegateAdapter.Builder getCourseListAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(CourseInfoVo.class, new CourseItemHolder(context));

    }

    public DelegateAdapter.Builder getFollowAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(FollowDrawInfoVo.class, new FollowDrawListHolder(context));
    }

    public DelegateAdapter.Builder getQaAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(QaListVo.DataBean.class, new QaListItemHolder(context));
    }

    public DelegateAdapter.Builder getMaterialListAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(MaterialInfoVo.class, new MaterialListHolder(context));
    }

    public DelegateAdapter.Builder getMaterialRemAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(MatreialSubjectVo.class, new MaterialItemHolder(context));
    }

    public DelegateAdapter.Builder getLiveAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(LiveRecommendVo.class, new LiveListItemHolder(context));
    }

    public DelegateAdapter.Builder getLiveRemAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(LiveRecommendVo.class, new LiveItemHolder(context));
    }

    public DelegateAdapter.Builder getHomeAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(TypeVo.class, new TypeItemView(context))
                .bind(CategoryVo.class, new CategoryItemView(context))
                .bind(BookList.class, new BookItemHolder(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(LiveRecommendVo.class, new HomeLiveItemView(context))
                .bind(MatreialSubjectVo.class, new HomeMaterialItemView(context));
    }

    public DelegateAdapter.Builder getDynamicAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bindArray(DynamicInfoVo.class, new DynamicCorrectHolder(context),
                        new DynamicWorkHolder(context),
                        new DynamicSubjectHolder(context),
                        new DynamicArticleHolder(context),
                        new DynamicCourseHolder(context),
                        new DynamicLiveHolder(context),
                        new DynamicFollowHolder(context))
                .withClass((OneToMany<DynamicInfoVo>) (i, dynamicInfoVo) -> {
                    if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_CORRECT)) {
                        return DynamicCorrectHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_WORK)) {
                        return DynamicWorkHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_MATERIAL_SUBJECT)) {
                        return DynamicSubjectHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_ARTICLE)) {
                        return DynamicArticleHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_FOLLOW_DRAW)) {
                        return DynamicFollowHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_LIVE)) {
                        return DynamicLiveHolder.class;
                    } else if (dynamicInfoVo.subjecttype.equals(Constants.TYPE_LESSON)) {
                        return DynamicCourseHolder.class;
                    }
                    return null;
                });
    }
}
