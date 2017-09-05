DROP PROCEDURE IF EXISTS p_AlterTableAddColumn;  
CREATE PROCEDURE p_AlterTableAddColumn(tableName nvarchar(50) ,colName nvarchar(50) ,type nvarchar(100) )
	BEGIN
			IF NOT EXISTS (SELECT 1 
							FROM INFORMATION_SCHEMA.COLUMNS 
							WHERE  TABLE_SCHEMA='jeecg_kbb'
							AND TABLE_NAME = tableName
							AND COLUMN_NAME =colName)
			 THEN 
					set @s=CONCAT('ALTER TABLE ',tableName,' ADD COLUMN ',colName,' ',type);
					PREPARE stmt1 FROM @s;
					EXECUTE stmt1 ;
			 END IF;
	END;

DROP PROCEDURE IF EXISTS p_AlterTableDropColumn;  
CREATE PROCEDURE p_AlterTableDropColumn(tableName nvarchar(50) ,colName nvarchar(50) )
	BEGIN
			IF EXISTS (SELECT 1 
							FROM INFORMATION_SCHEMA.COLUMNS 
							WHERE  TABLE_SCHEMA='jeecg_kbb'
							AND TABLE_NAME = tableName
							AND COLUMN_NAME =colName)
			 THEN 
					set @s=CONCAT('ALTER TABLE ',tableName,' DROP COLUMN ',colName);
					PREPARE stmt1 FROM @s;
					EXECUTE stmt1 ;
			 END IF;
	END;

DROP PROCEDURE IF EXISTS p_AlterTableModifyColumn;  
CREATE PROCEDURE p_AlterTableModifyColumn(tableName nvarchar(50) ,colName nvarchar(50),type nvarchar(100) )
	BEGIN
			IF EXISTS (SELECT 1 
							FROM INFORMATION_SCHEMA.COLUMNS 
							WHERE  TABLE_SCHEMA='jeecg_kbb'
							AND TABLE_NAME = tableName
							AND COLUMN_NAME =colName)
			 THEN 					
					set @s=CONCAT('ALTER TABLE ',tableName,' MODIFY ',colName,' ',type);
					PREPARE stmt1 FROM @s;
					EXECUTE stmt1 ;
			 ELSE
					set @s=CONCAT('ALTER TABLE ',tableName,' ADD COLUMN ',colName,' ',type);
					PREPARE stmt1 FROM @s;
					EXECUTE stmt1 ;
			 END IF;
	END;
#删除系数，在门型维护中去维护
CALL p_AlterTableDropColumn('t_base_surface','fratio');

DROP TABLE IF EXISTS  t_base_standard_model;

#代码、名称、规格型号、品牌、价格、备注
CALL p_AlterTableAddColumn('t_base_standard','fmodel','VARCHAR(100)');
CALL p_AlterTableAddColumn('t_base_standard','fbrand','VARCHAR(100)');

#配件资料代码可空，做保留字段使用
CALL p_AlterTableModifyColumn('t_base_standard','fnumber','varchar(50) null');
CALL p_AlterTableModifyColumn('t_base_surface','fnumber','varchar(50) null');
CALL p_AlterTableModifyColumn('t_door_standard','fnumber','varchar(50) null');
CALL p_AlterTableModifyColumn('t_door_options','fnumber','varchar(50) null');
CALL p_AlterTableModifyColumn('t_doors_model','fnumber','varchar(50) null');

#门型维护中标准配件和可选配件新增品牌
CALL p_AlterTableAddColumn('t_door_options','fbrand','VARCHAR(50)');
CALL p_AlterTableAddColumn('t_door_standard','fbrand','VARCHAR(50)');

#配件资料使用行编辑
update t_s_function set functionurl='baseStandardController.do?list' where functionname='配件资料';
#表面处理使用行编辑
update t_s_function set functionurl='baseSurfaceController.do?list' where functionname='表面处理';
#一般参数
#update t_s_function set functionurl='baseParamController.do?list' where functionname='门型参数';

#支持外包/外协
CALL p_AlterTableAddColumn('t_offers','fouterprice','VARCHAR(50)');
CALL p_AlterTableAddColumn('t_offers','fisoutsource','VARCHAR(10)');

#增加品牌、配件	
CALL p_AlterTableAddColumn('t_offer_options','brand','VARCHAR(100)');
CALL p_AlterTableAddColumn('t_offer_options','standard','VARCHAR(100)');








#新增已办任务
INSERT INTO `t_s_function` (`ID`, `functioniframe`, `functionlevel`, `functionname`, `functionorder`, `functionurl`, `parentfunctionid`, `iconid`, `desk_iconid`, `functiontype`, `function_icon_style`, `create_by`, `create_name`, `update_by`, `update_date`, `create_date`, `update_name`) 
VALUES ('402881835e4d9ded015e4da25d0b0003', NULL, '1', '已办任务', '2', 'activitiOffer.do?myDoneTaskList', '402881875d6e18bf015d6e2048440013', '8a8ab0b246dc81120146dc8180460000', '8a8ab0b246dc81120146dc8180dd001e', '0', '', NULL, NULL, 'admin', '2017-09-05 00:04:14', NULL, '管理员');
INSERT INTO `t_s_function` (`ID`, `functioniframe`, `functionlevel`, `functionname`, `functionorder`, `functionurl`, `parentfunctionid`, `iconid`, `desk_iconid`, `functiontype`, `function_icon_style`, `create_by`, `create_name`, `update_by`, `update_date`, `create_date`, `update_name`) 
VALUES ('402881875d6e18bf015d6e2085e90015', NULL, '1', '待办任务', '0', 'activitiOffer.do?myTaskList', '402881875d6e18bf015d6e2048440013', '8a8ab0b246dc81120146dc8180460000', '8a8ab0b246dc81120146dc8180dd001e', '0', '', NULL, NULL, 'admin', '2017-09-05 00:04:36', NULL, '管理员');




#项目权限
INSERT INTO `t_s_muti_lang` (`id`, `lang_key`, `lang_context`, `lang_code`, `create_date`, `create_by`, `create_name`, `update_date`, `update_by`, `update_name`) VALUES ('jglongjba487c37d001487c488a4d109', 'menu.offer', '报价单管理', 'zh-cn', '2017-09-05 23:41:31', '8a8ab0b246dc81120146dc8181950052', 'admin', '2017-09-05 23:41:41', NULL, NULL);
update t_s_function set functionname='menu.offer' where functionurl='wxOffer.do?list'

INSERT INTO `t_s_data_rule` (`id`, `rule_name`, `rule_column`, `rule_conditions`, `rule_value`, `create_date`, `create_by`, `create_name`, `update_date`, `update_by`, `update_name`, `functionId`) VALUES ('4028ef81574ae99701574aed26530005', '允许查看项目', 'enableviewproject', '=', '1', '2016-09-21 12:07:18', 'admin', '管理员', NULL, NULL, NULL, '402881875d752ad0015d752b97530001');





