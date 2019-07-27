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

 Date: 27/07/2019 12:25:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_auto_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_auto_reply`;
CREATE TABLE `wx_auto_reply` (
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
  `udpate_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信自动回复内容表';

-- ----------------------------
-- Table structure for wx_user_t
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_t`;
CREATE TABLE `wx_user_t` (
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
