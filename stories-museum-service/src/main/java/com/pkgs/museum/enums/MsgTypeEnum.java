package com.pkgs.museum.enums;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 00:21
 * <p>
 * @since 1.0.0
 */
public enum MsgTypeEnum {


    /**
     * 文本
     */
    TEXT("text"),


    /**
     * 事件
     */
    EVENT("event"),

    /**
     * 其他事件
     */
    OTHER("OTHER");

    private final String value;

    MsgTypeEnum(String initValue) {
        value = initValue;
    }

    public String getValue() {
        return value;
    }
}
