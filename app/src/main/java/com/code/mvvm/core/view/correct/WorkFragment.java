package com.code.mvvm.core.view.correct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
import com.code.mvvm.core.data.pojo.correct.WorkMergeVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.vm.WorkViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;
import com.trecyclerview.listener.OnItemClickListener;


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
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_WORK_LIST_STATE;
    }

    @Override
    protected void dataObserver() {
        registerObserver(Constants.EVENT_KEY_WORK_LIST, WorkMergeVo.class).observe(this, workMergeVo -> {
            if (workMergeVo != null) {
                newItems.clear();
                newItems.add(workMergeVo.bannerListVo);
                lastId = workMergeVo.worksListVo.data.content.get(workMergeVo.worksListVo.data.content.size() - 1).tid;
                uTime = workMergeVo.worksListVo.data.content.get(workMergeVo.worksListVo.data.content.size() - 1).utime;
                setData(workMergeVo.worksListVo.data.content);
            }
        });

        registerObserver(Constants.EVENT_KEY_WORK_MORE, WorksListVo.class).observe(this, worksListVo -> {
            if (worksListVo != null) {
                lastId = worksListVo.data.content.get(worksListVo.data.content.size() - 1).tid;
                uTime = worksListVo.data.content.get(worksListVo.data.content.size() - 1).utime;
                setData(worksListVo.data.content);
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
    protected void getLoadMoreData() {
        mViewModel.getWorkMoreData("", lastId, uTime);
    }

    @Override
    public void onItemClick(View view, int i, Object o) {
        if (o != null) {
            if (o instanceof WorksListVo.Works) {
                WorksListVo.Works data = (WorksListVo.Works) o;
                Intent starter = new Intent(getActivity(), WorkDetailsActivity.class);
                starter.putExtra("correct_id", data.correct.correctid);
                startActivity(starter);
            }

        }
    }
}
