package com.code.mvvm.core.view.live;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.course.CourseDetailRemVideoVo;
import com.code.mvvm.core.data.pojo.course.CourseDetailVo;
import com.code.mvvm.core.data.pojo.live.LiveDetailsVo;
import com.code.mvvm.core.view.course.holder.CourseRecommendHolder;
import com.code.mvvm.network.ApiService;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.LiveVideoPlayer;
import com.mvvm.base.BaseActivity;
import com.mvvm.http.HttpHelper;
import com.mvvm.http.rx.RxSchedulers;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * @author：tqzhang on 18/7/7 15:09
 */
public class LiveDetailsActivity extends BaseActivity {
    LiveVideoPlayer mVideoPlayer;
    OrientationUtils mOrientationUtils;

    protected RecyclerView mRecyclerView;

    String liveId;
    String liveUrl;
    private String teacherId;
    private String fCatalogId;
    private String sCatalogId;
    private CourseDetailVo.DataEntity lessonData = null;

    private boolean liveType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        liveId = getIntent().getStringExtra("liveId");
        mRecyclerView =  findViewById(R.id.recycler_view);
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
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                mOrientationUtils.resolveByClick();
//                mVideoPlayer.getTitleTextView().setText(((VideoMode) sections.get(sectionPosition).get(position)).getVideoTitle());
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                mVideoPlayer.startWindowFullscreen(LiveDetailsActivity.this, true, true);
            }
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
        if (TextUtils.isEmpty(liveId)) {
            //页面加载错误
            return;
        }
        HttpHelper.getInstance().create(ApiService.class).getLiveData(liveId)
                .compose(RxSchedulers.<LiveDetailsVo>io_main())
                .subscribe(new Subscriber<LiveDetailsVo>() {

                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(final LiveDetailsVo liveData) {
                        liveType = liveData.data.live_status == 2 ? true : false;
                        ImageView imageView = new ImageView(LiveDetailsActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(LiveDetailsActivity.this).load(liveType ? liveData.data.live_thumb_url : liveData.data.recording_thumb_url).into(imageView);
                        if (liveType) {
                            //直播
                            if (TextUtils.isEmpty(liveData.data.playtype) || TextUtils.equals("2", liveData.data.playtype)) {
                                liveUrl = liveData.data.rtmp_url;
                            } else if (TextUtils.equals("1", liveData.data.playtype)) {
                                liveUrl = liveData.data.live_display_url;
                            }
                        } else {
                            //回放
                            liveUrl = liveData.data.videoinfo.m3u8url;
                        }
                        mVideoPlayer.setThumbPlay(false);
                        mVideoPlayer.setThumbImageView(imageView);
                        mVideoPlayer.setUp(liveUrl, false, liveData.data.live_title);
                        mVideoPlayer.startPlayLogic();
                        getData();
                        loadManager.showSuccess();
                    }
                });


    }

    private void getData() {
    }


    private void setData(CourseDetailRemVideoVo lessonDetailAboutVideoBean) {
        ItemData items = new ItemData();
        DelegateAdapter adapter = new DelegateAdapter.Builder<>()
                .bind(CourseDetailRemVideoVo.DataBean.CourseListBean.class, new CourseRecommendHolder(LiveDetailsActivity.this)).build();
        mRecyclerView.setAdapter(adapter);
        for (int i = 0; i < lessonDetailAboutVideoBean.getData().getCourse_list().size(); i++) {
            items.add(lessonDetailAboutVideoBean.getData().getCourse_list().get(i));
        }
        adapter.setDatas(items);
        adapter.notifyDataSetChanged();
    }

}
