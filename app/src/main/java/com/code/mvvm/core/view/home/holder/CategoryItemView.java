package com.code.mvvm.core.view.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.code.mvvm.R;
import com.code.mvvm.adapter.HomeCategoryAdapter;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.home.CatagoryInfoVo;
import com.code.mvvm.core.data.pojo.home.CategoryVo;
import com.code.mvvm.core.view.common.CommonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/6/20 13:41
 */
public class CategoryItemView extends AbsItemHolder<CategoryVo, CategoryItemView.ViewHolder> {
    private String[] tvNames;
    private int[] tvIcons;
    private List<CatagoryInfoVo> list = new ArrayList<>();
    private HomeCategoryAdapter adapter;

    public CategoryItemView(Context context) {
        super(context);
        tvNames = new String[]{mContext.getResources().getString(R.string.dynamic_title_name),
                mContext.getResources().getString(R.string.material_title_name),
                mContext.getResources().getString(R.string.article_title_name),
                mContext.getResources().getString(R.string.follow_draw_title_name),
                mContext.getResources().getString(R.string.live_title_name),
                mContext.getResources().getString(R.string.book_title_name),
                mContext.getResources().getString(R.string.qa_title_name),
                mContext.getResources().getString(R.string.activity_title_name)};
        tvIcons = new int[]{R.drawable.dynamic_icon, R.drawable.work_icon, R.drawable.article_icon, R.drawable.follow_draw_icon, R.drawable.live_icon, R.drawable.book_icon, R.drawable.qa_icon, R.drawable.activities_icon};
        initData();
        adapter = new HomeCategoryAdapter(mContext, list, 0);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_category;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CategoryVo categoryTop) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener((v, position) -> {
            if (list.get(position).title.equals(mContext.getResources().getString(R.string.material_title_name))) {
                CommonActivity.start(mContext, Constants.MATERIAL, mContext.getResources().getString(R.string.material_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.article_title_name))) {
                CommonActivity.start(mContext, Constants.ARTICLE, mContext.getResources().getString(R.string.article_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.follow_draw_title_name))) {
                CommonActivity.start(mContext, Constants.FOLLOW_DRAW, mContext.getResources().getString(R.string.follow_draw_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.live_title_name))) {
                CommonActivity.start(mContext, Constants.LIVE, mContext.getResources().getString(R.string.live_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.book_title_name))) {
                CommonActivity.start(mContext, Constants.BOOK, mContext.getResources().getString(R.string.book_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.dynamic_title_name))) {
                CommonActivity.start(mContext, Constants.DYNAMIC, mContext.getResources().getString(R.string.dynamic_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.qa_title_name))) {
                CommonActivity.start(mContext, Constants.QA, mContext.getResources().getString(R.string.qa_title_name));
            } else if (list.get(position).title.equals(mContext.getResources().getString(R.string.activity_title_name))) {
                CommonActivity.start(mContext, Constants.ACTIVITY, mContext.getResources().getString(R.string.activity_title_name));
            }
        });
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    public static class ViewHolder extends AbsHolder {

        private RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = getViewById(R.id.recycler_view);
        }
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < tvNames.length; i++) {
            list.add(new CatagoryInfoVo(tvNames[i], tvIcons[i]));
        }
    }

}
