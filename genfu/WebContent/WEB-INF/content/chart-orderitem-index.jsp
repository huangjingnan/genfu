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
	<h3 style="margin-bottom:0px;">Example 222</h3>
	<div id="chart_orderitem1" style="width:540px;height:250px;padding:5px;"></div>
<div style="text-align:right;padding-right:5px;">
	Start delay: <input id="chart_orderitemstart" style="width:35px" value="0" />
	Steps: <input id="chart_orderitemstart" style="width:25px" value="135" />
	Duration: <input id="chart_orderitemduration" style="width:35px" value="3000" /> <i>ms</i>
	Direction: <select id="chart_orderitemdir"><option value="right">Right</option><option value="left" selected>Left</option><option value="center">Center</option></select>
	<button id="chart_orderitembnt1" type="button">Animate</button>
</div>
</td>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 2</h3>
	<div id="chart_orderitem2" style="width:540px;height:250px;padding:5px;"></div>
	<button id="chart_orderitembnt2" type="button">Draw Evolution Line</button>
</td>
</tr>
<tr>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 3</h3>
	<div id="chart_orderitem3" style="width:540px;height:250px;padding:5px;"></div>
	<button id="chart_orderitembnt3" type="button">Animate</button>
</td>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 4</h3>
	<div id="chart_orderitem4" style="width:540px;height:250px;padding:5px;"></div>
	<button id="chart_orderitembnt4" type="button">Animate</button>
</td>
</tr></table>
</div>


<script type="text/javascript">
	$(document).ready(function() {

	var d8 = [[2, 5],[4, 8],[6, 2],[7, 5],[9,1],[10, 4],[12, 5],[13, 6],[14, 4]];
	var d9 = [[2, 4],[2.5, 5],[4.5, 8],[6.5, 2],[7.5, 5],[9.5,1],[10.5, 4],[12.5, 5],[13.5, 6],[14.5, 4],[15, 3]];	
	var plot1 = $.plotAnimator($("#chart_orderitem1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:$("#chart_orderitemstart").val(), steps:$("#chart_orderitemsteps").val(), duration:$("#chart_orderitemduration").val(), direction: $("#chart_orderitemdir").val()}}]);
	
	$("#chart_orderitembnt1").attr("disabled", "disabled");
	$("#chart_orderitem1").on("animatorComplete", function() {
		$("#chart_orderitembnt1").removeAttr("disabled");
	});
	$("#chart_orderitembnt1").on("click",function() {
		$("#chart_orderitembnt1").attr("disabled", "disabled");
		plot1 = $.plotAnimator($("#chart_orderitem1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:$("#chart_orderitemstart").val(), steps:$("#chart_orderitemsteps").val(), duration:$("#chart_orderitemduration").val(), direction: $("#chart_orderitemdir").val()}}]);
	});



	var d0 = [[2, 5], [4, 8], [6, 2], [7, 3], [10, 4], [12, 5], [13, 6], [14, 4]];
	var d1 = [[2, 5], [4, 8], [6, 2], [7, 3], [10, 4], [12, 5], [13, 6], [14, 4]];
    	var plot2 = $.plotAnimator($("#chart_orderitem2"), [{ data: d1, animator: {steps: 136, duration: 2500, start:0}, points: { show: true, fill: true, radius:10,fillColor:"#aa00aa" },label: "Bars" }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});

	$("#chart_orderitembnt2").attr("disabled", "disabled");
	$("#chart_orderitem2").on("animatorComplete", function() {
		$("#chart_orderitembnt2").removeAttr("disabled");
	});
	$("#chart_orderitembnt2").on("click",function() {
		$("#chart_orderitembnt2").attr("disabled", "disabled");
	    	plot2 = $.plotAnimator($("#chart_orderitem2"), [{ data: d1, points: { show: true, fill: true , radius:10,fillColor:"#aa00aa"},label: "Bars" }, { data: d0, animator: {steps: 136, duration: 2500, start:0}, lines: { show: true, fill: false },label: "Evolution" }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});
	});


	var d5 = [[1, 4], [2, 2], [4, 4], [6, 2], [8, 4], [10, 2], [15, 4], [20, 2]];
    	var d6 = [[1, 3], [20, 3]];
	var plot3 = $.plotAnimator($("#chart_orderitem3"), [{ data: d5, animator: {steps: 136, duration: 2000, start:0}, lines: { show: true, fill: true }, label: "Fill this"}, { data: d6, lines: { show: true, fill: false}, label: "Standard Values" }],{grid: { backgroundColor: { colors: [ "#fff", "#ebe" ] }}});

	$("#chart_orderitembnt3").attr("disabled", "disabled");
	$("#chart_orderitem3").on("animatorComplete", function() {
		$("#chart_orderitembnt3").removeAttr("disabled");
	});
	$("#chart_orderitembnt3").on("click",function() {
		$("#chart_orderitembnt3").attr("disabled", "disabled");
		plot3 = $.plotAnimator($("#chart_orderitem3"), [{ data: d5, animator: {steps: 136, duration: 2000, start:0}, lines: { show: true, fill: true }, label: "Fill this"}, { data: d6, lines: { show: true, fill: false}, label: "Standard Values" }],{grid: { backgroundColor: { colors: [ "#fff", "#ebe" ] }}});
	});


	var d2 = [];
	for (var i = 0 ; i < 20.1 ; i += 0.1) d2.push([i, Math.sin(i)]);
    	var d3 = [[0, 0], [20, 0]];
    	var plot4 = $.plotAnimator($("#chart_orderitem4"), [{ data: d2, animator: {steps: 136, duration: 3000, start:0}, lines: { show: true, fill: true }, label: "sin(x)" }, { data: d3, lines: { show: true, fill: false } }, { data: d2, lines: { show: true, fill: false } }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});

	$("#chart_orderitembnt4").attr("disabled", "disabled");
	$("#chart_orderitem4").on("animatorComplete", function() {
		$("#chart_orderitembnt4").removeAttr("disabled");
	});
	$("#chart_orderitembnt4").on("click",function() {
		$("#chart_orderitembnt4").attr("disabled", "disabled");
    		plot4 = $.plotAnimator($("#chart_orderitem4"), [{ data: d2, animator: {steps: 136, duration: 3000, start:0}, lines: { show: true, fill: true }, label: "sin(x)" }, { data: d3, lines: { show: true, fill: false } }, { data: d2, lines: { show: true, fill: false } }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});
	});


    });
</script>
</body>

</html>
