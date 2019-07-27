package com.pkgs.museum.builder;

/**
 * message builder
 *
 * @author huanghuapeng create at 2019/7/26 13:31
 * @version 1.0.0
 */
public class MessageBuilder {

    /**
     * 建立文本消息
     *
     * <pre>
     * {
     *     "touser":"${openId}",
     *     "msgtype":"text",
     *     "text":
     *         {
     *              "content":"${message}"
     *      }
     * }
     * </pre>
     *
     * @param openId openId
     * @param text   文本内容
     * @return String
     */
    public static String text(String openId, String text) {
        return "{\n" +
                "    \"touser\":\"" + openId + "\",\n" +
                "    \"msgtype\":\"text\",\n" +
                "    \"text\":\n" +
                "    {\n" +
                "         \"content\":\"" + text + "\"\n" +
                "    }\n" +
                "}";
    }


    /**
     * 建立图片消息
     *
     * @param openId  微信用户openId
     * @param mediaId 媒体内容Id
     * @return String
     */
    public static String image(String openId, String mediaId) {
        return "{\n" +
                "    \"touser\":\"" + openId + "\",\n" +
                "    \"msgtype\":\"image\",\n" +
                "    \"image\":\n" +
                "    {\n" +
                "      \"media_id\":\"" + mediaId + "\"\n" +
                "    }\n" +
                "}";
    }


    /**
     * 图文连接
     *
     * <pre>
     * {
     *     "touser":"OPENID",
     *     "msgtype":"news",
     *     "news":{
     *         "articles": [
     *          {
     *              "title":"Happy Day",
     *              "description":"Is Really A Happy Day",
     *              "url":"URL",
     *              "picurl":"PIC_URL"
     *          }
     *          ]
     *     }
     * }
     * </pre>
     *
     * @param openId 用户openId
     * @param title  标题
     * @param desc   内容描述
     * @param url    跳转连接
     * @param picUrl 图片连接
     * @return String
     */
    public static String news(String openId, String title, String desc, String url, String picUrl) {
        return "{\n" +
                "    \"touser\":\"" + openId + "\",\n" +
                "    \"msgtype\":\"news\",\n" +
                "    \"news\":{\n" +
                "        \"articles\": [\n" +
                "         {\n" +
                "             \"title\":\"" + title + "\",\n" +
                "             \"description\":\"" + desc + "\",\n" +
                "             \"url\":\"" + url + "\",\n" +
                "             \"picurl\":\"" + picUrl + "\"\n" +
                "         }\n" +
                "         ]\n" +
                "    }\n" +
                "}";
    }


}
