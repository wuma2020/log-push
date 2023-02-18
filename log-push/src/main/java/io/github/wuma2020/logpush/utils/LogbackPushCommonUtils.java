package io.github.wuma2020.logpush.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.yomahub.tlog.constant.TLogConstants;
import com.yomahub.tlog.context.TLogContext;
import com.yomahub.tlog.core.context.AspectLogContext;
import io.github.wuma2020.logpush.context.LogPushContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/2 22:59
 * FileName: PushCommonUtils
 * Description:
 */
@Slf4j
public class LogbackPushCommonUtils {

    /**
     * 理论上 event 就是日志对象
     *
     * @param event the event
     */
    public static void push(Object event) {

        if (StringUtils.isEmpty(LogPushContext.getLogPushLevel())) {
            return;
        }

        if (event instanceof ILoggingEvent) {
            ILoggingEvent iLoggingEvent = (ILoggingEvent) event;
            String levelStr = iLoggingEvent.getLevel().levelStr;
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

    private static String getMessage(ILoggingEvent iLoggingEvent) {
        String message = null;
        if (iLoggingEvent.getThrowableProxy() == null) {
            message = getMessageNoException(iLoggingEvent);
        } else {
            message = getMessageWithException(iLoggingEvent);
        }
        return message;
    }

    private static String getMessageNoException(ILoggingEvent iLoggingEvent) {
        String message;
        message = String.format(LogPushContext.getTextString(), LogPushContext.getAppName(), LogPushContext.getEnv(),
                getCustomContextData(iLoggingEvent));
        return message;
    }

    private static String getMessageWithException(ILoggingEvent iLoggingEvent) {
        String message;
        StringBuilder sb = new StringBuilder();
        getStackInfo(iLoggingEvent,sb);
        message = String.format(LogPushContext.getTextString() + LogPushContext.getStackInfo(), LogPushContext.getAppName(),
                LogPushContext.getEnv(),
                getCustomContextData(iLoggingEvent), sb);
        return message;
    }

    private static void getStackInfo(ILoggingEvent iLoggingEvent, StringBuilder sb) {
        for (StackTraceElementProxy sof : iLoggingEvent.getThrowableProxy().getStackTraceElementProxyArray()) {
            if(sb.length() > LogPushContext.getMaxStackNumber()) {
                return;
            }
            sb.append(sof.toString()).append("\n");
        }
    }

    private static String getCustomContextData(ILoggingEvent iLoggingEvent) {
        if (iLoggingEvent.getMDCPropertyMap() != null) {
            return iLoggingEvent.getMDCPropertyMap().get(TLogConstants.MDC_KEY) + " " + iLoggingEvent.getMessage();
        } else if (!TLogContext.hasTLogMDC()) {
            return iLoggingEvent.getFormattedMessage();
        } else {
            return AspectLogContext.getLogValue() + " " + iLoggingEvent.getMessage();
        }
    }

}
