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
	<%-- <script src="${ctx}/js/jquery/jquery-2.0.3.js" type="text/javascript"></script> --%>
	<script src="${ctx}/js/chart/jquery_flot_animator/jquery.flot.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/chart/jquery_flot_animator/jquery.flot.animator.min.js" type="text/javascript"></script>
<%-- <script src="${ctx}/js/chart/jquery.peity.min.js" type="text/javascript"></script> --%>
<script type="text/javascript">
	//$(".bar").peity("bar");
	//$(".line").peity("line");
	
</script>
<s:head />
</head>
<body>

<%-- <span data-height="400" data-gap="5" data-width="900" class="bar">5,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2</span>
<span data-height="400" data-width="900" class="line">5,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2,3,9,6,5,9,7,3,5,2</span> --%>
<div style="text-align:center;">
<h1>统计图表</h1>
<table style="margin-left:auto;margin-right:auto;"><tr>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">最近14天</h3>
	<div id="chart1" style="width:540px;height:250px;padding:5px;"></div>
<%-- <div style="text-align:right;padding-right:5px;">
	Start delay: <input id="start" style="width:35px" value="0" />
	Steps: <input id="steps" style="width:25px" value="135" />
	Duration: <input id="duration" style="width:35px" value="3000" /> <i>ms</i>
	Direction: <select id="dir"><option value="right">Right</option><option value="left" selected>Left</option><option value="center">Center</option></select>
	<button id="bnt1" type="button">Animate</button>
</div> --%>
</td>
</tr>
</table>
</div>


<script type="text/javascript">

jQuery("#tagging_dishes").jqGrid({ 
	url:'${ctx}/charts-order.json?style=jqGrid',
	datatype: "json",
	colNames:['商品ID','商品名称', 'isbn', 'masterId','publishedAt','状态','价格', '创建时间', '更新时间','图片信息','图片文件','标签','描述'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'dishName',index:'dishName', width:150,editable:true,editoptions:{size:10}},
   		{name:'isbn',index:'isbn', width:90,editable:true,hidden:true,editoptions:{size:25}},
   		{name:'publishedId',index:'publishedId', width:60,editable:false, hidden:true,editoptions:{size:10}},
   		{name:'publishedAt',index:'publishedAt', width: 80, hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'dishFlag',index:'dishFlag', width:80, editable: true,align:"left",edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'price',index:'price',editable:true, align:"right",formatter:'number', width:80,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'createdAt',index:'createdAt', width:100, align:"center", hidden:true, sorttype: 'date',
            formatter: 'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'updatedAt',index:'updatedAt',width:100,align:"center", hidden:true,sorttype:'date',formatter:'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},	
   		{name:'coverImage',index:'coverImage',width:170},	
   		{name:'fileImage',index:'fileImage',width:70,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'taggings',index:'taggings',width:200,formatter:tagsFmatter},
   		{name:'blurb',index:'blurb',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#tagging_dish_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "商品" }); 


	$(document).ready(function() {

	var d8 = [[1, 10.50], [2, 13.33], [3, 12.35], [4, 9.89], [5, 12.35], [6, 6.35], [7, 8.35], [8, 6], [9, 4], [10, 2], [11, 12.35], [12, 11.35], [13, 12.35], [14, 5]];
	var d9 = [[1.5, 10.50], [2.5, 13.33], [3.5, 12.35], [4.5, 9.89], [5.5, 12.35], [6.5, 6.35], [7.5, 8.35], [8.5, 6], [9.5, 4], [10.5, 2], [11.5, 12.35], [12.5, 11.35], [13.5, 12.35], [14.5, 5]];
	debugger;
	var plot1 = $.plotAnimator($("#chart1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:0, steps:135, duration:30, direction: "left"}}]);
	//var plot1 = $.plotAnimator($("#chart1"), [{data : d8, bars: { show: true, fill: true}}]);
	
/* 	$("#bnt1").attr("disabled", "disabled");
	$("#chart1").on("animatorComplete", function() {
		$("#bnt1").removeAttr("disabled")
	});
	$("#bnt1").on("click",function() {
		$("#bnt1").attr("disabled", "disabled");
		plot1 = $.plotAnimator($("#chart1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:$("#start").val(), steps:$("#steps").val(), duration:$("#duration").val(), direction: $("#dir").val()}}]);
	}); */

    });
</script>

<table id="charts_order"></table>
<div id="pcharts_order"></div>

</body>

</html>
