$(function(){
	$('#detailworkflow').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget); // Button that triggered the modal		 
		  var url_input=document.getElementById("wxUrl");
		  var businesskey=$('#detailworkflow_businesskey').val();
		  var url=url_input.value+"activitiOffer.do?showWorkflow&businesskey="+businesskey;
		  var arr=businesskey.split('.');
		  if(arr[0]=="toDiscount"){//如果是折扣申请提交的时候
			  url=url_input.value+"activitiOffer.do?toDiscount&id="+arr[1];
			  $('#detailModalLabel').html('折扣申请');
			  $('.modal-dialog').css('width','600px');
		  }
		  $.ajax({  
		        url: url,  
		        type: "get",  
		        dataType: "html",  
		        success: function (result) {
		        	$("#detailworkflow-dialog-body").html(result);
		        }
		    });		
		})
	
});
function submitTask(id,type){
	var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?submitTask&id="+id+"&type="+type,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	if(result=="OK"){
	        		 $.messager.show({  
		        	        title : '消息提醒',  
		        	        msg : "提交成功",  
		        	        timeout : 1000 * 5
		        	    });	        	
	        		 setTimeout("document.getElementById('formSubmit').submit();",1000)
	        	}else{
	        	    $.messager.show({  
	        	        title : '消息提醒',  
	        	        msg : "提交失败。"+result,  
	        	        timeout : 1000 * 5
	        	    });
	        	}
	        }  
	    });
}

function showWorkflow(id){
	var url_input=document.getElementById("wxUrl");
	 $.ajax({  
	        url: url_input.value+"activitiOffer.do?showWorkflow&id="+id,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	
	        }  
	    });
}
