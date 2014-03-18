<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tags</title>
<%-- 
<script src="${ctx}/js/jq-ingrid/lib/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery-ui-custom.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery.layout.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/ui.multiselect.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.contextmenu.js" type="text/javascript"></script> --%>
<script type="text/javascript">

var myJqGrid = jQuery("#genfu_tags").jqGrid({ 
	url:'${ctx}/tag.json?style=jqGrid',
	datatype: "json", 
	height: 250, 
	colNames:['tagId','tagName','tagSn'],
	colModel:[
   		{name:'id',index:'id', width:320,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'name',index:'name', width:320,editable:true,editoptions:{size:90},editrules:{required:true}},
   		{name:'tagSn',index:'tagSn', width:120,editable:true}
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#pgenfu_tags',
   	sortname: 'id',
    viewrecords: true,
    autowidth: true,
	multiselect: true, caption: "tags"
}); 

	jQuery("#genfu_tags").jqGrid('navGrid','#pgenfu_tags',{},
			{url:'${ctx}/tag/0?_method=put',reloadAfterSubmit:false},
			{url:'${ctx}/tag',reloadAfterSubmit:false},
			{url:'${ctx}/tag/0?_method=DELETE&className=com.genfu.reform.model.Tag',reloadAfterSubmit:false}, {});
	//jQuery("#pgenfu_tags").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
</script>
<s:head />
</head>
<body>

<table id="genfu_tags"></table>
<div id="pgenfu_tags"></div>
	
</body>

</html>
