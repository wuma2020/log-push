package io.github.wuma2020.logpush.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
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
public class LogbackPushCommonUtils {

    /**
     * 理论上 event 就是日志对象
     *
     * @param event
     */
    public static void push(Object event) {

        if (StringUtils.isEmpty(LogPushContext.getLogPushLevel())) {
            return;
        }

        //对指定等级日志进行推送报警
        if (event instanceof ILoggingEvent) {
            ILoggingEvent iLoggingEvent = (ILoggingEvent) event;
            String levelStr = iLoggingEvent.getLevel().levelStr;
            if (LogPushContext.getLogPushLevel().equalsIgnoreCase(levelStr)) {
                //进行获取日志信息和钉钉信息，进行推送
                String url = LogPushContext.getUrl();
                if (Strings.isNotBlank(url)) {
                    String message = null;
                    if (iLoggingEvent.getThrowableProxy() == null) {
                        message = String.format("traceID:[%s],错误msg:[%s],info:[%s]", TLogContext.getTraceId(), iLoggingEvent.getMessage(), AspectLogContext.getLogValue());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        // TODO 栈信息行数可以做成可以控制的，比如前30行或者50行堆栈信息
                        Arrays.asList(iLoggingEvent.getThrowableProxy().getStackTraceElementProxyArray()).forEach(sof -> {
                            sb.append(sof.toString()).append("\r");
                        });
                        message = String.format("traceID:[%s],错误msg:[%s],info:[%s],异常栈信息:\r[%s]", TLogContext.getTraceId(), iLoggingEvent.getMessage(), AspectLogContext.getLogValue(), sb);
                    }
                    DingTalkUtils.sendText(url, message);
                }
            }
        }else {
            System.out.println("event type not support : " + event.getClass().getName());
        }
    }

}
