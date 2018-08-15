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
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
import com.code.mvvm.core.data.pojo.correct.WorkRecommentVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.viewholder.CorrectPicHolder;
import com.code.mvvm.core.view.correct.viewholder.CorrectRemItemHolder;
import com.code.mvvm.core.view.home.viewholder.HomeCourseItemView;
import com.code.mvvm.core.view.home.viewholder.HomeLiveItemView;
import com.code.mvvm.core.viewmodel.WorkViewModel;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.lang.ref.WeakReference;


/**
 * @author：tqzhang on 18/7/16 18:06
 */
public class WorkDetailsActivity extends LifecycleActivity<WorkViewModel> {

    protected TRecyclerView mRecyclerView;
    private MultiTypeAdapter adapter;
    protected Items items = new Items();
    private String correctId;

    private WeakReference<WorkDetailsActivity> weakReference;

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
        if (getIntent() != null) {
            correctId = getIntent().getStringExtra("correct_id");
        }

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
        adapter.register(WorkDetailVo.class, new CorrectPicHolder(weakReference.get()));
        adapter.register(CourseInfoVo.class, new HomeCourseItemView(weakReference.get()));
        adapter.register(LiveRecommendVo.class, new HomeLiveItemView(weakReference.get()));
        adapter.register(WorkInfoVo.class, new CorrectRemItemHolder(weakReference.get()));
        adapter.register(TypeVo.class, new TypeItemView());

    }

    @Override
    protected WorkViewModel createViewModelProviders() {
        return VMProviders(this, WorkViewModel.class);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getWorkDetailData().observe(this, new Observer<WorkDetailVo>() {
            @Override
            public void onChanged(@Nullable WorkDetailVo workDetailVo) {
                items.add(workDetailVo);
                adapter.setItems(items);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        mViewModel.getWorkRecommentData().observe(this, new Observer<WorkRecommentVo>() {
            @Override
            public void onChanged(@Nullable WorkRecommentVo workRecommentVo) {
                if (workRecommentVo == null) {
                    return;
                }
                if (workRecommentVo.data.course.size() > 0) {
                    if (workRecommentVo.data.live.size() > 0) {
                        items.add(new TypeVo("直播推荐"));
                        items.addAll(workRecommentVo.data.live);
                    }
                    items.add(new TypeVo("视频课程"));
                    items.addAll(workRecommentVo.data.course);
                    items.add(new TypeVo("精彩批改"));
                    items.addAll(workRecommentVo.data.content);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getNetWorkData() {
        mViewModel.getWorkMergeData(correctId);
    }

    public static void start(Context context, String correctId) {
        Intent starter = new Intent(context, WorkDetailsActivity.class);
        starter.putExtra("correct_id", correctId);
        context.startActivity(starter);
    }
}
