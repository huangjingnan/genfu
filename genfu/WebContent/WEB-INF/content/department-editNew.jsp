
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

@import url(${ctx}/js/jquery.simpledialog/jquery.simpledialog.0.1.css);
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
<script type="text/javascript"
	src="${ctx}/js/jquery.simpledialog/jquery.simpledialog.0.1.js"></script>
<script>
	$(function() {
		$("#userExpDate,#userEffDate").datepicker({
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("#userExpDate,#userEffDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);

		$('#sdHc3').simpleDialog(
				{
					showCloseLabel : false,
					open : function() {
						$('#checkboxStatus').html('');
					},
					close : function() {
						var c = [];
						$('#checkboxForm :checkbox:checked').each(function() {
							c.push($(this).val());
						});
						$('#checkboxStatus').html(
								'Checked ' + c.join(', ') + '.').show();
					}
				});

	});

	function selectUpperUser() {

		//alert(window.screen.availHeight);
		//alert(window.screen.availWidth);
		var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

		window
				.open(
						'${ctx}/department',
						'winSelectUpperUser',
						'height=500,width=900,top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
	}
</script>
</head>
<body>
	<s:actionmessage />
	<div class="demo">
		<s:form method="post" action="%{#ctx}/department">
			<table class="datepickers">
				<tr>
					<td><s:textfield name="id" label="id" /></td>
				</tr>
				<tr>
					<td><s:textfield name="departmentName" label="departmentName" />
					</td>
				</tr>
				<tr>
					<td><s:textfield name="updateDate" label="updateDate"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:textfield id="upId" name="upId"
							onclick="selectUpperUser();" cssStyle="display: none;">
						</s:textfield></td>
				</tr>
				<tr>
					<td onclick="selectUpperUser();">upperUser:</td>
					<td><span id="checkboxStatus"></span></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit /></td>
				</tr>
			</table>
		</s:form>
		<a href="${ctx}/department">Back to Orders</a>
	</div>

</body>
</html>