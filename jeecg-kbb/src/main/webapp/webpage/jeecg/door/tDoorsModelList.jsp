<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTDoorsModelBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTDoorsModelBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTDoorsModelBtn').bind('click', function(){   
 		 var tr =  $("#add_tDoorsModel_table_template tr").clone();
	 	 $("#add_tDoorsModel_table").append(tr);
	 	 resetTrNum('add_tDoorsModel_table');
	 	 return false;
    });  
	$('#delTDoorsModelBtn').bind('click', function(){   
      	$("#add_tDoorsModel_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tDoorsModel_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#tDoorsModel_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTDoorsModelBtn" href="#">添加</a> <a id="delTDoorsModelBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="tDoorsModel_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						代码
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						名称
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						规格型号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						备注
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						价格
				  </td>
	</tr>
	<tbody id="add_tDoorsModel_table">
	<c:if test="${fn:length(tDoorsModelList)  > 0 }">
		<c:forEach items="${tDoorsModelList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="tDoorsModelList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="tDoorsModelList[${stuts.index }].foreignid" type="hidden" value="${poVal.foreignid }"/>
				   <td align="left">
					  	<input name="tDoorsModelList[${stuts.index }].fnumber" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.fnumber }">
					  <label class="Validform_label" style="display: none;">代码</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorsModelList[${stuts.index }].fname" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.fname }">
					  <label class="Validform_label" style="display: none;">名称</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorsModelList[${stuts.index }].fmodel" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fmodel }">
					  <label class="Validform_label" style="display: none;">规格型号</label>
				   </td>
				   <td align="left">
					       	<input name="tDoorsModelList[${stuts.index }].fremark" maxlength="50" 
						  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.fremark }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorsModelList[${stuts.index }].fprice" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.fprice }">
					  <label class="Validform_label" style="display: none;">价格</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
