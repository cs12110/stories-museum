package com.pkgs.museum.service.zhihu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.zhihu.AnswerEntity;
import com.pkgs.museum.mapper.zhihu.AnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author huanghuapeng create at 2019/8/1 14:05
 * @version 1.0.0
 */
@Service
public class ZhihuService {

    private static Logger logger = LoggerFactory.getLogger(ZhihuService.class);

    @Resource
    private AnswerMapper answerMapper;


    /**
     * 获取随机的top answer
     *
     * @return AnswerEntity
     */
    public AnswerEntity getRandomTopAnswer() {
        long start = System.currentTimeMillis();
        // 获取总数
        int total = answerMapper.watchdog();
        int current = randomNum(total);


        Page<AnswerEntity> page = new Page<>(current, 1);
        List<AnswerEntity> list = answerMapper.selectByMap(page, Collections.emptyMap());

        // 随机获取一个答案
        AnswerEntity target = list.get(0);

        long end = System.currentTimeMillis();
        logger.info("Get:{}/{},spend:{}", current, total, (end - start));

        return target;
    }

    private static int randomNum(int max) {
        return (int) (Math.random() * max);
    }
}
