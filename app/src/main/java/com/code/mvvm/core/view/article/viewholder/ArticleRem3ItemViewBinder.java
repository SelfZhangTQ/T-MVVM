package com.code.mvvm.core.view.article.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class ArticleRem3ItemViewBinder extends AbsItemView<ArticleInfoVo, ArticleRem3ItemViewBinder.ViewHolder> {
    int commonWidth;
    private Context mContext;

    public ArticleRem3ItemViewBinder(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance()));

        return new ViewHolder(inflater.inflate(R.layout.item_article_type3, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleInfoVo matreialsubject) {
        // 图片适配
        double dv2 = 1;
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                (commonWidth - 2 * App.Instance()
                        .getResources().getDimensionPixelSize(
                                R.dimen.concise_three_img_margin))
                        / 3,
                (int) (dv2
                        * (commonWidth - 2 * App.Instance().getResources()
                        .getDimensionPixelSize(
                                R.dimen.concise_three_img_margin))
                        / 3));
        holder.iv_pic.setLayoutParams(params2);
        holder.iv_pic2.setLayoutParams(params2);
        holder.iv_pic3.setLayoutParams(params2);
        Glide.with(mContext).load(matreialsubject.img.get(0).img)
                .placeholder(R.color.black_333333).override(params2.width, params2.height).into(holder.iv_pic);
        if (matreialsubject.img.size() > 1) {
            Glide.with(mContext).load(matreialsubject.img.get(1).img)
                    .placeholder(R.color.black_333333).override(params2.width, params2.height).into(holder.iv_pic2);
        }
        if (matreialsubject.img.size() > 2) {
            Glide.with(mContext).load(matreialsubject.img.get(2).img)
                    .placeholder(R.color.black_333333)
                    .override(params2.width, params2.height).into(holder.iv_pic3);
        }
        holder.tvHits.setText(matreialsubject.hits);
        holder.tv_title.setText(matreialsubject.title);
        if (matreialsubject.stick_date != null && !"".equals(matreialsubject.stick_date) && Integer.valueOf(matreialsubject.stick_date) > 0) {
            holder.tvTagTop.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagTop.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(matreialsubject.newstype) && Integer.valueOf(matreialsubject.newstype) == 2) {
            holder.tvTagSubject.setVisibility(View.VISIBLE);
        } else {
            holder.tvTagSubject.setVisibility(View.GONE);
        }
    }

    static class ViewHolder extends BaseViewHolder {
        public ImageView iv_pic;

        public ImageView iv_pic2;

        public ImageView iv_pic3;

        public TextView tv_title;

        public TextView tvHits;

        public TextView tvTagTop;

        public TextView tvTagSubject;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView
                    .findViewById(R.id.con_list_img);
            iv_pic2 = (ImageView) itemView
                    .findViewById(R.id.con_list_img2);
            iv_pic3 = (ImageView) itemView
                    .findViewById(R.id.con_list_img3);
            tv_title = (TextView) itemView
                    .findViewById(R.id.tv_title);
            tvHits = getView(R.id.tv_hits);
            tvTagTop = getView(R.id.tv_tag_top);
            tvTagSubject = getView(R.id.tv_tag_subject);
        }

    }

}
