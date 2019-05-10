package com.code.mvvm.core.data.source;

import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.StringUtil;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;

import io.reactivex.Flowable;


/**
 * @author：tqzhang on 18/7/31 15:14
 */
public class CourseRepository extends BaseRepository {

    public static String EVENT_KEY_COURSE_LIDT = null;

    public static String EVENT_KEY_COURSE_RED = null;

    public static String EVENT_KEY_COURSE = null;

    public CourseRepository() {
        if (EVENT_KEY_COURSE_LIDT == null) {
            EVENT_KEY_COURSE_LIDT = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_RED == null) {
            EVENT_KEY_COURSE_RED = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE == null) {
            EVENT_KEY_COURSE = StringUtil.getEventKey();
        }

    }

    private Flowable<CourseDetailVo> mCourseDetailDataObservable;

    private Flowable<CourseDetailRemVideoVo> mCourseDetailRemVideoObservable;

    public void loadCourseList(String fCatalogId, String lastId, String rn) {
        addDisposable(apiService.getCourseList(fCatalogId, lastId, rn)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<CourseListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        postState(StateConstants.NET_WORK_STATE);
                    }

                    @Override
                    public void onSuccess(CourseListVo courseListVo) {
                        postData(EVENT_KEY_COURSE_LIDT, fCatalogId, courseListVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg,int code) {
                        postState(StateConstants.ERROR_STATE);
                    }
                }));
    }

    public void loadCourseRemList() {
        addDisposable(apiService.getCourseRemList()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<CourseRemVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(CourseRemVo courseRemVo) {
                        postData(EVENT_KEY_COURSE_RED, courseRemVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg,int code) {
                    }
                }));
    }

    public void loadCourseType() {
        addDisposable(apiService.getCourseType()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<CourseTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(CourseTypeVo courseTypeVo) {
                        postData(EVENT_KEY_COURSE, courseTypeVo);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg,int code) {
                    }
                }));
    }

    public void loadCourseDetailData(String id) {
        mCourseDetailDataObservable = apiService.getVideoDetailsData(id, "");

    }

    public void loadCourseDetailRemData(String id, String fCatalogId, String sCatalogId, String teacherId, String rn) {
        mCourseDetailRemVideoObservable = apiService.getVideoAboutData(id, fCatalogId, sCatalogId, teacherId, rn);
    }


    public void loadCourseDetailMerge() {

    }

}
