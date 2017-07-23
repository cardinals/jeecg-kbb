<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报价单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="tOffersController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${tOffersPage.id }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">单号:</label>
			</td>
			<td class="value">
		     	 <input id="fbillno" name="fbillno" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fbillno}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">项目:</label>
			</td>
			<td class="value">
			<input id="fprojectid" name="fprojectid" type="text" style="width: 150px" class="searchbox-inputtext"
			ignore="ignore"
			 onclick="inputClick(this,'id','t_base_project')" value='${tOffersPage.fprojectid}'>			    
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">项目</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">客户:</label>
			</td>
			<td class="value">
			<input id="fcustid" name="fcustid" type="text" style="width: 150px" class="searchbox-inputtext"
			ignore="ignore"
			 onclick="inputClick(this,'id','t_base_customer')" value='${tOffersPage.fcustid}'>			    
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户</label>
			</td>
			<td align="right">
				<label class="Validform_label">金额:</label>
			</td>
			<td class="value">
		     	 <input id="famount" name="famount" type="text" style="width: 150px" class="inputxt"  datatype="n" 
				ignore="checked"
		     	 value='${tOffersPage.famount}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
		     	 <input id="fstatus" name="fstatus" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fstatus}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">当前审批人:</label>
			</td>
			<td class="value">
		     	 <input id="fcurrentApprover" name="fcurrentApprover" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fcurrentApprover}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">当前审批人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">申请人:</label>
			</td>
			<td class="value">
		     	 <input id="fapplicant" name="fapplicant" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fapplicant}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请人</label>
			</td>
			<td align="right">
				<label class="Validform_label">申请时间:</label>
			</td>
			<td class="value">
		     	 <input id="fapplicantDate" name="fapplicantDate" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fapplicantDate}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="fremark" name="fremark" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 value='${tOffersPage.fremark}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="tOffersController.do?tOffersEntryList&id=${tOffersPage.id}" icon="icon-search" title="报价单明细" id="tOffersEntry"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_tOffersEntry_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="tOffersEntryList[#index#].fnumber" type="list" 
										dictTable="t_doors" dictField="id" dictText="fnumber" defaultVal="" hasLabel="false"  title="代码"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">代码</label>
				  </td>
				  <td align="left">
					       	<input name="tOffersEntryList[#index#].fname" maxlength="50" 
						  		type="text" class="inputxt"  style="width:120px;"  >
					  <label class="Validform_label" style="display: none;">名称</label>
				  </td>
				  <td align="left">
					       	<input name="tOffersEntryList[#index#].fmodel" maxlength="50" 
						  		type="text" class="inputxt"  style="width:120px;"  >
					  <label class="Validform_label" style="display: none;">门型型号</label>
				  </td>
				  <td align="left">
					       	<input name="tOffersEntryList[#index#].fstandard" maxlength="50" 
						  		type="text" class="inputxt"  style="width:120px;"  >
					  <label class="Validform_label" style="display: none;">标准配件</label>
				  </td>
				  <td align="left">
					       	<input name="tOffersEntryList[#index#].foptions" maxlength="50" 
						  		type="text" class="inputxt"  style="width:120px;"  >
					  <label class="Validform_label" style="display: none;">可选配件</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="tOffersEntryList[#index#].fsurface" type="list" 
										dictTable="t_door_surface" dictField="id" dictText="fname" defaultVal="" hasLabel="false"  title="表面处理"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">表面处理</label>
				  </td>
				  <td align="left">
					  	<input name="tOffersEntryList[#index#].fratio" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">系数</label>
				  </td>
				  <td align="left">
					  	<input name="tOffersEntryList[#index#].fprice" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">价格</label>
				  </td>
				  <td align="left">
					  	<input name="tOffersEntryList[#index#].famount" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">金额</label>
				  </td>
				  <td align="left">
					  	<input name="tOffersEntryList[#index#].fremark" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/offer/tOffers.js"></script>	
