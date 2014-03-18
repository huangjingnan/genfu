<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>actor-info ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<s:iterator value="NAVIGATION_NODE">
			<tr>
				<td>${id}</td><td>${naviEffDate}</td><td>${naviExpDate}</td><td>${naviText}</td>
			</tr>
				</s:iterator>
		</tr>
	</table>
</body>
</html>
