
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Event</title>
<style type="text/css">
@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/themes/base/jquery.ui.all.css)
	;

@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/demos/demos.css)
	;
</style>

<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/jquery-1.7.2.js"></script>
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
	$(function() {
		$("#stateDate").datepicker({
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("#stateDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
</script>
<s:head />
</head>
<body>
	<div class="demo">
		<s:form method="post" action="%{#ctx}/event">
			<table class="datepickers">
				<tr>
					<td><p>
							<s:textfield name="title" label="title" />
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="stateDate" name="stateDate" label="stateDate">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit /></td>
				</tr>
			</table>
		</s:form>
		<a href="${ctx}/event">Back to Events</a>
	</div>
</body>
</html>
