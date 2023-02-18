package io.github.wuma2020.logpush.springconfig;

import io.github.wuma2020.logpush.context.LogPushContext;
import io.github.wuma2020.logpush.context.MsgType;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/1/22 17:37
 * FileName: CommonConfig
 * Description:
 */
@Configuration
@ConfigurationProperties(prefix = "log.push")
@Data
public class CommonConfig implements InitializingBean {

    /**
     * 发送消息的群 服务器url
     */
    private String url;

    /**
     * 需要推送的日志级别
     */
    private String logPushLevel;

    /**
     * 消息的类型
     */
    private String msgType;

    /**
     * 当前应用的名称
     */
    private String appName = "appName";

    /**
     * 当前环境-一般是dev,test,pre,prod
     */
    private String env = "test";

    /**
     * 输出最大的堆栈深度
     */
    private Integer maxStackNumber = 1000;

    @Override
    public void afterPropertiesSet() throws Exception {
        LogPushContext.setUrl(this.getUrl());
        LogPushContext.setLogPushLevel(this.getLogPushLevel());
        LogPushContext.setMessageType(MsgType.getTypeByString(this.getMsgType()));
        LogPushContext.setEnv(this.getEnv());
        LogPushContext.setAppName(this.getAppName());
        LogPushContext.setMaxStackNumber(this.getMaxStackNumber());
    }
}
