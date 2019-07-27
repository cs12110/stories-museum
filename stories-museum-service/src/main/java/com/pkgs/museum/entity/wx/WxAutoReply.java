package com.pkgs.museum.entity.wx;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author cs12110 create at 2019-07-27 12:43
 * <p>
 * @since 1.0.0
 */
@Data
@TableName("t_wx_auto_reply")
public class WxAutoReply {

    private Integer id;

    private String code;

    private Integer status;

    private Integer type;

    private Integer seq;

    private String content;

    private String mediaId;

    private String url;

    private String title;

    private String description;

    private String picUrl;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
