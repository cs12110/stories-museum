package com.pkgs.museum.enums;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 00:18
 * <p>
 * @since 1.0.0
 */
public enum EventEnum {

    /**
     * 关注事件
     */
    SUBSCRIBE("subscribe"),

    /**
     * 取消关注事件
     */
    UNSUBSCRIBE("unsubscribe"),


    /**
     * 其他
     */
    OTHER("other");

    private final String value;

    EventEnum(String initValue) {
        value = initValue;
    }

    public String getValue() {
        return value;
    }
}
