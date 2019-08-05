package com.pkgs.museum.mapper.zhihu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.zhihu.AnswerEntity;
import com.pkgs.museum.entity.zhihu.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TODO: MapperXML.vm for Answer
 * <p>
 *
 * @author cs12110 create at: 2019/1/6 19:51
 * Since: 1.0.0
 */
public interface AnswerMapper extends BaseMapper<AnswerEntity> {

    /**
     * 获取答案总数
     *
     * @return int
     */
    int watchdog();

    /**
     * 新增
     *
     * @param entity entity
     * @return int
     */
    int save(AnswerEntity entity);

    /**
     * 统计符合条件的数据
     *
     * @param search 查询条件
     * @return int
     */
    int selectCount(@Param("cm") AnswerEntity search);

    /**
     * 根据连接获取id
     *
     * @param link 连接
     * @return Integer
     */
    Integer selectIdByLink(String link);

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param columnMap 查询条件
     * @return List
     */
    List<AnswerEntity> selectByMap(Page<AnswerEntity> page, @Param("cm") Map<String, Object> columnMap);


    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param columnMap 查询条件
     * @return List
     */
    List<AnswerEntity> selectByMap1(Page<AnswerEntity> page, @Param("cm") Map<String, Object> columnMap);


    List<AnswerMapper> selectByTopics(List<TopicEntity> topicList);

    /**
     * 更新点赞数
     *
     * @param answerId 回答id
     * @param voteNum  点赞数
     * @return 更新成功返回1, 否则返回0
     */
    int updateVoteNum(@Param("id") Integer answerId, @Param("voteNum") Integer voteNum);
}
