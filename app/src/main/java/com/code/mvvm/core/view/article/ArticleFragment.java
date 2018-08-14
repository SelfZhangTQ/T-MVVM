package com.code.mvvm.core.view.article;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.basiclibrary.base.BaseFragment;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author：zhangtianqiu on 18/7/2 14:24
 */
public class ArticleFragment extends BaseViewPagerFragment<ArticleViewModel> {
    private List<ArticleTypeVo.DataBean> titleName;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        titleName = new ArrayList<>();
        getTabData();
    }

    @Override
    protected void dataObserver() {
        mViewModel.getArticleType().observe(this, new Observer<ArticleTypeVo>() {
            @Override
            public void onChanged(@Nullable ArticleTypeVo articleTypeVo) {
                if (articleTypeVo != null) {
                    setData(articleTypeVo);
                }
            }
        });

    }

    @Override
    protected ArticleViewModel createViewModelProviders() {
        return VMProviders(this, ArticleViewModel.class);
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getTabData();
    }

    @Override
    protected String[] createPageTitle() {
        return mArrTitles;
    }

    @Override
    protected List<BaseFragment> createFragments() {
        return mFragments;
    }


    private void getTabData() {
        mViewModel.getArticleTypeData();
    }


    public void setData(ArticleTypeVo articleTypeVo) {
        mArrTitles = new String[articleTypeVo.data.size() + 1];
        titleName.clear();
        ArticleTypeVo.DataBean dataBean = new ArticleTypeVo.DataBean();
        dataBean.maintypename = "推荐";
        mArrTitles[0] = "推荐";
        titleName.add(dataBean);
        for (int j = 0; j < articleTypeVo.data.size(); j++) {
            titleName.add(articleTypeVo.data.get(j));
            mArrTitles[j + 1] = (articleTypeVo.data.get(j).maintypename);
        }
        initFragment();
        setAdapter();
    }

    private void initFragment() {
        for (int i = 0; i < titleName.size(); i++) {
            ArticleListFragment articleListFragment = ArticleListFragment.newInstance();
            Bundle bundle = new Bundle();
            if (i == 0) {
                bundle.putString("type_id", "0");
            } else {
                bundle.putString("type_id", String.valueOf(titleName.get(i).maintypeid));
            }
            articleListFragment.setArguments(bundle);
            mFragments.add(articleListFragment);
        }
    }
}
