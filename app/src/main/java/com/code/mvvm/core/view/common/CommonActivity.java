package com.code.mvvm.core.view.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basiclibrary.base.BaseActivity;
import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.R;
import com.code.mvvm.core.view.activity.ActivityListFragment;
import com.code.mvvm.core.view.article.ArticleFragment;
import com.code.mvvm.core.view.book.BookFragment;
import com.code.mvvm.core.view.dynamic.DynamicFragment;
import com.code.mvvm.core.view.followdraw.FollowDrawFragment;
import com.code.mvvm.core.view.live.LiveFragment;
import com.code.mvvm.core.view.material.MaterialFragment;
import com.code.mvvm.core.view.qa.QAListFragment;

/**
 * @author：zhangtianqiu on 18/7/2 14:17
 */
public class CommonActivity extends BaseActivity implements View.OnClickListener {
    public final static String MATERIAL = "MaterialFragment";
    public final static String FOLLOWDRAW = "FollowDrawingFragment";
    public final static String ARTIALE = "ArticleFragment";
    public final static String LIVE = "LiveFragment";
    public final static String BOOK = "BookFragment";
    public final static String TRICKS = "DynamicFragment";
    public final static String ACTIVITY = "ActivityListFragment";
    public final static String QA = "QAListFragment";


    private String typeFragment;

    private FragmentTransaction ft;


    private TextView barTitle;

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
        ImageView barBack = findViewById(R.id.iv_back);
        RelativeLayout mTitleLayout = findViewById(R.id.rl_title_bar);
        mTitleLayout.setVisibility(View.VISIBLE);
        barTitle = findViewById(R.id.tv_title);
        getIntentData();
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        showFragment();
        barBack.setOnClickListener(this);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            typeFragment = intent.getStringExtra("type_fragment");
            String titleName = intent.getStringExtra("title_name");
            barTitle.setText(titleName);
        }

    }

    private void showFragment() {
        switch (typeFragment) {
            case MATERIAL:
                commitFragment(new MaterialFragment());
                break;
            case ARTIALE:
                commitFragment(new ArticleFragment());
                break;
            case FOLLOWDRAW:
                commitFragment(new FollowDrawFragment());
                break;
            case TRICKS:
                commitFragment(new DynamicFragment());
                break;
            case BOOK:
                commitFragment(new BookFragment());
                break;
            case LIVE:
                commitFragment(new LiveFragment());
                break;
            case ACTIVITY:
                commitFragment(new ActivityListFragment());
                break;
            case QA:
                commitFragment(new QAListFragment());
                break;
            default:
                break;
        }

    }

    public void commitFragment(BaseFragment baseFragment) {
        ft.replace(R.id.fragment_content, baseFragment).commit();
    }

    public static void start(Context context, String typeFragment, String titleName) {
        Intent starter = new Intent(context, CommonActivity.class);
        starter.putExtra("type_fragment", typeFragment);
        starter.putExtra("title_name", titleName);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
