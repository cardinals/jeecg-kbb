<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="webpage/jeecg/offer-v2/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="webpage/jeecg/offer-v2/css/icon.css">
	<script type="text/javascript" src="webpage/jeecg/offer-v2/js/jquery.min.js"></script>
	<script type="text/javascript" src="webpage/jeecg/offer-v2/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="webpage/jeecg/offer-v2/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="webpage/jeecg/offer-v2/js/offer2.js"></script>
	<style>
			.row{
				display: table;
				width: 98%;
			}
			.form-inline{
			    display: inline-block;
			    font-size: 11px;
			    margin-right: 20px;
			}	
			.datagrid-footer {   
			    background-color: #E0ECFF;
			    font-weight: bold;
			}		
	</style>
</head>
<body id="offer-edit-form">
	<h2>报价单</h2>
	<input id="offer-id" type="hidden" value="${wxOffer.id}">
	<input id="backUrl" type="hidden" value="${backUrl}">
	<input id="offer-action" type="hidden" value="${action}">
	<div class="row">
		<div class="form-inline">
			<label class="form-inline">单号</label>
			<input id="fbillno" class="form-inline easyui-textbox" disabled="disabled"/>
		</div>		
		<div class="form-inline">
			<label class="form-inline">项目编码</label>
			<input id="fprojectNumber" class="form-inline easyui-textbox" />
		</div>
		<div class="form-inline">
			<label class="form-inline">项目名称</label>
			<input id="fprojectName" class="form-inline easyui-textbox" />
		</div>
		<div class="form-inline">
			<label class="form-inline">客户</label>
			<input class="easyui-combobox" 
			id="fcustid"  
			data-options="				
					url:'wxBase.do?getBaseCustomer',
					method:'get',
					valueField:'id',
					textField:'fname',
					panelHeight:'auto'
			">
		</div>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：旋转门及选项
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-xzm" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-xzm" class="easyui-datagrid" title="旋转门及选项" >
		</table>		
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：平门系列及选项
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-pm" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-pm" class="easyui-datagrid" title="平门系列及选项">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：边门选项
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-frame" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-frame" class="easyui-datagrid" title="边门选项">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：维保费用
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-maintenance" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-maintenance" class="easyui-datagrid" title="维保费用">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：运输费用
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-transport" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-transport" class="easyui-datagrid" title="运输费用">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：安装费用
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-installation" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-installation" class="easyui-datagrid" title="安装费用">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：其他费用
    -->
    <div style="margin:20px 0;"></div>
	<div class="row">
		<div id="tb-other" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销</a>	
		</div>
		<table id="table-other" class="easyui-datagrid" title="其他费用">
		</table>
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：备注
    -->
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="备注" style="width:97%;height:100px;">
		<input id="fremark" class="easyui-textbox" data-options="multiline:true" style="width:99%;height:70px">
	</div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：总金额
    -->
	<div style="margin:20px 0;"></div>
	<div style="margin-right: 200px;font-size: larger;float:right;">总金额
		<div id="ftotalAmount" class="form-inline" style="font-size: larger;margin-left: 20px;"> 0.00</div>
	</div>
	<div style="height: 50px;"></div>
	<!--
    	作者：edwardorchis@hotmail.com
    	时间：2017-09-17
    	描述：按钮
    -->
	<div style="float: right;margin-right: 20px;margin-bottom:20px;padding:5px 0;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="doSave()" style="width:80px">保存</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="goBack()" style="width:80px">返回</a>
	</div>
	

</body>
</html>