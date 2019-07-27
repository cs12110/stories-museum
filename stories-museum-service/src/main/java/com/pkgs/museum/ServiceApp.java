package com.pkgs.museum;

import com.pkgs.museum.service.wx.WxAutoReplyService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * App
 *
 * @author cs12110 create at 2019-07-27
 */
@SpringBootApplication
@MapperScan("com.pkgs.museum.mapper")
public class ServiceApp {

    private static Logger logger = LoggerFactory.getLogger(ServiceApp.class);


    @Resource
    private WxAutoReplyService wxAutoReplyService;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }

    @PostConstruct
    public void init() {
        logger.info("PostConstruct start");

        wxAutoReplyService.findAutoReplyList();
    }

}
