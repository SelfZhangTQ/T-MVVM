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
public class CommonActivity extends BaseActivity {
    public final static String MATERIAL = "MaterialFragment";
    public final static String FOLLOWDRAW = "FollowDrawingFragment";
    public final static String ARTIALE = "ArticleFragment";
    public final static String LIVE = "LiveFragment";
    public final static String BOOK = "BookFragment";
    public final static String TRICKS = "DynamicFragment";
    public final static String ACTIVITY = "ActivityListFragment";
    public final static String QA = "QAListFragment";


    private String tag;
    private String title;

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
        barBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            tag = intent.getStringExtra("tag");
            title = intent.getStringExtra("title");
        }
        barTitle.setText(title);
    }

    private void showFragment() {
        switch (tag) {
            //素材
            case MATERIAL:
                commitFragment(new MaterialFragment());
                break;
            //精讲
            case ARTIALE:
                commitFragment(new ArticleFragment());
                break;
            //跟着画
            case FOLLOWDRAW:
                commitFragment(new FollowDrawFragment());
                break;
            case TRICKS:
                commitFragment(new DynamicFragment());
                break; //课程
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
        }

    }

    public void commitFragment(BaseFragment baseFragment) {
        ft.replace(R.id.fragment_content, baseFragment).commit();
    }

    public static void start(Context context, String tag, String title) {
        Intent starter = new Intent(context, CommonActivity.class);
        starter.putExtra("tag", tag);
        starter.putExtra("title", title);
        context.startActivity(starter);
    }
}
