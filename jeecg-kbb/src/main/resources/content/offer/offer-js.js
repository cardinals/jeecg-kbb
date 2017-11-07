var comboStandardInfo3;
var comboStandardInfo4;
var comboStandardInfo5;
var comboDoorInfo;
var comboSmoothDoorInfo;
$(function(){
	$demo = $("#dailogForm").Validform({
		callback:function(data){
			//返回数据data是json对象，{"info":"demo info","status":"y"}
			//info: 输出提示信息;
			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
			//你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
			//ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；
	 
			//这里执行回调操作;
			//注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
			console.log(data);
		}
	});
	$demo.config({
		tiptype:function(msg,o,cssctl){
			if(o.type == 3){//验证失败的时候弹出框当中显示相关的信息
//				alert(msg);
			}
		}
	});
	
	initFileUploader();	
	
	$("#revolutionDoor_add").click(function(){
		var tr = $("#add_revolution_door_template tr").clone();
		$("#revolutionDoor_table tbody").append(tr);
		resetTrNum('revolutionDoor_table','revolutionDoor');
	});
	$("#smoothDoor_add").click(function(){
		var tr = $("#add_smooth_door_template tr").clone();
		$("#smoothDoor_table tbody").append(tr);
		resetTrNum('smoothDoor_table','smoothDoor');
	});
	
	$("#groupInfo3s_add").click(function(){		
		apendStandard("groupInfo3s");
	});
	
	$("#groupInfo4s_add").click(function(){		
		apendStandard("groupInfo4s");
	});
	
	$("#groupInfo5s_add").click(function(){		
		apendStandard("groupInfo5s");
	});
	
	
	$('#detailModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget); // Button that triggered the modal
		  var tag = button.data('tag'); // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.			  
		  //		  var modal = $(this);
		  var wxOffer_id=document.getElementById("wxOffer_id").value;
		  var url_input=document.getElementById("wxUrl");
		  $.ajax({  
		        url: url_input.value+"wxOffer.do?goDetail2&id="+wxOffer_id+"&item_id="+document.getElementById(tag+".item_id").value,  
		        type: "get",  
		        dataType: "html",  
		        success: function (result) {
		        	$("#modal-dialog-body").html(result);
		        	document.getElementById("detailModal_tag").value=tag;
		        	document.getElementById("detailModal_action").value=button.data('action');
		        	initDialog();
		        }
		    });		  
	});
	
});

function showDetailModel(tag,actionStr){
	  var wxOffer_id=document.getElementById("wxOffer_id").value;
	  var url_input=document.getElementById("wxUrl");
	  $.ajax({  
	        url: url_input.value+"wxOffer.do?goDetail2&id="+wxOffer_id+"&item_id="+document.getElementById(tag+".item_id").value
	        +"&tag="+tag+"&actionStr="+actionStr,  
	        type: "get",  
	        dataType: "html",  
	        success: function (result) {
	        	$("#modal-dialog-body",window.parent.document).html(result);
//	        	initDialog();
	        	$("#detailModal",window.parent.document).modal();
	        	initDialog();
	        }
	    });		
}

function apendStandard(prex){
	var $tr = $("#add_standard_template tr").clone();
	$tr.find('input,button').each(function(){
		var name=$(this).attr("id");
		$(this).attr("name",prex+"[#index#]."+name);
		$(this).attr("id",prex+"[#index#]."+name);
		if(name=="price" || name=="quantity"){
			var group_id=prex.substr(prex.length-2,1);
			$(this).attr("onChange","calEntryAmount('"+group_id+"','#index#')");
		}
	});
	$("#"+prex+"_table tbody").append($tr);		
	resetTrNum(prex+'_table',prex);
}


function initStandard(){
	//初始化配件选择
	var j=3;
	var comboStandardInfo={};
	for(j=3;j<6;j++){
		if(3==j){
			comboStandardInfo=comboStandardInfo3;
		}else if(4==j){
			comboStandardInfo=comboStandardInfo4;
		}else if(5==j){
			comboStandardInfo=comboStandardInfo5;
		}
		var i=1;
		var obj=document.getElementById("groupInfo"+j+"s["+i+"].standard");
		while(obj!=null){
			initComboStandardInfo(obj.getAttribute("id"),"groupInfo"+j+"s","sdtype"+(j+2),comboStandardInfo);
			i++;
			obj=document.getElementById("groupInfo"+j+"s["+i+"].standard");
		}
	}
}

