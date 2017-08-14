function jumpToBill(id){
	 var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?jumpToBill&id="+id,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	doUrl(url_input.value+result);
	        }  
	    });
	
}
function rejectTask(id){
	 var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?rejectTask&id="+id,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	if(result=="OK"){
	        		 $.messager.show({  
		        	        title : '消息提醒',  
		        	        msg : "驳回成功",  
		        	        timeout : 1000 * 5
		        	    });	        	
	        		 document.getElementById('formSubmit').submit();
	        	}else{
	        	    $.messager.show({  
	        	        title : '消息提醒',  
	        	        msg : "驳回失败。"+result,  
	        	        timeout : 1000 * 5
	        	    });
	        	}
	        }  
	    });
}

function adoptTask(id){
	$('#detailModalTaskHandle').modal('show');
	$('#btn-task-handle-primary').unbind("click");
	var url_input=document.getElementById("wxUrl");
	$.ajax({  
        url: url_input.value+"activitiOffer.do?showApproval&id="+id,
        type: "get",  
        dataType: "html",  
        success: function (result) {
        	$('#modal-dialog-taskhandle-body').html(result);
        	$('#btn-task-handle-primary').bind('click',function(){       		
        		btnPrimaryClick();
        	}); 
        }
    });
}
function btnPrimaryClick(){
	 var message=document.getElementById('task-handle-message').value;
	 var url_input=document.getElementById("wxUrl").value;
	 var nextprocessor=document.getElementById('task-handle-nextprocesser').value;
	 var id=document.getElementById("task-handle-taskId").value;
	 $.ajax({  
		 	url: url_input+"activitiOffer.do?adoptTask&id="+id+"&message="+message+"&nextprocessor="+nextprocessor,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	if(result=="OK"){
	        		 $.messager.show({  
		        	        title : '消息提醒',  
		        	        msg : "审核成功",  
		        	        timeout : 1000 * 5
		        	    });	        	
	        		 document.getElementById('formSubmit').submit();
	        	}else{
	        	    $.messager.show({  
	        	        title : '消息提醒',  
	        	        msg : "审核失败。"+result,  
	        	        timeout : 1000 * 5
        	    });
        	}
        }  
    });
}

function bindComboGridDoor(input_id){
	var url_input=document.getElementById("wxUrl").value;
	var id=document.getElementById("task-handle-taskId").value;
	$('#'+input_id).combogrid({
	    panelWidth:200, 
	    panelHeight:300,
	    url:url_input+"activitiOffer.do?getNextProcessor&id="+id,
	    idField:'fname',  
	    textField:'fname',  
	    onLoadSuccess:function(old){
	    	$('#'+input_id).combogrid('setValue',old.rows[0].name);//设置文本框的默认值为第一条，下标从0开始  
	    },  
		onSelect: function(rowIndex, rowData){document.getElementById(input_id).value=rowData.fname;},
	    columns:[[ 
	    	 	  {field:'id',title:'ID',width:60,hidden:true}, 
	              {field:'fname',title:'名称',width:200}          
	              ]]
	}); 
}
	
	
	
	
