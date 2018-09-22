package com.mvvm.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

/**
 * @author：tqzhang on 18/3/12 19:22
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected LoadManager loadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏
        initStatusBar();
        //设置布局内容
        setContentView(getLayoutId());
        loadManager = new LoadManager.Builder()
                .setViewParams(this)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View v) {
                        onStateRefresh();
                    }
                })
                .build();
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();

    }

    /**
     *
     */
    protected  void onStateRefresh(){

    }


    protected void initStatusBar() {

    }


    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化toolbar
     */
    protected void initToolBar() {
        //doSomething
    }


    /**
     * 显示进度条
     */
    public void showProgressBar() {
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

