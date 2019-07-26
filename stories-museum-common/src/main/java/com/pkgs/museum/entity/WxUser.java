package com.pkgs.museum.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @author huanghuapeng create at 2019/7/26 11:26
 * @version 1.0.0
 */
@Data
public class WxUser {
    private int subscribe;
    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private long subscribe_time;
    private String remark;
    private int groupid;
    private List<String> tagid_list;
    private String subscribe_scene;
    private int qr_scene;
    private String qr_scene_str;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
