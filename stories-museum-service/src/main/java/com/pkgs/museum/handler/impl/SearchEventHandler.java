package com.pkgs.museum.handler.impl;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.entity.sys.SysDict;
import com.pkgs.museum.entity.zhihu.AnswerEntity;
import com.pkgs.museum.entity.zhihu.TopicEntity;
import com.pkgs.museum.handler.EventHandler;
import com.pkgs.museum.service.sys.SysDictService;
import com.pkgs.museum.service.zhihu.ZhihuService;
import com.pkgs.museum.util.FeedbackXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service(value = "searchEventHandler")
public class SearchEventHandler implements EventHandler {

    private static Logger logger = LoggerFactory.getLogger(SearchEventHandler.class);

    @Resource
    private SysDictService sysDictService;

    @Resource
    private ZhihuService zhihuService;


    private static final String DEF_REPLY = "We can't find:${key} for you,sorry about that.";

    @Override
    public Object dealWith(Map<String, String> eventMap) {

        // 对用户搜索内容继续回复操作
        log.info(JSON.toJSONString(eventMap, true));
        String searchKey = eventMap.get("Content");
        log.info(searchKey);

        if ("menu".equals(searchKey)) {
            return dealWithMenu(eventMap);
        }

        // 根据话题来查询数据
        if (searchKey.startsWith("#")) {
            return dealWithAnswer(eventMap, searchKey);
        }

        // 回馈用户搜索内容
        //String reply = getReply(searchKey);
        //WxServiceHandler.sendTextMessage(fromUser, reply);

        return buildRandomAnswerXml(eventMap);
    }


    private String dealWithMenu(Map<String, String> eventMap) {
        return buildMenuXml(eventMap);
    }


    private String dealWithAnswer(Map<String, String> eventMap, String searchKey) {
        String topic = searchKey.substring(1);
        AnswerEntity answer = zhihuService.getAnswerByTopic(topic);


        if (null == answer) {
            String withoutAnyTips = "话题:" + topic + ",没有高赞回复,请输入`menu`,或者`#话题`(如#篮球),";
            return FeedbackXmlUtil.buildTextTipsXml(eventMap, withoutAnyTips);
        }

        return FeedbackXmlUtil.buildZhihuAnswerFeedbackXml(eventMap, answer);
    }

    private String buildMenuXml(Map<String, String> eventMap) {
        int index = 1;
        StringBuilder menu = new StringBuilder();

        List<TopicEntity> topicList = zhihuService.getTopicList();

        menu.append("----- 知乎话题 -----\n");
        for (TopicEntity topic : topicList) {
            menu.append(index).append(". ").append(topic.getName()).append(System.lineSeparator());
            index++;
        }

        menu.append("操作提示,输入`#话题`获取相关内容,如#篮球");

        return FeedbackXmlUtil.buildTextTipsXml(eventMap, menu.toString());
    }


    private String buildRandomAnswerXml(Map<String, String> eventMap) {
        AnswerEntity answer = zhihuService.getRandomTopAnswer();
        return FeedbackXmlUtil.buildZhihuAnswerFeedbackXml(eventMap, answer);
    }


    /**
     * 获取回复内容
     *
     * @param key key
     * @return String
     */
    private String getReply(String key) {
        SysDict dictValue = sysDictService.findDictValue(key);
        if (dictValue == null) {
            return DEF_REPLY.replace("${key}", key);
        }
        return dictValue.getDictValue();
    }
}
