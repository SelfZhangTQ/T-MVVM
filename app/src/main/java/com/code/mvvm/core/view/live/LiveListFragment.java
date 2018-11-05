package com.code.mvvm.core.view.live;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.vm.LiveViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

/**
 * @author：tqzhang on 18/6/30 18:36
 */
public class LiveListFragment extends BaseListFragment<LiveViewModel> {

    private String typeId;

    public static LiveListFragment newInstance() {
        return new LiveListFragment();
    }


    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_LIVE_LIST_STATE;
    }

    @Override
    protected String getStateEventTag() {
        return typeId;
    }

    @Override
    protected void dataObserver() {
        if (getArguments() != null) {
            typeId = getArguments().getString("type_id");
        }


        registerObserver(Constants.EVENT_KEY_LIVE_LIST, typeId, LiveListVo.class).observe(this, liveListVo -> {
            if (liveListVo != null && liveListVo.data != null) {
                lastId = liveListVo.data.get(liveListVo.data.size() - 1).liveid;
                setData(liveListVo.data);
            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getLiveAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getLiveList(typeId, lastId);
    }

    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }
}
