
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />
<div id="target_content${nextAction}">
</div>
<div id="login_content${nextAction}">
	<s:actionmessage />
	<s:actionerror />
	<s:fielderror />
	<form method="post" id="relogin${nextAction}" action="%{#ctx}/re-login">
		<%-- <s:textfield label="userCode" name="userInfo.userCode"></s:textfield>
		<s:password label="userPassword" name="userInfo.userPassword"></s:password> --%>
		<input name="nextAction" type="hidden" value="${nextAction}" />
		<label for="userCode"> userCode </label><br /><input id="userCode" name="userCode" type="text" />
		<br />
		<label for="userPassword"> userPassword </label><br /><input id="userPassword" name="userPassword" type="password" />

		<%-- <s:submit /> --%>
		<input type="button" onclick="relogin()" value="确认"/>
	</form>
<script type="text/javascript">
	//<![CDATA[

	var nextAction = '${nextAction}';
	           
	function relogin(){
		$.ajax({
			url : '${ctx}/re-login',
			//async : false,
			data : $('#relogin'+nextAction).serialize(),
			type : "POST",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				try {
					var ret = JSON.parse(multiple.responseText);
					//重新登录
				} catch (e) {
					$("#target_content"+nextAction).html(multiple.responseText);
					$("#login_content"+nextAction).hide();
				}
			},
			complete : function() {
				//
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	
	//]]>	
</script>
</div>
