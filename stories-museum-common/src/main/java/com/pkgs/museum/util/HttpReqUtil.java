package com.pkgs.museum.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http request
 * 
 * @author cs12110 create at 2019-07-25
 *
 */
public class HttpReqUtil {

	/**
	 * 日志类
	 */
	protected static final Logger logger = LoggerFactory.getLogger(HttpReqUtil.class);

	/**
	 * User Agent
	 */
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";

	/**
	 * By get
	 *
	 * @param url url
	 */
	public static Object get(String url) {
		String resultStr = null;

		// client是否要关闭?
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
				logger.info("failure to get:{},{}", url, result.getStatusLine());
			}
			closeHttpClient(client);
		} catch (Exception e) {
			logger.error("", e);
		}

		return resultStr;
	}

	/**
	 * By post
	 *
	 * @param url    url
	 * @param params 查询参数
	 * @return R
	 */
	public static String post(String url, Map<String, String> params) {
		String resultStr = null;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		setUserAgent(post);

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
				logger.error("", e);
			}
		}

		// 请求
		try (CloseableHttpResponse result = client.execute(post)) {
			int code = result.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity entity = result.getEntity();
				resultStr = EntityUtils.toString(entity, "utf-8");
			} else {
				logger.info("failure to post:{},{}", url, result.getStatusLine());
			}
			closeHttpClient(client);
		} catch (Exception e) {
			logger.error("", e);
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
