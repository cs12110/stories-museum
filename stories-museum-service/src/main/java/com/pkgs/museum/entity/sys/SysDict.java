package com.pkgs.museum.entity.sys;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 12:46
 * <p>
 * @since 1.0.0
 */
@Data
public class SysDict {

    private Integer id;

    private String dictKey;

    private String dictValue;

    private Integer status;

    private String remark;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
