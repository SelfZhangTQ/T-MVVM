package com.code.mvvm.core.view.book.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.mvvm.App;
import com.code.mvvm.R;
import com.code.mvvm.core.data.pojo.book.BookVo;
import com.code.mvvm.util.DisplayUtil;
import com.trecyclerview.multitype.AbsItemView;

/**
 * @author：zhangtianqiu on 18/6/20 13:41
 */
public class BookListItemView extends AbsItemView<BookVo, BookListItemView.ViewHolder> {
private int commonwidth;
    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        commonwidth = (int) (((float) DisplayUtil.getScreenWidth(App.Instance())
                - DisplayUtil.dp2px(App.Instance(), 30)) / 3);
        return new ViewHolder(inflater.inflate(R.layout.item_book_list, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BookVo bookAd) {
        holder.book_name.setText(bookAd.title);
        holder.press_name.setText(bookAd.publishing_name);
        holder.price.setText("￥" + bookAd.price);
        ImageView book_img=holder.book_img;
        if (!TextUtils.isEmpty(bookAd.img.l.url)) {
            if (!bookAd.img.l.url.equals((book_img.getTag(R.id.book_id)))) {
                book_img.setTag(R.id.book_id, bookAd.img.l.url);
                Glide.with(App.Instance()).load(bookAd.img.l.url).placeholder(R.color.black_333333).skipMemoryCache(true).into(book_img);
            }
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

private TextView book_name,press_name,price;
private ImageView book_img;
private TextView book_press_1,book_press_2,book_press_3;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            press_name = itemView.findViewById(R.id.press_name);
            book_name = itemView.findViewById(R.id.book_name);
            price = itemView.findViewById(R.id.price);
            book_img = itemView.findViewById(R.id.book_img);

        }
    }
}
