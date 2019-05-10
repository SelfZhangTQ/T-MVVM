package com.code.mvvm.core.view.article.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;

/**
 * @author：tqzhang on 18/6/19 15:16
 */
public class ArticleRem2ItemHolder extends AbsItemHolder<ArticleInfoVo, ArticleRem2ItemHolder.ViewHolder> {

    public ArticleRem2ItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_article_type2;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleInfoVo matreialsubject) {
        Glide.with(mContext).load(matreialsubject.img.get(0).img)
                .placeholder(R.color.black_e8e8e8)
                .into(holder.ivPic);

        holder.tvHits.setText(matreialsubject.hits);
        holder.tvTitle.setText(matreialsubject.title);
        if (!TextUtils.isEmpty(matreialsubject.stick_date) && Integer.valueOf(matreialsubject.stick_date) > 0) {
            holder.tvTagTop.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagTop.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(matreialsubject.newstype) && Integer.valueOf(matreialsubject.newstype) == 2) {
            holder.tvTagSubject.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagSubject.setVisibility(View.GONE);
        }

        //2视频，3音频
        if ("2".equals(matreialsubject.content_type)) {
            holder.ivType.setVisibility(View.VISIBLE);
            holder.ivType.setBackgroundResource(R.drawable.tag_video_icon);
        } else if ("3".equals(matreialsubject.content_type)) {
            holder.ivType.setVisibility(View.VISIBLE);
            holder.ivType.setBackgroundResource(R.drawable.tag_audio_icon);
        } else {
            holder.ivType.setVisibility(View.GONE);
        }
    }

    static class ViewHolder extends AbsHolder {
        private ImageView ivType;

        private ImageView ivPic;

        private TextView tvTitle;

        private TextView tvHits;

        private TextView tvTagTop;

        private TextView tvTagSubject;

        public ViewHolder(View itemView) {
            super(itemView);
            ivType = getViewById(R.id.iv_type);
            ivPic = getViewById(R.id.iv_pic);
            tvTitle = getViewById(R.id.tv_title);
            tvHits = getViewById(R.id.tv_hits);
            tvTagTop = getViewById(R.id.tv_tag_top);
            tvTagSubject = getViewById(R.id.tv_tag_subject);
        }

    }

}
