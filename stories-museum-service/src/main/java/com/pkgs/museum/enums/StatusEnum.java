package com.pkgs.museum.enums;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 14:12
 * <p>
 * @since 1.0.0
 */
public enum StatusEnum {

    /**
     * 有效
     */
    USEFUL(1),

    /**
     * 失效
     */
    DISABLE(0);

    StatusEnum(Integer v) {
        this.value = v;
    }

    private final Integer value;


    public Integer getValue() {
        return value;
    }
}
