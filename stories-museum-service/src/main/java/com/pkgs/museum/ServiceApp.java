package com.pkgs.museum;

import com.alibaba.fastjson.JSON;
import com.pkgs.museum.entity.zhihu.AnswerEntity;
import com.pkgs.museum.service.zhihu.ZhihuService;
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
    private ZhihuService zhihuService;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }

    @PostConstruct
    public void init() {
        logger.info("PostConstruct start");


        AnswerEntity answer = zhihuService.getRandomTopAnswer();
        logger.info(JSON.toJSONString(answer, true));
    }

}
