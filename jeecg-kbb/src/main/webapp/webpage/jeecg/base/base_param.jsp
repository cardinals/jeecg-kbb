<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="t_base_paramList"  checkbox="true" pagination="true" fitColumns="true" 
  title="一般参数" actionUrl="baseParamController.do?datagrid" idField="id"  queryMode="group">
    <t:dgCol title="id"  field="id"   hidden="true"   width="140"></t:dgCol>    
    <t:dgCol title="字段名"  field="ffeildname" query="true" extendParams="editor:'text'" width="150"></t:dgCol>
    <t:dgCol title="描述"  field="fcaption" query="true" extendParams="editor:'text'" width="150"></t:dgCol>
   	<t:dgCol title="备注"  field="fremark" query="true" extendParams="editor:'text'" width="150"></t:dgCol>
   	<t:dgCol title="是否同步到数据库"  field="fisdbsynch" query="true" width="150" readonly="readonly"></t:dgCol>
   
    <t:dgToolBar operationCode="add" title="新增" icon="icon-add"  funname="addRow"></t:dgToolBar>
	<t:dgToolBar operationCode="edit" title="编辑" icon="icon-edit"  funname="editRow"></t:dgToolBar>
	<t:dgToolBar operationCode="save" title="保存" icon="icon-save" url="baseSurfaceController.do?saveRows" funname="saveData"></t:dgToolBar>
	<t:dgToolBar operationCode="undo" title="取消编辑" icon="icon-undo" funname="reject"></t:dgToolBar>
	<t:dgToolBar title="批量删除"  icon="icon-remove" url="baseSurfaceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
<%-- 	<t:dgToolBar operationCode="import" title="Excel数据导入"  icon="icon-put" funname="add('Excel数据导入','excelTempletController.do?goImplXls&tableName=t_base_surface','t_base_surfaceList')"></t:dgToolBar>
	<t:dgToolBar operationCode="excel" title="Excel导出"  icon="icon-putout" funname="t_base_surfaceExportExcel"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
//添加行
	function addRow(title,addurl,gname){
		$('#'+gname).datagrid('appendRow',{});
		var editIndex = $('#'+gname).datagrid('getRows').length-1;
		$('#'+gname).datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	}
	//保存数据
	function saveData(title,addurl,gname){
		if(!endEdit(gname))
			return false;
		var rows=$('#'+gname).datagrid("getChanges","inserted");
		var uprows=$('#'+gname).datagrid("getChanges","updated");
		rows=rows.concat(uprows);
		if(rows.length<=0){
			tip("没有需要保存的数据！")
			return false;
		}
		var result={};
		for(var i=0;i<rows.length;i++){
			for(var d in rows[i]){
				result["demos["+i+"]."+d]=rows[i][d];
			}
		}
		$.ajax({
			url:"<%=basePath%>/"+addurl,
			type:"post",
			data:result,
			dataType:"json",
			success:function(data){
				tip(data.msg);
				if(data.success){
					reloadTable();
				}
			}
		})
	}
	//结束编辑
	function endEdit(gname){
		var  editIndex = $('#'+gname).datagrid('getRows').length-1;
		for(var i=0;i<=editIndex;i++){
			if($('#'+gname).datagrid('validateRow', i))
				$('#'+gname).datagrid('endEdit', i);
			else
				return false;
		}
		return true;
	}
	//编辑行
	function editRow(title,addurl,gname){
		var rows=$('#'+gname).datagrid("getChecked");
		if(rows.length==0){
			tip("请选择条目");
			return false;
		}
		for(var i=0;i<rows.length;i++){
			var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
			$('#'+gname).datagrid('beginEdit', index);
		}
	}

	//取消编辑
	function reject(title,addurl,gname){
		$('#'+gname).datagrid('clearChecked');
		$('#'+gname).datagrid('rejectChanges');


	}
	
	
 
 </script>