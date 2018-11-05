package com.code.mvvm.core.view.material;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.vm.MaterialViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;


/**
 * @author：tqzhang on 18/7/2 14:39
 */
public class MaterialRecommendFragment extends BaseListFragment<MaterialViewModel> {

    public static MaterialRecommendFragment newInstance() {
        return new MaterialRecommendFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_MT_RED_STATE;
    }

    @Override
    protected void dataObserver() {
        registerObserver(Constants.EVENT_KEY_MT_RED, MaterialRecommendVo.class).observe(this, materialRecommendVo -> {
            if (materialRecommendVo != null) {
                lastId = materialRecommendVo.data.content.get(materialRecommendVo.data.content.size() - 1).subjectid;
                setData(materialRecommendVo.data.content);
            }

        });
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getMaterialRemAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getMaterialRemList("0", lastId);
    }

    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }
}
