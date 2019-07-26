package com.pkgs.museum.util;

import java.util.List;

/**
 * @author huanghuapeng create at 2019/7/26 10:31
 * @version 1.0.0
 */
public class SysUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }


    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return boolean
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }
}
