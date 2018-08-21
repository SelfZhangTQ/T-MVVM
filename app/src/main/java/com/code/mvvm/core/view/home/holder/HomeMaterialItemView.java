package com.code.mvvm.core.view.home.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.code.mvvm.R;
import com.code.mvvm.adapter.HomeListAdapter;
import com.code.mvvm.core.data.pojo.material.MaterialListVo;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseHolder;

/**
 * @author：tqzhang on 18/6/19 15:16
 */
public class HomeMaterialItemView extends AbsViewHolder<MaterialListVo, HomeMaterialItemView.ViewHolder> {


    public HomeMaterialItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_matreial_item;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MaterialListVo matreialsubject) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        holder.mRecyclerView.setLayoutManager(layoutManager);
        HomeListAdapter adapter = new HomeListAdapter(mContext, null, 0);
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setNestedScrollingEnabled(false);
        adapter.setList(matreialsubject.matreialsubject);
        adapter.notifyDataSetChanged();
    }

    static class ViewHolder extends BaseHolder {

        private RecyclerView mRecyclerView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = getViewById(R.id.recycler_view);
        }

    }

}
