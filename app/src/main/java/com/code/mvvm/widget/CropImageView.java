package com.code.mvvm.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author：tqzhang  on 18/7/3 19:37
 */
public class CropImageView extends AppCompatImageView
{

    public CropImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setup();
    }

    public CropImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setup();
    }

    public CropImageView(Context context)
    {
        super(context);
        setup();
    }

    private void setup()
    {
        setScaleType(ScaleType.CENTER_CROP);
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected boolean setFrame(int frameLeft, int frameTop, int frameRight,
                               int frameBottom)
    {
        float frameWidth = frameRight - frameLeft;
        float frameHeight = frameBottom - frameTop;
        float originalImageWidth = 0;
        float originalImageHeight = 0;
        if (getDrawable() != null)
        {

            originalImageWidth = (float) getDrawable().getIntrinsicWidth();
            originalImageHeight = (float) getDrawable().getIntrinsicHeight();
        }

        float usedScaleFactor = 1;

        if ((frameWidth > originalImageWidth)
                || (frameHeight > originalImageHeight))
        {
            float fitHorizontallyScaleFactor = frameWidth / originalImageWidth;
            float fitVerticallyScaleFactor = frameHeight / originalImageHeight;

            usedScaleFactor = Math.max(fitHorizontallyScaleFactor,
                    fitVerticallyScaleFactor);
        }

        float newImageWidth = originalImageWidth * usedScaleFactor;
        float newImageHeight = originalImageHeight * usedScaleFactor;

        Matrix matrix = getImageMatrix();
        matrix.setScale(usedScaleFactor, usedScaleFactor, 0, 0);
        int h= (int) (-(newImageHeight-frameHeight)/6);
        matrix.postTranslate((frameWidth - newImageWidth) / 2,h);
        setImageMatrix(matrix);
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom);
    }

}
