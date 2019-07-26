# 微信公众号

微信公众号官方文档: [link](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183)

因为是个人的公众号,所以几乎没什么能玩的.

---

## 1. 公众号配置

注意: <u>一个邮箱只能注册一个微信东西,如果你的邮箱注册了小程序,想注册公众号的话,必须换另一个邮箱.</u>

### 1.1 公众号配置属性

| 配置名称              | 配置值                           |
| --------------------- | -------------------------------- |
| 开发者 ID(AppID)      | wx13a560116ac43e71               |
| 开发者密码(AppSecret) | c39049de02354bb7c81c47d13b66d8d6 |

### 1.2 获取 AccessToken

微信公众号的 AccessToken 会在 7200 秒后过期,因此在生产环境里面需要定时的刷新这个 token.

---

## 2. 相关代码

### 2.1 公众号接入代码

```java
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
```

### 2.2 微信回调接口

不知道在实际的有权限的操作里面,是不是可以给某些事件配置对应的回调接口,还是所有的调用都是通过同一个接口,然后得到消息体再自己进行区分.

```java
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
```

例如通过页面调用测试[test page](https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95&form=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95%E5%88%9B%E5%BB%BA%E6%8E%A5%E5%8F%A3%20/menu/creat)

![](imgs/event.jpg)

接口接受到微信传输过来的消息如下

```java
2019-07-26 09:09:07 INFO  com.pkgs.museum.ctrl.MuseumCtrl:35 - {"URL":"https://mr3306.top/museum/museum/enter","ToUserName":"haiyan","FromUserName":"mr3306","CreateTime":20190726,"MsgType":"event","Event":"subscribe","Latitude":0,"Longitude":0,"Precision":0,"MsgId":1234}
```

---

## 3. 通用接口类型

通用接口类型可以在这个页面找到相关文档: [link](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454)

然后可以在这个页面上进行测试操作: [link](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454)
