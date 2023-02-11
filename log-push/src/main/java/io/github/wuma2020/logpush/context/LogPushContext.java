package io.github.wuma2020.logpush.context;

import lombok.Data;

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
    public static String logPushLevel = "ERROR";

    /**
     * 当前应用的名称
     */
    public static String appName = "appName";

    /**
     * 当前环境-一般是dev,test,pre,prod
     */
    public static String env = "test";

    /**
     * 默认的消息卡片类型
     */
    public static String MESSAGE_TYPE = "text";

    /**
     * text 消息格式下的内容
     */
    public static String TEXT_STRING = "应用名称:%s\r环境:%s\r信息:%s\r";

    /**
     * 堆栈信息
     */
    public static String STACK_INFO = "堆栈信息:%s\r";

    public static String getStackInfo() {
        return STACK_INFO;
    }

    public static void setStackInfo(String stackInfo) {
        LogPushContext.STACK_INFO = stackInfo;
    }

    public static String getMessageType() {
        return MESSAGE_TYPE;
    }

    public static void setMessageType(String messageType) {
        LogPushContext.MESSAGE_TYPE = messageType;
    }

    public static String getTextString() {
        return TEXT_STRING;
    }

    public static void setTextString(String textString) {
        LogPushContext.TEXT_STRING = textString;
    }

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        LogPushContext.appName = appName;
    }

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        LogPushContext.env = env;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public static void setUrl(String url) {
        LogPushContext.url = url;
    }

    /**
     * Gets log push level.
     *
     * @return the log push level
     */
    public static String getLogPushLevel() {
        return logPushLevel;
    }

    /**
     * Sets log push level.
     *
     * @param logPushLevel the log push level
     */
    public static void setLogPushLevel(String logPushLevel) {
        LogPushContext.logPushLevel = logPushLevel;
    }
}
