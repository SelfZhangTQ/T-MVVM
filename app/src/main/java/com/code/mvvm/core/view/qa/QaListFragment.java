package com.code.mvvm.core.view.qa;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.core.vm.QaViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.adapter.DelegateAdapter;

/**
 * @author：tqzhang on 18/7/4 14:10
 */
public class QaListFragment extends BaseListFragment<QaViewModel> {

    public static QaListFragment newInstance() {
        return new QaListFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_QA_STATE;
    }

    @Override
    protected void dataObserver() {

        registerObserver(Constants.EVENT_KEY_QA, QaListVo.class).observe(this, qaListVo -> {
            if (qaListVo == null && qaListVo.data != null && qaListVo.data.size() == 0) {
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
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getQaAdapter(activity).build();
    }

    @Override
    protected void getRemoteData() {
        mViewModel.getQAList(lastId, Constants.PAGE_RN);
    }

    @Override
    protected void getLoadMoreData() {
        getRemoteData();
    }
}
