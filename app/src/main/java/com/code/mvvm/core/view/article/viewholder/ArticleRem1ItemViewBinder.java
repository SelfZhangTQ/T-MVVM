package com.code.mvvm.core.view.article.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:16
 */
public class ArticleRem1ItemViewBinder extends AbsItemView<ArticleInfoVo, ArticleRem1ItemViewBinder.ViewHolder> {
    private int commonWidth = 0;

    private Context mContext;

    public ArticleRem1ItemViewBinder(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        if (commonWidth == 0) {
            commonWidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance()));
        }
        return new ViewHolder(inflater.inflate(R.layout.item_article_type1, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleInfoVo matreialsubject) {
        // 图片适配
        double dv = 0.5;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, (int) (dv * commonWidth));
        holder.ivPic.setLayoutParams(params);
        Glide.with(mContext).load(matreialsubject.img.get(0).img)
                .placeholder(R.color.black_333333)
                .into(holder.ivPic);
        holder.tvHits.setText(matreialsubject.hits);
        holder.tvTitle.setText(matreialsubject.title);
        if (!TextUtils.isEmpty(matreialsubject.stick_date) && Integer.valueOf(matreialsubject.stick_date) > 0) {
            holder.tvTagTop.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagTop.setVisibility(View.GONE);
        }
        if (matreialsubject.newstype != null && !"".equals(matreialsubject.newstype) && Integer.valueOf(matreialsubject.newstype) == 2) {
            holder.tvTagSubject.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagSubject.setVisibility(View.GONE);
        }
        if ("2".equals(matreialsubject.content_type)) {
            holder.ivType.setVisibility(View.VISIBLE);
            holder.ivType.setBackgroundResource(R.drawable.jingjiang_shipin_da);
        } else if ("3".equals(matreialsubject.content_type)) {
            holder.ivType.setVisibility(View.VISIBLE);
            holder.ivType.setBackgroundResource(R.drawable.jingjiang_yinpin_da);
        } else {
            holder.ivType.setVisibility(View.GONE);
        }
    }

    static class ViewHolder extends BaseViewHolder {
        private ImageView ivType;

        private ImageView ivPic;

        private TextView tvTitle;

        private TextView tvHits;

        private TextView tvTagTop;

        private TextView tvTagSubject;

        public ViewHolder(View itemView) {
            super(itemView);
            ivType = getView(R.id.iv_type);
            ivPic = getView(R.id.iv_pic);
            tvTitle = getView(R.id.tv_title);
            tvHits = getView(R.id.tv_hits);
            tvTagTop = getView(R.id.tv_tag_top);
            tvTagSubject = getView(R.id.tv_tag_subject);
        }

    }

}
