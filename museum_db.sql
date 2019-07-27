/*
 Navicat Premium Data Transfer

 Source Server         : 47.98.104.252
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 47.98.104.252:3306
 Source Schema         : museum_db

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 27/07/2019 14:29:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_key` varchar(64) NOT NULL COMMENT 'key',
  `dict_value` varchar(512) DEFAULT NULL COMMENT 'VALUE',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态,0:失效,1:有效',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_key` (`dict_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典表';

-- ----------------------------
-- Table structure for t_wx_auto_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_auto_reply`;
CREATE TABLE `t_wx_auto_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态,0:失效,1:有效',
  `type` tinyint(3) DEFAULT NULL COMMENT '类型,1:text,2:image,3:news',
  `seq` int(3) DEFAULT NULL COMMENT '显示顺序',
  `content` varchar(256) DEFAULT NULL COMMENT 'text.content',
  `media_id` varchar(256) DEFAULT NULL COMMENT 'image.mediaId',
  `url` varchar(256) DEFAULT NULL COMMENT 'news.url',
  `title` varchar(256) DEFAULT NULL COMMENT 'news.title',
  `description` varchar(256) DEFAULT NULL COMMENT 'news.description',
  `pic_url` varchar(256) DEFAULT NULL COMMENT 'news.picUrl',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信自动回复内容表';

-- ----------------------------
-- Table structure for t_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_user`;
CREATE TABLE `t_wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `nickname` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信用户昵称',
  `open_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信用户openId',
  `union_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信用户unionId',
  `subscribe` tinyint(4) DEFAULT NULL COMMENT '是否订阅',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别,男:1,女:0',
  `country` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家',
  `province` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
  `city` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
  `head_img_url` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像url',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录创建人',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录更新人',
  `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2472 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信用户表';

SET FOREIGN_KEY_CHECKS = 1;
