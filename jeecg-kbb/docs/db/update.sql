#1、新增配件类型字段；
call p_AlterTableAddColumn('t_base_standard','ftype','varchar(100)');
#2、修改字典组的Code长度；
call p_AlterTableModifyColumn('t_s_typegroup','typegroupcode','varchar(100)');
#3、insert cgform_field中的配件类型字段
INSERT INTO `cgform_field` (`id`, `content`, `create_by`, `create_date`, `create_name`, `dict_field`, `dict_table`, `dict_text`, `field_default`, `field_href`, `field_length`, `field_name`, `field_valid_type`, `field_must_input`, `is_key`, `is_null`, `is_query`, `is_show`, `is_show_list`, `length`, `main_field`, `main_table`, `old_field_name`, `order_num`, `point_length`, `query_mode`, `show_type`, `type`, `update_by`, `update_date`, `update_name`, `table_id`, `extend_json`) VALUES ('402881835e6c56a9015e6c5802db0002', '类型', 'admin', '2017-09-10 23:11:01', '管理员', 'standardtype', '', 'typename', '', '', '120', 'ftype', '', 'N', 'N', 'Y', 'N', 'Y', 'Y', '32', '', '', 'ftype', '2', '0', 'single', 'text', 'string', 'admin', '2017-09-11 00:01:52', '管理员', '402881875d5b3341015d5b66f9b60056', '');

#配件类型新增
INSERT INTO `t_s_typegroup` (`ID`, `typegroupcode`, `typegroupname`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e67548a160001', 'standardtype', '配件类型', '2017-09-09 23:49:07', '管理员');
delete from t_s_type where typegroupid='402881835e6753bc015e67548a160001';
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e67553e9d0003', 'sdtype1', '旋转门标准配件', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:49:53', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e67558adf0005', 'sdtype2', '旋转门可选配件', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:50:13', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e6755dec50007', 'sdtype3', '平滑门标准配件', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:50:34', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e675626620009', 'sdtype4', '平滑门可选配件', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:50:53', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e6756632c000b', 'sdtype5', '边门门体及门区框架部分', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:51:08', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e67569a02000d', 'sdtype6', '边门选项', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:51:22', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e6756cc0a000f', 'sdtype7', '其他费用', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:51:35', '管理员');
INSERT INTO `t_s_type` (`ID`, `typecode`, `typename`, `typepid`, `typegroupid`, `create_date`, `create_name`) 
VALUES ('402881835e6753bc015e6756ffaf0011', 'sdtype8', '其他', NULL, '402881835e6753bc015e67548a160001', '2017-09-09 23:51:48', '管理员');





#
call p_AlterTableAddColumn('t_offer_options','fname','varchar(255) default null');
call p_AlterTableAddColumn('t_offer_options','unit','varchar(50) default null');


call p_AlterTableAddColumn('t_offers','fprojectname','varchar(255) default null');