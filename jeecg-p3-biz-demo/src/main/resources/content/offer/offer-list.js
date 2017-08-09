
function submitTask(id){

	var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?submitTask&id="+id,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	if(result.equals("OK")){
	        		 $.messager.show({  
		        	        title : '消息提醒',  
		        	        msg : "提交成功",  
		        	        timeout : 1000 * 2
		        	    });
	        	    document.getElementById("formSubmit").submit();
	        	}else{
	        	    $.messager.show({  
	        	        title : '消息提醒',  
	        	        msg : "提交失败。"+result,  
	        	        timeout : 1000 * 2
	        	    });
	        	}
	        }  
	    });
}
