package com.network.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;


public class NetworkStateReceiver extends BroadcastReceiver {
    public static final int MIN_SEND_DELAY_TIME = 3000;
    public static long lastClickTime = 0;
    private static Boolean networkAvailable = false;
    private static NetworkUtils.NetType netType;
    private static ArrayList<NetworkChangeObserver> netChangeObserverArrayList = new ArrayList<>();
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    //    public final static String ZW_ANDROID_NET_CHANGE_ACTION = "zw.android.net.conn.CONNECTIVITY_CHANGE";
    private static BroadcastReceiver receiver;

    /**
     * @return BroadcastReceiver 返回类型
     * @throws
     * @Title: getReceiver
     * @说 明:获得广播实例
     * @参 数: @return
     */
    private static BroadcastReceiver getReceiver() {
        if (receiver == null) {
            receiver = new NetworkStateReceiver();
        }
        return receiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        receiver = NetworkStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)
//                || intent.getAction().equalsIgnoreCase(ZW_ANDROID_NET_CHANGE_ACTION)
                ) {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                networkAvailable = false;
            } else {
                netType = NetworkUtils.getNetworkType(context);
                networkAvailable = true;
            }
            notifyObserver();
        }
    }

    /**
     * 注册网络状态广播
     *
     * @param mContext
     */
    public static void registerNetworkStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
//        filter.addAction(ZW_ANDROID_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 检查网络状态
     *
     * @param mContext
     */
    public static void checkNetworkState(Context mContext) {
        Intent intent = new Intent();
//        intent.setAction(ZW_ANDROID_NET_CHANGE_ACTION);
        mContext.sendBroadcast(intent);
    }

    /**
     * 注销网络状态广播
     *
     * @param mContext
     */
    public static void unRegisterNetworkStateReceiver(Context mContext) {
        if (receiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(receiver);
            } catch (Exception e) {
            }
        }

    }

    /**
     * 获取当前网络状态，true为网络连接成功，否则网络连接失败
     *
     * @return
     */
    public static Boolean isNetworkAvailable() {
        return networkAvailable;
    }

    public static NetworkUtils.NetType getAPNType() {
        return netType;
    }

    private void notifyObserver() {

        for (int i = 0; i < netChangeObserverArrayList.size(); i++) {
            NetworkChangeObserver observer = netChangeObserverArrayList.get(i);
            if (observer != null) {
                if (isNetworkAvailable()) {
                    observer.onConnect(netType);
                } else {
                    observer.onDisConnect();
                }
            }
        }

    }

    /**
     * 注册网络连接观察者
     *
     * @param observer observer
     */
    public static void registerObserver(NetworkChangeObserver observer) {
        if (netChangeObserverArrayList == null) {
            netChangeObserverArrayList = new ArrayList<>();
        }
        netChangeObserverArrayList.add(observer);
    }

    /**
     * 注销网络连接观察者
     *
     * @param observer observerKey
     */
    public static void removeRegisterObserver(NetworkChangeObserver observer) {
        if (netChangeObserverArrayList != null) {
            netChangeObserverArrayList.remove(observer);
        }
    }
}
