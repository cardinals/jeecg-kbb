
var whole_amount=0.00;
var parts_amout=0.00;
var surfaceRatio=1.00;
var totalAmount=0.00;
var detail2_obj={};

function initDialog(){	
	var tag=document.getElementById('detailModal_tag').value;
	var action=document.getElementById('detailModal_action').value;
	$("#p1ModelHeadCaption").text(document.getElementById(tag+".item_name").value);
	document.getElementById("p5Amount.wholerate").value=document.getElementById(tag+".wholerate").value;
	document.getElementById("p5Amount.partsrate").value=document.getElementById(tag+".partsrate").value;
	document.getElementById("p5Amount.amount").value=document.getElementById(tag+".price").value;
	
	var detail2_json=document.getElementById(tag+".detail2json").value;
	if(detail2_json){
		var obj =JSON.parse(detail2_json);
		setRedioValue("p1ModelE",obj.p1);
		setCheckValue('p2Standard',obj.p2);
		setCheckValue('p3Option',obj.p3);
		setRedioValue('p4Surface',obj.p4);
	}
    document.getElementById("p5Amount.remark").value=document.getElementById(tag+".remark").value;
	if(action=="view"){
		$("#modal-dialog-body input").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("#detailModal .btn-primary").hide();
	}else{
		$("#detailModal .btn-primary").show();
		$("#detailModal input[type='checkbox']").each(function(){
			$(this).bind("click",function(){
		    		this.value=this.checked;
		    		detail2getAmount();
		    		detail2calAmount();
		    	});
	    	});
		$("#detailModal input[type='radio']").each(function(){
			$(this).bind("click",function(){
				detail2getAmount();
	    		detail2calAmount();
	    	});
    	});
		
		$("#detailModal .btn-primary").bind("click",function(){
			$("#detailModal").modal('hide');
			tag=document.getElementById('detailModal_tag').value;
			document.getElementById(tag+".price").value=totalAmount;
			var index=tag.substring(tag.indexOf('[')+1,tag.indexOf(']'));
			var prex=tag.substring(0,tag.indexOf('['));
			if(prex=="smoothDoor"){
				calsmoothDoorAmount(index);
			}else{
				calRevolutionDoorAmount(index);
			}
			
			document.getElementById(tag+".remark").value=document.getElementById("p5Amount.remark").value;
			document.getElementById(tag+".detail2json").value=JSON.stringify(detail2_obj);
			document.getElementById(tag+".wholerate").value=document.getElementById("p5Amount.wholerate").value;
			document.getElementById(tag+".partsrate").value=document.getElementById("p5Amount.partsrate").value;		
		});
	}
	
}
function setRedioValue(idprex,objval){
	if(objval){
		var i =0;
		var p1rowdata=document.getElementById(idprex +'['+i+"].id");
		while(p1rowdata!=null){
			if(p1rowdata.value==objval){				
				document.getElementById(idprex +'['+i+'].ck').checked=true;
				break;
			}
			i=i+1;
			p1rowdata=document.getElementById(idprex +'['+i+"].id");
		}
	}
}
function setCheckValue(idprex,arr){
	if(arr!=null && arr.length>0){
		var i =0;
		var p1rowdata=document.getElementById(idprex+'['+i+"].id");
		while(p1rowdata!=null){
			$('#'+idprex+'['+i+'].ck').value="false";
			document.getElementById(idprex +'['+i+'].ck').checked=false;
			for(var j=0;j<arr.length;j++){
				if(arr[j]==p1rowdata.value){
					document.getElementById(idprex +'['+i+'].ck').checked=true;
					$('#'+idprex+'['+i+'].ck').value="true";
					break;
				}
			}				
			i=i+1;
			p1rowdata=document.getElementById(idprex+'['+i+"].id");
		}
	}
}




function detail2getAmount(){
	//整门金额
	whole_amount=0.00;
	detail2_obj.p1="";
	$("input[name='p1ModelE_radio']:checked").each(function(){
		var whole_id=$(this).attr("id");
		whole_amount=parseFloat(document.getElementById(whole_id.replace('.ck','.fprice')).value);
		detail2_obj.p1=document.getElementById(whole_id.replace('.ck','.id')).value;
	});
	//配件金额
	parts_amout=0.00;
	var p2array=new Array();
	var i=0;
	 $("#p2Standard input[type='checkbox']").each(function(){
	    	if (this.checked) {
	    		 var amount_id=$(this).attr('id').replace('.ck','.famount');
	    		 parts_amout=parts_amout+parseFloat(document.getElementById(amount_id).value);
	    		 p2array[i]=document.getElementById($(this).attr('id').replace('.ck','.id')).value;
	    		 i++;
	           }
	    	});
	 detail2_obj.p2=p2array;
	 i=0;
	 var p3array=new Array();
	 $("#p3Option input[type='checkbox']").each(function(){
	    	if (this.checked) {
	    		 var amount_id=$(this).attr('id').replace('.ck','.famount');
	    		 parts_amout=parts_amout+parseFloat(document.getElementById(amount_id).value);
	    		 p3array[i]=document.getElementById($(this).attr('id').replace('.ck','.id')).value;
	    		 i++;
	           }
	    	});
	 detail2_obj.p3=p3array;	
	 detail2_obj.p4="";
	 surfaceRatio=1.00;
	 $("input[name='p4Surface_radio']:checked").each(function(){
			var surface_id=$(this).attr("id");
			surfaceRatio=parseFloat(document.getElementById(surface_id.replace('.ck','.fratio')).value);
			detail2_obj.p4=document.getElementById($(this).attr('id').replace('.ck','.id')).value;
		});	
}

function detail2calAmount(){
	 var partsRate=parseFloat(document.getElementById("p5Amount.partsrate").value);
	 var wholeRate=parseFloat(document.getElementById("p5Amount.wholerate").value);
	 
	 totalAmount=(whole_amount*wholeRate+parts_amout*partsRate)*surfaceRatio;
	 document.getElementById("p5Amount.amount").value=fmoney(totalAmount,2);
}
