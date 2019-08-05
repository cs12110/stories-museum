package com.pkgs.museum.util;

import com.pkgs.museum.entity.zhihu.AnswerEntity;

import java.util.Map;

/**
 * @author huanghuapeng create at 2019/8/5 9:45
 * @version 1.0.0
 */
public class FeedbackXmlUtil {

    /**
     * 拼凑知乎高赞答案回复内容xml
     *
     * @param eventMap 事件map
     * @param answer   回答对象
     * @return String
     */
    public static String buildZhihuAnswerFeedbackXml(Map<String, String> eventMap, AnswerEntity answer) {
        String toUserName = eventMap.get("FromUserName");
        String fromUserName = eventMap.get("ToUserName");
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + toUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>\n" +
                "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "  <MsgType><![CDATA[news]]></MsgType>\n" +
                "  <ArticleCount>1</ArticleCount>\n" +
                "  <Articles>\n" +
                "    <item>\n" +
                "      <Title><![CDATA[" + answer.getQuestion() + "]]></Title>\n" +
                "      <Description><![CDATA[" + answer.getSummary() + "]]></Description>\n" +
                "      <PicUrl><![CDATA[" + answer.getAuthorImg() + "]]></PicUrl>\n" +
                "      <Url><![CDATA[" + answer.getLink() + "]]></Url>\n" +
                "    </item>\n" +
                "  </Articles>\n" +
                "</xml>";
    }

    /**
     * 文字提示内容xml
     *
     * @param eventMap 事件map
     * @param tips     提示内容
     * @return String
     */
    public static String buildTextTipsXml(Map<String, String> eventMap, String tips) {
        String toUserName = eventMap.get("FromUserName");
        String fromUserName = eventMap.get("ToUserName");

        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + toUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>\n" +
                "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + tips + "]]></Content>\n" +
                "</xml>";
    }
}
