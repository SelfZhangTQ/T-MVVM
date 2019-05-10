package com.code.mvvm.core.view.material;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.adapter.adapter.DelegateAdapter;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.material.MaterialVo;
import com.code.mvvm.core.data.source.MaterialRepository;
import com.code.mvvm.core.vm.MaterialViewModel;
import com.code.mvvm.util.AdapterPool;

/**
 * @author：tqzhang on 18/7/2 14:40
 */
public class MaterialListFragment extends BaseListFragment<MaterialViewModel> {
    private String subId;

    public static MaterialListFragment newInstance() {
        return new MaterialListFragment();
    }


    @Override
    protected void dataObserver() {
        if (getArguments() != null) {
            subId = getArguments().getString(Constants.SUB_ID);
        }
        registerSubscriber(MaterialRepository.EVENT_KEY_MT_LIST,subId, MaterialVo.class).observe(this, materialListVo -> {
            if (materialListVo != null) {
                lastId = materialListVo.data.content.get(materialListVo.data.content.size() - 1).tid;
                mItems.clear();
                mItems.addAll(materialListVo.data.content);
                setData();
            }
        });
        registerSubscriber(MaterialRepository.EVENT_KEY_MT_MORE_LIST,subId, MaterialVo.class).observe(this, materialListVo -> {
            if (materialListVo != null && materialListVo.data != null && materialListVo.data.content.size() > 0) {
                lastId = materialListVo.data.content.get(materialListVo.data.content.size() - 1).tid;
                mItems.addAll(materialListVo.data.content);
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
        return AdapterPool.newInstance().getMaterialListAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getMaterialList("0", subId);
    }


    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        mViewModel.getMaterialMoreList("0", subId, lastId);
    }
}
