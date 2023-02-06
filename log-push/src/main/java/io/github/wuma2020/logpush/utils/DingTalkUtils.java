package io.github.wuma2020.logpush.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import com.yomahub.tlog.context.TLogContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/1/22 17:29
 * FileName: DingTalkUtils
 * Description:
 */
@Slf4j
public class DingTalkUtils {

    /**
     * 发送文本消息
     *
     * @param url
     * @param text
     */
    public static void sendText(String url, String text) {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text textType = new OapiRobotSendRequest.Text();
        textType.setContent("业务报警:" + text);
        request.setText(textType);
        try {
            //发送是否失败，不做处理，直接抛出异常即可。如果再调用log.error容易造成死循环
            client.execute(request);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
