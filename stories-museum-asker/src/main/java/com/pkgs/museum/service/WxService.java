package com.pkgs.museum.service;

import com.pkgs.museum.handler.WxServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huanghuapeng create at 2019/7/26 10:06
 * @version 1.0.0
 */
public class WxService {

    private static Logger logger = LoggerFactory.getLogger(WxService.class);

    public static void main(String[] args) {


        for (int index = 0; index < 5; index++) {
            WxServiceHandler.sendTextMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", String.valueOf(System.currentTimeMillis()));
            WxServiceHandler.sendImageMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", "0gMh9eWwz6iY3b6tHG9vyOwcsqFtyRRUPFSFEe9Pp1i_rGyvoBHOHvoc7adU3mXA");
            WxServiceHandler.sendNewsMessage("oX1LUwPSirpY4DxJy2ELVN1PLyJo", "1234", "5678", "www.bing.com", "http://www.72xit.com/wp-content/uploads/2014/04/mysql.jpg");

            try {
                Thread.sleep(30000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
