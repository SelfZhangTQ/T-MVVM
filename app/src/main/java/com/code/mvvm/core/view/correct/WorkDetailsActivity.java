package com.code.mvvm.core.view.correct;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.adapter.listener.OnItemClickListener;
import com.code.mvvm.R;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.common.TypeVo;
import com.code.mvvm.core.data.pojo.correct.WorkDetailVo;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.view.common.TypeItemView;
import com.code.mvvm.core.view.correct.holder.CorrectPicHolder;
import com.code.mvvm.core.view.correct.holder.CorrectRemItemHolder;
import com.code.mvvm.core.view.course.holder.CourseItemHolder;
import com.code.mvvm.core.view.home.holder.HomeLiveItemView;
import com.code.mvvm.core.vm.WorkViewModel;
import com.mvvm.base.AbsLifecycleActivity;
import com.mvvm.event.LiveBus;

import java.lang.ref.WeakReference;


/**
 * @author：tqzhang on 18/7/16 18:06
 */
public class WorkDetailsActivity extends AbsLifecycleActivity<WorkViewModel> implements OnItemClickListener {

    protected RecyclerView mRecyclerView;
    private DelegateAdapter adapter;
    protected ItemData items = new ItemData();
    private String correctId;

    private WeakReference<WorkDetailsActivity> weakReference;

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        mViewModel.getWorkDetaiMergeData(correctId);
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
        adapter = new DelegateAdapter.Builder<>()
                .bind(WorkDetailVo.class, new CorrectPicHolder(weakReference.get()))
                .bind(CourseInfoVo.class, new CourseItemHolder(weakReference.get()))
                .bind(LiveRecommendVo.class, new HomeLiveItemView(weakReference.get()))
                .bind(WorkInfoVo.class, new CorrectRemItemHolder(weakReference.get()))
                .bind(TypeVo.class, new TypeItemView(weakReference.get()))
                .setOnItemClickListener(this)
                .build();

    }

    @Override
    protected void dataObserver() {
        LiveBus.getDefault().subscribe(Constants.EVENT_KEY_WORK_STATE).observe(this, observer);

        LiveBus.getDefault().subscribe(Constants.EVENT_KEY_WORK, WorkMergeVo.class).observe(this, new Observer<WorkMergeVo>() {
            @Override
            public void onChanged(@Nullable WorkMergeVo workMergeVo) {
                if (workMergeVo.workDetailVo != null) {
                    items.add(workMergeVo.workDetailVo);
                }

                if (workMergeVo.workRecommentVo != null) {
                    if (workMergeVo.workRecommentVo.data.course.size() > 0) {
                        if (workMergeVo.workRecommentVo.data.live.size() > 0) {
                            items.add(new TypeVo("直播推荐"));
                            items.addAll(workMergeVo.workRecommentVo.data.live);
                        }
                        items.add(new TypeVo("视频课程"));
                        items.addAll(workMergeVo.workRecommentVo.data.course);
                        items.add(new TypeVo("精彩批改"));
                        items.addAll(workMergeVo.workRecommentVo.data.content);
                    }
                }
                adapter.setDatas(items);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }


    private void getNetWorkData() {
        mViewModel.getWorkDetaiMergeData(correctId);
    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        if (o != null) {
            if (o instanceof WorkInfoVo) {
                WorkInfoVo data = (WorkInfoVo) o;
                Intent starter = new Intent(this, WorkDetailsActivity.class);
                starter.putExtra("correct_id", data.correctid);
                startActivity(starter);
                finish();
            }

        }
    }
}
