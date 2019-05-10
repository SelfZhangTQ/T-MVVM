package com.code.mvvm.core.view.course;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.view.course.holder.CourseRecommendHolder;
import com.code.mvvm.network.ApiService;
import com.code.mvvm.network.rx.RxSubscriber;
import com.code.mvvm.util.DisplayUtil;
import com.mvvm.base.BaseActivity;
import com.mvvm.http.HttpHelper;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author：tqzhang on 18/7/7 15:09
 */
public class VideoDetailsActivity extends BaseActivity {
    private StandardGSYVideoPlayer mVideoPlayer;
    private OrientationUtils mOrientationUtils;

    protected RecyclerView mRecyclerView;

    private String lessonId;
    private String teacherId;
    private String fCatalogId;
    private String sCatalogId;
    private CourseDetailVo.DataEntity lessonData = null;

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        lessonId = getIntent().getStringExtra("course_id");
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVideoPlayer = findViewById(R.id.video_player);
        int widthVideo = DisplayUtil.getScreenWidth(this);
        int heightVideo = widthVideo * 9 / 16;
        mVideoPlayer.getLayoutParams().width = widthVideo;
        mVideoPlayer.getLayoutParams().height = heightVideo;
        mOrientationUtils = new OrientationUtils(this, mVideoPlayer);
        mOrientationUtils.setEnable(false);
        mVideoPlayer.setIsTouchWiget(true);
        mVideoPlayer.setRotateViewAuto(false);
        mVideoPlayer.setLockLand(false);
        mVideoPlayer.setShowFullAnimation(false);
        mVideoPlayer.setNeedLockFull(true);
        mVideoPlayer.setEnlargeImageRes(R.drawable.player_controller_full_screen);
        mVideoPlayer.setShrinkImageRes(R.drawable.player_controller_small_screen);
        mVideoPlayer.getFullscreenButton().setOnClickListener(v -> {
            //直接横屏
            mOrientationUtils.resolveByClick();
            mVideoPlayer.startWindowFullscreen(VideoDetailsActivity.this, true, true);
        });

        mVideoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                mOrientationUtils.setEnable(true);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);

                if (mOrientationUtils != null) {
                    mOrientationUtils.backToProtVideo();
                }
            }
        });
        getNetWorkData();

    }



    private void getNetWorkData() {
        if (TextUtils.isEmpty(lessonId)) {
            //页面加载错误
            return;
        }
        HttpHelper.getInstance().create(ApiService.class).getVideoDetailsData(lessonId, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new RxSubscriber<CourseDetailVo>() {

                    @Override
                    public void onSuccess(CourseDetailVo courseDetailVo) {
                        lessonData = courseDetailVo.data;
                        fCatalogId = lessonData.f_catalog_id;
                        sCatalogId = lessonData.s_catalog_id;
                        teacherId = lessonData.teacheruid;
                        ImageView imageView = new ImageView(VideoDetailsActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(VideoDetailsActivity.this).load(courseDetailVo.data.thumb_url).into(imageView);
                        mVideoPlayer.setThumbImageView(imageView);
                        mVideoPlayer.setUp(courseDetailVo.data.sectioin.get(0).videos.get(0).video_info.m3u8url, false, courseDetailVo.data.sectioin.get(0).videos.get(0).title);
                        mVideoPlayer.startPlayLogic();
                        getAboutData();
                    }

                    @Override
                    public void onFailure(String msg,int code) {

                    }


                });


    }

    private void getAboutData() {
        HttpHelper.getInstance().create(ApiService.class).getVideoAboutData(lessonId, fCatalogId, sCatalogId, teacherId, "20")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new RxSubscriber<CourseDetailRemVideoVo>() {

                    @Override
                    public void onSuccess(CourseDetailRemVideoVo courseDetailRemVideoVo) {
                        if (courseDetailRemVideoVo != null && courseDetailRemVideoVo.errno == 0) {
                            setData(courseDetailRemVideoVo);
                            loadManager.showSuccess();
                        }
                    }

                    @Override
                    public void onFailure(String msg,int code) {

                    }

                });
    }


    private void setData(CourseDetailRemVideoVo lessonDetailAboutVideoBean) {
        ItemData items = new ItemData();
        DelegateAdapter adapter = new DelegateAdapter.Builder<>()
        .bind(CourseDetailRemVideoVo.DataBean.CourseListBean.class, new CourseRecommendHolder(VideoDetailsActivity.this)).build();
        mRecyclerView.setAdapter(adapter);
        items.addAll(lessonDetailAboutVideoBean.getData().getCourse_list());
//        mRecyclerView.refreshComplete(items, false);
        adapter.setDatas(items);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (null!=mVideoPlayer){
            mVideoPlayer.onVideoPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null!=mVideoPlayer){
            mVideoPlayer.onVideoResume();
        }
    }
}
