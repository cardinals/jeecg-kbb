/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : mst_report

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-07-23 13:58:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cgform_field
-- ----------------------------
DROP TABLE IF EXISTS `cgform_field`;
CREATE TABLE `cgform_field` (
  `id` varchar(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(32) DEFAULT NULL,
  `dict_field` varchar(100) DEFAULT NULL,
  `dict_table` varchar(100) DEFAULT NULL,
  `dict_text` varchar(100) DEFAULT NULL,
  `field_default` varchar(20) DEFAULT NULL,
  `field_href` varchar(200) DEFAULT NULL,
  `field_length` int(11) DEFAULT NULL,
  `field_name` varchar(32) NOT NULL,
  `field_valid_type` varchar(100) DEFAULT NULL,
  `field_must_input` varchar(2) DEFAULT NULL,
  `is_key` varchar(2) DEFAULT NULL,
  `is_null` varchar(5) DEFAULT NULL,
  `is_query` varchar(5) DEFAULT NULL,
  `is_show` varchar(5) DEFAULT NULL,
  `is_show_list` varchar(5) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `main_field` varchar(100) DEFAULT NULL,
  `main_table` varchar(100) DEFAULT NULL,
  `old_field_name` varchar(32) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `point_length` int(11) DEFAULT NULL,
  `query_mode` varchar(10) DEFAULT NULL,
  `show_type` varchar(10) DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_name` varchar(32) DEFAULT NULL,
  `table_id` varchar(32) NOT NULL,
  `extend_json` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_field
-- ----------------------------
INSERT INTO `cgform_field` VALUES ('2c9437f45d5d7da8015d5d9d6f33000a', '主键', 'admin', '2017-07-20 09:29:45', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('2c9437f45d5d7da8015d5d9d6f34000b', '字段名', 'admin', '2017-07-20 09:29:45', '管理员', '', '', '', '', '', '120', 'ffeildname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'ffeildname', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-20 09:31:00', '管理员', '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('2c9437f45d5d7da8015d5d9d6f34000c', '描述', 'admin', '2017-07-20 09:29:45', '管理员', '', '', '', '', '', '120', 'fcaption', '', null, 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'fcaption', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-20 09:31:00', '管理员', '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('2c9437f45d5d7da8015d5d9d6f34000d', '备注', 'admin', '2017-07-20 09:29:45', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '4', '0', 'single', 'textarea', 'string', 'admin', '2017-07-20 09:31:00', '管理员', '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('2c9437f45d5e206e015d5e26c5950003', '校验规则', 'admin', '2017-07-20 11:59:46', '管理员', 'dataType', '', '', '', '', '120', 'fdatatype', '', null, 'N', 'Y', 'N', 'Y', 'Y', '32', '', '', 'fdatatype', '5', '0', 'single', 'list', 'string', null, null, null, '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292e8001c', '主键', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292e9001d', '项目代号', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_code', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'project_code', '2', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292eb001e', '英文名称', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_enname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'project_enname', '3', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292ec001f', '中文名称', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'project_name', '4', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292ed0020', '联系人', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_linkman', '', null, 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'project_linkman', '5', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292ee0021', '联系人电话', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_link_tel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'project_link_tel', '6', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f00022', '项目地址', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_address', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'project_address', '7', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f10023', '客户ID', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'customer_id', '', null, 'N', 'Y', 'N', 'Y', 'Y', '36', '', '', 'customer_id', '8', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f20024', '项目开始时间', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_starttime', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'project_starttime', '9', '0', 'single', 'date', 'Date', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f30025', '项目结束时间', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'project_endtime', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'project_endtime', '10', '0', 'single', 'date', 'Date', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f60026', '项目负责人', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'manager_name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '32', '', '', 'manager_name', '11', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f70027', '负责人电话', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '120', 'manager_tel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '32', '', '', 'manager_tel', '12', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c0292f90028', '备注', 'admin', '2017-07-13 20:53:08', '管理员', '', '', '', '', '', '240', 'remark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '500', '', '', 'remark', '13', '0', 'single', 'textarea', 'string', null, null, null, '402880835d3bf64f015d3c0292e6001b', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc160035', '主键', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc180036', '代号', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'code', '', null, 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'code', '2', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc190037', '中文名称', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'name', '3', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1a0038', '英文名称', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'en_name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'en_name', '4', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1a0039', '电话', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'tel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'tel', '5', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1b003a', '地址', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'address', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'address', '6', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1c003b', '负责人名称', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'master_name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'master_name', '7', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1d003c', '负责人电话', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'master_tel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'master_tel', '8', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1e003d', '规模', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'scale', '', null, 'N', 'Y', 'N', 'Y', 'Y', '10', '', '', 'scale', '9', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc1f003e', '财务名称', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'finance_name', '', null, 'N', 'Y', 'N', 'Y', 'Y', '255', '', '', 'finance_name', '10', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc20003f', '财务电话', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'finance_tel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'finance_tel', '11', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402880835d3bf64f015d3c07dc210040', '备注', 'admin', '2017-07-13 20:58:54', '管理员', '', '', '', '', '', '120', 'remark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '500', '', '', 'remark', '12', '0', 'single', 'text', 'string', null, null, null, '402880835d3bf64f015d3c07dc150034', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b66f9b80057', '主键', 'admin', '2017-07-19 23:11:02', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b66f9b60056', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b66f9b80058', '代码', 'admin', '2017-07-19 23:11:02', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b66f9b60056', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b66f9b80059', '名称', 'admin', '2017-07-19 23:11:02', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b66f9b60056', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b66f9b9005a', '备注', 'admin', '2017-07-19 23:11:02', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '4', '0', 'single', 'textarea', 'string', null, null, null, '402881875d5b3341015d5b66f9b60056', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b6b0cd50063', '主键', 'admin', '2017-07-19 23:15:29', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b6b0cd60064', '代码', 'admin', '2017-07-19 23:15:29', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b6b0cd70065', '名称', 'admin', '2017-07-19 23:15:29', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b6b0cd70066', '备注', 'admin', '2017-07-19 23:15:29', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '4', '0', 'single', 'textarea', 'string', null, null, null, '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b6b0cd80067', '价格', 'admin', '2017-07-19 23:15:29', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', 'fprice', '5', '0', 'single', 'text', 'double', 'admin', '2017-07-20 00:31:28', '管理员', '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b71ee9c0070', '外键', 'admin', '2017-07-19 23:23:00', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'N', 'N', 'N', 'N', '32', 'id', 't_base_standard', 'foreignid', '6', '0', 'single', 'text', 'string', null, null, null, '402881875d5b3341015d5b6b0cd50062', '');
INSERT INTO `cgform_field` VALUES ('402881875d5b3341015d5b767f160073', '价格', 'admin', '2017-07-19 23:27:59', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', 'fprice', '5', '0', 'single', 'text', 'double', 'admin', '2017-07-20 00:31:35', '管理员', '402881875d5b3341015d5b66f9b60056', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb532ce000e', '主键', 'admin', '2017-07-20 00:36:28', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bb532ce000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb532cf000f', '代码', 'admin', '2017-07-20 00:36:28', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-20 00:40:09', '管理员', '402881875d5bae3a015d5bb532ce000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb532cf0010', '名称', 'admin', '2017-07-20 00:36:28', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-20 00:40:09', '管理员', '402881875d5bae3a015d5bb532ce000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb532cf0011', '备注', 'admin', '2017-07-20 00:36:28', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '4', '0', 'single', 'textarea', 'string', 'admin', '2017-07-20 00:40:09', '管理员', '402881875d5bae3a015d5bb532ce000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb532d20012', '价格', 'admin', '2017-07-20 00:36:28', '管理员', '', '', '', '', '', '120', 'fprice', 'n', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fprice', '5', '0', 'single', 'text', 'double', 'admin', '2017-07-20 00:40:09', '管理员', '402881875d5bae3a015d5bb532ce000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734c001a', '主键', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '20', '', '', 'id', '1', '0', 'single', 'text', 'int', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734c001b', '代码', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734d001c', '名称', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734d001d', '价格', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fprice', '4', '0', 'single', 'text', 'double', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734e001e', '备注', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '5', '0', 'single', 'textarea', 'string', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bb7734e001f', '外键', 'admin', '2017-07-20 00:38:56', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'N', 'N', 'N', 'N', '32', 'id', 't_base_options', 'foreignid', '6', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bb7734c0019', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bbca220002e', '主键', 'admin', '2017-07-20 00:44:35', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bbca220002d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bbca220002f', '代码', 'admin', '2017-07-20 00:44:35', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bbca220002d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bbca2210030', '名称', 'admin', '2017-07-20 00:44:35', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bbca220002d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bbca2210031', '系数', 'admin', '2017-07-20 00:44:35', '管理员', '', '', '', '1', '', '120', 'fratio', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', 'fratio', '4', '0', 'single', 'text', 'double', null, null, null, '402881875d5bae3a015d5bbca220002d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bbca2210032', '备注', 'admin', '2017-07-20 00:44:35', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '5', '0', 'single', 'textarea', 'string', null, null, null, '402881875d5bae3a015d5bbca220002d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bcccc7c003f', '主键', 'admin', '2017-07-20 01:02:15', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bcccc7c003e', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bcccc7d0041', '代码', 'admin', '2017-07-20 01:02:15', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-20 01:07:58', '管理员', '402881875d5bae3a015d5bcccc7c003e', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bcccc7d0042', '名称', 'admin', '2017-07-20 01:02:15', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-20 01:07:58', '管理员', '402881875d5bae3a015d5bcccc7c003e', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bcccc7e0047', '类型', 'admin', '2017-07-20 01:02:15', '管理员', 'doorType', '', '', '', '', '120', 'fdoortype', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fdoortype', '4', '0', 'single', 'list', 'string', 'admin', '2017-07-20 01:10:42', '管理员', '402881875d5bae3a015d5bcccc7c003e', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eae004e', '主键', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eae004f', '代码', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eaf0050', '名称', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'fname', '', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eaf0051', '规格型号', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'fmodel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'fmodel', '4', '0', 'single', 'text', 'string', 'admin', '2017-07-21 23:50:45', '管理员', '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eaf0052', '备注', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fremark', '5', '0', 'single', 'textarea', 'string', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eaf0053', '价格', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fprice', '6', '0', 'single', 'text', 'double', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d5bae3a015d5bd14eb00054', '外键', 'admin', '2017-07-20 01:07:10', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'N', 'N', 'N', 'N', '32', 'id', 't_doors', 'foreignid', '7', '0', 'single', 'text', 'string', null, null, null, '402881875d5bae3a015d5bd14eac004d', '');
INSERT INTO `cgform_field` VALUES ('402881875d606ca3015d6073eb160004', '是否同步数据库', 'admin', '2017-07-20 22:43:16', '管理员', '', '', '', '', '', '120', 'fisdbsynch', '', null, 'N', 'Y', 'N', 'N', 'Y', '32', '', '', 'fisdbsynch', '6', '0', 'single', 'text', 'string', 'admin', '2017-07-20 22:43:47', '管理员', '2c9437f45d5d7da8015d5d9d6f320009', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d660d0005', '主键', 'admin', '2017-07-21 20:09:23', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d660e0006', '代码', 'admin', '2017-07-21 20:09:23', '管理员', 'id', 't_base_standard', 'fnumber', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d660f0007', '名称', 'admin', '2017-07-21 20:09:23', '管理员', 'id', 't_base_standard', 'fname', '', '', '120', 'fname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d660f0008', '型号', 'admin', '2017-07-21 20:09:23', '管理员', 'id', 't_base_standard_model', 'fname', '', '', '120', 'fmodel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fmodel', '4', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d66100009', '数量', 'admin', '2017-07-21 20:09:23', '管理员', '', '', '', '0', '', '120', 'fqty', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fqty', '5', '0', 'single', 'text', 'double', 'admin', '2017-07-21 20:15:08', '管理员', '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d6611000a', '价格', 'admin', '2017-07-21 20:09:23', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fprice', '6', '0', 'single', 'text', 'double', 'admin', '2017-07-21 20:15:08', '管理员', '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d6612000b', '备注', 'admin', '2017-07-21 20:09:23', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '8', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:15:08', '管理员', '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d650d6613000c', '外键', 'admin', '2017-07-21 20:09:23', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'Y', 'N', 'N', 'N', '36', 'id', 't_doors', 'foreignid', '9', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:22:33', '管理员', '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2280012', '主键', 'admin', '2017-07-21 20:14:05', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2280013', '代码', 'admin', '2017-07-21 20:14:05', '管理员', 'id', 't_base_options', 'fnumber', '', '', '120', 'fnumber', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2280014', '名称', 'admin', '2017-07-21 20:14:05', '管理员', 'id', 't_base_options', 'fname', '', '', '120', 'fname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fname', '3', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2290015', '型号', 'admin', '2017-07-21 20:14:05', '管理员', 'id', 't_base_options_model', 'fname', '', '', '120', 'fmodel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fmodel', '4', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2290016', '数量', 'admin', '2017-07-21 20:14:05', '管理员', '', '', '', '0', '', '120', 'fqty', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fqty', '5', '0', 'single', 'text', 'double', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2290017', '价格', 'admin', '2017-07-21 20:14:05', '管理员', '', '', '', '0', '', '120', 'fprice', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fprice', '6', '0', 'single', 'text', 'double', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b2290018', '金额', 'admin', '2017-07-21 20:14:05', '管理员', '', '', '', '0', '', '120', 'famount', '', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', 'famount', '7', '0', 'single', 'text', 'double', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6511b22a0019', '外键', 'admin', '2017-07-21 20:14:05', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'N', 'N', 'N', 'N', '36', 'id', 't_doors', 'foreignid', '9', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:22:26', '管理员', '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d6512a7da001b', '金额', 'admin', '2017-07-21 20:15:08', '管理员', '', '', '', '0', '', '120', 'famount', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', 'famount', '7', '0', 'single', 'text', 'double', null, null, null, '402881875d6501dc015d650d66090004', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7d0022', '主键', 'admin', '2017-07-21 20:18:59', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7e0023', '代码', 'admin', '2017-07-21 20:18:59', '管理员', 'id', 't_base_surface', 'fnumber', '', '', '120', 'fnumber', '', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:19:25', '管理员', '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7e0024', '名称', 'admin', '2017-07-21 20:18:59', '管理员', 'id', 't_base_surface', 'fname', '', '', '120', 'fname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fname', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:19:25', '管理员', '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7e0025', '系数', 'admin', '2017-07-21 20:18:59', '管理员', '', '', '', '1', '', '120', 'fratio', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '20', '', '', 'fratio', '4', '0', 'single', 'text', 'double', 'admin', '2017-07-21 20:19:25', '管理员', '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7e0026', '备注', 'admin', '2017-07-21 20:18:59', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '5', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:19:25', '管理员', '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65162d7e0027', '外键', 'admin', '2017-07-21 20:18:59', '管理员', '', '', '', '', '', '120', 'foreignid', '', null, 'N', 'N', 'N', 'N', 'Y', '36', 'id', 't_doors', 'foreignid', '6', '0', 'single', 'text', 'string', 'admin', '2017-07-21 20:22:19', '管理员', '402881875d6501dc015d65162d7d0021', '');
INSERT INTO `cgform_field` VALUES ('402881875d6501dc015d65178a68002a', '备注', 'admin', '2017-07-21 20:20:28', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '8', '0', 'single', 'text', 'string', null, null, null, '402881875d6501dc015d6511b2270011', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a840002', '主键', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a850003', '备注', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '10', '0', 'single', 'text', 'string', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a850004', '单号', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fbillno', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fbillno', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a860005', '客户', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fcustid', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fcustid', '4', '0', 'single', 'text', 'string', null, null, null, '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a860006', '金额', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '0', '', '120', 'famount', 'n', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', 'famount', '5', '0', 'single', 'text', 'double', null, null, null, '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a870007', '状态', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fstatus', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fstatus', '6', '0', 'single', 'text', 'string', null, null, null, '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a8a0008', '申请人', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fapplicant', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fapplicant', '8', '0', 'single', 'text', 'string', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a8b0009', '申请时间', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fapplicant_date', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fapplicant_date', '9', '0', 'single', 'text', 'Date', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a8b000a', '项目', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fprojectid', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fprojectid', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d695d3a8c000b', '当前审批人', 'admin', '2017-07-22 16:15:04', '管理员', '', '', '', '', '', '120', 'fcurrent_approver', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fcurrent_approver', '7', '0', 'single', 'text', 'string', 'admin', '2017-07-22 16:25:32', '管理员', '402881875d694cf9015d695d3a830001', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dbe000e', '主键', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '120', 'id', '', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dbf000f', '代码', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'fnumber', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fnumber', '2', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dbf0010', '名称', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'fname', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fname', '3', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc00011', '门型型号', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'fmodel', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fmodel', '4', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc00012', '表面处理', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'fsurface', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fsurface', '7', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc10013', '系数', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '1', '', '100', 'fratio', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fratio', '8', '0', 'single', 'text', 'double', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc60014', '价格', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '0', '', '100', 'fprice', '', null, 'N', 'Y', 'N', 'Y', 'Y', '20', '', '', 'fprice', '9', '0', 'single', 'text', 'double', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc60015', '金额', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '0', '', '100', 'famount', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'famount', '10', '0', 'single', 'text', 'double', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc60016', '备注', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'fremark', '', null, 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'fremark', '11', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69641dc60017', '外键', 'admin', '2017-07-22 16:22:35', '管理员', '', '', '', '', '', '100', 'foreignid', '', null, 'N', 'Y', 'N', 'N', 'N', '36', 'id', 't_offers', 'foreignid', '12', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69afc69f001f', '标准配件', 'admin', '2017-07-22 17:45:14', '管理员', '', '', '', '', '', '100', 'fstandard', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'fstandard', '5', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');
INSERT INTO `cgform_field` VALUES ('402881875d694cf9015d69afc69f0020', '可选配件', 'admin', '2017-07-22 17:45:14', '管理员', '', '', '', '', '', '100', 'foptions', '', null, 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'foptions', '6', '0', 'single', 'text', 'string', 'admin', '2017-07-23 00:06:06', '管理员', '402881875d694cf9015d69641dbd000d', '');

-- ----------------------------
-- Table structure for cgform_head
-- ----------------------------
DROP TABLE IF EXISTS `cgform_head`;
CREATE TABLE `cgform_head` (
  `id` varchar(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(32) DEFAULT NULL,
  `is_checkbox` varchar(5) NOT NULL,
  `is_dbsynch` varchar(20) NOT NULL,
  `is_pagination` varchar(5) NOT NULL,
  `is_tree` varchar(5) NOT NULL,
  `jform_pk_sequence` varchar(200) DEFAULT NULL,
  `jform_pk_type` varchar(100) DEFAULT NULL,
  `jform_type` int(11) NOT NULL,
  `jform_version` varchar(10) NOT NULL,
  `querymode` varchar(10) NOT NULL,
  `relation_type` int(11) DEFAULT NULL,
  `sub_table_str` varchar(1000) DEFAULT NULL,
  `tab_order` int(11) DEFAULT NULL,
  `table_name` varchar(50) NOT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_name` varchar(32) DEFAULT NULL,
  `tree_parentid_fieldname` varchar(50) DEFAULT NULL,
  `tree_id_fieldname` varchar(50) DEFAULT NULL,
  `tree_fieldname` varchar(50) DEFAULT NULL,
  `jform_category` varchar(50) NOT NULL DEFAULT 'bdfl_ptbd',
  `form_template` varchar(50) DEFAULT NULL COMMENT '表单模板',
  `form_template_mobile` varchar(50) DEFAULT NULL COMMENT '表单模板样式(移动端)',
  `table_type` varchar(50) DEFAULT NULL,
  `table_version` int(255) DEFAULT NULL,
  `physice_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_head
-- ----------------------------
INSERT INTO `cgform_head` VALUES ('2c9437f45d5d7da8015d5d9d6f320009', '一般参数', 'admin', '2017-07-20 09:29:45', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '1', '8', 'single', '0', null, null, 't_base_params', 'admin', '2017-07-20 22:43:47', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402880835d3bf64f015d3c0292e6001b', '项目信息', 'admin', '2017-07-13 20:53:08', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '1', '1', 'group', '0', null, null, 't_base_project', 'admin', '2017-07-13 20:53:21', '管理员', null, 'id', null, 'bdfl_ptbd', 'ledefault', 'ledefault', null, null, null);
INSERT INTO `cgform_head` VALUES ('402880835d3bf64f015d3c07dc150034', '客户信息', 'admin', '2017-07-13 20:58:54', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '1', '1', 'single', '0', null, null, 't_base_customer', 'admin', '2017-07-13 20:58:59', '管理员', null, 'id', null, 'bdfl_ptbd', 'ledefault', 'ledefault', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5b3341015d5b66f9b60056', '标准配件', 'admin', '2017-07-19 23:11:02', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '2', '12', 'single', '0', 't_base_standard_model', null, 't_base_standard', 'admin', '2017-07-20 00:31:35', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5b3341015d5b6b0cd50062', '标准配件规格型号', 'admin', '2017-07-19 23:15:29', '管理员', 'N', 'Y', 'Y', 'N', '', 'NATIVE', '3', '6', 'single', '0', null, null, 't_base_standard_model', 'admin', '2017-07-20 00:31:28', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5bae3a015d5bb532ce000d', '可选配件', 'admin', '2017-07-20 00:36:28', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '2', '3', 'single', '0', 't_base_options_model', null, 't_base_options', 'admin', '2017-07-20 00:40:09', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5bae3a015d5bb7734c0019', '可选配件规格型号', 'admin', '2017-07-20 00:38:56', '管理员', 'N', 'Y', 'Y', 'N', '', 'NATIVE', '3', '1', 'single', '0', null, null, 't_base_options_model', 'admin', '2017-07-20 00:38:59', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5bae3a015d5bbca220002d', '表面处理', 'admin', '2017-07-20 00:44:35', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '1', '1', 'single', '0', null, null, 't_base_surface', 'admin', '2017-07-20 00:44:38', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5bae3a015d5bcccc7c003e', '门型维护', 'admin', '2017-07-20 01:02:15', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '2', '23', 'single', '0', 't_doors_model,t_door_standard,t_door_surface,t_door_options', null, 't_doors', 'admin', '2017-07-21 23:50:48', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d5bae3a015d5bd14eac004d', '门型型号', 'admin', '2017-07-20 01:07:10', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '3', '3', 'single', '0', null, '1', 't_doors_model', 'admin', '2017-07-21 23:50:47', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d6501dc015d650d66090004', '标准配件', 'admin', '2017-07-21 20:09:23', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '3', '5', 'single', '0', null, '2', 't_door_standard', 'admin', '2017-07-21 20:23:25', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d6501dc015d6511b2270011', '可选配件', 'admin', '2017-07-21 20:14:05', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '3', '5', 'single', '0', null, '3', 't_door_options', 'admin', '2017-07-21 20:23:20', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d6501dc015d65162d7d0021', '表面处理', 'admin', '2017-07-21 20:18:59', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '3', '5', 'single', '0', null, '3', 't_door_surface', 'admin', '2017-07-21 20:23:14', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d694cf9015d695d3a830001', '报价单', 'admin', '2017-07-22 16:15:04', '管理员', 'N', 'Y', 'Y', 'N', '', 'UUID', '2', '6', 'single', '0', 't_offers_entry', null, 't_offers', 'admin', '2017-07-23 00:06:07', '管理员', null, 'id', null, 'bdfl_ptbd', 'ledefault2', '', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881875d694cf9015d69641dbd000d', '门型', 'admin', '2017-07-22 16:22:35', '管理员', 'Y', 'Y', 'Y', 'N', '', 'UUID', '3', '3', 'single', '0', null, null, 't_offers_entry', 'admin', '2017-07-23 00:06:06', '管理员', null, 'id', null, 'bdfl_ptbd', '', '', null, null, null);
