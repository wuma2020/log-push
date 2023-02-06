package io.github.wuma2020.logpush.springconfig;

import io.github.wuma2020.logpush.context.LogPushContext;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        LogPushContext.setUrl(this.getUrl());
        LogPushContext.setLogPushLevel(this.getLogPushLevel());
    }
}
