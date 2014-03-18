
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
<jsp:include page="/include/editNewInclude.jsp" flush="true" />
<script type="text/javascript"
	src="${ctx}/js/jquery.simpledialog/jquery.simpledialog.0.1.js"></script>
<script type="text/javascript" src="${ctx}/js/genfu/genfu.js"></script>
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
						'${ctx}/user-info',
						'winSelectUpperUser',
						'height=500,width=900,top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
	}

	function selectRoles() {
		//alert(myRoleIds);
		var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

		window
				.open(
						'${ctx}/role-info',
						'winSelectRoles',
						'height=500,width=900,top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
		//alert(myRoleIds);

	}

	function setRoles(myRoleIds) {
		var oResult = "";
		try {
			oResult = eval("(" + myRoleIds + ")");
		} catch (e) {
		}

		var roleIds = '';
		for ( var i = 0; i < oResult.length; i++) {
			roleIds = roleIds + ',' + oResult[i].id;
		}
		document.getElementById("roleIds").value = roleIds.substring(1);
	}
</script>
</head>
<body>
	<s:actionmessage />
	<div class="demo">
		<s:form method="post" action="%{#ctx}/user-info" enctype="multipart/form-data">
			<table class="datepickers">
				<tr>
					<td><s:textfield name="id" label="id" /></td>
				</tr>
				<tr>
					<td><s:textfield name="departmentId" label="departmentId" />
					</td>
				</tr>
				<tr>
					<td><s:textfield name="districtId" label="districtId"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:textfield id="userUpperId" name="userUpperId"
							onclick="selectUpperUser();" cssStyle="display: none;">
						</s:textfield></td>
				</tr>
				<tr>
					<td onclick="selectUpperUser();">upperUser:</td>
					<td><span id="checkboxStatus"></span></td>
				</tr>
				<tr>
					<td><s:textfield id="userCode" name="userCode"
							label="userCode">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="userEffDate" name="userEffDate"
							label="userEffDate">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="userExpDate" name="userExpDate"
							label="userExpDate">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="userFlag" name="userFlag"
							label="userFlag">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="userName" name="userName"
							label="userName">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="userPassword" name="userPassword"
							label="userPassword">
						</s:textfield></td>
				</tr>
				<tr>
					<td><s:textarea name="userDescription" label="userDescription">
						</s:textarea></td>
				</tr>
				<tr>
					<td><s:textarea name="userOthers" label="userOthers">
						</s:textarea></td>
				</tr>
				<tr>
					<td><s:textarea id="roleIds" name="roleIds" label="roleIds"
							onclick="selectRoles();">
						</s:textarea></td>
				</tr>
				<tr>
					<td>
						<s:file name="userImage" label="User Image" />
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