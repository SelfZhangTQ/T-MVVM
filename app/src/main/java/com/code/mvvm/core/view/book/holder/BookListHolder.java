package com.code.mvvm.core.view.book.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.book.BookVo;

/**
 * @author：tqzhang on 18/6/20 13:41
 */
public class BookListHolder extends AbsItemHolder<BookVo, BookListHolder.ViewHolder> {

    public BookListHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_book_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookVo bookAd) {
        holder.bookName.setText(bookAd.title);
        holder.pressName.setText(bookAd.publishing_name);
        holder.price.setText("￥" + bookAd.price);
        Glide.with(mContext).load(bookAd.img.l.url).placeholder(R.color.black_e8e8e8).into(holder.bookImg);
    }


    static class ViewHolder extends AbsHolder {

        private TextView bookName, pressName, price;
        private ImageView bookImg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            pressName = getViewById(R.id.press_name);
            bookName = getViewById(R.id.book_name);
            price = getViewById(R.id.price);
            bookImg = getViewById(R.id.book_img);

        }
    }
}
