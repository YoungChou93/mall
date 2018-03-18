/*
Navicat MySQL Data Transfer

Source Server         : weather
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : neteasemall

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-18 17:32:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `gid` int(11) NOT NULL COMMENT '商品id',
  `uid` int(11) NOT NULL,
  `number` int(11) NOT NULL COMMENT '商品数量',
  `price` double(12,2) NOT NULL,
  `buytime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_bill_user` (`uid`),
  KEY `fk_bill_goods` (`gid`),
  CONSTRAINT `fk_bill_goods` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_bill_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '2', '1', '2', '22.00', '2018-03-18 16:35:12');
INSERT INTO `bill` VALUES ('2', '2', '1', '3', '22.00', '2018-03-18 16:35:19');
INSERT INTO `bill` VALUES ('3', '5', '1', '9', '222.00', '2018-03-11 14:16:18');
INSERT INTO `bill` VALUES ('4', '2', '1', '3', '22.00', '2018-03-11 14:16:18');
INSERT INTO `bill` VALUES ('5', '5', '1', '6', '222.00', '2018-03-11 14:52:45');
INSERT INTO `bill` VALUES ('6', '24', '1', '18', '16.16', '2018-03-18 16:33:08');
INSERT INTO `bill` VALUES ('7', '6', '1', '4', '222.00', '2018-03-18 16:57:33');
INSERT INTO `bill` VALUES ('8', '6', '1', '1', '111.00', '2018-03-18 17:30:15');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `title` varchar(80) NOT NULL COMMENT '标题',
  `imagepath` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `imageurl` varchar(200) DEFAULT NULL COMMENT '图片url',
  `abstracts` varchar(140) NOT NULL COMMENT '摘要',
  `content` text NOT NULL COMMENT '正文',
  `price` double(12,2) NOT NULL COMMENT '价格',
  `uid` int(11) NOT NULL DEFAULT '2' COMMENT '发布商品的卖家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', '商品1', null, 'http://yanxuan.nosdn.127.net/8adfcfc388ca864049685ae4a569f523.jpg?imageView&thumbnail=430x430&quality=95', '摘要', '正文', '22.00', '2');
INSERT INTO `goods` VALUES ('5', 'shang', 'seller/picture/20180219150017.jpg', '', 'sdfsf', 'sdfsf', '222.00', '2');
INSERT INTO `goods` VALUES ('6', '商品', 'seller/picture/20180219151457.jpg', '', '摘要', 'hahahahahhaha', '111.00', '2');
INSERT INTO `goods` VALUES ('15', '男式牛仔外套', null, 'http://yanxuan.nosdn.127.net/ce5d4fe3db1d413f83baacd699ea7f8e.png?imageView&quality=95&thumbnail=245x245', '测试', '测试', '199.00', '2');
INSERT INTO `goods` VALUES ('22', '柔风黄油曲奇 480克', null, 'http://yanxuan.nosdn.127.net/3ff6c1d3eaf637801f3d7fef3183d746.png?imageView&thumbnail=430x430&quality=95', '进口优质原料，不人工添加食品添加剂', '品名柔风黄油曲奇净含量480克保质期60天配料黄油风味：小麦粉、黄油、白砂糖、乳粉、鸡蛋、食用盐\r\n抹茶风味：小麦粉、黄油、白砂糖、乳粉、鸡蛋、抹茶粉、食用盐\r\n咖啡风味：小麦粉、黄油、白砂糖、乳粉、鸡蛋、咖啡粉、食用盐', '98.99', '2');
INSERT INTO `goods` VALUES ('23', '绿豆糕', null, 'http://yanxuan.nosdn.127.net/66b9f1638c0517d179262f14ed1345f9.png?imageView&thumbnail=430x430&quality=95', '细腻松软，入口绵柔', '品名绿豆糕净含量160克（8枚入）配料表原味：绿豆沙馅（脱皮绿豆、白砂糖、饮用水、精炼植物油、海藻糖、食用盐）\r\n玫瑰味：绿豆沙馅（脱皮绿豆、白砂糖、饮用水、精炼植物油、海藻糖、食用盐）、重瓣红玫瑰保质期60天贮存条件阴凉干燥处保存产地四川省乐山市温馨提示1、网易严选出售的食品，除明确质量问题外均不接受退换货\r\n2、本产品不添加防腐剂，开封后请尽快食用', '18.90', '2');
INSERT INTO `goods` VALUES ('24', '草莓白巧克力 65克', null, 'http://yanxuan.nosdn.127.net/b5228b1128f8aeb1a0d7163696302b90.png?imageView&thumbnail=430x430&quality=95', '奶香果味，清新风味', '品名草莓白巧克力净含量65克产地山东省青岛市保质期270天配料白巧克力（白砂糖、可可脂、全脂乳粉、磷脂、聚甘油蓖麻醇酸酯、食用香精香料），冻干草莓贮存条件请避免阳光直射，并请在28℃以下的阴凉处保存温馨提示1.本品除明确质量问题外不支持30天无忧退货', '16.16', '2');
INSERT INTO `goods` VALUES ('25', '手剥山核桃(铁锅水煮)', null, 'http://yanxuan.nosdn.127.net/58bcca3a6fe1d3930c322e87795ac0ca.jpg?imageView&thumbnail=430x430&quality=95', '壳薄易剥，肉仁饱满', '品名铁锅水煮山核桃（醇香味）配料山核桃、白砂糖、食用盐、味精、香辛料、食品添加剂（安赛蜜、糖精钠、甜蜜素）、食品用香精净含量128克产地浙江省杭州市保质期12个月（常温下）贮存条件放置阴凉通风干燥处致敏物质本品属于坚果及其果仁类制品温馨提示1、网易严选出售的食品，除明确质量问题外均不接受退换货\r\n2、夏秋高温季节，请放入冰箱冷藏。开罐后，请尽快食用；\r\n3、 适宜五周岁以上人群食用，儿童须在成人监护下食用，如有脱氧剂请勿食用。', '32.10', '2');
INSERT INTO `goods` VALUES ('26', '果蔬脆片大联盟', 'seller/picture/20180318162248.jpg', '', '果蔬达人、果蔬盛宴', '产地国家中国大陆保质期270天品名果蔬脆片大联盟产地福建省龙岩市贮存条件常温、阴凉、通风、干燥温馨提示1、网易严选出售的食品，除明确质量问题外均不接受退换货\r\n2、请密封避光置于干燥处，开封后请尽快食用，以免影响风味\r\n3、老人、小孩请在成人监护下进行食用净含量100克*7', '99.00', '2');
INSERT INTO `goods` VALUES ('28', '黄秋葵脆片', 'seller/picture/20180318164527.jpg', '', '少油低脂，清雅香甜', '品名黄秋葵脆片产地福建省龙岩市净含量100g保质期270天配料黄秋葵、麦芽糖，精炼植物油贮存条件常温、阴凉、通风、干燥温馨提示1、网易严选出售的食品，除明确质量问题外均不接受退换货\r\n2、请放置于阴凉、通风、干燥处\r\n3、拆封后请尽快食用，以免影受潮响产品风味\r\n4、包装出现漏气现象请勿食用', '16.00', '2');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shoppingcart_user` (`uid`),
  KEY `fk_shoppingcart_goods` (`gid`),
  CONSTRAINT `fk_shoppingcart_goods` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_shoppingcart_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
  `password` varchar(32) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '用户类型 0:卖家 1:买家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'buyer', '37254660e226ea65ce6f1efd54233424', '1');
INSERT INTO `user` VALUES ('2', 'seller', '981c57a5cfb0f868e064904b8745766f', '0');
