/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : eee

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-01-07 16:39:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '200', 'xxx', '2018-10-26', '1', '1');
INSERT INTO `account` VALUES ('2', '300', 'xx', '2018-10-27', '2', '1');
INSERT INTO `account` VALUES ('3', '300', 'xxdsds', '2018-10-24', '3', '8');
INSERT INTO `account` VALUES ('4', '400', 'z', '2018-10-25', '1', '1');

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', 'A', '1', 'a', '8');
INSERT INTO `items` VALUES ('2', 'D', '0', 'd', '1');
INSERT INTO `items` VALUES ('3', 'C', '1', 'c', '1');
INSERT INTO `items` VALUES ('4', 'B', '0', 'b', '8');
INSERT INTO `items` VALUES ('5', 'E', '1', 'e', '9');
INSERT INTO `items` VALUES ('6', 'F', '0', 'f', '9');
INSERT INTO `items` VALUES ('7', 'G', '1', 'g', '10');
INSERT INTO `items` VALUES ('8', 'H', '0', 'h', '10');
INSERT INTO `items` VALUES ('9', 'J2', 't22', 'j2', '11');
INSERT INTO `items` VALUES ('11', 'J22', 't222', 'j22', '8');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `truename` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'leifeng', 'z', '123', '1');
INSERT INTO `user` VALUES ('8', 'zhaizo2ng', 'lym2', '6767322', '0');
INSERT INTO `user` VALUES ('9', 'leifeng4', 'zzzz', '245', '1');
INSERT INTO `user` VALUES ('10', 'lengfeng', 'lym', '1243', '0');
INSERT INTO `user` VALUES ('11', 'zhaizong', 'lym', '67673', '0');
INSERT INTO `user` VALUES ('12', 'wife_reaper', 'lym', '67673', '0');
