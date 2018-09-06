package com.code.mvvm.core.view.book.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseHolder;

/**
 * @author：tqzhang on 18/6/20 13:41
 */
public class BookItemHolder extends AbsViewHolder<BookList, BookItemHolder.ViewHolder> {
    private int commonWidth;

    public BookItemHolder(Context context) {
        super(context);
        commonWidth = (int) (((float) DisplayUtil.getScreenWidth(context)
                - DisplayUtil.dp2px(context, 30)) / 3);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_remmend_book;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookList mBookList) {
        double dv = 1.3;
        int width = commonWidth;
        int high = (int) (width * dv);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, high);

        holder.book1.setLayoutParams(layoutParams);
        holder.book2.setLayoutParams(layoutParams);
        holder.book3.setLayoutParams(layoutParams);
        Glide.with(mContext).load(mBookList.publishingbook.get(0).img.l.url).placeholder(R.color.black_e8e8e8).centerCrop().into(holder.book1);
        Glide.with(mContext).load(mBookList.publishingbook.get(1).img.l.url).placeholder(R.color.black_e8e8e8).centerCrop().into(holder.book2);
        Glide.with(mContext).load(mBookList.publishingbook.get(2).img.l.url).placeholder(R.color.black_e8e8e8).centerCrop().into(holder.book3);
        holder.bookName1.setText(mBookList.publishingbook.get(0).title);
        holder.bookName2.setText(mBookList.publishingbook.get(1).title);
        holder.bookName3.setText(mBookList.publishingbook.get(2).title);
        holder.bookPress1.setText(mBookList.publishingbook.get(0).publishing_name);
        holder.bookPress2.setText(mBookList.publishingbook.get(1).publishing_name);
        holder.bookPress3.setText(mBookList.publishingbook.get(2).publishing_name);
    }
    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends BaseHolder {

        private ImageView book1, book2, book3;
        private TextView bookName1, bookName2, bookName3;
        private TextView bookPress1, bookPress2, bookPress3;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            book1 = getViewById(R.id.book_1);
            book2 = getViewById(R.id.book_2);
            book3 = getViewById(R.id.book_3);
            bookName1 = getViewById(R.id.book_name_1);
            bookName2 = getViewById(R.id.book_name_2);
            bookName3 = getViewById(R.id.book_name_3);
            bookPress1 = getViewById(R.id.book_press_1);
            bookPress2 = getViewById(R.id.book_press_2);
            bookPress3 = getViewById(R.id.book_press_3);
        }
    }
}
