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