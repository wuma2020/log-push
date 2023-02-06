package io.github.wuma2020.logpush.appenders.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.yomahub.tlog.core.enhance.logback.async.AspectLogbackAsyncAppender;
import io.github.wuma2020.logpush.utils.LogbackPushCommonUtils;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/2 23:12
 * FileName: LogbackAspectLogbackAsync
 * Description:
 */
public class LogbackAspectLogbackAsyncPush extends AspectLogbackAsyncAppender {


    @Override
    protected void append(ILoggingEvent eventObject) {

        //完成了原先的日志输出功能
        super.append(eventObject);

        //发送推送
        LogbackPushCommonUtils.push(eventObject);

    }
}
