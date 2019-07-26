package com.pkgs.museum.service;

import com.pkgs.museum.handler.WxHandler;

/**
 * @author huanghuapeng create at 2019/7/26 10:06
 * @version 1.0.0
 */
public class WxService {

    public static void main(String[] args) {

        WxHandler.sendTextMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", String.valueOf(System.currentTimeMillis()));
        WxHandler.sendImageMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", "0gMh9eWwz6iY3b6tHG9vyOwcsqFtyRRUPFSFEe9Pp1i_rGyvoBHOHvoc7adU3mXA");
        WxHandler.sendNewsMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", "1234", "5678", "www.bing.com", "http://www.72xit.com/wp-content/uploads/2014/04/mysql.jpg");
    }


}
