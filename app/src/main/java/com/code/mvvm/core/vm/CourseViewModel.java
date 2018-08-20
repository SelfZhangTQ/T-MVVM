package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.OnResultCallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.core.data.source.CourseRepository;

/**
 * @author：zhangtianqiu on 18/7/31 15:13
 */
public class CourseViewModel extends BaseViewModel<CourseRepository> {
    public CourseViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<CourseTypeVo> mCourseType;
    private MutableLiveData<CourseListVo> mCourseListData;
    private MutableLiveData<CourseRemVo> mCourseRemData;

    public LiveData<CourseTypeVo> getCourseType() {
        if (mCourseType == null) {
            mCourseType = new MutableLiveData<>();
        }
        return mCourseType;
    }

    public LiveData<CourseListVo> getCourseList() {
        if (mCourseListData == null) {
            mCourseListData = new MutableLiveData<>();
        }
        return mCourseListData;
    }


    public LiveData<CourseRemVo> getCourseRemList() {
        if (mCourseRemData == null) {
            mCourseRemData = new MutableLiveData<>();
        }
        return mCourseRemData;
    }


    public void getCourseTypeData() {
        mRepository.loadCourseType(new OnResultCallBack<CourseTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseTypeVo courseTypeVo) {
                mCourseType.postValue(courseTypeVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getCourseList(String fCatalogId,String lastId, String rn) {
        mRepository.loadCourseList(fCatalogId,lastId, rn, new OnResultCallBack<CourseListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseListVo courseListVo) {
                mCourseListData.postValue(courseListVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }

    public void getCourseRemList(String rn) {
        mRepository.loadCourseRemList(rn, new OnResultCallBack<CourseRemVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(Constants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseRemVo courseRemVo) {
                mCourseRemData.postValue(courseRemVo);
                loadState.postValue(Constants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(Constants.ERROR_STATE);
            }
        });
    }
}
