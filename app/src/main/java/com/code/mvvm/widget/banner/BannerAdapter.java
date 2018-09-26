package com.code.mvvm.widget.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * @author：tqzhang  on 18/5/8 15:38
 */
public class BannerAdapter extends PagerAdapter{

    private List<ImageView> mList;
    private int pos;
    private int pointSize;
    private ViewPagerOnItemClickListener mViewPagerOnItemClickListener;

    void setViewPagerOnItemClickListener(ViewPagerOnItemClickListener mViewPagerOnItemClickListener) {
        this.mViewPagerOnItemClickListener = mViewPagerOnItemClickListener;
    }

    BannerAdapter(List<ImageView> list, int pointSize) {
        this.mList = list;
        this.pointSize = pointSize;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= mList.size();
        if (position < 0) {
            position = mList.size() + position;
        }
        ImageView v = mList.get(position);
        pos = position;
        v.setScaleType(ImageView.ScaleType.CENTER);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = v.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(v);
        }
        final int finalPosition = position;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (mViewPagerOnItemClickListener != null) {
                    if (pointSize == 2) {
                        mViewPagerOnItemClickListener.onItemClick(finalPosition>=2?finalPosition-2:finalPosition);
                    } else {
                        mViewPagerOnItemClickListener.onItemClick(finalPosition);
                    }
                }
            }
        });
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }


    interface ViewPagerOnItemClickListener {
        void onItemClick(int position);
    }

}
