package com.code.mvvm.core.view.home.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.mvvm.R;
import com.code.mvvm.adapter.HomeListAdapter;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.material.MatreialListVo;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:16
 */
public class HomeMaterialItemView extends AbsItemView<MatreialListVo, HomeMaterialItemView.ViewHolder> {

    private Context mContext;

    public HomeMaterialItemView(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.home_matreial_item, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MatreialListVo matreialsubject) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        holder.mRecyclerView.setLayoutManager(layoutManager);
        HomeListAdapter adapter = new HomeListAdapter(mContext, null, 0);
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setNestedScrollingEnabled(false);
        adapter.setList(matreialsubject.matreialsubject);
        adapter.notifyDataSetChanged();
    }

    static class ViewHolder extends BaseViewHolder {

        private RecyclerView mRecyclerView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
        }

    }

}