function initFileUploader(){
	var url_input=document.getElementById("wxUrl");
	var BASE_URL=url_input.value;
	var urlc= BASE_URL+'systemController/filedeal.do';
	var uploader = WebUploader.create({
	    // swf文件路径
	    swf: BASE_URL+'/plug-in/webuploader/Uploader.swf',
	    // 文件接收服务端。
		server: urlc,
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#attachment_add',
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
	    //指明参数名称，后台也用这个参数接收文件
	    duplicate: false,
	    auto: true,
	    //每次上传附带参数
	    formData:{"isup":"1"}
	 
	});
	
	uploader.on( 'uploadSuccess', function(file,rsp) {
//	    $( '#'+file.id ).find('div.state').html(file.name+'---上传成功!');
		var tr = $("#add_attachment_template li").clone();
		$("#attachment_list ul").append(tr);
		resetLiNum('attachment_list',file,rsp);	   
	});
	uploader.on( 'uploadError', function( file) {
//	    $( '#'+file.id ).find('div.state').html(file.name+'---上传出错');
	    $.messager.show({  
   	        title : '消息提醒',  
   	        msg : file.name+"上传出错",  
   	        timeout : 1000 * 5
   	     });
	});
	uploader.on( 'uploadComplete', function( file ) {
//	   $( '#'+file.id ).find('.progress').fadeOut('slow');
		uploader.removeFile(file);
	});
}

function initBaseDoors(input_obj,prex){
var url_input=document.getElementById("wxUrl");
if(url_input!=null){
	var doortype="XZM";
	if(prex=="smoothDoor"){
		doortype="PM";
	}
    $.ajax({  
        url: url_input.value+"wxBase.do?getBaseDoors&doortype="+doortype,  
        type: "get",  	
        dataType: "json",  
        success: function (result) {  
        	if(prex=="smoothDoor"){
        		comboSmoothDoorInfo=result;
        	}else{
        		comboDoorInfo = result;  
        	}
        	bindComboGridDoor(input_obj,prex);
        }  
    });  
	}
}

//初始化下标
function resetTrNum(tableId,prex) {
	$tbody = $("#"+tableId+" tbody");
	$tbody.find('>tr').each(function(i){
		i++;		
		$(this).attr("name",prex+"["+i+"]");
		$(this).attr("id",prex+"["+i+"]");
		$(':input, select,button,a', this).each(function(){			
			var $this = $(this);			
			replaceIndex($this,prex,"name",i);
			replaceIndex($this,prex,"id",i);
			replaceIndex($this,prex,"data-tag",i);
			replaceIndex($this,prex,"onclick",i);
			replaceIndex($this,prex,"onChange",i);
			replaceIndex($this,prex,"comboname",i);			
		});
		$(this).find('div[name=\'xh\']').html(i);	
	});
}
function replaceIndex($this,prex,attrStr,i){
	var attr = $this.attr(attrStr);
	if(attr!=null){
		if (attr.indexOf("#index#") >= 0){
			$this.attr(attrStr,attr.replace('#index#',i));
		}else{
			var s = attr.indexOf("[");
			var e = attr.indexOf("]");
			var oldIndex = attr.substring(s,e+1);
			if(oldIndex!=""){
				var newName=attr.replace(oldIndex,"["+i+"]");
				$this.attr(attrStr,newName);
				if(attrStr=="id" && attr.substr(e+2)=="standard"){					
					$("table[name='"+attr+"']").attr('name',newName);
				}
			}			
		}
		if(attr==prex+"[#index#].findex"){
			$this.attr("value",i);
		}else if(attrStr=="id" && attr==prex+"[#index#].item_number"){
			//只通过id绑定，其他的不管
			if(prex=="smoothDoor"){						
				if(!comboSmoothDoorInfo){
					initBaseDoors($this,prex);
				}else{
					bindComboGridDoor($this,prex);
				}
			}else if(prex=="revolutionDoor"){
				if(!comboDoorInfo){
					initBaseDoors($this,prex);
				}else{
					bindComboGridDoor($this,prex);
				}
			}			
		}else if(attrStr=="id" && attr==prex+"[#index#].standard"){			
			if(prex=="groupInfo3s"){
				initComboStandardInfo($this.attr('id'),prex,"sdtype5",comboStandardInfo3);
			}else if(prex=="groupInfo4s"){
				initComboStandardInfo($this.attr('id'),prex,"sdtype6",comboStandardInfo4);
			}else if(prex=="groupInfo5s"){
				initComboStandardInfo($this.attr('id'),prex,"sdtype7",comboStandardInfo5);
			}
		}
	}
}


