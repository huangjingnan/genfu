<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>order-item</title>
<script type="text/javascript">
jQuery("#k_process").jqGrid({
	url:'${ctx}/kitchen-process.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	multiselect: true,
   	caption: 'process',
    pager: '#pk_process'
});
jQuery("#k_process").jqGrid('navGrid','#pk_process',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-process/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-process',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
jQuery("#k_fruition").jqGrid({
	url:'${ctx}/kitchen-fruition.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
    caption: 'fruition',
    pager: '#pk_fruition'
});
jQuery("#k_fruition").jqGrid('navGrid','#pk_fruition',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-fruition/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-fruition',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});		
jQuery("#k_open").jqGrid({
	url:'${ctx}/kitchen-open.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	multiselect: true,
    caption: 'open',
    pager: '#pk_open'
});
jQuery("#k_open").jqGrid('navGrid','#pk_open',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-process/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-process',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
jQuery("#k_waiting").jqGrid({
	url:'${ctx}/kitchen-waiting.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   	   		formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
    caption: 'waiting',
    pager: '#pk_waiting'
});
jQuery("#k_waiting").jqGrid('navGrid','#pk_waiting',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-process/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-process',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
function dishDatefmt(cellvalue, options, rowObject)
{
	//debugger;
	if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
		return new Date(cellvalue.time).toLocaleString("ca");
	}else{
		return "";
	}
	//
	//return cellvalue.toLocaleString("ca");
	//return 0;
}		
/* var mydata1 = [
    {id1:"1",name1:"test1",values1:'One'},
    {id1:"2",name1:"test2",values1:'Two'},
    {id1:"3",name1:"test3",values1:'Three'}
];
var mydata2 = [
    {id2:"11",name2:"test11",values2:'One1'},
    {id2:"21",name2:"test21",values2:'Two1'},
    {id2:"31",name2:"test31",values2:'Three1'}
];
var mydata3 = [
    {id3:"12",name3:"test12",values3:'One2'},
    {id3:"22",name3:"test22",values3:'Two2'},
    {id3:"32",name3:"test32",values3:'Three2'}
]; */
/*for (var i = 0; i <= mydata1.length; i++) {
    jQuery("#k_process").jqGrid('addRowData',i + 1, mydata1[i]);
    jQuery("#k_open").jqGrid('addRowData',i + 1, mydata2[i]);
    jQuery("#k_waiting").jqGrid('addRowData',i + 1, mydata3[i]);
}*/
jQuery("#k_process").jqGrid('gridDnD',{connectWith:'#k_open,#k_waiting',ondrop:testDrop});
jQuery("#k_open").jqGrid('gridDnD',{connectWith:'#k_process',ondrop:testDrop});
jQuery("#k_waiting").jqGrid('gridDnD',{connectWith:'#k_fruition',ondrop:testDrop});

function testDrop(jG,opts){
	//debugger;
	if(jG.target.id == 'k_waiting'){
		kWaiting(opts.draggable[0].id,'');
		//jQuery("#k_waiting").trigger("reloadGrid");
	}else if(jG.target.id == 'k_open'){
		kOpen(opts.draggable[0].id,'');
		//jQuery("#k_open").trigger("reloadGrid");
	}else if(jG.target.id == 'k_process'){
		kProcess(opts.draggable[0].id,'');
		//jQuery("#k_process").trigger("reloadGrid");
	}else if(jG.target.id == 'k_fruition'){
		kFruition(opts.draggable[0].id,'');
		//jQuery("#k_process").trigger("reloadGrid");
	}
	//alert(jG.target.id);
	//alert(opts.draggable[0].id);
}
var ctxk = '${ctx}';
function kWaiting(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-waiting/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_waiting").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kOpen(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-open/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_open").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kProcess(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-process/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_open").trigger("reloadGrid");
        	jQuery("#k_process").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kFruition(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-fruition/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_waiting").trigger("reloadGrid");
        	jQuery("#k_fruition").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}
</script>
<s:head />
</head>
<body>
<table>
    <tbody>
        <tr>
            <td><table id="k_fruition"></table><div id="pk_fruition"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td><table id="k_waiting"></table><div id="pk_waiting"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td><table id="k_process"></table><div id="pk_process"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td align= "center"> \/ </td>
        </tr>
        <tr>
            <td>
                <table id="k_open"></table>
                <div id="pk_open"></div>
            </td>
        </tr>
    </tbody>
</table>
</body>

</html>
