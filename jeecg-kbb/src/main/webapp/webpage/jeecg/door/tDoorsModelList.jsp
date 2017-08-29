<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTDoorsModelBtn" href="#">添加</a> <a id="delTDoorsModelBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="tDoorsModel_table">
	   <tr bgcolor="#E6E6E6" >
			
		</tr>
	
	<tbody id="add_tDoorsModel_table">	
		  <tr bgcolor="#E6E6E6">
			<td align="center" bgcolor="#EEEEEE" style="width: 30px;">序号</td>
			<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>	
			  <c:forEach items="${tDoorModelListCaption}" var="poVal">		  
			    <td align="left" bgcolor="#EEEEEE" style="width: 126px;" id="tdDoorModelListCaption.${poVal.fkey}">
					${poVal.fcaption}
			  	</td>
			  </c:forEach>	
		  </tr>
		<c:forEach items="${tDoorModelExMap}" var="entry" varStatus="stuts">  
			<tr>
			   <c:set value="${entry.key}" var="id"/>   
				<td align="center"><div style="width: 30px;" name="xh"></div></td>
				<td align="center"><input style="width:25px;"  type="checkbox" name="ck" /></td>
				<input name="tDoorModelExMap[#index#][id]"  id="tDoorModelExMap[#index#][id]"   type="hidden" value="${id}"/>
				<%-- <input name="tDoorModelExMap[${id}][${tDoorModelExMap[id]}]" type="hidden" value="${tDoorModelExMap[id][\"foreignid\"] }"/> --%>			
			   <c:forEach items="${tDoorModelListCaption}" var="pVal">		
			   		<c:set value="${pVal.fkey}" var="key"/>  
			   		<td align="left"  id="tdDoorModelExMap[#index#][${key}]" > 
					    <input name="tDoorModelExMap[#index#][${key}]" id="tDoorModelExMap[#index#][${key}]"  maxlength="20" 
					  		type="${pVal.finputtype}" class="inputxt"  style="width:120px;"  value="${tDoorModelExMap[id][key]}">
					    <label class="Validform_label" style="display: none;">${pVal.fcaption} </label>
				     </td>
			   </c:forEach>
	 		</tr>
		</c:forEach>
	</tbody>
</table>
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
		
 		resetTrNum("add_tDoorsModel_table");
    	initTdDisplay("规格型号");
    });
</script>