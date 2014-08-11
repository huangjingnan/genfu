<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>charts</title>
<style>
</style>
<script type="text/javascript">
jQuery("#groupOrderItem").jqGrid({
   	url:'${ctx}/group-orderitem.json?style=jqGrid',
	datatype: "json",
   	colNames:['ID', '订单ID','商品ID','商品名称', '商品价格','数量','总额','服务员','厨师','创建日期','更新时间','状态'],
   	colModel:[
   		{name:'id',index:'id', width:55, editable:false, sorttype:'int',summaryType:'count', summaryTpl : '({0}) total'},
   		{name:'orderId',index:'orderId', width:100},
   		{name:'dishId',index:'dishId', width:90},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:80,align:"right",sorttype:'number',formatter:'number', summaryType:'sum'},
   		{name:'amount',index:'amount', width:80, align:"right", sorttype:'number',formatter:'number',summaryType:'sum'},
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
   		{name:'staffNumber',index:'staffNumber', width:100},
   		{name:'kitchener',index:'kitchener', width:100},
   		{name:'createdAt',index:'createdAt', width:90},
   		{name:'updatedAt',index:'updatedAt', width:90, formatter:'date'},
   		{name:'status',index:'status', width:90}	
   	],
   	rowNum:10,
   	rowList:[10,20,30,300],
   	height: 'auto',
   	pager: '#pgroupOrderItem',
   	sortname: 'id',
    viewrecords: true,
    autowidth: true,
    sortorder: "desc",
    caption:"分组统计查看",
    grouping: true,
   	groupingView : {
   		groupField : ['dishId'],
   		groupColumnShow : [true],
   		groupText : ['<b>{0}</b>'],
   		groupCollapse : false,
		groupOrder: ['asc'],
		groupSummary : [true],
		groupDataSorted : true
   	},
    footerrow: true,
    userDataOnFooter: true
});

jQuery("#groupOrderItem").jqGrid('navGrid','#pgroupOrderItem',{view:true,edit:false,add:false,del:false},
		{},
		{},
		{},
		{multipleSearch:true, multipleGroup:true, showQuery: true},
		{height:250,jqModal:false,closeOnEscape:true});

jQuery("#chnGroupOrderItem").change(function(){
	var vl = $(this).val();
	if(vl) {
		if(vl == "clear") {
			jQuery("#groupOrderItem").jqGrid('groupingRemove',true);
		} else {
			jQuery("#groupOrderItem").jqGrid('groupingGroupBy',vl);
		}
	}
});
function mysum(val, name, record)
{
	//debugger;
	//val = val - 0.00;
	return parseFloat(val||0) + parseFloat(record['price'] * record['amount']);
	//return 0;
}
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


function dishDatefmtGroup(cellvalue, options, rowObject)
{
	debugger;
	if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
		return cellvalue.year + '-' + cellvalue.month + '-' + cellvalue.day;
	}else{
		return "";
	}
	//
	//return cellvalue.toLocaleString("ca");
	//return 0;
}
	
</script>
<s:head />
</head>
<body>
分组: <select id="chnGroupOrderItem">
	<option value="dishId">商品ID</option>
	<option value="createdAt">创建日期</option>
	<option value="orderId">订单ID</option>	
</select>
<br />
<br />
<table id="groupOrderItem"></table>
<div id="pgroupOrderItem"></div>

</body>

</html>
