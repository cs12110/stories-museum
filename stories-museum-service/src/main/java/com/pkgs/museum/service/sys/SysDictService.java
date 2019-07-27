package com.pkgs.museum.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pkgs.museum.entity.sys.SysDict;
import com.pkgs.museum.enums.StatusEnum;
import com.pkgs.museum.mapper.sys.SysDictMapper;
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
public class SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;


    public SysDict findDictValue(String dictKey) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("dictKey", dictKey);
        map.put("status", StatusEnum.USEFUL.getValue());

        Page<SysDict> page = new Page<>();

        List<SysDict> dicts = sysDictMapper.selectByMap(page, map);

        return null == dicts || dicts.isEmpty() ? null : dicts.get(0);
    }
}
