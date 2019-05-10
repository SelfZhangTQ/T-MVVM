package com.code.mvvm.core.view.dynamic;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.adapter.adapter.DelegateAdapter;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.core.data.source.DynamicRepository;
import com.code.mvvm.core.vm.DynamicViewModel;
import com.code.mvvm.util.AdapterPool;

/**
 * @author：tqzhang on 18/6/30 11:13
 */
public class DynamicFragment extends BaseListFragment<DynamicViewModel> {

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }


    @Override
    protected void dataObserver() {
        registerSubscriber(DynamicRepository.EVENT_KEY_DYNAMIC, DynamicListVo.class).observe(this, dynamicListVo -> {
            if (dynamicListVo != null && dynamicListVo.data != null) {
                lastId = dynamicListVo.data.get(dynamicListVo.data.size() - 1).feedid;
                setUiData(dynamicListVo.data);
            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getDynamicAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getDynamicList("e25af8d190ede08f60f2d8bb8dcd229b", lastId);

    }

    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        getRemoteData();
    }
}
