package com.pkgs.museum.handler.impl;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.handler.EventHandler;
import com.pkgs.museum.handler.WxServiceHandler;
import com.pkgs.museum.util.SysUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 00:15
 * <p>
 * @since 1.0.0
 */
@Slf4j
@Service(value = "searchEventHandler")
public class SearchEventHandler implements EventHandler {

    @Override
    public Object dealWith(Map<String, String> eventMap) {
        String fromUser = eventMap.get("FromUserName");
        String searchKey = eventMap.get("Content");

        // 对用户搜索内容继续回复操作
        log.info(JSON.toJSONString(eventMap, true));
        log.info(searchKey);


        // 回馈用户搜索内容
        String reply = getReply(searchKey);
        WxServiceHandler.sendTextMessage(fromUser, reply);

        return null;
    }


    /**
     * 获取回复内容
     *
     * @param key key
     * @return String
     */
    private String getReply(String key) {
        if (SysUtil.isEmpty(key)) {
            return "请输入内容";
        }

        return "你输入的是: " + key;
    }
}
