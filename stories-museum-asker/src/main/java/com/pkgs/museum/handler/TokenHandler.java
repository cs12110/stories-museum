package com.pkgs.museum.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pkgs.museum.builder.AskUrlBuilder;
import com.pkgs.museum.util.AskUtil;
import com.pkgs.museum.util.StrUtil;
import com.pkgs.museum.util.ThreadFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 微信token handler
 *
 * @author huanghuapeng create at 2019/7/26 17:10
 * @version 1.0.0
 */
public class TokenHandler {

    private static Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    /**
     * 微信用户token
     */
    private static String accessToken = null;

    static {
        // 定时刷新线程池
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(
                1,
                ThreadFactoryUtil.build("AccessTokenSchedulePool"));

        // 定时刷新
        poolExecutor.scheduleAtFixedRate(TokenHandler::refreshToken, 0, 30, TimeUnit.MINUTES);
    }


    /**
     * 获取token
     *
     * @return String
     */
    static String getAccessToken() {
        if (null == accessToken) {
            refreshToken();
        }
        return accessToken;
    }

    /**
     * 刷新token
     */
    private static void refreshToken() {
        accessToken = getAccessTokenByAsk();
    }


    /**
     * 获取access token
     *
     * @return String
     */
    private static String getAccessTokenByAsk() {
        String url = AskUrlBuilder.buildAccessTokenUrl();
        String feedback = String.valueOf(AskUtil.get(url));

        logger.info("Refresh access toke:{}", feedback);

        JSONObject object = JSON.parseObject(feedback);
        String accessToken = object.getString("access_token");

        return StrUtil.isEmpty(accessToken) ? "" : accessToken;
    }
}
