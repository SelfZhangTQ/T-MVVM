package com.code.mvvm.core.view.correct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.source.WorkRepository;
import com.code.mvvm.core.vm.WorkViewModel;
import com.code.mvvm.util.AdapterPool;


/**
 * @author：tqzhang on 18/5/2 19:30
 */
public class WorkFragment extends BaseListFragment<WorkViewModel> implements OnItemClickListener {

    private String uTime;

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        setTitle(getResources().getString(R.string.work_title_name));
    }


    @Override
    protected void dataObserver() {
        registerSubscriber(WorkRepository.EVENT_KEY_WORK_LIST, WorkMergeVo.class).observe(this, workMergeVo -> {
            if (workMergeVo != null) {
                mItems.clear();
                mItems.add(workMergeVo.bannerListVo);
                lastId = workMergeVo.worksListVo.data.content.get(workMergeVo.worksListVo.data.content.size() - 1).tid;
                uTime = workMergeVo.worksListVo.data.content.get(workMergeVo.worksListVo.data.content.size() - 1).utime;
                mItems.addAll(workMergeVo.worksListVo.data.content);
                setData();
            }
        });

        registerSubscriber(WorkRepository.EVENT_KEY_WORK_MORE, WorksListVo.class).observe(this, worksListVo -> {
            if (worksListVo != null) {
                lastId = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                uTime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                mItems.addAll(worksListVo.data.content);
                setMoreData();
            }

        });

    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getWorkAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getWorkListData();
    }


    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        mViewModel.getWorkMoreData("", lastId, uTime);
    }

    @Override
    public void onItemClick(View view, int i, Object obj) {
        if (obj != null) {
            if (obj instanceof WorksListVo.Works) {
                WorksListVo.Works data = (WorksListVo.Works) obj;
                Intent starter = new Intent(getActivity(), WorkDetailsActivity.class);
                starter.putExtra(Constants.CORRECT_ID, data.correct.correctid);
                startActivity(starter);
            }

        }
    }
}
