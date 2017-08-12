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
	 var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?adoptTask&id="+id+"&message=同意"+"&nextprocessor=张三",
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