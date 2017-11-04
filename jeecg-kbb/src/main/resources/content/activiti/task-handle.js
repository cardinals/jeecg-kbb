//var comboNextProcessorInfo;
var currentNodeFormKey;//当前节点的FormKey
var currentBusinessKey;
var existNextProcesser=false;
jQuery(function(){
	var url_input=document.getElementById("wxUrl").value;
	var id=document.getElementById("task-handle-taskId").value;
	jQuery.ajax({  
		 	url: url_input+"activitiOffer.do?getTaskHandle&id="+id,
	        type: "get",  
	        dataType: "json",  
	        success: function (data) {
	        	if(data.success=="true"){
	        		if(data.errdesc=="end"){
	        			jQuery('#nextprocesser-appoint').remove();
	        		}
	        		if(data.businesskey!=""){
	        			currentBusinessKey=data.businesskey;
        				htmlworkflow();
	        		}	        		     
	        	}else{
	        		jQuery('#task-handle-business-content').html(data.errdesc);
	        		jQuery('#task-handle-business-content').css('color','red');
     	        }
	        	initDivs(data);
     	}
 	});
	bindRatioClick();
	jQuery('#btn-task-handle-primary').bind('click',function(){       		
		btnPrimaryClick();
	}); 
});
function initDivs(data){
	var url_input=document.getElementById("wxUrl").value;
	currentNodeFormKey=data.formKey;
	//没有分支，隐藏同意/驳回
	if(currentNodeFormKey.indexOf("branchable")==-1){
		jQuery("#task-handle-action").css("display","none");
	}
	//显示是否外协
	if(currentNodeFormKey.indexOf("outsource")!=-1){
		jQuery("#task-handle-action").css("display","none");
		jQuery("#task-handle-outsource").css("display","block");
		bindOutsourceRatioClick();
		//默认是直接往下走
		bindNextProcessor("xiaoguan");
//		jQuery("#nextprocesser-appoint").css("display","none");
	}
	//折扣申请环节
	if(currentNodeFormKey.indexOf("discount")!=-1){
		jQuery("#task-handle-action").css("display","none");
		jQuery("#task-handle-discount").css("display","block");
		jQuery("#nextprocesser-appoint").css("display","none");
		bindDiscountRatioClick();
	}
	//经理审批折扣环节
	if(currentNodeFormKey.indexOf("ratify")!=-1){	
		jQuery("#nextprocesser-appoint").css("display","none");
		jQuery.ajax({  
	        url: url_input+"activitiOffer.do?toDiscount&businesskey="+currentBusinessKey, 
	        type: "get",  
	        dataType: "html",  
	        success: function (result) {
	        	jQuery("#task-handle-business-content").html(result);		         	
	        }
	    });	
	}
	//物资部外协/外包报价
	if(currentNodeFormKey.indexOf("outerprice")!=-1){
		jQuery("#nextprocesser-appoint").css("display","none");
		jQuery.ajax({  
	        url: url_input+"activitiOffer.do?toOuterprice&businesskey="+currentBusinessKey, 
	        type: "get",  
	        dataType: "html",  
	        success: function (result) {
	        	jQuery("#task-handle-business-content").html(result);		         	
	        }
	    });	
	}
	if(!existNextProcesser){
		bindNextProcessor("EMPTY");
	}
}

function htmlworkflow(){	
	var url_input=document.getElementById("wxUrl").value;
	jQuery.ajax({  
        url:  url_input+"activitiOffer.do?showWorkflow&viewImage=false&businesskey="+currentBusinessKey,  
        type: "get",  
        dataType: "html",  
        success: function (result) {
        	jQuery("#task-handle-workflow-content").html(result);              	
        }
    });	
}
function bindRatioClick(){	
	jQuery("input[name='task-handle-radio-action']").bind('click',function(){       		
		var action=jQuery("input[name='task-handle-radio-action']:checked").val();
		if(action=="同意"){
			jQuery("#nextprocesser-appoint").css("display","block");
		}else{
			jQuery("#nextprocesser-appoint").css("display","none");
		}
	}); 
}
function bindOutsourceRatioClick(){	
	jQuery("input[name='task-handle-radio-outsource']").bind('click',function(){       		
		var action=jQuery("input[name='task-handle-radio-outsource']:checked").val();
		console.log("bindOutsourceRatioClick:"+action);
		if(action=="驳回"){//驳回不需要指定下一步处理人
//			jQuery('#task-handle-nextprocesser').combogrid('grid').datagrid("loadData", comboNextProcessorInfo);  
			jQuery("#nextprocesser-appoint").css("display","none");
		}else{
			var definitionid="material";
			if(action=="同意"){
				definitionid="xiaoguan";
			}			
			bindNextProcessor(definitionid);
		}
	}); 
}
function bindDiscountRatioClick(){	
	var url_input=document.getElementById("wxUrl").value;	
	jQuery("input[name='task-handle-radio-discount']").bind('click',function(){       		
		var action=jQuery("input[name='task-handle-radio-discount']:checked").val();
		if(action=="不打折"){
			jQuery("#task-handle-business-content").html("");
		}else{
			jQuery.ajax({  
		        url: url_input+"activitiOffer.do?toDiscount&businesskey="+currentBusinessKey, 
		        type: "get",  
		        dataType: "html",  
		        success: function (result) {
		        	jQuery("#task-handle-business-content").html(result);		         	
		        }
		    });	
		}
	}); 
}

