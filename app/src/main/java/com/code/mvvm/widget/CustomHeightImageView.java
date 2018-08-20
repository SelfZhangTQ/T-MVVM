package com.code.mvvm.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author tqzhang
 */
public class CustomHeightImageView extends AppCompatImageView {

    private double mHeightRatio;
    private int mHeight;

    public CustomHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHeightImageView(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }

    public void setHeight(int height) {
        if (height != mHeight) {
            mHeight = height;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0.0 || mHeight > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            setMeasuredDimension(width, mHeight);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
