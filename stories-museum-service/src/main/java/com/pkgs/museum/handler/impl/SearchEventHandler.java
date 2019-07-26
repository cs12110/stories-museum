package com.pkgs.museum.handler.impl;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.handler.EventHandler;
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
        String searchKey = eventMap.get("Content");

        // 对用户搜索内容继续回复操作
        log.info(JSON.toJSONString(eventMap, true));
        log.info(searchKey);


        return null;
    }
}
