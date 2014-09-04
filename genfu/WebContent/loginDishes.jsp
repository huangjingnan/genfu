
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>genfuReform</title>
</head>
<body>
	<s:actionmessage />
	<s:actionerror />
	<s:fielderror />
	<s:form method="post" action="login-dishes" namespace="/">
		<s:textfield label="userCode" name="userInfo.userCode"></s:textfield>
		<s:password label="userPassword" name="userInfo.userPassword"></s:password>


		<s:submit />
	</s:form>
</body>
</html>