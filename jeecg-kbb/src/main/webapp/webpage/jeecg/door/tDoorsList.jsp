<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tDoorsList" checkbox="true" fitColumns="false" title="门型维护" actionUrl="tDoorsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="代码"  field="fnumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="fname"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="fdoortype"    queryMode="single" dictionary="doorType" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tDoorsController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tDoorsController.do?goAdd" funname="add" width="900" height="600"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tDoorsController.do?goUpdate" funname="update" width="900" height="600"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tDoorsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tDoorsController.do?goUpdate" funname="detail" width="900" height="600"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/jeecg/door/tDoorsList.js"></script>		
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tDoorsController.do?upload', "tDoorsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tDoorsController.do?exportXls","tDoorsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tDoorsController.do?exportXlsByT","tDoorsList");
}
 </script>