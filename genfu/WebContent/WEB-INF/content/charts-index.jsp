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
	$(document).ready(function() {

	var d8 = [[1, 10.50], [2, 13.33], [3, 12.35], [4, 9.89], [5, 12.35], [6, 6.35], [7, 8.35], [8, 6], [9, 4], [10, 2], [11, 12.35], [12, 11.35], [13, 12.35], [14, 5]];
	var d9 = [[1.5, 10.50], [2.5, 13.33], [3.5, 12.35], [4.5, 9.89], [5.5, 12.35], [6.5, 6.35], [7.5, 8.35], [8.5, 6], [9.5, 4], [10.5, 2], [11.5, 12.35], [12.5, 11.35], [13.5, 12.35], [14.5, 5]];
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
</body>

</html>
