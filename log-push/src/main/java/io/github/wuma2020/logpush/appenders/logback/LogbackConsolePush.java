package io.github.wuma2020.logpush.appenders.logback;

import ch.qos.logback.core.ConsoleAppender;
import io.github.wuma2020.logpush.utils.LogbackPushCommonUtils;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/1/20 20:42
 * FileName: DingdingPush
 * Description:
 */
public class LogbackConsolePush<E> extends ConsoleAppender<E> {

    @Override
    protected void subAppend(E event) {

        //完成了原先的日志输出功能
        super.subAppend(event);

        //发送推送
        LogbackPushCommonUtils.push(event);
    }

}
