package io.github.wuma2020.logpush.utils;

import com.yomahub.tlog.constant.TLogConstants;
import com.yomahub.tlog.context.TLogContext;
import com.yomahub.tlog.core.context.AspectLogContext;
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

        //对指定等级日志进行推送报警
        if (event instanceof LogEvent) {
            LogEvent iLoggingEvent = (LogEvent) event;
            String levelStr = iLoggingEvent.getLevel().toString();
            if (LogPushContext.getLogPushLevel().equalsIgnoreCase(levelStr)) {
                //进行获取日志信息和钉钉信息，进行推送
                String url = LogPushContext.getUrl();
                if (Strings.isNotBlank(url)) {
                    String message = null;
                    if (iLoggingEvent.getThrownProxy() == null) {
                        message = String.format(LogPushContext.TEXT_STRING, LogPushContext.getAppName(), LogPushContext.getEnv(),
                                getCustomContextData(iLoggingEvent) + " " + iLoggingEvent.getMessage());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        // TODO 栈信息行数可以做成可以控制的，比如前30行或者50行堆栈信息
                        Arrays.asList(iLoggingEvent.getThrownProxy().getStackTrace()).forEach(sof -> {
                            sb.append(sof.toString()).append("\r");
                        });
                        //"应用名称:%s\n环境:%s\n信息:%s\n堆栈信息:%s\n"
                        message = String.format(LogPushContext.TEXT_STRING + LogPushContext.getStackInfo(), LogPushContext.getAppName(),
                                LogPushContext.getEnv(),
                                getCustomContextData(iLoggingEvent) + " " + iLoggingEvent.getMessage(), sb);
                    }
                    DingTalkUtils.sendText(url, message);
                }
            }
        } else {
            System.out.println("event type not support : " + event.getClass().getName());
        }
    }

    private static String getCustomContextData(LogEvent iLoggingEvent) {
        return iLoggingEvent.getContextData().getValue(TLogConstants.MDC_KEY);
    }

}
