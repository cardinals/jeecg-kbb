<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	
	
    $(document).ready(function(){
    	 $(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
// 	    $("#tDoorParams_table").createhftable({
// 	    	height:'300px',
// 		/* 	width:'auto', */
// 			fixFooter:false
// 			}); 	
		
	    $("#add_tDoorParams_table input[type='checkbox']").each(function(){
	    	if (this.value == "true") {
	           		$(this).attr("checked", true);
	           }else{
	        	   $(this).attr("checked", false);
	           }
	    	});

    });
</script>
<table id="tDoorParams_table"  border="0" cellpadding="2" cell	pacing="0">
	<thead>
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 50px;">序号</td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
				名称
		  </td>
		  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
				备注
		  </td>
		  <td align="center" bgcolor="#EEEEEE" style="width: 120px;">显示</td>
	</tr>
	</thead>
	<tbody id="add_tDoorParams_table">
	<c:if test="${fn:length(tDoorParamsList)  > 0 }">	
		<c:forEach items="${tDoorParamsList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width:50px;"  name="xh">${stuts.index + 1}</div></td>				
				<input name="tDoorParamsList[${stuts.index }].fparamsid" id="tDoorParamsList[${stuts.index }].fparamsid" 
					type="hidden" value="${poVal.fparamsid }"/>
				<input name="tDoorParamsList[${stuts.index }].ffeildname" id="tDoorParamsList[${stuts.index }].ffeildname" 
					type="hidden" value="${poVal.ffeildname }"/>	
			   <td align="left" style="width:120px;" >${poVal.fcaption }</td>			 
			   <td align="left" style="width:120px;" >${poVal.fremark }</td>
			   <td align="center">
			   		<input type="checkbox" name="tDoorParamsList[${stuts.index }].fshow" id="tDoorParamsList[${stuts.index }].fshow" maxlength="50" 			   			
			   		style="width:120px;"  value="${poVal.fshow }" onclick="this.value=this.checked">
			   	</td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
