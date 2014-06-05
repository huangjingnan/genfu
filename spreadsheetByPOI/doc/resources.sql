/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50613
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2013-09-12 12:57:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `resource_id` int(12) NOT NULL,
  `resource_ip` varchar(200) DEFAULT NULL,
  `resource_name` varchar(200) DEFAULT NULL,
  `resource_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `un_idx_ip_name` (`resource_ip`,`resource_name`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
