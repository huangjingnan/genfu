<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>navigation-node <s:property value="id" /></title>

<script>
	$(function() {
		$("#naviEffDate,#naviExpDate").datepicker({
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("#naviEffDate,#naviExpDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
</script>
</head>
<body>
	<s:form method="post" action="%{#ctx}/navigation-node/%{id}">
		<s:hidden name="_method" value="put" />
		<s:textfield name="id" label="id" />
		<s:textfield name="naviAction" label="naviAction" />
		<s:textarea name="naviDescription" label="naviDescription"></s:textarea>
		<s:textfield id="naviEffDate" name="naviEffDate" label="naviEffDate">
		</s:textfield>
		<s:textfield id="naviExpDate" name="naviExpDate" label="naviExpDate">
		</s:textfield>
		<s:textfield name="naviFlag" label="naviFlag">
		</s:textfield>
		<s:textfield name="naviOthers" label="naviOthers">
		</s:textfield>
		<s:textfield name="naviParentId" label="naviParentId">
		</s:textfield>
		<s:textfield name="naviSrc" label="naviSrc">
		</s:textfield>
		<s:textfield name="naviText" label="naviText">
		</s:textfield>
		<s:submit />
	</s:form>
	<a href="${ctx}/navigation-node">Back to Orders</a>
</body>
</html>
