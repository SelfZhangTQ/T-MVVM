package com.code.mvvm.core.view.article.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class ArticleItemViewBinder extends AbsItemView<ArticleInfoVo, ArticleItemViewBinder.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_type, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleInfoVo category) {
//        holder.title.setText(category.title);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

//        private @NonNull final TextView title;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            title = itemView.findViewById(R.id.live_recommend_title_tv);
        }
    }
}
