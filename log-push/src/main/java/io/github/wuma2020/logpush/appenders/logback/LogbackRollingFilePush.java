package io.github.wuma2020.logpush.appenders.logback;

import ch.qos.logback.core.rolling.RollingFileAppender;
import io.github.wuma2020.logpush.utils.LogbackPushCommonUtils;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/2 22:56
 * FileName: LogbackRollingFilePush
 * Description: 文件 appender push实现类
 */
public class LogbackRollingFilePush extends RollingFileAppender {

    @Override
    protected void subAppend(Object event) {

        //完成了原先的日志输出功能
        super.subAppend(event);

        //发送推送
        LogbackPushCommonUtils.push(event);
    }
}
