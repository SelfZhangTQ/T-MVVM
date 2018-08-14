package com.basiclibrary.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author：zhangtianqiu on 18/3/14 11:37
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;
    private Context context;

    @Deprecated
    public View convertView;

    public BaseViewHolder(final View view, Context context) {
        super(view);
        this.views = new SparseArray<>();
        this.convertView = view;
        this.context = context;
    }

    public View getConvertView() {

        return convertView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public void setImageResource(int viewId, int audio_zong_anim) {
        ImageView view = getView(viewId);
        view.setImageResource(audio_zong_anim);
    }
}
