package com.pkgs.museum.util;

/**
 * @author huanghuapeng create at 2019/8/5 10:13
 * @version 1.0.0
 */
public class RandomUtil {

    /**
     * 获取随机数
     *
     * @param max 最大数(不包含)
     * @return int
     */
    public static int getRandomInt(int max) {
        return (int) (Math.random() * max);
    }
}
