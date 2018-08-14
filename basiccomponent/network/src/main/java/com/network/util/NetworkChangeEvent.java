package com.network.util;


public class NetworkChangeEvent {
    private NetworkUtils.NetType netType;

    public NetworkUtils.NetType getNetType() {
        return netType;
    }

    public void setNetType(NetworkUtils.NetType netType) {
        this.netType = netType;
    }
}
