<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events</title>
<style type="text/css">
@import url(${ctx}/js/__jquery.tablesorter/themes/blue/style.css);

@import
	url(${ctx}/js/__jquery.tablesorter/addons/pager/jquery.tablesorter.pager.css)
	;

@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/themes/base/jquery.ui.all.css)
	;

@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/demos/demos.css)
	;
/*@import url(${ctx}/js/jquery-ui-1.8.20.custom/css/screen.css);*/
</style>

<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/jquery-1.7.2.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/js/custom.js"></script>
<script type="application/javascript"
	src="${ctx}/js/__jquery.tablesorter/jquery.tablesorter.js"></script>
<script type="application/javascript"
	src="${ctx}/js/__jquery.tablesorter/addons/pager/jquery.tablesorter.pager.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="application/javascript">
	
	
	
	$(document).ready(function() {
		$("#large").tablesorter({
			// striping looking
			widgets : [ 'zebra' ],
			headers : {
				// assign the 4th column (we start counting zero)
				11 : {
					// disable it by setting the property sorter to false
					sorter : false
				}
			}

		}).tablesorterPager({
			container : $("#pager")
		});
		
		
		$("#ajax-append").click(function() {
			 $.get("${ctx}/js/__jquery.tablesorter/docs/assets/ajax-content.html", function(html) {
			 	// append the "ajax'd" data to the table body
			 	$("table tbody").append(html);
				// let the plugin know that we made a update
				$("table").trigger("update");
				// set sorting column and direction, this will sort on the first and third column
				var sorting = [[2,1],[0,0]];
				// sort on the first column
				$("table").trigger("sorton",[sorting]);
			});
			return false;
		});
	});
	

	
	function win_open(td_obj){
		
		var win_open_url="";
		
		win_open_url=td_obj.value;
		win_open_name=td_obj.name;
		//alert(window.screen.availHeight);
		//alert(window.screen.availWidth);
		var iTop = (window.screen.availHeight-500)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-900)/2; //获得窗口的水平位置;
		
		window.open (win_open_url,win_open_name,'height=500,width=900,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
	}

	function show_detail(){
		
		alert(0);
	}





</script>
</head>
<body>

	<table id="large" class="tablesorter" cellspacing="0">
		<thead>
			<tr>
				<th>naviAction</th>
				<th>naviDescription</th>
				<th>naviEffDate</th>
				<th>naviExpDate</th>
				<th>naviFlag</th>
				<th>naviOrder</th>
				<th>naviOthers</th>
				<th>naviParentId</th>
				<th>naviSrc</th>
				<th>naviText</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="model">
				<tr ondblclick="show_detail();">
					<td>${naviAction}</td>
					<td>${naviDescription}</td>
					<td>${naviEffDate}</td>
					<td>${naviExpDate}</td>
					<td>${naviFlag}</td>
					<td>${naviOrder}</td>
					<td>${naviOthers}</td>
					<td>${naviParentId}</td>
					<td>${naviSrc}</td>
					<td>${naviText}</td>
					<td><button value="${ctx}/navigation-node/${id}"
							onclick="win_open(this);" name="navigation_${id}">view</button> |
						<a href="${ctx}/navigation-node/${id}/edit">Edit</a> | <a
						href="${ctx}/navigation-node/${id}/deleteConfirm">Delete</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div id="pager" class="pager" style="margin-top: 0px; float: left;">
		<form id="pager_form">
			<img
				src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/first.png"
				class="first" /> <img
				src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/prev.png"
				class="prev" /> <input type="text" class="pagedisplay" /> <img
				src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/next.png"
				class="next" /> <img
				src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/last.png"
				class="last" /> <select class="pagesize">
				<option selected="selected" value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
			</select>
		</form>
		<a href="${ctx}/navigation-node/new">Create a new object</a> <a
			href="#" id="ajax-append">Append new table data</a>
	</div>
</body>
</html>
