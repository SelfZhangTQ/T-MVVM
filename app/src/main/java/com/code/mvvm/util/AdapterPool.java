package com.code.mvvm.util;

import android.content.Context;
import android.support.annotation.NonNull;

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
import com.code.mvvm.core.data.pojo.material.MaterialListVo;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.view.activity.viewholder.ActivityItemHolder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem1ItemHolder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem2ItemHolder;
import com.code.mvvm.core.view.article.viewholder.ArticleRem3ItemHolder;
import com.code.mvvm.core.view.book.viewholder.BookItemHolder;
import com.code.mvvm.core.view.book.viewholder.BookListHolder;
import com.code.mvvm.core.view.common.FootItemViewBinder;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.viewholder.CorrectItemHolder;
import com.code.mvvm.core.view.course.viewholder.CourseItemHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicArticleHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicCorrectHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicCourseHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicFollowHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicLiveHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicSubjectHolder;
import com.code.mvvm.core.view.dynamic.viewholder.DynamicWorkHolder;
import com.code.mvvm.core.view.followdraw.viewholder.FollowDrawListHolder;
import com.code.mvvm.core.view.home.viewholder.CategoryItemView;
import com.code.mvvm.core.view.home.viewholder.HomeLiveItemView;
import com.code.mvvm.core.view.home.viewholder.HomeMaterialItemView;
import com.code.mvvm.core.view.home.viewholder.HomeVideoItemView;
import com.code.mvvm.core.view.live.viewholder.LiveItemHolder;
import com.code.mvvm.core.view.live.viewholder.LiveListItemHolder;
import com.code.mvvm.core.view.material.viewholder.MaterialItemHolder;
import com.code.mvvm.core.view.material.viewholder.MaterialListHolder;
import com.code.mvvm.core.view.qa.viewholder.QaListItemHolder;
import com.code.mvvm.widget.banner.BannerItemView;
import com.trecyclerview.entity.FootInfo;
import com.trecyclerview.entity.HeaderInfo;
import com.trecyclerview.multitype.AbsItemView;
import com.trecyclerview.multitype.ClassLinker;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.view.HeaderItemView;

/**
 * @author：tqzhang  on 18/8/3 16:25
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

    public MultiTypeAdapter getWorkAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerListVo.class, new BannerItemView(context));
        adapter.register(WorksListVo.Works.class, new CorrectItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getSwipeCorrectAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerListVo.class, new BannerItemView(context));
        adapter.register(WorksListVo.Works.class, new CorrectItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getBookAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BookVo.class, new BookListHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getActivityAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(ActivityListVo.DataBean.class, new ActivityItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getArticleAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(ArticleInfoVo.class).to(new ArticleRem1ItemHolder(context), new ArticleRem2ItemHolder(context), new ArticleRem3ItemHolder(context))
                .withClassLinker(new ClassLinker<ArticleInfoVo>() {
                    @NonNull
                    @Override
                    public Class<? extends AbsItemView<ArticleInfoVo, ?>> index(int position, @NonNull ArticleInfoVo listBean) {
                        if ("1".equals(listBean.thumbtype)) {
                            return ArticleRem1ItemHolder.class;
                        } else if ("2".equals(listBean.thumbtype)) {
                            return ArticleRem2ItemHolder.class;
                        } else if ("3".equals(listBean.thumbtype)) {
                            return ArticleRem3ItemHolder.class;
                        }
                        return null;
                    }
                });
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getCourseRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(TypeVo.class, new TypeItemView(context));
        adapter.register(BannerListVo.class, new BannerItemView(context));
        adapter.register(CourseInfoVo.class, new HomeVideoItemView(context));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(context));
        return getNoFootAdapter(adapter, context);

    }

    public MultiTypeAdapter getCourseListAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(CourseInfoVo.class, new CourseItemHolder(context));
        return getAdapter(adapter, context);

    }

    public MultiTypeAdapter getFollowAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(FollowDrawInfoVo.class, new FollowDrawListHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getQaAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(QaListVo.DataBean.class, new QaListItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getMaterialListAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(MaterialInfoVo.class, new MaterialListHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getMaterialRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(MatreialSubjectVo.class, new MaterialItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getLiveAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(LiveRecommendVo.class, new LiveListItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getLiveRemAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(LiveRecommendVo.class, new LiveItemHolder(context));
        return getAdapter(adapter, context);
    }

    public MultiTypeAdapter getHomeAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(BannerListVo.class, new BannerItemView(context));
        adapter.register(TypeVo.class, new TypeItemView(context));
        adapter.register(CatagoryVo.class, new CategoryItemView(context));
        adapter.register(BookList.class, new BookItemHolder(context));
        adapter.register(CourseInfoVo.class, new HomeVideoItemView(context));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(context));
        adapter.register(MaterialListVo.class, new HomeMaterialItemView(context));
        return getNoFootAdapter(adapter, context);
    }

    public MultiTypeAdapter getDynamicAdapter(Context context) {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(DynamicInfoVo.class)
                .to(new DynamicCorrectHolder(context),
                        new DynamicWorkHolder(context),
                        new DynamicSubjectHolder(context),
                        new DynamicArticleHolder(context),
                        new DynamicCourseHolder(context),
                        new DynamicLiveHolder(context),
                        new DynamicFollowHolder(context)
                )
                .withClassLinker(new ClassLinker<DynamicInfoVo>() {
                    @NonNull
                    @Override
                    public Class<? extends AbsItemView<DynamicInfoVo, ?>> index(int position, @NonNull DynamicInfoVo dynamicInfoVo) {
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
                    }
                });
        return getAdapter(adapter, context);
    }
}
