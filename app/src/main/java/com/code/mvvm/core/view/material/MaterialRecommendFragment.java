package com.code.mvvm.core.view.material;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.adapter.adapter.DelegateAdapter;
import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.material.MaterialRecommendVo;
import com.code.mvvm.core.data.source.MaterialRepository;
import com.code.mvvm.core.vm.MaterialViewModel;
import com.code.mvvm.util.AdapterPool;


/**
 * @author：tqzhang on 18/7/2 14:39
 */
public class MaterialRecommendFragment extends BaseListFragment<MaterialViewModel> {

    public static MaterialRecommendFragment newInstance() {
        return new MaterialRecommendFragment();
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(MaterialRepository.EVENT_KEY_MT_RED, MaterialRecommendVo.class).observe(this, materialRecommendVo -> {
            if (materialRecommendVo != null) {
                lastId = materialRecommendVo.data.content.get(materialRecommendVo.data.content.size() - 1).subjectid;
                setUiData(materialRecommendVo.data.content);
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
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        super.onLoadMore(isLoadMore, pageIndex);
        getRemoteData();
    }
}
