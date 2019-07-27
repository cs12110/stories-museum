package com.pkgs.museum.mapper.wx;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.wx.WxAutoReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 12:59
 * <p>
 * @since 1.0.0
 */
public interface WxAutoReplyMapper extends BaseMapper<WxAutoReply> {


    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param columnMap 查询条件
     * @return List
     */
    List<WxAutoReply> selectByMap(Page<WxAutoReply> page, @Param("cm") Map<String, Object> columnMap);


}
