package com.code.mvvm.core.data.source;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.network.rx.RxSubscriber;
import com.mvvm.http.rx.RxSchedulers;

import rx.Observable;

/**
 * @author：tqzhang  on 18/7/31 15:14
 */
public class CourseRepository extends BaseRepository {


    private Observable<CourseDetailVo> mCourseDetailDataObservable;

    private Observable<CourseDetailRemVideoVo> mCourseDetailRemVideoObservable;

    public void loadCourseList(String fCatalogId,String lastId, String rn, final CallBack<CourseListVo> onResultCallBack) {
        addSubscribe(apiService.getCourseList(fCatalogId,lastId,rn)
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<CourseListVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        onResultCallBack.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(CourseListVo courseListVo) {
                        onResultCallBack.onNext(courseListVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        onResultCallBack.onError(msg);
                    }
                }));
    }

    public void loadCourseRemList(final CallBack<CourseRemVo> onResultCallBack) {
        addSubscribe(apiService.getCourseRemList()
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<CourseRemVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        onResultCallBack.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(CourseRemVo courseRemVo) {
                        onResultCallBack.onNext(courseRemVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        onResultCallBack.onError(msg);
                    }
                }));
    }

    public void loadCourseType(final CallBack<CourseTypeVo> listener) {
        addSubscribe(apiService.getCourseType()
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<CourseTypeVo>() {
                    @Override
                    protected void onNoNetWork() {
                        super.onNoNetWork();
                        listener.onNoNetWork();
                    }

                    @Override
                    public void onSuccess(CourseTypeVo courseTypeVo) {
                        listener.onNext(courseTypeVo);
                    }

                    @Override
                    public void onFailure(String msg) {
                        listener.onError(msg);
                    }
                }));
    }

    public void loadCourseDetailData(String id) {
        mCourseDetailDataObservable=apiService.getVideoDetailsData(id,"");

    }

    public void loadCourseDetailRemData(String id, String fCatalogId, String sCatalogId, String teacherId, String rn) {
        mCourseDetailRemVideoObservable=apiService.getVideoAboutData(id,fCatalogId,sCatalogId,teacherId,rn);
    }


    public void loadCourseDetailMerge() {

    }

}
