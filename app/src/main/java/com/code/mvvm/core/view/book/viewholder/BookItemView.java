package com.code.mvvm.core.view.book.viewholder;

import android.support.annotation.NonNull;
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
import com.code.mvvm.core.data.pojo.book.BookList;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class BookItemView extends AbsItemView<BookList, BookItemView.ViewHolder> {
    private int commonwidth;

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonwidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                - DisplayUtil.dp2px(App.Instance(), 30)) / 3);
        return new ViewHolder(inflater.inflate(R.layout.home_remmend_book, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookList mBookList) {
        //图书推荐
        double dv = 1.3;
        int width = commonwidth;
        int high = (int) (width * dv);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, high);

        holder.book1.setLayoutParams(layoutParams);
        holder.book2.setLayoutParams(layoutParams);
        holder.book3.setLayoutParams(layoutParams);
        Glide.with(App.Instance()).load(mBookList.publishingbook.get(0).img.l.url).placeholder(R.color.white).centerCrop().into(holder.book1);
        Glide.with(App.Instance()).load(mBookList.publishingbook.get(1).img.l.url).placeholder(R.color.white).centerCrop().into(holder.book2);
        Glide.with(App.Instance()).load(mBookList.publishingbook.get(2).img.l.url).placeholder(R.color.white).centerCrop().into(holder.book3);
        holder.bookName1.setText(mBookList.publishingbook.get(0).title);
        holder.bookName2.setText(mBookList.publishingbook.get(1).title);
        holder.bookName3.setText(mBookList.publishingbook.get(2).title);
        holder.bookPress1.setText(mBookList.publishingbook.get(0).publishing_name);
        holder.bookPress2.setText(mBookList.publishingbook.get(1).publishing_name);
        holder.bookPress3.setText(mBookList.publishingbook.get(2).publishing_name);
    }


    static class ViewHolder extends BaseViewHolder {

        private @NonNull
        final ImageView book1, book2, book3;
        private TextView bookName1, bookName2, bookName3;
        private TextView bookPress1, bookPress2, bookPress3;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            book1 = itemView.findViewById(R.id.book_1);
            book2 = itemView.findViewById(R.id.book_2);
            book3 = itemView.findViewById(R.id.book_3);
            bookName1 = itemView.findViewById(R.id.book_name_1);
            bookName2 = itemView.findViewById(R.id.book_name_2);
            bookName3 = itemView.findViewById(R.id.book_name_3);
            bookPress1 = itemView.findViewById(R.id.book_press_1);
            bookPress2 = itemView.findViewById(R.id.book_press_2);
            bookPress3 = itemView.findViewById(R.id.book_press_3);
        }
    }
}
