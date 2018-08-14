package com.network.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * author：zhangtianqiu on 18/1/22 11:09
 */
public class NetworkUtils {


    public enum NetType {
        NETWORK_UNKNOWN, NETWORK_WIFI, NETWORK_3_G, NETWORK_4_G, NETWORK_2_G, NETWORK_NO_CONNECT
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
   public static String getConnectType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {

                if (mNetworkInfo.getType()==1){
                    return "WIFI";

                }else if(mNetworkInfo.getType()==2){
                    return "4G";

                }else if(mNetworkInfo.getType()==3){
                    return "4G";
                }
            }
        }
        return "没有网络";
    }

    /**
     * @return netType 返回类型
     * @throws
     * @方法名: getAPNType
     * @说 明: 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     * @参 数: @param context
     * @参 数: @return
     */
    public static NetType getNetworkType(Context context) {
        NetType netType = null;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NetType.NETWORK_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        netType = NetType.NETWORK_2_G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        netType = NetType.NETWORK_3_G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        netType = NetType.NETWORK_4_G;
                        break;
                    default:
                        String typeName = networkInfo.getSubtypeName();
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (typeName.equalsIgnoreCase("TD-SCDMA") || typeName.equalsIgnoreCase("WCDMA") || typeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NetType.NETWORK_3_G;
                        } else {
                            netType = NetType.NETWORK_UNKNOWN;
                        }
                        break;
                }
            }else {
                netType = NetType.NETWORK_UNKNOWN;
            }
        }else {
            netType = NetType.NETWORK_NO_CONNECT;
        }

        return netType;
    }

    public static class APNNet {
        /**
         * Network type is unknown
         */
        public static final int NETWORK_TYPE_UNKNOWN = 0;
        /**
         * Current network is GPRS
         */
        public static final int NETWORK_TYPE_GPRS = 1;
        /**
         * Current network is EDGE
         */
        public static final int NETWORK_TYPE_EDGE = 2;
        /**
         * Current network is UMTS
         */
        public static final int NETWORK_TYPE_UMTS = 3;
        /**
         * Current network is CDMA: Either IS95A or IS95B
         */
        public static final int NETWORK_TYPE_CDMA = 4;
        /**
         * Current network is EVDO revision 0
         */
        public static final int NETWORK_TYPE_EVDO_0 = 5;
        /**
         * Current network is EVDO revision A
         */
        public static final int NETWORK_TYPE_EVDO_A = 6;
        /**
         * Current network is 1xRTT
         */
        public static final int NETWORK_TYPE_1xRTT = 7;
        /**
         * Current network is HSDPA
         */
        public static final int NETWORK_TYPE_HSDPA = 8;
        /**
         * Current network is HSUPA
         */
        public static final int NETWORK_TYPE_HSUPA = 9;
        /**
         * Current network is HSPA
         */
        public static final int NETWORK_TYPE_HSPA = 10;
        /**
         * Current network is iDen
         */
        public static final int NETWORK_TYPE_IDEN = 11;
        /**
         * Current network is EVDO revision B
         */
        public static final int NETWORK_TYPE_EVDO_B = 12;
        /**
         * Current network is LTE
         */
        public static final int NETWORK_TYPE_LTE = 13;
        /**
         * Current network is eHRPD
         */
        public static final int NETWORK_TYPE_EHRPD = 14;
        /**
         * Current network is HSPA+
         */
        public static final int NETWORK_TYPE_HSPAP = 15;
        /**
         * Current network is GSM
         */
        public static final int NETWORK_TYPE_GSM = 16;
        /**
         * Current network is TD_SCDMA
         */
        public static final int NETWORK_TYPE_TD_SCDMA = 17;
        /**
         * Current network is IWLAN
         */
        public static final int NETWORK_TYPE_IWLAN = 18;
    }
}
