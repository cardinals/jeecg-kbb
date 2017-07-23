/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : mst_report

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-07-23 13:56:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_base_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_base_customer`;
CREATE TABLE `t_base_customer` (
  `id` varchar(36) NOT NULL,
  `code` varchar(100) DEFAULT NULL COMMENT '代号',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `en_name` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `master_name` varchar(255) DEFAULT NULL COMMENT '负责人名称',
  `master_tel` varchar(20) DEFAULT NULL COMMENT '负责人电话',
  `scale` varchar(10) DEFAULT NULL COMMENT '规模',
  `finance_name` varchar(255) DEFAULT NULL COMMENT '财务名称',
  `finance_tel` varchar(20) DEFAULT NULL COMMENT '财务电话',
  `remark` longtext COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_customer
-- ----------------------------

-- ----------------------------
-- Table structure for t_base_options
-- ----------------------------
DROP TABLE IF EXISTS `t_base_options`;
CREATE TABLE `t_base_options` (
  `id` varchar(36) NOT NULL,
  `fnumber` varchar(50) DEFAULT NULL COMMENT '代码',
  `fname` varchar(100) DEFAULT NULL COMMENT '名称',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `fprice` double DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_options
-- ----------------------------
INSERT INTO `t_base_options` VALUES ('402881875d5bb1b4015d5bb99a8e0001', '01', '普通不锈钢天花', null, '10000');

-- ----------------------------
-- Table structure for t_base_options_model
-- ----------------------------
DROP TABLE IF EXISTS `t_base_options_model`;
CREATE TABLE `t_base_options_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fprice` double NOT NULL DEFAULT '0' COMMENT '价格',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `foreignid` varchar(32) NOT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_options_model
-- ----------------------------
INSERT INTO `t_base_options_model` VALUES ('1', '<4200', '直径小于4200', '7000', null, '402881875d5bb1b4015d5bb99a8e0001');

-- ----------------------------
-- Table structure for t_base_params
-- ----------------------------
DROP TABLE IF EXISTS `t_base_params`;
CREATE TABLE `t_base_params` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `ffeildname` varchar(50) DEFAULT NULL COMMENT '字段名',
  `fcaption` varchar(100) DEFAULT NULL COMMENT '描述',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `fdatatype` varchar(32) DEFAULT NULL COMMENT '校验规则',
  `fisdbsynch` varchar(32) DEFAULT NULL COMMENT '是否同步数据库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_params
-- ----------------------------
INSERT INTO `t_base_params` VALUES ('2c9437f45d5e2757015d5e2757420000', 'width', '洞口宽度', null, 'n', '已同步');
INSERT INTO `t_base_params` VALUES ('402881875d606e7f015d606e7f0a0000', 'totalheight', '总高', null, 'n', '已同步');

-- ----------------------------
-- Table structure for t_base_project
-- ----------------------------
DROP TABLE IF EXISTS `t_base_project`;
CREATE TABLE `t_base_project` (
  `id` varchar(36) NOT NULL,
  `project_code` varchar(50) DEFAULT NULL COMMENT '项目代号',
  `project_enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `project_name` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `project_linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `project_link_tel` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `project_address` varchar(255) DEFAULT NULL COMMENT '项目地址',
  `customer_id` varchar(36) DEFAULT NULL COMMENT '客户ID',
  `project_starttime` datetime DEFAULT NULL COMMENT '项目开始时间',
  `project_endtime` datetime DEFAULT NULL COMMENT '项目结束时间',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '项目负责人',
  `manager_tel` varchar(32) DEFAULT NULL COMMENT '负责人电话',
  `remark` longtext COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_project
-- ----------------------------

-- ----------------------------
-- Table structure for t_base_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_base_standard`;
CREATE TABLE `t_base_standard` (
  `id` varchar(36) NOT NULL,
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `fprice` double NOT NULL DEFAULT '0' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_standard
-- ----------------------------
INSERT INTO `t_base_standard` VALUES ('402881875d5bb1b4015d5bb1b4f50000', '01', '主控单元', null, '1000');

-- ----------------------------
-- Table structure for t_base_standard_model
-- ----------------------------
DROP TABLE IF EXISTS `t_base_standard_model`;
CREATE TABLE `t_base_standard_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `fprice` double NOT NULL DEFAULT '0' COMMENT '价格',
  `foreignid` varchar(32) NOT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_standard_model
-- ----------------------------

-- ----------------------------
-- Table structure for t_base_surface
-- ----------------------------
DROP TABLE IF EXISTS `t_base_surface`;
CREATE TABLE `t_base_surface` (
  `id` varchar(36) NOT NULL,
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fratio` double NOT NULL DEFAULT '1' COMMENT '系数',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_base_surface
-- ----------------------------

-- ----------------------------
-- Table structure for t_door_options
-- ----------------------------
DROP TABLE IF EXISTS `t_door_options`;
CREATE TABLE `t_door_options` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fnumber` varchar(50) DEFAULT NULL COMMENT '代码',
  `fname` varchar(50) DEFAULT NULL COMMENT '名称',
  `fmodel` varchar(50) DEFAULT NULL COMMENT '型号',
  `fqty` double(50,0) NOT NULL DEFAULT '0' COMMENT '数量',
  `fprice` double(50,0) NOT NULL DEFAULT '0' COMMENT '价格',
  `famount` double(32,0) NOT NULL DEFAULT '0' COMMENT '金额',
  `foreignid` varchar(36) NOT NULL COMMENT '外键',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_door_options
-- ----------------------------

-- ----------------------------
-- Table structure for t_door_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_door_standard`;
CREATE TABLE `t_door_standard` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(50) DEFAULT NULL COMMENT '名称',
  `fmodel` varchar(50) DEFAULT NULL COMMENT '型号',
  `fqty` double(50,0) NOT NULL DEFAULT '0' COMMENT '数量',
  `fprice` double(50,0) NOT NULL DEFAULT '0' COMMENT '价格',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `foreignid` varchar(36) DEFAULT NULL COMMENT '外键',
  `famount` double(32,0) NOT NULL DEFAULT '0' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_door_standard
-- ----------------------------

-- ----------------------------
-- Table structure for t_door_surface
-- ----------------------------
DROP TABLE IF EXISTS `t_door_surface`;
CREATE TABLE `t_door_surface` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(50) DEFAULT NULL COMMENT '名称',
  `fratio` double(20,0) NOT NULL DEFAULT '1' COMMENT '系数',
  `fremark` varchar(200) DEFAULT NULL COMMENT '备注',
  `foreignid` varchar(36) NOT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_door_surface
-- ----------------------------

-- ----------------------------
-- Table structure for t_doors
-- ----------------------------
DROP TABLE IF EXISTS `t_doors`;
CREATE TABLE `t_doors` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fdoortype` varchar(50) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_doors
-- ----------------------------
INSERT INTO `t_doors` VALUES ('402881875d65b091015d65b10a8f0001', 'sf', 'sfs', 'XZM');

-- ----------------------------
-- Table structure for t_doors_model
-- ----------------------------
DROP TABLE IF EXISTS `t_doors_model`;
CREATE TABLE `t_doors_model` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fnumber` varchar(50) NOT NULL COMMENT '代码',
  `fname` varchar(100) NOT NULL COMMENT '名称',
  `fmodel` varchar(100) DEFAULT NULL COMMENT '规格型号',
  `fremark` varchar(50) DEFAULT NULL COMMENT '备注',
  `fprice` double(50,0) DEFAULT '0' COMMENT '价格',
  `foreignid` varchar(32) NOT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_doors_model
-- ----------------------------
INSERT INTO `t_doors_model` VALUES ('402881875d65da85015d65dcaa110003', 'w3', '23', '3', '', '2', '402881875d65b091015d65b10a8f0001');

-- ----------------------------
-- Table structure for t_doors_model_ex
-- ----------------------------
DROP TABLE IF EXISTS `t_doors_model_ex`;
CREATE TABLE `t_doors_model_ex` (
  `id` varchar(36) NOT NULL,
  `totalheight` varchar(50) DEFAULT NULL,
  `width` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_doors_model_ex_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_doors_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_doors_model_ex
-- ----------------------------
