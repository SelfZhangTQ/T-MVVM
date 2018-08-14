package com.code.mvvm;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.basiclibrary.base.BaseActivity;
import com.bottomnavigation.BottomNavigationBar;
import com.bottomnavigation.BottomNavigationItem;
import com.code.mvvm.core.view.course.CourseFragment;
import com.code.mvvm.core.view.home.HomeFragment;
import com.code.mvvm.core.view.swipe.SwipeCorrectFragment;


/**
 * @author zhangtianqiu
 */
public class MainActivity extends BaseActivity {

    private BottomNavigationBar mBottomNavigationBar;
    private HomeFragment mHomeFragment;
    //    private CorrectFragment mCorrectFragment;
    private SwipeCorrectFragment mCorrectFragment;
    private CourseFragment mCourseFragment;
    private MineFragment mMineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();
        initView();
        //初始化底部tabBar
        initNavBar();
        //初始化fragment
        initFragment(0);
//        Observable.interval(5000, 5000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object t) {
//                        SysUtil.getCacheInfo(MainActivity.this);
////
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                    }
//                });
    }


    private void initView() {
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
    }


    private void initNavBar() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.tab_home_icon, "首页").setInactiveIconResource(R.mipmap.tab_home_icon_def))
                .addItem(new BottomNavigationItem(R.mipmap.tab_works_icon, "作品").setInactiveIconResource(R.mipmap.tab_works_icon_def))
                .addItem(new BottomNavigationItem(R.mipmap.tab_course_icon, "视频").setInactiveIconResource(R.mipmap.tab_course_icon_def))
                .addItem(new BottomNavigationItem(R.mipmap.tab_mine_icon, "我的").setInactiveIconResource(R.mipmap.tab_mine_icon_def))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                initFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void initFragment(int i) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content, mHomeFragment, "home");
                } else {
                    fragmentTransaction.show(mHomeFragment);
                }
                break;

            case 1:
                if (mCorrectFragment == null) {
//                    mCorrectFragment = CorrectFragment.newInstance();
                    mCorrectFragment = SwipeCorrectFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content, mCorrectFragment, "correct");
                } else {
                    fragmentTransaction.show(mCorrectFragment);
                }
                break;
            case 2:
                if (mCourseFragment == null) {
                    mCourseFragment = CourseFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content, mCourseFragment, "course");
                } else {
                    fragmentTransaction.show(mCourseFragment);
                }
                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance();
                    fragmentTransaction.add(R.id.home_content, mMineFragment, "mine");
                } else {
                    fragmentTransaction.show(mMineFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mHomeFragment != null) {
            fragmentTransaction.hide(mHomeFragment);
        }

        if (mCorrectFragment != null) {
            fragmentTransaction.hide(mCorrectFragment);
        }
        if (mCourseFragment != null) {
            fragmentTransaction.hide(mCourseFragment);
        }

        if (mMineFragment != null) {
            fragmentTransaction.hide(mMineFragment);
        }
    }
}
