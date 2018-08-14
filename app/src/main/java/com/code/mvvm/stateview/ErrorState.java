package com.code.mvvm.stateview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.code.mvvm.R;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：zhangtianqiu on 18/7/16 15:07
 */
public class ErrorState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.common_empty_view;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        TextView tv_desc = view.findViewById(R.id.tv_desc);
        if (view.getTag() != null) {
            if (view.getTag().equals("1")) {
                tv_desc.setText("网络不给力～_~");
            } else if (view.getTag().equals("2")) {
                tv_desc.setText("没有数据");
            }

        }
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }
}