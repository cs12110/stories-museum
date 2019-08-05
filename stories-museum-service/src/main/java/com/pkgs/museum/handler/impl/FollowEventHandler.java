package com.pkgs.museum.handler.impl;

import com.pkgs.museum.entity.wx.WxAutoReply;
import com.pkgs.museum.enums.EventEnum;
import com.pkgs.museum.handler.EventHandler;
import com.pkgs.museum.handler.WxServiceHandler;
import com.pkgs.museum.service.wx.WxAutoReplyService;
import com.pkgs.museum.util.FeedbackXmlUtil;
import com.pkgs.museum.util.SysUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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

    @Resource
    private WxAutoReplyService wxAutoReplyService;

    @Override
    public Object dealWith(Map<String, String> eventMap) {
        String event = eventMap.get("Event");
        EventEnum eventEnum = getEventEnum(event);

        log.info("{}", eventEnum);

        if (eventEnum == EventEnum.SUBSCRIBE) {
            // dealWithSubscribe(eventMap);
            return buildTipsXml(eventMap);
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
        List<WxAutoReply> replies = wxAutoReplyService.findAutoReplyList();
        if (!SysUtil.isEmpty(replies)) {
            for (WxAutoReply reply : replies) {
                // 文本消息
                if (reply.getType() == 1) {
                    WxServiceHandler.sendTextMessage(formUser, reply.getContent());
                } else if (reply.getType() == 3) {
                    WxServiceHandler.sendNewsMessage(formUser, reply.getTitle(), reply.getDescription(), reply.getUrl(), reply.getPicUrl());
                }
            }
        }
    }

    private String buildTipsXml(Map<String, String> eventMap) {
        String feedback = "--- 3306 Museum ---"
                + "\n\nJust for fun,stories museum"
                + "\n\n知乎高赞回答,请输入`menu` 查看菜单,输入`#话题` 获取话题答案(如#篮球)";

        return FeedbackXmlUtil.buildTextTipsXml(eventMap, feedback);
    }

    /**
     * 处理用户取消关注
     *
     * @param eventMap eventMap
     */
    private void dealWithUnsubscribe(Map<String, String> eventMap) {

    }
}
