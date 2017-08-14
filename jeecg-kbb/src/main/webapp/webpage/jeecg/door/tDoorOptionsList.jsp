<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTDoorOptionsBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTDoorOptionsBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTDoorOptionsBtn').bind('click', function(){   
 		 var tr =  $("#add_tDoorOptions_table_template tr").clone();
	 	 $("#add_tDoorOptions_table").append(tr);
	 	 resetTrNum('add_tDoorOptions_table');
	 	 return false;
    });  
	$('#delTDoorOptionsBtn').bind('click', function(){   
      	$("#add_tDoorOptions_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tDoorOptions_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}	
		//将表格的表头固定
	    $("#tDoorOptions_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
		resetTrNum("add_tDoorOptions_table");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTDoorOptionsBtn" href="#">添加</a> <a id="delTDoorOptionsBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="tDoorOptions_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 50px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 50px;">操作</td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				代码
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				名称
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				型号
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				数量
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				价格
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				金额
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
				备注
		  </td>
	</tr>
	<tbody id="add_tDoorOptions_table">
	<c:if test="${fn:length(tDoorOptionsList)  > 0 }">
		<c:forEach items="${tDoorOptionsList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh"></div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="tDoorOptionsList[#index#].id" id="tDoorOptionsList[#index#].id" type="hidden" value="${poVal.id }"/>
					<input name="tDoorOptionsList[#index#].foreignid"  id="tDoorOptionsList[#index#].foreignid" type="hidden" value="${poVal.foreignid }"/>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fnumber"  id="tDoorOptionsList[#index#].fnumber" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.fnumber }">
					  <label class="Validform_label" style="display: none;">代码</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fname"  id="tDoorOptionsList[#index#].fname" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fname }" readonly="readonly">
					  <label class="Validform_label" style="display: none;">名称</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fmodel"  id="tDoorOptionsList[#index#].fmodel" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fmodel }">
					  <label class="Validform_label" style="display: none;">型号</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fqty"  id="tDoorOptionsList[#index#].fqty" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.fqty }"
					  		onChange="calAmount('tDoorOptionsList[#index#]')" >
					  <label class="Validform_label" style="display: none;">数量</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fprice"  id="tDoorOptionsList[#index#].fprice" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.fprice }"
					  		onChange="calAmount('tDoorOptionsList[#index#]')">
					  <label class="Validform_label" style="display: none;">价格</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].famount"  id="tDoorOptionsList[#index#].famount" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.famount }"readonly="readonly">
					  <label class="Validform_label" style="display: none;">金额</label>
				   </td>
				   <td align="left">
					  	<input name="tDoorOptionsList[#index#].fremark" id="tDoorOptionsList[#index#].fremark"  maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fremark }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
