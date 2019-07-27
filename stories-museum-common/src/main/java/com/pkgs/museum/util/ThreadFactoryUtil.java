package com.pkgs.museum.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 *
 * @author huanghuapeng create at 2019/7/26 17:21
 * @version 1.0.0
 */
public class ThreadFactoryUtil {

    /**
     * 创建threadFactory
     *
     * @param factoryName threadFactory名称
     * @return ThreadFactory
     */

    public static ThreadFactory build(String factoryName) {
        return new MyThreadFactory(factoryName);
    }


    /**
     * 自定义线程工厂
     */
    private static class MyThreadFactory implements ThreadFactory {

        private String factoryName;
        private AtomicInteger count = new AtomicInteger(1);

        MyThreadFactory(String factoryName) {
            this.factoryName = factoryName;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, factoryName + "-" + count.getAndIncrement());
        }
    }
}
