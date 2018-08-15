package com.code.mvvm.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.code.mvvm.R;

/**
 * @author：tqzhang on 18/8/15 11:24
 */
public class ViewUtils {

    public static View CreateTagView(Context context, String tagContent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_tag, null);
        TextView tvTag = view.findViewById(R.id.tv_tag);
        tvTag.setText(tagContent);
        return view;
    }

}
