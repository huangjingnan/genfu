<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfo <s:property value="id" /></title>
<jsp:include page="/include/editInclude.jsp" flush="true" />


<script>
	$(function() {
		$("#roleExpDate,#roleEffDate")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "${ctx}/js/jquery-ui-1.10.3/demos/datepicker/images/calendar.gif",
							buttonImageOnly : true,
							changeMonth : true,
							changeYear : true,
							showOtherMonths : true,
							selectOtherMonths : true
						});
		$("#roleExpDate,#roleEffDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
</script>
</head>
<body>
	<div class="demo">
		<s:form method="post" action="%{#ctx}/role-info/%{id}">
			<s:hidden name="_method" value="put" />
			<table class="datepickers">
				<tr>
					<td><s:textfield name="id" label="id" /></td>
				</tr>
				<tr>
					<td><s:textfield name="roleName" label="roleName" /></td>
				</tr>
				<tr>
					<td><s:textfield name="roleDescription"
							label="roleDescription"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="roleFlag" label="roleFlag"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:textfield id="roleEffDate" name="roleEffDate"
							label="roleEffDate"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="roleExpDate" name="roleExpDate"
							label="roleExpDate"></s:textfield></td>
				</tr>
				<tr>
					<td><s:select list="listNavi" name="navigationNodeIds"
							listKey="id" listValue="naviText" multiple="true"
							label="navigationNodes" headerKey="-1" headerValue="请选择"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:submit /></td>
				</tr>
			</table>
		</s:form>
		<a href="${ctx}/actor-info">Back to Orders</a>
	</div>
</body>
</html>
