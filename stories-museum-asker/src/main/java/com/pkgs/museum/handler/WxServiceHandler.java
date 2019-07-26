package com.pkgs.museum.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pkgs.museum.builder.MessageBuilder;
import com.pkgs.museum.builder.AskUrlBuilder;
import com.pkgs.museum.util.AskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghuapeng create at 2019/7/26 9:53
 * @version 1.0.0
 */
public class WxServiceHandler {

    private static Logger logger = LoggerFactory.getLogger(WxServiceHandler.class);

    ///**
    // * 获取access token
    // *
    // * @return String
    // */
    //public static String getAccessToken() {
    //    String url = AskUrlBuilder.buildAccessTokenUrl();
    //    String feedback = String.valueOf(AskUtil.get(url));
    //
    //    logger.info("Get access toke:{}", feedback);
    //
    //    JSONObject object = JSON.parseObject(feedback);
    //    String accessToken = object.getString("access_token");
    //
    //    return SysUtil.isEmpty(accessToken) ? "" : accessToken;
    //}

    /**
     * 获取关注公众号的用户OpenId
     *
     * @return List
     */
    public static List<String> getFollowUsers() {
        String value = AskUtil.get(AskUrlBuilder.buildFollowUserUrl(AccessTokenHandler.getAccessToken()));
        JSONObject object = JSON.parseObject(value);
        JSONArray array = object.getJSONObject("data").getJSONArray("openid");

        logger.info("Get follow users:{}", value);

        List<String> wxOpenIdList = new ArrayList<>();
        if (array != null) {
            for (Object each : array) {
                wxOpenIdList.add(String.valueOf(each));
            }
        }
        return wxOpenIdList;
    }


    /**
     * 获取维信用户信息
     *
     * <p>
     * 官方文档: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839
     * </p>
     * 请求接口:
     * <pre>
     *     https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     * </pre>
     *
     * @param openId 用户openId
     * @return Object
     */
    public static String getUserInfo(String openId) {
        String token = AccessTokenHandler.getAccessToken();
        String value = AskUtil.get(AskUrlBuilder.buildUserInfoUrl(token, openId));
        logger.info("Get user:{} info:{} ", openId, value);
        return value;
    }

    /**
     * 发送消息接口
     *
     * @param openId  openId
     * @param message 消息
     * @return String
     */
    public static String sendTextMessage(String openId, String message) {
        String text = MessageBuilder.text(openId, message);
        return sendMessage(text);
    }


    /**
     * 发送图片消息
     *
     * @param openId       openId
     * @param imageMediaId 图片媒体id
     * @return String
     */
    public static String sendImageMessage(String openId, String imageMediaId) {
        String image = MessageBuilder.image(openId, imageMediaId);
        return sendMessage(image);
    }

    /**
     * 发送图文连接
     *
     * @param openId 微信用户OpenId
     * @param title  标题
     * @param desc   描述
     * @param url    跳转url
     * @param picUrl 图标url
     * @return String
     */
    public static String sendNewsMessage(String openId, String title, String desc, String url, String picUrl) {
        String news = MessageBuilder.news(openId, title, desc, url, picUrl);
        return sendMessage(news);
    }


    /**
     * 发送消息
     *
     * @param messageJSON 消息json数据
     * @return String
     */
    private static String sendMessage(String messageJSON) {
        String token = AccessTokenHandler.getAccessToken();
        String url = AskUrlBuilder.buildSendMessageUrl(token);
        String result = AskUtil.post(url, messageJSON);
        logger.info("Send:{}, result:{}", messageJSON, result);
        return result;
    }
}
