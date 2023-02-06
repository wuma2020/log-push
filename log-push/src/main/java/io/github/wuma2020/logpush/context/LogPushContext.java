package io.github.wuma2020.logpush.context;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/1/28 22:34
 * FileName: LogPushContext
 * Description: 最基础的日志推送上下文--以后可以继续扩展
 */
public class LogPushContext {

    /**
     * 发送消息的群 服务器url
     */
    public static String url;

    /**
     * 需要推送的日志级别
     */
    public static String logPushLevel;


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        LogPushContext.url = url;
    }

    public static String getLogPushLevel() {
        return logPushLevel;
    }

    public static void setLogPushLevel(String logPushLevel) {
        LogPushContext.logPushLevel = logPushLevel;
    }
}
