
var comboDoorInfo;
var comboSmoothDoorInfo;
$(function(){
	$demo = $("#dailogForm").Validform();
	$demo.config({
		tiptype:function(msg,o,cssctl){
			if(o.type == 3){//验证失败的时候弹出框当中显示相关的信息
//				alert(msg);
			}
		}
	});
	initFileUploader();
	
	
	$("#revolution_add").click(function(){
		var tr = $("#add_revolution_door_template tr").clone();
		$("#revolution_table tbody").append(tr);
		resetTrNum('revolution_table','revolutionDoor');
	});
	$("#smooth_add").click(function(){
		var tr = $("#add_smooth_door_template tr").clone();
		$("#smooth_table tbody").append(tr);
		resetTrNum('smooth_table','smoothDoor');
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
		})
});
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
//	uploader.on( 'fileQueued', function( file ) {
//		$("#thelist").append( '<div id="' + file.id + '" class="item">' +
//	        '<div class="state">'+file.name+'---等待上传...</div>' +
//	    '</div>' );
//	}); 
//	
//	//文件上传过程中创建进度条实时显示.
//	 uploader.on( 'uploadProgress', function( file, percentage ) {
//	    var $li = $( '#'+file.id ),
//	        $percent = $li.find('.progress .progress-bar');
//	    // 避免重复创建
//	    if ( !$percent.length ) {
//	        $percent = $('<div class="progress progress-striped active">' +
//	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
//	          '</div>' +
//	        '</div>').appendTo( $li ).find('.progress-bar');
//	    }
//	    $li.find('div.state').html(file.name+'---上传中');
//	    $percent.css( 'width', percentage * 100 + '%' );
//	});
	
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
    $.ajax({  
        url: url_input.value+"wxBase.do?getBaseDoors&doortype="+prex,  
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
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),val = $this.val(),
			onclick_str=$this.attr('onclick'), 
			onchange_str=$this.attr('onchange');
			data_tag=$this.attr('data-tag');
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
				if(name==prex+"[#index#].findex"){
					$this.attr("value",i);
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
				}
				if(id==prex+"[#index#].findex"){
					$this.attr("value",i);
				}else if(name==prex+"[#index#].item_number"){
					if(prex=="smoothDoor"){						
						if(!comboSmoothDoorInfo){
							initBaseDoors($this,prex);
						}else{
							bindComboGridDoor($this,prex);
						}
					}else{
						if(!comboDoorInfo){
							initBaseDoors($this,prex);
						}else{
							bindComboGridDoor($this,prex);
						}
					}
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/,i));
				}else{
				}
			}
			if(onchange_str!=null){
				if (onchange_str.indexOf("#index#") >= 0){
					$this.attr("onchange",onchange_str.replace(/#index#/,i));
				}else{
				}
			}
			if(data_tag!=null){
				if (data_tag.indexOf("#index#") >= 0){
					$this.attr("data-tag",data_tag.replace(/#index#/,i));
				}else{
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i);	
	});
}

function bindComboGridDoor(input_obj,prex){
	input_obj.combogrid({      
    panelWidth:400, 
    panelHeight:300,
    //value:'1', 此处可以设置默认值，对应idField属性列的值   
    idField:'id',  
    textField:'fname',  
//    onLoadSuccess:function(old){  
//        //console.log(old);   //old为数据对象格式为：{total:2,rows:Array[2]}  
//        $(input_name).combogrid('setValue',old.rows[0].name);//设置文本框的默认值为第一条，下标从0开始  
//    },  
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
	sumAmount(group_id);
}

function calsmoothDoorAmount(index)
{
	var price=document.getElementById("smoothDoor["+index +"].price").value;
	var quantity=document.getElementById("smoothDoor["+index +"].quantity").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById("smoothDoor["+index +"].amount").value=amount;
	sumAmount("2");
}

function calRevolutionDoorAmount(index)
{
	var price=document.getElementById("revolutionDoor["+index +"].price").value;
	var quantity=document.getElementById("revolutionDoor["+index +"].quantity").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById("revolutionDoor["+index +"].amount").value=amount;
	sumAmount("1");
}

function sumAmount(group_id)
{
	var sumGroup=parseFloat(0.00);
	var i=1;
	if(group_id=="1"){	
		var idAmount=document.getElementById("revolutionDoor["+i+"].amount");
		while (idAmount!=null)
		{
			sumGroup=sumGroup+readNumber(idAmount.value,0);
			i++;
			idAmount=document.getElementById("revolutionDoor["+i+"].amount");
		}
	}else if(group_id=="2"){
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
		totalAmount=totalAmount+readNumber(document.getElementById("groupInfo"+i+"sSumAmount").value,0);
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
	//					document.getElementById("attachment["+ i +"].id").value=file.id;
					}else if(id=="attachment[#index#].filename"){
						$(this).val(file.name);
	//					document.getElementById("attachment["+ i +"].filename").value=file.name;
					}else if(id=="attachment[#index#].path"){
						$(this).val(rsp.obj);
	//					document.getElementById("attachment["+ i +"].path").value=rsp.obj;
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
