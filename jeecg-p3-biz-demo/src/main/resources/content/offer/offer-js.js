
function calEntryAmount(group_id,index)
{
	var price=document.getElementById("groupInfo"+group_id+"s["+index+"].price").value;
	var quantity=document.getElementById("groupInfo"+group_id+"s["+index+"].quantity").value;
	var amount=readNumber(price,0)*readNumber(quantity,0);
	document.getElementById("groupInfo"+group_id+"s["+index+"].amount").value=amount;
	sumAmount(group_id);
}

function sumAmount(group_id)
{
	var sumGroup=parseFloat(0.00);
	var i=1;
	var idAmount=document.getElementById("groupInfo"+group_id+"s["+i+"].amount");
	while (idAmount!=null)
	{
		sumGroup=sumGroup+readNumber(idAmount.value,0);
		i++;
		idAmount=document.getElementById("groupInfo"+group_id+"s["+i+"].amount");
	}
	setAmount("groupInfo"+group_id+"sSumAmount",sumGroup);
	
	var totalAmount=parseFloat(0.00);
	i=2;
	while (i<6)
	{
		totalAmount=totalAmount+readNumber(document.getElementById("groupInfo"+i+"sSumAmount").value,0);
		i++;
	}
	setAmount("totalAmount",totalAmount);
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