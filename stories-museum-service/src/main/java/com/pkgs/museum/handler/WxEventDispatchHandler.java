package com.pkgs.museum.handler;

import com.pkgs.museum.enums.MsgTypeEnum;
import com.pkgs.museum.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信事件回调处理类
 * <p>
 *
 * @author cs12110 create at 2019-07-27 00:11
 * <p>
 * @since 1.0.0
 */
@Service
@Slf4j
public class WxEventDispatchHandler {

    @Resource(name = "searchEventHandler")
    private EventHandler searchEventHandler;

    @Resource(name = "followEventHandler")
    private EventHandler followEventHandler;


    /**
     * 处理事件
     *
     * @param xml xml
     */
    public Object dealWith(String xml) {
        Map<String, String> xmlMap = parseXml2Map(xml);
        MsgTypeEnum msgType = getMsgType(xmlMap);

        log.info("Message type:{}", msgType);

        if (msgType == MsgTypeEnum.EVENT) {
            return followEventHandler.dealWith(xmlMap);
        }

        if (msgType == MsgTypeEnum.TEXT) {
            return searchEventHandler.dealWith(xmlMap);
        }

        return null;
    }


    private MsgTypeEnum getMsgType(Map<String, String> xmlMap) {
        String msgType = xmlMap.get("MsgType");
        for (MsgTypeEnum e : MsgTypeEnum.values()) {
            if (e.getValue().equals(msgType)) {
                return e;
            }
        }
        return MsgTypeEnum.OTHER;
    }

    private Map<String, String> parseXml2Map(String xml) {
        return XmlUtil.toMap(xml);
    }
}
