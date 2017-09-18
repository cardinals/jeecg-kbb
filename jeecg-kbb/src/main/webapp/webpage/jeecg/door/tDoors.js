var comboStandardInfo;//配件列表
var comboSurfaceInfo;//表面处理列表


$(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
	       initTdDisplay(title);
		}
	});
	$(".tabs-wrap").css('width','90%');	
	
  });
 
 function initTdDisplay(title){
	 if(title=="规格型号"){
   	  //获取一般参数中的勾选
   	   var i=0;
   	   var doc=document.getElementById("tDoorParamsList["+i+"].ffeildname");
   	   while(doc!=null){
   		   var ffeildName=doc.value;
   		   if(document.getElementById("tDoorParamsList["+i+"].fshow").value=="false"){	    			  
   			   setTdDisplay(ffeildName,"none");
   		   }else{
   			   setTdDisplay(ffeildName,"table-cell");
   		   }
   		   i++;
   		   doc=document.getElementById("tDoorParamsList["+i+"].ffeildname");
   	   }
   	   //循环隐藏未勾选显示字段
	}
 }
 
 
 
 function setTdDisplay(ffeildName,strDisplay){
	 var doc=document.getElementById('tdDoorModelListCaption.'+ffeildName);
	 if(doc!=null){
		 doc.style.display=strDisplay;
	 }	 
	 var j=1;
	   var docj=document.getElementById("tdDoorModelExMap["+j +"]["+ ffeildName +"]");
	   while(docj!=null){	    				 
		   document.getElementById("tdDoorModelExMap["+j +"]["+ ffeildName +"]").style.display=strDisplay
		   j++;
		   docj=document.getElementById("tdDoorModelExMap["+j +"]["+ ffeildName +"]");
	   }
	   document.getElementById('tdDoorModelExMap[#index#]['+ffeildName+']').style.display=strDisplay;	   
 }
 
 

