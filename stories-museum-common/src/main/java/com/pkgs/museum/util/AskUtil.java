package com.pkgs.museum.util;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http request
 *
 * @author cs12110 create at 2019-07-25
 */
@Slf4j
public class AskUtil {

    static {
        // 设置http的logger
        ch.qos.logback.classic.Logger httpLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.apache.http");
        httpLogger.setLevel(Level.INFO);
    }

    /**
     * User Agent
     */
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";

    /**
     * By get
     *
     * @param url url
     */
    public static String get(String url) {
        String resultStr = null;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        setUserAgent(get);

        // 使用 try...resource
        try (CloseableHttpResponse result = client.execute(get)) {
            int code = result.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity entity = result.getEntity();
                resultStr = EntityUtils.toString(entity, "utf-8");
            } else {
                log.info("failure to get:{},{}", url, result.getStatusLine());
            }
            closeHttpClient(client);
        } catch (Exception e) {
            log.error("", e);
        }

        return resultStr;
    }

    /**
     * By post
     *
     * @param url    url
     * @param params 查询参数
     * @return String
     */
    public static String post(String url, Map<String, String> params) {
        HttpPost post = new HttpPost(url);
        // 设置请求参数
        if (null != params && params.size() > 0) {
            List<BasicNameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
                post.setEntity(entity);
            } catch (Exception e) {
                log.error("", e);
            }
        }

        return doPost(post);
    }

    /**
     * by post
     *
     * @param url     请求url
     * @param content 请求参数内容
     * @return String
     */
    public static String post(String url, String content) {
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(content, "utf-8");
        post.setEntity(stringEntity);
        return doPost(post);
    }


    /**
     * 请求
     *
     * @param post http post
     * @return String
     */
    private static String doPost(HttpPost post) {
        String resultStr = null;
        setUserAgent(post);
        // 请求
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try (CloseableHttpResponse result = client.execute(post)) {
            int code = result.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                HttpEntity entity = result.getEntity();
                resultStr = EntityUtils.toString(entity, "utf-8");
            } else {
                log.info("failure to post:{},{}", post.getURI(), result.getStatusLine());
            }
            closeHttpClient(client);
        } catch (Exception e) {
            log.error("", e);
        }

        return resultStr;
    }

    /**
     * 设置user-agent
     *
     * @param req {@link HttpUriRequest}
     */
    private static void setUserAgent(HttpUriRequest req) {
        req.setHeader("User-Agent", USER_AGENT);
    }

    /**
     * 关闭http client
     *
     * @param client client
     */
    private static void closeHttpClient(CloseableHttpClient client) {
        if (null != client) {
            try {
                client.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }

}
