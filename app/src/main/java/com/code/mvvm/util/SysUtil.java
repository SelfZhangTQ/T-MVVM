package com.code.mvvm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * @author：zhangtianqiu on 18/8/7 12:42
 */
public class SysUtil {
    public static void getCacheInfo(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memInfo);
        long availMem = memInfo.availMem / 1000000;//可用内存
        boolean isLowMem = memInfo.lowMemory;//是否达到最低内存
        long threshold = memInfo.threshold / 1000000;//临界值，达到这个值，进程就要被杀死
        long totalMem = memInfo.totalMem / 1000000;//总内存
//        Log.e("memInfo", "avail:" + availMem + ",isLowMem:" + isLowMem + ",threshold:" + threshold + ",totalMem:" + totalMem);
        //最大分配内存获取方法2
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
//         当前分配的总内存
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
        Log.e("memInfo", "maxMemory: " + maxMemory + ",totalMemory: " + totalMemory + ",freeMemory: " + freeMemory);

    }
}