function bindComboGridDoor(input_obj,prex){
	input_obj.combogrid({
	width:180,
    panelWidth:400, 
    panelHeight:300, 
    idField:'id',  
    textField:'fnumber',
	onSelect: function (rowIndex, rowData){ 		
		var $this = $(this),name = $this.attr('name');		
		var old_item_id=document.getElementById(name.replace('item_number','item_id')).value;
		if(old_item_id!=rowData.id){
			document.getElementById(name.replace('item_number','item_name')).value=rowData.fname;
			document.getElementById(name.replace('item_number','item_id')).value=rowData.id;
			document.getElementById(name).value=rowData.fnumber;
			document.getElementById(name.replace('item_number','price')).value="0";
			document.getElementById(name.replace('item_number','wholerate')).value="1.0";
			document.getElementById(name.replace('item_number','partsrate')).value="1.0";
			document.getElementById(name.replace('item_number','detail2json')).value="";
		}
	},
    columns:[[          
    	 	  {field:'id',title:'ID',width:60,hidden:true},     
              {field:'fnumber',title:'编码',width:100},          
              {field:'fname',title:'名称',width:200}          
              ]]});  
           //combogrid加载本地数据，要调用datagrid的loadData方法，注意第二个参数格式  
	if(prex=="smoothDoor"){
		input_obj.combogrid('grid').datagrid("loadData", comboSmoothDoorInfo);  
	}else{
		input_obj.combogrid('grid').datagrid("loadData", comboDoorInfo);  
	}
	input_obj.combogrid('grid').attr('name',input_obj.attr('id'));
	var valt=document.getElementById(input_obj.attr('id').replace('item_number','item_id')).value;
	if(valt!=null){
		input_obj.combogrid('setValue',valt);
	}
}

function calEntryAmount(group_id,index)
{
	var price=document.getElementById("groupInfo"+group_id+"s["+index+"].price").value;
	var quantity=document.getElementById("groupInfo"+group_id+"s["+index+"].quantity").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById("groupInfo"+group_id+"s["+index+"].amount").value=amount;
	sumAmount(parseInt(group_id));
}

function calSumDoorAmount(obj)
{	var inputId="";
	try{
		 inputId=obj.getAttribute('id');
	}catch(e){
		inputId=obj.attr('id');
	}
	var s = inputId.indexOf("[");
	var e = inputId.indexOf("]");
	var index = inputId.substring(s+1,e);	
	var prex=inputId.substring(0,s);	
	var price=document.getElementById(prex+"["+index +"].price").value;
	var quantity=document.getElementById(prex+"["+index +"].quantity").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById(prex+"["+index +"].amount").value=amount;
	if(prex=="revolutionDoor"){
		sumAmount(1);
	}else{
		sumAmount(2);
	}
}

