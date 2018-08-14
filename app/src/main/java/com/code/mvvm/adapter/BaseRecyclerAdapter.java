package com.code.mvvm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.mvvm.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author：zhangtianqiu on 17/6/6 12:08
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected OnItemClickListener onItemClickListener;//单击事件

    protected OnItemLongClickListener onItemLongClickListener;//长按单击事件

    protected List<T> dataList;

    private int mItemLayoutId;

    private Context context;

    protected Context getContext() {
        return context;
    }

    public List<T> getList() {
        return dataList;
    }

    public void setList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * @param context
     * @param list
     * @param itemLayoutId
     */
    public BaseRecyclerAdapter(Context context, @Nullable List<T> list, int itemLayoutId) {
        this.context = context;
        this.dataList = list;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(mItemLayoutId, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);

        } else {
            convert(holder, dataList.get(position), position, payloads);
        }


    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        List list = new ArrayList();
        convert(holder, dataList.get(position), position, list);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(v, position);
                }
            }

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClickListener(v, position);
                }

                return true;
            }
        });
    }

    protected abstract void convert(BaseViewHolder holder, T t, int position, List<Object> payloads);

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View v, int position);
    }
}
