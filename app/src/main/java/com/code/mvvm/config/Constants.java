package com.code.mvvm.config;

/**
 * @author：zhangtianqiu on 18/7/27 16:19
 */
public class Constants {
    public static final String NET_WORK_STATE = "1";
    public static final String ERROR_STATE = "2";
    public static final String LOADING_STATE = "3";
    public static final String SUCCESS_STATE = "4";

    public static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_CACHE_MEMORY_SIZE = MAX_HEAP_SIZE / 4;
    public static final int MAX_CACHE_DISK_SIZE = 250 * 1024 * 1024;

}