//初始化下标
function resetTrNum(tableId) {
	$tbody = $("#"+tableId+"");
	$tbody.find('>tr').each(function(i){		
		if(tableId!="add_tDoorsModel_table"){i++;}
		$(':input, select,button,a,td', this).each(function(){
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),val = $this.val(),
			onclick_str=$this.attr('onclick'), 
			onchange_str=$this.attr('onchange');
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					if(s>=0){
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
					}
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					if(s>=0){
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
					}
				}
				if(id=="tDoorStandardList[#index#].fname" || id=="tDoorOptionsList[#index#].fname" ){	
//					$this.attr("comboname",$this.attr('comboname').replace('#index#',i));
					if(!comboStandardInfo){
						initStandard($this);
					}else{
						comboGridStandardLoadData($this);
					}
				}	
				if(id=="tDoorSurfaceList[#index#].fname"  ){	
//					$this.attr("comboname",$this.attr('comboname').replace('#index#',i));
					if(!comboSurfaceInfo){
						initSurface($this);
					}else{
						comboGridSurfaceLoadData($this);
					}
				}	
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
			if(onchange_str!=null){
				if (onchange_str.indexOf("#index#") >= 0){
					$this.attr("onchange",onchange_str.replace(/#index#/,i));
				}else{
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i);
	});
}

function isInputId(id,prex,suffix){
	var patt1 = new RegExp(prex+"(.*)"+suffix);
	var result = patt1.test(id);
	return result;
}


//通用弹出式文件上传
function commonUpload(callback,inputId){
    $.dialog({
           content: "url:systemController.do?commonUpload",
           lock : true,
           title:"文件上传",
           zIndex:2100,
           width:700,
           height: 200,
           parent:windowapi,
           cache:false,
       ok: function(){
               var iframe = this.iframe.contentWindow;
               iframe.uploadCallback(callback,inputId);
               return true;
       },
       cancelVal: '关闭',
       cancel: function(){
       } 
   });
}
//通用弹出式文件上传-回调
function commonUploadDefaultCallBack(url,name,inputId){
	$("#"+inputId+"_href").attr('href',url).html('下载');
	$("#"+inputId).val(url);
}
function browseImages(inputId, Img) {// 图片管理器，可多个上传共用
}
function browseFiles(inputId, file) {// 文件管理器，可多个上传共用
}
function decode(value, id) {//value传入值,id接受值
	var last = value.lastIndexOf("/");
	var filename = value.substring(last + 1, value.length);
	$("#" + id).text(decodeURIComponent(filename));
}



function initStandard(input_obj){	
    $.ajax({  
        url: "tDoorsController.do?getBaseStandard",
        type: "get",  
        dataType: "json",  
        success: function (result) {  
        	comboStandardInfo = result;  
        	comboGridStandardLoadData(input_obj);
        }  
    });  
}

function comboGridStandardLoadData(input_obj){		
	input_obj.combogrid({      
	    panelWidth:300, 
	    panelHeight:300,
	    idField:'fname',  
	    textField:'fname', 
		onSelect: function (rowIndex, rowData){ 
			var $this = $(this),name = $this.attr('name');
			if(name==undefined){return;}
			var old_item_id=document.getElementById(name).value;
			if(old_item_id!=rowData.id){
				document.getElementById(name.replace('fname','fmodel')).value=rowData.fmodel;
				document.getElementById(name.replace('fname','fbrand')).value=rowData.fbrand;
//				document.getElementById(name.replace('fnumber','id')).value=rowData.id;
				document.getElementById(name).value=rowData.fname;
				document.getElementById(name.replace('fname','fprice')).value=rowData.fprice;
			}
		},
	    columns:[[          
//	    	 	  {field:'id',title:'ID',width:60,hidden:true},     
//	              {field:'fnumber',title:'编码',width:100},          
	              {field:'fname',title:'名称',width:200},
	              {field:'fmodel',title:'规格型号',width:150},
	              {field:'fbrand',title:'品牌',width:150}
	              ]]
	});  

	input_obj.combogrid('grid').datagrid("loadData", comboStandardInfo);  
	input_obj.combogrid('grid').attr('name',input_obj.attr('id'));
//	var valt=document.getElementById(input_obj.attr('id').replace('fnumber','id')).value;
	var valt=input_obj.val();
	if(valt!=null){
		input_obj.combogrid("setText",valt);
	}	
}

function calAmount(prex)
{
	var price=document.getElementById(prex+".fprice").value;
	var quantity=document.getElementById(prex+".fqty").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById(prex+".famount").value=amount;

}

function readNumber(n,m) {
   if(!isNaN(parseFloat(n)) && isFinite(n))
	   return parseFloat(n);
   else
	   return parseFloat(m);
}








function initSurface(input_obj){	
    $.ajax({  
        url: "tDoorsController.do?getBaseSurface",
        type: "get",  
        dataType: "json",  
        success: function (result) {  
        	comboSurfaceInfo = result;  
        	comboGridSurfaceLoadData(input_obj);
        }  
    });  
}

function comboGridSurfaceLoadData(input_obj){		
	input_obj.combogrid({      
	    panelWidth:300, 
	    panelHeight:300,
	    idField:'fname',
	    textField:'fname', 
		onSelect: function (rowIndex, rowData){ 
			var $this = $(this),name = $this.attr('name');
			if(name==undefined){return;}
			var old_item_id=document.getElementById(name).value;
			if(old_item_id!=rowData.id){
//				document.getElementById(name.replace('fnumber','fname')).value=rowData.fname;
//				document.getElementById(name.replace('fnumber','id')).value=rowData.id;
				document.getElementById(name).value=rowData.fname;
//				document.getElementById(name.replace('fnumber','fratio')).value=rowData.fratio;
			}
		},
	    columns:[[          
//	    	 	  {field:'id',title:'ID',width:60,hidden:true},     
//	              {field:'fnumber',title:'编码',width:100},          
	              {field:'fname',title:'名称',width:200}
	              ]]
	});  

	input_obj.combogrid('grid').datagrid("loadData", comboSurfaceInfo);  
	input_obj.combogrid('grid').attr('name',input_obj.attr('id'));
	var valt=input_obj.val();
	if(valt!=null){
		input_obj.combogrid("setText",valt);
	}		
}




