<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tOffersList" checkbox="true" fitColumns="false" title="报价单" actionUrl="tOffersController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单号"  field="fbillno"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目"  field="fprojectid"    queryMode="single" dictionary="t_base_project,id,fname" popup="true" width="120"></t:dgCol>
   <t:dgCol title="客户"  field="fcustid"    queryMode="single" dictionary="t_base_customer,id,fname" popup="true" width="120"></t:dgCol>
   <t:dgCol title="金额"  field="famount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="fstatus"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前审批人"  field="fcurrentApprover"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请人"  field="fapplicant"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请时间"  field="fapplicantDate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="fremark"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tOffersController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tOffersController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tOffersController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tOffersController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tOffersController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/offer/tOffersList.js"></script>		
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tOffersController.do?upload', "tOffersList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tOffersController.do?exportXls","tOffersList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tOffersController.do?exportXlsByT","tOffersList");
}
 </script>