package com.code.mvvm.core.view.home.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.code.mvvm.core.data.pojo.qa.QaListVo;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/19 15:00
 */
public class QAListItemViewBinder extends AbsItemView<QaListVo.DataBean, QAListItemViewBinder.ViewHolder> {
    private int commonWidth;

    private Context mContext;

    public QAListItemViewBinder(Context context) {
        mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonWidth = (int) ((float) DisplayUtil.getScreenWidth(App.Instance())
                - 2 * App.Instance().getResources()
                .getDimensionPixelSize(
                        R.dimen.concise_three_layout_margin));
        return new ViewHolder(inflater.inflate(R.layout.item_qa_list, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QaListVo.DataBean dataBean) {
        holder.activity_list_textview.setText(dataBean.title);
        holder.tv_decs.setText(dataBean.desc);
        holder.tv_look.setText(dataBean.hits);
        holder.tv_decs_type.setText(dataBean.keywords);
        double dv = 0.5;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.activity_list_imageview.getLayoutParams();
        params.height = (int) (dv * commonWidth);
        Glide.with(mContext).load(dataBean.imgs.get(0).img)
                .placeholder(R.color.black_333333)
                .into(holder.activity_list_imageview);
    }


    static class ViewHolder extends BaseViewHolder {
        TextView tv_decs;
        TextView tv_look;
        TextView tv_decs_type;
        TextView activity_list_textview;
        ImageView activity_list_imageview;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_list_textview = itemView.findViewById(R.id.activity_list_textview);
            tv_decs_type = itemView.findViewById(R.id.tv_decs_type);
            tv_look = itemView.findViewById(R.id.tv_look);
            tv_decs = itemView.findViewById(R.id.tv_decs);
            activity_list_imageview = itemView.findViewById(R.id.activity_list_imageview);

        }
    }

}