function sumAmount(group_id)
{
	var sumGroup=parseFloat(0.00);
	var i=1;
	if(group_id==1){	
		var idAmount=document.getElementById("revolutionDoor["+i+"].amount");
		while (idAmount!=null)
		{
			sumGroup=sumGroup+readNumber(idAmount.value,0);
			i++;
			idAmount=document.getElementById("revolutionDoor["+i+"].amount");
		}
	}else if(group_id==2){
		var idAmount=document.getElementById("smoothDoor["+i+"].amount");
		while (idAmount!=null)
		{
			sumGroup=sumGroup+readNumber(idAmount.value,0);
			i++;
			idAmount=document.getElementById("smoothDoor["+i+"].amount");
		}
	}else{
		var idAmount=document.getElementById("groupInfo"+group_id+"s["+i+"].amount");
		while (idAmount!=null)
		{
			sumGroup=sumGroup+readNumber(idAmount.value,0);
			i++;
			idAmount=document.getElementById("groupInfo"+group_id+"s["+i+"].amount");
		}
	}	
	setAmount("groupInfo"+group_id+"sSumAmount",sumGroup);
	
	var totalAmount=parseFloat(0.00);
	i=1;
	while (i<6)
	{
		if(document.getElementById("groupInfo"+i+"sSumAmount")!=null){
			totalAmount=totalAmount+readNumber(document.getElementById("groupInfo"+i+"sSumAmount").value,0);
		}
		i++;
	}
	setAmount("famount",totalAmount);
}


function setAmount(id,value){
	document.getElementById(id).value=value;
	document.getElementById(id+"Display").innerHTML=fmoney(value,2);
}
function fmoney(s, n)  
{  
   n = n > 0 && n <= 20 ? n : 2;  
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
   var l = s.split(".")[0].split("").reverse(),  
   r = s.split(".")[1];  
   t = "";  
   for(i = 0; i < l.length; i ++ )  
   {  
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
   }  
   return t.split("").reverse().join("") + "." + r;  
} 

function readNumber(n,m) {
	   if(!isNaN(parseFloat(n)) && isFinite(n))
		   return parseFloat(n);
	   else
		   return parseFloat(m);
	}


//初始化下标
function resetLiNum(listId,file,rsp) {
	$ullist = $("#"+listId+" ul");
	var prex="attachment";
	$ullist.find('>li').each(function(i){		
		$(this).attr("id",prex+"["+i+"]");
		$(':input, button', this).each(function(){			
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),
			onclick_str=$this.attr('onclick');
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}
				if(file!=null && file!=undefined){
					if(id=="attachment[#index#].fileid"){
						$(this).val(file.id);
					}else if(id=="attachment[#index#].filename"){
						$(this).val(file.name);
					}else if(id=="attachment[#index#].path"){
						$(this).val(rsp.obj);
					}else if(id=="attachment[#index#].link"){
						$(this).html(file.name);
					}
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/,i));
				}else{
				}
			}				
		});
	});
}


function removeAttachment(index){
	var url_input=document.getElementById("wxUrl");
	var id=document.getElementById("wxOffer_id").value;	
	var fileid=document.getElementById("attachment["+ index +"].fileid").value;	
	var filename=document.getElementById("attachment["+ index +"].filename").value;
	if(url_input!=null){
	    $.ajax({
	        url: url_input.value+"wxOffer.do?delAttachment&id="+ id +"&fileid="+fileid, 
	        type: "post",  
	        dataType: "json",  
	        success: function (result) {  
	        	if(result.success){	        		
	        		deleteAttachmentFile(index);
	        	}else{
	        		 $.messager.show({  
        	   	        title : '消息提醒',  
        	   	        msg : filename+result.msg,  
        	   	        timeout : 1000 * 5
        	   	     });
	        	}
	        }  
	    });  
	}
}

function deleteAttachmentFile(index){
	var url_input=document.getElementById("wxUrl");
	var path=document.getElementById("attachment["+ index +"].path").value;
	var filename=document.getElementById("attachment["+ index +"].filename").value;
	if(url_input!=null){
		var data={};
		data.isdel=1;
		data.path=$.base64.btoa(path,true);
	    $.ajax({
	        url: url_input.value+"systemController/filedeal.do", 
	        type: "post",  
	        data:data,
	        dataType: "json",  
	        success: function (result) {  
	        	if(result.success){	        		
	        		document.getElementById('attachment['+ index +']').remove();
	        	}else{
	        		 $.messager.show({  
        	   	        title : '消息提醒',  
        	   	        msg : filename+result.msg,  
        	   	        timeout : 1000 * 5
        	   	     });
	        	}
	        }  
	    });  
	}
}

