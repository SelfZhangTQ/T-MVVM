package com.code.mvvm.util;

import android.content.Context;

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
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
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
import com.trecyclerview.multitype.ClassLinker;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;
import com.trecyclerview.progressindicator.ProgressStyle;
import com.trecyclerview.view.FootViewHolder;
import com.trecyclerview.view.HeaderViewHolder;

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

    public MultiTypeAdapter getNoHeadAdapter(MultiTypeAdapter.Builder builder, Context context) {
        return builder
                .bind(FootVo.class, new FootViewHolder(context, ProgressStyle.Pacman))
                .build();
    }

    public MultiTypeAdapter getNoFootAdapter(MultiTypeAdapter.Builder builder, Context context) {
        return builder
                .bind(HeaderVo.class, new HeaderViewHolder(context, ProgressStyle.Pacman))
                .build();
    }

    public MultiTypeAdapter getAdapter(MultiTypeAdapter.Builder builder, Context context) {
        return builder.bind(HeaderVo.class, new HeaderViewHolder(context, ProgressStyle.Pacman))
                .bind(FootVo.class, new FootViewHolder(context, ProgressStyle.Pacman))
                .build();
    }

    public MultiTypeAdapter getWorkAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(WorksListVo.Works.class, new CorrectItemHolder(context)), context);
    }

    public MultiTypeAdapter getSwipeCorrectAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(WorksListVo.Works.class, new CorrectItemHolder(context)), context);
    }

    public MultiTypeAdapter getBookAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(BookVo.class, new BookListHolder(context)), context);
    }

    public MultiTypeAdapter getActivityAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(ActivityListVo.DataBean.class, new ActivityItemHolder(context)), context);
    }

    public MultiTypeAdapter getArticleAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bindArray(ArticleInfoVo.class, new ArticleRem1ItemHolder(context), new ArticleRem2ItemHolder(context), new ArticleRem3ItemHolder(context))
                .withClass((ClassLinker<ArticleInfoVo>) (position, listBean) -> {
                    if ("1".equals(listBean.thumbtype)) {
                        return ArticleRem1ItemHolder.class;
                    } else if ("2".equals(listBean.thumbtype)) {
                        return ArticleRem2ItemHolder.class;
                    } else if ("3".equals(listBean.thumbtype)) {
                        return ArticleRem3ItemHolder.class;
                    }
                    return null;
                }), context);
    }

    public MultiTypeAdapter getCourseRemAdapter(Context context) {
        return getNoFootAdapter(new MultiTypeAdapter.Builder<>()
                .bind(TypeVo.class, new TypeItemView(context))
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(LiveRecommendVo.class, new HomeLiveItemView(context)), context);

    }

    public MultiTypeAdapter getCourseListAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(CourseInfoVo.class, new CourseItemHolder(context)), context);

    }

    public MultiTypeAdapter getFollowAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(FollowDrawInfoVo.class, new FollowDrawListHolder(context)), context);
    }

    public MultiTypeAdapter getQaAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(QaListVo.DataBean.class, new QaListItemHolder(context)), context);
    }

    public MultiTypeAdapter getMaterialListAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(MaterialInfoVo.class, new MaterialListHolder(context)), context);
    }

    public MultiTypeAdapter getMaterialRemAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(MatreialSubjectVo.class, new MaterialItemHolder(context)), context);
    }

    public MultiTypeAdapter getLiveAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(LiveRecommendVo.class, new LiveListItemHolder(context)), context);
    }

    public MultiTypeAdapter getLiveRemAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bind(LiveRecommendVo.class, new LiveItemHolder(context)), context);
    }

    public MultiTypeAdapter getHomeAdapter(Context context) {
        return getNoFootAdapter(new MultiTypeAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(TypeVo.class, new TypeItemView(context))
                .bind(CatagoryVo.class, new CategoryItemView(context))
                .bind(BookList.class, new BookItemHolder(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(LiveRecommendVo.class, new HomeLiveItemView(context))
                .bind(MatreialSubjectVo.class, new HomeMaterialItemView(context)), context);
    }

    public MultiTypeAdapter getDynamicAdapter(Context context) {
        return getAdapter(new MultiTypeAdapter.Builder<>()
                .bindArray(DynamicInfoVo.class, new DynamicCorrectHolder(context),
                        new DynamicWorkHolder(context),
                        new DynamicSubjectHolder(context),
                        new DynamicArticleHolder(context),
                        new DynamicCourseHolder(context),
                        new DynamicLiveHolder(context),
                        new DynamicFollowHolder(context))
                .withClass((ClassLinker<DynamicInfoVo>) (position, dynamicInfoVo) -> {
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
                }), context);
    }
}
