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
<h1>Jquery Flot Animator Samples</h1>
<table style="margin-left:auto;margin-right:auto;"><tr>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 1</h3>
	<div id="chart1" style="width:540px;height:250px;padding:5px;"></div>
<div style="text-align:right;padding-right:5px;">
	Start delay: <input id="start" style="width:35px" value="0" />
	Steps: <input id="steps" style="width:25px" value="135" />
	Duration: <input id="duration" style="width:35px" value="3000" /> <i>ms</i>
	Direction: <select id="dir"><option value="right">Right</option><option value="left" selected>Left</option><option value="center">Center</option></select>
	<button id="bnt1" type="button">Animate</button>
</div>
</td>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 2</h3>
	<div id="chart2" style="width:540px;height:250px;padding:5px;"></div>
	<button id="bnt2" type="button">Draw Evolution Line</button>
</td>
</tr>
<tr>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 3</h3>
	<div id="chart3" style="width:540px;height:250px;padding:5px;"></div>
	<button id="bnt3" type="button">Animate</button>
</td>
<td style="text-align:center;">
	<h3 style="margin-bottom:0px;">Example 4</h3>
	<div id="chart4" style="width:540px;height:250px;padding:5px;"></div>
	<button id="bnt4" type="button">Animate</button>
</td>
</tr></table>
</div>


<script type="text/javascript">
	$(document).ready(function() {

	var d8 = [[2, 2], [4, 3], [6, 5], [7, 8], [10, 6], [12, 4], [13, 2], [14, 5]];
	var d9 = [[2, 2], [4, 3], [6, 5], [7, 8], [10, 6], [12, 4], [13, 2], [14, 5]];
	var plot1 = $.plotAnimator($("#chart1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:$("#start").val(), steps:$("#steps").val(), duration:$("#duration").val(), direction: $("#dir").val()}}]);
	
	$("#bnt1").attr("disabled", "disabled");
	$("#chart1").on("animatorComplete", function() {
		$("#bnt1").removeAttr("disabled")
	});
	$("#bnt1").on("click",function() {
		$("#bnt1").attr("disabled", "disabled");
		plot1 = $.plotAnimator($("#chart1"), [{data : d8, bars: { show: true, fill: true}},{data : d9, lines: {lineWidth:5}, animator: {start:$("#start").val(), steps:$("#steps").val(), duration:$("#duration").val(), direction: $("#dir").val()}}]);
	});



	var d0 = [[2, 5], [4, 8], [6, 2], [7, 3], [10, 4], [12, 5], [13, 6], [14, 4]];
	var d1 = [[2, 5], [4, 8], [6, 2], [7, 3], [10, 4], [12, 5], [13, 6], [14, 4]];
    	var plot2 = $.plotAnimator($("#chart2"), [{ data: d1, animator: {steps: 136, duration: 2500, start:0}, points: { show: true, fill: true, radius:10,fillColor:"#aa00aa" },label: "Bars" }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});

	$("#bnt2").attr("disabled", "disabled");
	$("#chart2").on("animatorComplete", function() {
		$("#bnt2").removeAttr("disabled")
	});
	$("#bnt2").on("click",function() {
		$("#bnt2").attr("disabled", "disabled");
	    	plot2 = $.plotAnimator($("#chart2"), [{ data: d1, points: { show: true, fill: true , radius:10,fillColor:"#aa00aa"},label: "Bars" }, { data: d0, animator: {steps: 136, duration: 2500, start:0}, lines: { show: true, fill: false },label: "Evolution" }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});
	});


	var d5 = [[1, 4], [2, 2], [4, 4], [6, 2], [8, 4], [10, 2], [15, 4], [20, 2]];
    	var d6 = [[1, 3], [20, 3]];
	var plot3 = $.plotAnimator($("#chart3"), [{ data: d5, animator: {steps: 136, duration: 2000, start:0}, lines: { show: true, fill: true }, label: "Fill this"}, { data: d6, lines: { show: true, fill: false}, label: "Standard Values" }],{grid: { backgroundColor: { colors: [ "#fff", "#ebe" ] }}});

	$("#bnt3").attr("disabled", "disabled");
	$("#chart3").on("animatorComplete", function() {
		$("#bnt3").removeAttr("disabled")
	});
	$("#bnt3").on("click",function() {
		$("#bnt3").attr("disabled", "disabled");
		plot3 = $.plotAnimator($("#chart3"), [{ data: d5, animator: {steps: 136, duration: 2000, start:0}, lines: { show: true, fill: true }, label: "Fill this"}, { data: d6, lines: { show: true, fill: false}, label: "Standard Values" }],{grid: { backgroundColor: { colors: [ "#fff", "#ebe" ] }}});
	});


	var d2 = [];
	for (var i = 0 ; i < 20.1 ; i += 0.1) d2.push([i, Math.sin(i)]);
    	var d3 = [[0, 0], [20, 0]];
    	var plot4 = $.plotAnimator($("#chart4"), [{ data: d2, animator: {steps: 136, duration: 3000, start:0}, lines: { show: true, fill: true }, label: "sin(x)" }, { data: d3, lines: { show: true, fill: false } }, { data: d2, lines: { show: true, fill: false } }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});

	$("#bnt4").attr("disabled", "disabled");
	$("#chart4").on("animatorComplete", function() {
		$("#bnt4").removeAttr("disabled")
	});
	$("#bnt4").on("click",function() {
		$("#bnt4").attr("disabled", "disabled");
    		plot4 = $.plotAnimator($("#chart4"), [{ data: d2, animator: {steps: 136, duration: 3000, start:0}, lines: { show: true, fill: true }, label: "sin(x)" }, { data: d3, lines: { show: true, fill: false } }, { data: d2, lines: { show: true, fill: false } }],{grid: { backgroundColor: { colors: [ "#fff", "#eee" ] }}});
	});


    });
</script>
</body>

</html>
