package com.code.mvvm.core.view.correct;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.R;
import com.code.mvvm.base.LifecycleActivity;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.correct.CorrectDetailVo;
import com.code.mvvm.core.data.pojo.correct.CorrectInfoVo;
import com.code.mvvm.core.data.pojo.correct.CorrectRecommentVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.viewholder.CorrectPicViewBinder;
import com.code.mvvm.core.view.correct.viewholder.CorrectRemItemViewBinder;
import com.code.mvvm.core.view.home.viewholder.HomeCourseItemView;
import com.code.mvvm.core.view.home.viewholder.HomeLiveItemView;
import com.code.mvvm.core.viewmodel.CorrectViewModel;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.lang.ref.WeakReference;


/**
 * @author：zhangtianqiu on 18/7/16 18:06
 */
public class CorrectDetailsActivity extends LifecycleActivity<CorrectViewModel> {

    protected TRecyclerView mRecyclerView;
    private MultiTypeAdapter adapter;
    protected Items items = new Items();
    private String correctId;

    private WeakReference<CorrectDetailsActivity> weakReference;

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_correct_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        weakReference = new WeakReference<>(this);
        correctId = getIntent().getStringExtra("correctid");
        initAdapter();
        initRecyclerView();
        getNetWorkData();
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new MultiTypeAdapter();
        adapter.register(CorrectDetailVo.class, new CorrectPicViewBinder(weakReference.get()));
        adapter.register(CourseInfoVo.class, new HomeCourseItemView(weakReference.get()));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(weakReference.get()));
        adapter.register(CorrectInfoVo.class, new CorrectRemItemViewBinder(weakReference.get()));
        adapter.register(TypeVo.class, new TypeItemView());

    }

    @Override
    protected CorrectViewModel createViewModelProviders() {
        return VMProviders(this, CorrectViewModel.class);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getCorrectDetailData().observe(this, new Observer<CorrectDetailVo>() {
            @Override
            public void onChanged(@Nullable CorrectDetailVo correctDetailVo) {
                items.add(correctDetailVo);
                adapter.setItems(items);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        mViewModel.getCorrectRecommentData().observe(this, new Observer<CorrectRecommentVo>() {
            @Override
            public void onChanged(@Nullable CorrectRecommentVo correctRecommentVo) {
                if (correctRecommentVo == null) {
                    return;
                }
                if (correctRecommentVo.data.course.size() > 0) {
                    if (correctRecommentVo.data.live.size() > 0) {
                        items.add(new TypeVo("直播推荐"));
                        items.addAll(correctRecommentVo.data.live);
                    }
                    items.add(new TypeVo("视频课程"));
                    items.addAll(correctRecommentVo.data.course);
                    items.add(new TypeVo("精彩批改"));
                    items.addAll(correctRecommentVo.data.content);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getNetWorkData() {
        mViewModel.getCorrectMergeData(correctId);
    }

    public static void start(Context context, String correctid) {
        Intent starter = new Intent(context, CorrectDetailsActivity.class);
        starter.putExtra("correctid", correctid);
        context.startActivity(starter);
    }
}
