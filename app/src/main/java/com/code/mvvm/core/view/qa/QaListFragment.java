package com.code.mvvm.core.view.qa;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.vm.QaViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：tqzhang on 18/7/4 14:10
 */
public class QaListFragment extends BaseListFragment<QaViewModel> {

    public static QaListFragment newInstance() {
        return new QaListFragment();
    }

    @Override
    protected void dataObserver() {
        mViewModel.getQAList().observe(this, qaListVo -> {
            if (qaListVo == null&&qaListVo.data!=null&&qaListVo.data.size()==0) {
                return;
            }
            lastId = qaListVo.data.get(qaListVo.data.size() - 1).newsid;
            setData(qaListVo.data);
        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getQaAdapter(activity);
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

    public void getNetWorkData() {
        mViewModel.getQAList(lastId, Constants.PAGE_RN);
    }


    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mViewModel.getQAList(lastId, Constants.PAGE_RN);
    }
}