function btnPrimaryClick(){
	 var data={};
	 data.definitionid="";
	 data.branch="";
	 data.message=document.getElementById('task-handle-message').value;
	 data.businessType="";
	 var url_input=document.getElementById("wxUrl").value;
	 data.nextprocessor="";
	 var nextEle= document.getElementById('task-handle-nextprocesser');
	 if(nextEle!=null && nextEle!=undefined){
		 data.nextprocessor=nextEle.value;
	 }
	 data.id=document.getElementById("task-handle-taskId").value;
	//如果是技术总监，下一步连线有三个分支
	 if(currentNodeFormKey.indexOf("outsource")!=-1){
		 data.branch=jQuery("input[name='task-handle-radio-outsource']:checked").val();
	/*	 if(data.branch=="同意"){
			 data.definitionid="saleman";
		 }*/
	 }else if(currentNodeFormKey.indexOf("discount")!=-1){
		 data.branch=jQuery("input[name='task-handle-radio-discount']:checked").val();
		 if(data.branch=="打折"){
			 data.businessType="discount";
			 data.businessPackage=packageDiscountData();
		 }
	 }else if(currentNodeFormKey.indexOf("outerprice")!=-1){
		 data.businessType="outerprice";
		 data.businessPackage={};
		 data.businessPackage.fouterprice=jQuery("#outsource\\.fouterprice").val();
	 }else{
		 data.branch=jQuery("input[name='task-handle-radio-action']:checked").val();
	 }
	 jQuery.ajax({  
		 	url: url_input+"activitiOffer.do?adoptTask",
		 	data:data,
	        type: "post",  
	        dataType: "text",  
	        success: function (result) {  
	        	if(result=="OK"){
	        		jQuery.messager.show({  
		        	        title : '消息提醒',  
		        	        msg : "审核成功",  
		        	        timeout : 1000 * 5
		        	    });	        	
	        		 document.getElementById('formSubmit').submit();
	        	}else{
	        		jQuery.messager.show({  
	        	        title : '消息提醒',  
	        	        msg : "审核失败。"+result,  
	        	        timeout : 1000 * 5
	        			});
	        	}
       }  
   });
}
function packageDiscountData(){
	var arr={};
	arr.discountrate=document.getElementById("discount.rate").value;
	arr.afteramount=document.getElementById("discount.afteramount").value;	
	return arr;
}

function bindNextProcessor(definitionid){
	var url_input=document.getElementById("wxUrl").value;
	if(!existNextProcesser){
		jQuery('#task-handle-nextprocesser').combogrid({
		    panelWidth:200, 
		    panelHeight:300,
	//	    url:url_input+"activitiOffer.do?getNextProcessor&id="+id,
		    idField:'fname',  
		    textField:'fname',  
		    onLoadSuccess:function(data){
		    	if(data.total>0){
		    		jQuery('#task-handle-nextprocesser').combogrid('grid').datagrid('selectRow',0);
		    	}
		    },  
			onSelect: function(rowIndex, rowData){jQuery('#task-handle-nextprocesser').val(rowData.fname);},
		    columns:[[ 
		    	 	  {field:'id',title:'ID',width:60,hidden:true}, 
		              {field:'fname',title:'名称',width:200}          
		              ]]
		});
		existNextProcesser=true;
	}
//	if(!comboNextProcessorInfo[definitionid]){
		var url_input=document.getElementById("wxUrl").value;	
		var id=document.getElementById("task-handle-taskId").value;
		var requestDefinitionid=definitionid;
		if(definitionid==="EMPTY"){
			requestDefinitionid="";
		}
	    $.ajax({  
	        url:url_input+"activitiOffer.do?getNextProcessor&definitionid="+requestDefinitionid+"&id="+id,
	        type: "get",  	
	        dataType: "json",  
	        success: function (result) {  
//	        	comboNextProcessorInfo[definitionid]=result;
	        	jQuery('#task-handle-nextprocesser').combogrid('grid').datagrid("loadData", result); 	        
	        }  
	    });  
//	}else{
//		jQuery('#task-handle-nextprocesser').combogrid('grid').datagrid("loadData", comboNextProcessorInfo[definitionid]);  
//	}
	    jQuery("#nextprocesser-appoint").css("display","block");
}

