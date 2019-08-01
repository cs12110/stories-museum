package com.pkgs.museum.ctrl;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.handler.WxEventDispatchHandler;
import com.pkgs.museum.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信回调接口
 *
 * @author cs12110 create 2019-07-27
 */
@Controller
@RequestMapping(value = "/museum/")
public class MuseumCtrl {


    private static Logger logger = LoggerFactory.getLogger(MuseumCtrl.class);


    @Resource
    private WxEventDispatchHandler handler;

    /**
     * 微信公众号接入接口,与其他事件是同一个接口
     * <p>
     * 比如用户在公众号输入关键字的时候,会能通过try2Read方法获取到 orz
     *
     * @param request request
     * @return String
     */
    @RequestMapping("/bigbang")
    @ResponseBody
    public void bigbang(HttpServletRequest request, HttpServletResponse response) {
        String reqValue = try2Read(request);
        logger.info("\n{}", reqValue);

        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, Object> map = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            map.put(key, request.getParameter(key));
        }


        logger.info("\n{}", JSON.toJSONString(map, true));

        response.setCharacterEncoding("utf-8");
        try (PrintWriter writer = response.getWriter()) {
            String feedback = try2SendMessageToStoriesMuseum(reqValue);
            try2Write(writer, feedback);
            //try2Write(writer, String.valueOf(map.get("echostr")));
        } catch (Exception e) {
            logger.error("", e);
        }
    }


    /**
     * 读取事件内容
     *
     * @param request 请求
     * @return String
     */
    private String try2Read(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        try (ServletInputStream stream = request.getInputStream()) {
            byte[] arr = new byte[1024];
            int len;
            while (-1 != (len = stream.read(arr))) {
                builder.append(new String(arr, 0, len));
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return builder.toString();
    }


    private void try2Write(PrintWriter writer, String message) {
        writer.write(message);
        writer.flush();
    }


    private String try2SendMessageToStoriesMuseum(String xml) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, String> eventMap = XmlUtil.toMap(xml);
        String toUserName = eventMap.get("FromUserName");
        String fromUserName = eventMap.get("ToUserName");
        String content = eventMap.get("Content");


        String feedback = sdf.format(new Date()) + ",输入了: " + content;

        String textXml = "<xml>\n" +
                "  <ToUserName><![CDATA[" + toUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>\n" +
                "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + feedback + "]]></Content>\n" +
                "</xml>";

        //try (PrintWriter writer = response.getWriter()) {
        //    writer.write(textXml);
        //    writer.flush();
        //} catch (Exception e) {
        //    logger.error("", e);
        //}

        return textXml;

    }
}
