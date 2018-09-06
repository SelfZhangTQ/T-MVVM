package com.code.mvvm.core.view.material;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.code.mvvm.base.BaseListFragment;
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
    protected void dataObserver() {
        mViewModel.getMaterialRecommendList().observe(this, materialRecommendVo -> {
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
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getMaterialRemAdapter(activity);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNetWorkData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getNetWorkData();
    }

    public void getNetWorkData() {
        mViewModel.getMaterialRemList("0", lastId);
    }


}
