package com.code.mvvm.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.code.mvvm.App;

/**
 * @description
 * @author：tqzhang on 19/5/8 10:24
 */
public class ToastUtils {


    private static String oldMsg;

    private static Toast toast = null;

    private static long oneTime = 0;

    private static long twoTime = 0;

    public static void showToast(String s) {
        if (null == s) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(App.instance(), s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void showToast(Context context, String s, int duration) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, s, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();

            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > duration) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
