$(function(){
	loadBill();
	initDoor("table-xzm","XZM");
	initDoor("table-pm","PM");
	initStandard("table-frame","sdtype5");
	initStandard("table-maintenance","sdtype6");
	initStandard("table-transport","sdtype7");
	initStandard("table-installation","sdtype8");
	initStandard("table-other","sdtype9");
	
	
});
var action="";//动作：新增(add)、编辑(update)、详情(detail)
function loadBill(){
	action=$('#offer-action').val();
	if(action=="add"){
		initBillNo();
	}else{
		var id=$('#offer-id').val();
		 $.ajax({  
		        url: "wxOffer2.do?getOffers&id"+id,
		        type: "get",  
		        dataType: "json",  
		        success: function (result) {  
		        	console.log(result);
		        }  
		    });  
	}
}

function initBillNo(){	
    $.ajax({  
        url: "wxOffer2.do?getBillNo",
        type: "get",  
        dataType: "text",  
        success: function (result) {  
        	$('#fbillno').textbox('setValue',result.replace('\"','').replace('\"',''));
        }  
    });  
}
	
function initDoor(tableId,doortype){
	var entryType=tableId.split('-')[1];			
	var $this=$('#'+tableId);
	$this.datagrid({
		url: 'wxOffer2.do?getEntryList&type='+entryType+'&offerId='+$('#offer-id').val(),
		method: 'get',
		title: '旋转门及选项',			
		width: 'auto',
		height: 150,
		fitColumns: true,
		singleSelect: true,
		rownumbers:true,
		showFooter: true,
		columns:[[					
			{field:'id',title:'id',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fitemid',title:'itemid',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fitemnumber',title:'编码',width:80,align:'left',editor:{
					type:'combobox',
					options:{
						valueField:'fnumber',
						textField:'fnumber',
						method:'get',
						url:'wxBase.do?getBaseDoors&doortype='+doortype,
						required:true,								
						onSelect:function(rec){  									
                            var row = $this.datagrid('getSelected');                                  
                            var index=$this.datagrid('getRowIndex',row);
                            var editors=$this.datagrid('getEditors', index);                                  
							$(editors[1].target).textbox('setValue', rec.id);
							$(editors[3].target).textbox('setValue', rec.fname);
							$(editors[7].target).numberbox('setValue', 1);
                        }  
					}
				}},
			{field:'fitemname',title:'名称',width:80,align:'left',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fmodel',title:'规格',width:80,align:'left',editor:'textbox',hidden:'true'},
			{field:'fbranch',title:'品牌',width:80,align:'left',editor:'textbox',hidden:'true'},				
			{field:'funit',title:'单位',width:80,align:'left',hidden:'true',editor:{
					type:'combobox',
					options:{
						valueField:'fnumber',
						textField:'fnumber',
						method:'get',
						url:'wxBase.do?getBaseUnits'							
					}
				}},
			{field:'fqty',title:'数量',width:80,align:'right', sum:true,editor:{
				type:'numberbox',
				options:{
					required:true,							
					precision:2,
					onChange:function(){
						calAmount($this);
					}
				}
			}},
			{field:'fprice',title:'价格',width:80,align:'right',editor:{
				type:'numberbox',
				options:{
					required:true,							
					precision:2,
					onChange:function(){
						calAmount($this);
					}
				}
			}},
			{field:'famount',title:'金额',width:80,align:'right', sum:true,editor:{type:'numberbox',options:{disabled:'disabled',precision:2}}},
			{field:'fremark',title:'备注',width:80,align:'left',editor:'textbox'},
			{field:'fdetail2json',title:'detail2json',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'operate',title:'操作',width:80,align:'center',
				formatter:function(value, row, index){
					return '<a href="#" onclick="">明细</a>'; 
			}},
		]],
		onClickCell: onClickCell,
		onEndEdit: onEndEdit
	});
	$('#tb-'+entryType+' >a:first').click(function(){append($this)});
	$('#tb-'+entryType+' >a:nth-child(2)').click(function(){removeit($this)});
	$('#tb-'+entryType+' >a:nth-child(3)').click(function(){accept($this)});
	$('#tb-'+entryType+' >a:nth-child(4)').click(function(){reject($this)});	
	$this.datagrid('statistics');//sum
}



function initStandard(tableId,standardtype){
	var entryType=tableId.split('-')[1];			
	var $this=$('#'+tableId);
	$this.datagrid({
		url: 'wxOffer2.do?getEntryList&type='+entryType+'&offerId='+$('#offer-id').val(),
		method: 'get',
		title: $this.attr('title'),			
		width: 'auto',
		height: 150,
		fitColumns: true,
		singleSelect: true,
		rownumbers:true,
		showFooter: true,
		columns:[[					
			{field:'id',title:'id',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fitemid',title:'itemid',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fitemnumber',title:'编码',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
			{field:'fitemname',title:'名称',width:80,align:'left',editor:{
					type:'combobox',
					options:{
						valueField:'fname',
						textField:'fname',
						method:'get',
						url:'wxBase.do?getBaseStandard&standardtype='+standardtype,
						required:true,								
						onSelect:function(rec){  									
                            var row = $this.datagrid('getSelected');                                  
                            var index=$this.datagrid('getRowIndex',row);
                            var editors=$this.datagrid('getEditors', index);                                  
							$(editors[1].target).textbox('setValue', rec.id);	
							$(editors[4].target).textbox('setValue', rec.fmodel);	
							$(editors[5].target).textbox('setValue', rec.fbrand);	
							$(editors[7].target).numberbox('setValue', 1);
							$(editors[8].target).numberbox('setValue', rec.fprice);
                        }  
					}
				}},
			{field:'fmodel',title:'规格',width:80,align:'left',editor:'textbox'},
			{field:'fbranch',title:'品牌',width:80,align:'left',editor:'textbox'},				
			{field:'funit',title:'单位',width:80,align:'left',editor:{
					type:'combobox',
					options:{
						valueField:'fnumber',
						textField:'fnumber',
						method:'get',
						url:'wxBase.do?getBaseUnits'							
					}
				}},
			{field:'fqty',title:'数量',width:80,align:'right', sum:true,editor:{
				type:'numberbox',
				options:{
					required:true,							
					precision:2,
					onChange:function(){
						calAmount($this);
					}
				}
			}},
			{field:'fprice',title:'价格',width:80,align:'right',editor:{
				type:'numberbox',
				options:{
					required:true,							
					precision:2,
					onChange:function(){
						calAmount($this);
					}
				}
			}},
			{field:'famount',title:'金额',width:80,align:'right', sum:true,editor:{type:'numberbox',options:{disabled:'disabled',precision:2}}},
			{field:'fremark',title:'备注',width:80,align:'left',editor:'textbox'},
			{field:'fdetail2json',title:'detail2json',hidden:'true',editor:{type:'textbox',options:{disabled:'disabled'}}},
		]],
		onClickCell: onClickCell,
		onEndEdit: onEndEdit
	});
	$('#tb-'+entryType+' >a:first').click(function(){append($this)});
	$('#tb-'+entryType+' >a:nth-child(2)').click(function(){removeit($this)});
	$('#tb-'+entryType+' >a:nth-child(3)').click(function(){accept($this)});
	$('#tb-'+entryType+' >a:nth-child(4)').click(function(){reject($this)});	
	$this.datagrid('statistics');//sum
}



function calAmount($this){
	var row = $this.datagrid('getSelected');                                  
    var index=$this.datagrid('getRowIndex',row);
	var editors=$this.datagrid('getEditors', index);  
	var price = readNumber(editors[8].target.val(),0); 
	var qty = readNumber(editors[7].target.val(),0); 
	$(editors[9].target).numberbox('setValue', price*qty);	
}

function readNumber(obj,defaultVal){
	if(obj==undefined || !$.isNumeric(obj)){
		return defaultVal;
	}else{
		return obj;
	}
}
var editIndex = undefined;
function endEditing($this){
	if (editIndex == undefined){return true}
	if ($this.datagrid('validateRow', editIndex)){
		$this.datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickCell(index, field){
	if (editIndex != index){			
		if (endEditing($(this))){
			$(this).datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			var ed = $(this).datagrid('getEditor', {index:index,field:field});
			if (ed){
				($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
			}
			editIndex = index;
		} else {
			setTimeout(function(){
				$(this).datagrid('selectRow', editIndex);
			},0);
		}
	}
}
function onEndEdit(index, row){
	$(this).datagrid('statistics');
	/*var ed = $(this).datagrid('getEditor', {
		index: index,
		field: 'fitemnumber'
	});
	row.fitemname = $(ed.target).combobox('getText');*/
}
function append($this){
	if (endEditing($this)){			
		editIndex = $this.datagrid('getRows').length-1;
		$this.datagrid('appendRow',{findex:editIndex+2});
		$this.datagrid('selectRow', editIndex)
				.datagrid('beginEdit', editIndex);
	}
}
function removeit($this){
	if (editIndex == undefined){return}
	$this.datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;
	$this.datagrid('statistics');
}
function accept($this){
	if (endEditing($this)){
//		console.log($this.datagrid('getChanges','inserted'));
//		console.log($this.datagrid('getChanges','deleted'));
//		console.log($this.datagrid('getChanges','updated'));
		$this.datagrid('acceptChanges');
	}
}
function reject($this){
	$this.datagrid('rejectChanges');
	editIndex = undefined;
}
/*	function getChanges(){
		var rows = $('#table-xzm').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}*/

//datagrid合计扩展
$.extend($.fn.datagrid.methods, {
    statistics: function (jq) {
        var opt = $(jq).datagrid('options').columns;
        var rows = $(jq).datagrid("getRows");
        var footer = new Array();
        footer['sum'] = "";
        for (var i = 0; i < opt[0].length; i++) {
            if (opt[0][i].sum) {
                footer['sum'] = footer['sum'] + sum(opt[0][i].field) + ',';
            }
        }

        var footerObj = new Array();
		var colIndex=2;
		var tableId=$(jq).attr('id');		
        if (footer['sum'] != "") {
            var tmp = '{' + footer['sum'].substring(0, footer['sum'].length - 1) + "}";
            var obj = eval('(' + tmp + ')');
            if(tableId!='table-xzm' && tableId!='table-pm'){
				colIndex=3;
			}
            if (obj[opt[0][colIndex].field] == undefined) {
                footer['sum'] += '"' + opt[0][colIndex].field + '":"<b>合计:</b>"';
                obj = eval('({' + footer['sum'] + '})');
            } else {
                obj[opt[0][colIndex].field] = "<b>合计:</b>" + obj[opt[0][colIndex].field];
            }
            footerObj.push(obj);
        }
        if (footerObj.length > 0) {
            $(jq).datagrid('reloadFooter', footerObj);
            sumTotalAmount();
        }

        function sum(filed) {
            var sumNum = 0.00;
            for (var i = 0; i < rows.length; i++) {
            	var num=rows[i][filed];
            	if(jQuery.isNumeric(num)){
                	sumNum += Number(num);
               	}
            }
            return '"' + filed + '":"' + sumNum.toFixed(2) + '"';
        }
    }
});

function sumTotalAmount(){
	var total=0.00;
	total+=getSumAmount($('#table-xzm'));
	total+=getSumAmount($('#table-pm'));
	total+=getSumAmount($('#table-frame'));
	total+=getSumAmount($('#table-maintenance'));
	total+=getSumAmount($('#table-transport'));
	total+=getSumAmount($('#table-installation'));
	total+=getSumAmount($('#table-other'));
	$('#ftotalAmount').val(total);
	$('#ftotalAmount').text(fmoney(total,2));
}
function getSumAmount($this){
	var rows = $this.datagrid('getFooterRows');  
	if(rows==undefined){
		return 0;
	}
	var num= rows[0]["famount"];
	if(jQuery.isNumeric(num)){
    	return  Number(num);
   	}else{
   		return 0;
   	}
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


function doSave(){
	var result={};
	result.fbillno=$('#fbillno').textbox('getValue');
	result.fprojectNumber=$('#fprojectNumber').textbox('getValue');
	result.fprojectName=$('#fprojectName').textbox('getValue');
	result.fcustid=$('#fcustid').combobox('getValue');
	result.fremark=$('#fremark').textbox('getValue');
	result.ftotalAmount=$('#ftotalAmount').val();
	result.tableXzm=$("#table-xzm").datagrid("getRows");
	result.tableFrame=$("#table-frame").datagrid("getRows");
	result.tableMaintenance=$("#table-maintenance").datagrid("getRows");
	result.tableTransport=$("#table-transport").datagrid("getRows");
	result.tableInstallation=$("#table-installation").datagrid("getRows");
	result.tableOther=$("#table-other").datagrid("getRows");
	console.log(result);
}
function goBack(){
	var backurl=document.getElementById("backUrl").value;
	doUrl(backurl);
}
