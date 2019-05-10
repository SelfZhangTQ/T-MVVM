package com.code.mvvm.core.view.article.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.util.DisplayUtil;

/**
 * @author：tqzhang on 18/6/19 15:16
 */
public class ArticleRem1ItemHolder extends AbsItemHolder<ArticleInfoVo, ArticleRem1ItemHolder.ViewHolder> {
    private int commonWidth;

    public ArticleRem1ItemHolder(Context context) {
        super(context);
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(App.instance()));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_article_type1;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleInfoVo matreialsubject) {
        double dv = 0.5;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonWidth));
        holder.ivPic.setLayoutParams(params);
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
