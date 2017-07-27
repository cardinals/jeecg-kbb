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
});


//初始化下标
function resetTrNum(tableId) {
	$tbody = $("#"+tableId+" tbody");
	$tbody.find('>tr').each(function(i){	
		$(this).attr("name","revolutionDoor["+i+"]");
		$(this).attr("id","revolutionDoor["+i+"]");
		$(':input, select,button,a', this).each(function(){			
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),val = $this.val(),
			onclick_str=$this.attr('onclick'), 
			onchange_str=$this.attr('onchange');
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
			if(onchange_str!=null){
				if (onchange_str.indexOf("#index#") >= 0){
					$this.attr("onchange",onchange_str.replace(/#index#/g,i));
				}else{
				}
			}
		});
		$(this).find('div[name=\'xh\']').html(i+1);
		$(this).find('input[name=\'revolutionDoor['+ (i+1) +'].index\']').val(i+1);		
	});
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
		i=0;
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