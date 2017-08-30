#删除系数，在门型维护中去维护
alter table t_base_surface drop column fratio;

#表面处理使用行编辑
update t_s_function set functionurl='baseSurfaceController.do?list' where functionname='表面处理';



drop table t_base_standard_model;

#代码、名称、规格型号、品牌、价格、备注
alter table t_base_standard add column fmodel VARCHAR(100);
alter table t_base_standard add column fbrand VARCHAR(100);

#配件资料使用行编辑
update t_s_function set functionurl='baseStandardController.do?list' where functionname='配件资料';

#配件资料代码可空，做保留字段使用
alter table t_base_standard modify fnumber varchar(50) null;
#配件资料代码可空，做保留字段使用
alter table t_base_surface modify fnumber varchar(50) null;


#门型维护中标准配件和可选配件新增品牌
alter table t_door_options add COLUMN fbrand varchar(50) null;
alter table t_door_standard add COLUMN fbrand varchar(50) null;
