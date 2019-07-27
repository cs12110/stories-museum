package com.pkgs.museum.service.wx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.wx.WxAutoReply;
import com.pkgs.museum.enums.StatusEnum;
import com.pkgs.museum.mapper.wx.WxAutoReplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 13:12
 * <p>
 * @since 1.0.0
 */
@Slf4j
@Service
public class WxAutoReplyService {

    @Resource
    private WxAutoReplyMapper wxAutoReplyMapper;


    public List<WxAutoReply> findAutoReplyList() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("status", StatusEnum.USEFUL.getValue());

        Page<WxAutoReply> page = new Page<>();

        return wxAutoReplyMapper.selectByMap(page, map);
    }
}
