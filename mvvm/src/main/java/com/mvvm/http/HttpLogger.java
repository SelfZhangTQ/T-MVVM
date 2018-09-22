package com.mvvm.http;


import com.mvvm.util.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author：tqzhang on 18/9/8 21:31
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private static String TAG = HttpLogger.class.getSimpleName();

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = Logger.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        if (message.startsWith("<-- END HTTP")) {
            Logger.e(TAG, mMessage.toString());
        }
    }
}