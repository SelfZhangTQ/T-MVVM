package com.code.mvvm.core.data.source;

import com.basiclibrary.helper.RxSchedulers;
import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.core.data.BaseRepository;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.network.RxSubscriber;

/**
 * @author：zhangtianqiu on 18/7/31 15:14
 */
public class CourseRepository extends BaseRepository {
    public void loadCourseList(String f_catalog_id,String lastid, String rn, final OnResultCallBack<CourseListVo> onResultCallBack) {
        apiService.getCourseList(f_catalog_id,lastid,rn)
                .compose(RxSchedulers.<CourseListVo>io_main())
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
                });
    }

    public void loadCourseRemList(String rn, final OnResultCallBack<CourseRemVo> onResultCallBack) {
        apiService.getCourseRemList()
                .compose(RxSchedulers.<CourseRemVo>io_main())
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
                });
    }

    public void loadCourseType(final OnResultCallBack<CourseTypeVo> listener) {
        apiService.getCourseType()
                .compose(RxSchedulers.<CourseTypeVo>io_main())
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
                });
    }
}
