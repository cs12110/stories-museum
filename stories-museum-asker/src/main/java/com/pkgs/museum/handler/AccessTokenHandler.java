package com.pkgs.museum.handler;

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
public class AccessTokenHandler {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenHandler.class);

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
        poolExecutor.scheduleAtFixedRate(AccessTokenHandler::refreshToken, 0, 30, TimeUnit.MINUTES);
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
        //String url = AskUrlBuilder.buildAccessTokenUrl();
        //String feedback = String.valueOf(AskUtil.get(url));
        //
        //logger.info("Refresh access toke:{}", feedback);
        //
        //JSONObject object = JSON.parseObject(feedback);
        //String accessToken = object.getString("access_token");
        //
        //return SysUtil.isEmpty(accessToken) ? "" : accessToken;

        return "23_Wbd5ae1K5KLvh1TPhUKZzkMV93jKbMhE9v3hqxYZv8DHo5Cv5InxJloT2RZKnke4lgm9eeE7hZfXwN91nhohvar1IcV0wUeMN7EAr5jB9BQg36IJCau0EWHYmzSlFaLpiCTVXaUm70igWX2ETTObACAFFA";
    }
}
