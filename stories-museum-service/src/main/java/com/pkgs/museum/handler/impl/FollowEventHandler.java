package com.pkgs.museum.handler.impl;

import com.pkgs.museum.enums.EventEnum;
import com.pkgs.museum.handler.EventHandler;
import com.pkgs.museum.handler.WxServiceHandler;
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
@Service(value = "followEventHandler")
public class FollowEventHandler implements EventHandler {

    @Override
    public Object dealWith(Map<String, String> eventMap) {
        String event = eventMap.get("Event");
        EventEnum eventEnum = getEventEnum(event);

        log.info("{}", eventEnum);

        if (eventEnum == EventEnum.SUBSCRIBE) {
            dealWithSubscribe(eventMap);
        }

        if (eventEnum == EventEnum.UNSUBSCRIBE) {
            dealWithUnsubscribe(eventMap);
        }

        return null;
    }

    private EventEnum getEventEnum(String event) {
        for (EventEnum e : EventEnum.values()) {
            if (e.getValue().equals(event)) {
                return e;
            }
        }
        return EventEnum.OTHER;
    }

    /**
     * 处理用户关注
     *
     * @param eventMap eventMap
     */
    private void dealWithSubscribe(Map<String, String> eventMap) {

        String formUser = eventMap.get("FromUserName");


        String title = "欢迎关注我们的公众号,May u have fun";
        String desc = "这个一个关于故事的公众号";
        String url = "https://github.com/cs12110/stories-museum";
        String picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564170859120&di=98c823f33e470662725f6d780f7191c1&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F2e2eb9389b504fc2bbdd8ce9ebdde71191ef6d5f.jpg";

        WxServiceHandler.sendNewsMessage(formUser, title, desc, url, picUrl);
    }

    /**
     * 处理用户取消关注
     *
     * @param eventMap eventMap
     */
    private void dealWithUnsubscribe(Map<String, String> eventMap) {

    }
}
