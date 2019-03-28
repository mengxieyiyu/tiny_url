/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1-linux
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : tinyurldb

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-28 18:41:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tiny_url
-- ----------------------------
DROP TABLE IF EXISTS `tiny_url`;
CREATE TABLE `tiny_url` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `short_url` varchar(100) NOT NULL DEFAULT '' COMMENT '短url',
  `long_url` varchar(500) NOT NULL DEFAULT '' COMMENT '长url',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（1:正常,2:冻结,3:删除）',
  PRIMARY KEY (`id`),
  KEY `index_2` (`long_url`) USING BTREE,
  KEY `index_1` (`short_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='短网址服务表';

-- ----------------------------
-- Records of tiny_url
-- ----------------------------
INSERT INTO `tiny_url` VALUES ('14', 'F3eaey', 'https://music.163.com/#/discover/toplist?id=2250011882', '2019-03-28 17:48:05', '2019-03-28 17:48:05', '1');
INSERT INTO `tiny_url` VALUES ('16', 'EbERna', 'https://blog.csdn.net/MyMBS/article/details/79827832', '2019-03-28 17:54:10', '2019-03-28 17:54:10', '1');
INSERT INTO `tiny_url` VALUES ('17', 'Eb6N73', 'https://www.cnblogs.com/cielosun/p/6582333.html', '2019-03-28 18:09:27', '2019-03-28 18:09:27', '1');
INSERT INTO `tiny_url` VALUES ('18', 'hasdghaoihgoiw', 'https://www.cnblogs.com/cielosun/p/6582333.html', '2019-03-28 18:11:07', '2019-03-28 18:11:07', '1');
INSERT INTO `tiny_url` VALUES ('19', 'aIBN7f', 'https://music.163.com/#/discover/playlist', '2019-03-28 18:32:27', '2019-03-28 18:32:27', '1');
INSERT INTO `tiny_url` VALUES ('20', 'fAVJVb', 'https://github.com/apache/rocketmq', '2019-03-28 18:37:49', '2019-03-28 18:37:49', '1');