function downloadAttachment(index){
	var wxUrl=document.getElementById("wxUrl").value;
	var path=document.getElementById("attachment["+ index +"].path").value;
	doUrl(wxUrl+"systemController/showOrDownByurl.do?down=1&dbPath="+$.base64.btoa(path,true));	
}

/*详情使用*/
function goback(){
	var wxurl=document.getElementById("wxUrl").value;
	var backurl=document.getElementById("backUrl").value;
	doUrl(wxurl+backurl);
}

//	
//配件选择		begin
//
function initComboStandardInfo(input_obj_id,prex,standardtype,comboStandardInfo){
	var input_obj=jQueryObj(input_obj_id);
	if(!comboStandardInfo){
		var url_input=document.getElementById("wxUrl");
		if(url_input!=null){
		    $.ajax({  
		        url: url_input.value+"wxBase.do?getBaseStandard&standardtype="+standardtype,  
		        type: "get",  	
		        dataType: "json",  
		        success: function (result) {	        	
		        	comboStandardInfo = result;  
		        	bindStandardInfo(input_obj,prex,comboStandardInfo);
		        }  
		    });  
		}
	}else{
		bindStandardInfo(input_obj,prex,comboStandardInfo);
	}
}

function bindStandardInfo(input_obj,prex,comboStandardInfo){	
	input_obj.combogrid({
		width:180,
	    panelWidth:400, 
	    panelHeight:300,
	    style:"padding:0",
	    idField:'fname',  
	    textField:'fname',  
		onSelect: function (rowIndex, rowData){ 
			var $this = $(this),name = $this.attr('name');
			if(name==undefined){
				return;
			}			
			var old_item_id=document.getElementById(name.replace('standard','standard_id')).value;
			if(old_item_id!=rowData.id){			
				document.getElementById(name).value=rowData.fname;
				document.getElementById(name.replace('standard','standard_id')).value=rowData.id;
				document.getElementById(name.replace('standard','model')).value=rowData.fmodel;
				document.getElementById(name.replace('standard','brand')).value=rowData.fbrand;
				document.getElementById(name.replace('standard','quantity')).value="1";
				document.getElementById(name.replace('standard','price')).value=rowData.fprice;
				document.getElementById(name.replace('standard','unit')).value=rowData.funit;
				var s = name.indexOf("[");
				var e = name.indexOf("]");
				var index = name.substring(s+1,e);
				calEntryAmount(name.substring(9,10),index);
			}
		},
	    columns:[[          
	    	 	  {field:'id',title:'ID',width:60,hidden:true}, 
	              {field:'fname',title:'名称',width:200},  
	    	 	  {field:'fmodel',title:'规格型号',width:200},
	    	 	  {field:'fbrand',title:'品牌',width:100},
	    	 	  {field:'funit',title:'单位',width:50},
	    	 	  {field:'fprice',title:'价格',width:100,hidden:true}
	              ]]
	});  
  
	input_obj.combogrid('grid').datagrid("loadData", comboStandardInfo);
	input_obj.combogrid('grid').attr('name',input_obj.attr('id'));
	var valt=input_obj.val();
	if(valt!=null){
		input_obj.combogrid('setValue',valt);
	}
}



//
//配件选择		end
//
function onDeleteRow(obj){
	var btnId=obj.getAttribute('id');//revolutionDoor[#index#].btnDel
	var s = btnId.indexOf("[");
	var e = btnId.indexOf("]");
	var index = btnId.substring(s+1,e);
	var prex=btnId.substring(0,s);
	//1、remove revolutionDoor[#index#]
	jQueryObj(btnId.split(".")[0]).remove();
	//2、sumAmount(#group_id#)
	if(prex=="smoothDoor"){						
		sumAmount(2);
	}else if(prex=="revolutionDoor"){
		sumAmount(1);
	}else if(prex=="groupInfo3s"){
		sumAmount(3);
	}else if(prex=="groupInfo4s"){
		sumAmount(4);
	}else if(prex=="groupInfo5s"){
		sumAmount(5);
	}	
	//3、resetTrNum(#tableName#,#prex#)
	tableName=prex+"_table";
	resetTrNum(tableName,prex);
}

function jQueryObj(id){	
	return $('[id="'+ id +'"]');
}