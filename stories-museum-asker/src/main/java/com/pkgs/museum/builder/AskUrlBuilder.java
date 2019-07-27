package com.pkgs.museum.builder;

import com.pkgs.museum.util.PropUtil;

import java.util.Map;

/**
 * 请求地址url builder
 *
 * @author huanghuapeng create at 2019/7/26 9:54
 * @version 1.0.0
 */
public class AskUrlBuilder {

    private static final String PROP_FILE_NAME = "/wx.properties";

    private static String appId;
    private static String appSecret;

    static {
        Map<String, Object> valueMap = PropUtil.get(PROP_FILE_NAME);
        appId = String.valueOf(valueMap.get("wx.app.id"));
        appSecret = String.valueOf(valueMap.get("wx.app.secret"));
    }


    /**
     * 获取access token url
     *
     * @return String
     */
    public static String buildAccessTokenUrl() {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&secret=${appSecret}";
        return accessTokenUrl.replace("${appId}", appId).replace("${appSecret}", appSecret);
    }


    /**
     * 获取关注公众号的用户url
     *
     * @param token token
     * @return String
     */
    public static String buildFollowUserUrl(String token) {
        String followUsersUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=${token}";
        return followUsersUrl.replace("${token}", token);
    }


    /**
     * 获取用户信息
     *
     * @param token  token
     * @param openId 微信用户openId
     * @return String
     */
    public static String buildUserInfoUrl(String token, String openId) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${token}&openid=${openId}&lang=zh_CN";
        return url.replace("${token}", token).replace("${openId}", openId);
    }

    /**
     * 发送消息
     *
     * @param token token
     * @return String
     */
    public static String buildSendMessageUrl(String token) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${token}";
        return url.replace("${token}", token);
    }
}
