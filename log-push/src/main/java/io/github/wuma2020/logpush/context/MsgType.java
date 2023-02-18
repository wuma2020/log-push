package io.github.wuma2020.logpush.context;

import java.util.Arrays;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/18 20:45
 * FileName: MsgType
 * Description:
 */
public enum MsgType {

    /**
     * Text type msg type.
     */
    TEXT_TYPE("text", "文本类型"),
    /**
     * Markdown text msg type.
     */
    MARKDOWN_TEXT("markdown", "markdown类型"),
    /**
     * Image text msg type.
     */
    IMAGE_TEXT("image", "图片类型"),

    ;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 类型描述
     */
    private String desc;

    MsgType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据字符串获取消息类型
     *
     * @param typeString the type string
     * @return type by string
     */
    public static MsgType getTypeByString(String typeString) {
        MsgType msgType = Arrays.stream(MsgType.values()).filter(type -> {
            if (type.getType().equals(typeString)) {
                return true;
            }
            return false;
        }).findFirst().orElse(TEXT_TYPE);
        return msgType;
    }

}
