package com.code.mvvm.core.view.live;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adapter.adapter.DelegateAdapter;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.live.LiveListVo;
import com.code.mvvm.core.data.source.LiveRepository;
import com.code.mvvm.core.vm.LiveViewModel;
import com.code.mvvm.util.AdapterPool;

/**
 * @author：tqzhang on 18/6/30 18:36
 */
public class LiveListFragment extends BaseListFragment<LiveViewModel> {

    private String typeId;

    public static LiveListFragment newInstance() {
        return new LiveListFragment();
    }


    @Override
    protected void dataObserver() {
        if (getArguments() != null) {
            typeId = getArguments().getString("type_id");
        }

        registerSubscriber(LiveRepository.EVENT_KEY_LIVE_LIST, LiveListVo.class).observe(this, liveListVo -> {
            if (liveListVo != null && liveListVo.data != null) {
                lastId = liveListVo.data.get(liveListVo.data.size() - 1).liveid;
                setUiData(liveListVo.data);
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
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        getRemoteData();
    }
}
