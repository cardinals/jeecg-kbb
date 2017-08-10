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
	 var j=0;
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
		i++;
		$(':input, select,button,a', this).each(function(){
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
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
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i);
	});
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