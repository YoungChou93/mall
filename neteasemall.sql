/*
Navicat MySQL Data Transfer

Source Server         : point
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : neteasemall

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-02-23 22:52:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL COMMENT '账单id',
  `gid` int(11) NOT NULL COMMENT '商品id',
  `uid` int(11) NOT NULL,
  `number` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`),
  KEY `fk_bill_user` (`uid`),
  KEY `fk_bill_goods` (`gid`),
  CONSTRAINT `fk_bill_goods` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_bill_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '2', '1', '2');
INSERT INTO `bill` VALUES ('2', '2', '1', '3');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `title` varchar(280) NOT NULL COMMENT '标题',
  `imagepath` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `imageurl` varchar(200) DEFAULT NULL COMMENT '图片url',
  `abstracts` text NOT NULL COMMENT '摘要',
  `content` longtext NOT NULL COMMENT '正文',
  `price` double(10,0) NOT NULL COMMENT '价格',
  `uid` int(11) NOT NULL DEFAULT '2' COMMENT '发布商品的卖家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', '商品1', null, 'http://pic41.huitu.com/res/20151020/92967_20151020100448391200_1.jpg', '摘要', '正文', '22', '2');
INSERT INTO `goods` VALUES ('3', '商品2', null, 'http://pic41.huitu.com/res/20151020/92967_20151020100448391200_1.jpg', '摘要2', '正文2', '3', '2');
INSERT INTO `goods` VALUES ('4', '商品2', null, 'http://pic41.huitu.com/res/20151020/92967_20151020100448391200_1.jpg', '摘要', '正文', '223', '2');
INSERT INTO `goods` VALUES ('5', 'shang', 'seller/picture/20180219150017.jpg', '', 'sdfsf', 'sdfsf', '222', '2');
INSERT INTO `goods` VALUES ('6', '商品', 'seller/picture/20180219151457.jpg', '', '摘要', '正文', '222', '2');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shoppingcart_user` (`uid`),
  KEY `fk_shoppingcart_goods` (`gid`),
  CONSTRAINT `fk_shoppingcart_goods` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_shoppingcart_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '用户类型 0:卖家 1:买家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'buyer', 'reyub', '1');
INSERT INTO `user` VALUES ('2', 'seller', 'relles', '0');
