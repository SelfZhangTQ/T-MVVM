//package com.mvvmcode.ui.common;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//import com.multirecyclerview.ArrowRefreshHeader;
//import com.multirecyclerview.ProgressStyle;
//import com.multirecyclerview.entity.HeaderInfo;
//import com.multirecyclerview.multitype.ItemViewBinder;
//import com.multirecyclerview.view.HeaderItemView;
//
///**
// * @author：zhangtianqiu on 18/6/20 13:41
// */
//public class HeaderItemViewBinder extends HeaderItemView<HeaderInfo, HeaderItemViewBinder.ViewHolder> {
//
//    public HeaderItemViewBinder(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected RecyclerView.ViewHolder createItemView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
//        return new ViewHolder(getRefreshHeaderView());
//    }
//
//    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull HeaderInfo item) {
//       super.onBindViewHolder(holder,item);
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//
//        ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//}
