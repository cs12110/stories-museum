package com.pkgs.museum.demo;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-26 23:20
 * <p>
 * @since 1.0.0
 */
public class XmlDemo {


    /**
     * 关注公众号回馈信息
     */
    public static String subscribeXml = "<xml><ToUserName><![CDATA[gh_aa25bff261ad]]></ToUserName>\n" +
            "<FromUserName><![CDATA[oX1LUwPSirpY4DxJy2ELVN1PLyJo]]></FromUserName>\n" +
            "<CreateTime>1564157037</CreateTime>\n" +
            "<MsgType><![CDATA[event]]></MsgType>\n" +
            "<Event><![CDATA[subscribe]]></Event>\n" +
            "<EventKey><![CDATA[]]></EventKey>\n" +
            "</xml>";


    /**
     * 取消关注公众号消息
     */
    public static String unsubscribeXml = "<xml><ToUserName><![CDATA[gh_aa25bff261ad]]></ToUserName>\n" +
            "<FromUserName><![CDATA[oX1LUwPSirpY4DxJy2ELVN1PLyJo]]></FromUserName>\n" +
            "<CreateTime>1564157020</CreateTime>\n" +
            "<MsgType><![CDATA[event]]></MsgType>\n" +
            "<Event><![CDATA[unsubscribe]]></Event>\n" +
            "<EventKey><![CDATA[]]></EventKey>\n" +
            "</xml>";

    /**
     * 发送到公众号的text信息
     */
    public static String textMesssageXml = "<xml><ToUserName><![CDATA[gh_aa25bff261ad]]></ToUserName>\n" +
            "<FromUserName><![CDATA[oX1LUwPSirpY4DxJy2ELVN1PLyJo]]></FromUserName>\n" +
            "<CreateTime>1564157103</CreateTime>\n" +
            "<MsgType><![CDATA[text]]></MsgType>\n" +
            "<Content><![CDATA[abc]]></Content>\n" +
            "<MsgId>22393348704684397</MsgId>\n" +
            "</xml>";
}
