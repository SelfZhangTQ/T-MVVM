package com.code.mvvm.core.view.dynamic;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.core.vm.DynamicViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

/**
 * @author：tqzhang on 18/6/30 11:13
 */
public class DynamicFragment extends BaseListFragment<DynamicViewModel> {

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_DYNAMIC_STATE;
    }

    @Override
    protected void dataObserver() {

        registerObserver(Constants.EVENT_KEY_DYNAMIC, DynamicListVo.class).observe(this, dynamicListVo -> {
            if (dynamicListVo != null&&dynamicListVo.data!=null) {
                lastId = dynamicListVo.data.get(dynamicListVo.data.size() - 1).feedid;
                setData(dynamicListVo.data);
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
        mViewModel.getDynamicList( "45ca1f01417c758505e2919e701c3f99", lastId);

    }

    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }


}
