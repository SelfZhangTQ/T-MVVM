package com.code.mvvm.core.view.book.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.book.BookVo;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseViewHolder;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class BookListHolder extends AbsViewHolder<BookVo, BookListHolder.ViewHolder> {

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
        holder.book_name.setText(bookAd.title);
        holder.press_name.setText(bookAd.publishing_name);
        holder.price.setText("￥" + bookAd.price);
        Glide.with(mContext).load(bookAd.img.l.url).placeholder(R.color.black_e8e8e8).into(holder.book_img);
    }


    static class ViewHolder extends BaseViewHolder {

        private TextView book_name, press_name, price;
        private ImageView book_img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            press_name = getViewById(R.id.press_name);
            book_name = getViewById(R.id.book_name);
            price = getViewById(R.id.price);
            book_img = getViewById(R.id.book_img);

        }
    }
}
