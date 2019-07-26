package com.pkgs.museum.ctrl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/museum/")
public class MuseumCtrl {


    private static Logger logger = LoggerFactory.getLogger(MuseumCtrl.class);


    /**
     * 事件接口
     *
     * @param request  请求
     * @param response 反馈
     */
    @RequestMapping("/enter")
    public void enter(HttpServletRequest request, HttpServletResponse response) {
        try (ServletInputStream stream = request.getInputStream()) {
            byte[] arr = new byte[1024];
            int len;

            StringBuilder builder = new StringBuilder();
            while (-1 != (len = stream.read(arr))) {
                builder.append(new String(arr, 0, len));
            }
            logger.info("{}", builder.toString());

            response.getWriter().write("SUCCESS");
        } catch (Exception e) {
            logger.error("", e);
        }
    }


    /**
     * 微信公众号接入接口
     *
     * @param request request
     * @return String
     */
    @RequestMapping("/bigbang")
    @ResponseBody
    public Object bigbang(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, Object> map = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            map.put(key, request.getParameter(key));
        }

        logger.info(JSON.toJSONString(map, true));

        return map.get("echostr");
    }
}
