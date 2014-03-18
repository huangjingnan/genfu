<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>comment_mgr</title>
<script src="${ctx}/js/uploadfile/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">

	jQuery("#comment_mgr").jqGrid({ 
	url:'${ctx}/comment-mgr.json?style=jqGrid',
	datatype: "json", 
	height: 250, 
	colNames:['评价ID','orderItemId', '商品ID', 'orderId','商品名称','评价时间','得分', '评价内容', '联系人','联系方式','coverImage','commentOthers'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'orderItemId',index:'orderItemId', width:80,hidden:true,editable:true,editoptions:{size:10}},
   		{name:'dishId',index:'dishId', width:90,editoptions:{size:25}},
   		{name:'orderId',index:'orderId', width:60,hidden:true,editoptions:{size:10}},
   		{name:'dishName',index:'dishName', width:80,editable:true,editoptions:{size:10}},
   		{name:'commentAt',index:'commentAt', width:80, editable: true,align:"left",sorttype: 'date',formatter:dishDatefmt},
   		{name:'score',index:'score',editable:true, align:"right",formatter:'number', width:80,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'comment',index:'comment',width:100,align:"left",sortable:false},
   		{name:'commentator',index:'commentator',width:100,sortable:false,align:"left"},	
   		{name:'contact',index:'contact',width:170,sortable:false,align:"left"},	
   		{name:'coverImage',index:'coverImage',width:70,hidden:true,sortable:false,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'commentOthers',index:'commentOthers',width:100,hidden:true,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#pcomment_mgr',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "评价查看" }); 
	
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

	jQuery("#comment_mgr").jqGrid('navGrid','#pcomment_mgr',{edit:false,add:false,del:false},
			{url:'${ctx}/comment-mgr/0?_method=put',reloadAfterSubmit:false},
			{url:'${ctx}/comment-mgr',clearAfterAdd:true,reloadAfterSubmit:false},
			{url:'${ctx}/comment-mgr/0?_method=DELETE',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:true, showQuery: true});
	

</script>
<s:head />
</head>
<body>

<table id="comment_mgr"></table>
<div id="pcomment_mgr"></div>
	
</body>

</html>
