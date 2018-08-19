package com.code.mvvm.core.view.dynamic;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.dynamic.DynamicListVo;
import com.code.mvvm.core.viewmodel.DynamicViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang  on 18/6/30 11:13
 */
public class DynamicFragment extends BaseListFragment<DynamicViewModel> {

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }


    @Override
    protected void dataObserver() {
        mViewModel.getDynamicList().observe(this, new Observer<DynamicListVo>() {
            @Override
            public void onChanged(@Nullable DynamicListVo dynamicListVo) {
                if (dynamicListVo != null) {
                    lastId = dynamicListVo.data.get(dynamicListVo.data.size() - 1).feedid;
                    setData(dynamicListVo.data);
                }
            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getDynamicAdapter(getActivity());
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getDynamicListData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getDynamicListData();
    }

    @Override
    protected void getRemoteData() {
        getDynamicListData();
    }

    private void getDynamicListData() {
        mViewModel.getDynamicList("20", "818d8a6b54870bdffc333376723289d9", lastId);

    }
}
