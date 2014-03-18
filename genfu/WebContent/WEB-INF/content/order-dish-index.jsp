<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orders</title>
<script type="text/javascript">

var order_detail_url = "${ctx}/order-item.json?style=jqGrid";

jQuery("#order_header").jqGrid({
   	url:'${ctx}/order.json?style=jqGrid&status_EQ=OPEN',
	datatype: "json",
   	colNames:['id','orderName', 'numberPeople', 'staffNumber', 'Amount','status', 'createdAt', 'updatedAt','phoneNumber','email','Notes'],
   	colModel:[
   		{name:'id',index:'id', width:55},
   		{name:'orderName',index:'orderName', width:90,editable:true,editrules:{required:true}},
   		{name:'numberPeople',index:'numberPeople',width:90, align:"right",editable:true,editrules:{required:true,integer:true,minValue:1,maxValue:1000}},
   		{name:'staffNumber',index:'staffNumber', width:90},
   		{name:'amount',index:'amount', width:80, align:"right"},
   		{name:'status',index:'status', width:90,defval:"OPEN", editable: true,edittype:"select",editoptions:{value:"FAILED:FAILED;OPEN:OPEN"}},
   		{name:'createdAt',index:'createdAt', formatter: 'date', formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100},
   		{name:'updatedAt',index:'updatedAt', formatter: 'date', formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100},
   		{name:'phoneNumber',index:'phoneNumber',editable:true, width:80},		
   		{name:'email',index:'email', width:80,editable:true,editrules:{required:true,email:true},align:"right"},		
   		{name:'errorMessage',index:'errorMessage',sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}		
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#order_header_pager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
	multiselect: false, 
	autowidth: true,
	caption: "Order Header",
	onSelectRow: function(ids) {
		if(ids == null) {
			ids=0;
			if(jQuery("#order_detail").jqGrid('getGridParam','records') >0 )
			{
				var _detail_url = order_detail_url + "&orderId=" + ids;
				jQuery("#order_detail").jqGrid('setGridParam',{url:_detail_url,page:1});
				jQuery("#order_detail").jqGrid('setCaption',"Order Detail: "+ids)
				.trigger('reloadGrid');
			}
			enableAddToOrder(true);
		} else {
			var _detail_url = order_detail_url + "&orderId=" + ids;
			jQuery("#order_detail").jqGrid('setGridParam',{url:_detail_url,page:1});
			jQuery("#order_detail").jqGrid('setCaption',"Order Detail: "+ids)
			.trigger('reloadGrid');
			jQuery("#orderAmount").val(1);
			//debugger;
			enableAddToOrder(false);
		}
	}
});
jQuery("#order_header").jqGrid('navGrid','#order_header_pager',
		{edit:true,add:false,del:false},
		{url:'${ctx}/order/0?_method=put',reloadAfterSubmit:false},{},{},{multipleSearch:true, multipleGroup:false, showQuery: true});
function mysum(val, name, record)
{
	//debugger;
	//val = val - 0.00;
	return parseFloat(val||0) + parseFloat(record['price'] * record['amount']);
	//return 0;
}
jQuery("#order_detail").jqGrid({
   	url:'${ctx}/order-item.json?style=jqGrid&orderId=0',
	datatype: "json", 
	//height: 290, 
   	colNames:['id','orderId','dishId', 'orderItemName', 'staffNumber', 'price', 'amount', 'total', 'createdAt', 'updatedAt'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'staffNumber',index:'staffNumber', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number', width:100, searchtype:"number"},
   		{name:'amount',index:'amount', width:55, align:"right",editable:true,
   			editrules:{required:true,integer:true,minValue:1,maxValue:1000}, summaryType:'sum'},
   		{name:'total',index:'total', width:55, align:"right", width:100,search:false,
   			cellattr: function(rowId, value, rowObject, colModel, arrData) {
				return ' colspan=1';
			},
			formatter : function(value, options, rData){
				//parseFloat(tempSum).toFixed(2)
				//debugger;
				if(value !== undefined && value !== 'undefined'){
					return parseFloat(value).toFixed(2);
				}
				return parseFloat(rData['price'] * rData['amount']).toFixed(2);
			}, summaryType:mysum
   		},
   		{name:'createdAt',index:'createdAt', width:110,align:"center",sorttype:'datetime',
   			formatter: 'date', formatoptions: {newformat: 'Y-m-d H:i:s'}},
 	   	{name:'updatedAt',index:'updatedAt', width:110,align:"center",sorttype:'datetime',
   	   		formatter: 'date', formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	rowNum:2000,
   	rowList:[50,100,200],
   	pager: '#order_detail_pager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
	multiselect: false, 
	autowidth: true,
	grouping:true,
	groupingView : {
   		groupField : ['orderId'],
   		groupSummary : [true],
   		groupColumnShow : [false],
   		groupText : ['<b>{0}</b>'],
   		groupCollapse : false,
		groupOrder: ['asc']
   	},
	//footerrow : true,
	//userDataOnFooter: false,
	caption:"Order Detail"
});
function myDatefmt(cellvalue, options, rowObject)
{
	return cellvalue.toLocaleString();
	//return 0;
}
jQuery("#order_detail").jqGrid('navGrid','#order_detail_pager',{edit:true,add:false,del:true},
		{url:'${ctx}/order-item/0?_method=put',reloadAfterSubmit:false},
		{},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:true},{multipleSearch:true, multipleGroup:false, showQuery: true});


jQuery("#order_dishes").jqGrid({ 
	url:'${ctx}/dish.json?style=jqGrid',
	datatype: "json",
	colNames:['dishId','dishName', 'isbn', 'masterId','publishedAt','dishFlag','price', 'createdAt', 'updatedAt','coverImage','fileImage','tags','blurb'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'dishName',index:'dishName', width:150,editable:true,editoptions:{size:10}},
   		{name:'isbn',index:'isbn', width:90,editable:true,hidden:true,editoptions:{size:25}},
   		{name:'publishedId',index:'publishedId', width:60,editable:false, hidden:true,editoptions:{size:10}},
   		{name:'publishedAt',index:'publishedAt', width: 80, hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'dishFlag',index:'dishFlag', width:100, editable: true,edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'price',index:'price',editable:true, align:"right",formatter:'number', width:100,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'createdAt',index:'createdAt', width:90, align:"center", hidden:true, sorttype: 'date',
            formatter: 'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'updatedAt',index:'updatedAt',width:90,align:"center", hidden:true,sorttype:'date',formatter:'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},	
   		{name:'coverImage',index:'coverImage',width:170},	
   		{name:'fileImage',index:'fileImage',width:70,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'taggings',index:'taggings',width:200,formatter:tagsFmatter},
   		{name:'blurb',index:'blurb',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#order_dish_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "dishes" }); 
jQuery("#order_dishes").jqGrid('navGrid','#order_dish_pager',{edit:false,add:false,del:false},
		{url:'${ctx}/dish/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/dish',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.Dish',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
		
function tagsFmatter ( cellvalue, options, rowObject ){
	var tagNames="";
	//debugger;
	if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
		for (var i=0;i<cellvalue.length;i++){
			tagNames=tagNames+','+cellvalue[i].tag.name;
			//alert(cellvalue[i].tag.name);
		}
	}
	return tagNames.substring(1);
}

var timeoutHnd;
var flAuto = false;

function doSearch(ev){
	if(!flAuto)
		return;
//	var elem = ev.target||ev.srcElement;
	if(timeoutHnd)
		clearTimeout(timeoutHnd);
	timeoutHnd = setTimeout(gridReload,1000);
}
var order_dishes_url = "${ctx}/dish.json?style=jqGrid";
var preCD_mask = "";
function gridReload(){
	//var nm_mask = jQuery("#item_nm").val();
	var cd_mask = jQuery("#search_cd").val();

	var paramsKeyValue = "";
	if(escape(cd_mask) !== '%20' && preCD_mask !== cd_mask){
		preCD_mask = cd_mask;
		//cd_mask = encodeURIComponent(cd_mask);
		//alert(cd_mask);
		//encodeURI(":");
		if(cd_mask !== ""){
			//paramsKeyValue = encodeURI(order_dishes_url +"&cd_mask="+cd_mask);
			paramsKeyValue = order_dishes_url +"&cd_mask="+encodeURIComponent(encodeURIComponent(cd_mask));
			//alert(paramsKeyValue);
			jQuery("#order_dishes").jqGrid('setGridParam',{url:paramsKeyValue,page:1}).trigger("reloadGrid");
		}else{
			jQuery("#order_dishes").jqGrid('setGridParam',{url:order_dishes_url,page:1}).trigger("reloadGrid");
		}
	}
	//alert(nm_mask);
	//alert(cd_mask);
	//jQuery("#order_dishes").jqGrid('setGridParam',{url:"${ctx}/dish.json?style=jqGrid&nm_mask="+escape(nm_mask)+"&cd_mask="+escape(cd_mask),page:1}).trigger("reloadGrid");
}
function enableAutosubmit(state){
	flAuto = state;
	jQuery("#submitButton").attr("disabled",state);
}

function enableAddToOrder(state){
	jQuery("#addDishToOrder").attr("disabled",state);
	jQuery("#orderAmount").attr("disabled",state);
}
enableAddToOrder(true);

</script>
<s:head />
</head>
<body>
<table id="order_header"></table>
<div id="order_header_pager"></div>
<br />
<table id="order_detail"></table>
<div id="order_detail_pager"></div>	
<br />
<div class="h">Search By:</div>
<div>
	<input type="checkbox" id="autosearch" onclick="enableAutosubmit(this.checked)"> Enable Autosearch <br/>
	dishName
	<input type="text" id="search_cd" onkeydown="doSearch(arguments[0]||event)" onchange=""/>
	<!-- blurb
	<input type="text" id="item_nm" onkeydown="doSearch(arguments[0]||event)" onchange="" /> -->
	<button onclick="gridReload()" id="submitButton" style="margin-right:30px;margin-left:30px;">Search</button>
	orderAmount
	<input type="text" id="orderAmount" />
	<input type="BUTTON" id="addDishToOrder" value="addDishToOrder" style="margin-left:30px;" />
</div>

<table id="order_dishes"></table>
<div id="order_dish_pager"></div>	
</body>

</html>