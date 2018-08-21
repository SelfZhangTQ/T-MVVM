package com.code.mvvm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.glide.GlideRoundTransform;
import com.code.mvvm.util.DisplayUtil;
import com.code.mvvm.widget.CustomHeightImageView;

import java.util.List;

/**
 * @author：tqzhang on 18/5/7 14:55
 */
public class HomeListAdapter extends BaseRecyclerAdapter<MatreialSubjectVo> {


    private int commonwidth = (int) (((float) DisplayUtil.getScreenWidth(getContext())
            - DisplayUtil.dp2px(getContext(), 8)) / 2);

    /**
     * @param context
     * @param list
     * @param itemLayoutId
     */
    public HomeListAdapter(Context context, @Nullable List list, int itemLayoutId) {
        super(context, list, R.layout.item_home_list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MatreialSubjectVo matreialsubject, int position, List payloads) {
        //设置推荐素材封面高度
        float dv = (float) matreialsubject.picurl.l.h / (float) matreialsubject.picurl.l.w;
        int height = (int) (dv * commonwidth);
        CustomHeightImageView video_img = holder.getView(R.id.iv_pic_image);
        video_img.setLayoutParams(new RelativeLayout.LayoutParams(commonwidth, height));
        Glide.with(getContext()).load(matreialsubject.picurl.l.url).skipMemoryCache(true)
                .placeholder(R.color.black_e8e8e8)
                .transform(new GlideRoundTransform(getContext(), 4))
                .override(commonwidth, (int) (dv * commonwidth)).into(video_img);
        ((TextView) holder.getView(R.id.video_title)).setText(matreialsubject.title);
    }
}
