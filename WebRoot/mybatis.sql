/*
Navicat MySQL Data Transfer

Source Server         : myconnection
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-11-28 15:24:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`nid`),
  KEY `userid` (`userid`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', '1', '标题1', '内容1');
INSERT INTO `note` VALUES ('2', '1', '标题2', '内容2');
INSERT INTO `note` VALUES ('3', '1', '标题3', '内容3');
INSERT INTO `note` VALUES ('5', '3', '标题11', '内容11');
INSERT INTO `note` VALUES ('6', '3', 'bia22', 'cc22');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'doris', '123');
INSERT INTO `user` VALUES ('3', 'kitty', 'hello');
INSERT INTO `user` VALUES ('4', 'kitty', 'hello');
