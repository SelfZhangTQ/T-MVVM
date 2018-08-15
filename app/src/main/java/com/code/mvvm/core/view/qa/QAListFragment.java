package com.code.mvvm.core.view.qa;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.viewmodel.QAViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.multitype.MultiTypeAdapter;

/**
 * @author：zhangtianqiu on 18/7/4 14:10
 */
public class QAListFragment extends BaseListFragment<QAViewModel> {
    @Override
    public void initView(Bundle state) {
        super.initView(state);
    }

    @Override
    protected void dataObserver() {
        mViewModel.getQAList().observe(this, new Observer<QaListVo>() {
            @Override
            public void onChanged(@Nullable QaListVo qaListObject) {
                lastId = qaListObject.data.get(qaListObject.data.size() - 1).newsid;
                setData(qaListObject.data);

            }
        });
    }

    @Override
    protected QAViewModel createViewModelProviders() {
        return VMProviders(this, QAViewModel.class);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        return AdapterPool.newInstance().getQaAdapter(getActivity());
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
        mViewModel.getQAList(lastId, "30");
    }
}
