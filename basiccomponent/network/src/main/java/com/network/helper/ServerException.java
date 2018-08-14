package com.network.helper;

/**
 * @author：zhangtianqiu on 18/4/19 11:08
 */
public class ServerException extends RuntimeException {
    public String message;

    public int code;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
