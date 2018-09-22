package com.code.mvvm.core.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.code.mvvm.callback.CallBack;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.data.pojo.course.CourseRemVo;
import com.code.mvvm.core.data.pojo.course.CourseTypeVo;
import com.code.mvvm.core.data.source.CourseRepository;
import com.mvvm.base.AbsViewModel;
import com.mvvm.stateview.StateConstants;

import static com.code.mvvm.util.Preconditions.checkNotNull;

/**
 * @author：tqzhang on 18/7/31 15:13
 */
public class CourseViewModel extends AbsViewModel<CourseRepository> {

    private MutableLiveData<CourseTypeVo> mCourseType;

    private MutableLiveData<CourseListVo> mCourseListData;

    private MutableLiveData<CourseRemVo> mCourseRemData;

    private MutableLiveData<CourseDetailRemVideoVo> mCourseDetailRemData;

    private MutableLiveData<CourseDetailVo> mCourseDetailData;

    public CourseViewModel(@NonNull Application application) {
        super(application);
    }

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

    public LiveData<CourseDetailVo> getCourseDetailData() {
        if (mCourseDetailData == null) {
            mCourseDetailData = new MutableLiveData<>();
        }
        return mCourseDetailData;
    }

    public LiveData<CourseDetailRemVideoVo> getCourseDetailRemData() {
        if (mCourseDetailRemData == null) {
            mCourseDetailRemData = new MutableLiveData<>();
        }
        return mCourseDetailRemData;
    }


    public void getCourseTypeData() {
        mRepository.loadCourseType(new CallBack<CourseTypeVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseTypeVo courseTypeVo) {
                mCourseType.postValue(courseTypeVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getCourseList(String fCatalogId, String lastId) {
        checkNotNull(fCatalogId);
        mRepository.loadCourseList(fCatalogId, lastId, Constants.PAGE_RN, new CallBack<CourseListVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseListVo courseListVo) {
                mCourseListData.postValue(courseListVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    public void getCourseRemList(String rn) {
        mRepository.loadCourseRemList(new CallBack<CourseRemVo>() {
            @Override
            public void onNoNetWork() {
                loadState.postValue(StateConstants.NET_WORK_STATE);
            }

            @Override
            public void onNext(CourseRemVo courseRemVo) {
                mCourseRemData.postValue(courseRemVo);
                loadState.postValue(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onError(String e) {
                loadState.postValue(StateConstants.ERROR_STATE);
            }
        });
    }

    private void getCourseDetailData(String id) {
        mRepository.loadCourseDetailData(id);
    }

    private void getCourseDetailRemData(String id,
                                        String fCatalogId,
                                        String sCatalogId,
                                        String teacherId,
                                        String rn) {
        mRepository.loadCourseDetailRemData(id, fCatalogId, sCatalogId, teacherId, rn);

    }

    public void getRequestMerge() {
        mRepository.loadCourseDetailMerge();
    }
}
