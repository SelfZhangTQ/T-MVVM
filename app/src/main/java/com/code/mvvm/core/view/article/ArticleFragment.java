package com.code.mvvm.core.view.article;

import android.os.Bundle;

import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewPagerFragment;
import com.code.mvvm.core.data.pojo.article.ArticleTypeVo;
import com.code.mvvm.core.data.source.ArticleRepository;
import com.code.mvvm.core.vm.ArticleViewModel;
import com.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @author：tqzhang on 18/7/2 14:24
 */
public class ArticleFragment extends BaseViewPagerFragment<ArticleViewModel> {
    private List<ArticleTypeVo.DataBean> titleName = new ArrayList<>();

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        getTabData();
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(ArticleRepository.EVENT_KEY_ARTICLE, ArticleTypeVo.class).observe(this, articleTypeVo -> {
            if (articleTypeVo != null) {
                setData(articleTypeVo);
            }
        });

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
        dataBean.maintypename = getResources().getString(R.string.recommend_tab_name);
        mArrTitles[0] = getResources().getString(R.string.recommend_tab_name);
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
