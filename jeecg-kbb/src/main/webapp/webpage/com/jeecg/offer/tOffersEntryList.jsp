<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTOffersEntryBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTOffersEntryBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTOffersEntryBtn').bind('click', function(){   
 		 var tr =  $("#add_tOffersEntry_table_template tr").clone();
	 	 $("#add_tOffersEntry_table").append(tr);
	 	 resetTrNum('add_tOffersEntry_table');
	 	 return false;
    });  
	$('#delTOffersEntryBtn').bind('click', function(){   
      	$("#add_tOffersEntry_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tOffersEntry_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#tOffersEntry_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTOffersEntryBtn" href="#">添加</a> <a id="delTOffersEntryBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="tOffersEntry_table">
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
						门型型号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						标准配件
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						可选配件
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						表面处理
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						系数
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
	<tbody id="add_tOffersEntry_table">
	<c:if test="${fn:length(tOffersEntryList)  > 0 }">
		<c:forEach items="${tOffersEntryList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="tOffersEntryList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="tOffersEntryList[${stuts.index }].foreignid" type="hidden" value="${poVal.foreignid }"/>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fnumber" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fnumber }">
					  <label class="Validform_label" style="display: none;">代码</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fname" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fname }">
					  <label class="Validform_label" style="display: none;">名称</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fmodel" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fmodel }">
					  <label class="Validform_label" style="display: none;">门型型号</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fstandard" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fstandard }">
					  <label class="Validform_label" style="display: none;">标准配件</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].foptions" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.foptions }">
					  <label class="Validform_label" style="display: none;">可选配件</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fsurface" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fsurface }">
					  <label class="Validform_label" style="display: none;">表面处理</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fratio" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fratio }">
					  <label class="Validform_label" style="display: none;">系数</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fprice" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fprice }">
					  <label class="Validform_label" style="display: none;">价格</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].famount" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.famount }">
					  <label class="Validform_label" style="display: none;">金额</label>
				   </td>
				   <td align="left">
					  	<input name="tOffersEntryList[${stuts.index }].fremark" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.fremark }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
