<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="t_base_surfaceList"  checkbox="true" pagination="true" fitColumns="true" 
  title="表面处理" actionUrl="baseSurfaceController.do?datagrid" idField="id"  queryMode="group"  
 pageSize="100" extendParams="pageList:'[100,300,500]',">
    <t:dgCol title="id"  field="id"   hidden="true"   width="140"></t:dgCol>    
    <t:dgCol title="名称"  field="fname" query="true" extendParams="editor:'text'" width="150"></t:dgCol>
    <t:dgCol title="代码"  field="fnumber" query="true" extendParams="editor:'text'" width="150"></t:dgCol>
   	<t:dgCol title="备注"  field="fremark" query="true" extendParams="editor:'text'" width="300"></t:dgCol>
   
    <t:dgToolBar operationCode="add" title="新增" icon="icon-add"  funname="addRow"></t:dgToolBar>
	<t:dgToolBar operationCode="edit" title="编辑" icon="icon-edit"  funname="editRow"></t:dgToolBar>
	<t:dgToolBar operationCode="save" title="保存" icon="icon-save" url="baseSurfaceController.do?saveRows" funname="saveData"></t:dgToolBar>
	<t:dgToolBar operationCode="undo" title="取消编辑" icon="icon-undo" funname="reject"></t:dgToolBar>
	<t:dgToolBar title="批量删除"  icon="icon-remove" url="baseSurfaceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>	
 	<t:dgToolBar exp=" id=\"t_base_surfaceimport\" " operationCode="import" title="Excel数据导入"  icon="icon-put"></t:dgToolBar>
<!-- 	<a id="t_base_surfaceimport">Excel导入</a> -->
	<t:dgToolBar operationCode="excel" title="Excel导出"  icon="icon-putout" funname="t_base_surfaceExportExcel"></t:dgToolBar>
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
	
	function t_base_surfaceExportExcel(){
		var queryParams = $('#t_base_surfaceList').datagrid('options').queryParams;
		$('#t_base_surfaceListtb').find('*').each(function() {
		    queryParams[$(this).attr('name')] = $(this).val();
		});
		var params = '&';
		$.each(queryParams, function(key, val){
			params+='&'+key+'='+val;
		}); 
		var fields = '&field=';//field=fname,fnumber,fremark
		$.each($('#t_base_surfaceList').datagrid('options').columns[0], function(i, val){
			if(val.field != 'opt'&&val.field != 'ck'){
				fields+=val.field+',';
			}
		}); 

		window.location.href = "excelTempletController.do?exportXls&tableName=t_base_surface"+encodeURI(params+fields)
	}
	
	$(function(){
		var uploader = WebUploader.create({
		    // swf文件路径
		    swf: '/plug-in/webuploader/Uploader.swf',
		    // 文件接收服务端。
			server: 'excelTempletController.do?importExcel&tableName=t_base_surface',
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: '#t_base_surfaceimport',
		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
		    //指明参数名称，后台也用这个参数接收文件
		    duplicate: false,
		    auto: true//,
		    //每次上传附带参数
		    //formData:{"isup":"1"}
		 
		});
		
		uploader.on( 'uploadSuccess', function(file,rsp) {
			$('#t_base_surfaceList').datagrid('reload');
		});
		uploader.on( 'uploadError', function( file) {
//		    $( '#'+file.id ).find('div.state').html(file.name+'---上传出错');
		    $.messager.show({  
	   	        title : '消息提醒',  
	   	        msg : file.name+"上传出错",  
	   	        timeout : 1000 * 5
	   	     });
		});
		uploader.on( 'uploadComplete', function( file ) {
//		   $( '#'+file.id ).find('.progress').fadeOut('slow');
			uploader.removeFile(file);
		});
	});
 
 </script>
<style>
 	.webuploader-pick {
	    position: relative;
	    display: inline-block;
	    cursor: pointer;
	    /* background: #00b7ee; */
	    padding: 0;
	    color: #fff;
	    text-align: center;
	    border-radius: 0;
	    overflow: visible;
	}
 </style>