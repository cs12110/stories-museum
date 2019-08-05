package com.pkgs.museum.service.zhihu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.zhihu.AnswerEntity;
import com.pkgs.museum.entity.zhihu.TopicEntity;
import com.pkgs.museum.mapper.zhihu.AnswerMapper;
import com.pkgs.museum.mapper.zhihu.TopicMapper;
import com.pkgs.museum.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author huanghuapeng create at 2019/8/1 14:05
 * @version 1.0.0
 */
@Service
public class ZhihuService {

    private static Logger logger = LoggerFactory.getLogger(ZhihuService.class);


    @Resource
    private TopicMapper topicMapper;

    @Resource
    private AnswerMapper answerMapper;


    /**
     * 获取一级话题
     *
     * @return List
     */
    public List<TopicEntity> getTopicList() {
        return topicMapper.queryTopTopics();
    }

    /**
     * 根据话题随机获取里面的一个答案
     *
     * @param topicName 话题名称
     * @return AnswerEntity
     */
    public AnswerEntity getAnswerByTopic(String topicName) {
        try {
            // 获取该话题下的所有子话题
            Integer topicId = topicMapper.queryTopTopicIdByName(topicName);
            if (null == topicId) {
                logger.info("Can't find:{}", topicName);
                return null;
            }

            List<TopicEntity> list = topicMapper.queryTopics(String.valueOf(topicId));
            List<Integer> topicIdList = new ArrayList<>();
            for (TopicEntity t : list) {
                topicIdList.add(t.getId());
            }

            Page<AnswerEntity> page = new Page<>();
            Map<String, Object> conditionMap = new HashMap<>(1);
            conditionMap.put("topicIdList", topicIdList);

            List<AnswerEntity> answerEntities = answerMapper.selectByMap1(page, conditionMap);
            int index = RandomUtil.getRandomInt(answerEntities.size());
            return answerEntities.get(index);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

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
