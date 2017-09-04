function jumpToBill(id,backurl,businesskey){
	 var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?jumpToBill&id="+id+"&backurl="+backurl+"&businesskey="+businesskey,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	doUrl(url_input.value+result);
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
        }
    });
}


	
	
	
	
