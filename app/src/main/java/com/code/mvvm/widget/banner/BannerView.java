package com.code.mvvm.widget.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.banner.BannerVo;
import com.code.mvvm.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author：tqzhang  on 18/5/8 15:38
 */
public class BannerView extends RelativeLayout {
    ViewPager viewPager;
    LinearLayout points;
    private CompositeDisposable compositeSubscription;
    //默认轮播时间，10s
    private int delayTime = 10;
    private List<ImageView> imageViewList;
    private List<BannerVo> bannerList;
    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;
    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;
    //当前页的下标
    private int currentPos;
    private BannerAdapter bannerAdapter;


    public BannerView(Context context) {
        this(context, null);
    }


    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.common_banner, this, true);
        imageViewList = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.layout_banner_viewpager);
        points = (LinearLayout) findViewById(R.id.layout_banner_points_group);
    }


    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {
        this.delayTime = time;
        return this;
    }


    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {
        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }


    /**
     * 图片轮播需要传入参数
     */
    public void build(List<BannerVo> list) {
        destroy();
        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }
        bannerList = new ArrayList<>();
        bannerList.clear();
        bannerList.addAll(list);
        final int pointSize;
        pointSize = bannerList.size();

        //页面等于2是左滑出现空白，
        if (pointSize == 2) {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getContext(), 5),
                    DisplayUtil.dp2px(getContext(), 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }
        points.getChildAt(0).setBackgroundResource(selectRes);
        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                pos = pos % pointSize;
                currentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        compositeSubscription.clear();
                        break;
                }
            }
        });
        bannerAdapter = new BannerAdapter(imageViewList, pointSize);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setViewPagerOnItemClickListener(new BannerAdapter.ViewPagerOnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                if (mOnBannerItemClickListener == null) {
                    return;
                }

                mOnBannerItemClickListener.onItemClick(position);
            }
        });


        if (bannerList.size() > 1) {
            //图片开始轮播
            startScroll();
            points.setVisibility(VISIBLE);
        } else {
            points.setVisibility(GONE);
        }
    }

    private boolean isStopScroll = false;


    /**
     * 图片开始轮播
     */
    private void startScroll() {
        compositeSubscription = new CompositeDisposable();
        isStopScroll = false;
        Disposable subscription = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isStopScroll) {
                            return;
                        }
                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }

                });
        compositeSubscription.add(subscription);
    }


    /**
     * 图片停止轮播
     */
    private void stopScroll() {
        isStopScroll = true;
    }


    public void destroy() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }


    /**
     * 设置ViewPager的Item点击回调事件
     */
//    @Override
//    public void onItemClick() {
//    BrowserActivity.launch((Activity) getContext(),
//        bannerList.get(currentPos).link,
//        bannerList.get(currentPos).title);
//    }

    public onBannerItemClickListener mOnBannerItemClickListener;


    public void setOnBannerItemClickListener(onBannerItemClickListener mOnBannerItemClickListener) {
        this.mOnBannerItemClickListener = mOnBannerItemClickListener;
    }

    public BannerView setBannerView(OnBindView onBindView) {
        if (onBindView != null) {
            imageViewList.clear();
            imageViewList.addAll(onBindView.bindView());
        }
        return this;
    }

    public interface OnBindView {
        List<ImageView> bindView();
    }

    public interface onBannerItemClickListener {
        void onItemClick(int position);
    }

}
