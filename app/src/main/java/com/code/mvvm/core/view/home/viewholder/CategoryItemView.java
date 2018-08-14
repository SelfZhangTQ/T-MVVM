package com.code.mvvm.core.view.home.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.adapter.BaseRecyclerAdapter;
import com.code.mvvm.adapter.HomeCatagoryAdapter;
import com.code.mvvm.base.BaseViewHolder;
import com.code.mvvm.core.data.pojo.home.CatagoryInfoVo;
import com.code.mvvm.core.data.pojo.home.CatagoryVo;
import com.code.mvvm.core.view.common.CommonActivity;
import com.trecyclerview.multitype.AbsItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class CategoryItemView extends AbsItemView<CatagoryVo, CategoryItemView.ViewHolder> {
    private Context mContext;

    public CategoryItemView(Context context) {
        this.mContext = context;
    }

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_category, parent, false), mContext);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CatagoryVo categoryTop) {
    }


    public static class ViewHolder extends BaseViewHolder {
        private String[] tvNames = new String[]{"动态", "图库", "精讲", "跟着画", "直播课", "图书", "名师问答", "活动"};
        private int[] tvIcons = new int[]{R.drawable.dynamic_icon, R.drawable.work_icon, R.drawable.article_icon, R.drawable.follow_draw_icon, R.drawable.live_icon, R.drawable.book_icon, R.drawable.qa_icon, R.drawable.activities_icon};
        public List<CatagoryInfoVo> list = new ArrayList<>();

        private HomeCatagoryAdapter adapter;
        private RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView, final Context activity) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(App.Instance(), 4);
            recyclerView.setLayoutManager(layoutManager);
            new LinearSnapHelper().attachToRecyclerView(recyclerView);
            initData();
            adapter = new HomeCatagoryAdapter(App.Instance(), list, 0);
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(View v, int position) {
                    if (list.get(position).title.equals("图库")) {
                        CommonActivity.start(activity, CommonActivity.MATERIAL, "图库");
                    } else if (list.get(position).title.equals("精讲")) {
                        CommonActivity.start(activity, CommonActivity.ARTIALE, "精讲");
                    } else if (list.get(position).title.equals("跟着画")) {
                        CommonActivity.start(activity, CommonActivity.FOLLOWDRAW, "跟着画");
                    } else if (list.get(position).title.equals("直播课")) {
                        CommonActivity.start(activity, CommonActivity.LIVE, "直播课");
                    } else if (list.get(position).title.equals("图书")) {
                        CommonActivity.start(activity, CommonActivity.BOOK, "图书");
                    } else if (list.get(position).title.equals("动态")) {
                        CommonActivity.start(activity, CommonActivity.TRICKS, "动态");
                    } else if (list.get(position).title.equals("名师问答")) {
                        CommonActivity.start(activity, CommonActivity.QA, "名师问答");
                    } else if (list.get(position).title.equals("活动")) {
                        CommonActivity.start(activity, CommonActivity.ACTIVITY, "活动");
                    }
                }
            });
        }

        private void initData() {
            for (int i = 0; i < tvNames.length; i++) {
                list.add(new CatagoryInfoVo(tvNames[i], tvIcons[i]));
            }
        }

    }


}
