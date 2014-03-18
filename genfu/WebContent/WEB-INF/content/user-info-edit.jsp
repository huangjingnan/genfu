<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfo <s:property value="id" /></title>
<jsp:include page="/include/editInclude.jsp" flush="true" />
<script>
	$(function() {
		$("#userExpDate,#userEffDate")
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
		$("#userExpDate,#userEffDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
</script>
</head>
<body>
	<div class="demo">
		<s:form method="post" action="%{#ctx}/user-info/%{id}"
			enctype="multipart/form-data">
			<s:hidden name="_method" value="put" />
			<table class="datepickers">
				<tr>
					<td><p>
							<s:textfield name="id" label="id" />
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield name="departmentId" label="departmentId" />
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield name="districtId" label="districtId"></s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userUpperId" name="userUpperId"
								label="userUpperId">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userCode" name="userCode" label="userCode">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userEffDate" name="userEffDate"
								label="userEffDate">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userExpDate" name="userExpDate"
								label="userExpDate">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userFlag" name="userFlag" label="userFlag">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userName" name="userName" label="userName">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textfield id="userPassword" name="userPassword"
								label="userPassword">
							</s:textfield>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textarea name="userDescription" label="userDescription">
							</s:textarea>
						</p></td>
				</tr>
				<tr>
					<td><p>
							<s:textarea name="userOthers" label="userOthers">
							</s:textarea>
						</p></td>
				</tr>
				<tr>
					<td>
						<p>
							<s:file name="userImage" label="User Image" />
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:submit /></td>
				</tr>
			</table>
		</s:form>
		<a href="${ctx}/user-info">Back to Orders</a>
	</div>
</body>
</html>
