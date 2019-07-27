package com.pkgs.museum.util;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.demo.XmlDemo;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml工具类
 * <p>
 *
 * @author cs12110 create at 2019-07-26 23:10
 * <p>
 * @since 1.0.0
 */
@Slf4j
public class XmlUtil {


    /**
     * 将xml转换成map
     *
     * @param xml xml
     * @return map
     */
    public static Map<String, String> toMap(String xml) {
        Map<String, String> valueMap = new HashMap<>();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();

            List<Element> elements = root.elements();
            for (Element e : elements) {
                valueMap.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            log.error("", e);

        }

        log.info(JSON.toJSONString(valueMap));

        return valueMap;
    }

    public static void main(String[] args) {
        toMap(XmlDemo.subscribeXml);
    }
}
