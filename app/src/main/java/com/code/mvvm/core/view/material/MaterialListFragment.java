package com.code.mvvm.core.view.material;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.vm.MaterialViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang  on 18/7/2 14:40
 */
public class MaterialListFragment extends BaseListFragment<MaterialViewModel> {
    private String subId;

    public static MaterialListFragment newInstance() {
        return new MaterialListFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        if (getArguments() != null) {
            subId = getArguments().getString("sub_id");
        }
    }

    @Override
    protected void dataObserver() {
        mViewModel.getMaterialList().observe(this, materialListVo -> {
            if (materialListVo != null) {
                lastId = materialListVo.data.content.get(materialListVo.data.content.size() - 1).tid;
                setData(materialListVo.data.content);
            }
        });
        mViewModel.getMaterialMoreList().observe(this, materialListVo -> {
            if (materialListVo != null && materialListVo.data != null && materialListVo.data.content.size() > 0) {
                lastId = materialListVo.data.content.get(materialListVo.data.content.size() - 1).tid;
                setData(materialListVo.data.content);
            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getMaterialListAdapter(activity);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNewData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNewData();
    }



    @Override
    public void onRefresh() {
        super.onRefresh();
        getNewData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mViewModel.getMaterialMoreList("0", subId, lastId);
    }

    private void getNewData() {
        mViewModel.getMaterialList("0", subId);

    }
}
