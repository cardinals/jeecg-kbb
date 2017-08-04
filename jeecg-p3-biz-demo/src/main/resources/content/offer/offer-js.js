
var comboDoorInfo;
$(function(){
	$demo = $("#dailogForm").Validform();
	$demo.config({
		tiptype:function(msg,o,cssctl){
			if(o.type == 3){//验证失败的时候弹出框当中显示相关的信息
//				alert(msg);
			}
		}
	});
	
	$("#revolution_add").click(function(){
		var tr = $("#add_revolution_door_template tr").clone();
		$("#revolution_table tbody").append(tr);
		resetTrNum('revolution_table');
	});
	
	
	$('#detailModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget); // Button that triggered the modal
		  var tag = button.data('tag'); // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.			  
		  var modal = $(this);
		  var item_id=document.getElementById(tag).value;
		  var url_input=document.getElementById("wxUrl");
		  $.ajax({  
		        url: url_input.value+"wxOffer.do?goDetail&id=1&item_id="+item_id,  
		        type: "get",  
		        dataType: "html",  
		        success: function (result) {  
		        	/*document.getElementById("modal-dialog-body").innerHTML=result;*/
		        	$("#modal-dialog-body").html(result);
		        }
		    });		  
		})
});

function initBaseDoors(input_obj){
var url_input=document.getElementById("wxUrl");
if(url_input!=null){
    $.ajax({  
        url: url_input.value+"wxBase.do?getBaseDoors",  
        type: "get",  
        dataType: "json",  
        success: function (result) {  
        	comboDoorInfo = result;  
        	bindComboGridDoor(input_obj);
        }  
    });  
}
}

//初始化下标
function resetTrNum(tableId) {
	$tbody = $("#"+tableId+" tbody");
	$tbody.find('>tr').each(function(i){
		i++;		
		$(this).attr("name","revolutionDoor["+i+"]");
		$(this).attr("id","revolutionDoor["+i+"]");
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
				if(name=="revolutionDoor[#index#].findex"){
					$this.attr("value",i);
				}else if(name=="revolutionDoor[#index#].item_number"){
					if(!comboDoorInfo){
						initBaseDoors($this);
					}else{
					bindComboGridDoor($this);
					}
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
				if(id=="revolutionDoor[#index#].findex"){
					$this.attr("value",i);
				}else if(name=="revolutionDoor[#index#].item_number"){
					if(!comboDoorInfo){
						initBaseDoors($this);
					}else{
					bindComboGridDoor($this);
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

function bindComboGridDoor(input_obj){
	input_obj.combogrid({      
    panelWidth:180,     
    //value:'1', 此处可以设置默认值，对应idField属性列的值   
    idField:'id',  
    textField:'fname',  
//    onLoadSuccess:function(old){  
//        //console.log(old);   //old为数据对象格式为：{total:2,rows:Array[2]}  
//        $(input_name).combogrid('setValue',old.rows[0].name);//设置文本框的默认值为第一条，下标从0开始  
//    },  
	onSelect: function (rowIndex, rowData){ 
		var $this = $(this),name = $this.attr('name');		
		document.getElementById(name.replace('item_number','item_name')).value=rowData.fname;
		document.getElementById(name.replace('item_number','item_id')).value=rowData.id;
		document.getElementById(name).value=rowData.fnumber;		
	},
    columns:[[          
    	 	  {field:'id',title:'ID',width:60,hidden:true},     
              {field:'fnumber',title:'编码',width:60},          
              {field:'fname',title:'名称',width:100}          
              ]]});  
           //combogrid加载本地数据，要调用datagrid的loadData方法，注意第二个参数格式  
	input_obj.combogrid('grid').datagrid("loadData", comboDoorInfo);  
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






