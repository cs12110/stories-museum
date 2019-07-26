package com.pkgs.museum.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author huanghuapeng create at 2019/7/26 9:47
 * @version 1.0.0
 */
public class PropUtil {

    private static Logger logger = LoggerFactory.getLogger(PropUtil.class);

    /**
     * 获取properties文件配置内容
     *
     * @param propertiesFileName 配置文件名称,必须位于resource/文件夹下
     * @return Map
     */
    public static Map<String, Object> get(String propertiesFileName) {
        Map<String, Object> valueMap = new HashMap<>();
        try (InputStream stream = PropUtil.class.getResourceAsStream(propertiesFileName)) {
            Properties properties = new Properties();
            properties.load(stream);
            Enumeration<?> names = properties.propertyNames();
            while (names.hasMoreElements()) {
                Object key = names.nextElement();
                String value = properties.getProperty(String.valueOf(key));
                valueMap.put(String.valueOf(key), value);
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        logger.info("Get: {} values:{}", propertiesFileName, JSON.toJSONString(valueMap, true));

        return valueMap;
    }
}
