/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : jeecg_kbb

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-09-03 23:08:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cgform_enhance_java
-- ----------------------------
DROP TABLE IF EXISTS `cgform_enhance_java`;
CREATE TABLE `cgform_enhance_java` (
  `id` varchar(36) NOT NULL,
  `button_code` varchar(32) DEFAULT NULL COMMENT '按钮编码',
  `cg_java_type` varchar(32) NOT NULL COMMENT '类型',
  `cg_java_value` varchar(200) NOT NULL COMMENT '数值',
  `form_id` varchar(32) NOT NULL COMMENT '表单ID',
  `active_status` varchar(2) DEFAULT '1' COMMENT '生效状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_enhance_java
-- ----------------------------
INSERT INTO `cgform_enhance_java` VALUES ('402881835e483f99015e484030a70001', 'add', 'spring', 'paramsAddOrUpdate', '2c9437f45d5d7da8015d5d9d6f320009', '1');
INSERT INTO `cgform_enhance_java` VALUES ('402881835e483f99015e484051b70003', 'update', 'spring', 'paramsAddOrUpdate', '2c9437f45d5d7da8015d5d9d6f320009', '1');
INSERT INTO `cgform_enhance_java` VALUES ('402881875d98985a015d9898f5000001', 'synchtodb', 'spring', 'paramsSynchToDB', '2c9437f45d5d7da8015d5d9d6f320009', '1');
INSERT INTO `cgform_enhance_java` VALUES ('402881875d98a9eb015d98aa8bd90003', 'delete', 'spring', 'paramsDelFromDB', '2c9437f45d5d7da8015d5d9d6f320009', '1');
INSERT INTO `cgform_enhance_java` VALUES ('402883885139c8d7015139cb2de40002', 'add', 'spring', 'cgformJavaInterDemo', '8a8ab0b246dc81120146dc818484013c', '1');
INSERT INTO `cgform_enhance_java` VALUES ('4028ef815602f891015602fb7cac0001', 'add', 'class', 'org.jeecgframework.web.cgform.service.impl.enhance.CgformEnhanceJavaServiceImpl', '4028b881535b12bd01535b1ae3680001', '1');
