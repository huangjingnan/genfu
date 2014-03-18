<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfo ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<th>departmentName</th>
			<td>${departmentName}</td>
		</tr>
		<tr>
			<th>updateDate</th>
			<td>${updateDate}</td>
		</tr>
		<tr>
			<th>upId</th>
			<td>${upId}</td>
		</tr>
	</table>
	<a href="${ctx}/department">Back to UserInfos</a>
</body>
</html>
