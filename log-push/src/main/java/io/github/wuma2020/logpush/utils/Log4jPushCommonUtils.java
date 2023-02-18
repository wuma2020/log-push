package io.github.wuma2020.logpush.utils;

import com.yomahub.tlog.constant.TLogConstants;
import io.github.wuma2020.logpush.context.LogPushContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/2 22:59
 * FileName: PushCommonUtils
 * Description:
 */
@Slf4j
public class Log4jPushCommonUtils {

    /**
     * 理论上 event 就是日志对象
     *
     * @param event the event
     */
    public static void push(Object event) {

        if (StringUtils.isEmpty(LogPushContext.getLogPushLevel())) {
            return;
        }

        if (event instanceof LogEvent) {
            LogEvent iLoggingEvent = (LogEvent) event;
            String levelStr = iLoggingEvent.getLevel().toString();
            if (LogPushContext.getLogPushLevel().equalsIgnoreCase(levelStr)) {
                String url = LogPushContext.getUrl();
                if (Strings.isNotBlank(url)) {
                    String message = getMessage(iLoggingEvent);
                    DingTalkUtils.sendText(url, message);
                }
            }
        } else {
            System.out.println("event type not support : " + event.getClass().getName());
        }
    }

    private static String getMessage(LogEvent iLoggingEvent) {

        String message;
        if (iLoggingEvent.getThrownProxy() == null) {
            message = String.format(LogPushContext.getTextString(), LogPushContext.getAppName(), LogPushContext.getEnv(),
                    getCustomContextData(iLoggingEvent) + " " + iLoggingEvent.getMessage());
        } else {
            StringBuilder sb = new StringBuilder();
            getStackInfo(iLoggingEvent, sb);
            message = String.format(LogPushContext.getTextString() + LogPushContext.getStackInfo(), LogPushContext.getAppName(),
                    LogPushContext.getEnv(),
                    getCustomContextData(iLoggingEvent) + " " + iLoggingEvent.getMessage(), sb);
        }
        return message;
    }

    private static void getStackInfo(LogEvent iLoggingEvent, StringBuilder sb) {
        for (StackTraceElement sof : Arrays.asList(iLoggingEvent.getThrownProxy().getStackTrace())) {
            if (sb.length() > LogPushContext.getMaxStackNumber()) {
                return;
            }
            sb.append(sof.toString()).append("\n");
        }
    }

    private static String getCustomContextData(LogEvent iLoggingEvent) {
        return iLoggingEvent.getContextData().getValue(TLogConstants.MDC_KEY);
    }

}
