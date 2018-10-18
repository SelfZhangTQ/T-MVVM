package com.code.mvvm.network;

import com.code.mvvm.config.URL;
import com.code.mvvm.core.data.pojo.activity.ActivityListVo;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.pojo.article.ArticleVo;
import com.code.mvvm.core.data.pojo.banner.BannerListVo;
import com.code.mvvm.core.data.pojo.book.BookListVo;
import com.code.mvvm.core.data.pojo.book.BookTypeVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawRecommendVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawTypeVo;
import com.code.mvvm.core.data.pojo.home.HomeListVo;
import com.code.mvvm.core.data.pojo.live.LiveDetailsVo;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.pojo.live.LiveTypeVo;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.pojo.material.MaterialTypeVo;
import com.code.mvvm.core.data.pojo.material.MaterialVo;
import com.code.mvvm.core.data.pojo.qa.QaListVo;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author tqzhang
 */
public interface ApiService {

    @POST(URL.HOME_LIST)
    @FormUrlEncoded
    Flowable<HomeListVo> getHomeData(@Field("professionid") String professionId);

    @POST(URL.WORK_LIST)
    @FormUrlEncoded
    Flowable<WorksListVo> getWorkData(@Field("corrected") String corrected, @Field("rn") String rn);

    @POST(URL.WORK_MORE_LIST)
    @FormUrlEncoded
    Flowable<WorksListVo> getWorkMoreData(@Field("last_id") String last_id, @Field("utime") String utime, @Field("rn") String rn);

    @POST(URL.WORK_DETAIL)
    @FormUrlEncoded
    Flowable<WorkDetailVo> getWorkDetailData(@Field("correctid") String correctid);

    @POST(URL.WORK_RECOMMEND)
    @FormUrlEncoded
    Flowable<WorkRecommentVo> getWorkRecommendData(@Field("correctid") String correctid);


    @POST(URL.BANNER)
    @FormUrlEncoded
    Flowable<BannerListVo> getBannerData(@Field("pos_type") String posType,
                                           @Field("f_catalog_id") String f_catalog_id,
                                           @Field("s_catalog_id") String s_catalog_id,
                                           @Field("t_catalog_id") String t_catalog_id,
                                           @Field("province") String province
    );

    @GET(URL.VIDEO_TYPE)
    Flowable<CourseTypeVo> getCourseType();

    @GET(URL.RECOMMEND_COURSE)
    Flowable<CourseRemVo> getCourseRemList();

    @POST(URL.VIDEO_LIST)
    @FormUrlEncoded
    Flowable<CourseListVo> getCourseList(@Field("f_catalog_id") String f_catalog_id, @Field("lastid") String lastid, @Field("rn") String rn);

    @GET(URL.MATERIAL_TYPE)
    Flowable<MaterialTypeVo> getMaterialInfo();

    @GET(URL.LIVING_TYPE)
    Flowable<LiveTypeVo> getLiveType();

    @GET(URL.ARTICLE_TYPE)
    Flowable<ArticleTypeVo> getArticleType();

    @GET(URL.FOLLOW_D_TYPE)
    Flowable<FollowDrawTypeVo> getFollowDrawType();

    @GET(URL.BOOK_TYPE)
    Flowable<BookTypeVo> getBookType();

    @POST(URL.DYNAMIC_LIST)
    @FormUrlEncoded
    Flowable<DynamicListVo> getDynamicList(@Field("rn") String rn, @Field("token") String token, @Field("lastid") String lastid);


    @POST(URL.ARTICLE_LIST)
    @FormUrlEncoded
    Flowable<ArticleVo> getArticleRemList(@Field("lecture_level1") String lecture_level1, @Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.RECOMMEND_BOOKS_LIST)
    @FormUrlEncoded
    Flowable<BookListVo> getBookList(@Field("f_catalog_id") String f_catalog_id, @Field("last_id") String last_id, @Field("rn") String rn);

    @POST(URL.MATERIAL_SUBJECT_LIST)
    @FormUrlEncoded
    Flowable<MaterialRecommendVo> getMaterialRemList(@Field("f_catalog_id") String f_catalog_id, @Field("lastid") String lastid, @Field("rn") String rn);


    @POST(URL.MATERIAL_LIST_NEW)
    @FormUrlEncoded
    Flowable<MaterialVo> getMaterialList(@Field("f_catalog_id") String f_catalog_id, @Field("mlevel") String mlevel, @Field("rn") String rn);

    @POST(URL.MATERIAL_LIST)
    @FormUrlEncoded
    Flowable<MaterialVo> getMaterialMoreList(@Field("f_catalog_id") String f_catalog_id, @Field("mlevel") String mlevel, @Field("lasttid") String lasttid, @Field("rn") String rn);

    @POST(URL.FOLLOW_RECOMMEND)
    @FormUrlEncoded
    Flowable<FollowDrawRecommendVo> getFollowDrawRemList(@Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.FOLLOW_LIST)
    @FormUrlEncoded
    Flowable<FollowDrawRecommendVo> getollowDrawList(@Field("maintypeid") String maintypeid, @Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.ACTIVITY_LIST)
    @FormUrlEncoded
    Flowable<ActivityListVo> getActivityList(@Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.QA_LIST)
    @FormUrlEncoded
    Flowable<QaListVo> getQAList(@Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.LIVE_LIST)
    @FormUrlEncoded
    Flowable<LiveListVo> getLiveRem(@Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.LIVING_LIST)
    @FormUrlEncoded
    Flowable<LiveListVo> getLiveList(@Field("f_catalog_id") String f_catalog_id, @Field("lastid") String lastid, @Field("rn") String rn);

    @POST(URL.VIDEO_DETAILS_DATA)
    @FormUrlEncoded
    Flowable<CourseDetailVo> getVideoDetailsData(@Field("courseid") String courseId, @Field("notbrowse") String notBrowse);

    @POST(URL.LIVE_DETAILS_DATA)
    @FormUrlEncoded
    Flowable<LiveDetailsVo> getLiveData(@Field("liveid") String liveid);

    @POST(URL.VIDEO_DETAILS_ABOUT_DATA)
    @FormUrlEncoded
    Flowable<CourseDetailRemVideoVo> getVideoAboutData(@Field("courseid") String courseId
            , @Field("f_catalog_id") String f_catalog_id
            , @Field("s_catalog_id") String s_catalog_id
            , @Field("teacherid") String teacherId
            , @Field("rn") String rn
    );
}
