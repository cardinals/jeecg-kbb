/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : jeecg_kbb

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-08-17 23:07:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_offer_attachement
-- ----------------------------
DROP TABLE IF EXISTS `t_offer_attachement`;
CREATE TABLE `t_offer_attachement` (
  `id` varchar(36) NOT NULL,
  `fileid` varchar(100) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_offer_attachement
-- ----------------------------
INSERT INTO `jeecg_kbb`.`t_config_billno` (`ftablename`, `frule`, `fnum`) VALUES ('t_base_standard', 'PJ0000', '1');


alter table t_offers add column fdiscountrate FLOAT;
alter table t_offers add column fafteramount FLOAT;

update t_s_muti_lang set lang_context='通知' where lang_key='notice.tip' and lang_code='zh-cn'


