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
    private static String url;

    /**
     * 需要推送的日志级别
     */
    private static String logPushLevel = "ERROR";

    /**
     * 当前应用的名称
     */
    private static String appName = "appName";

    /**
     * 当前环境-一般是dev,test,pre,prod
     */
    private static String env = "test";

    /**
     * 默认的消息卡片类型
     */
    private static MsgType MESSAGE_TYPE = MsgType.TEXT_TYPE;

    /**
     * 输出最大的堆栈深度
     */
    private static Integer maxStackNumber = 10000;

    /**
     * text 消息格式下的内容
     */
    private static String TEXT_STRING = "应用名称:%s\n环境:%s\n信息:%s\n";

    /**
     * 堆栈信息
     */
    private static String STACK_INFO = "堆栈信息:%s\n";

    /**
     * Gets stack info.
     *
     * @return the stack info
     */
    public static String getStackInfo() {
        return STACK_INFO;
    }

    /**
     * Sets stack info.
     *
     * @param stackInfo the stack info
     */
    public static void setStackInfo(String stackInfo) {
        LogPushContext.STACK_INFO = stackInfo;
    }

    /**
     * Gets message type.
     *
     * @return the message type
     */
    public static MsgType getMessageType() {
        return MESSAGE_TYPE;
    }

    /**
     * Sets message type.
     *
     * @param messageType the message type
     */
    public static void setMessageType(MsgType messageType) {
        LogPushContext.MESSAGE_TYPE = messageType;
    }

    /**
     * Gets text string.
     *
     * @return the text string
     */
    public static String getTextString() {
        return TEXT_STRING;
    }

    /**
     * Sets text string.
     *
     * @param textString the text string
     */
    public static void setTextString(String textString) {
        LogPushContext.TEXT_STRING = textString;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public static String getAppName() {
        return appName;
    }

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public static void setAppName(String appName) {
        LogPushContext.appName = appName;
    }

    /**
     * Gets env.
     *
     * @return the env
     */
    public static String getEnv() {
        return env;
    }

    /**
     * Sets env.
     *
     * @param env the env
     */
    public static void setEnv(String env) {
        LogPushContext.env = env;
    }

    /**
     * Gets max stack number.
     *
     * @return the max stack number
     */
    public static Integer getMaxStackNumber() {
        return maxStackNumber;
    }

    /**
     * Sets max stack number.
     *
     * @param maxStackNumber the max stack number
     */
    public static void setMaxStackNumber(Integer maxStackNumber) {
        LogPushContext.maxStackNumber = maxStackNumber;
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
