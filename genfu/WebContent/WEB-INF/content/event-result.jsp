<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

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
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.effects.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.effects.bounce.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-HK.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-TW.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-en-AU.js"></script>
<script type="application/javascript">
	
	
	
	$(document).ready(function() {
		$("#large").tablesorter({
			// striping looking
			widgets : [ 'zebra' ],
			headers : {
				// assign the 4th column (we start counting zero)
				3 : {
					// disable it by setting the property sorter to false
					sorter : false
				}
			}

		}).tablesorterPager({
			container : $("#pager")
		});
	});
	


</script>
</head>
<body>

	<table id="large" class="tablesorter" cellspacing="0">
		<thead>
			<tr>
				<th>ID</th>
				<th>title</th>
				<th>stateDate</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody id="resultSet_tbody">
			<s:iterator value="model" var="resultSet_iterator">
				<tr>
					<td>${id}</td>
					<td>${title}</td>
					<td>${stateDate}</td>
					<td><a href="${ctx}/event/${id}">View</a> | <a
						href="${ctx}/event/${id}/edit">Edit</a> | <a
						href="${ctx}/event/${id}/deleteConfirm">Delete</a></td>
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
		<a href="${ctx}/event/new">Create a new object</a>
	</div>
</body>
</html>
