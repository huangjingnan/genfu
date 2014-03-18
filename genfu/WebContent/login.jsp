
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
	<s:form method="post" action="%{#ctx}/login">
		<s:textfield key="global.username" name="userInfo.userCode"></s:textfield>
		<s:password key="global.password" name="userInfo.userPassword"></s:password>


		<s:submit value="确认"></s:submit>
	</s:form>
	<s:url id="localeEN" namespace="/" action="login">
		<s:param name="request_locale">en</s:param>
	</s:url>
	<s:url id="localezhCN" namespace="/" action="login">
		<s:param name="request_locale">zh_CN</s:param>
	</s:url>
	<s:url id="localeDE" namespace="/" action="login">
		<s:param name="request_locale">de</s:param>
	</s:url>
	<s:url id="localeFR" namespace="/" action="login">
		<s:param name="request_locale">fr</s:param>
	</s:url>

<%-- 	<s:a href="%{localeEN}">English</s:a>
	<s:a href="%{localezhCN}">Chinese</s:a>
	<s:a href="%{localeDE}">German</s:a>
	<s:a href="%{localeFR}">France</s:a> --%>
</body>
</html>