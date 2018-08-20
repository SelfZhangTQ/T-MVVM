package com.code.mvvm.core.view.material;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.vm.MaterialViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;


/**
 * @author：tqzhang on 18/7/2 14:39
 */
public class MaterialRecommendFragment extends BaseListFragment<MaterialViewModel> {

    public static MaterialRecommendFragment newInstance() {
        return new MaterialRecommendFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);

    }

    @Override
    protected void dataObserver() {
        mViewModel.getMaterialRecommendList().observe(this, new Observer<MaterialRecommendVo>() {
            @Override
            public void onChanged(@Nullable MaterialRecommendVo materialRecommendVo) {
                if (materialRecommendVo != null) {
                    lastId = materialRecommendVo.data.content.get(materialRecommendVo.data.content.size() - 1).subjectid;
                    setData(materialRecommendVo.data.content);
                }

            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getMaterialRemAdapter(getActivity());
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getRemoteData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getRemoteData();
    }


    @Override
    protected void onRefreshAction() {
        super.onRefreshAction();
        getRemoteData();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getMaterialRemList("0", lastId, "20");
    }


}
